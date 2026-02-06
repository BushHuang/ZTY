package SevenZip.Compression.LZMA;

import SevenZip.Compression.LZ.OutWindow;
import SevenZip.Compression.RangeCoder.BitTreeDecoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Decoder {
    int m_PosStateMask;
    OutWindow m_OutWindow = new OutWindow();
    SevenZip.Compression.RangeCoder.Decoder m_RangeDecoder = new SevenZip.Compression.RangeCoder.Decoder();
    short[] m_IsMatchDecoders = new short[192];
    short[] m_IsRepDecoders = new short[12];
    short[] m_IsRepG0Decoders = new short[12];
    short[] m_IsRepG1Decoders = new short[12];
    short[] m_IsRepG2Decoders = new short[12];
    short[] m_IsRep0LongDecoders = new short[192];
    BitTreeDecoder[] m_PosSlotDecoder = new BitTreeDecoder[4];
    short[] m_PosDecoders = new short[114];
    BitTreeDecoder m_PosAlignDecoder = new BitTreeDecoder(4);
    LenDecoder m_LenDecoder = new LenDecoder();
    LenDecoder m_RepLenDecoder = new LenDecoder();
    LiteralDecoder m_LiteralDecoder = new LiteralDecoder();
    int m_DictionarySize = -1;
    int m_DictionarySizeCheck = -1;

    class LenDecoder {
        short[] m_Choice = new short[2];
        BitTreeDecoder[] m_LowCoder = new BitTreeDecoder[16];
        BitTreeDecoder[] m_MidCoder = new BitTreeDecoder[16];
        BitTreeDecoder m_HighCoder = new BitTreeDecoder(8);
        int m_NumPosStates = 0;

        LenDecoder() {
        }

        public void Create(int i) {
            while (true) {
                int i2 = this.m_NumPosStates;
                if (i2 >= i) {
                    return;
                }
                this.m_LowCoder[i2] = new BitTreeDecoder(3);
                this.m_MidCoder[this.m_NumPosStates] = new BitTreeDecoder(3);
                this.m_NumPosStates++;
            }
        }

        public int Decode(SevenZip.Compression.RangeCoder.Decoder decoder, int i) throws IOException {
            if (decoder.DecodeBit(this.m_Choice, 0) == 0) {
                return this.m_LowCoder[i].Decode(decoder);
            }
            return (decoder.DecodeBit(this.m_Choice, 1) == 0 ? this.m_MidCoder[i].Decode(decoder) : this.m_HighCoder.Decode(decoder) + 8) + 8;
        }

        public void Init() {
            SevenZip.Compression.RangeCoder.Decoder.InitBitModels(this.m_Choice);
            for (int i = 0; i < this.m_NumPosStates; i++) {
                this.m_LowCoder[i].Init();
                this.m_MidCoder[i].Init();
            }
            this.m_HighCoder.Init();
        }
    }

    class LiteralDecoder {
        Decoder2[] m_Coders;
        int m_NumPosBits;
        int m_NumPrevBits;
        int m_PosMask;

        class Decoder2 {
            short[] m_Decoders = new short[768];

            Decoder2() {
            }

            public byte DecodeNormal(SevenZip.Compression.RangeCoder.Decoder decoder) throws IOException {
                int iDecodeBit = 1;
                do {
                    iDecodeBit = decoder.DecodeBit(this.m_Decoders, iDecodeBit) | (iDecodeBit << 1);
                } while (iDecodeBit < 256);
                return (byte) iDecodeBit;
            }

            public byte DecodeWithMatchByte(SevenZip.Compression.RangeCoder.Decoder decoder, byte b) throws IOException {
                int iDecodeBit = 1;
                while (true) {
                    int i = (b >> 7) & 1;
                    b = (byte) (b << 1);
                    int iDecodeBit2 = decoder.DecodeBit(this.m_Decoders, ((i + 1) << 8) + iDecodeBit);
                    iDecodeBit = (iDecodeBit << 1) | iDecodeBit2;
                    if (i != iDecodeBit2) {
                        while (iDecodeBit < 256) {
                            iDecodeBit = (iDecodeBit << 1) | decoder.DecodeBit(this.m_Decoders, iDecodeBit);
                        }
                    } else if (iDecodeBit >= 256) {
                        break;
                    }
                }
                return (byte) iDecodeBit;
            }

            public void Init() {
                SevenZip.Compression.RangeCoder.Decoder.InitBitModels(this.m_Decoders);
            }
        }

        LiteralDecoder() {
        }

        public void Create(int i, int i2) {
            if (this.m_Coders != null && this.m_NumPrevBits == i2 && this.m_NumPosBits == i) {
                return;
            }
            this.m_NumPosBits = i;
            this.m_PosMask = (1 << i) - 1;
            this.m_NumPrevBits = i2;
            int i3 = 1 << (i2 + i);
            this.m_Coders = new Decoder2[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                this.m_Coders[i4] = new Decoder2();
            }
        }

        Decoder2 GetDecoder(int i, byte b) {
            Decoder2[] decoder2Arr = this.m_Coders;
            int i2 = i & this.m_PosMask;
            int i3 = this.m_NumPrevBits;
            return decoder2Arr[(i2 << i3) + ((b & 255) >>> (8 - i3))];
        }

        public void Init() {
            int i = 1 << (this.m_NumPrevBits + this.m_NumPosBits);
            for (int i2 = 0; i2 < i; i2++) {
                this.m_Coders[i2].Init();
            }
        }
    }

    public Decoder() {
        for (int i = 0; i < 4; i++) {
            this.m_PosSlotDecoder[i] = new BitTreeDecoder(6);
        }
    }

    public boolean Code(InputStream inputStream, OutputStream outputStream, long j) throws IOException {
        int iDecodeDirectBits;
        byte bGetByte;
        this.m_RangeDecoder.SetStream(inputStream);
        this.m_OutWindow.SetStream(outputStream);
        Init();
        int iStateInit = Base.StateInit();
        long j2 = 0;
        long j3 = 0;
        byte b = 0;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int iDecode = 1;
            if (j >= j2 && j3 >= j) {
                break;
            }
            int i5 = (int) j3;
            int i6 = this.m_PosStateMask & i5;
            int i7 = (iStateInit << 4) + i6;
            if (this.m_RangeDecoder.DecodeBit(this.m_IsMatchDecoders, i7) == 0) {
                LiteralDecoder.Decoder2 decoder2GetDecoder = this.m_LiteralDecoder.GetDecoder(i5, b);
                bGetByte = !Base.StateIsCharState(iStateInit) ? decoder2GetDecoder.DecodeWithMatchByte(this.m_RangeDecoder, this.m_OutWindow.GetByte(i3)) : decoder2GetDecoder.DecodeNormal(this.m_RangeDecoder);
                this.m_OutWindow.PutByte(bGetByte);
                iStateInit = Base.StateUpdateChar(iStateInit);
                j3++;
            } else {
                if (this.m_RangeDecoder.DecodeBit(this.m_IsRepDecoders, iStateInit) == 1) {
                    if (this.m_RangeDecoder.DecodeBit(this.m_IsRepG0Decoders, iStateInit) != 0) {
                        if (this.m_RangeDecoder.DecodeBit(this.m_IsRepG1Decoders, iStateInit) != 0) {
                            if (this.m_RangeDecoder.DecodeBit(this.m_IsRepG2Decoders, iStateInit) == 0) {
                                int i8 = i4;
                                i4 = i;
                                i = i8;
                            }
                            int i9 = i4;
                            i4 = i;
                            i = i2;
                            i2 = i9;
                        }
                        iDecode = 0;
                        int i10 = i3;
                        i3 = i2;
                        i2 = i10;
                    } else if (this.m_RangeDecoder.DecodeBit(this.m_IsRep0LongDecoders, i7) == 0) {
                        iStateInit = Base.StateUpdateShortRep(iStateInit);
                    } else {
                        iDecode = 0;
                    }
                    if (iDecode == 0) {
                        iDecode = this.m_RepLenDecoder.Decode(this.m_RangeDecoder, i6) + 2;
                        iStateInit = Base.StateUpdateRep(iStateInit);
                    }
                } else {
                    int iDecode2 = this.m_LenDecoder.Decode(this.m_RangeDecoder, i6) + 2;
                    iStateInit = Base.StateUpdateMatch(iStateInit);
                    int iDecode3 = this.m_PosSlotDecoder[Base.GetLenToPosState(iDecode2)].Decode(this.m_RangeDecoder);
                    if (iDecode3 >= 4) {
                        int i11 = (iDecode3 >> 1) - 1;
                        int i12 = ((iDecode3 & 1) | 2) << i11;
                        if (iDecode3 < 14) {
                            iDecodeDirectBits = i12 + BitTreeDecoder.ReverseDecode(this.m_PosDecoders, (i12 - iDecode3) - 1, this.m_RangeDecoder, i11);
                        } else {
                            iDecodeDirectBits = i12 + (this.m_RangeDecoder.DecodeDirectBits(i11 - 4) << 4) + this.m_PosAlignDecoder.ReverseDecode(this.m_RangeDecoder);
                            if (iDecodeDirectBits < 0) {
                                if (iDecodeDirectBits != -1) {
                                    return false;
                                }
                            }
                        }
                        iDecode = iDecode2;
                        i4 = i;
                        i = i2;
                        i2 = i3;
                        i3 = iDecodeDirectBits;
                    } else {
                        iDecode = iDecode2;
                        i4 = i;
                        i = i2;
                        i2 = i3;
                        i3 = iDecode3;
                    }
                }
                if (i3 >= j3 || i3 >= this.m_DictionarySizeCheck) {
                    break;
                }
                this.m_OutWindow.CopyBlock(i3, iDecode);
                j3 += iDecode;
                bGetByte = this.m_OutWindow.GetByte(0);
            }
            b = bGetByte;
            j2 = 0;
        }
        return false;
    }

    void Init() throws IOException {
        this.m_OutWindow.Init(false);
        SevenZip.Compression.RangeCoder.Decoder.InitBitModels(this.m_IsMatchDecoders);
        SevenZip.Compression.RangeCoder.Decoder.InitBitModels(this.m_IsRep0LongDecoders);
        SevenZip.Compression.RangeCoder.Decoder.InitBitModels(this.m_IsRepDecoders);
        SevenZip.Compression.RangeCoder.Decoder.InitBitModels(this.m_IsRepG0Decoders);
        SevenZip.Compression.RangeCoder.Decoder.InitBitModels(this.m_IsRepG1Decoders);
        SevenZip.Compression.RangeCoder.Decoder.InitBitModels(this.m_IsRepG2Decoders);
        SevenZip.Compression.RangeCoder.Decoder.InitBitModels(this.m_PosDecoders);
        this.m_LiteralDecoder.Init();
        for (int i = 0; i < 4; i++) {
            this.m_PosSlotDecoder[i].Init();
        }
        this.m_LenDecoder.Init();
        this.m_RepLenDecoder.Init();
        this.m_PosAlignDecoder.Init();
        this.m_RangeDecoder.Init();
    }

    public boolean SetDecoderProperties(byte[] bArr) {
        if (bArr.length < 5) {
            return false;
        }
        int i = bArr[0] & 255;
        int i2 = i % 9;
        int i3 = i / 9;
        int i4 = i3 % 5;
        int i5 = i3 / 5;
        int i6 = 0;
        int i7 = 0;
        while (i6 < 4) {
            int i8 = i6 + 1;
            i7 += (bArr[i8] & 255) << (i6 * 8);
            i6 = i8;
        }
        if (SetLcLpPb(i2, i4, i5)) {
            return SetDictionarySize(i7);
        }
        return false;
    }

    boolean SetDictionarySize(int i) {
        if (i < 0) {
            return false;
        }
        if (this.m_DictionarySize != i) {
            this.m_DictionarySize = i;
            int iMax = Math.max(i, 1);
            this.m_DictionarySizeCheck = iMax;
            this.m_OutWindow.Create(Math.max(iMax, 4096));
        }
        return true;
    }

    boolean SetLcLpPb(int i, int i2, int i3) {
        if (i > 8 || i2 > 4 || i3 > 4) {
            return false;
        }
        this.m_LiteralDecoder.Create(i2, i);
        int i4 = 1 << i3;
        this.m_LenDecoder.Create(i4);
        this.m_RepLenDecoder.Create(i4);
        this.m_PosStateMask = i4 - 1;
        return true;
    }
}
