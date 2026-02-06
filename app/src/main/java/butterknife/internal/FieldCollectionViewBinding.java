package butterknife.internal;

final class FieldCollectionViewBinding implements ViewBinding {
    private final Kind kind;
    private final String name;
    private final boolean required;
    private final String type;

    enum Kind {
        ARRAY,
        LIST
    }

    FieldCollectionViewBinding(String str, String str2, Kind kind, boolean z) {
        this.name = str;
        this.type = str2;
        this.kind = kind;
        this.required = z;
    }

    @Override
    public String getDescription() {
        return "field '" + this.name + "'";
    }

    public Kind getKind() {
        return this.kind;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public boolean isRequired() {
        return this.required;
    }
}
