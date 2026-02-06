package butterknife.internal;

final class FieldResourceBinding {
    private final int id;
    private final String method;
    private final String name;

    FieldResourceBinding(int i, String str, String str2) {
        this.id = i;
        this.name = str;
        this.method = str2;
    }

    public int getId() {
        return this.id;
    }

    public String getMethod() {
        return this.method;
    }

    public String getName() {
        return this.name;
    }
}
