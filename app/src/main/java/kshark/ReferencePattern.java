package kshark;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00032\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lkshark/ReferencePattern;", "Ljava/io/Serializable;", "()V", "Companion", "InstanceFieldPattern", "JavaLocalPattern", "NativeGlobalVariablePattern", "StaticFieldPattern", "Lkshark/ReferencePattern$JavaLocalPattern;", "Lkshark/ReferencePattern$StaticFieldPattern;", "Lkshark/ReferencePattern$InstanceFieldPattern;", "Lkshark/ReferencePattern$NativeGlobalVariablePattern;", "shark"}, k = 1, mv = {1, 1, 15})
public abstract class ReferencePattern implements Serializable {
    private static final long serialVersionUID = -5113635523713591133L;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\b\u0010\u0012\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0014"}, d2 = {"Lkshark/ReferencePattern$InstanceFieldPattern;", "Lkshark/ReferencePattern;", "className", "", "fieldName", "(Ljava/lang/String;Ljava/lang/String;)V", "getClassName", "()Ljava/lang/String;", "getFieldName", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class InstanceFieldPattern extends ReferencePattern {
        private static final long serialVersionUID = 6649791455204159802L;
        private final String className;
        private final String fieldName;

        public InstanceFieldPattern(String str, String str2) {
            super(null);
            Intrinsics.checkParameterIsNotNull(str, "className");
            Intrinsics.checkParameterIsNotNull(str2, "fieldName");
            this.className = str;
            this.fieldName = str2;
        }

        public static InstanceFieldPattern copy$default(InstanceFieldPattern instanceFieldPattern, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = instanceFieldPattern.className;
            }
            if ((i & 2) != 0) {
                str2 = instanceFieldPattern.fieldName;
            }
            return instanceFieldPattern.copy(str, str2);
        }

        public final String getClassName() {
            return this.className;
        }

        public final String getFieldName() {
            return this.fieldName;
        }

        public final InstanceFieldPattern copy(String className, String fieldName) {
            Intrinsics.checkParameterIsNotNull(className, "className");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            return new InstanceFieldPattern(className, fieldName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InstanceFieldPattern)) {
                return false;
            }
            InstanceFieldPattern instanceFieldPattern = (InstanceFieldPattern) other;
            return Intrinsics.areEqual(this.className, instanceFieldPattern.className) && Intrinsics.areEqual(this.fieldName, instanceFieldPattern.fieldName);
        }

        public final String getClassName() {
            return this.className;
        }

        public final String getFieldName() {
            return this.fieldName;
        }

        public int hashCode() {
            String str = this.className;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.fieldName;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "instance field " + this.className + '#' + this.fieldName;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lkshark/ReferencePattern$JavaLocalPattern;", "Lkshark/ReferencePattern;", "threadName", "", "(Ljava/lang/String;)V", "getThreadName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class JavaLocalPattern extends ReferencePattern {
        private static final long serialVersionUID = -8985446122829543654L;
        private final String threadName;

        public JavaLocalPattern(String str) {
            super(null);
            Intrinsics.checkParameterIsNotNull(str, "threadName");
            this.threadName = str;
        }

        public static JavaLocalPattern copy$default(JavaLocalPattern javaLocalPattern, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = javaLocalPattern.threadName;
            }
            return javaLocalPattern.copy(str);
        }

        public final String getThreadName() {
            return this.threadName;
        }

        public final JavaLocalPattern copy(String threadName) {
            Intrinsics.checkParameterIsNotNull(threadName, "threadName");
            return new JavaLocalPattern(threadName);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof JavaLocalPattern) && Intrinsics.areEqual(this.threadName, ((JavaLocalPattern) other).threadName);
            }
            return true;
        }

        public final String getThreadName() {
            return this.threadName;
        }

        public int hashCode() {
            String str = this.threadName;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "local variable on thread " + this.threadName;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lkshark/ReferencePattern$NativeGlobalVariablePattern;", "Lkshark/ReferencePattern;", "className", "", "(Ljava/lang/String;)V", "getClassName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class NativeGlobalVariablePattern extends ReferencePattern {
        private static final long serialVersionUID = -2651328076202244933L;
        private final String className;

        public NativeGlobalVariablePattern(String str) {
            super(null);
            Intrinsics.checkParameterIsNotNull(str, "className");
            this.className = str;
        }

        public static NativeGlobalVariablePattern copy$default(NativeGlobalVariablePattern nativeGlobalVariablePattern, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = nativeGlobalVariablePattern.className;
            }
            return nativeGlobalVariablePattern.copy(str);
        }

        public final String getClassName() {
            return this.className;
        }

        public final NativeGlobalVariablePattern copy(String className) {
            Intrinsics.checkParameterIsNotNull(className, "className");
            return new NativeGlobalVariablePattern(className);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof NativeGlobalVariablePattern) && Intrinsics.areEqual(this.className, ((NativeGlobalVariablePattern) other).className);
            }
            return true;
        }

        public final String getClassName() {
            return this.className;
        }

        public int hashCode() {
            String str = this.className;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "native global variable referencing " + this.className;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\b\u0010\u0012\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0014"}, d2 = {"Lkshark/ReferencePattern$StaticFieldPattern;", "Lkshark/ReferencePattern;", "className", "", "fieldName", "(Ljava/lang/String;Ljava/lang/String;)V", "getClassName", "()Ljava/lang/String;", "getFieldName", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class StaticFieldPattern extends ReferencePattern {
        private static final long serialVersionUID = 7656908128775899611L;
        private final String className;
        private final String fieldName;

        public StaticFieldPattern(String str, String str2) {
            super(null);
            Intrinsics.checkParameterIsNotNull(str, "className");
            Intrinsics.checkParameterIsNotNull(str2, "fieldName");
            this.className = str;
            this.fieldName = str2;
        }

        public static StaticFieldPattern copy$default(StaticFieldPattern staticFieldPattern, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = staticFieldPattern.className;
            }
            if ((i & 2) != 0) {
                str2 = staticFieldPattern.fieldName;
            }
            return staticFieldPattern.copy(str, str2);
        }

        public final String getClassName() {
            return this.className;
        }

        public final String getFieldName() {
            return this.fieldName;
        }

        public final StaticFieldPattern copy(String className, String fieldName) {
            Intrinsics.checkParameterIsNotNull(className, "className");
            Intrinsics.checkParameterIsNotNull(fieldName, "fieldName");
            return new StaticFieldPattern(className, fieldName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StaticFieldPattern)) {
                return false;
            }
            StaticFieldPattern staticFieldPattern = (StaticFieldPattern) other;
            return Intrinsics.areEqual(this.className, staticFieldPattern.className) && Intrinsics.areEqual(this.fieldName, staticFieldPattern.fieldName);
        }

        public final String getClassName() {
            return this.className;
        }

        public final String getFieldName() {
            return this.fieldName;
        }

        public int hashCode() {
            String str = this.className;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.fieldName;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "static field " + this.className + '#' + this.fieldName;
        }
    }

    private ReferencePattern() {
    }

    public ReferencePattern(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
