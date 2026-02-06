package com.fasterxml.jackson.databind.util;

import java.io.Serializable;

public abstract class NameTransformer {
    public static final NameTransformer NOP = new NopTransformer();

    public static class Chained extends NameTransformer implements Serializable {
        private static final long serialVersionUID = 1;
        protected final NameTransformer _t1;
        protected final NameTransformer _t2;

        public Chained(NameTransformer nameTransformer, NameTransformer nameTransformer2) {
            this._t1 = nameTransformer;
            this._t2 = nameTransformer2;
        }

        @Override
        public String reverse(String str) {
            String strReverse = this._t1.reverse(str);
            return strReverse != null ? this._t2.reverse(strReverse) : strReverse;
        }

        public String toString() {
            return "[ChainedTransformer(" + this._t1 + ", " + this._t2 + ")]";
        }

        @Override
        public String transform(String str) {
            return this._t1.transform(this._t2.transform(str));
        }
    }

    protected static final class NopTransformer extends NameTransformer implements Serializable {
        private static final long serialVersionUID = 1;

        protected NopTransformer() {
        }

        @Override
        public String reverse(String str) {
            return str;
        }

        @Override
        public String transform(String str) {
            return str;
        }
    }

    protected NameTransformer() {
    }

    public static NameTransformer chainedTransformer(NameTransformer nameTransformer, NameTransformer nameTransformer2) {
        return new Chained(nameTransformer, nameTransformer2);
    }

    public static NameTransformer simpleTransformer(final String str, final String str2) {
        boolean z = str != null && str.length() > 0;
        boolean z2 = str2 != null && str2.length() > 0;
        return z ? z2 ? new NameTransformer() {
            @Override
            public String reverse(String str3) {
                if (!str3.startsWith(str)) {
                    return null;
                }
                String strSubstring = str3.substring(str.length());
                if (strSubstring.endsWith(str2)) {
                    return strSubstring.substring(0, strSubstring.length() - str2.length());
                }
                return null;
            }

            public String toString() {
                return "[PreAndSuffixTransformer('" + str + "','" + str2 + "')]";
            }

            @Override
            public String transform(String str3) {
                return str + str3 + str2;
            }
        } : new NameTransformer() {
            @Override
            public String reverse(String str3) {
                if (str3.startsWith(str)) {
                    return str3.substring(str.length());
                }
                return null;
            }

            public String toString() {
                return "[PrefixTransformer('" + str + "')]";
            }

            @Override
            public String transform(String str3) {
                return str + str3;
            }
        } : z2 ? new NameTransformer() {
            @Override
            public String reverse(String str3) {
                if (str3.endsWith(str2)) {
                    return str3.substring(0, str3.length() - str2.length());
                }
                return null;
            }

            public String toString() {
                return "[SuffixTransformer('" + str2 + "')]";
            }

            @Override
            public String transform(String str3) {
                return str3 + str2;
            }
        } : NOP;
    }

    public abstract String reverse(String str);

    public abstract String transform(String str);
}
