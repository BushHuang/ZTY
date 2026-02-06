package com.obs.services.model;

import java.util.ArrayList;
import java.util.List;

public class BucketNotificationConfiguration extends HeaderResponse {
    private List<FunctionGraphConfiguration> functionGraphConfigurations;
    private List<TopicConfiguration> topicConfigurations;

    public BucketNotificationConfiguration addFunctionGraphConfiguration(FunctionGraphConfiguration functionGraphConfiguration) {
        getFunctionGraphConfigurations().add(functionGraphConfiguration);
        return this;
    }

    public BucketNotificationConfiguration addTopicConfiguration(TopicConfiguration topicConfiguration) {
        getTopicConfigurations().add(topicConfiguration);
        return this;
    }

    public List<FunctionGraphConfiguration> getFunctionGraphConfigurations() {
        if (this.functionGraphConfigurations == null) {
            this.functionGraphConfigurations = new ArrayList();
        }
        return this.functionGraphConfigurations;
    }

    public List<TopicConfiguration> getTopicConfigurations() {
        if (this.topicConfigurations == null) {
            this.topicConfigurations = new ArrayList();
        }
        return this.topicConfigurations;
    }

    public void setFunctionGraphConfigurations(List<FunctionGraphConfiguration> list) {
        this.functionGraphConfigurations = list;
    }

    public void setTopicConfigurations(List<TopicConfiguration> list) {
        this.topicConfigurations = list;
    }

    @Override
    public String toString() {
        return "BucketNotificationConfiguration [topicConfigurations=" + this.topicConfigurations + ", functionGraphConfigurations=" + this.functionGraphConfigurations + "]";
    }
}
