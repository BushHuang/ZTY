package com.oef.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Iterator;

public class DisPolicy {

    @JsonProperty("rules")
    private ArrayList<DisPolicyBean> rules;

    public DisPolicy() {
    }

    public DisPolicy(ArrayList<DisPolicyBean> arrayList) {
        this.rules = arrayList;
    }

    public void addRules(DisPolicyBean disPolicyBean) {
        if (this.rules == null) {
            this.rules = new ArrayList<>();
        }
        this.rules.add(disPolicyBean);
    }

    public ArrayList<DisPolicyBean> getRules() {
        return this.rules;
    }

    public void setRules(ArrayList<DisPolicyBean> arrayList) {
        this.rules = arrayList;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<DisPolicyBean> arrayList = this.rules;
        if (arrayList != null) {
            Iterator<DisPolicyBean> it = arrayList.iterator();
            while (it.hasNext()) {
                sb.append(it.next().toString());
                sb.append("\n");
            }
        }
        return "DisPolicy[" + ((Object) sb) + "]";
    }
}
