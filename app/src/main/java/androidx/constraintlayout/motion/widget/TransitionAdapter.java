package androidx.constraintlayout.motion.widget;

import androidx.constraintlayout.motion.widget.MotionLayout;

public abstract class TransitionAdapter implements MotionLayout.TransitionListener {
    @Override
    public void onTransitionChange(MotionLayout motionLayout, int i, int i2, float f) {
    }

    @Override
    public void onTransitionCompleted(MotionLayout motionLayout, int i) {
    }

    @Override
    public void onTransitionStarted(MotionLayout motionLayout, int i, int i2) {
    }

    @Override
    public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean z, float f) {
    }
}
