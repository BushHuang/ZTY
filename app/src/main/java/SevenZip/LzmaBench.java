package SevenZip;

import SevenZip.Compression.LZMA.Decoder;
import SevenZip.Compression.LZMA.Encoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LzmaBench {
    static final int kAdditionalSize = 2097152;
    static final int kCompressedAdditionalSize = 1024;
    static final int kSubBits = 8;

    static class CBenchRandomGenerator {
        public int BufferSize;
        int Pos;
        int Rep0;
        CBitRandomGenerator RG = new CBitRandomGenerator();
        public byte[] Buffer = null;

        public void Generate() {
            int iGetOffset;
            int iGetLen2;
            this.RG.Init();
            this.Rep0 = 1;
            while (this.Pos < this.BufferSize) {
                if (GetRndBit() == 0 || this.Pos < 1) {
                    byte[] bArr = this.Buffer;
                    int i = this.Pos;
                    this.Pos = i + 1;
                    bArr[i] = (byte) this.RG.GetRnd(8);
                } else {
                    if (this.RG.GetRnd(3) == 0) {
                        iGetLen2 = GetLen1() + 1;
                    } else {
                        do {
                            iGetOffset = GetOffset();
                            this.Rep0 = iGetOffset;
                        } while (iGetOffset >= this.Pos);
                        this.Rep0 = iGetOffset + 1;
                        iGetLen2 = GetLen2() + 2;
                    }
                    int i2 = 0;
                    while (i2 < iGetLen2) {
                        int i3 = this.Pos;
                        if (i3 < this.BufferSize) {
                            byte[] bArr2 = this.Buffer;
                            bArr2[i3] = bArr2[i3 - this.Rep0];
                            i2++;
                            this.Pos = i3 + 1;
                        }
                    }
                }
            }
        }

        int GetLen1() {
            CBitRandomGenerator cBitRandomGenerator = this.RG;
            return cBitRandomGenerator.GetRnd(cBitRandomGenerator.GetRnd(2) + 1);
        }

        int GetLen2() {
            CBitRandomGenerator cBitRandomGenerator = this.RG;
            return cBitRandomGenerator.GetRnd(cBitRandomGenerator.GetRnd(2) + 2);
        }

        int GetLogRandBits(int i) {
            return this.RG.GetRnd(this.RG.GetRnd(i));
        }

        int GetOffset() {
            return GetRndBit() == 0 ? GetLogRandBits(4) : (GetLogRandBits(4) << 10) | this.RG.GetRnd(10);
        }

        int GetRndBit() {
            return this.RG.GetRnd(1);
        }

        public void Set(int i) {
            this.Buffer = new byte[i];
            this.Pos = 0;
            this.BufferSize = i;
        }
    }

    static class CBitRandomGenerator {
        int NumBits;
        CRandomGenerator RG = new CRandomGenerator();
        int Value;

        CBitRandomGenerator() {
        }

        public int GetRnd(int i) {
            int i2 = this.NumBits;
            if (i2 > i) {
                int i3 = this.Value;
                int i4 = i3 & ((1 << i) - 1);
                this.Value = i3 >>> i;
                this.NumBits = i2 - i;
                return i4;
            }
            int i5 = i - i2;
            int i6 = this.Value << i5;
            int iGetRnd = this.RG.GetRnd();
            this.Value = iGetRnd;
            int i7 = i6 | (iGetRnd & ((1 << i5) - 1));
            this.Value = iGetRnd >>> i5;
            this.NumBits = 32 - i5;
            return i7;
        }

        public void Init() {
            this.Value = 0;
            this.NumBits = 0;
        }
    }

    static class CProgressInfo implements ICodeProgress {
        public long ApprovedStart;
        public long InSize;
        public long Time;

        CProgressInfo() {
        }

        public void Init() {
            this.InSize = 0L;
        }

        @Override
        public void SetProgress(long j, long j2) {
            if (j < this.ApprovedStart || this.InSize != 0) {
                return;
            }
            this.Time = System.currentTimeMillis();
            this.InSize = j;
        }
    }

    static class CRandomGenerator {
        int A1;
        int A2;

        public CRandomGenerator() {
            Init();
        }

        public int GetRnd() {
            int i = this.A1;
            int i2 = ((i & 65535) * 36969) + (i >>> 16);
            this.A1 = i2;
            int i3 = i2 << 16;
            int i4 = this.A2;
            int i5 = ((65535 & i4) * 18000) + (i4 >>> 16);
            this.A2 = i5;
            return i3 ^ i5;
        }

        public void Init() {
            this.A1 = 362436069;
            this.A2 = 521288629;
        }
    }

    static class CrcOutStream extends OutputStream {
        public CRC CRC = new CRC();

        CrcOutStream() {
        }

        public int GetDigest() {
            return this.CRC.GetDigest();
        }

        public void Init() {
            this.CRC.Init();
        }

        @Override
        public void write(int i) {
            this.CRC.UpdateByte(i);
        }

        @Override
        public void write(byte[] bArr) {
            this.CRC.Update(bArr);
        }

        @Override
        public void write(byte[] bArr, int i, int i2) {
            this.CRC.Update(bArr, i, i2);
        }
    }

    static class MyInputStream extends InputStream {
        byte[] _buffer;
        int _pos;
        int _size;

        public MyInputStream(byte[] bArr, int i) {
            this._buffer = bArr;
            this._size = i;
        }

        @Override
        public int read() {
            int i = this._pos;
            if (i >= this._size) {
                return -1;
            }
            byte[] bArr = this._buffer;
            this._pos = i + 1;
            return bArr[i] & 255;
        }

        @Override
        public void reset() {
            this._pos = 0;
        }
    }

    static class MyOutputStream extends OutputStream {
        byte[] _buffer;
        int _pos;
        int _size;

        public MyOutputStream(byte[] bArr) {
            this._buffer = bArr;
            this._size = bArr.length;
        }

        public void reset() {
            this._pos = 0;
        }

        public int size() {
            return this._pos;
        }

        @Override
        public void write(int i) throws IOException {
            int i2 = this._pos;
            if (i2 >= this._size) {
                throw new IOException("Error");
            }
            byte[] bArr = this._buffer;
            this._pos = i2 + 1;
            bArr[i2] = (byte) i;
        }
    }

    static long GetCompressRating(int i, long j, long j2) {
        long jGetLogSize = GetLogSize(i) - 4608;
        return MyMultDiv64(j2 * ((((jGetLogSize * jGetLogSize) * 10) >> 16) + 1060), j);
    }

    static long GetDecompressRating(long j, long j2, long j3) {
        return MyMultDiv64((j3 * 220) + (j2 * 20), j);
    }

    static int GetLogSize(int i) {
        for (int i2 = 8; i2 < 32; i2++) {
            for (int i3 = 0; i3 < 256; i3++) {
                if (i <= (1 << i2) + (i3 << (i2 - 8))) {
                    return (i2 << 8) + i3;
                }
            }
        }
        return 8192;
    }

    static long GetTotalRating(int i, long j, long j2, long j3, long j4, long j5) {
        return (GetCompressRating(i, j, j2) + GetDecompressRating(j3, j4, j5)) / 2;
    }

    public static int LzmaBenchmark(int i, int i2) throws Exception {
        MyOutputStream myOutputStream;
        MyInputStream myInputStream;
        if (i <= 0) {
            return 0;
        }
        if (i2 < 262144) {
            System.out.println("\nError: dictionary size for benchmark must be >= 18 (256 KB)");
            return 1;
        }
        System.out.print("\n       Compressing                Decompressing\n\n");
        Encoder encoder = new Encoder();
        Decoder decoder = new Decoder();
        if (!encoder.SetDictionarySize(i2)) {
            throw new Exception("Incorrect dictionary size");
        }
        int i3 = i2 + 2097152;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        encoder.WriteCoderProperties(byteArrayOutputStream);
        decoder.SetDecoderProperties(byteArrayOutputStream.toByteArray());
        CBenchRandomGenerator cBenchRandomGenerator = new CBenchRandomGenerator();
        cBenchRandomGenerator.Set(i3);
        cBenchRandomGenerator.Generate();
        CRC crc = new CRC();
        crc.Init();
        crc.Update(cBenchRandomGenerator.Buffer, 0, cBenchRandomGenerator.BufferSize);
        CProgressInfo cProgressInfo = new CProgressInfo();
        cProgressInfo.ApprovedStart = i2;
        MyInputStream myInputStream2 = new MyInputStream(cBenchRandomGenerator.Buffer, cBenchRandomGenerator.BufferSize);
        byte[] bArr = new byte[(i3 / 2) + 1024];
        MyOutputStream myOutputStream2 = new MyOutputStream(bArr);
        CrcOutStream crcOutStream = new CrcOutStream();
        MyInputStream myInputStream3 = null;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        int size = 0;
        int i4 = 0;
        while (i4 < i) {
            cProgressInfo.Init();
            myInputStream2.reset();
            myOutputStream2.reset();
            MyInputStream myInputStream4 = myInputStream2;
            MyInputStream myInputStream5 = myInputStream2;
            int i5 = size;
            CrcOutStream crcOutStream2 = crcOutStream;
            int i6 = i4;
            encoder.Code(myInputStream4, myOutputStream2, -1L, -1L, cProgressInfo);
            long jCurrentTimeMillis = System.currentTimeMillis() - cProgressInfo.Time;
            if (i6 == 0) {
                size = myOutputStream2.size();
                myOutputStream = myOutputStream2;
                myInputStream = new MyInputStream(bArr, size);
            } else {
                if (i5 != myOutputStream2.size()) {
                    throw new Exception("Encoding error");
                }
                myOutputStream = myOutputStream2;
                size = i5;
                myInputStream = myInputStream3;
            }
            byte[] bArr2 = bArr;
            if (cProgressInfo.InSize == 0) {
                throw new Exception("Internal ERROR 1282");
            }
            long jCurrentTimeMillis2 = 0;
            int i7 = 0;
            while (i7 < 2) {
                myInputStream.reset();
                crcOutStream2.Init();
                byte[] bArr3 = bArr2;
                long jCurrentTimeMillis3 = System.currentTimeMillis();
                Encoder encoder2 = encoder;
                CrcOutStream crcOutStream3 = crcOutStream2;
                if (!decoder.Code(myInputStream, crcOutStream3, i3)) {
                    throw new Exception("Decoding Error");
                }
                jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis3;
                if (crcOutStream3.GetDigest() != crc.GetDigest()) {
                    throw new Exception("CRC Error");
                }
                i7++;
                crcOutStream2 = crcOutStream3;
                bArr2 = bArr3;
                encoder = encoder2;
            }
            byte[] bArr4 = bArr2;
            Encoder encoder3 = encoder;
            long j5 = i3;
            long j6 = j5 - cProgressInfo.InSize;
            PrintResults(i2, jCurrentTimeMillis, j6, false, 0L);
            System.out.print("     ");
            long j7 = size;
            PrintResults(i2, jCurrentTimeMillis2, j5, true, j7);
            System.out.println();
            j3 += j6;
            j2 += jCurrentTimeMillis;
            j += jCurrentTimeMillis2;
            j4 += j7;
            i3 = i3;
            myInputStream2 = myInputStream5;
            encoder = encoder3;
            decoder = decoder;
            bArr = bArr4;
            crc = crc;
            cProgressInfo = cProgressInfo;
            myInputStream3 = myInputStream;
            crcOutStream = crcOutStream2;
            MyOutputStream myOutputStream3 = myOutputStream;
            i4 = i6 + 1;
            myOutputStream2 = myOutputStream3;
        }
        System.out.println("---------------------------------------------------");
        PrintResults(i2, j2, j3, false, 0L);
        System.out.print("     ");
        PrintResults(i2, j, i * i3, true, j4);
        System.out.println("    Average");
        return 0;
    }

    static long MyMultDiv64(long j, long j2) {
        if (j2 == 0) {
            j2 = 1;
        }
        return (j * 1000) / j2;
    }

    static void PrintRating(long j) {
        PrintValue(j / 1000000);
        System.out.print(" MIPS");
    }

    static void PrintResults(int i, long j, long j2, boolean z, long j3) {
        PrintValue(MyMultDiv64(j2, j) / 1024);
        System.out.print(" KB/s  ");
        PrintRating(z ? GetDecompressRating(j, j2, j3) : GetCompressRating(i, j, j2));
    }

    static void PrintValue(long j) {
        String str = "" + j;
        for (int i = 0; str.length() + i < 6; i++) {
            System.out.print(" ");
        }
        System.out.print(str);
    }
}
