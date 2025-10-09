package com.zyrexinfinity.f1racemanager.enums;

public enum MessageColor {
    RED("\u001B[31m"), GREEN("\u001B[32m"), YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"), PURPLE("\u001B[35m"), CYAN("\u001B[36m");

    private final String color;

    MessageColor(String color){
        this.color = color;
    }
    public String getColor() {
        return color;
    }
}
