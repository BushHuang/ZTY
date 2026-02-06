package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

public class ColognePhonetic implements StringEncoder {
    private static final char[] AEIJOUY = {'A', 'E', 'I', 'J', 'O', 'U', 'Y'};
    private static final char[] SCZ = {'S', 'C', 'Z'};
    private static final char[] WFPV = {'W', 'F', 'P', 'V'};
    private static final char[] GKQ = {'G', 'K', 'Q'};
    private static final char[] CKQ = {'C', 'K', 'Q'};
    private static final char[] AHKLOQRUX = {'A', 'H', 'K', 'L', 'O', 'Q', 'R', 'U', 'X'};
    private static final char[] SZ = {'S', 'Z'};
    private static final char[] AHOUKQX = {'A', 'H', 'O', 'U', 'K', 'Q', 'X'};
    private static final char[] TDX = {'T', 'D', 'X'};
    private static final char[][] PREPROCESS_MAP = {new char[]{196, 'A'}, new char[]{220, 'U'}, new char[]{214, 'O'}, new char[]{223, 'S'}};

    private abstract class CologneBuffer {
        protected final char[] data;
        protected int length;

        public CologneBuffer(int i) {
            this.length = 0;
            this.data = new char[i];
            this.length = 0;
        }

        public CologneBuffer(char[] cArr) {
            this.length = 0;
            this.data = cArr;
            this.length = cArr.length;
        }

        protected abstract char[] copyData(int i, int i2);

        public int length() {
            return this.length;
        }

        public String toString() {
            return new String(copyData(0, this.length));
        }
    }

    private class CologneInputBuffer extends CologneBuffer {
        public CologneInputBuffer(char[] cArr) {
            super(cArr);
        }

        public void addLeft(char c) {
            this.length++;
            this.data[getNextPos()] = c;
        }

        @Override
        protected char[] copyData(int i, int i2) {
            char[] cArr = new char[i2];
            System.arraycopy(this.data, (this.data.length - this.length) + i, cArr, 0, i2);
            return cArr;
        }

        public char getNextChar() {
            return this.data[getNextPos()];
        }

        protected int getNextPos() {
            return this.data.length - this.length;
        }

        public char removeNext() {
            this.length--;
            return getNextChar();
        }
    }

    private class CologneOutputBuffer extends CologneBuffer {
        public CologneOutputBuffer(int i) {
            super(i);
        }

        public void addRight(char c) {
            this.data[this.length] = c;
            this.length++;
        }

        @Override
        protected char[] copyData(int i, int i2) {
            char[] cArr = new char[i2];
            System.arraycopy(this.data, i, cArr, 0, i2);
            return cArr;
        }
    }

    private static boolean arrayContains(char[] cArr, char c) {
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    private String preprocess(String str) {
        char[] charArray = str.toUpperCase(Locale.GERMAN).toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] > 'Z') {
                char[][] cArr = PREPROCESS_MAP;
                int length = cArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 < length) {
                        char[] cArr2 = cArr[i2];
                        if (charArray[i] == cArr2[0]) {
                            charArray[i] = cArr2[1];
                            break;
                        }
                        i2++;
                    }
                }
            }
        }
        return new String(charArray);
    }

    public String colognePhonetic(String str) {
        if (str == null) {
            return null;
        }
        String strPreprocess = preprocess(str);
        CologneOutputBuffer cologneOutputBuffer = new CologneOutputBuffer(strPreprocess.length() * 2);
        CologneInputBuffer cologneInputBuffer = new CologneInputBuffer(strPreprocess.toCharArray());
        int length = cologneInputBuffer.length();
        char c = '/';
        char c2 = '-';
        while (length > 0) {
            char cRemoveNext = cologneInputBuffer.removeNext();
            int length2 = cologneInputBuffer.length();
            char nextChar = length2 > 0 ? cologneInputBuffer.getNextChar() : '-';
            char c3 = '4';
            if (arrayContains(AEIJOUY, cRemoveNext)) {
                c3 = '0';
            } else if (cRemoveNext == 'H' || cRemoveNext < 'A' || cRemoveNext > 'Z') {
                if (c == '/') {
                    length = length2;
                } else {
                    c3 = '-';
                }
            } else if (cRemoveNext == 'B' || (cRemoveNext == 'P' && nextChar != 'H')) {
                c3 = '1';
            } else if ((cRemoveNext == 'D' || cRemoveNext == 'T') && !arrayContains(SCZ, nextChar)) {
                c3 = '2';
            } else if (arrayContains(WFPV, cRemoveNext)) {
                c3 = '3';
            } else if (!arrayContains(GKQ, cRemoveNext)) {
                if (cRemoveNext == 'X' && !arrayContains(CKQ, c2)) {
                    cologneInputBuffer.addLeft('S');
                    length2++;
                } else if (cRemoveNext != 'S' && cRemoveNext != 'Z') {
                    if (cRemoveNext == 'C') {
                        c3 = c == '/' ? '8' : '8';
                    } else if (!arrayContains(TDX, cRemoveNext)) {
                        c3 = cRemoveNext == 'R' ? '7' : cRemoveNext == 'L' ? '5' : (cRemoveNext == 'M' || cRemoveNext == 'N') ? '6' : cRemoveNext;
                    }
                }
            }
            if (c3 != '-' && ((c != c3 && (c3 != '0' || c == '/')) || c3 < '0' || c3 > '8')) {
                cologneOutputBuffer.addRight(c3);
            }
            c2 = cRemoveNext;
            length = length2;
            c = c3;
        }
        return cologneOutputBuffer.toString();
    }

    @Override
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return encode((String) obj);
        }
        throw new EncoderException("This method's parameter was expected to be of the type " + String.class.getName() + ". But actually it was of the type " + obj.getClass().getName() + ".");
    }

    @Override
    public String encode(String str) {
        return colognePhonetic(str);
    }

    public boolean isEncodeEqual(String str, String str2) {
        return colognePhonetic(str).equals(colognePhonetic(str2));
    }
}
