package com.obs.services.model;

public class PolicyConditionItem {
    private String key;
    private ConditionOperator operator;
    private String value;

    public enum ConditionOperator {
        EQUAL("eq"),
        STARTS_WITH("starts-with");

        private String operationCode;

        ConditionOperator(String str) {
            this.operationCode = str;
        }

        public String getOperationCode() {
            return this.operationCode;
        }
    }

    public PolicyConditionItem(ConditionOperator conditionOperator, String str, String str2) {
        this.operator = conditionOperator;
        this.key = str;
        this.value = str2;
    }

    public String toString() {
        if (this.value == null) {
            this.value = "";
        }
        return String.format("[\"%s\",\"$%s\",\"%s\"]", this.operator.getOperationCode(), this.key, this.value);
    }
}
