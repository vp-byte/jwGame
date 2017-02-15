package com.jw.game.minigames.quiz.data;

public enum Right {

    A, B, C, D;

    public static Right parseIgnoreCase(String value) {
        for (Right right : Right.values()) {
            if (value.equalsIgnoreCase(right.name())) {
                return right;
            }
        }
        throw new RuntimeException("Can't find value: " + value + " for reight answer");
    }
}
