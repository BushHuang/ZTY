package com.obs.services.model;

import java.util.ArrayList;
import java.util.List;

public class ReplicationConfiguration extends HeaderResponse {
    private String agency;
    private List<Rule> rules;

    public static class Destination {
        private String bucket;
        private StorageClassEnum storageClass;

        public String getBucket() {
            return this.bucket;
        }

        public StorageClassEnum getObjectStorageClass() {
            return this.storageClass;
        }

        public void setBucket(String str) {
            this.bucket = str;
        }

        public void setObjectStorageClass(StorageClassEnum storageClassEnum) {
            this.storageClass = storageClassEnum;
        }

        public String toString() {
            return "Destination [bucket=" + this.bucket + ", storageClass=" + this.storageClass + "]";
        }
    }

    public static class Rule {
        private Destination destination;
        private String id;
        private String prefix;
        private RuleStatusEnum status;

        public Destination getDestination() {
            return this.destination;
        }

        public String getId() {
            return this.id;
        }

        public String getPrefix() {
            return this.prefix;
        }

        public RuleStatusEnum getStatus() {
            return this.status;
        }

        public void setDestination(Destination destination) {
            this.destination = destination;
        }

        public void setId(String str) {
            this.id = str;
        }

        public void setPrefix(String str) {
            this.prefix = str;
        }

        public void setStatus(RuleStatusEnum ruleStatusEnum) {
            this.status = ruleStatusEnum;
        }

        public String toString() {
            return "Rule [id=" + this.id + ", status=" + this.status + ", prefix=" + this.prefix + ", destination=" + this.destination + "]";
        }
    }

    public String getAgency() {
        return this.agency;
    }

    public List<Rule> getRules() {
        if (this.rules == null) {
            this.rules = new ArrayList();
        }
        return this.rules;
    }

    public void setAgency(String str) {
        this.agency = str;
    }

    public void setRules(List<Rule> list) {
        this.rules = list;
    }

    @Override
    public String toString() {
        return "ReplicationConfiguration [agency=" + this.agency + ", rules=" + this.rules + "]";
    }
}
