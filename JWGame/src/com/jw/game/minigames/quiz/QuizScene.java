package com.jw.game.minigames.quiz;

import com.jw.game.levels.main.*;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioRenderer;
import com.jme3.input.InputManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

public class QuizScene extends AbstractAppState implements ScreenController {

    private SimpleApplication app;
    private AppStateManager stateManager;
    private AssetManager assetManager;
    private InputManager inputManager;
    private AudioRenderer audioRenderer;
    private ViewPort guiViewPort;
    private Node rootNode;
    private Spatial scene;
    private Nifty nifty;
    private Screen screen;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        this.stateManager = this.app.getStateManager();
        this.assetManager = this.app.getAssetManager();
        this.inputManager = this.app.getInputManager();
        this.audioRenderer = this.app.getAudioRenderer();
        this.guiViewPort = this.app.getGuiViewPort();
        this.rootNode = this.app.getRootNode();

        addScene();

        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager,
                inputManager,
                audioRenderer,
                guiViewPort);
        this.nifty = niftyDisplay.getNifty();
        nifty.fromXml("Interface/quiz/quiz.xml", "quizScreen", this);
        this.screen = this.nifty.getCurrentScreen();
        

        // attach the nifty display to the gui view port as a processor
        guiViewPort.addProcessor(niftyDisplay);

        // disable the fly cam
//        flyCam.setEnabled(false);
//        flyCam.setDragToRotate(true);
        inputManager.setCursorVisible(true);
    }

    private void addScene() {
        scene = assetManager.loadModel(QuizData.quizSceneAsset());
        scene.setName(MainData.mainSceneSpartial());
        this.rootNode.attachChild(scene);
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }

    @Override
    public void cleanup() {
        super.cleanup();
        this.rootNode.detachAllChildren();
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
