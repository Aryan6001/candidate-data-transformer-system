package com.eightfold.transformer.constants;

public enum SourceType {

    RECRUITER_CSV("Recruiter CSV"),
    RESUME("Resume");

    private final String displayName;

    SourceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}