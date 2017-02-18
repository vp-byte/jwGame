package com.jw.game;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import com.jw.game.levels.main.MainScene;
import com.jw.game.minigames.quiz.QuizScene;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class JWGame extends SimpleApplication implements ScreenController {

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
        
/*        
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager,
                inputManager,
                audioRenderer,
                guiViewPort);
        Nifty nifty = niftyDisplay.getNifty();
        nifty.fromXml("Interface/quiz/quiz.xml", "start", this);

        // attach the nifty display to the gui view port as a processor
        guiViewPort.addProcessor(niftyDisplay);

        // disable the fly cam
//        flyCam.setEnabled(false);
//        flyCam.setDragToRotate(true);
        inputManager.setCursorVisible(true);*/
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

    @Override
    public void bind(Nifty nifty, Screen screen) {
        System.out.println("bind( " + screen.getScreenId() + ")");
    }

    @Override
    public void onStartScreen() {
        System.out.println("onStartScreen");
    }

    @Override
    public void onEndScreen() {
        System.out.println("onEndScreen");
    }
}
