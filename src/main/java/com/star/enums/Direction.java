package com.star.enums;

public enum Direction {
    UP("up"),
    DOWN("down"),
    LEFT("left"),
    RIGHT("right");

    final String desc;

    public String getDesc() {
        return desc;
    }

    Direction(String desc) {
        this.desc = desc;
    }
}
