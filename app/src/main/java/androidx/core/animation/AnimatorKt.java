package androidx.core.animation;

import android.animation.Animator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u001a¡\u0001\u0010\u0000\u001a\u00020\u0001*\u00020\u00022#\b\u0006\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0086\b\u001aW\u0010\f\u001a\u00020\r*\u00020\u00022#\b\u0006\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\u000f\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u0010\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0086\b\u001a2\u0010\u0012\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0086\b\u001a2\u0010\u0013\u001a\u00020\r*\u00020\u00022#\b\u0004\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u0014\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0086\b\u001a2\u0010\u0015\u001a\u00020\r*\u00020\u00022#\b\u0004\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u0016\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0086\b¨\u0006\u0017"}, d2 = {"addListener", "Landroid/animation/Animator$AnimatorListener;", "Landroid/animation/Animator;", "onEnd", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "animator", "", "onStart", "onCancel", "onRepeat", "addPauseListener", "Landroid/animation/Animator$AnimatorPauseListener;", "onResume", "onPause", "doOnCancel", "action", "doOnEnd", "doOnPause", "doOnRepeat", "doOnResume", "doOnStart", "core-ktx_release"}, k = 2, mv = {1, 1, 16})
public final class AnimatorKt {
    public static final Animator.AnimatorListener addListener(Animator animator, Function1<? super Animator, Unit> function1, Function1<? super Animator, Unit> function12, Function1<? super Animator, Unit> function13, Function1<? super Animator, Unit> function14) {
        Intrinsics.checkParameterIsNotNull(animator, "$this$addListener");
        Intrinsics.checkParameterIsNotNull(function1, "onEnd");
        Intrinsics.checkParameterIsNotNull(function12, "onStart");
        Intrinsics.checkParameterIsNotNull(function13, "onCancel");
        Intrinsics.checkParameterIsNotNull(function14, "onRepeat");
        AnimatorKt$addListener$listener$1 animatorKt$addListener$listener$1 = new AnimatorKt$addListener$listener$1(function14, function1, function13, function12);
        animator.addListener(animatorKt$addListener$listener$1);
        return animatorKt$addListener$listener$1;
    }

    public static Animator.AnimatorListener addListener$default(Animator animator, Function1 function1, Function1 function12, Function1 function13, Function1 function14, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Animator, Unit>() {
                @Override
                public Unit invoke(Animator animator2) {
                    invoke2(animator2);
                    return Unit.INSTANCE;
                }

                public final void invoke2(Animator animator2) {
                    Intrinsics.checkParameterIsNotNull(animator2, "it");
                }
            };
        }
        if ((i & 2) != 0) {
            function12 = new Function1<Animator, Unit>() {
                @Override
                public Unit invoke(Animator animator2) {
                    invoke2(animator2);
                    return Unit.INSTANCE;
                }

                public final void invoke2(Animator animator2) {
                    Intrinsics.checkParameterIsNotNull(animator2, "it");
                }
            };
        }
        if ((i & 4) != 0) {
            function13 = new Function1<Animator, Unit>() {
                @Override
                public Unit invoke(Animator animator2) {
                    invoke2(animator2);
                    return Unit.INSTANCE;
                }

                public final void invoke2(Animator animator2) {
                    Intrinsics.checkParameterIsNotNull(animator2, "it");
                }
            };
        }
        if ((i & 8) != 0) {
            function14 = new Function1<Animator, Unit>() {
                @Override
                public Unit invoke(Animator animator2) {
                    invoke2(animator2);
                    return Unit.INSTANCE;
                }

                public final void invoke2(Animator animator2) {
                    Intrinsics.checkParameterIsNotNull(animator2, "it");
                }
            };
        }
        Intrinsics.checkParameterIsNotNull(animator, "$this$addListener");
        Intrinsics.checkParameterIsNotNull(function1, "onEnd");
        Intrinsics.checkParameterIsNotNull(function12, "onStart");
        Intrinsics.checkParameterIsNotNull(function13, "onCancel");
        Intrinsics.checkParameterIsNotNull(function14, "onRepeat");
        AnimatorKt$addListener$listener$1 animatorKt$addListener$listener$1 = new AnimatorKt$addListener$listener$1(function14, function1, function13, function12);
        animator.addListener(animatorKt$addListener$listener$1);
        return animatorKt$addListener$listener$1;
    }

    public static final Animator.AnimatorPauseListener addPauseListener(Animator animator, Function1<? super Animator, Unit> function1, Function1<? super Animator, Unit> function12) {
        Intrinsics.checkParameterIsNotNull(animator, "$this$addPauseListener");
        Intrinsics.checkParameterIsNotNull(function1, "onResume");
        Intrinsics.checkParameterIsNotNull(function12, "onPause");
        AnimatorKt$addPauseListener$listener$1 animatorKt$addPauseListener$listener$1 = new AnimatorKt$addPauseListener$listener$1(function12, function1);
        animator.addPauseListener(animatorKt$addPauseListener$listener$1);
        return animatorKt$addPauseListener$listener$1;
    }

    public static Animator.AnimatorPauseListener addPauseListener$default(Animator animator, Function1 function1, Function1 function12, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Animator, Unit>() {
                @Override
                public Unit invoke(Animator animator2) {
                    invoke2(animator2);
                    return Unit.INSTANCE;
                }

                public final void invoke2(Animator animator2) {
                    Intrinsics.checkParameterIsNotNull(animator2, "it");
                }
            };
        }
        if ((i & 2) != 0) {
            function12 = new Function1<Animator, Unit>() {
                @Override
                public Unit invoke(Animator animator2) {
                    invoke2(animator2);
                    return Unit.INSTANCE;
                }

                public final void invoke2(Animator animator2) {
                    Intrinsics.checkParameterIsNotNull(animator2, "it");
                }
            };
        }
        Intrinsics.checkParameterIsNotNull(animator, "$this$addPauseListener");
        Intrinsics.checkParameterIsNotNull(function1, "onResume");
        Intrinsics.checkParameterIsNotNull(function12, "onPause");
        AnimatorKt$addPauseListener$listener$1 animatorKt$addPauseListener$listener$1 = new AnimatorKt$addPauseListener$listener$1(function12, function1);
        animator.addPauseListener(animatorKt$addPauseListener$listener$1);
        return animatorKt$addPauseListener$listener$1;
    }

    public static final Animator.AnimatorListener doOnCancel(Animator animator, final Function1<? super Animator, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(animator, "$this$doOnCancel");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationCancel(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
                function1.invoke(animator2);
            }

            @Override
            public void onAnimationEnd(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
            }

            @Override
            public void onAnimationRepeat(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
            }

            @Override
            public void onAnimationStart(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
            }
        };
        animator.addListener(animatorListener);
        return animatorListener;
    }

    public static final Animator.AnimatorListener doOnEnd(Animator animator, final Function1<? super Animator, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(animator, "$this$doOnEnd");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationCancel(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
            }

            @Override
            public void onAnimationEnd(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
                function1.invoke(animator2);
            }

            @Override
            public void onAnimationRepeat(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
            }

            @Override
            public void onAnimationStart(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
            }
        };
        animator.addListener(animatorListener);
        return animatorListener;
    }

    public static final Animator.AnimatorPauseListener doOnPause(Animator animator, final Function1<? super Animator, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(animator, "$this$doOnPause");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        Animator.AnimatorPauseListener animatorPauseListener = new Animator.AnimatorPauseListener() {
            @Override
            public void onAnimationPause(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
                function1.invoke(animator2);
            }

            @Override
            public void onAnimationResume(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
            }
        };
        animator.addPauseListener(animatorPauseListener);
        return animatorPauseListener;
    }

    public static final Animator.AnimatorListener doOnRepeat(Animator animator, final Function1<? super Animator, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(animator, "$this$doOnRepeat");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationCancel(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
            }

            @Override
            public void onAnimationEnd(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
            }

            @Override
            public void onAnimationRepeat(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
                function1.invoke(animator2);
            }

            @Override
            public void onAnimationStart(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
            }
        };
        animator.addListener(animatorListener);
        return animatorListener;
    }

    public static final Animator.AnimatorPauseListener doOnResume(Animator animator, final Function1<? super Animator, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(animator, "$this$doOnResume");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        Animator.AnimatorPauseListener animatorPauseListener = new Animator.AnimatorPauseListener() {
            @Override
            public void onAnimationPause(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
            }

            @Override
            public void onAnimationResume(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
                function1.invoke(animator2);
            }
        };
        animator.addPauseListener(animatorPauseListener);
        return animatorPauseListener;
    }

    public static final Animator.AnimatorListener doOnStart(Animator animator, final Function1<? super Animator, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(animator, "$this$doOnStart");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationCancel(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
            }

            @Override
            public void onAnimationEnd(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
            }

            @Override
            public void onAnimationRepeat(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
            }

            @Override
            public void onAnimationStart(Animator animator2) {
                Intrinsics.checkParameterIsNotNull(animator2, "animator");
                function1.invoke(animator2);
            }
        };
        animator.addListener(animatorListener);
        return animatorListener;
    }
}
