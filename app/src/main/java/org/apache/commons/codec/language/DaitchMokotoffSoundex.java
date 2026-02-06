package org.apache.commons.codec.language;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

public class DaitchMokotoffSoundex implements StringEncoder {
    private static final String COMMENT = "//";
    private static final String DOUBLE_QUOTE = "\"";
    private static final int MAX_LENGTH = 6;
    private static final String MULTILINE_COMMENT_END = "*/";
    private static final String MULTILINE_COMMENT_START = "/*";
    private static final String RESOURCE_FILE = "org/apache/commons/codec/language/dmrules.txt";
    private final boolean folding;
    private static final Map<Character, List<Rule>> RULES = new HashMap();
    private static final Map<Character, Character> FOLDINGS = new HashMap();

    private static final class Branch {
        private final StringBuilder builder;
        private String cachedString;
        private String lastReplacement;

        private Branch() {
            this.builder = new StringBuilder();
            this.lastReplacement = null;
            this.cachedString = null;
        }

        public Branch createBranch() {
            Branch branch = new Branch();
            branch.builder.append(toString());
            branch.lastReplacement = this.lastReplacement;
            return branch;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Branch) {
                return toString().equals(((Branch) obj).toString());
            }
            return false;
        }

        public void finish() {
            while (this.builder.length() < 6) {
                this.builder.append('0');
                this.cachedString = null;
            }
        }

        public int hashCode() {
            return toString().hashCode();
        }

        public void processNextReplacement(String str, boolean z) {
            String str2 = this.lastReplacement;
            if ((str2 == null || !str2.endsWith(str) || z) && this.builder.length() < 6) {
                this.builder.append(str);
                if (this.builder.length() > 6) {
                    StringBuilder sb = this.builder;
                    sb.delete(6, sb.length());
                }
                this.cachedString = null;
            }
            this.lastReplacement = str;
        }

        public String toString() {
            if (this.cachedString == null) {
                this.cachedString = this.builder.toString();
            }
            return this.cachedString;
        }
    }

    private static final class Rule {
        private final String pattern;
        private final String[] replacementAtStart;
        private final String[] replacementBeforeVowel;
        private final String[] replacementDefault;

        protected Rule(String str, String str2, String str3, String str4) {
            this.pattern = str;
            this.replacementAtStart = str2.split("\\|");
            this.replacementBeforeVowel = str3.split("\\|");
            this.replacementDefault = str4.split("\\|");
        }

        private boolean isVowel(char c) {
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        }

        public int getPatternLength() {
            return this.pattern.length();
        }

        public String[] getReplacements(String str, boolean z) {
            if (z) {
                return this.replacementAtStart;
            }
            int patternLength = getPatternLength();
            return patternLength < str.length() ? isVowel(str.charAt(patternLength)) : false ? this.replacementBeforeVowel : this.replacementDefault;
        }

        public boolean matches(String str) {
            return str.startsWith(this.pattern);
        }

        public String toString() {
            return String.format("%s=(%s,%s,%s)", this.pattern, Arrays.asList(this.replacementAtStart), Arrays.asList(this.replacementBeforeVowel), Arrays.asList(this.replacementDefault));
        }
    }

    static {
        InputStream resourceAsStream = DaitchMokotoffSoundex.class.getClassLoader().getResourceAsStream("org/apache/commons/codec/language/dmrules.txt");
        if (resourceAsStream == null) {
            throw new IllegalArgumentException("Unable to load resource: org/apache/commons/codec/language/dmrules.txt");
        }
        Scanner scanner = new Scanner(resourceAsStream, "UTF-8");
        try {
            parseRules(scanner, "org/apache/commons/codec/language/dmrules.txt", RULES, FOLDINGS);
            scanner.close();
            Iterator<Map.Entry<Character, List<Rule>>> it = RULES.entrySet().iterator();
            while (it.hasNext()) {
                Collections.sort(it.next().getValue(), new Comparator<Rule>() {
                    @Override
                    public int compare(Rule rule, Rule rule2) {
                        return rule2.getPatternLength() - rule.getPatternLength();
                    }
                });
            }
        } catch (Throwable th) {
            scanner.close();
            throw th;
        }
    }

    public DaitchMokotoffSoundex() {
        this(true);
    }

    public DaitchMokotoffSoundex(boolean z) {
        this.folding = z;
    }

    private String cleanup(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                char lowerCase = Character.toLowerCase(c);
                if (this.folding && FOLDINGS.containsKey(Character.valueOf(lowerCase))) {
                    lowerCase = FOLDINGS.get(Character.valueOf(lowerCase)).charValue();
                }
                sb.append(lowerCase);
            }
        }
        return sb.toString();
    }

    private static void parseRules(Scanner scanner, String str, Map<Character, List<Rule>> map, Map<Character, Character> map2) {
        int i = 0;
        loop0: while (true) {
            boolean z = false;
            while (scanner.hasNextLine()) {
                i++;
                String strNextLine = scanner.nextLine();
                if (z) {
                    if (strNextLine.endsWith("*/")) {
                        break;
                    }
                } else if (strNextLine.startsWith("/*")) {
                    z = true;
                } else {
                    int iIndexOf = strNextLine.indexOf("//");
                    String strTrim = (iIndexOf >= 0 ? strNextLine.substring(0, iIndexOf) : strNextLine).trim();
                    if (strTrim.length() == 0) {
                        continue;
                    } else if (strTrim.contains("=")) {
                        String[] strArrSplit = strTrim.split("=");
                        if (strArrSplit.length != 2) {
                            throw new IllegalArgumentException("Malformed folding statement split into " + strArrSplit.length + " parts: " + strNextLine + " in " + str);
                        }
                        String str2 = strArrSplit[0];
                        String str3 = strArrSplit[1];
                        if (str2.length() != 1 || str3.length() != 1) {
                            break loop0;
                        } else {
                            map2.put(Character.valueOf(str2.charAt(0)), Character.valueOf(str3.charAt(0)));
                        }
                    } else {
                        String[] strArrSplit2 = strTrim.split("\\s+");
                        if (strArrSplit2.length != 4) {
                            throw new IllegalArgumentException("Malformed rule statement split into " + strArrSplit2.length + " parts: " + strNextLine + " in " + str);
                        }
                        try {
                            Rule rule = new Rule(stripQuotes(strArrSplit2[0]), stripQuotes(strArrSplit2[1]), stripQuotes(strArrSplit2[2]), stripQuotes(strArrSplit2[3]));
                            char cCharAt = rule.pattern.charAt(0);
                            List<Rule> arrayList = map.get(Character.valueOf(cCharAt));
                            if (arrayList == null) {
                                arrayList = new ArrayList<>();
                                map.put(Character.valueOf(cCharAt), arrayList);
                            }
                            arrayList.add(rule);
                        } catch (IllegalArgumentException e) {
                            throw new IllegalStateException("Problem parsing line '" + i + "' in " + str, e);
                        }
                    }
                }
            }
            return;
        }
    }

    private String[] soundex(String str, boolean z) {
        String str2;
        int i;
        String str3;
        if (str == null) {
            return null;
        }
        String strCleanup = cleanup(str);
        LinkedHashSet<Branch> linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(new Branch());
        int patternLength = 0;
        char c = 0;
        while (patternLength < strCleanup.length()) {
            char cCharAt = strCleanup.charAt(patternLength);
            if (Character.isWhitespace(cCharAt)) {
                str2 = strCleanup;
                i = 1;
            } else {
                String strSubstring = strCleanup.substring(patternLength);
                List<Rule> list = RULES.get(Character.valueOf(cCharAt));
                if (list != null) {
                    List arrayList = z ? new ArrayList() : Collections.EMPTY_LIST;
                    Iterator<Rule> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            str2 = strCleanup;
                            i = 1;
                            break;
                        }
                        Rule next = it.next();
                        if (next.matches(strSubstring)) {
                            if (z) {
                                arrayList.clear();
                            }
                            String[] replacements = next.getReplacements(strSubstring, c == 0);
                            boolean z2 = replacements.length > 1 && z;
                            for (Branch branch : linkedHashSet) {
                                int length = replacements.length;
                                int i2 = 0;
                                while (true) {
                                    if (i2 >= length) {
                                        str3 = strCleanup;
                                        break;
                                    }
                                    String str4 = replacements[i2];
                                    Branch branchCreateBranch = z2 ? branch.createBranch() : branch;
                                    str3 = strCleanup;
                                    branchCreateBranch.processNextReplacement(str4, (c == 'm' && cCharAt == 'n') || (c == 'n' && cCharAt == 'm'));
                                    if (z) {
                                        arrayList.add(branchCreateBranch);
                                        i2++;
                                        strCleanup = str3;
                                    }
                                }
                                strCleanup = str3;
                            }
                            str2 = strCleanup;
                            if (z) {
                                linkedHashSet.clear();
                                linkedHashSet.addAll(arrayList);
                            }
                            i = 1;
                            patternLength += next.getPatternLength() - 1;
                        }
                    }
                    c = cCharAt;
                }
            }
            patternLength += i;
            strCleanup = str2;
        }
        String[] strArr = new String[linkedHashSet.size()];
        int i3 = 0;
        for (Branch branch2 : linkedHashSet) {
            branch2.finish();
            strArr[i3] = branch2.toString();
            i3++;
        }
        return strArr;
    }

    private static String stripQuotes(String str) {
        if (str.startsWith("\"")) {
            str = str.substring(1);
        }
        return str.endsWith("\"") ? str.substring(0, str.length() - 1) : str;
    }

    @Override
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return encode((String) obj);
        }
        throw new EncoderException("Parameter supplied to DaitchMokotoffSoundex encode is not of type java.lang.String");
    }

    @Override
    public String encode(String str) {
        if (str == null) {
            return null;
        }
        return soundex(str, false)[0];
    }

    public String soundex(String str) {
        String[] strArrSoundex = soundex(str, true);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str2 : strArrSoundex) {
            sb.append(str2);
            i++;
            if (i < strArrSoundex.length) {
                sb.append('|');
            }
        }
        return sb.toString();
    }
}
