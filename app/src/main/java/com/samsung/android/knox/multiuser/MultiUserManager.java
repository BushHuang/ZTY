package com.samsung.android.knox.multiuser;

public class MultiUserManager {
    private android.app.enterprise.multiuser.MultiUserManager mMultiUserManager;

    public MultiUserManager(android.app.enterprise.multiuser.MultiUserManager multiUserManager) {
        this.mMultiUserManager = multiUserManager;
    }

    public boolean allowMultipleUsers(boolean z) {
        return this.mMultiUserManager.allowMultipleUsers(z);
    }

    public boolean allowUserCreation(boolean z) {
        return this.mMultiUserManager.allowUserCreation(z);
    }

    public boolean allowUserRemoval(boolean z) {
        return this.mMultiUserManager.allowUserRemoval(z);
    }

    public int createUser(String str) {
        return this.mMultiUserManager.createUser(str);
    }

    public int[] getUsers() {
        return this.mMultiUserManager.getUsers();
    }

    public boolean isUserCreationAllowed() {
        return this.mMultiUserManager.isUserCreationAllowed();
    }

    public boolean isUserRemovalAllowed() {
        return this.mMultiUserManager.isUserRemovalAllowed();
    }

    public boolean multipleUsersAllowed() {
        return this.mMultiUserManager.multipleUsersAllowed();
    }

    public boolean multipleUsersSupported() {
        return this.mMultiUserManager.multipleUsersSupported();
    }

    public boolean removeUser(int i) {
        return this.mMultiUserManager.removeUser(i);
    }
}
