package com.oef.services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Iterator;

public class DisPolicyBean {

    @JsonProperty("agency")
    private String agency;

    @JsonProperty("events")
    private ArrayList<String> events;

    @JsonProperty("id")
    private String id;

    @JsonProperty("prefix")
    private String prefix;

    @JsonProperty("project")
    private String project;

    @JsonProperty("stream")
    private String stream;

    @JsonProperty("suffix")
    private String suffix;

    public DisPolicyBean() {
    }

    public DisPolicyBean(String str, String str2, String str3, ArrayList<String> arrayList, String str4) {
        setId(str);
        setStream(str2);
        setProject(str3);
        setEvents(arrayList);
        setAgency(str4);
    }

    public String getAgency() {
        return this.agency;
    }

    public ArrayList<String> getEvents() {
        return this.events;
    }

    public String getId() {
        return this.id;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getProject() {
        return this.project;
    }

    public String getStream() {
        return this.stream;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setAgency(String str) {
        this.agency = str;
    }

    public void setEvents(ArrayList<String> arrayList) {
        this.events = arrayList;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setPrefix(String str) {
        this.prefix = str;
    }

    public void setProject(String str) {
        this.project = str;
    }

    public void setStream(String str) {
        this.stream = str;
    }

    public void setSuffix(String str) {
        this.suffix = str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> arrayList = this.events;
        if (arrayList != null) {
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                sb.append(it.next().toString());
                sb.append(",");
            }
            if (sb.length() > 0) {
                sb = sb.deleteCharAt(sb.length() - 1);
            }
        }
        return "DisPolicyBean [id = " + this.id + ", stream = " + this.stream + ", project = " + this.project + ", events = " + ((Object) sb) + ", prefix = " + this.prefix + ", suffix = " + this.suffix + ", agency = " + this.agency + "]";
    }
}
