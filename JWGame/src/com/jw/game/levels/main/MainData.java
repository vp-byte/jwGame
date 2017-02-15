package com.jw.game.levels.main;

public class MainData {

    public static String mainSceneAsset() {
        return "Scenes/levels/mainscene.j3o";
    }

    public static String mainSceneSpartial() {
        return "mainscene";
    }

    public static String mainSkySpartial() {
        return "sky";
    }

    public static String mainSkyTextureWest() {
        return "Textures/world/sky/lagoon_west.jpg";
    }

    public static String mainSkyTextureEast() {
        return "Textures/world/sky/lagoon_east.jpg";
    }

    public static String mainSkyTextureNorth() {
        return "Textures/world/sky/lagoon_north.jpg";
    }

    public static String mainSkyTextureSouth() {
        return "Textures/world/sky/lagoon_south.jpg";
    }

    public static String mainSkyTextureUp() {
        return "Textures/world/sky/lagoon_up.jpg";
    }

    public static String mainSkyTextureDown() {
        return "Textures/world/sky/lagoon_down.jpg";
    }

    public static String nameProtagonistSpartial() {
        return "protagonist";
    }

    public static String nameProtagonistNode() {
        return nameProtagonistSpartial() + "Node";
    }

    public static String nameCameraNode() {
        return "cameraNode";
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
