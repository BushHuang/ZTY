package butterknife.internal;

final class Parameter {
    static final Parameter[] NONE = new Parameter[0];
    private final int listenerPosition;
    private final String type;

    Parameter(int i, String str) {
        this.listenerPosition = i;
        this.type = str;
    }

    int getListenerPosition() {
        return this.listenerPosition;
    }

    String getType() {
        return this.type;
    }

    public boolean requiresCast(String str) {
        return !this.type.equals(str);
    }
}
