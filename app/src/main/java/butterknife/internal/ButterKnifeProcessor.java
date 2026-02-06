package butterknife.internal;

import butterknife.Bind;
import butterknife.BindBool;
import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindDrawable;
import butterknife.BindInt;
import butterknife.BindString;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import butterknife.OnItemSelected;
import butterknife.OnLongClick;
import butterknife.OnPageChange;
import butterknife.OnTextChanged;
import butterknife.OnTouch;
import butterknife.internal.FieldCollectionViewBinding;
import butterknife.internal.ListenerClass;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

public final class ButterKnifeProcessor extends AbstractProcessor {
    static final boolean $assertionsDisabled = false;
    public static final String ANDROID_PREFIX = "android.";
    private static final String COLOR_STATE_LIST_TYPE = "android.content.res.ColorStateList";
    private static final String DRAWABLE_TYPE = "android.graphics.drawable.Drawable";
    private static final String ITERABLE_TYPE = "java.lang.Iterable<?>";
    public static final String JAVA_PREFIX = "java.";
    private static final String NULLABLE_ANNOTATION_NAME = "Nullable";
    public static final String SUFFIX = "$$ViewBinder";
    static final String VIEW_TYPE = "android.view.View";
    private Elements elementUtils;
    private Filer filer;
    private Types typeUtils;
    private static final String LIST_TYPE = List.class.getCanonicalName();
    private static final List<Class<? extends Annotation>> LISTENERS = Arrays.asList(OnCheckedChanged.class, OnClick.class, OnEditorAction.class, OnFocusChange.class, OnItemClick.class, OnItemLongClick.class, OnItemSelected.class, OnLongClick.class, OnPageChange.class, OnTextChanged.class, OnTouch.class);

    private String doubleErasure(TypeMirror typeMirror) {
        String string = this.typeUtils.erasure(typeMirror).toString();
        int iIndexOf = string.indexOf(60);
        return iIndexOf != -1 ? string.substring(0, iIndexOf) : string;
    }

    private void error(Element element, String str, Object... objArr) {
        if (objArr.length > 0) {
            str = String.format(str, objArr);
        }
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, str, element);
    }

    private void findAndParseListener(RoundEnvironment roundEnvironment, Class<? extends Annotation> cls, Map<TypeElement, BindingClass> map, Set<String> set) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(cls)) {
            try {
                parseListenerAnnotation(cls, element, map, set);
            } catch (Exception e) {
                StringWriter stringWriter = new StringWriter();
                e.printStackTrace(new PrintWriter(stringWriter));
                error(element, "Unable to generate view binder for @%s.\n\n%s", cls.getSimpleName(), stringWriter.toString());
            }
        }
    }

    private Map<TypeElement, BindingClass> findAndParseTargets(RoundEnvironment roundEnvironment) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Bind.class)) {
            try {
                parseBind(element, linkedHashMap, linkedHashSet);
            } catch (Exception e) {
                logParsingError(element, Bind.class, e);
            }
        }
        Iterator<Class<? extends Annotation>> it = LISTENERS.iterator();
        while (it.hasNext()) {
            findAndParseListener(roundEnvironment, it.next(), linkedHashMap, linkedHashSet);
        }
        for (Element element2 : roundEnvironment.getElementsAnnotatedWith(BindBool.class)) {
            try {
                parseResourceBool(element2, linkedHashMap, linkedHashSet);
            } catch (Exception e2) {
                logParsingError(element2, BindBool.class, e2);
            }
        }
        for (Element element3 : roundEnvironment.getElementsAnnotatedWith(BindColor.class)) {
            try {
                parseResourceColor(element3, linkedHashMap, linkedHashSet);
            } catch (Exception e3) {
                logParsingError(element3, BindColor.class, e3);
            }
        }
        for (Element element4 : roundEnvironment.getElementsAnnotatedWith(BindDimen.class)) {
            try {
                parseResourceDimen(element4, linkedHashMap, linkedHashSet);
            } catch (Exception e4) {
                logParsingError(element4, BindDimen.class, e4);
            }
        }
        for (Element element5 : roundEnvironment.getElementsAnnotatedWith(BindDrawable.class)) {
            try {
                parseResourceDrawable(element5, linkedHashMap, linkedHashSet);
            } catch (Exception e5) {
                logParsingError(element5, BindDrawable.class, e5);
            }
        }
        for (Element element6 : roundEnvironment.getElementsAnnotatedWith(BindInt.class)) {
            try {
                parseResourceInt(element6, linkedHashMap, linkedHashSet);
            } catch (Exception e6) {
                logParsingError(element6, BindInt.class, e6);
            }
        }
        for (Element element7 : roundEnvironment.getElementsAnnotatedWith(BindString.class)) {
            try {
                parseResourceString(element7, linkedHashMap, linkedHashSet);
            } catch (Exception e7) {
                logParsingError(element7, BindString.class, e7);
            }
        }
        for (Map.Entry<TypeElement, BindingClass> entry : linkedHashMap.entrySet()) {
            String strFindParentFqcn = findParentFqcn(entry.getKey(), linkedHashSet);
            if (strFindParentFqcn != null) {
                entry.getValue().setParentViewBinder(strFindParentFqcn + "$$ViewBinder");
            }
        }
        return linkedHashMap;
    }

    private static Integer findDuplicate(int[] iArr) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (int i : iArr) {
            if (!linkedHashSet.add(Integer.valueOf(i))) {
                return Integer.valueOf(i);
            }
        }
        return null;
    }

    private String findParentFqcn(TypeElement typeElement, Set<String> set) {
        do {
            DeclaredType superclass = typeElement.getSuperclass();
            if (superclass.getKind() == TypeKind.NONE) {
                return null;
            }
            typeElement = (TypeElement) superclass.asElement();
        } while (!set.contains(typeElement.toString()));
        String packageName = getPackageName(typeElement);
        return packageName + "." + getClassName(typeElement, packageName);
    }

    private static String getClassName(TypeElement typeElement, String str) {
        return typeElement.getQualifiedName().toString().substring(str.length() + 1).replace('.', '$');
    }

    private BindingClass getOrCreateTargetClass(Map<TypeElement, BindingClass> map, TypeElement typeElement) {
        BindingClass bindingClass = map.get(typeElement);
        if (bindingClass != null) {
            return bindingClass;
        }
        String string = typeElement.getQualifiedName().toString();
        String packageName = getPackageName(typeElement);
        BindingClass bindingClass2 = new BindingClass(packageName, getClassName(typeElement, packageName) + "$$ViewBinder", string);
        map.put(typeElement, bindingClass2);
        return bindingClass2;
    }

    private String getPackageName(TypeElement typeElement) {
        return this.elementUtils.getPackageOf(typeElement).getQualifiedName().toString();
    }

    private static boolean hasAnnotationWithName(Element element, String str) {
        Iterator it = element.getAnnotationMirrors().iterator();
        while (it.hasNext()) {
            if (str.equals(((AnnotationMirror) it.next()).getAnnotationType().asElement().getSimpleName().toString())) {
                return true;
            }
        }
        return false;
    }

    private boolean isBindingInWrongPackage(Class<? extends Annotation> cls, Element element) {
        String string = element.getEnclosingElement().getQualifiedName().toString();
        if (string.startsWith("android.")) {
            error(element, "@%s-annotated class incorrectly in Android framework package. (%s)", cls.getSimpleName(), string);
            return true;
        }
        if (!string.startsWith("java.")) {
            return false;
        }
        error(element, "@%s-annotated class incorrectly in Java framework package. (%s)", cls.getSimpleName(), string);
        return true;
    }

    private boolean isInaccessibleViaGeneratedCode(Class<? extends Annotation> cls, String str, Element element) {
        boolean z;
        TypeElement enclosingElement = element.getEnclosingElement();
        Set modifiers = element.getModifiers();
        if (modifiers.contains(Modifier.PRIVATE) || modifiers.contains(Modifier.STATIC)) {
            error(element, "@%s %s must not be private or static. (%s.%s)", cls.getSimpleName(), str, enclosingElement.getQualifiedName(), element.getSimpleName());
            z = true;
        } else {
            z = false;
        }
        if (enclosingElement.getKind() != ElementKind.CLASS) {
            error(enclosingElement, "@%s %s may only be contained in classes. (%s.%s)", cls.getSimpleName(), str, enclosingElement.getQualifiedName(), element.getSimpleName());
            z = true;
        }
        if (!enclosingElement.getModifiers().contains(Modifier.PRIVATE)) {
            return z;
        }
        error(enclosingElement, "@%s %s may not be contained in private classes. (%s.%s)", cls.getSimpleName(), str, enclosingElement.getQualifiedName(), element.getSimpleName());
        return true;
    }

    private boolean isInterface(TypeMirror typeMirror) {
        return (typeMirror instanceof DeclaredType) && ((DeclaredType) typeMirror).asElement().getKind() == ElementKind.INTERFACE;
    }

    private static boolean isRequiredBinding(Element element) {
        return !hasAnnotationWithName(element, "Nullable");
    }

    private boolean isSubtypeOfType(TypeMirror typeMirror, String str) {
        if (str.equals(typeMirror.toString())) {
            return true;
        }
        if (typeMirror.getKind() != TypeKind.DECLARED) {
            return false;
        }
        DeclaredType declaredType = (DeclaredType) typeMirror;
        List typeArguments = declaredType.getTypeArguments();
        if (typeArguments.size() > 0) {
            StringBuilder sb = new StringBuilder(declaredType.asElement().toString());
            sb.append('<');
            for (int i = 0; i < typeArguments.size(); i++) {
                if (i > 0) {
                    sb.append(',');
                }
                sb.append('?');
            }
            sb.append('>');
            if (sb.toString().equals(str)) {
                return true;
            }
        }
        TypeElement typeElementAsElement = declaredType.asElement();
        if (!(typeElementAsElement instanceof TypeElement)) {
            return false;
        }
        TypeElement typeElement = typeElementAsElement;
        if (isSubtypeOfType(typeElement.getSuperclass(), str)) {
            return true;
        }
        Iterator it = typeElement.getInterfaces().iterator();
        while (it.hasNext()) {
            if (isSubtypeOfType((TypeMirror) it.next(), str)) {
                return true;
            }
        }
        return false;
    }

    private void logParsingError(Element element, Class<? extends Annotation> cls, Exception exc) {
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        error(element, "Unable to parse @%s binding.\n\n%s", cls.getSimpleName(), stringWriter);
    }

    private void parseBind(Element element, Map<TypeElement, BindingClass> map, Set<String> set) {
        if (isInaccessibleViaGeneratedCode(Bind.class, "fields", element) || isBindingInWrongPackage(Bind.class, element)) {
            return;
        }
        TypeMirror typeMirrorAsType = element.asType();
        if (typeMirrorAsType.getKind() == TypeKind.ARRAY) {
            parseBindMany(element, map, set);
            return;
        }
        if (LIST_TYPE.equals(doubleErasure(typeMirrorAsType))) {
            parseBindMany(element, map, set);
        } else if (isSubtypeOfType(typeMirrorAsType, "java.lang.Iterable<?>")) {
            error(element, "@%s must be a List or array. (%s.%s)", Bind.class.getSimpleName(), element.getEnclosingElement().getQualifiedName(), element.getSimpleName());
        } else {
            parseBindOne(element, map, set);
        }
    }

    private void parseBindMany(Element element, Map<TypeElement, BindingClass> map, Set<String> set) {
        TypeMirror upperBound;
        boolean z;
        FieldCollectionViewBinding.Kind kind;
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        ArrayType arrayTypeAsType = element.asType();
        String strDoubleErasure = doubleErasure(arrayTypeAsType);
        if (arrayTypeAsType.getKind() == TypeKind.ARRAY) {
            upperBound = arrayTypeAsType.getComponentType();
            kind = FieldCollectionViewBinding.Kind.ARRAY;
            z = false;
        } else {
            if (!LIST_TYPE.equals(strDoubleErasure)) {
                throw new AssertionError();
            }
            List typeArguments = ((DeclaredType) arrayTypeAsType).getTypeArguments();
            if (typeArguments.size() != 1) {
                error(element, "@%s List must have a generic component. (%s.%s)", Bind.class.getSimpleName(), typeElement.getQualifiedName(), element.getSimpleName());
                upperBound = null;
                z = true;
            } else {
                upperBound = (TypeMirror) typeArguments.get(0);
                z = false;
            }
            kind = FieldCollectionViewBinding.Kind.LIST;
        }
        if (upperBound != null && upperBound.getKind() == TypeKind.TYPEVAR) {
            upperBound = ((TypeVariable) upperBound).getUpperBound();
        }
        if (upperBound != null && !isSubtypeOfType(upperBound, "android.view.View") && !isInterface(upperBound)) {
            error(element, "@%s List or array type must extend from View or be an interface. (%s.%s)", Bind.class.getSimpleName(), typeElement.getQualifiedName(), element.getSimpleName());
            z = true;
        }
        if (z) {
            return;
        }
        String string = element.getSimpleName().toString();
        int[] iArrValue = ((Bind) element.getAnnotation(Bind.class)).value();
        if (iArrValue.length == 0) {
            error(element, "@%s must specify at least one ID. (%s.%s)", Bind.class.getSimpleName(), typeElement.getQualifiedName(), element.getSimpleName());
            return;
        }
        Integer numFindDuplicate = findDuplicate(iArrValue);
        if (numFindDuplicate != null) {
            error(element, "@%s annotation contains duplicate ID %d. (%s.%s)", Bind.class.getSimpleName(), numFindDuplicate, typeElement.getQualifiedName(), element.getSimpleName());
        }
        getOrCreateTargetClass(map, typeElement).addFieldCollection(iArrValue, new FieldCollectionViewBinding(string, upperBound.toString(), kind, isRequiredBinding(element)));
        set.add(typeElement.toString());
    }

    private void parseBindOne(Element element, Map<TypeElement, BindingClass> map, Set<String> set) {
        boolean z;
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        TypeMirror typeMirrorAsType = element.asType();
        if (typeMirrorAsType.getKind() == TypeKind.TYPEVAR) {
            typeMirrorAsType = ((TypeVariable) typeMirrorAsType).getUpperBound();
        }
        if (isSubtypeOfType(typeMirrorAsType, "android.view.View") || isInterface(typeMirrorAsType)) {
            z = false;
        } else {
            error(element, "@%s fields must extend from View or be an interface. (%s.%s)", Bind.class.getSimpleName(), typeElement.getQualifiedName(), element.getSimpleName());
            z = true;
        }
        int[] iArrValue = ((Bind) element.getAnnotation(Bind.class)).value();
        if (iArrValue.length != 1) {
            error(element, "@%s for a view must only specify one ID. Found: %s. (%s.%s)", Bind.class.getSimpleName(), Arrays.toString(iArrValue), typeElement.getQualifiedName(), element.getSimpleName());
            z = true;
        }
        if (z) {
            return;
        }
        int i = iArrValue[0];
        BindingClass orCreateTargetClass = map.get(typeElement);
        if (orCreateTargetClass != null) {
            ViewBindings viewBinding = orCreateTargetClass.getViewBinding(i);
            if (viewBinding != null) {
                Iterator<FieldViewBinding> it = viewBinding.getFieldBindings().iterator();
                if (it.hasNext()) {
                    error(element, "Attempt to use @%s for an already bound ID %d on '%s'. (%s.%s)", Bind.class.getSimpleName(), Integer.valueOf(i), it.next().getName(), typeElement.getQualifiedName(), element.getSimpleName());
                    return;
                }
            }
        } else {
            orCreateTargetClass = getOrCreateTargetClass(map, typeElement);
        }
        orCreateTargetClass.addField(i, new FieldViewBinding(element.getSimpleName().toString(), typeMirrorAsType.toString(), isRequiredBinding(element)));
        set.add(typeElement.toString());
    }

    private void parseListenerAnnotation(Class<? extends Annotation> cls, Element element, Map<TypeElement, BindingClass> map, Set<String> set) throws Exception {
        ListenerMethod listenerMethod;
        ListenerClass listenerClass;
        if (!(element instanceof ExecutableElement) || element.getKind() != ElementKind.METHOD) {
            throw new IllegalStateException(String.format("@%s annotation must be on a method.", cls.getSimpleName()));
        }
        ExecutableElement executableElement = (ExecutableElement) element;
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        Annotation annotation = element.getAnnotation(cls);
        Method declaredMethod = cls.getDeclaredMethod("value", new Class[0]);
        if (declaredMethod.getReturnType() != int[].class) {
            throw new IllegalStateException(String.format("@%s annotation value() type not int[].", cls));
        }
        int[] iArr = (int[]) declaredMethod.invoke(annotation, new Object[0]);
        String string = executableElement.getSimpleName().toString();
        boolean zIsRequiredBinding = isRequiredBinding(element);
        boolean zIsInaccessibleViaGeneratedCode = isInaccessibleViaGeneratedCode(cls, "methods", element) | isBindingInWrongPackage(cls, element);
        Integer numFindDuplicate = findDuplicate(iArr);
        if (numFindDuplicate != null) {
            error(element, "@%s annotation for method contains duplicate ID %d. (%s.%s)", cls.getSimpleName(), numFindDuplicate, typeElement.getQualifiedName(), element.getSimpleName());
            zIsInaccessibleViaGeneratedCode = true;
        }
        ListenerClass listenerClass2 = (ListenerClass) cls.getAnnotation(ListenerClass.class);
        if (listenerClass2 == null) {
            throw new IllegalStateException(String.format("No @%s defined on @%s.", ListenerClass.class.getSimpleName(), cls.getSimpleName()));
        }
        for (int i : iArr) {
            if (i == -1) {
                if (iArr.length == 1) {
                    if (!zIsRequiredBinding) {
                        error(element, "ID-free binding must not be annotated with @Nullable. (%s.%s)", typeElement.getQualifiedName(), element.getSimpleName());
                        zIsInaccessibleViaGeneratedCode = true;
                    }
                    String strTargetType = listenerClass2.targetType();
                    if (!isSubtypeOfType(typeElement.asType(), strTargetType) && !isInterface(typeElement.asType())) {
                        error(element, "@%s annotation without an ID may only be used with an object of type \"%s\" or an interface. (%s.%s)", cls.getSimpleName(), strTargetType, typeElement.getQualifiedName(), element.getSimpleName());
                    }
                } else {
                    error(element, "@%s annotation contains invalid ID %d. (%s.%s)", cls.getSimpleName(), Integer.valueOf(i), typeElement.getQualifiedName(), element.getSimpleName());
                }
                zIsInaccessibleViaGeneratedCode = true;
            }
        }
        ListenerMethod[] listenerMethodArrMethod = listenerClass2.method();
        if (listenerMethodArrMethod.length > 1) {
            throw new IllegalStateException(String.format("Multiple listener methods specified on @%s.", cls.getSimpleName()));
        }
        if (listenerMethodArrMethod.length != 1) {
            Enum r4 = (Enum) cls.getDeclaredMethod("callback", new Class[0]).invoke(annotation, new Object[0]);
            ListenerMethod listenerMethod2 = (ListenerMethod) r4.getDeclaringClass().getField(r4.name()).getAnnotation(ListenerMethod.class);
            if (listenerMethod2 == null) {
                throw new IllegalStateException(String.format("No @%s defined on @%s's %s.%s.", ListenerMethod.class.getSimpleName(), cls.getSimpleName(), r4.getDeclaringClass().getSimpleName(), r4.name()));
            }
            listenerMethod = listenerMethod2;
        } else {
            if (listenerClass2.callbacks() != ListenerClass.NONE.class) {
                throw new IllegalStateException(String.format("Both method() and callback() defined on @%s.", cls.getSimpleName()));
            }
            listenerMethod = listenerMethodArrMethod[0];
        }
        List parameters = executableElement.getParameters();
        if (parameters.size() > listenerMethod.parameters().length) {
            error(element, "@%s methods can have at most %s parameter(s). (%s.%s)", cls.getSimpleName(), Integer.valueOf(listenerMethod.parameters().length), typeElement.getQualifiedName(), element.getSimpleName());
            zIsInaccessibleViaGeneratedCode = true;
        }
        TypeMirror returnType = executableElement.getReturnType();
        if (returnType instanceof TypeVariable) {
            returnType = ((TypeVariable) returnType).getUpperBound();
        }
        if (!returnType.toString().equals(listenerMethod.returnType())) {
            error(element, "@%s methods must have a '%s' return type. (%s.%s)", cls.getSimpleName(), listenerMethod.returnType(), typeElement.getQualifiedName(), element.getSimpleName());
            zIsInaccessibleViaGeneratedCode = true;
        }
        if (zIsInaccessibleViaGeneratedCode) {
            return;
        }
        Parameter[] parameterArr = Parameter.NONE;
        if (parameters.isEmpty()) {
            listenerClass = listenerClass2;
        } else {
            int size = parameters.size();
            Parameter[] parameterArr2 = new Parameter[size];
            BitSet bitSet = new BitSet(parameters.size());
            String[] strArrParameters = listenerMethod.parameters();
            int i2 = 0;
            while (i2 < parameters.size()) {
                TypeMirror typeMirrorAsType = ((VariableElement) parameters.get(i2)).asType();
                if (typeMirrorAsType instanceof TypeVariable) {
                    typeMirrorAsType = ((TypeVariable) typeMirrorAsType).getUpperBound();
                }
                ListenerClass listenerClass3 = listenerClass2;
                for (int i3 = 0; i3 < strArrParameters.length; i3++) {
                    if (!bitSet.get(i3) && (isSubtypeOfType(typeMirrorAsType, strArrParameters[i3]) || isInterface(typeMirrorAsType))) {
                        parameterArr2[i2] = new Parameter(i3, typeMirrorAsType.toString());
                        bitSet.set(i3);
                        break;
                    }
                }
                if (parameterArr2[i2] == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unable to match @");
                    sb.append(cls.getSimpleName());
                    sb.append(" method arguments. (");
                    sb.append((CharSequence) typeElement.getQualifiedName());
                    sb.append('.');
                    sb.append((CharSequence) element.getSimpleName());
                    sb.append(')');
                    int i4 = 0;
                    while (i4 < size) {
                        Parameter parameter = parameterArr2[i4];
                        sb.append("\n\n  Parameter #");
                        int i5 = i4 + 1;
                        sb.append(i5);
                        sb.append(": ");
                        sb.append(((VariableElement) parameters.get(i4)).asType().toString());
                        sb.append("\n    ");
                        if (parameter == null) {
                            sb.append("did not match any listener parameters");
                        } else {
                            sb.append("matched listener parameter #");
                            sb.append(parameter.getListenerPosition() + 1);
                            sb.append(": ");
                            sb.append(parameter.getType());
                        }
                        i4 = i5;
                    }
                    sb.append("\n\nMethods may have up to ");
                    sb.append(listenerMethod.parameters().length);
                    sb.append(" parameter(s):\n");
                    for (String str : listenerMethod.parameters()) {
                        sb.append("\n  ");
                        sb.append(str);
                    }
                    sb.append("\n\nThese may be listed in any order but will be searched for from top to bottom.");
                    error(executableElement, sb.toString(), new Object[0]);
                    return;
                }
                i2++;
                listenerClass2 = listenerClass3;
            }
            listenerClass = listenerClass2;
            parameterArr = parameterArr2;
        }
        MethodViewBinding methodViewBinding = new MethodViewBinding(string, Arrays.asList(parameterArr), zIsRequiredBinding);
        BindingClass orCreateTargetClass = getOrCreateTargetClass(map, typeElement);
        int length = iArr.length;
        int i6 = 0;
        while (i6 < length) {
            int i7 = iArr[i6];
            ListenerClass listenerClass4 = listenerClass;
            if (!orCreateTargetClass.addMethod(i7, listenerClass4, listenerMethod, methodViewBinding)) {
                error(element, "Multiple listener methods with return value specified for ID %d. (%s.%s)", Integer.valueOf(i7), typeElement.getQualifiedName(), element.getSimpleName());
                return;
            } else {
                i6++;
                listenerClass = listenerClass4;
            }
        }
        set.add(typeElement.toString());
    }

    private void parseResourceBool(Element element, Map<TypeElement, BindingClass> map, Set<String> set) {
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        boolean z = true;
        if (element.asType().getKind() != TypeKind.BOOLEAN) {
            error(element, "@%s field type must be 'boolean'. (%s.%s)", BindBool.class.getSimpleName(), typeElement.getQualifiedName(), element.getSimpleName());
        } else {
            z = false;
        }
        if ((isInaccessibleViaGeneratedCode(BindBool.class, "fields", element) | z) || isBindingInWrongPackage(BindBool.class, element)) {
            return;
        }
        getOrCreateTargetClass(map, typeElement).addResource(new FieldResourceBinding(((BindBool) element.getAnnotation(BindBool.class)).value(), element.getSimpleName().toString(), "getBoolean"));
        set.add(typeElement.toString());
    }

    private void parseResourceColor(Element element, Map<TypeElement, BindingClass> map, Set<String> set) {
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        TypeMirror typeMirrorAsType = element.asType();
        boolean z = true;
        boolean z2 = false;
        if ("android.content.res.ColorStateList".equals(typeMirrorAsType.toString())) {
            z = false;
            z2 = true;
        } else if (typeMirrorAsType.getKind() != TypeKind.INT) {
            error(element, "@%s field type must be 'int' or 'ColorStateList'. (%s.%s)", BindColor.class.getSimpleName(), typeElement.getQualifiedName(), element.getSimpleName());
        } else {
            z = false;
        }
        if ((isInaccessibleViaGeneratedCode(BindColor.class, "fields", element) | z) || isBindingInWrongPackage(BindColor.class, element)) {
            return;
        }
        String string = element.getSimpleName().toString();
        getOrCreateTargetClass(map, typeElement).addResource(new FieldResourceBinding(((BindColor) element.getAnnotation(BindColor.class)).value(), string, z2 ? "getColorStateList" : "getColor"));
        set.add(typeElement.toString());
    }

    private void parseResourceDimen(Element element, Map<TypeElement, BindingClass> map, Set<String> set) {
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        TypeMirror typeMirrorAsType = element.asType();
        boolean z = true;
        boolean z2 = false;
        if (typeMirrorAsType.getKind() == TypeKind.INT) {
            z = false;
            z2 = true;
        } else if (typeMirrorAsType.getKind() != TypeKind.FLOAT) {
            error(element, "@%s field type must be 'int' or 'float'. (%s.%s)", BindDimen.class.getSimpleName(), typeElement.getQualifiedName(), element.getSimpleName());
        } else {
            z = false;
        }
        if ((isInaccessibleViaGeneratedCode(BindDimen.class, "fields", element) | z) || isBindingInWrongPackage(BindDimen.class, element)) {
            return;
        }
        String string = element.getSimpleName().toString();
        getOrCreateTargetClass(map, typeElement).addResource(new FieldResourceBinding(((BindDimen) element.getAnnotation(BindDimen.class)).value(), string, z2 ? "getDimensionPixelSize" : "getDimension"));
        set.add(typeElement.toString());
    }

    private void parseResourceDrawable(Element element, Map<TypeElement, BindingClass> map, Set<String> set) {
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        boolean z = true;
        if ("android.graphics.drawable.Drawable".equals(element.asType().toString())) {
            z = false;
        } else {
            error(element, "@%s field type must be 'Drawable'. (%s.%s)", BindDrawable.class.getSimpleName(), typeElement.getQualifiedName(), element.getSimpleName());
        }
        if ((isInaccessibleViaGeneratedCode(BindDrawable.class, "fields", element) | z) || isBindingInWrongPackage(BindDrawable.class, element)) {
            return;
        }
        getOrCreateTargetClass(map, typeElement).addResource(new FieldResourceBinding(((BindDrawable) element.getAnnotation(BindDrawable.class)).value(), element.getSimpleName().toString(), "getDrawable"));
        set.add(typeElement.toString());
    }

    private void parseResourceInt(Element element, Map<TypeElement, BindingClass> map, Set<String> set) {
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        boolean z = true;
        if (element.asType().getKind() != TypeKind.INT) {
            error(element, "@%s field type must be 'int'. (%s.%s)", BindInt.class.getSimpleName(), typeElement.getQualifiedName(), element.getSimpleName());
        } else {
            z = false;
        }
        if ((isInaccessibleViaGeneratedCode(BindInt.class, "fields", element) | z) || isBindingInWrongPackage(BindInt.class, element)) {
            return;
        }
        getOrCreateTargetClass(map, typeElement).addResource(new FieldResourceBinding(((BindInt) element.getAnnotation(BindInt.class)).value(), element.getSimpleName().toString(), "getInteger"));
        set.add(typeElement.toString());
    }

    private void parseResourceString(Element element, Map<TypeElement, BindingClass> map, Set<String> set) {
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        boolean z = true;
        if ("java.lang.String".equals(element.asType().toString())) {
            z = false;
        } else {
            error(element, "@%s field type must be 'String'. (%s.%s)", BindString.class.getSimpleName(), typeElement.getQualifiedName(), element.getSimpleName());
        }
        if ((isInaccessibleViaGeneratedCode(BindString.class, "fields", element) | z) || isBindingInWrongPackage(BindString.class, element)) {
            return;
        }
        getOrCreateTargetClass(map, typeElement).addResource(new FieldResourceBinding(((BindString) element.getAnnotation(BindString.class)).value(), element.getSimpleName().toString(), "getString"));
        set.add(typeElement.toString());
    }

    public Set<String> getSupportedAnnotationTypes() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(Bind.class.getCanonicalName());
        Iterator<Class<? extends Annotation>> it = LISTENERS.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(it.next().getCanonicalName());
        }
        linkedHashSet.add(BindBool.class.getCanonicalName());
        linkedHashSet.add(BindColor.class.getCanonicalName());
        linkedHashSet.add(BindDimen.class.getCanonicalName());
        linkedHashSet.add(BindDrawable.class.getCanonicalName());
        linkedHashSet.add(BindInt.class.getCanonicalName());
        linkedHashSet.add(BindString.class.getCanonicalName());
        return linkedHashSet;
    }

    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        this.elementUtils = processingEnvironment.getElementUtils();
        this.typeUtils = processingEnvironment.getTypeUtils();
        this.filer = processingEnvironment.getFiler();
    }

    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) throws IOException {
        for (Map.Entry<TypeElement, BindingClass> entry : findAndParseTargets(roundEnvironment).entrySet()) {
            Element element = (TypeElement) entry.getKey();
            BindingClass value = entry.getValue();
            try {
                Writer writerOpenWriter = this.filer.createSourceFile(value.getFqcn(), new Element[]{element}).openWriter();
                writerOpenWriter.write(value.brewJava());
                writerOpenWriter.flush();
                writerOpenWriter.close();
            } catch (IOException e) {
                error(element, "Unable to write view binder for type %s: %s", element, e.getMessage());
            }
        }
        return true;
    }
}
