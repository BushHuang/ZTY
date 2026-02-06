package net.contrapunctus.lzma;

public class Version {
    public static final String context = "\nContext:\n\n[TAG 0.92\nChristopher League <league@contrapunctus.net>**20081016191208] \n";
    public static final int major = 0;
    public static final int minor = 93;

    public static void main(String[] strArr) {
        if (strArr.length > 0) {
            System.out.println("\nContext:\n\n[TAG 0.92\nChristopher League <league@contrapunctus.net>**20081016191208] \n");
        } else {
            System.out.printf("lzmajio-%d.%d%n", 0, 93);
        }
    }
}
