package com.obs.services.model;

import com.obs.services.model.AbstractNotification;
import java.util.List;

public class FunctionGraphConfiguration extends AbstractNotification {
    private String functionGraph;

    public FunctionGraphConfiguration() {
    }

    public FunctionGraphConfiguration(String str, AbstractNotification.Filter filter, String str2, List<EventTypeEnum> list) {
        super(str, filter, list);
        this.functionGraph = str2;
    }

    public String getFunctionGraph() {
        return this.functionGraph;
    }

    public void setFunctionGraph(String str) {
        this.functionGraph = str;
    }

    @Override
    public String toString() {
        return "FunctionGraphConfiguration [id=" + this.id + ", functionGraph=" + this.functionGraph + ", events=" + this.events + ", filter=" + this.filter + "]";
    }
}
