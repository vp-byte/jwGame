package com.jw.game.minigames.quiz;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizAnimationControl extends AbstractControl implements AnimEventListener {

    private final String spartialName;
    private AnimControl control;
    private AnimChannel channel;
    private List<String> goodAnims = Arrays.asList("good_veryeasy", "good_easy", "good_normal", "good_hard");
    private List<String> badAnims = Arrays.asList("bad_veryeasy", "bad_easy", "bad_normal", "bad_hard");
    private String animationName = "stand";

    public QuizAnimationControl(String spartialName) {
        this.spartialName = spartialName;
    }

    public void right() {
        Collections.shuffle(goodAnims);
        animationName = goodAnims.get(0);
    }

    public void wrong() {
        Collections.shuffle(badAnims);
        animationName = badAnims.get(0);
    }

    @Override
    public void setSpatial(Spatial spatial) {
        if (spatial != null) {
            this.spatial = spatial;
            control = ((Node) this.spatial).getChild(spartialName).getControl(AnimControl.class);
            control.addListener(this);
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
        if (animationName == null) {
            animationName = "stand";
        }
        
        if (channel.getAnimationName() == null || !channel.getAnimationName().equals(animationName)) {
            channel.setLoopMode(LoopMode.DontLoop);
            channel.setAnim(animationName);
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {

    }

    @Override
    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        if (!animName.equals("stand")) {
            animationName = "stand";
        }
    }

    @Override
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
    }

}
