package com.example.demo.entity;

public enum MailType {
    LETTER("Letter"),
    PACKAGE("Package"),
    PARCEL("Parcel"),
    POSTCARD("Postcard");

    private final String displayName;

    MailType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}