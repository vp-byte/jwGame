package com.jw.game.minigames.quiz.data;

public enum Difficulty {

    FUNNY,
    EASY,
    NORMAL,
    HARD;

    public static Difficulty parseIgnoreCase(String value) {
        for(Difficulty difficulty : Difficulty.values()) {
            if(value.equalsIgnoreCase(difficulty.name())){
                return difficulty;
            }
        }
        throw new RuntimeException("Can't find value: " + value + " for difficulty");
    }
}
