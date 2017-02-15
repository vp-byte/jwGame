package com.jw.game.levels.main.protagonist;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jw.game.levels.main.MainData;

public class ProtagonistState extends AbstractAppState implements ActionListener {

    private SimpleApplication app;
    private InputManager inputManager;
    private Node rootNode;
    private Spatial protagonist;
    private Node protagonistNode;
    private ProtagonistControl protagonistControl;
    private Vector3f walkDirection = new Vector3f(0, 0, 0);
    private Vector3f viewDirection = new Vector3f(0, 0, 1);
    private float walkSpeep = 2.5f;
    private float runSpeed = 5f;
    private float duckedSpeed = 1f;
    private float speed = 0;

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        this.app = (SimpleApplication) app;
        this.inputManager = this.app.getInputManager();

        this.rootNode = this.app.getRootNode();
        this.protagonist = this.rootNode.getChild(MainData.nameProtagonistSpartial());
        this.protagonistNode = (Node) this.rootNode.getChild(MainData.nameProtagonistNode());
        protagonistControl = protagonistNode.getControl(ProtagonistControl.class);

        setupKeys();
    }

    private void setupKeys() {
        inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Backward", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Rotate Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Rotate Right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Strafe Left", new KeyTrigger(KeyInput.KEY_Q));
        inputManager.addMapping("Strafe Right", new KeyTrigger(KeyInput.KEY_E));
        inputManager.addMapping("Run", new KeyTrigger(KeyInput.KEY_LSHIFT));
        inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping("Duck", new KeyTrigger(KeyInput.KEY_C));

        inputManager.addListener(this, "Forward", "Backward");
        inputManager.addListener(this, "Rotate Left", "Rotate Right");
        inputManager.addListener(this, "Strafe Left", "Strafe Right");
        inputManager.addListener(this, "Run", "Jump", "Duck");
    }

    public void onAction(String binding, boolean value, float tpf) {
        if (binding.equals("Forward")) {
            if (value) {
                protagonistControl.setForward(true);
            } else {
                protagonistControl.setForward(false);
            }
        } else if (binding.equals("Backward")) {
            if (value) {
                protagonistControl.setBackward(true);
            } else {
                protagonistControl.setBackward(false);
            }
        } else if (binding.equals("Rotate Left")) {
            if (value) {
                protagonistControl.setLeftRotate(true);
            } else {
                protagonistControl.setLeftRotate(false);
            }
        } else if (binding.equals("Rotate Right")) {
            if (value) {
                protagonistControl.setRightRotate(true);
            } else {
                protagonistControl.setRightRotate(false);
            }
        } else if (binding.equals("Strafe Left")) {
            if (value) {
                protagonistControl.setLeftStrafe(true);
            } else {
                protagonistControl.setLeftStrafe(false);
            }
        } else if (binding.equals("Strafe Right")) {
            if (value) {
                protagonistControl.setRightStrafe(true);
            } else {
                protagonistControl.setRightStrafe(false);
            }
        } else if (binding.equals("Run")) {
            if (value) {
                protagonistControl.setRun(true);
            } else {
                protagonistControl.setRun(false);
            }
        } else if (binding.equals("Jump")) {
            protagonistControl.jump();
            protagonistControl.setOnJump(true);
        } else if (binding.equals("Duck")) {
            if (value) {
                protagonistControl.setDucked(true);
            } else {
                protagonistControl.setDucked(false);
            }
        }
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);

        if (protagonistControl.isOnGround()) {
            protagonistControl.setOnJump(false);
        }

        Vector3f modelForwardDir = protagonistNode.getWorldRotation().mult(Vector3f.UNIT_Z);
        Vector3f modelLeftDir = protagonistNode.getWorldRotation().mult(Vector3f.UNIT_X);

        if (protagonistControl.isRun() && !protagonistControl.isDucked()) {
            speed = runSpeed;
        } else if (protagonistControl.isDucked()) {
            speed = duckedSpeed;
        } else {
            speed = walkSpeep;
        }

        walkDirection.set(0, 0, 0);
        if (protagonistControl.isForward() && !protagonistControl.isLeftStrafe() && !protagonistControl.isRightStrafe()) {
            Quaternion rotateL = new Quaternion().fromAngleAxis(0f, Vector3f.UNIT_Y);
            protagonist.setLocalRotation(rotateL);
            walkDirection.addLocal(modelForwardDir.mult(speed));
        } else if (protagonistControl.isBackward() && !protagonistControl.isLeftStrafe() && !protagonistControl.isRightStrafe()) {
            Quaternion rotateL = new Quaternion().fromAngleAxis(0f, Vector3f.UNIT_Y);
            protagonist.setLocalRotation(rotateL);
            walkDirection.addLocal(modelForwardDir.negate().mult(speed));
        }

        if (protagonistControl.isLeftRotate()) {
            Quaternion rotateL = new Quaternion().fromAngleAxis(FastMath.PI * tpf, Vector3f.UNIT_Y);
            rotateL.multLocal(viewDirection);
        } else if (protagonistControl.isRightRotate()) {
            Quaternion rotateR = new Quaternion().fromAngleAxis(-FastMath.PI * tpf, Vector3f.UNIT_Y);
            rotateR.multLocal(viewDirection);
        }

        if (protagonistControl.isLeftStrafe()) {
            if (protagonistControl.isForward()) {
                Quaternion rotateL = new Quaternion().fromAngleAxis(FastMath.PI / 8, Vector3f.UNIT_Y);
                protagonist.setLocalRotation(rotateL);
                walkDirection.addLocal(modelForwardDir.mult(speed / 2f));
                walkDirection.addLocal(modelLeftDir.mult(speed / 2f));
            } else if (protagonistControl.isBackward()) {
                Quaternion rotateL = new Quaternion().fromAngleAxis(-FastMath.PI / 8, Vector3f.UNIT_Y);
                protagonist.setLocalRotation(rotateL);
                walkDirection.addLocal(modelForwardDir.negate().mult(speed / 2f));
                walkDirection.addLocal(modelLeftDir.mult(speed / 2));
            } else {
                Quaternion rotateL = new Quaternion().fromAngleAxis(FastMath.PI / 2, Vector3f.UNIT_Y);
                protagonist.setLocalRotation(rotateL);
                walkDirection.addLocal(modelLeftDir.mult(speed));
            }
        } else if (protagonistControl.isRightStrafe()) {
            if (protagonistControl.isForward()) {
                Quaternion rotateL = new Quaternion().fromAngleAxis(-FastMath.PI / 8, Vector3f.UNIT_Y);
                protagonist.setLocalRotation(rotateL);
                walkDirection.addLocal(modelForwardDir.mult(speed / 2f));
                walkDirection.addLocal(modelLeftDir.negate().mult(speed / 2f));
            } else if (protagonistControl.isBackward()) {
                Quaternion rotateL = new Quaternion().fromAngleAxis(FastMath.PI / 8, Vector3f.UNIT_Y);
                protagonist.setLocalRotation(rotateL);
                walkDirection.addLocal(modelForwardDir.negate().mult(speed / 2f));
                walkDirection.addLocal(modelLeftDir.negate().mult(speed / 2f));
            } else {
                Quaternion rotateL = new Quaternion().fromAngleAxis(-FastMath.PI / 2, Vector3f.UNIT_Y);
                protagonist.setLocalRotation(rotateL);
                walkDirection.addLocal(modelLeftDir.negate().multLocal(speed));
            }
        }

        protagonistControl.setWalkDirection(walkDirection);
        protagonistControl.setViewDirection(viewDirection);
    }
}
