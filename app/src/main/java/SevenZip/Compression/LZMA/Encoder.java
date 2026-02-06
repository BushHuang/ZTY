package SevenZip.Compression.LZMA;

import SevenZip.Compression.LZ.BinTree;
import SevenZip.Compression.RangeCoder.BitTreeEncoder;
import SevenZip.ICodeProgress;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Encoder {
    public static final int EMatchFinderTypeBT2 = 0;
    public static final int EMatchFinderTypeBT4 = 1;
    static byte[] g_FastPos = null;
    static final int kDefaultDictionaryLogSize = 22;
    static final int kIfinityPrice = 268435455;
    static final int kNumFastBytesDefault = 32;
    public static final int kNumLenSpecSymbols = 16;
    static final int kNumOpts = 4096;
    public static final int kPropSize = 5;
    int _additionalOffset;
    int _alignPriceCount;
    boolean _finished;
    InputStream _inStream;
    int _longestMatchLength;
    boolean _longestMatchWasFound;
    int _matchPriceCount;
    int _numDistancePairs;
    int _optimumCurrentIndex;
    int _optimumEndIndex;
    byte _previousByte;
    int backRes;
    long nowPos64;
    int _state = Base.StateInit();
    int[] _repDistances = new int[4];
    Optimal[] _optimum = new Optimal[4096];
    BinTree _matchFinder = null;
    SevenZip.Compression.RangeCoder.Encoder _rangeEncoder = new SevenZip.Compression.RangeCoder.Encoder();
    short[] _isMatch = new short[192];
    short[] _isRep = new short[12];
    short[] _isRepG0 = new short[12];
    short[] _isRepG1 = new short[12];
    short[] _isRepG2 = new short[12];
    short[] _isRep0Long = new short[192];
    BitTreeEncoder[] _posSlotEncoder = new BitTreeEncoder[4];
    short[] _posEncoders = new short[114];
    BitTreeEncoder _posAlignEncoder = new BitTreeEncoder(4);
    LenPriceTableEncoder _lenEncoder = new LenPriceTableEncoder();
    LenPriceTableEncoder _repMatchLenEncoder = new LenPriceTableEncoder();
    LiteralEncoder _literalEncoder = new LiteralEncoder();
    int[] _matchDistances = new int[548];
    int _numFastBytes = 32;
    int[] _posSlotPrices = new int[256];
    int[] _distancesPrices = new int[512];
    int[] _alignPrices = new int[16];
    int _distTableSize = 44;
    int _posStateBits = 2;
    int _posStateMask = 3;
    int _numLiteralPosStateBits = 0;
    int _numLiteralContextBits = 3;
    int _dictionarySize = 4194304;
    int _dictionarySizePrev = -1;
    int _numFastBytesPrev = -1;
    int _matchFinderType = 1;
    boolean _writeEndMark = false;
    boolean _needReleaseMFStream = false;
    int[] reps = new int[4];
    int[] repLens = new int[4];
    long[] processedInSize = new long[1];
    long[] processedOutSize = new long[1];
    boolean[] finished = new boolean[1];
    byte[] properties = new byte[5];
    int[] tempPrices = new int[128];

    class LenEncoder {
        short[] _choice = new short[2];
        BitTreeEncoder[] _lowCoder = new BitTreeEncoder[16];
        BitTreeEncoder[] _midCoder = new BitTreeEncoder[16];
        BitTreeEncoder _highCoder = new BitTreeEncoder(8);

        public LenEncoder() {
            for (int i = 0; i < 16; i++) {
                this._lowCoder[i] = new BitTreeEncoder(3);
                this._midCoder[i] = new BitTreeEncoder(3);
            }
        }

        public void Encode(SevenZip.Compression.RangeCoder.Encoder encoder, int i, int i2) throws IOException {
            if (i < 8) {
                encoder.Encode(this._choice, 0, 0);
                this._lowCoder[i2].Encode(encoder, i);
                return;
            }
            int i3 = i - 8;
            encoder.Encode(this._choice, 0, 1);
            if (i3 < 8) {
                encoder.Encode(this._choice, 1, 0);
                this._midCoder[i2].Encode(encoder, i3);
            } else {
                encoder.Encode(this._choice, 1, 1);
                this._highCoder.Encode(encoder, i3 - 8);
            }
        }

        public void Init(int i) {
            SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this._choice);
            for (int i2 = 0; i2 < i; i2++) {
                this._lowCoder[i2].Init();
                this._midCoder[i2].Init();
            }
            this._highCoder.Init();
        }

        public void SetPrices(int i, int i2, int[] iArr, int i3) {
            int i4 = 0;
            int iGetPrice0 = SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._choice[0]);
            int iGetPrice1 = SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._choice[0]);
            int iGetPrice02 = SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._choice[1]) + iGetPrice1;
            int iGetPrice12 = iGetPrice1 + SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._choice[1]);
            while (i4 < 8) {
                if (i4 >= i2) {
                    return;
                }
                iArr[i3 + i4] = this._lowCoder[i].GetPrice(i4) + iGetPrice0;
                i4++;
            }
            while (i4 < 16) {
                if (i4 >= i2) {
                    return;
                }
                iArr[i3 + i4] = this._midCoder[i].GetPrice(i4 - 8) + iGetPrice02;
                i4++;
            }
            while (i4 < i2) {
                iArr[i3 + i4] = this._highCoder.GetPrice((i4 - 8) - 8) + iGetPrice12;
                i4++;
            }
        }
    }

    class LenPriceTableEncoder extends LenEncoder {
        int[] _counters;
        int[] _prices;
        int _tableSize;

        LenPriceTableEncoder() {
            super();
            this._prices = new int[4352];
            this._counters = new int[16];
        }

        @Override
        public void Encode(SevenZip.Compression.RangeCoder.Encoder encoder, int i, int i2) throws IOException {
            super.Encode(encoder, i, i2);
            int[] iArr = this._counters;
            int i3 = iArr[i2] - 1;
            iArr[i2] = i3;
            if (i3 == 0) {
                UpdateTable(i2);
            }
        }

        public int GetPrice(int i, int i2) {
            return this._prices[(i2 * 272) + i];
        }

        public void SetTableSize(int i) {
            this._tableSize = i;
        }

        void UpdateTable(int i) {
            SetPrices(i, this._tableSize, this._prices, i * 272);
            this._counters[i] = this._tableSize;
        }

        public void UpdateTables(int i) {
            for (int i2 = 0; i2 < i; i2++) {
                UpdateTable(i2);
            }
        }
    }

    class LiteralEncoder {
        Encoder2[] m_Coders;
        int m_NumPosBits;
        int m_NumPrevBits;
        int m_PosMask;

        class Encoder2 {
            short[] m_Encoders = new short[768];

            Encoder2() {
            }

            public void Encode(SevenZip.Compression.RangeCoder.Encoder encoder, byte b) throws IOException {
                int i = 1;
                for (int i2 = 7; i2 >= 0; i2--) {
                    int i3 = (b >> i2) & 1;
                    encoder.Encode(this.m_Encoders, i, i3);
                    i = (i << 1) | i3;
                }
            }

            public void EncodeMatched(SevenZip.Compression.RangeCoder.Encoder encoder, byte b, byte b2) throws IOException {
                int i;
                int i2 = 1;
                boolean z = true;
                for (int i3 = 7; i3 >= 0; i3--) {
                    int i4 = (b2 >> i3) & 1;
                    if (z) {
                        int i5 = (b >> i3) & 1;
                        i = ((i5 + 1) << 8) + i2;
                        z = i5 == i4;
                    } else {
                        i = i2;
                    }
                    encoder.Encode(this.m_Encoders, i, i4);
                    i2 = (i2 << 1) | i4;
                }
            }

            public int GetPrice(boolean z, byte b, byte b2) {
                int i;
                int iGetPrice = 0;
                int i2 = 7;
                if (z) {
                    i = 1;
                    while (i2 >= 0) {
                        int i3 = (b >> i2) & 1;
                        int i4 = (b2 >> i2) & 1;
                        iGetPrice += SevenZip.Compression.RangeCoder.Encoder.GetPrice(this.m_Encoders[((i3 + 1) << 8) + i], i4);
                        i = (i << 1) | i4;
                        if (i3 != i4) {
                            break;
                        }
                        i2--;
                    }
                } else {
                    i = 1;
                }
                if (i2 < 0) {
                    int i5 = (b2 >> i2) & 1;
                    iGetPrice += SevenZip.Compression.RangeCoder.Encoder.GetPrice(this.m_Encoders[i], i5);
                    i = (i << 1) | i5;
                    i2--;
                    if (i2 < 0) {
                        return iGetPrice;
                    }
                }
            }

            public void Init() {
                SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this.m_Encoders);
            }
        }

        LiteralEncoder() {
        }

        public void Create(int i, int i2) {
            if (this.m_Coders != null && this.m_NumPrevBits == i2 && this.m_NumPosBits == i) {
                return;
            }
            this.m_NumPosBits = i;
            this.m_PosMask = (1 << i) - 1;
            this.m_NumPrevBits = i2;
            int i3 = 1 << (i2 + i);
            this.m_Coders = new Encoder2[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                this.m_Coders[i4] = new Encoder2();
            }
        }

        public Encoder2 GetSubCoder(int i, byte b) {
            Encoder2[] encoder2Arr = this.m_Coders;
            int i2 = i & this.m_PosMask;
            int i3 = this.m_NumPrevBits;
            return encoder2Arr[(i2 << i3) + ((b & 255) >>> (8 - i3))];
        }

        public void Init() {
            int i = 1 << (this.m_NumPrevBits + this.m_NumPosBits);
            for (int i2 = 0; i2 < i; i2++) {
                this.m_Coders[i2].Init();
            }
        }
    }

    class Optimal {
        public int BackPrev;
        public int BackPrev2;
        public int Backs0;
        public int Backs1;
        public int Backs2;
        public int Backs3;
        public int PosPrev;
        public int PosPrev2;
        public boolean Prev1IsChar;
        public boolean Prev2;
        public int Price;
        public int State;

        Optimal() {
        }

        public boolean IsShortRep() {
            return this.BackPrev == 0;
        }

        public void MakeAsChar() {
            this.BackPrev = -1;
            this.Prev1IsChar = false;
        }

        public void MakeAsShortRep() {
            this.BackPrev = 0;
            this.Prev1IsChar = false;
        }
    }

    static {
        byte[] bArr = new byte[2048];
        g_FastPos = bArr;
        bArr[0] = 0;
        bArr[1] = 1;
        int i = 2;
        for (int i2 = 2; i2 < 22; i2++) {
            int i3 = 1 << ((i2 >> 1) - 1);
            int i4 = 0;
            while (i4 < i3) {
                g_FastPos[i] = (byte) i2;
                i4++;
                i++;
            }
        }
    }

    public Encoder() {
        for (int i = 0; i < 4096; i++) {
            this._optimum[i] = new Optimal();
        }
        for (int i2 = 0; i2 < 4; i2++) {
            this._posSlotEncoder[i2] = new BitTreeEncoder(6);
        }
    }

    static int GetPosSlot(int i) {
        return i < 2048 ? g_FastPos[i] : i < 2097152 ? g_FastPos[i >> 10] + 20 : g_FastPos[i >> 20] + 40;
    }

    static int GetPosSlot2(int i) {
        return i < 131072 ? g_FastPos[i >> 6] + 12 : i < 134217728 ? g_FastPos[i >> 16] + 32 : g_FastPos[i >> 26] + 52;
    }

    int Backward(int i) {
        this._optimumEndIndex = i;
        int i2 = this._optimum[i].PosPrev;
        int i3 = this._optimum[i].BackPrev;
        while (true) {
            if (this._optimum[i].Prev1IsChar) {
                this._optimum[i2].MakeAsChar();
                int i4 = i2 - 1;
                this._optimum[i2].PosPrev = i4;
                if (this._optimum[i].Prev2) {
                    this._optimum[i4].Prev1IsChar = false;
                    Optimal[] optimalArr = this._optimum;
                    optimalArr[i4].PosPrev = optimalArr[i].PosPrev2;
                    Optimal[] optimalArr2 = this._optimum;
                    optimalArr2[i4].BackPrev = optimalArr2[i].BackPrev2;
                }
            }
            int i5 = this._optimum[i2].BackPrev;
            int i6 = this._optimum[i2].PosPrev;
            this._optimum[i2].BackPrev = i3;
            this._optimum[i2].PosPrev = i;
            if (i2 <= 0) {
                this.backRes = this._optimum[0].BackPrev;
                int i7 = this._optimum[0].PosPrev;
                this._optimumCurrentIndex = i7;
                return i7;
            }
            i = i2;
            i3 = i5;
            i2 = i6;
        }
    }

    void BaseInit() {
        this._state = Base.StateInit();
        this._previousByte = (byte) 0;
        for (int i = 0; i < 4; i++) {
            this._repDistances[i] = 0;
        }
    }

    boolean ChangePair(int i, int i2) {
        return i < 33554432 && i2 >= (i << 7);
    }

    public void Code(InputStream inputStream, OutputStream outputStream, long j, long j2, ICodeProgress iCodeProgress) throws IOException {
        this._needReleaseMFStream = false;
        try {
            SetStreams(inputStream, outputStream, j, j2);
            while (true) {
                CodeOneBlock(this.processedInSize, this.processedOutSize, this.finished);
                if (this.finished[0]) {
                    return;
                }
                if (iCodeProgress != null) {
                    iCodeProgress.SetProgress(this.processedInSize[0], this.processedOutSize[0]);
                }
            }
        } finally {
            ReleaseStreams();
        }
    }

    public void CodeOneBlock(long[] jArr, long[] jArr2, boolean[] zArr) throws IOException {
        jArr[0] = 0;
        jArr2[0] = 0;
        zArr[0] = true;
        InputStream inputStream = this._inStream;
        if (inputStream != null) {
            this._matchFinder.SetStream(inputStream);
            this._matchFinder.Init();
            this._needReleaseMFStream = true;
            this._inStream = null;
        }
        if (this._finished) {
            return;
        }
        this._finished = true;
        long j = this.nowPos64;
        if (j == 0) {
            if (this._matchFinder.GetNumAvailableBytes() == 0) {
                Flush((int) this.nowPos64);
                return;
            }
            ReadMatchDistances();
            this._rangeEncoder.Encode(this._isMatch, (this._state << 4) + (this._posStateMask & ((int) this.nowPos64)), 0);
            this._state = Base.StateUpdateChar(this._state);
            byte bGetIndexByte = this._matchFinder.GetIndexByte(0 - this._additionalOffset);
            this._literalEncoder.GetSubCoder((int) this.nowPos64, this._previousByte).Encode(this._rangeEncoder, bGetIndexByte);
            this._previousByte = bGetIndexByte;
            this._additionalOffset--;
            this.nowPos64++;
        }
        if (this._matchFinder.GetNumAvailableBytes() == 0) {
            Flush((int) this.nowPos64);
            return;
        }
        while (true) {
            int iGetOptimum = GetOptimum((int) this.nowPos64);
            int i = this.backRes;
            int i2 = this._posStateMask & ((int) this.nowPos64);
            int i3 = (this._state << 4) + i2;
            if (iGetOptimum == 1 && i == -1) {
                this._rangeEncoder.Encode(this._isMatch, i3, 0);
                byte bGetIndexByte2 = this._matchFinder.GetIndexByte(0 - this._additionalOffset);
                LiteralEncoder.Encoder2 encoder2GetSubCoder = this._literalEncoder.GetSubCoder((int) this.nowPos64, this._previousByte);
                if (Base.StateIsCharState(this._state)) {
                    encoder2GetSubCoder.Encode(this._rangeEncoder, bGetIndexByte2);
                } else {
                    encoder2GetSubCoder.EncodeMatched(this._rangeEncoder, this._matchFinder.GetIndexByte(((0 - this._repDistances[0]) - 1) - this._additionalOffset), bGetIndexByte2);
                }
                this._previousByte = bGetIndexByte2;
                this._state = Base.StateUpdateChar(this._state);
            } else {
                this._rangeEncoder.Encode(this._isMatch, i3, 1);
                if (i < 4) {
                    this._rangeEncoder.Encode(this._isRep, this._state, 1);
                    if (i == 0) {
                        this._rangeEncoder.Encode(this._isRepG0, this._state, 0);
                        if (iGetOptimum == 1) {
                            this._rangeEncoder.Encode(this._isRep0Long, i3, 0);
                        } else {
                            this._rangeEncoder.Encode(this._isRep0Long, i3, 1);
                        }
                    } else {
                        this._rangeEncoder.Encode(this._isRepG0, this._state, 1);
                        if (i == 1) {
                            this._rangeEncoder.Encode(this._isRepG1, this._state, 0);
                        } else {
                            this._rangeEncoder.Encode(this._isRepG1, this._state, 1);
                            this._rangeEncoder.Encode(this._isRepG2, this._state, i - 2);
                        }
                    }
                    if (iGetOptimum == 1) {
                        this._state = Base.StateUpdateShortRep(this._state);
                    } else {
                        this._repMatchLenEncoder.Encode(this._rangeEncoder, iGetOptimum - 2, i2);
                        this._state = Base.StateUpdateRep(this._state);
                    }
                    int i4 = this._repDistances[i];
                    if (i != 0) {
                        while (i >= 1) {
                            int[] iArr = this._repDistances;
                            iArr[i] = iArr[i - 1];
                            i--;
                        }
                        this._repDistances[0] = i4;
                    }
                } else {
                    this._rangeEncoder.Encode(this._isRep, this._state, 0);
                    this._state = Base.StateUpdateMatch(this._state);
                    this._lenEncoder.Encode(this._rangeEncoder, iGetOptimum - 2, i2);
                    int i5 = i - 4;
                    int iGetPosSlot = GetPosSlot(i5);
                    this._posSlotEncoder[Base.GetLenToPosState(iGetOptimum)].Encode(this._rangeEncoder, iGetPosSlot);
                    if (iGetPosSlot >= 4) {
                        int i6 = (iGetPosSlot >> 1) - 1;
                        int i7 = ((iGetPosSlot & 1) | 2) << i6;
                        int i8 = i5 - i7;
                        if (iGetPosSlot < 14) {
                            BitTreeEncoder.ReverseEncode(this._posEncoders, (i7 - iGetPosSlot) - 1, this._rangeEncoder, i6, i8);
                        } else {
                            this._rangeEncoder.EncodeDirectBits(i8 >> 4, i6 - 4);
                            this._posAlignEncoder.ReverseEncode(this._rangeEncoder, i8 & 15);
                            this._alignPriceCount++;
                        }
                    }
                    for (int i9 = 3; i9 >= 1; i9--) {
                        int[] iArr2 = this._repDistances;
                        iArr2[i9] = iArr2[i9 - 1];
                    }
                    this._repDistances[0] = i5;
                    this._matchPriceCount++;
                }
                this._previousByte = this._matchFinder.GetIndexByte((iGetOptimum - 1) - this._additionalOffset);
            }
            int i10 = this._additionalOffset - iGetOptimum;
            this._additionalOffset = i10;
            this.nowPos64 += iGetOptimum;
            if (i10 == 0) {
                if (this._matchPriceCount >= 128) {
                    FillDistancesPrices();
                }
                if (this._alignPriceCount >= 16) {
                    FillAlignPrices();
                }
                jArr[0] = this.nowPos64;
                jArr2[0] = this._rangeEncoder.GetProcessedSizeAdd();
                if (this._matchFinder.GetNumAvailableBytes() == 0) {
                    Flush((int) this.nowPos64);
                    return;
                } else if (this.nowPos64 - j >= 4096) {
                    this._finished = false;
                    zArr[0] = false;
                    return;
                }
            }
        }
    }

    void Create() {
        if (this._matchFinder == null) {
            BinTree binTree = new BinTree();
            binTree.SetType(this._matchFinderType == 0 ? 2 : 4);
            this._matchFinder = binTree;
        }
        this._literalEncoder.Create(this._numLiteralPosStateBits, this._numLiteralContextBits);
        if (this._dictionarySize == this._dictionarySizePrev && this._numFastBytesPrev == this._numFastBytes) {
            return;
        }
        this._matchFinder.Create(this._dictionarySize, 4096, this._numFastBytes, 274);
        this._dictionarySizePrev = this._dictionarySize;
        this._numFastBytesPrev = this._numFastBytes;
    }

    void FillAlignPrices() {
        for (int i = 0; i < 16; i++) {
            this._alignPrices[i] = this._posAlignEncoder.ReverseGetPrice(i);
        }
        this._alignPriceCount = 0;
    }

    void FillDistancesPrices() {
        for (int i = 4; i < 128; i++) {
            int iGetPosSlot = GetPosSlot(i);
            int i2 = (iGetPosSlot >> 1) - 1;
            this.tempPrices[i] = BitTreeEncoder.ReverseGetPrice(this._posEncoders, (r4 - iGetPosSlot) - 1, i2, i - (((iGetPosSlot & 1) | 2) << i2));
        }
        for (int i3 = 0; i3 < 4; i3++) {
            BitTreeEncoder bitTreeEncoder = this._posSlotEncoder[i3];
            int i4 = i3 << 6;
            for (int i5 = 0; i5 < this._distTableSize; i5++) {
                this._posSlotPrices[i4 + i5] = bitTreeEncoder.GetPrice(i5);
            }
            for (int i6 = 14; i6 < this._distTableSize; i6++) {
                int[] iArr = this._posSlotPrices;
                int i7 = i4 + i6;
                iArr[i7] = iArr[i7] + ((((i6 >> 1) - 1) - 4) << 6);
            }
            int i8 = i3 * 128;
            int i9 = 0;
            while (i9 < 4) {
                this._distancesPrices[i8 + i9] = this._posSlotPrices[i4 + i9];
                i9++;
            }
            while (i9 < 128) {
                this._distancesPrices[i8 + i9] = this._posSlotPrices[GetPosSlot(i9) + i4] + this.tempPrices[i9];
                i9++;
            }
        }
        this._matchPriceCount = 0;
    }

    void Flush(int i) throws IOException {
        ReleaseMFStream();
        WriteEndMarker(i & this._posStateMask);
        this._rangeEncoder.FlushData();
        this._rangeEncoder.FlushStream();
    }

    int GetOptimum(int i) throws IOException {
        int iReadMatchDistances;
        int i2;
        int iStateUpdateChar;
        int i3;
        int i4;
        int iStateUpdateRep;
        int iStateUpdateShortRep;
        int i5;
        boolean z;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int[] iArr;
        int i19;
        int i20;
        int i21;
        int iStateUpdateRep2;
        int iGetRepLen1Price;
        int i22 = i;
        int i23 = this._optimumEndIndex;
        int i24 = this._optimumCurrentIndex;
        if (i23 != i24) {
            int i25 = this._optimum[i24].PosPrev;
            int i26 = this._optimumCurrentIndex;
            int i27 = i25 - i26;
            this.backRes = this._optimum[i26].BackPrev;
            this._optimumCurrentIndex = this._optimum[this._optimumCurrentIndex].PosPrev;
            return i27;
        }
        char c = 0;
        this._optimumEndIndex = 0;
        this._optimumCurrentIndex = 0;
        if (this._longestMatchWasFound) {
            iReadMatchDistances = this._longestMatchLength;
            this._longestMatchWasFound = false;
        } else {
            iReadMatchDistances = ReadMatchDistances();
        }
        int i28 = this._numDistancePairs;
        int i29 = 2;
        if (this._matchFinder.GetNumAvailableBytes() + 1 < 2) {
            this.backRes = -1;
            return 1;
        }
        int i30 = 0;
        int i31 = 0;
        while (true) {
            if (i30 >= 4) {
                break;
            }
            int[] iArr2 = this.reps;
            iArr2[i30] = this._repDistances[i30];
            this.repLens[i30] = this._matchFinder.GetMatchLen(-1, iArr2[i30], 273);
            int[] iArr3 = this.repLens;
            if (iArr3[i30] > iArr3[i31]) {
                i31 = i30;
            }
            i30++;
        }
        int[] iArr4 = this.repLens;
        int i32 = iArr4[i31];
        int i33 = this._numFastBytes;
        if (i32 >= i33) {
            this.backRes = i31;
            int i34 = iArr4[i31];
            MovePos(i34 - 1);
            return i34;
        }
        if (iReadMatchDistances >= i33) {
            this.backRes = this._matchDistances[i28 - 1] + 4;
            MovePos(iReadMatchDistances - 1);
            return iReadMatchDistances;
        }
        byte bGetIndexByte = this._matchFinder.GetIndexByte(-1);
        byte bGetIndexByte2 = this._matchFinder.GetIndexByte(((0 - this._repDistances[0]) - 1) - 1);
        if (iReadMatchDistances < 2 && bGetIndexByte != bGetIndexByte2 && this.repLens[i31] < 2) {
            this.backRes = -1;
            return 1;
        }
        this._optimum[0].State = this._state;
        int i35 = this._posStateMask & i22;
        this._optimum[1].Price = SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._isMatch[(this._state << 4) + i35]) + this._literalEncoder.GetSubCoder(i22, this._previousByte).GetPrice(!Base.StateIsCharState(this._state), bGetIndexByte2, bGetIndexByte);
        this._optimum[1].MakeAsChar();
        int iGetPrice1 = SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._isMatch[(this._state << 4) + i35]);
        int iGetPrice12 = SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._isRep[this._state]) + iGetPrice1;
        if (bGetIndexByte2 == bGetIndexByte && (iGetRepLen1Price = GetRepLen1Price(this._state, i35) + iGetPrice12) < this._optimum[1].Price) {
            this._optimum[1].Price = iGetRepLen1Price;
            this._optimum[1].MakeAsShortRep();
        }
        int[] iArr5 = this.repLens;
        int i36 = iReadMatchDistances >= iArr5[i31] ? iReadMatchDistances : iArr5[i31];
        if (i36 < 2) {
            this.backRes = this._optimum[1].BackPrev;
            return 1;
        }
        this._optimum[1].PosPrev = 0;
        this._optimum[0].Backs0 = this.reps[0];
        this._optimum[0].Backs1 = this.reps[1];
        this._optimum[0].Backs2 = this.reps[2];
        this._optimum[0].Backs3 = this.reps[3];
        int i37 = i36;
        while (true) {
            int i38 = i37 - 1;
            this._optimum[i37].Price = 268435455;
            if (i38 < 2) {
                break;
            }
            i37 = i38;
        }
        int i39 = 0;
        for (i2 = 4; i39 < i2; i2 = 4) {
            int i40 = this.repLens[i39];
            if (i40 >= 2) {
                int iGetPureRepPrice = GetPureRepPrice(i39, this._state, i35) + iGetPrice12;
                do {
                    int iGetPrice = this._repMatchLenEncoder.GetPrice(i40 - 2, i35) + iGetPureRepPrice;
                    Optimal optimal = this._optimum[i40];
                    if (iGetPrice < optimal.Price) {
                        optimal.Price = iGetPrice;
                        optimal.PosPrev = 0;
                        optimal.BackPrev = i39;
                        optimal.Prev1IsChar = false;
                    }
                    i40--;
                } while (i40 >= 2);
            }
            i39++;
        }
        int iGetPrice0 = iGetPrice1 + SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._isRep[this._state]);
        int[] iArr6 = this.repLens;
        int i41 = iArr6[0] >= 2 ? iArr6[0] + 1 : 2;
        if (i41 <= iReadMatchDistances) {
            int i42 = 0;
            while (i41 > this._matchDistances[i42]) {
                i42 += 2;
            }
            while (true) {
                int i43 = this._matchDistances[i42 + 1];
                int iGetPosLenPrice = GetPosLenPrice(i43, i41, i35) + iGetPrice0;
                Optimal optimal2 = this._optimum[i41];
                if (iGetPosLenPrice < optimal2.Price) {
                    optimal2.Price = iGetPosLenPrice;
                    optimal2.PosPrev = 0;
                    optimal2.BackPrev = i43 + 4;
                    optimal2.Prev1IsChar = false;
                }
                if (i41 == this._matchDistances[i42] && (i42 = i42 + 2) == i28) {
                    break;
                }
                i41++;
            }
        }
        int i44 = 0;
        ?? r4 = 1;
        while (true) {
            i44 += r4;
            if (i44 == i36) {
                return Backward(i44);
            }
            int iReadMatchDistances2 = ReadMatchDistances();
            int i45 = this._numDistancePairs;
            if (iReadMatchDistances2 >= this._numFastBytes) {
                this._longestMatchLength = iReadMatchDistances2;
                this._longestMatchWasFound = r4;
                return Backward(i44);
            }
            i22 += r4;
            int i46 = this._optimum[i44].PosPrev;
            if (this._optimum[i44].Prev1IsChar) {
                i46--;
                if (this._optimum[i44].Prev2) {
                    Optimal[] optimalArr = this._optimum;
                    int i47 = optimalArr[optimalArr[i44].PosPrev2].State;
                    iStateUpdateRep2 = this._optimum[i44].BackPrev2 < 4 ? Base.StateUpdateRep(i47) : Base.StateUpdateMatch(i47);
                } else {
                    iStateUpdateRep2 = this._optimum[i46].State;
                }
                iStateUpdateChar = Base.StateUpdateChar(iStateUpdateRep2);
            } else {
                iStateUpdateChar = this._optimum[i46].State;
            }
            if (i46 == i44 - 1) {
                iStateUpdateShortRep = this._optimum[i44].IsShortRep() ? Base.StateUpdateShortRep(iStateUpdateChar) : Base.StateUpdateChar(iStateUpdateChar);
            } else {
                if (this._optimum[i44].Prev1IsChar && this._optimum[i44].Prev2) {
                    i46 = this._optimum[i44].PosPrev2;
                    i3 = this._optimum[i44].BackPrev2;
                    iStateUpdateRep = Base.StateUpdateRep(iStateUpdateChar);
                    i4 = 4;
                } else {
                    i3 = this._optimum[i44].BackPrev;
                    i4 = 4;
                    iStateUpdateRep = i3 < 4 ? Base.StateUpdateRep(iStateUpdateChar) : Base.StateUpdateMatch(iStateUpdateChar);
                }
                Optimal optimal3 = this._optimum[i46];
                if (i3 >= i4) {
                    int[] iArr7 = this.reps;
                    iArr7[c] = i3 - 4;
                    iArr7[1] = optimal3.Backs0;
                    this.reps[i29] = optimal3.Backs1;
                    this.reps[3] = optimal3.Backs2;
                } else if (i3 == 0) {
                    this.reps[c] = optimal3.Backs0;
                    this.reps[1] = optimal3.Backs1;
                    this.reps[i29] = optimal3.Backs2;
                    this.reps[3] = optimal3.Backs3;
                } else if (i3 == 1) {
                    this.reps[c] = optimal3.Backs1;
                    this.reps[1] = optimal3.Backs0;
                    this.reps[i29] = optimal3.Backs2;
                    this.reps[3] = optimal3.Backs3;
                } else if (i3 == i29) {
                    this.reps[c] = optimal3.Backs2;
                    this.reps[1] = optimal3.Backs0;
                    this.reps[i29] = optimal3.Backs1;
                    this.reps[3] = optimal3.Backs3;
                } else {
                    this.reps[c] = optimal3.Backs3;
                    this.reps[1] = optimal3.Backs0;
                    this.reps[i29] = optimal3.Backs1;
                    this.reps[3] = optimal3.Backs2;
                }
                iStateUpdateShortRep = iStateUpdateRep;
            }
            this._optimum[i44].State = iStateUpdateShortRep;
            this._optimum[i44].Backs0 = this.reps[c];
            this._optimum[i44].Backs1 = this.reps[1];
            this._optimum[i44].Backs2 = this.reps[i29];
            this._optimum[i44].Backs3 = this.reps[3];
            int i48 = this._optimum[i44].Price;
            byte bGetIndexByte3 = this._matchFinder.GetIndexByte(-1);
            byte bGetIndexByte4 = this._matchFinder.GetIndexByte(((0 - this.reps[c]) - 1) - 1);
            int i49 = this._posStateMask & i22;
            int i50 = (iStateUpdateShortRep << 4) + i49;
            int iGetPrice02 = SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._isMatch[i50]) + i48 + this._literalEncoder.GetSubCoder(i22, this._matchFinder.GetIndexByte(-2)).GetPrice(!Base.StateIsCharState(iStateUpdateShortRep), bGetIndexByte4, bGetIndexByte3);
            int i51 = i44 + 1;
            Optimal optimal4 = this._optimum[i51];
            if (iGetPrice02 < optimal4.Price) {
                optimal4.Price = iGetPrice02;
                optimal4.PosPrev = i44;
                optimal4.MakeAsChar();
                i5 = i36;
                z = true;
            } else {
                i5 = i36;
                z = false;
            }
            int iGetPrice13 = i48 + SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._isMatch[i50]);
            int iGetPrice14 = SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._isRep[iStateUpdateShortRep]) + iGetPrice13;
            if (bGetIndexByte4 != bGetIndexByte3 || (optimal4.PosPrev < i44 && optimal4.BackPrev == 0)) {
                i6 = i45;
            } else {
                int iGetRepLen1Price2 = GetRepLen1Price(iStateUpdateShortRep, i49) + iGetPrice14;
                i6 = i45;
                if (iGetRepLen1Price2 <= optimal4.Price) {
                    optimal4.Price = iGetRepLen1Price2;
                    optimal4.PosPrev = i44;
                    optimal4.MakeAsShortRep();
                    z = true;
                }
            }
            int iMin = Math.min(4095 - i44, this._matchFinder.GetNumAvailableBytes() + 1);
            if (iMin < 2) {
                i36 = i5;
            } else {
                int i52 = this._numFastBytes;
                if (iMin <= i52) {
                    i52 = iMin;
                }
                if (z || bGetIndexByte4 == bGetIndexByte3) {
                    i7 = iReadMatchDistances2;
                    i8 = iGetPrice13;
                    i9 = i5;
                    i10 = 2;
                    i11 = 0;
                    while (i11 < 4) {
                        int iGetMatchLen = this._matchFinder.GetMatchLen(-1, this.reps[i11], i52);
                        if (iGetMatchLen < 2) {
                            i19 = i22;
                            i20 = iGetPrice14;
                        } else {
                            int i53 = iGetMatchLen;
                            while (true) {
                                int i54 = i44 + i53;
                                if (i9 >= i54) {
                                    int iGetRepPrice = GetRepPrice(i11, i53, iStateUpdateShortRep, i49) + iGetPrice14;
                                    Optimal optimal5 = this._optimum[i54];
                                    if (iGetRepPrice < optimal5.Price) {
                                        optimal5.Price = iGetRepPrice;
                                        optimal5.PosPrev = i44;
                                        optimal5.BackPrev = i11;
                                        optimal5.Prev1IsChar = false;
                                    }
                                    i53--;
                                    if (i53 < 2) {
                                        break;
                                    }
                                } else {
                                    i9++;
                                    this._optimum[i9].Price = 268435455;
                                }
                            }
                            if (i11 == 0) {
                                i10 = iGetMatchLen + 1;
                            }
                            if (iGetMatchLen < iMin) {
                                int iGetMatchLen2 = this._matchFinder.GetMatchLen(iGetMatchLen, this.reps[i11], Math.min((iMin - 1) - iGetMatchLen, this._numFastBytes));
                                if (iGetMatchLen2 >= 2) {
                                    int iStateUpdateRep3 = Base.StateUpdateRep(iStateUpdateShortRep);
                                    int i55 = i22 + iGetMatchLen;
                                    i20 = iGetPrice14;
                                    int i56 = i10;
                                    int i57 = iGetMatchLen - 1;
                                    i19 = i22;
                                    int iGetRepPrice2 = iGetPrice14 + GetRepPrice(i11, iGetMatchLen, iStateUpdateShortRep, i49) + SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._isMatch[(iStateUpdateRep3 << 4) + (this._posStateMask & i55)]) + this._literalEncoder.GetSubCoder(i55, this._matchFinder.GetIndexByte(i57 - 1)).GetPrice(true, this._matchFinder.GetIndexByte(i57 - (this.reps[i11] + 1)), this._matchFinder.GetIndexByte(i57));
                                    int iStateUpdateChar2 = Base.StateUpdateChar(iStateUpdateRep3);
                                    int i58 = this._posStateMask & (i55 + 1);
                                    int iGetPrice15 = iGetRepPrice2 + SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._isMatch[(iStateUpdateChar2 << 4) + i58]) + SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._isRep[iStateUpdateChar2]);
                                    int i59 = iGetMatchLen + 1 + iGetMatchLen2;
                                    i9 = i9;
                                    while (true) {
                                        i21 = i44 + i59;
                                        if (i9 >= i21) {
                                            break;
                                        }
                                        i9++;
                                        this._optimum[i9].Price = 268435455;
                                    }
                                    int iGetRepPrice3 = iGetPrice15 + GetRepPrice(0, iGetMatchLen2, iStateUpdateChar2, i58);
                                    Optimal optimal6 = this._optimum[i21];
                                    if (iGetRepPrice3 < optimal6.Price) {
                                        optimal6.Price = iGetRepPrice3;
                                        optimal6.PosPrev = iGetMatchLen + i44 + 1;
                                        optimal6.BackPrev = 0;
                                        optimal6.Prev1IsChar = true;
                                        optimal6.Prev2 = true;
                                        optimal6.PosPrev2 = i44;
                                        optimal6.BackPrev2 = i11;
                                    }
                                    i10 = i56;
                                } else {
                                    i19 = i22;
                                    i20 = iGetPrice14;
                                    i10 = i10;
                                    i9 = i9;
                                }
                            }
                        }
                        i11++;
                        iGetPrice14 = i20;
                        i22 = i19;
                    }
                    int i60 = i22;
                    i12 = i7;
                    if (i12 <= i52) {
                        int i61 = 0;
                        while (true) {
                            iArr = this._matchDistances;
                            if (i52 <= iArr[i61]) {
                                break;
                            }
                            i61 += 2;
                        }
                        iArr[i61] = i52;
                        int i62 = i52;
                        i13 = i61 + 2;
                        i12 = i62;
                    } else {
                        i13 = i6;
                    }
                    if (i12 < i10) {
                        int iGetPrice03 = i8 + SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._isRep[iStateUpdateShortRep]);
                        while (i9 < i44 + i12) {
                            i9++;
                            this._optimum[i9].Price = 268435455;
                        }
                        int i63 = 0;
                        while (i10 > this._matchDistances[i63]) {
                            i63 += 2;
                        }
                        while (true) {
                            int i64 = this._matchDistances[i63 + 1];
                            int iGetPosLenPrice2 = GetPosLenPrice(i64, i10, i49) + iGetPrice03;
                            int i65 = i44 + i10;
                            Optimal optimal7 = this._optimum[i65];
                            if (iGetPosLenPrice2 < optimal7.Price) {
                                optimal7.Price = iGetPosLenPrice2;
                                optimal7.PosPrev = i44;
                                optimal7.BackPrev = i64 + 4;
                                optimal7.Prev1IsChar = false;
                            }
                            if (i10 != this._matchDistances[i63]) {
                                i14 = iMin;
                                i15 = iStateUpdateShortRep;
                                i16 = iGetPrice03;
                                i17 = i49;
                            } else if (i10 < iMin) {
                                int iGetMatchLen3 = this._matchFinder.GetMatchLen(i10, i64, Math.min((iMin - 1) - i10, this._numFastBytes));
                                if (iGetMatchLen3 >= 2) {
                                    int iStateUpdateMatch = Base.StateUpdateMatch(iStateUpdateShortRep);
                                    int i66 = i60 + i10;
                                    i14 = iMin;
                                    i15 = iStateUpdateShortRep;
                                    i16 = iGetPrice03;
                                    int i67 = i10 - 1;
                                    i17 = i49;
                                    int iGetPrice04 = iGetPosLenPrice2 + SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._isMatch[(iStateUpdateMatch << 4) + (this._posStateMask & i66)]) + this._literalEncoder.GetSubCoder(i66, this._matchFinder.GetIndexByte(i67 - 1)).GetPrice(true, this._matchFinder.GetIndexByte((i10 - (i64 + 1)) - 1), this._matchFinder.GetIndexByte(i67));
                                    int iStateUpdateChar3 = Base.StateUpdateChar(iStateUpdateMatch);
                                    int i68 = this._posStateMask & (i66 + 1);
                                    int iGetPrice16 = iGetPrice04 + SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._isMatch[(iStateUpdateChar3 << 4) + i68]) + SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._isRep[iStateUpdateChar3]);
                                    int i69 = i10 + 1 + iGetMatchLen3;
                                    i9 = i9;
                                    while (true) {
                                        i18 = i44 + i69;
                                        if (i9 >= i18) {
                                            break;
                                        }
                                        i9++;
                                        this._optimum[i9].Price = 268435455;
                                    }
                                    int iGetRepPrice4 = iGetPrice16 + GetRepPrice(0, iGetMatchLen3, iStateUpdateChar3, i68);
                                    Optimal optimal8 = this._optimum[i18];
                                    if (iGetRepPrice4 < optimal8.Price) {
                                        optimal8.Price = iGetRepPrice4;
                                        optimal8.PosPrev = i65 + 1;
                                        optimal8.BackPrev = 0;
                                        optimal8.Prev1IsChar = true;
                                        optimal8.Prev2 = true;
                                        optimal8.PosPrev2 = i44;
                                        optimal8.BackPrev2 = i64 + 4;
                                    }
                                } else {
                                    i14 = iMin;
                                    i15 = iStateUpdateShortRep;
                                    i16 = iGetPrice03;
                                    i17 = i49;
                                    i9 = i9;
                                }
                                i63 += 2;
                                if (i63 == i13) {
                                    break;
                                }
                            }
                            i10++;
                            iMin = i14;
                            iStateUpdateShortRep = i15;
                            iGetPrice03 = i16;
                            i49 = i17;
                        }
                    }
                    i36 = i9;
                    i22 = i60;
                } else {
                    int iGetMatchLen4 = this._matchFinder.GetMatchLen(0, this.reps[0], Math.min(iMin - 1, this._numFastBytes));
                    if (iGetMatchLen4 >= 2) {
                        int iStateUpdateChar4 = Base.StateUpdateChar(iStateUpdateShortRep);
                        int i70 = (i22 + 1) & this._posStateMask;
                        int iGetPrice17 = iGetPrice02 + SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._isMatch[(iStateUpdateChar4 << 4) + i70]) + SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._isRep[iStateUpdateChar4]);
                        int i71 = i51 + iGetMatchLen4;
                        i8 = iGetPrice13;
                        i9 = i5;
                        while (i9 < i71) {
                            int i72 = i9 + 1;
                            this._optimum[i72].Price = 268435455;
                            i9 = i72;
                            iReadMatchDistances2 = iReadMatchDistances2;
                        }
                        i7 = iReadMatchDistances2;
                        int iGetRepPrice5 = iGetPrice17 + GetRepPrice(0, iGetMatchLen4, iStateUpdateChar4, i70);
                        Optimal optimal9 = this._optimum[i71];
                        if (iGetRepPrice5 < optimal9.Price) {
                            optimal9.Price = iGetRepPrice5;
                            optimal9.PosPrev = i51;
                            optimal9.BackPrev = 0;
                            optimal9.Prev1IsChar = true;
                            optimal9.Prev2 = false;
                        }
                    }
                    i10 = 2;
                    i11 = 0;
                    while (i11 < 4) {
                    }
                    int i602 = i22;
                    i12 = i7;
                    if (i12 <= i52) {
                    }
                    if (i12 < i10) {
                    }
                    i36 = i9;
                    i22 = i602;
                }
            }
            c = 0;
            r4 = 1;
            i29 = 2;
        }
    }

    int GetPosLenPrice(int i, int i2, int i3) {
        int i4;
        int iGetLenToPosState = Base.GetLenToPosState(i2);
        if (i < 128) {
            i4 = this._distancesPrices[(iGetLenToPosState * 128) + i];
        } else {
            i4 = this._alignPrices[i & 15] + this._posSlotPrices[(iGetLenToPosState << 6) + GetPosSlot2(i)];
        }
        return i4 + this._lenEncoder.GetPrice(i2 - 2, i3);
    }

    int GetPureRepPrice(int i, int i2, int i3) {
        int iGetPrice;
        if (i == 0) {
            return SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._isRepG0[i2]) + SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._isRep0Long[(i2 << 4) + i3]);
        }
        int iGetPrice1 = SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._isRepG0[i2]);
        if (i == 1) {
            iGetPrice = SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._isRepG1[i2]);
        } else {
            iGetPrice1 += SevenZip.Compression.RangeCoder.Encoder.GetPrice1(this._isRepG1[i2]);
            iGetPrice = SevenZip.Compression.RangeCoder.Encoder.GetPrice(this._isRepG2[i2], i - 2);
        }
        return iGetPrice + iGetPrice1;
    }

    int GetRepLen1Price(int i, int i2) {
        return SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._isRepG0[i]) + SevenZip.Compression.RangeCoder.Encoder.GetPrice0(this._isRep0Long[(i << 4) + i2]);
    }

    int GetRepPrice(int i, int i2, int i3, int i4) {
        return this._repMatchLenEncoder.GetPrice(i2 - 2, i4) + GetPureRepPrice(i, i3, i4);
    }

    void Init() {
        BaseInit();
        this._rangeEncoder.Init();
        SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this._isMatch);
        SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this._isRep0Long);
        SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this._isRep);
        SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this._isRepG0);
        SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this._isRepG1);
        SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this._isRepG2);
        SevenZip.Compression.RangeCoder.Encoder.InitBitModels(this._posEncoders);
        this._literalEncoder.Init();
        for (int i = 0; i < 4; i++) {
            this._posSlotEncoder[i].Init();
        }
        this._lenEncoder.Init(1 << this._posStateBits);
        this._repMatchLenEncoder.Init(1 << this._posStateBits);
        this._posAlignEncoder.Init();
        this._longestMatchWasFound = false;
        this._optimumEndIndex = 0;
        this._optimumCurrentIndex = 0;
        this._additionalOffset = 0;
    }

    void MovePos(int i) throws IOException {
        if (i > 0) {
            this._matchFinder.Skip(i);
            this._additionalOffset += i;
        }
    }

    int ReadMatchDistances() throws IOException {
        int iGetMatchLen;
        int iGetMatches = this._matchFinder.GetMatches(this._matchDistances);
        this._numDistancePairs = iGetMatches;
        if (iGetMatches > 0) {
            int[] iArr = this._matchDistances;
            iGetMatchLen = iArr[iGetMatches - 2];
            if (iGetMatchLen == this._numFastBytes) {
                iGetMatchLen += this._matchFinder.GetMatchLen(iGetMatchLen - 1, iArr[iGetMatches - 1], 273 - iGetMatchLen);
            }
        } else {
            iGetMatchLen = 0;
        }
        this._additionalOffset++;
        return iGetMatchLen;
    }

    void ReleaseMFStream() {
        BinTree binTree = this._matchFinder;
        if (binTree == null || !this._needReleaseMFStream) {
            return;
        }
        binTree.ReleaseStream();
        this._needReleaseMFStream = false;
    }

    void ReleaseOutStream() {
        this._rangeEncoder.ReleaseStream();
    }

    void ReleaseStreams() {
        ReleaseMFStream();
        ReleaseOutStream();
    }

    public boolean SetAlgorithm(int i) {
        return true;
    }

    public boolean SetDictionarySize(int i) {
        int i2 = 0;
        if (i < 1 || i > 536870912) {
            return false;
        }
        this._dictionarySize = i;
        while (i > (1 << i2)) {
            i2++;
        }
        this._distTableSize = i2 * 2;
        return true;
    }

    public void SetEndMarkerMode(boolean z) {
        this._writeEndMark = z;
    }

    public boolean SetLcLpPb(int i, int i2, int i3) {
        if (i2 < 0 || i2 > 4 || i < 0 || i > 8 || i3 < 0 || i3 > 4) {
            return false;
        }
        this._numLiteralPosStateBits = i2;
        this._numLiteralContextBits = i;
        this._posStateBits = i3;
        this._posStateMask = (1 << i3) - 1;
        return true;
    }

    public boolean SetMatchFinder(int i) {
        if (i < 0 || i > 2) {
            return false;
        }
        int i2 = this._matchFinderType;
        this._matchFinderType = i;
        if (this._matchFinder == null || i2 == i) {
            return true;
        }
        this._dictionarySizePrev = -1;
        this._matchFinder = null;
        return true;
    }

    public boolean SetNumFastBytes(int i) {
        if (i < 5 || i > 273) {
            return false;
        }
        this._numFastBytes = i;
        return true;
    }

    void SetOutStream(OutputStream outputStream) {
        this._rangeEncoder.SetStream(outputStream);
    }

    void SetStreams(InputStream inputStream, OutputStream outputStream, long j, long j2) {
        this._inStream = inputStream;
        this._finished = false;
        Create();
        SetOutStream(outputStream);
        Init();
        FillDistancesPrices();
        FillAlignPrices();
        this._lenEncoder.SetTableSize((this._numFastBytes + 1) - 2);
        this._lenEncoder.UpdateTables(1 << this._posStateBits);
        this._repMatchLenEncoder.SetTableSize((this._numFastBytes + 1) - 2);
        this._repMatchLenEncoder.UpdateTables(1 << this._posStateBits);
        this.nowPos64 = 0L;
    }

    void SetWriteEndMarkerMode(boolean z) {
        this._writeEndMark = z;
    }

    public void WriteCoderProperties(OutputStream outputStream) throws IOException {
        this.properties[0] = (byte) ((((this._posStateBits * 5) + this._numLiteralPosStateBits) * 9) + this._numLiteralContextBits);
        int i = 0;
        while (i < 4) {
            int i2 = i + 1;
            this.properties[i2] = (byte) (this._dictionarySize >> (i * 8));
            i = i2;
        }
        outputStream.write(this.properties, 0, 5);
    }

    void WriteEndMarker(int i) throws IOException {
        if (this._writeEndMark) {
            this._rangeEncoder.Encode(this._isMatch, (this._state << 4) + i, 1);
            this._rangeEncoder.Encode(this._isRep, this._state, 0);
            this._state = Base.StateUpdateMatch(this._state);
            this._lenEncoder.Encode(this._rangeEncoder, 0, i);
            this._posSlotEncoder[Base.GetLenToPosState(2)].Encode(this._rangeEncoder, 63);
            this._rangeEncoder.EncodeDirectBits(67108863, 26);
            this._posAlignEncoder.ReverseEncode(this._rangeEncoder, 15);
        }
    }
}
