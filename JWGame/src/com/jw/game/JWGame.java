package com.jw.game;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import com.jw.game.levels.main.MainScene;
import com.jw.game.minigames.quiz.QuizScene;

public class JWGame extends SimpleApplication {
    private MainScene mainScene;
    private QuizScene quizScene;
    boolean test = false;

    public static void main(String[] args) {
        JWGame application = new JWGame();
//        JWSettings settings = new JWSettings(application);
//        settings.loadDefaults();
//        application.setShowSettings(false);
        application.start();
    }

    @Override
    public void simpleInitApp() {
        mainScene = new MainScene();
        quizScene = new QuizScene();
        
        stateManager.attach(mainScene);

        Spatial blueflower = assetManager.loadModel("Models/plants/blueflower/blueflower.j3o");
        blueflower.setName("blueflower");
        rootNode.attachChild(blueflower);

    }

    @Override
    public void simpleUpdate(float tpf) {
//        if(!test) {
//            test = true;
//            stateManager.detach(mainScene);
//            stateManager.attach(quizScene);
//        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
    }
}
