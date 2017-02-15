package com.jw.game.minigames.quiz.data;

public enum Difficulty {

    VERYEASY,
    EASY,
    NORMAL,
    HARD,
    VERYHARD;

    public static Difficulty parseIgnoreCase(String value) {
        for(Difficulty difficulty : Difficulty.values()) {
            if(value.equalsIgnoreCase(difficulty.name())){
                return difficulty;
            }
        }
        throw new RuntimeException("Can't find value: " + value + " for difficulty");
    }
}
