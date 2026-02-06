package androidx.core.transition;

import android.transition.Transition;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\u001aÆ\u0001\u0010\u0000\u001a\u00020\u0001*\u00020\u00022#\b\u0006\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\t\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u00042#\b\u0006\u0010\f\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\r\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u000f\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u0010\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u0011\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b\u001a2\u0010\u0012\u001a\u00020\u0001*\u00020\u00022#\b\u0004\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0087\b¨\u0006\u0013"}, d2 = {"addListener", "Landroid/transition/Transition$TransitionListener;", "Landroid/transition/Transition;", "onEnd", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "transition", "", "onStart", "onCancel", "onResume", "onPause", "doOnCancel", "action", "doOnEnd", "doOnPause", "doOnResume", "doOnStart", "core-ktx_release"}, k = 2, mv = {1, 1, 16})
public final class TransitionKt {
    public static final Transition.TransitionListener addListener(Transition transition, Function1<? super Transition, Unit> function1, Function1<? super Transition, Unit> function12, Function1<? super Transition, Unit> function13, Function1<? super Transition, Unit> function14, Function1<? super Transition, Unit> function15) {
        Intrinsics.checkParameterIsNotNull(transition, "$this$addListener");
        Intrinsics.checkParameterIsNotNull(function1, "onEnd");
        Intrinsics.checkParameterIsNotNull(function12, "onStart");
        Intrinsics.checkParameterIsNotNull(function13, "onCancel");
        Intrinsics.checkParameterIsNotNull(function14, "onResume");
        Intrinsics.checkParameterIsNotNull(function15, "onPause");
        TransitionKt$addListener$listener$1 transitionKt$addListener$listener$1 = new TransitionKt$addListener$listener$1(function1, function14, function15, function13, function12);
        transition.addListener(transitionKt$addListener$listener$1);
        return transitionKt$addListener$listener$1;
    }

    public static Transition.TransitionListener addListener$default(Transition transition, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = new Function1<Transition, Unit>() {
                @Override
                public Unit invoke(Transition transition2) {
                    invoke2(transition2);
                    return Unit.INSTANCE;
                }

                public final void invoke2(Transition transition2) {
                    Intrinsics.checkParameterIsNotNull(transition2, "it");
                }
            };
        }
        if ((i & 2) != 0) {
            function12 = new Function1<Transition, Unit>() {
                @Override
                public Unit invoke(Transition transition2) {
                    invoke2(transition2);
                    return Unit.INSTANCE;
                }

                public final void invoke2(Transition transition2) {
                    Intrinsics.checkParameterIsNotNull(transition2, "it");
                }
            };
        }
        Function1 function16 = function12;
        if ((i & 4) != 0) {
            function13 = new Function1<Transition, Unit>() {
                @Override
                public Unit invoke(Transition transition2) {
                    invoke2(transition2);
                    return Unit.INSTANCE;
                }

                public final void invoke2(Transition transition2) {
                    Intrinsics.checkParameterIsNotNull(transition2, "it");
                }
            };
        }
        Function1 function17 = function13;
        if ((i & 8) != 0) {
            function14 = new Function1<Transition, Unit>() {
                @Override
                public Unit invoke(Transition transition2) {
                    invoke2(transition2);
                    return Unit.INSTANCE;
                }

                public final void invoke2(Transition transition2) {
                    Intrinsics.checkParameterIsNotNull(transition2, "it");
                }
            };
        }
        if ((i & 16) != 0) {
            function15 = new Function1<Transition, Unit>() {
                @Override
                public Unit invoke(Transition transition2) {
                    invoke2(transition2);
                    return Unit.INSTANCE;
                }

                public final void invoke2(Transition transition2) {
                    Intrinsics.checkParameterIsNotNull(transition2, "it");
                }
            };
        }
        Intrinsics.checkParameterIsNotNull(transition, "$this$addListener");
        Intrinsics.checkParameterIsNotNull(function1, "onEnd");
        Intrinsics.checkParameterIsNotNull(function16, "onStart");
        Intrinsics.checkParameterIsNotNull(function17, "onCancel");
        Intrinsics.checkParameterIsNotNull(function14, "onResume");
        Intrinsics.checkParameterIsNotNull(function15, "onPause");
        TransitionKt$addListener$listener$1 transitionKt$addListener$listener$1 = new TransitionKt$addListener$listener$1(function1, function14, function15, function17, function16);
        transition.addListener(transitionKt$addListener$listener$1);
        return transitionKt$addListener$listener$1;
    }

    public static final Transition.TransitionListener doOnCancel(Transition transition, final Function1<? super Transition, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(transition, "$this$doOnCancel");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() {
            @Override
            public void onTransitionCancel(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
                function1.invoke(transition2);
            }

            @Override
            public void onTransitionEnd(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }

            @Override
            public void onTransitionPause(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }

            @Override
            public void onTransitionResume(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }

            @Override
            public void onTransitionStart(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }
        };
        transition.addListener(transitionListener);
        return transitionListener;
    }

    public static final Transition.TransitionListener doOnEnd(Transition transition, final Function1<? super Transition, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(transition, "$this$doOnEnd");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() {
            @Override
            public void onTransitionCancel(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }

            @Override
            public void onTransitionEnd(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
                function1.invoke(transition2);
            }

            @Override
            public void onTransitionPause(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }

            @Override
            public void onTransitionResume(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }

            @Override
            public void onTransitionStart(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }
        };
        transition.addListener(transitionListener);
        return transitionListener;
    }

    public static final Transition.TransitionListener doOnPause(Transition transition, final Function1<? super Transition, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(transition, "$this$doOnPause");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() {
            @Override
            public void onTransitionCancel(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }

            @Override
            public void onTransitionEnd(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }

            @Override
            public void onTransitionPause(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
                function1.invoke(transition2);
            }

            @Override
            public void onTransitionResume(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }

            @Override
            public void onTransitionStart(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }
        };
        transition.addListener(transitionListener);
        return transitionListener;
    }

    public static final Transition.TransitionListener doOnResume(Transition transition, final Function1<? super Transition, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(transition, "$this$doOnResume");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() {
            @Override
            public void onTransitionCancel(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }

            @Override
            public void onTransitionEnd(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }

            @Override
            public void onTransitionPause(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }

            @Override
            public void onTransitionResume(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
                function1.invoke(transition2);
            }

            @Override
            public void onTransitionStart(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }
        };
        transition.addListener(transitionListener);
        return transitionListener;
    }

    public static final Transition.TransitionListener doOnStart(Transition transition, final Function1<? super Transition, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(transition, "$this$doOnStart");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() {
            @Override
            public void onTransitionCancel(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }

            @Override
            public void onTransitionEnd(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }

            @Override
            public void onTransitionPause(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }

            @Override
            public void onTransitionResume(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
            }

            @Override
            public void onTransitionStart(Transition transition2) {
                Intrinsics.checkParameterIsNotNull(transition2, "transition");
                function1.invoke(transition2);
            }
        };
        transition.addListener(transitionListener);
        return transitionListener;
    }
}
