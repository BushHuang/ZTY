package butterknife.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class MethodViewBinding implements ViewBinding {
    private final String name;
    private final List<Parameter> parameters;
    private final boolean required;

    MethodViewBinding(String str, List<Parameter> list, boolean z) {
        this.name = str;
        this.parameters = Collections.unmodifiableList(new ArrayList(list));
        this.required = z;
    }

    @Override
    public String getDescription() {
        return "method '" + this.name + "'";
    }

    public String getName() {
        return this.name;
    }

    public List<Parameter> getParameters() {
        return this.parameters;
    }

    public boolean isRequired() {
        return this.required;
    }
}
