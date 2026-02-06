package kshark;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kshark.FilteringLeakingObjectFinder;
import kshark.HeapObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0016\b\u0086\u0001\u0018\u0000 \u001f2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u001fB\u0007\b\u0002¢\u0006\u0002\u0010\u0003R1\u0010\u0004\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001e¨\u0006 "}, d2 = {"Lkshark/AndroidObjectInspectors;", "", "Lkshark/ObjectInspector;", "(Ljava/lang/String;I)V", "leakingObjectFilter", "Lkotlin/Function1;", "Lkshark/HeapObject;", "Lkotlin/ParameterName;", "name", "heapObject", "", "getLeakingObjectFilter$shark", "()Lkotlin/jvm/functions/Function1;", "VIEW", "EDITOR", "ACTIVITY", "CONTEXT_WRAPPER", "DIALOG", "APPLICATION", "INPUT_METHOD_MANAGER", "FRAGMENT", "SUPPORT_FRAGMENT", "ANDROIDX_FRAGMENT", "MESSAGE_QUEUE", "MORTAR_PRESENTER", "MORTAR_SCOPE", "COORDINATOR", "MAIN_THREAD", "VIEW_ROOT_IMPL", "WINDOW", "TOAST", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public enum AndroidObjectInspectors implements ObjectInspector {
    VIEW {
        private final Function1<HeapObject, Boolean> leakingObjectFilter = new Function1<HeapObject, Boolean>() {
            @Override
            public Boolean invoke(HeapObject heapObject) {
                return Boolean.valueOf(invoke2(heapObject));
            }

            public final boolean invoke2(HeapObject heapObject) {
                HeapValue value;
                Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                if (heapObject instanceof HeapObject.HeapInstance) {
                    HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObject;
                    if (heapInstance.instanceOf("android.view.View")) {
                        HeapField heapField = heapInstance.get("android.view.View", "mContext");
                        if (heapField == null) {
                            Intrinsics.throwNpe();
                        }
                        HeapObject asObject = heapField.getValue().getAsObject();
                        if (asObject == null) {
                            Intrinsics.throwNpe();
                        }
                        HeapObject.HeapInstance asInstance = asObject.getAsInstance();
                        if (asInstance == null) {
                            Intrinsics.throwNpe();
                        }
                        HeapObject.HeapInstance heapInstanceUnwrapActivityContext = AndroidObjectInspectorsKt.unwrapActivityContext(asInstance);
                        if (heapInstanceUnwrapActivityContext != null) {
                            HeapField heapField2 = heapInstanceUnwrapActivityContext.get("android.app.Activity", "mDestroyed");
                            if (Intrinsics.areEqual((Object) ((heapField2 == null || (value = heapField2.getValue()) == null) ? null : value.getAsBoolean()), (Object) true)) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
        };

        @Override
        public Function1<HeapObject, Boolean> getLeakingObjectFilter$shark() {
            return this.leakingObjectFilter;
        }

        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf("android.view.View", new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    String strValueOf;
                    String str;
                    HeapValue value;
                    Boolean asBoolean;
                    HeapValue value2;
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "instance");
                    HeapField heapField = heapInstance.get("android.view.View", "mParent");
                    if (heapField == null) {
                        Intrinsics.throwNpe();
                    }
                    HeapValue value3 = heapField.getValue();
                    boolean zIsNonNullReference = value3.isNonNullReference();
                    HeapField heapField2 = heapInstance.get("android.view.View", "mWindowAttachCount");
                    HeapValue value4 = heapField2 != null ? heapField2.getValue() : null;
                    if (value4 == null) {
                        Intrinsics.throwNpe();
                    }
                    Integer asInt = value4.getAsInt();
                    if (asInt == null) {
                        Intrinsics.throwNpe();
                    }
                    int iIntValue = asInt.intValue();
                    HeapField heapField3 = heapInstance.get("android.view.View", "mAttachInfo");
                    if (heapField3 == null) {
                        Intrinsics.throwNpe();
                    }
                    boolean zIsNullReference = heapField3.getValue().isNullReference();
                    HeapField heapField4 = heapInstance.get("android.view.View", "mContext");
                    if (heapField4 == null) {
                        Intrinsics.throwNpe();
                    }
                    HeapObject asObject = heapField4.getValue().getAsObject();
                    if (asObject == null) {
                        Intrinsics.throwNpe();
                    }
                    HeapObject.HeapInstance asInstance = asObject.getAsInstance();
                    if (asInstance == null) {
                        Intrinsics.throwNpe();
                    }
                    HeapObject.HeapInstance heapInstanceUnwrapActivityContext = AndroidObjectInspectorsKt.unwrapActivityContext(asInstance);
                    LinkedHashSet<String> labels = objectReporter.getLabels();
                    if (heapInstanceUnwrapActivityContext == null) {
                        str = "mContext instance of " + asInstance.getInstanceClassName() + ", not wrapping activity";
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("with mDestroyed = ");
                        HeapField heapField5 = heapInstanceUnwrapActivityContext.get("android.app.Activity", "mDestroyed");
                        if (heapField5 == null || (value = heapField5.getValue()) == null || (asBoolean = value.getAsBoolean()) == null || (strValueOf = String.valueOf(asBoolean.booleanValue())) == null) {
                            strValueOf = "UNKNOWN";
                        }
                        sb.append((Object) strValueOf);
                        String string = sb.toString();
                        if (Intrinsics.areEqual(heapInstanceUnwrapActivityContext, asInstance)) {
                            str = "mContext instance of " + heapInstanceUnwrapActivityContext.getInstanceClassName() + ' ' + string;
                        } else {
                            str = "mContext instance of " + asInstance.getInstanceClassName() + ", wrapping activity " + heapInstanceUnwrapActivityContext.getInstanceClassName() + ' ' + string;
                        }
                    }
                    labels.add(str);
                    if (heapInstanceUnwrapActivityContext != null) {
                        HeapField heapField6 = heapInstanceUnwrapActivityContext.get("android.app.Activity", "mDestroyed");
                        if (Intrinsics.areEqual((Object) ((heapField6 == null || (value2 = heapField6.getValue()) == null) ? null : value2.getAsBoolean()), (Object) true)) {
                            objectReporter.getLeakingReasons().add("View.mContext references a destroyed activity");
                        }
                    } else if (zIsNonNullReference && iIntValue > 0) {
                        if (zIsNullReference) {
                            objectReporter.getLeakingReasons().add("View detached and has parent");
                        } else {
                            HeapObject asObject2 = value3.getAsObject();
                            if (asObject2 == null) {
                                Intrinsics.throwNpe();
                            }
                            HeapObject.HeapInstance asInstance2 = asObject2.getAsInstance();
                            if (asInstance2 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (asInstance2.instanceOf("android.view.View")) {
                                HeapField heapField7 = asInstance2.get("android.view.View", "mAttachInfo");
                                if (heapField7 == null) {
                                    Intrinsics.throwNpe();
                                }
                                if (heapField7.getValue().isNullReference()) {
                                    objectReporter.getLeakingReasons().add("View attached but parent " + asInstance2.getInstanceClassName() + " detached (attach disorder)");
                                } else {
                                    objectReporter.getNotLeakingReasons().add("View attached");
                                    objectReporter.getLabels().add("View.parent " + asInstance2.getInstanceClassName() + " attached as well");
                                }
                            } else {
                                objectReporter.getNotLeakingReasons().add("View attached");
                                objectReporter.getLabels().add("Parent " + asInstance2.getInstanceClassName() + " not a android.view.View");
                            }
                        }
                    }
                    objectReporter.getLabels().add(zIsNonNullReference ? "View#mParent is set" : "View#mParent is null");
                    objectReporter.getLabels().add(zIsNullReference ? "View#mAttachInfo is null (view detached)" : "View#mAttachInfo is not null (view attached)");
                    AndroidResourceIdNames fromHeap = AndroidResourceIdNames.INSTANCE.readFromHeap(heapInstance.getGraph());
                    if (fromHeap != null) {
                        HeapField heapField8 = heapInstance.get("android.view.View", "mID");
                        if (heapField8 == null) {
                            Intrinsics.throwNpe();
                        }
                        Integer asInt2 = heapField8.getValue().getAsInt();
                        if (asInt2 == null) {
                            Intrinsics.throwNpe();
                        }
                        int iIntValue2 = asInt2.intValue();
                        if (iIntValue2 != -1) {
                            String str2 = fromHeap.get(iIntValue2);
                            objectReporter.getLabels().add("View.mID = R.id." + str2);
                        }
                    }
                    objectReporter.getLabels().add("View.mWindowAttachCount = " + iIntValue);
                }
            });
        }
    },
    EDITOR {
        private final Function1<HeapObject, Boolean> leakingObjectFilter = new Function1<HeapObject, Boolean>() {
            @Override
            public Boolean invoke(HeapObject heapObject) {
                return Boolean.valueOf(invoke2(heapObject));
            }

            public final boolean invoke2(HeapObject heapObject) {
                boolean zBooleanValue;
                HeapValue value;
                HeapObject asObject;
                Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                if (!(heapObject instanceof HeapObject.HeapInstance)) {
                    return false;
                }
                HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObject;
                if (!heapInstance.instanceOf("android.widget.Editor")) {
                    return false;
                }
                HeapField heapField = heapInstance.get("android.widget.Editor", "mTextView");
                if (heapField == null || (value = heapField.getValue()) == null || (asObject = value.getAsObject()) == null) {
                    zBooleanValue = false;
                } else {
                    Function1<HeapObject, Boolean> leakingObjectFilter$shark = AndroidObjectInspectors.VIEW.getLeakingObjectFilter$shark();
                    if (leakingObjectFilter$shark == null) {
                        Intrinsics.throwNpe();
                    }
                    zBooleanValue = leakingObjectFilter$shark.invoke(asObject).booleanValue();
                }
                return zBooleanValue;
            }
        };

        @Override
        public Function1<HeapObject, Boolean> getLeakingObjectFilter$shark() {
            return this.leakingObjectFilter;
        }

        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf("android.widget.Editor", new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "instance");
                    AndroidObjectInspectorsKt.applyFromField(objectReporter, AndroidObjectInspectors.VIEW, heapInstance.get("android.widget.Editor", "mTextView"));
                }
            });
        }
    },
    ACTIVITY {
        private final Function1<HeapObject, Boolean> leakingObjectFilter = new Function1<HeapObject, Boolean>() {
            @Override
            public Boolean invoke(HeapObject heapObject) {
                return Boolean.valueOf(invoke2(heapObject));
            }

            public final boolean invoke2(HeapObject heapObject) {
                HeapValue value;
                Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                if (heapObject instanceof HeapObject.HeapInstance) {
                    HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObject;
                    if (heapInstance.instanceOf("android.app.Activity")) {
                        HeapField heapField = heapInstance.get("android.app.Activity", "mDestroyed");
                        if (Intrinsics.areEqual((Object) ((heapField == null || (value = heapField.getValue()) == null) ? null : value.getAsBoolean()), (Object) true)) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };

        @Override
        public Function1<HeapObject, Boolean> getLeakingObjectFilter$shark() {
            return this.leakingObjectFilter;
        }

        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf("android.app.Activity", new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "instance");
                    HeapField heapField = heapInstance.get("android.app.Activity", "mDestroyed");
                    if (heapField != null) {
                        Boolean asBoolean = heapField.getValue().getAsBoolean();
                        if (asBoolean == null) {
                            Intrinsics.throwNpe();
                        }
                        if (asBoolean.booleanValue()) {
                            objectReporter.getLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "true"));
                        } else {
                            objectReporter.getNotLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "false"));
                        }
                    }
                }
            });
        }
    },
    CONTEXT_WRAPPER {
        private final Function1<HeapObject, Boolean> leakingObjectFilter = new Function1<HeapObject, Boolean>() {
            @Override
            public Boolean invoke(HeapObject heapObject) {
                return Boolean.valueOf(invoke2(heapObject));
            }

            public final boolean invoke2(HeapObject heapObject) {
                HeapField heapField;
                HeapValue value;
                Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                if (heapObject instanceof HeapObject.HeapInstance) {
                    HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObject;
                    if (heapInstance.instanceOf("android.app.Activity")) {
                        HeapObject.HeapInstance heapInstanceUnwrapActivityContext = AndroidObjectInspectorsKt.unwrapActivityContext(heapInstance);
                        if (Intrinsics.areEqual((Object) ((heapInstanceUnwrapActivityContext == null || (heapField = heapInstanceUnwrapActivityContext.get("android.app.Activity", "mDestroyed")) == null || (value = heapField.getValue()) == null) ? null : value.getAsBoolean()), (Object) true)) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };

        @Override
        public Function1<HeapObject, Boolean> getLeakingObjectFilter$shark() {
            return this.leakingObjectFilter;
        }

        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf("android.content.ContextWrapper", new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "instance");
                    if (heapInstance.instanceOf("android.app.Activity")) {
                        return;
                    }
                    HeapObject.HeapInstance heapInstanceUnwrapActivityContext = AndroidObjectInspectorsKt.unwrapActivityContext(heapInstance);
                    if (heapInstanceUnwrapActivityContext == null) {
                        objectReporter.getLabels().add(heapInstance.getInstanceClassSimpleName() + " does not wrap an activity context");
                        return;
                    }
                    HeapField heapField = heapInstanceUnwrapActivityContext.get("android.app.Activity", "mDestroyed");
                    if (heapField != null) {
                        Boolean asBoolean = heapField.getValue().getAsBoolean();
                        if (asBoolean == null) {
                            Intrinsics.throwNpe();
                        }
                        if (asBoolean.booleanValue()) {
                            objectReporter.getLeakingReasons().add(heapInstance.getInstanceClassSimpleName() + " wraps an Activity with Activity.mDestroyed true");
                            return;
                        }
                        objectReporter.getLabels().add(heapInstance.getInstanceClassSimpleName() + " wraps an Activity with Activity.mDestroyed false");
                    }
                }
            });
        }
    },
    DIALOG {
        private final Function1<HeapObject, Boolean> leakingObjectFilter = new Function1<HeapObject, Boolean>() {
            @Override
            public Boolean invoke(HeapObject heapObject) {
                return Boolean.valueOf(invoke2(heapObject));
            }

            public final boolean invoke2(HeapObject heapObject) {
                Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                if (heapObject instanceof HeapObject.HeapInstance) {
                    HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObject;
                    if (heapInstance.instanceOf("android.app.Dialog")) {
                        HeapField heapField = heapInstance.get("android.app.Dialog", "mDecor");
                        if (heapField == null) {
                            Intrinsics.throwNpe();
                        }
                        if (heapField.getValue().isNullReference()) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };

        @Override
        public Function1<HeapObject, Boolean> getLeakingObjectFilter$shark() {
            return this.leakingObjectFilter;
        }

        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf("android.app.Dialog", new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "instance");
                    HeapField heapField = heapInstance.get("android.app.Dialog", "mDecor");
                    if (heapField == null) {
                        Intrinsics.throwNpe();
                    }
                    if (heapField.getValue().isNullReference()) {
                        objectReporter.getLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "null"));
                    } else {
                        objectReporter.getNotLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "not null"));
                    }
                }
            });
        }
    },
    APPLICATION {
        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf("android.app.Application", new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "it");
                    objectReporter.getNotLeakingReasons().add("Application is a singleton");
                }
            });
        }
    },
    INPUT_METHOD_MANAGER {
        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf("android.view.inputmethod.InputMethodManager", new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "it");
                    objectReporter.getNotLeakingReasons().add("InputMethodManager is a singleton");
                }
            });
        }
    },
    FRAGMENT {
        private final Function1<HeapObject, Boolean> leakingObjectFilter = new Function1<HeapObject, Boolean>() {
            @Override
            public Boolean invoke(HeapObject heapObject) {
                return Boolean.valueOf(invoke2(heapObject));
            }

            public final boolean invoke2(HeapObject heapObject) {
                Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                if (heapObject instanceof HeapObject.HeapInstance) {
                    HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObject;
                    if (heapInstance.instanceOf("android.app.Fragment")) {
                        HeapField heapField = heapInstance.get("android.app.Fragment", "mFragmentManager");
                        if (heapField == null) {
                            Intrinsics.throwNpe();
                        }
                        if (heapField.getValue().isNullReference()) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };

        @Override
        public Function1<HeapObject, Boolean> getLeakingObjectFilter$shark() {
            return this.leakingObjectFilter;
        }

        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf("android.app.Fragment", new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    HeapValue value;
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "instance");
                    HeapField heapField = heapInstance.get("android.app.Fragment", "mFragmentManager");
                    if (heapField == null) {
                        Intrinsics.throwNpe();
                    }
                    if (heapField.getValue().isNullReference()) {
                        objectReporter.getLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "null"));
                    } else {
                        objectReporter.getNotLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "not null"));
                    }
                    HeapField heapField2 = heapInstance.get("android.app.Fragment", "mTag");
                    String asJavaString = (heapField2 == null || (value = heapField2.getValue()) == null) ? null : value.readAsJavaString();
                    String str = asJavaString;
                    if (str == null || str.length() == 0) {
                        return;
                    }
                    objectReporter.getLabels().add("Fragment.mTag=" + asJavaString);
                }
            });
        }
    },
    SUPPORT_FRAGMENT {
        private final Function1<HeapObject, Boolean> leakingObjectFilter = new Function1<HeapObject, Boolean>() {
            @Override
            public Boolean invoke(HeapObject heapObject) {
                return Boolean.valueOf(invoke2(heapObject));
            }

            public final boolean invoke2(HeapObject heapObject) {
                Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                if (heapObject instanceof HeapObject.HeapInstance) {
                    HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObject;
                    if (heapInstance.instanceOf("androidx.fragment.app.Fragment")) {
                        HeapField heapField = heapInstance.get("androidx.fragment.app.Fragment", "mFragmentManager");
                        if (heapField == null) {
                            Intrinsics.throwNpe();
                        }
                        if (heapField.getValue().isNullReference()) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };

        @Override
        public Function1<HeapObject, Boolean> getLeakingObjectFilter$shark() {
            return this.leakingObjectFilter;
        }

        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf("androidx.fragment.app.Fragment", new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    HeapValue value;
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "instance");
                    HeapField heapField = heapInstance.get("androidx.fragment.app.Fragment", "mFragmentManager");
                    if (heapField == null) {
                        Intrinsics.throwNpe();
                    }
                    if (heapField.getValue().isNullReference()) {
                        objectReporter.getLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "null"));
                    } else {
                        objectReporter.getNotLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "not null"));
                    }
                    HeapField heapField2 = heapInstance.get("androidx.fragment.app.Fragment", "mTag");
                    String asJavaString = (heapField2 == null || (value = heapField2.getValue()) == null) ? null : value.readAsJavaString();
                    String str = asJavaString;
                    if (str == null || str.length() == 0) {
                        return;
                    }
                    objectReporter.getLabels().add("Fragment.mTag=" + asJavaString);
                }
            });
        }
    },
    ANDROIDX_FRAGMENT {
        private final Function1<HeapObject, Boolean> leakingObjectFilter = new Function1<HeapObject, Boolean>() {
            @Override
            public Boolean invoke(HeapObject heapObject) {
                return Boolean.valueOf(invoke2(heapObject));
            }

            public final boolean invoke2(HeapObject heapObject) {
                Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                if (heapObject instanceof HeapObject.HeapInstance) {
                    HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObject;
                    if (heapInstance.instanceOf("androidx.fragment.app.Fragment")) {
                        HeapField heapField = heapInstance.get("androidx.fragment.app.Fragment", "mFragmentManager");
                        if (heapField == null) {
                            Intrinsics.throwNpe();
                        }
                        if (heapField.getValue().isNullReference()) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };

        @Override
        public Function1<HeapObject, Boolean> getLeakingObjectFilter$shark() {
            return this.leakingObjectFilter;
        }

        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf("androidx.fragment.app.Fragment", new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    HeapValue value;
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "instance");
                    HeapField heapField = heapInstance.get("androidx.fragment.app.Fragment", "mFragmentManager");
                    if (heapField == null) {
                        Intrinsics.throwNpe();
                    }
                    if (heapField.getValue().isNullReference()) {
                        objectReporter.getLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "null"));
                    } else {
                        objectReporter.getNotLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "not null"));
                    }
                    HeapField heapField2 = heapInstance.get("androidx.fragment.app.Fragment", "mTag");
                    String asJavaString = (heapField2 == null || (value = heapField2.getValue()) == null) ? null : value.readAsJavaString();
                    String str = asJavaString;
                    if (str == null || str.length() == 0) {
                        return;
                    }
                    objectReporter.getLabels().add("Fragment.mTag=" + asJavaString);
                }
            });
        }
    },
    MESSAGE_QUEUE {
        private final Function1<HeapObject, Boolean> leakingObjectFilter = new Function1<HeapObject, Boolean>() {
            @Override
            public Boolean invoke(HeapObject heapObject) {
                return Boolean.valueOf(invoke2(heapObject));
            }

            public final boolean invoke2(HeapObject heapObject) {
                Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                if (heapObject instanceof HeapObject.HeapInstance) {
                    HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObject;
                    if (heapInstance.instanceOf("android.os.MessageQueue")) {
                        HeapField heapField = heapInstance.get("android.os.MessageQueue", "mQuitting");
                        if (heapField == null && (heapField = heapInstance.get("android.os.MessageQueue", "mQuiting")) == null) {
                            Intrinsics.throwNpe();
                        }
                        Boolean asBoolean = heapField.getValue().getAsBoolean();
                        if (asBoolean == null) {
                            Intrinsics.throwNpe();
                        }
                        if (asBoolean.booleanValue()) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };

        @Override
        public Function1<HeapObject, Boolean> getLeakingObjectFilter$shark() {
            return this.leakingObjectFilter;
        }

        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf("android.os.MessageQueue", new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "instance");
                    HeapField heapField = heapInstance.get("android.os.MessageQueue", "mQuitting");
                    if (heapField == null && (heapField = heapInstance.get("android.os.MessageQueue", "mQuiting")) == null) {
                        Intrinsics.throwNpe();
                    }
                    Boolean asBoolean = heapField.getValue().getAsBoolean();
                    if (asBoolean == null) {
                        Intrinsics.throwNpe();
                    }
                    if (asBoolean.booleanValue()) {
                        objectReporter.getLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "true"));
                    } else {
                        objectReporter.getNotLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "false"));
                    }
                }
            });
        }
    },
    MORTAR_PRESENTER {
        private final Function1<HeapObject, Boolean> leakingObjectFilter = new Function1<HeapObject, Boolean>() {
            @Override
            public Boolean invoke(HeapObject heapObject) {
                return Boolean.valueOf(invoke2(heapObject));
            }

            public final boolean invoke2(HeapObject heapObject) {
                Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                if (heapObject instanceof HeapObject.HeapInstance) {
                    HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObject;
                    if (heapInstance.instanceOf("mortar.Presenter")) {
                        HeapField heapField = heapInstance.get("mortar.Presenter", "view");
                        if (heapField == null) {
                            Intrinsics.throwNpe();
                        }
                        if (heapField.getValue().isNullReference()) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };

        @Override
        public Function1<HeapObject, Boolean> getLeakingObjectFilter$shark() {
            return this.leakingObjectFilter;
        }

        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf("mortar.Presenter", new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "instance");
                    HeapField heapField = heapInstance.get("mortar.Presenter", "view");
                    if (heapField == null) {
                        Intrinsics.throwNpe();
                    }
                    if (heapField.getValue().isNullReference()) {
                        objectReporter.getLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "null"));
                    } else {
                        objectReporter.getLabels().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "set"));
                    }
                }
            });
        }
    },
    MORTAR_SCOPE {
        private final Function1<HeapObject, Boolean> leakingObjectFilter = new Function1<HeapObject, Boolean>() {
            @Override
            public Boolean invoke(HeapObject heapObject) {
                return Boolean.valueOf(invoke2(heapObject));
            }

            public final boolean invoke2(HeapObject heapObject) {
                Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                if (heapObject instanceof HeapObject.HeapInstance) {
                    HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObject;
                    if (heapInstance.instanceOf("mortar.MortarScope")) {
                        HeapField heapField = heapInstance.get("mortar.MortarScope", "dead");
                        if (heapField == null) {
                            Intrinsics.throwNpe();
                        }
                        Boolean asBoolean = heapField.getValue().getAsBoolean();
                        if (asBoolean == null) {
                            Intrinsics.throwNpe();
                        }
                        if (asBoolean.booleanValue()) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };

        @Override
        public Function1<HeapObject, Boolean> getLeakingObjectFilter$shark() {
            return this.leakingObjectFilter;
        }

        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf("mortar.MortarScope", new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "instance");
                    HeapField heapField = heapInstance.get("mortar.MortarScope", "dead");
                    if (heapField == null) {
                        Intrinsics.throwNpe();
                    }
                    Boolean asBoolean = heapField.getValue().getAsBoolean();
                    if (asBoolean == null) {
                        Intrinsics.throwNpe();
                    }
                    boolean zBooleanValue = asBoolean.booleanValue();
                    HeapField heapField2 = heapInstance.get("mortar.MortarScope", "name");
                    if (heapField2 == null) {
                        Intrinsics.throwNpe();
                    }
                    String asJavaString = heapField2.getValue().readAsJavaString();
                    if (zBooleanValue) {
                        objectReporter.getLeakingReasons().add("mortar.MortarScope.dead is true for scope " + asJavaString);
                        return;
                    }
                    objectReporter.getNotLeakingReasons().add("mortar.MortarScope.dead is false for scope " + asJavaString);
                }
            });
        }
    },
    COORDINATOR {
        private final Function1<HeapObject, Boolean> leakingObjectFilter = new Function1<HeapObject, Boolean>() {
            @Override
            public Boolean invoke(HeapObject heapObject) {
                return Boolean.valueOf(invoke2(heapObject));
            }

            public final boolean invoke2(HeapObject heapObject) {
                Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                if (heapObject instanceof HeapObject.HeapInstance) {
                    HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObject;
                    if (heapInstance.instanceOf("com.squareup.coordinators.Coordinator")) {
                        HeapField heapField = heapInstance.get("com.squareup.coordinators.Coordinator", "attached");
                        if (heapField == null) {
                            Intrinsics.throwNpe();
                        }
                        Boolean asBoolean = heapField.getValue().getAsBoolean();
                        if (asBoolean == null) {
                            Intrinsics.throwNpe();
                        }
                        if (!asBoolean.booleanValue()) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };

        @Override
        public Function1<HeapObject, Boolean> getLeakingObjectFilter$shark() {
            return this.leakingObjectFilter;
        }

        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf("com.squareup.coordinators.Coordinator", new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "instance");
                    HeapField heapField = heapInstance.get("com.squareup.coordinators.Coordinator", "attached");
                    if (heapField == null) {
                        Intrinsics.throwNpe();
                    }
                    Boolean asBoolean = heapField.getValue().getAsBoolean();
                    if (asBoolean == null) {
                        Intrinsics.throwNpe();
                    }
                    if (asBoolean.booleanValue()) {
                        objectReporter.getNotLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "true"));
                    } else {
                        objectReporter.getLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "false"));
                    }
                }
            });
        }
    },
    MAIN_THREAD {
        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf(Reflection.getOrCreateKotlinClass(Thread.class), new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "instance");
                    HeapField heapField = heapInstance.get(Reflection.getOrCreateKotlinClass(Thread.class), "name");
                    if (heapField == null) {
                        Intrinsics.throwNpe();
                    }
                    if (Intrinsics.areEqual(heapField.getValue().readAsJavaString(), "main")) {
                        objectReporter.getNotLeakingReasons().add("the main thread always runs");
                    }
                }
            });
        }
    },
    VIEW_ROOT_IMPL {
        private final Function1<HeapObject, Boolean> leakingObjectFilter = new Function1<HeapObject, Boolean>() {
            @Override
            public Boolean invoke(HeapObject heapObject) {
                return Boolean.valueOf(invoke2(heapObject));
            }

            public final boolean invoke2(HeapObject heapObject) {
                Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                if (heapObject instanceof HeapObject.HeapInstance) {
                    HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObject;
                    if (heapInstance.instanceOf("android.view.ViewRootImpl")) {
                        HeapField heapField = heapInstance.get("android.view.ViewRootImpl", "mView");
                        if (heapField == null) {
                            Intrinsics.throwNpe();
                        }
                        if (heapField.getValue().isNullReference()) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };

        @Override
        public Function1<HeapObject, Boolean> getLeakingObjectFilter$shark() {
            return this.leakingObjectFilter;
        }

        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf("android.view.ViewRootImpl", new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "instance");
                    HeapField heapField = heapInstance.get("android.view.ViewRootImpl", "mView");
                    if (heapField == null) {
                        Intrinsics.throwNpe();
                    }
                    if (heapField.getValue().isNullReference()) {
                        objectReporter.getLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "null"));
                    } else {
                        objectReporter.getNotLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "not null"));
                    }
                }
            });
        }
    },
    WINDOW {
        private final Function1<HeapObject, Boolean> leakingObjectFilter = new Function1<HeapObject, Boolean>() {
            @Override
            public Boolean invoke(HeapObject heapObject) {
                return Boolean.valueOf(invoke2(heapObject));
            }

            public final boolean invoke2(HeapObject heapObject) {
                Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                if (heapObject instanceof HeapObject.HeapInstance) {
                    HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObject;
                    if (heapInstance.instanceOf("android.view.Window")) {
                        HeapField heapField = heapInstance.get("android.view.Window", "mDestroyed");
                        if (heapField == null) {
                            Intrinsics.throwNpe();
                        }
                        Boolean asBoolean = heapField.getValue().getAsBoolean();
                        if (asBoolean == null) {
                            Intrinsics.throwNpe();
                        }
                        if (asBoolean.booleanValue()) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };

        @Override
        public Function1<HeapObject, Boolean> getLeakingObjectFilter$shark() {
            return this.leakingObjectFilter;
        }

        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf("android.view.Window", new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "instance");
                    HeapField heapField = heapInstance.get("android.view.Window", "mDestroyed");
                    if (heapField == null) {
                        Intrinsics.throwNpe();
                    }
                    Boolean asBoolean = heapField.getValue().getAsBoolean();
                    if (asBoolean == null) {
                        Intrinsics.throwNpe();
                    }
                    if (asBoolean.booleanValue()) {
                        objectReporter.getLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "true"));
                    } else {
                        objectReporter.getNotLeakingReasons().add(AndroidObjectInspectorsKt.describedWithValue(heapField, "false"));
                    }
                }
            });
        }
    },
    TOAST {
        private final Function1<HeapObject, Boolean> leakingObjectFilter = new Function1<HeapObject, Boolean>() {
            @Override
            public Boolean invoke(HeapObject heapObject) {
                return Boolean.valueOf(invoke2(heapObject));
            }

            public final boolean invoke2(HeapObject heapObject) {
                Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                if (!(heapObject instanceof HeapObject.HeapInstance)) {
                    return false;
                }
                HeapObject.HeapInstance heapInstance = (HeapObject.HeapInstance) heapObject;
                if (!heapInstance.instanceOf("android.widget.Toast")) {
                    return false;
                }
                HeapField heapField = heapInstance.get("android.widget.Toast", "mTN");
                if (heapField == null) {
                    Intrinsics.throwNpe();
                }
                HeapObject asObject = heapField.getValue().getAsObject();
                if (asObject == null) {
                    Intrinsics.throwNpe();
                }
                HeapObject.HeapInstance asInstance = asObject.getAsInstance();
                if (asInstance == null) {
                    Intrinsics.throwNpe();
                }
                HeapField heapField2 = asInstance.get("android.widget.Toast$TN", "mWM");
                if (heapField2 == null) {
                    Intrinsics.throwNpe();
                }
                if (!heapField2.getValue().isNonNullReference()) {
                    return false;
                }
                HeapField heapField3 = asInstance.get("android.widget.Toast$TN", "mView");
                if (heapField3 == null) {
                    Intrinsics.throwNpe();
                }
                return heapField3.getValue().isNullReference();
            }
        };

        @Override
        public Function1<HeapObject, Boolean> getLeakingObjectFilter$shark() {
            return this.leakingObjectFilter;
        }

        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf("android.widget.Toast", new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "instance");
                    HeapField heapField = heapInstance.get("android.widget.Toast", "mTN");
                    if (heapField == null) {
                        Intrinsics.throwNpe();
                    }
                    HeapObject asObject = heapField.getValue().getAsObject();
                    if (asObject == null) {
                        Intrinsics.throwNpe();
                    }
                    HeapObject.HeapInstance asInstance = asObject.getAsInstance();
                    if (asInstance == null) {
                        Intrinsics.throwNpe();
                    }
                    HeapField heapField2 = asInstance.get("android.widget.Toast$TN", "mWM");
                    if (heapField2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (heapField2.getValue().isNonNullReference()) {
                        HeapField heapField3 = asInstance.get("android.widget.Toast$TN", "mView");
                        if (heapField3 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (heapField3.getValue().isNullReference()) {
                            objectReporter.getLeakingReasons().add("This toast is done showing (Toast.mTN.mWM != null && Toast.mTN.mView == null)");
                        } else {
                            objectReporter.getNotLeakingReasons().add("This toast is showing (Toast.mTN.mWM != null && Toast.mTN.mView != null)");
                        }
                    }
                }
            });
        }
    };


    public static final Companion INSTANCE = new Companion(null);
    private static final List<FilteringLeakingObjectFinder.LeakingObjectFilter> appLeakingObjectFilters;
    private final Function1<HeapObject, Boolean> leakingObjectFilter;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\u00042\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007¨\u0006\u000f"}, d2 = {"Lkshark/AndroidObjectInspectors$Companion;", "", "()V", "appDefaults", "", "Lkshark/ObjectInspector;", "getAppDefaults", "()Ljava/util/List;", "appLeakingObjectFilters", "Lkshark/FilteringLeakingObjectFinder$LeakingObjectFilter;", "getAppLeakingObjectFilters", "createLeakingObjectFilters", "inspectors", "", "Lkshark/AndroidObjectInspectors;", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final List<FilteringLeakingObjectFinder.LeakingObjectFilter> createLeakingObjectFilters(Set<? extends AndroidObjectInspectors> inspectors) {
            Intrinsics.checkParameterIsNotNull(inspectors, "inspectors");
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = inspectors.iterator();
            while (it.hasNext()) {
                Function1<HeapObject, Boolean> leakingObjectFilter$shark = ((AndroidObjectInspectors) it.next()).getLeakingObjectFilter$shark();
                if (leakingObjectFilter$shark != null) {
                    arrayList.add(leakingObjectFilter$shark);
                }
            }
            ArrayList<Function1> arrayList2 = arrayList;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            for (final Function1 function1 : arrayList2) {
                arrayList3.add(new FilteringLeakingObjectFinder.LeakingObjectFilter() {
                    @Override
                    public boolean isLeakingObject(HeapObject heapObject) {
                        Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                        return ((Boolean) function1.invoke(heapObject)).booleanValue();
                    }
                });
            }
            return arrayList3;
        }

        public final List<ObjectInspector> getAppDefaults() {
            return CollectionsKt.plus((Collection) ObjectInspectors.INSTANCE.getJdkDefaults(), (Object[]) AndroidObjectInspectors.values());
        }

        public final List<FilteringLeakingObjectFinder.LeakingObjectFilter> getAppLeakingObjectFilters() {
            return AndroidObjectInspectors.appLeakingObjectFilters;
        }
    }

    static {
        List<FilteringLeakingObjectFinder.LeakingObjectFilter> jdkLeakingObjectFilters = ObjectInspectors.INSTANCE.getJdkLeakingObjectFilters();
        Companion companion = INSTANCE;
        EnumSet enumSetAllOf = EnumSet.allOf(AndroidObjectInspectors.class);
        Intrinsics.checkExpressionValueIsNotNull(enumSetAllOf, "EnumSet.allOf(AndroidObjectInspectors::class.java)");
        appLeakingObjectFilters = CollectionsKt.plus((Collection) jdkLeakingObjectFilters, (Iterable) companion.createLeakingObjectFilters(enumSetAllOf));
    }

    AndroidObjectInspectors(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public Function1<HeapObject, Boolean> getLeakingObjectFilter$shark() {
        return this.leakingObjectFilter;
    }
}
