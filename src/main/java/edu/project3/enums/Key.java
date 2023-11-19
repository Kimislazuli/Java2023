package edu.project3.enums;

public enum Key {
    PATH("--path"),
    FROM("--from"),
    TO("--to"),
    FORMAT("--format");
    private String keyValue;

    Key(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getKeyValue() {
        return keyValue;
    }
}
