package kshark;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kshark.ReferencePattern;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\bC\b\u0086\u0001\u0018\u0000 I2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001IB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H ¢\u0006\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bDj\u0002\bEj\u0002\bFj\u0002\bGj\u0002\bH¨\u0006J"}, d2 = {"Lkshark/AndroidReferenceMatchers;", "", "(Ljava/lang/String;I)V", "add", "", "references", "", "Lkshark/ReferenceMatcher;", "add$shark", "IREQUEST_FINISH_CALLBACK", "ACTIVITY_CLIENT_RECORD__NEXT_IDLE", "SPAN_CONTROLLER", "MEDIA_SESSION_LEGACY_HELPER__SINSTANCE", "TEXT_LINE__SCACHED", "BLOCKING_QUEUE", "INPUT_METHOD_MANAGER_IS_TERRIBLE", "LAYOUT_TRANSITION", "SPELL_CHECKER_SESSION", "SPELL_CHECKER", "ACTIVITY_CHOOSE_MODEL", "MEDIA_PROJECTION_CALLBACK", "SPEECH_RECOGNIZER", "ACCOUNT_MANAGER", "MEDIA_SCANNER_CONNECTION", "USER_MANAGER__SINSTANCE", "APP_WIDGET_HOST_CALLBACKS", "AUDIO_MANAGER", "EDITTEXT_BLINK_MESSAGEQUEUE", "CONNECTIVITY_MANAGER__SINSTANCE", "ACCESSIBILITY_NODE_INFO__MORIGINALTEXT", "ASSIST_STRUCTURE", "ACCESSIBILITY_ITERATORS", "BIOMETRIC_PROMPT", "MAGNIFIER", "BACKDROP_FRAME_RENDERER__MDECORVIEW", "VIEWLOCATIONHOLDER_ROOT", "ACCESSIBILITY_NODE_ID_MANAGER", "TEXT_TO_SPEECH", "WINDOW_MANAGER_GLOBAL", "CONTROLLED_INPUT_CONNECTION_WRAPPER", "TOAST_TN", "SPEN_GESTURE_MANAGER", "CLIPBOARD_UI_MANAGER__SINSTANCE", "SEM_CLIPBOARD_MANAGER__MCONTEXT", "CLIPBOARD_EX_MANAGER", "SEM_EMERGENCY_MANAGER__MCONTEXT", "SEM_PERSONA_MANAGER", "SEM_APP_ICON_SOLUTION", "AW_RESOURCE__SRESOURCES", "TEXT_VIEW__MLAST_HOVERED_VIEW", "PERSONA_MANAGER", "RESOURCES__MCONTEXT", "VIEW_CONFIGURATION__MCONTEXT", "AUDIO_MANAGER__MCONTEXT_STATIC", "ACTIVITY_MANAGER_MCONTEXT", "STATIC_MTARGET_VIEW", "GESTURE_BOOST_MANAGER", "BUBBLE_POPUP_HELPER__SHELPER", "LGCONTEXT__MCONTEXT", "SMART_COVER_MANAGER", "MAPPER_CLIENT", "SYSTEM_SENSOR_MANAGER__MAPPCONTEXTIMPL", "INSTRUMENTATION_RECOMMEND_ACTIVITY", "DEVICE_POLICY_MANAGER__SETTINGS_OBSERVER", "EXTENDED_STATUS_BAR_MANAGER", "OEM_SCENE_CALL_BLOCKER", "REFERENCES", "FINALIZER_WATCHDOG_DAEMON", "MAIN", "LEAK_CANARY_THREAD", "LEAK_CANARY_HEAP_DUMPER", "LEAK_CANARY_INTERNAL", "EVENT_RECEIVER__MMESSAGE_QUEUE", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public enum AndroidReferenceMatchers {
    IREQUEST_FINISH_CALLBACK {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.app.Activity$1", "this$0", "Android Q added a new android.app.IRequestFinishCallback$Stub class. android.app.Activity creates an implementation of that interface as an anonymous subclass. That anonymous subclass has a reference to the activity. Another process is keeping the android.app.IRequestFinishCallback$Stub reference alive long after Activity.onDestroyed() has been called, causing the activity to leak.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() == 29;
                }
            }));
        }
    },
    ACTIVITY_CLIENT_RECORD__NEXT_IDLE {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.app.ActivityThread$ActivityClientRecord", "nextIdle", "Android AOSP sometimes keeps a reference to a destroyed activity as a nextIdle client record in the android.app.ActivityThread.mActivities map. Not sure what's going on there, input welcome.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    int sdkInt = androidBuildMirror.getSdkInt();
                    return 19 <= sdkInt && 27 >= sdkInt;
                }
            }));
        }
    },
    SPAN_CONTROLLER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            List<ReferenceMatcher> list = references;
            list.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.widget.Editor$SpanController", "this$0", "Editor inserts a special span, which has a reference to the EditText. That span is a NoCopySpan, which makes sure it gets dropped when creating a new SpannableStringBuilder from a given CharSequence. TextView.onSaveInstanceState() does a copy of its mText before saving it in the bundle. Prior to KitKat, that copy was done using the SpannableString constructor, instead of SpannableStringBuilder. The SpannableString constructor does not drop NoCopySpan spans. So we end up with a saved state that holds a reference to the textview and therefore the entire view hierarchy & activity context. Fix: https://github.com/android/platform_frameworks_base/commit/af7dcdf35a37d7a7dbaad7d9869c1c91bce2272b . To fix this, you could override TextView.onSaveInstanceState(), and then use reflection to access TextView.SavedState.mText and clear the NoCopySpan spans.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() <= 19;
                }
            }));
            list.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.widget.Editor$EasyEditSpanController", "this$0", "Editor inserts a special span, which has a reference to the EditText. That span is a NoCopySpan, which makes sure it gets dropped when creating a new SpannableStringBuilder from a given CharSequence. TextView.onSaveInstanceState() does a copy of its mText before saving it in the bundle. Prior to KitKat, that copy was done using the SpannableString constructor, instead of SpannableStringBuilder. The SpannableString constructor does not drop NoCopySpan spans. So we end up with a saved state that holds a reference to the textview and therefore the entire view hierarchy & activity context. Fix: https://github.com/android/platform_frameworks_base/commit/af7dcdf35a37d7a7dbaad7d9869c1c91bce2272b . To fix this, you could override TextView.onSaveInstanceState(), and then use reflection to access TextView.SavedState.mText and clear the NoCopySpan spans.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() <= 19;
                }
            }));
        }
    },
    MEDIA_SESSION_LEGACY_HELPER__SINSTANCE {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.staticFieldLeak("android.media.session.MediaSessionLegacyHelper", "sInstance", "MediaSessionLegacyHelper is a static singleton that is lazily instantiated and keeps a reference to the context it's given the first time MediaSessionLegacyHelper.getHelper() is called. This leak was introduced in android-5.0.1_r1 and fixed in Android 5.1.0_r1 by calling context.getApplicationContext(). Fix: https://github.com/android/platform_frameworks_base/commit/9b5257c9c99c4cb541d8e8e78fb04f008b1a9091 To fix this, you could call MediaSessionLegacyHelper.getHelper() early in Application.onCreate() and pass it the application context.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() == 21;
                }
            }));
        }
    },
    TEXT_LINE__SCACHED {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.staticFieldLeak("android.text.TextLine", "sCached", "TextLine.sCached is a pool of 3 TextLine instances. TextLine.recycle() has had at least two bugs that created memory leaks by not correctly clearing the recycled TextLine instances. The first was fixed in android-5.1.0_r1: https://github.com/android/platform_frameworks_base/commit/893d6fe48d37f71e683f722457bea646994a10 The second was fixed, not released yet: https://github.com/android/platform_frameworks_base/commit/b3a9bc038d3a218b1dbdf7b5668e3d6c12be5e To fix this, you could access TextLine.sCached and clear the pool every now and then (e.g. on activity destroy).", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() <= 22;
                }
            }));
        }
    },
    BLOCKING_QUEUE {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            List<ReferenceMatcher> list = references;
            list.add(Companion.instanceFieldLeak$default(AndroidReferenceMatchers.INSTANCE, "android.os.Message", "obj", "A thread waiting on a blocking queue will leak the last dequeued object as a stack local reference. So when a HandlerThread becomes idle, it keeps a local reference to the last message it received. That message then gets recycled and can be used again. As long as all messages are recycled after being used, this won't be a problem, because these references are cleared when being recycled. However, dialogs create template Message instances to be copied when a message needs to be sent. These Message templates holds references to the dialog listeners, which most likely leads to holding a reference onto the activity in some way. Dialogs never recycle their template Message, assuming these Message instances will get GCed when the dialog is GCed. The combination of these two things creates a high potential for memory leaks as soon as you use dialogs. These memory leaks might be temporary, but some handler threads sleep for a long time. To fix this, you could post empty messages to the idle handler threads from time to time. This won't be easy because you cannot access all handler threads, but a library that is widely used should consider doing this for its own handler threads. This leaks has been shown to happen in both Dalvik and ART.", null, 8, null));
            list.add(Companion.instanceFieldLeak$default(AndroidReferenceMatchers.INSTANCE, "android.os.Message", "next", "A thread waiting on a blocking queue will leak the last dequeued object as a stack local reference. So when a HandlerThread becomes idle, it keeps a local reference to the last message it received. That message then gets recycled and can be used again. As long as all messages are recycled after being used, this won't be a problem, because these references are cleared when being recycled. However, dialogs create template Message instances to be copied when a message needs to be sent. These Message templates holds references to the dialog listeners, which most likely leads to holding a reference onto the activity in some way. Dialogs never recycle their template Message, assuming these Message instances will get GCed when the dialog is GCed. The combination of these two things creates a high potential for memory leaks as soon as you use dialogs. These memory leaks might be temporary, but some handler threads sleep for a long time. To fix this, you could post empty messages to the idle handler threads from time to time. This won't be easy because you cannot access all handler threads, but a library that is widely used should consider doing this for its own handler threads. This leaks has been shown to happen in both Dalvik and ART.", null, 8, null));
            list.add(Companion.instanceFieldLeak$default(AndroidReferenceMatchers.INSTANCE, "android.os.Message", "target", "A thread waiting on a blocking queue will leak the last dequeued object as a stack local reference. So when a HandlerThread becomes idle, it keeps a local reference to the last message it received. That message then gets recycled and can be used again. As long as all messages are recycled after being used, this won't be a problem, because these references are cleared when being recycled. However, dialogs create template Message instances to be copied when a message needs to be sent. These Message templates holds references to the dialog listeners, which most likely leads to holding a reference onto the activity in some way. Dialogs never recycle their template Message, assuming these Message instances will get GCed when the dialog is GCed. The combination of these two things creates a high potential for memory leaks as soon as you use dialogs. These memory leaks might be temporary, but some handler threads sleep for a long time. To fix this, you could post empty messages to the idle handler threads from time to time. This won't be easy because you cannot access all handler threads, but a library that is widely used should consider doing this for its own handler threads. This leaks has been shown to happen in both Dalvik and ART.", null, 8, null));
        }
    },
    INPUT_METHOD_MANAGER_IS_TERRIBLE {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            List<ReferenceMatcher> list = references;
            list.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.view.inputmethod.InputMethodManager", "mNextServedView", "When we detach a view that receives keyboard input, the InputMethodManager leaks a reference to it until a new view asks for keyboard input. Tracked here: https://code.google.com/p/android/issues/detail?id=171190 Hack: https://gist.github.com/pyricau/4df64341cc978a7de414", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    int sdkInt = androidBuildMirror.getSdkInt();
                    return 15 <= sdkInt && 27 >= sdkInt;
                }
            }));
            list.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.view.inputmethod.InputMethodManager", "mServedView", "When we detach a view that receives keyboard input, the InputMethodManager leaks a reference to it until a new view asks for keyboard input. Tracked here: https://code.google.com/p/android/issues/detail?id=171190 Hack: https://gist.github.com/pyricau/4df64341cc978a7de414", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    int sdkInt = androidBuildMirror.getSdkInt();
                    return 15 <= sdkInt && 27 >= sdkInt;
                }
            }));
            list.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.view.inputmethod.InputMethodManager", "mServedInputConnection", "When we detach a view that receives keyboard input, the InputMethodManager leaks a reference to it until a new view asks for keyboard input. Tracked here: https://code.google.com/p/android/issues/detail?id=171190 Hack: https://gist.github.com/pyricau/4df64341cc978a7de414", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    int sdkInt = androidBuildMirror.getSdkInt();
                    return 15 <= sdkInt && 27 >= sdkInt;
                }
            }));
            list.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.view.inputmethod.InputMethodManager", "mLastSrvView", "HUAWEI added a mLastSrvView field to InputMethodManager that leaks a reference to the last served view.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    int sdkInt;
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "HUAWEI") && 23 <= (sdkInt = androidBuildMirror.getSdkInt()) && 28 >= sdkInt;
                }
            }));
            list.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.view.inputmethod.InputMethodManager", "mCurRootView", "The singleton InputMethodManager is holding a reference to mCurRootView long after the activity has been destroyed. Observed on ICS MR1: https://github.com/square/leakcanary/issues/1#issuecomment-100579429 Hack: https://gist.github.com/pyricau/4df64341cc978a7de414", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    int sdkInt = androidBuildMirror.getSdkInt();
                    return 15 <= sdkInt && 28 >= sdkInt;
                }
            }));
            list.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.view.inputmethod.InputMethodManager", "mImeInsetsConsumer", "Android Q Beta has a leak where InputMethodManager.mImeInsetsConsumer isn't set to\nnull when the activity is destroyed.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() == 28;
                }
            }));
            list.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.view.inputmethod.InputMethodManager", "mCurrentInputConnection", "In Android Q Beta InputMethodManager keeps its EditableInputConnection after the\nactivity has been destroyed.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() == 28;
                }
            }));
        }
    },
    LAYOUT_TRANSITION {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.animation.LayoutTransition$1", "val$parent", "LayoutTransition leaks parent ViewGroup through ViewTreeObserver.OnPreDrawListener When triggered, this leaks stays until the window is destroyed. Tracked here: https://code.google.com/p/android/issues/detail?id=171830", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    int sdkInt = androidBuildMirror.getSdkInt();
                    return 14 <= sdkInt && 22 >= sdkInt;
                }
            }));
        }
    },
    SPELL_CHECKER_SESSION {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.view.textservice.SpellCheckerSession$1", "this$0", "SpellCheckerSessionListenerImpl.mHandler is leaking destroyed Activity when the SpellCheckerSession is closed before the service is connected. Tracked here: https://code.google.com/p/android/issues/detail?id=172542", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    int sdkInt = androidBuildMirror.getSdkInt();
                    return 16 <= sdkInt && 24 >= sdkInt;
                }
            }));
        }
    },
    SPELL_CHECKER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.widget.SpellChecker$1", "this$0", "SpellChecker holds on to a detached view that points to a destroyed activity. mSpellRunnable is being enqueued, and that callback should be removed when  closeSession() is called. Maybe closeSession() wasn't called, or maybe it was  called after the view was detached.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() == 22;
                }
            }));
        }
    },
    ACTIVITY_CHOOSE_MODEL {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            List<ReferenceMatcher> list = references;
            list.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("androidx.appcompat.internal.widget.ActivityChooserModel", "mActivityChoserModelPolicy", "ActivityChooserModel holds a static reference to the last set ActivityChooserModelPolicy which can be an activity context. Tracked here: https://code.google.com/p/android/issues/detail?id=172659 Hack: https://gist.github.com/andaag/b05ab66ed0f06167d6e0", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    int sdkInt = androidBuildMirror.getSdkInt();
                    return 15 <= sdkInt && 22 >= sdkInt;
                }
            }));
            list.add(Companion.instanceFieldLeak$default(AndroidReferenceMatchers.INSTANCE, "android.widget.ActivityChooserModel", "mActivityChoserModelPolicy", "ActivityChooserModel holds a static reference to the last set ActivityChooserModelPolicy which can be an activity context. Tracked here: https://code.google.com/p/android/issues/detail?id=172659 Hack: https://gist.github.com/andaag/b05ab66ed0f06167d6e0", null, 8, null));
        }
    },
    MEDIA_PROJECTION_CALLBACK {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.media.projection.MediaProjection$MediaProjectionCallback", "this$0", "MediaProjectionCallback is held by another process, and holds on to MediaProjection\nwhich has an activity as its context.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    int sdkInt = androidBuildMirror.getSdkInt();
                    return 22 <= sdkInt && 28 >= sdkInt;
                }
            }));
        }
    },
    SPEECH_RECOGNIZER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.speech.SpeechRecognizer$InternalListener", "this$0", "Prior to Android 5, SpeechRecognizer.InternalListener was a non static inner class and leaked the SpeechRecognizer which leaked an activity context. Fixed in AOSP: https://github.com/android/platform_frameworks_base/commit /b37866db469e81aca534ff6186bdafd44352329b", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() < 21;
                }
            }));
        }
    },
    ACCOUNT_MANAGER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.accounts.AccountManager$AmsTask$Response", "this$1", "AccountManager$AmsTask$Response is a stub and is held in memory by native code, probably because the reference to the response in the other process hasn't been cleared. AccountManager$AmsTask is holding on to the activity reference to use for launching a new sub- Activity. Tracked here: https://code.google.com/p/android/issues/detail?id=173689 Fix: Pass a null activity reference to the AccountManager methods and then deal with the returned future to to get the result and correctly start an activity when it's available.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() <= 27;
                }
            }));
        }
    },
    MEDIA_SCANNER_CONNECTION {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.media.MediaScannerConnection", "mContext", "The static method MediaScannerConnection.scanFile() takes an activity context but the service might not disconnect after the activity has been destroyed. Tracked here: https://code.google.com/p/android/issues/detail?id=173788 Fix: Create an instance of MediaScannerConnection yourself and pass in the application context. Call connect() and disconnect() manually.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() <= 22;
                }
            }));
        }
    },
    USER_MANAGER__SINSTANCE {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.os.UserManager", "mContext", "UserManager has a static sInstance field that creates an instance and caches it the first time UserManager.get() is called. This instance is created with the outer context (which is an activity base context). Tracked here: https://code.google.com/p/android/issues/detail?id=173789 Introduced by: https://github.com/android/platform_frameworks_base/commit/27db46850b708070452c0ce49daf5f79503fbde6 Fix: trigger a call to UserManager.get() in Application.onCreate(), so that the UserManager instance gets cached with a reference to the application context.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    int sdkInt = androidBuildMirror.getSdkInt();
                    return 18 <= sdkInt && 25 >= sdkInt;
                }
            }));
        }
    },
    APP_WIDGET_HOST_CALLBACKS {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.appwidget.AppWidgetHost$Callbacks", "this$0", "android.appwidget.AppWidgetHost$Callbacks is a stub and is held in memory native code. The reference to the `mContext` was not being cleared, which caused the Callbacks instance to retain this reference Fixed in AOSP: https://github.com/android/platform_frameworks_base/commit/7a96f3c917e0001ee739b65da37b2fadec7d7765", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() < 22;
                }
            }));
        }
    },
    AUDIO_MANAGER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.media.AudioManager$1", "this$0", "Prior to Android M, VideoView required audio focus from AudioManager and never abandoned it, which leaks the Activity context through the AudioManager. The root of the problem is that AudioManager uses whichever context it receives, which in the case of the VideoView example is an Activity, even though it only needs the application's context. The issue is fixed in Android M, and the AudioManager now uses the application's context. Tracked here: https://code.google.com/p/android/issues/detail?id=152173 Fix: https://gist.github.com/jankovd/891d96f476f7a9ce24e2", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() <= 22;
                }
            }));
        }
    },
    EDITTEXT_BLINK_MESSAGEQUEUE {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.widget.Editor$Blink", "this$0", "The EditText Blink of the Cursor is implemented using a callback and Messages, which trigger the display of the Cursor. If an AlertDialog or DialogFragment that contains a blinking cursor is detached, a message is posted with a delay after the dialog has been closed and as a result leaks the Activity. This can be fixed manually by calling TextView.setCursorVisible(false) in the dismiss() method of the dialog. Tracked here: https://code.google.com/p/android/issues/detail?id=188551 Fixed in AOSP: https://android.googlesource.com/platform/frameworks/base/+/5b734f2430e9f26c769d6af8ea5645e390fcf5af%5E%21/", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() <= 23;
                }
            }));
        }
    },
    CONNECTIVITY_MANAGER__SINSTANCE {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.net.ConnectivityManager", "sInstance", "ConnectivityManager has a sInstance field that is set when the first ConnectivityManager instance is created. ConnectivityManager has a mContext field. When calling activity.getSystemService(Context.CONNECTIVITY_SERVICE) , the first ConnectivityManager instance is created with the activity context and stored in sInstance. That activity context then leaks forever. Until this is fixed, app developers can prevent this leak by making sure the ConnectivityManager is first created with an App Context. E.g. in some static init do: context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) Tracked here: https://code.google.com/p/android/issues/detail?id=198852 Introduced here: https://github.com/android/platform_frameworks_base/commit/e0bef71662d81caaaa0d7214fb0bef5d39996a69", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() <= 23;
                }
            }));
        }
    },
    ACCESSIBILITY_NODE_INFO__MORIGINALTEXT {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.view.accessibility.AccessibilityNodeInfo", "mOriginalText", "AccessibilityNodeInfo has a static sPool of AccessibilityNodeInfo. When AccessibilityNodeInfo instances are released back in the pool, AccessibilityNodeInfo.clear() does not clear the mOriginalText field, which causes spans to leak which in turns causes TextView.ChangeWatcher to leak and the whole view hierarchy. Introduced here: https://android.googlesource.com/platform/frameworks/base/+/193520e3dff5248ddcf8435203bf99d2ba667219%5E%21/core/java/android/view/accessibility/AccessibilityNodeInfo.java", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    int sdkInt = androidBuildMirror.getSdkInt();
                    return 26 <= sdkInt && 27 >= sdkInt;
                }
            }));
        }
    },
    ASSIST_STRUCTURE {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.app.assist.AssistStructure$ViewNodeText", "mText", "AssistStructure (google assistant / autofill) holds on to text spannables on the screen. TextView.ChangeWatcher and android.widget.Editor end up in spans and typically hold on to the view hierarchy", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    int sdkInt = androidBuildMirror.getSdkInt();
                    return 24 <= sdkInt && 28 >= sdkInt;
                }
            }));
        }
    },
    ACCESSIBILITY_ITERATORS {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.widget.AccessibilityIterators$LineTextSegmentIterator", "mLayout", "AccessibilityIterators holds on to text layouts which can hold on to spans TextView.ChangeWatcher and android.widget.Editor end up in spans and typically hold on to the view hierarchy", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() == 27;
                }
            }));
        }
    },
    BIOMETRIC_PROMPT {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.hardware.biometrics.BiometricPrompt", "mFingerprintManager", "BiometricPrompt holds on to a FingerprintManager which holds on to a destroyed activity.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() == 28;
                }
            }));
        }
    },
    MAGNIFIER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.widget.Magnifier$InternalPopupWindow", "mCallback", "android.widget.Magnifier.InternalPopupWindow registers a frame callback on android.view.ThreadedRenderer.SimpleRenderer which holds it as a native reference. android.widget.Editor$InsertionHandleView registers an OnOperationCompleteCallback on Magnifier.InternalPopupWindow. These references are held after the activity has been destroyed.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() == 28;
                }
            }));
        }
    },
    BACKDROP_FRAME_RENDERER__MDECORVIEW {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("com.android.internal.policy.BackdropFrameRenderer", "mDecorView", "When BackdropFrameRenderer.releaseRenderer() is called, there's an unknown case where mRenderer becomes null but mChoreographer doesn't and the thread doesn't stop and ends up leaking mDecorView which itself holds on to a destroyed activity", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    int sdkInt = androidBuildMirror.getSdkInt();
                    return 24 <= sdkInt && 26 >= sdkInt;
                }
            }));
        }
    },
    VIEWLOCATIONHOLDER_ROOT {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.view.ViewGroup$ViewLocationHolder", "mRoot", "In Android P, ViewLocationHolder has an mRoot field that is not cleared in its clear() method. Introduced in https://github.com/aosp-mirror/platform_frameworks_base/commit/86b326012813f09d8f1de7d6d26c986a909d Bug report: https://issuetracker.google.com/issues/112792715", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() == 28;
                }
            }));
        }
    },
    ACCESSIBILITY_NODE_ID_MANAGER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.view.accessibility.AccessibilityNodeIdManager", "mIdsToViews", "Android Q Beta added AccessibilityNodeIdManager which stores all views from their\nonAttachedToWindow() call, until detached. Unfortunately it's possible to trigger\nthe view framework to call detach before attach (by having a view removing itself\nfrom its parent in onAttach, which then causes AccessibilityNodeIdManager to keep\nchildren view forever. Future releases of Q will hold weak references.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    int sdkInt = androidBuildMirror.getSdkInt();
                    return 28 <= sdkInt && 29 >= sdkInt;
                }
            }));
        }
    },
    TEXT_TO_SPEECH {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            List<ReferenceMatcher> list = references;
            list.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.speech.tts.TextToSpeech", "mContext", "TextToSpeech.shutdown() does not release its references to context objects. Furthermore, TextToSpeech instances cannot be garbage collected due to other process keeping the references, resulting the context objects leaked. Developers might be able to mitigate the issue by passing application context to TextToSpeech constructor. Tracked at: https://github.com/square/leakcanary/issues/1210 and https://issuetracker.google.com/issues/129250419", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() == 24;
                }
            }));
            list.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.speech.tts.TtsEngines", "mContext", "TextToSpeech.shutdown() does not release its references to context objects. Furthermore, TextToSpeech instances cannot be garbage collected due to other process keeping the references, resulting the context objects leaked. Developers might be able to mitigate the issue by passing application context to TextToSpeech constructor. Tracked at: https://github.com/square/leakcanary/issues/1210 and https://issuetracker.google.com/issues/129250419", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() == 24;
                }
            }));
        }
    },
    WINDOW_MANAGER_GLOBAL {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.view.WindowManagerGlobal", "mRoots", "ViewRootImpl references a destroyed activity yet it's not detached (still has a view)\n and WindowManagerGlobal still references it.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return androidBuildMirror.getSdkInt() == 27;
                }
            }));
        }
    },
    CONTROLLED_INPUT_CONNECTION_WRAPPER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(Companion.nativeGlobalVariableLeak$default(AndroidReferenceMatchers.INSTANCE, "android.view.inputmethod.InputMethodManager$ControlledInputConnectionWrapper", "ControlledInputConnectionWrapper is held by a global variable in native code. ", null, 4, null));
        }
    },
    TOAST_TN {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(Companion.nativeGlobalVariableLeak$default(AndroidReferenceMatchers.INSTANCE, "android.widget.Toast$TN", "Toast.TN is held by a global variable in native code due to an IPC call to show the toast.", null, 4, null));
        }
    },
    SPEN_GESTURE_MANAGER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.staticFieldLeak("com.samsung.android.smartclip.SpenGestureManager", "mContext", "SpenGestureManager has a static mContext field that leaks a reference to the activity. Yes, a STATIC mContext field.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && androidBuildMirror.getSdkInt() == 19;
                }
            }));
        }
    },
    CLIPBOARD_UI_MANAGER__SINSTANCE {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.sec.clipboard.ClipboardUIManager", "mContext", "ClipboardUIManager is a static singleton that leaks an activity context. Fix: trigger a call to ClipboardUIManager.getInstance() in Application.onCreate() , so that the ClipboardUIManager instance gets cached with a reference to the application context. Example: https://gist.github.com/cypressious/91c4fb1455470d803a602838dfcd5774", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    int sdkInt;
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && 19 <= (sdkInt = androidBuildMirror.getSdkInt()) && 21 >= sdkInt;
                }
            }));
        }
    },
    SEM_CLIPBOARD_MANAGER__MCONTEXT {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            List<ReferenceMatcher> list = references;
            list.add(AndroidReferenceMatchers.INSTANCE.nativeGlobalVariableLeak("com.samsung.android.content.clipboard.SemClipboardManager$1", "SemClipboardManager inner classes are held by native references due to IPC calls ", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    int sdkInt;
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && 19 <= (sdkInt = androidBuildMirror.getSdkInt()) && 28 >= sdkInt;
                }
            }));
            list.add(AndroidReferenceMatchers.INSTANCE.nativeGlobalVariableLeak("com.samsung.android.content.clipboard.SemClipboardManager$3", "SemClipboardManager inner classes are held by native references due to IPC calls ", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    int sdkInt;
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && 19 <= (sdkInt = androidBuildMirror.getSdkInt()) && 28 >= sdkInt;
                }
            }));
        }
    },
    CLIPBOARD_EX_MANAGER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            List<ReferenceMatcher> list = references;
            list.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.sec.clipboard.ClipboardExManager", "mContext", "android.sec.clipboard.ClipboardExManager$IClipboardDataPasteEventImpl$1 is a native callback that holds IClipboardDataPasteEventImpl which holds ClipboardExManager which has a destroyed activity as mContext", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && androidBuildMirror.getSdkInt() == 23;
                }
            }));
            list.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.sec.clipboard.ClipboardExManager", "mPersonaManager", "android.sec.clipboard.ClipboardExManager$IClipboardDataPasteEventImpl$1 is a native callback that holds IClipboardDataPasteEventImpl which holds ClipboardExManager which holds PersonaManager which has a destroyed activity as mContext", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && androidBuildMirror.getSdkInt() == 23;
                }
            }));
            list.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.widget.TextView$IClipboardDataPasteEventImpl", "this$0", "TextView$IClipboardDataPasteEventImpl$1 is held by a native ref, and IClipboardDataPasteEventImpl ends up leaking a detached textview", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && androidBuildMirror.getSdkInt() == 22;
                }
            }));
        }
    },
    SEM_EMERGENCY_MANAGER__MCONTEXT {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("com.samsung.android.emergencymode.SemEmergencyManager", "mContext", "SemEmergencyManager is a static singleton that leaks a DecorContext. Fix: https://gist.github.com/jankovd/a210460b814c04d500eb12025902d60d", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    int sdkInt;
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && 19 <= (sdkInt = androidBuildMirror.getSdkInt()) && 24 >= sdkInt;
                }
            }));
        }
    },
    SEM_PERSONA_MANAGER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(Companion.instanceFieldLeak$default(AndroidReferenceMatchers.INSTANCE, "com.samsung.android.knox.SemPersonaManager", "mContext", null, new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && androidBuildMirror.getSdkInt() == 24;
                }
            }, 4, null));
        }
    },
    SEM_APP_ICON_SOLUTION {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(Companion.instanceFieldLeak$default(AndroidReferenceMatchers.INSTANCE, "android.app.SemAppIconSolution", "mContext", null, new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && androidBuildMirror.getSdkInt() == 28;
                }
            }, 4, null));
        }
    },
    AW_RESOURCE__SRESOURCES {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(Companion.staticFieldLeak$default(AndroidReferenceMatchers.INSTANCE, "com.android.org.chromium.android_webview.AwResource", "sResources", null, new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && androidBuildMirror.getSdkInt() == 19;
                }
            }, 4, null));
        }
    },
    TEXT_VIEW__MLAST_HOVERED_VIEW {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.staticFieldLeak("android.widget.TextView", "mLastHoveredView", "mLastHoveredView is a static field in TextView that leaks the last hovered view.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    int sdkInt;
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && 19 <= (sdkInt = androidBuildMirror.getSdkInt()) && 28 >= sdkInt;
                }
            }));
        }
    },
    PERSONA_MANAGER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.os.PersonaManager", "mContext", "android.app.LoadedApk.mResources has a reference to android.content.res.Resources.mPersonaManager which has a reference to android.os.PersonaManager.mContext which is an activity.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && androidBuildMirror.getSdkInt() == 19;
                }
            }));
        }
    },
    RESOURCES__MCONTEXT {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.content.res.Resources", "mContext", "In AOSP the Resources class does not have a context. Here we have ZygoteInit.mResources (static field) holding on to a Resources instance that has a context that is the activity. Observed here: https://github.com/square/leakcanary/issues/1#issue-74450184", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && androidBuildMirror.getSdkInt() == 19;
                }
            }));
        }
    },
    VIEW_CONFIGURATION__MCONTEXT {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.view.ViewConfiguration", "mContext", "In AOSP the ViewConfiguration class does not have a context. Here we have ViewConfiguration.sConfigurations (static field) holding on to a ViewConfiguration instance that has a context that is the activity. Observed here: https://github.com/square/leakcanary/issues/1#issuecomment-100324683", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && androidBuildMirror.getSdkInt() == 19;
                }
            }));
        }
    },
    AUDIO_MANAGER__MCONTEXT_STATIC {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.staticFieldLeak("android.media.AudioManager", "mContext_static", "Samsung added a static mContext_static field to AudioManager, holds a reference to the activity. Observed here: https://github.com/square/leakcanary/issues/32", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && androidBuildMirror.getSdkInt() == 19;
                }
            }));
        }
    },
    ACTIVITY_MANAGER_MCONTEXT {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.staticFieldLeak("android.app.ActivityManager", "mContext", "Samsung added a static mContext field to ActivityManager, holds a reference to the activity. Observed here: https://github.com/square/leakcanary/issues/177 Fix in comment: https://github.com/square/leakcanary/issues/177#issuecomment-222724283", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    int sdkInt;
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && 22 <= (sdkInt = androidBuildMirror.getSdkInt()) && 23 >= sdkInt;
                }
            }));
        }
    },
    STATIC_MTARGET_VIEW {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.staticFieldLeak("android.widget.TextView", "mTargetView", "Samsung added a static mTargetView field to TextView which holds on to detached views.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "samsung") && androidBuildMirror.getSdkInt() == 27;
                }
            }));
        }
    },
    GESTURE_BOOST_MANAGER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.staticFieldLeak("android.gestureboost.GestureBoostManager", "mContext", "GestureBoostManager is a static singleton that leaks an activity context. Fix: https://github.com/square/leakcanary/issues/696#issuecomment-296420756", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    int sdkInt;
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "HUAWEI") && 24 <= (sdkInt = androidBuildMirror.getSdkInt()) && 25 >= sdkInt;
                }
            }));
        }
    },
    BUBBLE_POPUP_HELPER__SHELPER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.staticFieldLeak("android.widget.BubblePopupHelper", "sHelper", "A static helper for EditText bubble popups leaks a reference to the latest focused view.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    int sdkInt;
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "LGE") && 19 <= (sdkInt = androidBuildMirror.getSdkInt()) && 22 >= sdkInt;
                }
            }));
        }
    },
    LGCONTEXT__MCONTEXT {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("com.lge.systemservice.core.LGContext", "mContext", "LGContext is a static singleton that leaks an activity context.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "LGE") && androidBuildMirror.getSdkInt() == 21;
                }
            }));
        }
    },
    SMART_COVER_MANAGER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("com.lge.systemservice.core.SmartCoverManager", "mContext", "SmartCoverManager$CallbackRegister is a callback held by a native ref, and SmartCoverManager ends up leaking an activity context.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "LGE") && androidBuildMirror.getSdkInt() == 27;
                }
            }));
        }
    },
    MAPPER_CLIENT {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("com.nvidia.ControllerMapper.MapperClient$ServiceClient", "this$0", "Not sure exactly what ControllerMapper is about, but there is an anonymous Handler in ControllerMapper.MapperClient.ServiceClient, which leaks ControllerMapper.MapperClient which leaks the activity context.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "NVIDIA") && androidBuildMirror.getSdkInt() == 19;
                }
            }));
        }
    },
    SYSTEM_SENSOR_MANAGER__MAPPCONTEXTIMPL {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.staticFieldLeak("android.hardware.SystemSensorManager", "mAppContextImpl", "SystemSensorManager stores a reference to context in a static field in its constructor. Fix: use application context to get SensorManager", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return (Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "LENOVO") && androidBuildMirror.getSdkInt() == 19) || (Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "vivo") && androidBuildMirror.getSdkInt() == 22);
                }
            }));
        }
    },
    INSTRUMENTATION_RECOMMEND_ACTIVITY {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.staticFieldLeak("android.app.Instrumentation", "mRecommendActivity", "Instrumentation would leak com.android.internal.app.RecommendActivity (in framework.jar) in Meizu FlymeOS 4.5 and above, which is based on Android 5.0 and  above", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    int sdkInt;
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "Meizu") && 21 <= (sdkInt = androidBuildMirror.getSdkInt()) && 22 >= sdkInt;
                }
            }));
        }
    },
    DEVICE_POLICY_MANAGER__SETTINGS_OBSERVER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.instanceFieldLeak("android.app.admin.DevicePolicyManager$SettingsObserver", "this$0", "DevicePolicyManager keeps a reference to the context it has been created with instead of extracting the application context. In this Motorola build, DevicePolicyManager has an inner SettingsObserver class that is a content observer, which is held into memory by a binder transport object.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    int sdkInt;
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "motorola") && 19 <= (sdkInt = androidBuildMirror.getSdkInt()) && 22 >= sdkInt;
                }
            }));
        }
    },
    EXTENDED_STATUS_BAR_MANAGER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.staticFieldLeak("android.app.ExtendedStatusBarManager", "sInstance", "ExtendedStatusBarManager is held in a static sInstance field and has a mContext\nfield which references a decor context which references a destroyed activity.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "SHARP") && androidBuildMirror.getSdkInt() == 28;
                }
            }));
        }
    },
    OEM_SCENE_CALL_BLOCKER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.staticFieldLeak("com.oneplus.util.OemSceneCallBlocker", "sContext", "OemSceneCallBlocker has a sContext static field which holds on to an activity instance.", new Function1<AndroidBuildMirror, Boolean>() {
                @Override
                public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
                    return Boolean.valueOf(invoke2(androidBuildMirror));
                }

                public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
                    Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
                    return Intrinsics.areEqual(androidBuildMirror.getManufacturer(), "OnePlus") && androidBuildMirror.getSdkInt() == 28;
                }
            }));
        }
    },
    REFERENCES {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            List<ReferenceMatcher> list = references;
            Companion companion = AndroidReferenceMatchers.INSTANCE;
            String name = WeakReference.class.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "WeakReference::class.java.name");
            list.add(companion.ignoredInstanceField(name, "referent"));
            list.add(AndroidReferenceMatchers.INSTANCE.ignoredInstanceField("leakcanary.KeyedWeakReference", "referent"));
            Companion companion2 = AndroidReferenceMatchers.INSTANCE;
            String name2 = SoftReference.class.getName();
            Intrinsics.checkExpressionValueIsNotNull(name2, "SoftReference::class.java.name");
            list.add(companion2.ignoredInstanceField(name2, "referent"));
            Companion companion3 = AndroidReferenceMatchers.INSTANCE;
            String name3 = PhantomReference.class.getName();
            Intrinsics.checkExpressionValueIsNotNull(name3, "PhantomReference::class.java.name");
            list.add(companion3.ignoredInstanceField(name3, "referent"));
            list.add(AndroidReferenceMatchers.INSTANCE.ignoredInstanceField("java.lang.ref.Finalizer", "prev"));
            list.add(AndroidReferenceMatchers.INSTANCE.ignoredInstanceField("java.lang.ref.Finalizer", "element"));
            list.add(AndroidReferenceMatchers.INSTANCE.ignoredInstanceField("java.lang.ref.Finalizer", "next"));
            list.add(AndroidReferenceMatchers.INSTANCE.ignoredInstanceField("java.lang.ref.FinalizerReference", "prev"));
            list.add(AndroidReferenceMatchers.INSTANCE.ignoredInstanceField("java.lang.ref.FinalizerReference", "element"));
            list.add(AndroidReferenceMatchers.INSTANCE.ignoredInstanceField("java.lang.ref.FinalizerReference", "next"));
            list.add(AndroidReferenceMatchers.INSTANCE.ignoredInstanceField("sun.misc.Cleaner", "prev"));
            list.add(AndroidReferenceMatchers.INSTANCE.ignoredInstanceField("sun.misc.Cleaner", "next"));
        }
    },
    FINALIZER_WATCHDOG_DAEMON {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.ignoredJavaLocal("FinalizerWatchdogDaemon"));
        }
    },
    MAIN {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.ignoredJavaLocal("main"));
        }
    },
    LEAK_CANARY_THREAD {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.ignoredJavaLocal("LeakCanary-Heap-Dump"));
        }
    },
    LEAK_CANARY_HEAP_DUMPER {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.ignoredInstanceField("leakcanary.internal.AndroidHeapDumper", "resumedActivity"));
        }
    },
    LEAK_CANARY_INTERNAL {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.ignoredInstanceField("leakcanary.internal.InternalLeakCanary", "application"));
        }
    },
    EVENT_RECEIVER__MMESSAGE_QUEUE {
        @Override
        public void add$shark(List<ReferenceMatcher> references) {
            Intrinsics.checkParameterIsNotNull(references, "references");
            references.add(AndroidReferenceMatchers.INSTANCE.ignoredInstanceField("android.view.Choreographer$FrameDisplayEventReceiver", "mMessageQueue"));
        }
    };

    public static final String HUAWEI = "HUAWEI";
    private static final String LEAK_CANARY_THREAD_NAME = "LeakCanary-Heap-Dump";
    public static final String LENOVO = "LENOVO";
    public static final String LG = "LGE";
    public static final String MEIZU = "Meizu";
    public static final String MOTOROLA = "motorola";
    public static final String NVIDIA = "NVIDIA";
    public static final String ONE_PLUS = "OnePlus";
    public static final String SAMSUNG = "samsung";
    public static final String SHARP = "SHARP";
    public static final String VIVO = "vivo";

    public static final Companion INSTANCE = new Companion(null);
    private static final Function1<AndroidBuildMirror, Boolean> ALWAYS = new Function1<AndroidBuildMirror, Boolean>() {
        @Override
        public Boolean invoke(AndroidBuildMirror androidBuildMirror) {
            return Boolean.valueOf(invoke2(androidBuildMirror));
        }

        public final boolean invoke2(AndroidBuildMirror androidBuildMirror) {
            Intrinsics.checkParameterIsNotNull(androidBuildMirror, "$receiver");
            return true;
        }
    };

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dJ\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\tJ\u000e\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\tJ;\u0010%\u001a\u00020&2\u0006\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t2\b\b\u0002\u0010'\u001a\u00020\t2\u0019\b\u0002\u0010(\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007J1\u0010)\u001a\u00020&2\u0006\u0010*\u001a\u00020+2\u0006\u0010'\u001a\u00020\t2\u0017\u0010(\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0002J3\u0010,\u001a\u00020&2\u0006\u0010!\u001a\u00020\t2\b\b\u0002\u0010'\u001a\u00020\t2\u0019\b\u0002\u0010(\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007J;\u0010-\u001a\u00020&2\u0006\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\t2\b\b\u0002\u0010'\u001a\u00020\t2\u0019\b\u0002\u0010(\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007R\u001f\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018¨\u0006."}, d2 = {"Lkshark/AndroidReferenceMatchers$Companion;", "", "()V", "ALWAYS", "Lkotlin/Function1;", "Lkshark/AndroidBuildMirror;", "", "Lkotlin/ExtensionFunctionType;", "HUAWEI", "", "LEAK_CANARY_THREAD_NAME", "LENOVO", "LG", "MEIZU", "MOTOROLA", "NVIDIA", "ONE_PLUS", "SAMSUNG", "SHARP", "VIVO", "appDefaults", "", "Lkshark/ReferenceMatcher;", "getAppDefaults", "()Ljava/util/List;", "ignoredReferencesOnly", "getIgnoredReferencesOnly", "buildKnownReferences", "referenceMatchers", "", "Lkshark/AndroidReferenceMatchers;", "ignoredInstanceField", "Lkshark/IgnoredReferenceMatcher;", "className", "fieldName", "ignoredJavaLocal", "threadName", "instanceFieldLeak", "Lkshark/LibraryLeakReferenceMatcher;", "description", "patternApplies", "libraryLeak", "referencePattern", "Lkshark/ReferencePattern;", "nativeGlobalVariableLeak", "staticFieldLeak", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static LibraryLeakReferenceMatcher instanceFieldLeak$default(Companion companion, String str, String str2, String str3, Function1 function1, int i, Object obj) {
            if ((i & 4) != 0) {
                str3 = "";
            }
            if ((i & 8) != 0) {
                function1 = AndroidReferenceMatchers.ALWAYS;
            }
            return companion.instanceFieldLeak(str, str2, str3, function1);
        }

        private final LibraryLeakReferenceMatcher libraryLeak(ReferencePattern referencePattern, String description, final Function1<? super AndroidBuildMirror, Boolean> patternApplies) {
            return new LibraryLeakReferenceMatcher(referencePattern, description, new Function1<HeapGraph, Boolean>() {
                {
                    super(1);
                }

                @Override
                public Boolean invoke(HeapGraph heapGraph) {
                    return Boolean.valueOf(invoke2(heapGraph));
                }

                public final boolean invoke2(HeapGraph heapGraph) {
                    Intrinsics.checkParameterIsNotNull(heapGraph, "graph");
                    return ((Boolean) patternApplies.invoke(AndroidBuildMirror.INSTANCE.fromHeapGraph(heapGraph))).booleanValue();
                }
            });
        }

        public static LibraryLeakReferenceMatcher nativeGlobalVariableLeak$default(Companion companion, String str, String str2, Function1 function1, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = "";
            }
            if ((i & 4) != 0) {
                function1 = AndroidReferenceMatchers.ALWAYS;
            }
            return companion.nativeGlobalVariableLeak(str, str2, function1);
        }

        public static LibraryLeakReferenceMatcher staticFieldLeak$default(Companion companion, String str, String str2, String str3, Function1 function1, int i, Object obj) {
            if ((i & 4) != 0) {
                str3 = "";
            }
            if ((i & 8) != 0) {
                function1 = AndroidReferenceMatchers.ALWAYS;
            }
            return companion.staticFieldLeak(str, str2, str3, function1);
        }

        public final List<ReferenceMatcher> buildKnownReferences(Set<? extends AndroidReferenceMatchers> referenceMatchers) {
            Intrinsics.checkParameterIsNotNull(referenceMatchers, "referenceMatchers");
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = referenceMatchers.iterator();
            while (it.hasNext()) {
                ((AndroidReferenceMatchers) it.next()).add$shark(arrayList);
            }
            return arrayList;
        }

        public final List<ReferenceMatcher> getAppDefaults() {
            Companion companion = AndroidReferenceMatchers.INSTANCE;
            EnumSet enumSetAllOf = EnumSet.allOf(AndroidReferenceMatchers.class);
            Intrinsics.checkExpressionValueIsNotNull(enumSetAllOf, "EnumSet.allOf(AndroidRef…enceMatchers::class.java)");
            return companion.buildKnownReferences(enumSetAllOf);
        }

        public final List<ReferenceMatcher> getIgnoredReferencesOnly() {
            Companion companion = AndroidReferenceMatchers.INSTANCE;
            EnumSet enumSetOf = EnumSet.of(AndroidReferenceMatchers.REFERENCES, AndroidReferenceMatchers.FINALIZER_WATCHDOG_DAEMON, AndroidReferenceMatchers.MAIN, AndroidReferenceMatchers.LEAK_CANARY_THREAD, AndroidReferenceMatchers.EVENT_RECEIVER__MMESSAGE_QUEUE);
            Intrinsics.checkExpressionValueIsNotNull(enumSetOf, "EnumSet.of(\n            …MESSAGE_QUEUE\n          )");
            return companion.buildKnownReferences(enumSetOf);
        }

        public final IgnoredReferenceMatcher ignoredInstanceField(String className, String fieldName) {
            Intrinsics.checkParameterIsNotNull(className, "className");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            return new IgnoredReferenceMatcher(new ReferencePattern.InstanceFieldPattern(className, fieldName));
        }

        public final IgnoredReferenceMatcher ignoredJavaLocal(String threadName) {
            Intrinsics.checkParameterIsNotNull(threadName, "threadName");
            return new IgnoredReferenceMatcher(new ReferencePattern.JavaLocalPattern(threadName));
        }

        public final LibraryLeakReferenceMatcher instanceFieldLeak(String className, String fieldName, String description, Function1<? super AndroidBuildMirror, Boolean> patternApplies) {
            Intrinsics.checkParameterIsNotNull(className, "className");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Intrinsics.checkParameterIsNotNull(description, "description");
            Intrinsics.checkParameterIsNotNull(patternApplies, "patternApplies");
            return libraryLeak(new ReferencePattern.InstanceFieldPattern(className, fieldName), description, patternApplies);
        }

        public final LibraryLeakReferenceMatcher nativeGlobalVariableLeak(String className, String description, Function1<? super AndroidBuildMirror, Boolean> patternApplies) {
            Intrinsics.checkParameterIsNotNull(className, "className");
            Intrinsics.checkParameterIsNotNull(description, "description");
            Intrinsics.checkParameterIsNotNull(patternApplies, "patternApplies");
            return libraryLeak(new ReferencePattern.NativeGlobalVariablePattern(className), description, patternApplies);
        }

        public final LibraryLeakReferenceMatcher staticFieldLeak(String className, String fieldName, String description, Function1<? super AndroidBuildMirror, Boolean> patternApplies) {
            Intrinsics.checkParameterIsNotNull(className, "className");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            Intrinsics.checkParameterIsNotNull(description, "description");
            Intrinsics.checkParameterIsNotNull(patternApplies, "patternApplies");
            return libraryLeak(new ReferencePattern.StaticFieldPattern(className, fieldName), description, patternApplies);
        }
    }

    AndroidReferenceMatchers(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract void add$shark(List<ReferenceMatcher> references);
}
