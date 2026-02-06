package com.xh.view.util;

import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

public class AnimationLoader {
    public static AnimationSet getInAnimation(Context context) {
        AnimationSet animationSet = new AnimationSet(context, null);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(90L);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.05f, 0.8f, 1.05f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(135L);
        ScaleAnimation scaleAnimation2 = new ScaleAnimation(1.05f, 0.95f, 1.05f, 0.95f, 1, 0.5f, 1, 0.5f);
        scaleAnimation2.setDuration(105L);
        scaleAnimation2.setStartOffset(135L);
        ScaleAnimation scaleAnimation3 = new ScaleAnimation(0.95f, 1.0f, 0.95f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation3.setDuration(60L);
        scaleAnimation3.setStartOffset(240L);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(scaleAnimation2);
        animationSet.addAnimation(scaleAnimation3);
        return animationSet;
    }

    public static AnimationSet getOutAnimation(Context context) {
        AnimationSet animationSet = new AnimationSet(context, null);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(150L);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.6f, 1.0f, 0.6f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(150L);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        return animationSet;
    }
}
