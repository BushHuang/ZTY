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
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Regex;
import kshark.FilteringLeakingObjectFinder;
import kshark.HeapObject;
import kshark.internal.KeyedWeakReferenceMirror;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u0012B\u0007\b\u0002¢\u0006\u0002\u0010\u0003R1\u0010\u0004\u001a\u001f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0013"}, d2 = {"Lkshark/ObjectInspectors;", "", "Lkshark/ObjectInspector;", "(Ljava/lang/String;I)V", "leakingObjectFilter", "Lkotlin/Function1;", "Lkshark/HeapObject;", "Lkotlin/ParameterName;", "name", "heapObject", "", "getLeakingObjectFilter$shark", "()Lkotlin/jvm/functions/Function1;", "KEYED_WEAK_REFERENCE", "CLASSLOADER", "CLASS", "ANONYMOUS_CLASS", "THREAD", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public enum ObjectInspectors implements ObjectInspector {
    KEYED_WEAK_REFERENCE {
        private final Function1<HeapObject, Boolean> leakingObjectFilter = new Function1<HeapObject, Boolean>() {
            @Override
            public Boolean invoke(HeapObject heapObject) {
                return Boolean.valueOf(invoke2(heapObject));
            }

            public final boolean invoke2(HeapObject heapObject) {
                Intrinsics.checkParameterIsNotNull(heapObject, "heapObject");
                List<KeyedWeakReferenceMirror> listFindKeyedWeakReferences$shark = KeyedWeakReferenceFinder.INSTANCE.findKeyedWeakReferences$shark(heapObject.getGraph());
                if (!(listFindKeyedWeakReferences$shark instanceof Collection) || !listFindKeyedWeakReferences$shark.isEmpty()) {
                    Iterator<T> it = listFindKeyedWeakReferences$shark.iterator();
                    while (it.hasNext()) {
                        if (((KeyedWeakReferenceMirror) it.next()).getReferent().getValue() == heapObject.getObjectId()) {
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
            List<KeyedWeakReferenceMirror> listFindKeyedWeakReferences$shark = KeyedWeakReferenceFinder.INSTANCE.findKeyedWeakReferences$shark(reporter.getHeapObject().getGraph());
            long objectId = reporter.getHeapObject().getObjectId();
            for (KeyedWeakReferenceMirror keyedWeakReferenceMirror : listFindKeyedWeakReferences$shark) {
                if (keyedWeakReferenceMirror.getReferent().getValue() == objectId) {
                    reporter.getLeakingReasons().add(keyedWeakReferenceMirror.getDescription().length() > 0 ? "ObjectWatcher was watching this because " + keyedWeakReferenceMirror.getDescription() : "ObjectWatcher was watching this");
                    reporter.getLabels().add("key = " + keyedWeakReferenceMirror.getKey());
                    if (keyedWeakReferenceMirror.getWatchDurationMillis() != null) {
                        reporter.getLabels().add("watchDurationMillis = " + keyedWeakReferenceMirror.getWatchDurationMillis());
                    }
                    if (keyedWeakReferenceMirror.getRetainedDurationMillis() != null) {
                        reporter.getLabels().add("retainedDurationMillis = " + keyedWeakReferenceMirror.getRetainedDurationMillis());
                    }
                }
            }
        }
    },
    CLASSLOADER {
        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            reporter.whenInstanceOf(Reflection.getOrCreateKotlinClass(ClassLoader.class), new Function2<ObjectReporter, HeapObject.HeapInstance, Unit>() {
                @Override
                public Unit invoke(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    invoke2(objectReporter, heapInstance);
                    return Unit.INSTANCE;
                }

                public final void invoke2(ObjectReporter objectReporter, HeapObject.HeapInstance heapInstance) {
                    Intrinsics.checkParameterIsNotNull(objectReporter, "$receiver");
                    Intrinsics.checkParameterIsNotNull(heapInstance, "it");
                    objectReporter.getNotLeakingReasons().add("A ClassLoader is never leaking");
                }
            });
        }
    },
    CLASS {
        @Override
        public void inspect(ObjectReporter reporter) {
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            if (reporter.getHeapObject() instanceof HeapObject.HeapClass) {
                reporter.getNotLeakingReasons().add("a class is never leaking");
            }
        }
    },
    ANONYMOUS_CLASS {
        @Override
        public void inspect(ObjectReporter reporter) throws ClassNotFoundException {
            String string;
            Intrinsics.checkParameterIsNotNull(reporter, "reporter");
            HeapObject heapObject = reporter.getHeapObject();
            if (heapObject instanceof HeapObject.HeapInstance) {
                HeapObject.HeapClass instanceClass = ((HeapObject.HeapInstance) heapObject).getInstanceClass();
                if (ObjectInspectors.ANONYMOUS_CLASS_NAME_PATTERN_REGEX.matches(instanceClass.getName())) {
                    HeapObject.HeapClass superclass = instanceClass.getSuperclass();
                    if (superclass == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!Intrinsics.areEqual(superclass.getName(), "java.lang.Object")) {
                        reporter.getLabels().add("Anonymous subclass of " + superclass.getName());
                        return;
                    }
                    try {
                        Class<?> cls = Class.forName(instanceClass.getName());
                        Intrinsics.checkExpressionValueIsNotNull(cls, "actualClass");
                        Class<?>[] interfaces = cls.getInterfaces();
                        LinkedHashSet<String> labels = reporter.getLabels();
                        Intrinsics.checkExpressionValueIsNotNull(interfaces, "interfaces");
                        if (!(interfaces.length == 0)) {
                            Class<?> cls2 = interfaces[0];
                            StringBuilder sb = new StringBuilder();
                            sb.append("Anonymous class implementing ");
                            Intrinsics.checkExpressionValueIsNotNull(cls2, "implementedInterface");
                            sb.append(cls2.getName());
                            string = sb.toString();
                        } else {
                            string = "Anonymous subclass of java.lang.Object";
                        }
                        labels.add(string);
                    } catch (ClassNotFoundException unused) {
                    }
                }
            }
        }
    },
    THREAD {
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
                    String asJavaString = heapField.getValue().readAsJavaString();
                    objectReporter.getLabels().add("Thread name: '" + asJavaString + '\'');
                }
            });
        }
    };

    private static final String ANONYMOUS_CLASS_NAME_PATTERN = "^.+\\$\\d+$";
    private static final List<FilteringLeakingObjectFinder.LeakingObjectFilter> jdkLeakingObjectFilters;
    private final Function1<HeapObject, Boolean> leakingObjectFilter;

    public static final Companion INSTANCE = new Companion(null);
    private static final Regex ANONYMOUS_CLASS_NAME_PATTERN_REGEX = new Regex("^.+\\$\\d+$");

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u0013"}, d2 = {"Lkshark/ObjectInspectors$Companion;", "", "()V", "ANONYMOUS_CLASS_NAME_PATTERN", "", "ANONYMOUS_CLASS_NAME_PATTERN_REGEX", "Lkotlin/text/Regex;", "jdkDefaults", "", "Lkshark/ObjectInspector;", "getJdkDefaults", "()Ljava/util/List;", "jdkLeakingObjectFilters", "Lkshark/FilteringLeakingObjectFinder$LeakingObjectFilter;", "getJdkLeakingObjectFilters", "createLeakingObjectFilters", "inspectors", "", "Lkshark/ObjectInspectors;", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final List<FilteringLeakingObjectFinder.LeakingObjectFilter> createLeakingObjectFilters(Set<? extends ObjectInspectors> inspectors) {
            Intrinsics.checkParameterIsNotNull(inspectors, "inspectors");
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = inspectors.iterator();
            while (it.hasNext()) {
                Function1<HeapObject, Boolean> leakingObjectFilter$shark = ((ObjectInspectors) it.next()).getLeakingObjectFilter$shark();
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

        public final List<ObjectInspector> getJdkDefaults() {
            return ArraysKt.toList(ObjectInspectors.values());
        }

        public final List<FilteringLeakingObjectFinder.LeakingObjectFilter> getJdkLeakingObjectFilters() {
            return ObjectInspectors.jdkLeakingObjectFilters;
        }
    }

    static {
        Companion companion = INSTANCE;
        EnumSet enumSetAllOf = EnumSet.allOf(ObjectInspectors.class);
        Intrinsics.checkExpressionValueIsNotNull(enumSetAllOf, "EnumSet.allOf(ObjectInspectors::class.java)");
        jdkLeakingObjectFilters = companion.createLeakingObjectFilters(enumSetAllOf);
    }

    ObjectInspectors(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public Function1<HeapObject, Boolean> getLeakingObjectFilter$shark() {
        return this.leakingObjectFilter;
    }
}
