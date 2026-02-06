package SevenZip;

import SevenZip.Compression.LZMA.Decoder;
import SevenZip.Compression.LZMA.Encoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class LzmaAlone {

    public static class CommandLine {
        public static final int kBenchmak = 2;
        public static final int kDecode = 1;
        public static final int kEncode = 0;
        public String InFile;
        public String OutFile;
        public int Command = -1;
        public int NumBenchmarkPasses = 10;
        public int DictionarySize = 8388608;
        public boolean DictionarySizeIsDefined = false;
        public int Lc = 3;
        public int Lp = 0;
        public int Pb = 2;
        public int Fb = 128;
        public boolean FbIsDefined = false;
        public boolean Eos = false;
        public int Algorithm = 2;
        public int MatchFinder = 1;

        public boolean Parse(String[] strArr) throws Exception {
            int i;
            boolean z = true;
            int i2 = 0;
            for (String str : strArr) {
                if (str.length() == 0) {
                    return false;
                }
                if (!z) {
                    if (i2 == 0) {
                        if (str.equalsIgnoreCase("e")) {
                            this.Command = 0;
                        } else if (str.equalsIgnoreCase("d")) {
                            this.Command = 1;
                        } else {
                            if (!str.equalsIgnoreCase("b")) {
                                return false;
                            }
                            this.Command = 2;
                        }
                    } else if (i2 != 1) {
                        if (i2 != 2) {
                            return false;
                        }
                        this.OutFile = str;
                    } else if (this.Command == 2) {
                        try {
                            i = Integer.parseInt(str);
                            this.NumBenchmarkPasses = i;
                        } catch (NumberFormatException unused) {
                        }
                        if (i < 1) {
                            return false;
                        }
                    } else {
                        this.InFile = str;
                    }
                    i2++;
                } else if (str.compareTo("--") == 0) {
                    z = false;
                } else if (str.charAt(0) == '-') {
                    String lowerCase = str.substring(1).toLowerCase();
                    if (lowerCase.length() == 0) {
                        return false;
                    }
                    if (!ParseSwitch(lowerCase)) {
                        return false;
                    }
                }
            }
            return true;
        }

        boolean ParseSwitch(String str) {
            if (str.startsWith("d")) {
                this.DictionarySize = 1 << Integer.parseInt(str.substring(1));
                this.DictionarySizeIsDefined = true;
            } else if (str.startsWith("fb")) {
                this.Fb = Integer.parseInt(str.substring(2));
                this.FbIsDefined = true;
            } else if (str.startsWith("a")) {
                this.Algorithm = Integer.parseInt(str.substring(1));
            } else if (str.startsWith("lc")) {
                this.Lc = Integer.parseInt(str.substring(2));
            } else if (str.startsWith("lp")) {
                this.Lp = Integer.parseInt(str.substring(2));
            } else if (str.startsWith("pb")) {
                this.Pb = Integer.parseInt(str.substring(2));
            } else {
                if (!str.startsWith("eos")) {
                    if (str.startsWith("mf")) {
                        String strSubstring = str.substring(2);
                        if (strSubstring.equals("bt2")) {
                            this.MatchFinder = 0;
                        } else if (strSubstring.equals("bt4")) {
                            this.MatchFinder = 1;
                        } else if (strSubstring.equals("bt4b")) {
                            this.MatchFinder = 2;
                        }
                    }
                    return false;
                }
                this.Eos = true;
            }
            return true;
        }
    }

    static void PrintHelp() {
        System.out.println("\nUsage:  LZMA <e|d> [<switches>...] inputFile outputFile\n  e: encode file\n  d: decode file\n  b: Benchmark\n<Switches>\n  -d{N}:  set dictionary - [0,28], default: 23 (8MB)\n  -fb{N}: set number of fast bytes - [5, 273], default: 128\n  -lc{N}: set number of literal context bits - [0, 8], default: 3\n  -lp{N}: set number of literal pos bits - [0, 4], default: 0\n  -pb{N}: set number of pos bits - [0, 4], default: 2\n  -mf{MF_ID}: set Match Finder: [bt2, bt4], default: bt4\n  -eos:   write End Of Stream marker\n");
    }

    public static void main(String[] strArr) throws Exception {
        System.out.println("\nLZMA (Java) 4.61  2008-11-23\n");
        if (strArr.length < 1) {
            PrintHelp();
            return;
        }
        CommandLine commandLine = new CommandLine();
        if (!commandLine.Parse(strArr)) {
            System.out.println("\nIncorrect command");
            return;
        }
        if (commandLine.Command == 2) {
            int i = commandLine.DictionarySizeIsDefined ? commandLine.DictionarySize : 2097152;
            if (commandLine.MatchFinder > 1) {
                throw new Exception("Unsupported match finder");
            }
            LzmaBench.LzmaBenchmark(commandLine.NumBenchmarkPasses, i);
            return;
        }
        if (commandLine.Command != 0 && commandLine.Command != 1) {
            throw new Exception("Incorrect command");
        }
        File file = new File(commandLine.InFile);
        File file2 = new File(commandLine.OutFile);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
        boolean z = commandLine.Eos;
        int i2 = 0;
        if (commandLine.Command == 0) {
            Encoder encoder = new Encoder();
            if (!encoder.SetAlgorithm(commandLine.Algorithm)) {
                throw new Exception("Incorrect compression mode");
            }
            if (!encoder.SetDictionarySize(commandLine.DictionarySize)) {
                throw new Exception("Incorrect dictionary size");
            }
            if (!encoder.SetNumFastBytes(commandLine.Fb)) {
                throw new Exception("Incorrect -fb value");
            }
            if (!encoder.SetMatchFinder(commandLine.MatchFinder)) {
                throw new Exception("Incorrect -mf value");
            }
            if (!encoder.SetLcLpPb(commandLine.Lc, commandLine.Lp, commandLine.Pb)) {
                throw new Exception("Incorrect -lc or -lp or -pb value");
            }
            encoder.SetEndMarkerMode(z);
            encoder.WriteCoderProperties(bufferedOutputStream);
            long length = z ? -1L : file.length();
            while (i2 < 8) {
                bufferedOutputStream.write(((int) (length >>> (i2 * 8))) & 255);
                i2++;
            }
            encoder.Code(bufferedInputStream, bufferedOutputStream, -1L, -1L, null);
        } else {
            byte[] bArr = new byte[5];
            if (bufferedInputStream.read(bArr, 0, 5) != 5) {
                throw new Exception("input .lzma file is too short");
            }
            Decoder decoder = new Decoder();
            if (!decoder.SetDecoderProperties(bArr)) {
                throw new Exception("Incorrect stream properties");
            }
            long j = 0;
            while (i2 < 8) {
                int i3 = bufferedInputStream.read();
                if (i3 < 0) {
                    throw new Exception("Can't read stream size");
                }
                j |= i3 << (i2 * 8);
                i2++;
            }
            if (!decoder.Code(bufferedInputStream, bufferedOutputStream, j)) {
                throw new Exception("Error in data stream");
            }
        }
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        bufferedInputStream.close();
    }
}
