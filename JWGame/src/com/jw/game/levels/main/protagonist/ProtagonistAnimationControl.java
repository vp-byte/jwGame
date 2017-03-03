package com.jw.game.levels.main.protagonist;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.LoopMode;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;

public class ProtagonistAnimationControl extends AbstractControl {

    ProtagonistControl protagonistControl;
    private AnimControl control;
    private AnimChannel channel;
    private String animationName;

    @Override
    public void setSpatial(Spatial spatial) {
        super.setSpatial(spatial);
        if (spatial != null) {
            this.protagonistControl = ((Node) this.spatial).getControl(ProtagonistControl.class);
            control = ((Node) this.spatial).getChild("protagonist").getControl(AnimControl.class);
            channel = control.createChannel();
        }
    }

    @Override
    protected void controlUpdate(float tpf) {
        if (spatial == null) {
            return;
        }
        updateAnimation();
    }

    private void updateAnimation() {
        animationName = channel.getAnimationName();

        if (animationName == null) {
            channel.setLoopMode(LoopMode.Loop);
            channel.setAnim("stand");
        } else if (protagonistControl.isOnGround()) {
            if (protagonistControl.isForward() && !protagonistControl.isDucked() || !protagonistControl.isBackward() && protagonistControl.isRightStrafe() && !protagonistControl.isDucked() || !protagonistControl.isBackward() && protagonistControl.isLeftStrafe() && !protagonistControl.isDucked()) {
                if (protagonistControl.isRun()) {
                    if (!animationName.equals("run")) {
                        channel.setAnim("run");
                        channel.setLoopMode(LoopMode.Loop);
                    }
                } else if (!animationName.equals("walk")) {
                    channel.setLoopMode(LoopMode.Loop);
                    channel.setAnim("walk");
                }
            } else if (protagonistControl.isBackward() && !protagonistControl.isDucked()) {
                if (!animationName.equals("walk_back")) {
                    channel.setLoopMode(LoopMode.Loop);
                    channel.setAnim("walk_back");
                }
            } else if (protagonistControl.isDucked()) {
                if (protagonistControl.isForward() || protagonistControl.isBackward() || protagonistControl.isRightStrafe() || protagonistControl.isLeftStrafe()) {
                    if (!animationName.equals("duck_forward")) {
                        channel.setAnim("duck_forward");
                        channel.setLoopMode(LoopMode.Loop);
                    }
                } else if (!animationName.equals("duck")) {
                    channel.setAnim("duck");
                    channel.setLoopMode(LoopMode.Loop);
                }
            } else if (!animationName.equals("stand")) {
                channel.setLoopMode(LoopMode.Loop);
                channel.setAnim("stand");
            }

        } else if (protagonistControl.isOnJump() && !animationName.equals("jump")) {
            channel.setLoopMode(LoopMode.Loop);
            channel.setAnim("jump");
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
}
