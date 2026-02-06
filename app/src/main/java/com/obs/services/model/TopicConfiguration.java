package com.obs.services.model;

import com.obs.services.model.AbstractNotification;
import java.util.List;

public class TopicConfiguration extends AbstractNotification {
    private String topic;

    public TopicConfiguration() {
    }

    public TopicConfiguration(String str, AbstractNotification.Filter filter, String str2, List<EventTypeEnum> list) {
        super(str, filter, list);
        this.topic = str2;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }

    @Override
    public String toString() {
        return "TopicConfiguration [id=" + this.id + ", topic=" + this.topic + ", events=" + this.events + ", filter=" + this.filter + "]";
    }
}
