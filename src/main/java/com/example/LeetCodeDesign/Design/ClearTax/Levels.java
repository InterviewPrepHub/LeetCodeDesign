package com.example.LeetCodeDesign.Design.ClearTax;

public class Levels {

    private int levelId;
    private String levelName;
    private Integer ParentLevelId;

    public Levels(int levelId, Integer parentLevelId) {
        this.levelId = levelId;
        ParentLevelId = parentLevelId;
    }

    public int getLevelId() {
        return levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public Integer getParentLevelId() {
        return ParentLevelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public void setParentLevelId(Integer parentLevelId) {
        ParentLevelId = parentLevelId;
    }
}
