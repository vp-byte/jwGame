package com.jw.game.minigames.quiz;

public class QuizData {

    public static String quizSceneAsset() {
        return "Scenes/minigames/quizscene.j3o";
    }
    public static String protagonist = "boy";

    public static String mainProtagonistAsset() {
        switch (protagonist) {
            case "boy":
                return "Models/boy/boy.j3o";
            case "girl":
                return "Models/girl/girl.j3o";
            default:
                throw new IllegalStateException("Protagonistmodel for " + protagonist + " do not exist");
        }
    }
}
