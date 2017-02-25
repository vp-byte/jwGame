package com.jw.game.minigames.quiz;

import com.jw.game.levels.main.*;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jw.game.minigames.quiz.data.Difficulty;
import com.jw.game.minigames.quiz.data.Quiz;
import com.jw.game.minigames.quiz.data.QuizService;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import java.util.Collections;
import java.util.List;

public class QuizScene extends AbstractAppState implements ScreenController {

    private SimpleApplication app;
    private AssetManager assetManager;
    private InputManager inputManager;
    private Node rootNode;
    private Spatial scene;
    private final Nifty nifty;
    private Screen screen;
    private final QuizService quizService;
    private List<Quiz> quizlist = null;

    public QuizScene(Nifty nifty) {
        this.nifty = nifty;
        this.quizService = new QuizService();
        this.quizlist = this.quizService.getAllQuizByDifficulty(Difficulty.values());
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        this.assetManager = this.app.getAssetManager();
        this.inputManager = this.app.getInputManager();
        this.rootNode = this.app.getRootNode();

        addScene();
        nifty.fromXml("Interface/quiz/quiz.xml", "quizScreen", this);
        this.app.getFlyByCamera().setEnabled(false);
        inputManager.setCursorVisible(true);
        setQuiz(nextQuiz());

        this.app.getCamera().setLocation(new Vector3f(0f, 1.2f, -2.85f));
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
        this.screen = screen;
    }

    @Override
    public void onStartScreen() {
    }

    @Override
    public void onEndScreen() {
    }

    public void answer(String answer) {
        Quiz quiz = quizlist.get(0);
        if (answer.equalsIgnoreCase(quiz.getRight().name())) {
            // TODO play congratulation cinematics
            System.err.println("play congratulation cinematics");
            if (quizlist.size() >= 1) {
                removeQuizFromList(quizlist.get(0));
                setQuiz(nextQuiz());
            } else {
                System.err.println("Game over");
                // TODO game over
            }
        } else {
            // TODO play wrong answer cinematics
            System.err.println("play wrong answer cinematics");
        }
    }

    private Quiz nextQuiz() {
        Collections.shuffle(quizlist);
        return quizlist.get(0);
    }

    private void setQuiz(Quiz quiz) {
        setQuizText(quiz.getQuestion());
        setButtonText("buttonA", quiz.getAnswer_a());
        setButtonText("buttonB", quiz.getAnswer_b());
        setButtonText("buttonC", quiz.getAnswer_c());
        setButtonText("buttonD", quiz.getAnswer_d());
    }

    private void setQuizText(String text) {
        Element textField = screen.findElementById("quizText");
        if (textField == null) {
            throw new IllegalStateException("TextField with id=quizText not found");
        }
        TextRenderer textRenderer = textField.getRenderer(TextRenderer.class);
        if (textRenderer == null) {
            throw new IllegalStateException("TextRenderer from quizText not found");
        }
        textRenderer.setText(text);
    }

    private void setButtonText(String buttonId, String text) {
        Button button = screen.findNiftyControl(buttonId, Button.class);
        if (button == null) {
            throw new IllegalStateException("Button with id=" + buttonId + " not found");
        }
        button.setText(text);
    }

    private void removeQuizFromList(Quiz quiz) {
        quizlist.remove(quiz);
    }
}
