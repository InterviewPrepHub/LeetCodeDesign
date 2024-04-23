package com.example.LeetCodeDesign.Design.ClearTax;

public class UserPermissions {
    private int userId;
    private int permissionId;
    private int levelId;

    public UserPermissions(int userId, int permissionId, int levelId) {
        this.userId = userId;
        this.permissionId = permissionId;
        this.levelId = levelId;
    }

    public int getUserId() {
        return userId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }
}
