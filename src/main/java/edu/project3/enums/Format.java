package edu.project3.enums;

public enum Format {
    ADOC("adoc"),
    MD("md");

    private String text;

    Format(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

