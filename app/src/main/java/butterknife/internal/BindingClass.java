package butterknife.internal;

import butterknife.internal.FieldCollectionViewBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class BindingClass {
    private final String className;
    private final String classPackage;
    private String parentViewBinder;
    private final String targetClass;
    private final Map<Integer, ViewBindings> viewIdMap = new LinkedHashMap();
    private final Map<FieldCollectionViewBinding, int[]> collectionBindings = new LinkedHashMap();
    private final List<FieldResourceBinding> resourceBindings = new ArrayList();

    static class AnonymousClass1 {
        static final int[] $SwitchMap$butterknife$internal$FieldCollectionViewBinding$Kind;

        static {
            int[] iArr = new int[FieldCollectionViewBinding.Kind.values().length];
            $SwitchMap$butterknife$internal$FieldCollectionViewBinding$Kind = iArr;
            try {
                iArr[FieldCollectionViewBinding.Kind.ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$butterknife$internal$FieldCollectionViewBinding$Kind[FieldCollectionViewBinding.Kind.LIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    BindingClass(String str, String str2, String str3) {
        this.classPackage = str;
        this.className = str2;
        this.targetClass = str3;
    }

    private void emitBindMethod(StringBuilder sb) {
        sb.append("  @Override ");
        sb.append("public void bind(final Finder finder, final T target, Object source) {\n");
        if (this.parentViewBinder != null) {
            sb.append("    super.bind(finder, target, source);\n\n");
        }
        if (!this.viewIdMap.isEmpty() || !this.collectionBindings.isEmpty()) {
            sb.append("    View view;\n");
            Iterator<ViewBindings> it = this.viewIdMap.values().iterator();
            while (it.hasNext()) {
                emitViewBindings(sb, it.next());
            }
            for (Map.Entry<FieldCollectionViewBinding, int[]> entry : this.collectionBindings.entrySet()) {
                emitCollectionBinding(sb, entry.getKey(), entry.getValue());
            }
        }
        if (!this.resourceBindings.isEmpty()) {
            sb.append("    Resources res = finder.getContext(source).getResources();\n");
            for (FieldResourceBinding fieldResourceBinding : this.resourceBindings) {
                sb.append("    target.");
                sb.append(fieldResourceBinding.getName());
                sb.append(" = res.");
                sb.append(fieldResourceBinding.getMethod());
                sb.append('(');
                sb.append(fieldResourceBinding.getId());
                sb.append(");\n");
            }
        }
        sb.append("  }\n");
    }

    private void emitCollectionBinding(StringBuilder sb, FieldCollectionViewBinding fieldCollectionViewBinding, int[] iArr) {
        sb.append("    target.");
        sb.append(fieldCollectionViewBinding.getName());
        sb.append(" = ");
        int i = AnonymousClass1.$SwitchMap$butterknife$internal$FieldCollectionViewBinding$Kind[fieldCollectionViewBinding.getKind().ordinal()];
        if (i == 1) {
            sb.append("Finder.arrayOf(");
        } else {
            if (i != 2) {
                throw new IllegalStateException("Unknown kind: " + fieldCollectionViewBinding.getKind());
            }
            sb.append("Finder.listOf(");
        }
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (i2 > 0) {
                sb.append(',');
            }
            sb.append("\n        finder.<");
            sb.append(fieldCollectionViewBinding.getType());
            sb.append(">");
            sb.append(fieldCollectionViewBinding.isRequired() ? "findRequiredView" : "findOptionalView");
            sb.append("(source, ");
            sb.append(iArr[i2]);
            sb.append(", \"");
            emitHumanDescription(sb, Collections.singleton(fieldCollectionViewBinding));
            sb.append("\")");
        }
        sb.append("\n    );\n");
    }

    private void emitFieldBindings(StringBuilder sb, ViewBindings viewBindings) {
        Collection<FieldViewBinding> fieldBindings = viewBindings.getFieldBindings();
        if (fieldBindings.isEmpty()) {
            return;
        }
        for (FieldViewBinding fieldViewBinding : fieldBindings) {
            sb.append("    target.");
            sb.append(fieldViewBinding.getName());
            sb.append(" = ");
            if (fieldViewBinding.requiresCast()) {
                sb.append("finder.castView(view");
                sb.append(", ");
                sb.append(viewBindings.getId());
                sb.append(", \"");
                emitHumanDescription(sb, fieldBindings);
                sb.append("\");\n");
            } else {
                sb.append("view;\n");
            }
        }
    }

    static void emitHumanDescription(StringBuilder sb, Collection<? extends ViewBinding> collection) {
        Iterator<? extends ViewBinding> it = collection.iterator();
        int size = collection.size();
        if (size == 1) {
            sb.append(it.next().getDescription());
            return;
        }
        if (size == 2) {
            sb.append(it.next().getDescription());
            sb.append(" and ");
            sb.append(it.next().getDescription());
            return;
        }
        int size2 = collection.size();
        for (int i = 0; i < size2; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            if (i == size2 - 1) {
                sb.append("and ");
            }
            sb.append(it.next().getDescription());
        }
    }

    private void emitMethodBindings(StringBuilder sb, ViewBindings viewBindings) {
        String str;
        Iterator<Map.Entry<ListenerClass, Map<ListenerMethod, Set<MethodViewBinding>>>> it;
        Map<ListenerMethod, Set<MethodViewBinding>> map;
        Iterator<ListenerMethod> it2;
        Map<ListenerClass, Map<ListenerMethod, Set<MethodViewBinding>>> methodBindings = viewBindings.getMethodBindings();
        if (methodBindings.isEmpty()) {
            return;
        }
        boolean zIsEmpty = viewBindings.getRequiredBindings().isEmpty();
        if (zIsEmpty) {
            sb.append("    if (view != null) {\n");
            str = "  ";
        } else {
            str = "";
        }
        Iterator<Map.Entry<ListenerClass, Map<ListenerMethod, Set<MethodViewBinding>>>> it3 = methodBindings.entrySet().iterator();
        while (it3.hasNext()) {
            Map.Entry<ListenerClass, Map<ListenerMethod, Set<MethodViewBinding>>> next = it3.next();
            ListenerClass key = next.getKey();
            Map<ListenerMethod, Set<MethodViewBinding>> value = next.getValue();
            boolean z = !"android.view.View".equals(key.targetType());
            sb.append(str);
            sb.append("    ");
            if (z) {
                sb.append("((");
                sb.append(key.targetType());
                if (key.genericArguments() > 0) {
                    sb.append('<');
                    for (int i = 0; i < key.genericArguments(); i++) {
                        if (i > 0) {
                            sb.append(", ");
                        }
                        sb.append('?');
                    }
                    sb.append('>');
                }
                sb.append(") ");
            }
            sb.append("view");
            if (z) {
                sb.append(')');
            }
            sb.append('.');
            sb.append(key.setter());
            sb.append("(\n");
            sb.append(str);
            sb.append("      new ");
            sb.append(key.type());
            sb.append("() {\n");
            Iterator<ListenerMethod> it4 = getListenerMethods(key).iterator();
            while (it4.hasNext()) {
                ListenerMethod next2 = it4.next();
                sb.append(str);
                sb.append("        @Override public ");
                sb.append(next2.returnType());
                sb.append(' ');
                sb.append(next2.name());
                sb.append("(\n");
                String[] strArrParameters = next2.parameters();
                int length = strArrParameters.length;
                for (int i2 = 0; i2 < length; i2++) {
                    sb.append(str);
                    sb.append("          ");
                    sb.append(strArrParameters[i2]);
                    sb.append(" p");
                    sb.append(i2);
                    if (i2 < length - 1) {
                        sb.append(',');
                    }
                    sb.append('\n');
                }
                sb.append(str);
                sb.append("        ) {\n");
                sb.append(str);
                sb.append("          ");
                boolean z2 = !"void".equals(next2.returnType());
                if (z2) {
                    sb.append("return ");
                }
                if (value.containsKey(next2)) {
                    Iterator<MethodViewBinding> it5 = value.get(next2).iterator();
                    while (it5.hasNext()) {
                        MethodViewBinding next3 = it5.next();
                        sb.append("target.");
                        sb.append(next3.getName());
                        sb.append('(');
                        List<Parameter> parameters = next3.getParameters();
                        String[] strArrParameters2 = next2.parameters();
                        int size = parameters.size();
                        int i3 = 0;
                        while (i3 < size) {
                            Iterator<Map.Entry<ListenerClass, Map<ListenerMethod, Set<MethodViewBinding>>>> it6 = it3;
                            Parameter parameter = parameters.get(i3);
                            Map<ListenerMethod, Set<MethodViewBinding>> map2 = value;
                            int listenerPosition = parameter.getListenerPosition();
                            Iterator<ListenerMethod> it7 = it4;
                            if (parameter.requiresCast(strArrParameters2[listenerPosition])) {
                                sb.append("finder.<");
                                sb.append(parameter.getType());
                                sb.append(">castParam(p");
                                sb.append(listenerPosition);
                                sb.append(", \"");
                                sb.append(next2.name());
                                sb.append("\", ");
                                sb.append(listenerPosition);
                                sb.append(", \"");
                                sb.append(next3.getName());
                                sb.append("\", ");
                                sb.append(i3);
                                sb.append(")");
                            } else {
                                sb.append('p');
                                sb.append(listenerPosition);
                            }
                            if (i3 < size - 1) {
                                sb.append(", ");
                            }
                            i3++;
                            value = map2;
                            it3 = it6;
                            it4 = it7;
                        }
                        Iterator<Map.Entry<ListenerClass, Map<ListenerMethod, Set<MethodViewBinding>>>> it8 = it3;
                        Map<ListenerMethod, Set<MethodViewBinding>> map3 = value;
                        Iterator<ListenerMethod> it9 = it4;
                        sb.append(");");
                        if (it5.hasNext()) {
                            sb.append("\n");
                            sb.append("          ");
                        }
                        value = map3;
                        it3 = it8;
                        it4 = it9;
                    }
                    it = it3;
                    map = value;
                    it2 = it4;
                } else {
                    it = it3;
                    map = value;
                    it2 = it4;
                    if (z2) {
                        sb.append(next2.defaultReturn());
                        sb.append(';');
                    }
                }
                sb.append('\n');
                sb.append(str);
                sb.append("        }\n");
                value = map;
                it3 = it;
                it4 = it2;
            }
            sb.append(str);
            sb.append("      });\n");
            it3 = it3;
        }
        if (zIsEmpty) {
            sb.append("    }\n");
        }
    }

    private void emitUnbindMethod(StringBuilder sb) {
        sb.append("  @Override public void unbind(T target) {\n");
        if (this.parentViewBinder != null) {
            sb.append("    super.unbind(target);\n\n");
        }
        Iterator<ViewBindings> it = this.viewIdMap.values().iterator();
        while (it.hasNext()) {
            for (FieldViewBinding fieldViewBinding : it.next().getFieldBindings()) {
                sb.append("    target.");
                sb.append(fieldViewBinding.getName());
                sb.append(" = null;\n");
            }
        }
        for (FieldCollectionViewBinding fieldCollectionViewBinding : this.collectionBindings.keySet()) {
            sb.append("    target.");
            sb.append(fieldCollectionViewBinding.getName());
            sb.append(" = null;\n");
        }
        sb.append("  }\n");
    }

    private void emitViewBindings(StringBuilder sb, ViewBindings viewBindings) {
        sb.append("    view = ");
        List<ViewBinding> requiredBindings = viewBindings.getRequiredBindings();
        if (requiredBindings.isEmpty()) {
            sb.append("finder.findOptionalView(source, ");
            sb.append(viewBindings.getId());
            sb.append(", null);\n");
        } else if (viewBindings.getId() == -1) {
            sb.append("target;\n");
        } else {
            sb.append("finder.findRequiredView(source, ");
            sb.append(viewBindings.getId());
            sb.append(", \"");
            emitHumanDescription(sb, requiredBindings);
            sb.append("\");\n");
        }
        emitFieldBindings(sb, viewBindings);
        emitMethodBindings(sb, viewBindings);
    }

    static List<ListenerMethod> getListenerMethods(ListenerClass listenerClass) {
        if (listenerClass.method().length == 1) {
            return Arrays.asList(listenerClass.method());
        }
        try {
            ArrayList arrayList = new ArrayList();
            Class<? extends Enum<?>> clsCallbacks = listenerClass.callbacks();
            for (Enum r6 : (Enum[]) clsCallbacks.getEnumConstants()) {
                ListenerMethod listenerMethod = (ListenerMethod) clsCallbacks.getField(r6.name()).getAnnotation(ListenerMethod.class);
                if (listenerMethod == null) {
                    throw new IllegalStateException(String.format("@%s's %s.%s missing @%s annotation.", clsCallbacks.getEnclosingClass().getSimpleName(), clsCallbacks.getSimpleName(), r6.name(), ListenerMethod.class.getSimpleName()));
                }
                arrayList.add(listenerMethod);
            }
            return arrayList;
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }

    private ViewBindings getOrCreateViewBindings(int i) {
        ViewBindings viewBindings = this.viewIdMap.get(Integer.valueOf(i));
        if (viewBindings != null) {
            return viewBindings;
        }
        ViewBindings viewBindings2 = new ViewBindings(i);
        this.viewIdMap.put(Integer.valueOf(i), viewBindings2);
        return viewBindings2;
    }

    void addField(int i, FieldViewBinding fieldViewBinding) {
        getOrCreateViewBindings(i).addFieldBinding(fieldViewBinding);
    }

    void addFieldCollection(int[] iArr, FieldCollectionViewBinding fieldCollectionViewBinding) {
        this.collectionBindings.put(fieldCollectionViewBinding, iArr);
    }

    boolean addMethod(int i, ListenerClass listenerClass, ListenerMethod listenerMethod, MethodViewBinding methodViewBinding) {
        ViewBindings orCreateViewBindings = getOrCreateViewBindings(i);
        if (orCreateViewBindings.hasMethodBinding(listenerClass, listenerMethod) && !"void".equals(listenerMethod.returnType())) {
            return false;
        }
        orCreateViewBindings.addMethodBinding(listenerClass, listenerMethod, methodViewBinding);
        return true;
    }

    void addResource(FieldResourceBinding fieldResourceBinding) {
        this.resourceBindings.add(fieldResourceBinding);
    }

    String brewJava() {
        StringBuilder sb = new StringBuilder();
        sb.append("// Generated code from Butter Knife. Do not modify!\n");
        sb.append("package ");
        sb.append(this.classPackage);
        sb.append(";\n\n");
        if (!this.resourceBindings.isEmpty()) {
            sb.append("import android.content.res.Resources;\n");
        }
        if (!this.viewIdMap.isEmpty() || !this.collectionBindings.isEmpty()) {
            sb.append("import android.view.View;\n");
        }
        sb.append("import butterknife.ButterKnife.Finder;\n");
        if (this.parentViewBinder == null) {
            sb.append("import butterknife.ButterKnife.ViewBinder;\n");
        }
        sb.append('\n');
        sb.append("public class ");
        sb.append(this.className);
        sb.append("<T extends ");
        sb.append(this.targetClass);
        sb.append(">");
        if (this.parentViewBinder != null) {
            sb.append(" extends ");
            sb.append(this.parentViewBinder);
            sb.append("<T>");
        } else {
            sb.append(" implements ViewBinder<T>");
        }
        sb.append(" {\n");
        emitBindMethod(sb);
        sb.append('\n');
        emitUnbindMethod(sb);
        sb.append("}\n");
        return sb.toString();
    }

    String getFqcn() {
        return this.classPackage + "." + this.className;
    }

    ViewBindings getViewBinding(int i) {
        return this.viewIdMap.get(Integer.valueOf(i));
    }

    void setParentViewBinder(String str) {
        this.parentViewBinder = str;
    }
}
