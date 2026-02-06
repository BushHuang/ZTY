package com.tencent.tinker.anno;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class AnnotationProcessor extends AbstractProcessor {
    private static final String APPLICATION_TEMPLATE_PATH = "/TinkerAnnoApplication.tmpl";
    private static final String SUFFIX = "$$DefaultLifeCycle";

    private void processDefaultLifeCycle(Set<? extends Element> set) throws IOException {
        Iterator<? extends Element> it = set.iterator();
        while (it.hasNext()) {
            TypeElement typeElement = (Element) it.next();
            DefaultLifeCycle defaultLifeCycle = (DefaultLifeCycle) typeElement.getAnnotation(DefaultLifeCycle.class);
            String string = typeElement.getQualifiedName().toString();
            String strSubstring = string.substring(0, string.lastIndexOf(46));
            String strSubstring2 = string.substring(string.lastIndexOf(46) + 1);
            String strApplication = defaultLifeCycle.application();
            if (strApplication.startsWith(".")) {
                strApplication = strSubstring + strApplication;
            }
            String strSubstring3 = strApplication.substring(0, strApplication.lastIndexOf(46));
            String strSubstring4 = strApplication.substring(strApplication.lastIndexOf(46) + 1);
            String strLoaderClass = defaultLifeCycle.loaderClass();
            if (strLoaderClass.startsWith(".")) {
                strLoaderClass = strSubstring + strLoaderClass;
            }
            String strReplaceAll = new Scanner(AnnotationProcessor.class.getResourceAsStream("/TinkerAnnoApplication.tmpl")).useDelimiter("\\A").next().replaceAll("%PACKAGE%", strSubstring3).replaceAll("%APPLICATION%", strSubstring4).replaceAll("%APPLICATION_LIFE_CYCLE%", strSubstring + "." + strSubstring2);
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(defaultLifeCycle.flags());
            String strReplaceAll2 = strReplaceAll.replaceAll("%TINKER_FLAGS%", sb.toString()).replaceAll("%TINKER_LOADER_CLASS%", "" + strLoaderClass).replaceAll("%TINKER_LOAD_VERIFY_FLAG%", "" + defaultLifeCycle.loadVerifyFlag());
            try {
                JavaFileObject javaFileObjectCreateSourceFile = this.processingEnv.getFiler().createSourceFile(strSubstring3 + "." + strSubstring4, new Element[0]);
                this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Creating " + javaFileObjectCreateSourceFile.toUri());
                Writer writerOpenWriter = javaFileObjectCreateSourceFile.openWriter();
                try {
                    PrintWriter printWriter = new PrintWriter(writerOpenWriter);
                    printWriter.print(strReplaceAll2);
                    printWriter.flush();
                    writerOpenWriter.close();
                } catch (Throwable th) {
                    writerOpenWriter.close();
                    throw th;
                }
            } catch (IOException e) {
                this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.toString());
            }
        }
    }

    public Set<String> getSupportedAnnotationTypes() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(DefaultLifeCycle.class.getName());
        return linkedHashSet;
    }

    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) throws IOException {
        processDefaultLifeCycle(roundEnvironment.getElementsAnnotatedWith(DefaultLifeCycle.class));
        return true;
    }
}
