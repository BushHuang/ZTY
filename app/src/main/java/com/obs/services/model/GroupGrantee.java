package com.obs.services.model;

public class GroupGrantee implements GranteeInterface {
    public static final GroupGrantee ALL_USERS = new GroupGrantee(GroupGranteeEnum.ALL_USERS);

    @Deprecated
    public static final GroupGrantee AUTHENTICATED_USERS = new GroupGrantee(GroupGranteeEnum.AUTHENTICATED_USERS);

    @Deprecated
    public static final GroupGrantee LOG_DELIVERY = new GroupGrantee(GroupGranteeEnum.LOG_DELIVERY);
    private GroupGranteeEnum groupGranteeType;

    public GroupGrantee() {
    }

    public GroupGrantee(GroupGranteeEnum groupGranteeEnum) {
        this.groupGranteeType = groupGranteeEnum;
    }

    public GroupGrantee(String str) {
        this.groupGranteeType = GroupGranteeEnum.getValueFromCode(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.groupGranteeType == ((GroupGrantee) obj).groupGranteeType;
    }

    public GroupGranteeEnum getGroupGranteeType() {
        return this.groupGranteeType;
    }

    @Override
    public String getIdentifier() {
        GroupGranteeEnum groupGranteeEnum = this.groupGranteeType;
        if (groupGranteeEnum == null) {
            return null;
        }
        return groupGranteeEnum.getCode();
    }

    public int hashCode() {
        GroupGranteeEnum groupGranteeEnum = this.groupGranteeType;
        return 31 + (groupGranteeEnum == null ? 0 : groupGranteeEnum.hashCode());
    }

    @Override
    public void setIdentifier(String str) {
        this.groupGranteeType = GroupGranteeEnum.getValueFromCode(str);
    }

    public String toString() {
        return "GroupGrantee [" + this.groupGranteeType + "]";
    }
}
