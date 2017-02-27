package com.jw.game.levels.main.protagonist;

import com.jme3.bullet.control.BetterCharacterControl;

public class ProtagonistControl extends BetterCharacterControl {

    private boolean run = false;
    private boolean forward = false;
    private boolean backward = false;
    private boolean leftRotate = false;
    private boolean rightRotate = false;
    private boolean leftStrafe = false;
    private boolean rightStrafe = false;
    private boolean onJump = false;

    public ProtagonistControl(float radius, float height, float mass) {
        super(radius, height, mass);
    }

    public boolean isLeftStrafe() {
        return leftStrafe;
    }

    public void setLeftStrafe(boolean leftStrafe) {
        this.leftStrafe = leftStrafe;
    }

    public boolean isRightStrafe() {
        return rightStrafe;
    }

    public void setRightStrafe(boolean rightStrafe) {
        this.rightStrafe = rightStrafe;
    }

    public boolean isLeftRotate() {
        return leftRotate;
    }

    public void setLeftRotate(boolean leftRotate) {
        this.leftRotate = leftRotate;
    }

    public boolean isRightRotate() {
        return rightRotate;
    }

    public void setRightRotate(boolean rightRotate) {
        this.rightRotate = rightRotate;
    }

    public boolean isForward() {
        return forward;
    }

    public void setForward(boolean forward) {
        this.forward = forward;
    }

    public boolean isBackward() {
        return backward;
    }

    public void setBackward(boolean backward) {
        this.backward = backward;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public boolean isOnJump() {
        return onJump;
    }

    public void setOnJump(boolean onJump) {
        this.onJump = onJump;
    }
}
