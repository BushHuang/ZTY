package kshark.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kshark.GcRoot;
import kshark.LeakTraceReference;
import kshark.LibraryLeakReferenceMatcher;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0007\b\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Lkshark/internal/ReferencePathNode;", "", "()V", "objectId", "", "getObjectId", "()J", "ChildNode", "LibraryLeakNode", "RootNode", "Lkshark/internal/ReferencePathNode$RootNode;", "Lkshark/internal/ReferencePathNode$ChildNode;", "shark"}, k = 1, mv = {1, 1, 15})
public abstract class ReferencePathNode {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0010\u0011B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0006R\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0082\u0001\u0002\u0012\u0013¨\u0006\u0014"}, d2 = {"Lkshark/internal/ReferencePathNode$ChildNode;", "Lkshark/internal/ReferencePathNode;", "()V", "declaredClassName", "", "getDeclaredClassName", "()Ljava/lang/String;", "parent", "getParent", "()Lkshark/internal/ReferencePathNode;", "refFromParentName", "getRefFromParentName", "refFromParentType", "Lkshark/LeakTraceReference$ReferenceType;", "getRefFromParentType", "()Lkshark/LeakTraceReference$ReferenceType;", "LibraryLeakChildNode", "NormalNode", "Lkshark/internal/ReferencePathNode$ChildNode$LibraryLeakChildNode;", "Lkshark/internal/ReferencePathNode$ChildNode$NormalNode;", "shark"}, k = 1, mv = {1, 1, 15})
    public static abstract class ChildNode extends ReferencePathNode {

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u00012\u00020\u0002B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\n¢\u0006\u0002\u0010\u000eR\u0014\u0010\r\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lkshark/internal/ReferencePathNode$ChildNode$LibraryLeakChildNode;", "Lkshark/internal/ReferencePathNode$ChildNode;", "Lkshark/internal/ReferencePathNode$LibraryLeakNode;", "objectId", "", "parent", "Lkshark/internal/ReferencePathNode;", "refFromParentType", "Lkshark/LeakTraceReference$ReferenceType;", "refFromParentName", "", "matcher", "Lkshark/LibraryLeakReferenceMatcher;", "declaredClassName", "(JLkshark/internal/ReferencePathNode;Lkshark/LeakTraceReference$ReferenceType;Ljava/lang/String;Lkshark/LibraryLeakReferenceMatcher;Ljava/lang/String;)V", "getDeclaredClassName", "()Ljava/lang/String;", "getMatcher", "()Lkshark/LibraryLeakReferenceMatcher;", "getObjectId", "()J", "getParent", "()Lkshark/internal/ReferencePathNode;", "getRefFromParentName", "getRefFromParentType", "()Lkshark/LeakTraceReference$ReferenceType;", "shark"}, k = 1, mv = {1, 1, 15})
        public static final class LibraryLeakChildNode extends ChildNode implements LibraryLeakNode {
            private final String declaredClassName;
            private final LibraryLeakReferenceMatcher matcher;
            private final long objectId;
            private final ReferencePathNode parent;
            private final String refFromParentName;
            private final LeakTraceReference.ReferenceType refFromParentType;

            public LibraryLeakChildNode(long j, ReferencePathNode referencePathNode, LeakTraceReference.ReferenceType referenceType, String str, LibraryLeakReferenceMatcher libraryLeakReferenceMatcher, String str2) {
                super(null);
                Intrinsics.checkParameterIsNotNull(referencePathNode, "parent");
                Intrinsics.checkParameterIsNotNull(referenceType, "refFromParentType");
                Intrinsics.checkParameterIsNotNull(str, "refFromParentName");
                Intrinsics.checkParameterIsNotNull(libraryLeakReferenceMatcher, "matcher");
                Intrinsics.checkParameterIsNotNull(str2, "declaredClassName");
                this.objectId = j;
                this.parent = referencePathNode;
                this.refFromParentType = referenceType;
                this.refFromParentName = str;
                this.matcher = libraryLeakReferenceMatcher;
                this.declaredClassName = str2;
            }

            @Override
            public String getDeclaredClassName() {
                return this.declaredClassName;
            }

            @Override
            public LibraryLeakReferenceMatcher getMatcher() {
                return this.matcher;
            }

            @Override
            public long getObjectId() {
                return this.objectId;
            }

            @Override
            public ReferencePathNode getParent() {
                return this.parent;
            }

            @Override
            public String getRefFromParentName() {
                return this.refFromParentName;
            }

            @Override
            public LeakTraceReference.ReferenceType getRefFromParentType() {
                return this.refFromParentType;
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bR\u0014\u0010\n\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lkshark/internal/ReferencePathNode$ChildNode$NormalNode;", "Lkshark/internal/ReferencePathNode$ChildNode;", "objectId", "", "parent", "Lkshark/internal/ReferencePathNode;", "refFromParentType", "Lkshark/LeakTraceReference$ReferenceType;", "refFromParentName", "", "declaredClassName", "(JLkshark/internal/ReferencePathNode;Lkshark/LeakTraceReference$ReferenceType;Ljava/lang/String;Ljava/lang/String;)V", "getDeclaredClassName", "()Ljava/lang/String;", "getObjectId", "()J", "getParent", "()Lkshark/internal/ReferencePathNode;", "getRefFromParentName", "getRefFromParentType", "()Lkshark/LeakTraceReference$ReferenceType;", "shark"}, k = 1, mv = {1, 1, 15})
        public static final class NormalNode extends ChildNode {
            private final String declaredClassName;
            private final long objectId;
            private final ReferencePathNode parent;
            private final String refFromParentName;
            private final LeakTraceReference.ReferenceType refFromParentType;

            public NormalNode(long j, ReferencePathNode referencePathNode, LeakTraceReference.ReferenceType referenceType, String str, String str2) {
                super(null);
                Intrinsics.checkParameterIsNotNull(referencePathNode, "parent");
                Intrinsics.checkParameterIsNotNull(referenceType, "refFromParentType");
                Intrinsics.checkParameterIsNotNull(str, "refFromParentName");
                Intrinsics.checkParameterIsNotNull(str2, "declaredClassName");
                this.objectId = j;
                this.parent = referencePathNode;
                this.refFromParentType = referenceType;
                this.refFromParentName = str;
                this.declaredClassName = str2;
            }

            @Override
            public String getDeclaredClassName() {
                return this.declaredClassName;
            }

            @Override
            public long getObjectId() {
                return this.objectId;
            }

            @Override
            public ReferencePathNode getParent() {
                return this.parent;
            }

            @Override
            public String getRefFromParentName() {
                return this.refFromParentName;
            }

            @Override
            public LeakTraceReference.ReferenceType getRefFromParentType() {
                return this.refFromParentType;
            }
        }

        private ChildNode() {
            super(null);
        }

        public ChildNode(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public abstract String getDeclaredClassName();

        public abstract ReferencePathNode getParent();

        public abstract String getRefFromParentName();

        public abstract LeakTraceReference.ReferenceType getRefFromParentType();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkshark/internal/ReferencePathNode$LibraryLeakNode;", "", "matcher", "Lkshark/LibraryLeakReferenceMatcher;", "getMatcher", "()Lkshark/LibraryLeakReferenceMatcher;", "shark"}, k = 1, mv = {1, 1, 15})
    public interface LibraryLeakNode {
        LibraryLeakReferenceMatcher getMatcher();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\t\n¨\u0006\u000b"}, d2 = {"Lkshark/internal/ReferencePathNode$RootNode;", "Lkshark/internal/ReferencePathNode;", "()V", "gcRoot", "Lkshark/GcRoot;", "getGcRoot", "()Lkshark/GcRoot;", "LibraryLeakRootNode", "NormalRootNode", "Lkshark/internal/ReferencePathNode$RootNode$LibraryLeakRootNode;", "Lkshark/internal/ReferencePathNode$RootNode$NormalRootNode;", "shark"}, k = 1, mv = {1, 1, 15})
    public static abstract class RootNode extends ReferencePathNode {

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lkshark/internal/ReferencePathNode$RootNode$LibraryLeakRootNode;", "Lkshark/internal/ReferencePathNode$RootNode;", "Lkshark/internal/ReferencePathNode$LibraryLeakNode;", "objectId", "", "gcRoot", "Lkshark/GcRoot;", "matcher", "Lkshark/LibraryLeakReferenceMatcher;", "(JLkshark/GcRoot;Lkshark/LibraryLeakReferenceMatcher;)V", "getGcRoot", "()Lkshark/GcRoot;", "getMatcher", "()Lkshark/LibraryLeakReferenceMatcher;", "getObjectId", "()J", "shark"}, k = 1, mv = {1, 1, 15})
        public static final class LibraryLeakRootNode extends RootNode implements LibraryLeakNode {
            private final GcRoot gcRoot;
            private final LibraryLeakReferenceMatcher matcher;
            private final long objectId;

            public LibraryLeakRootNode(long j, GcRoot gcRoot, LibraryLeakReferenceMatcher libraryLeakReferenceMatcher) {
                super(null);
                Intrinsics.checkParameterIsNotNull(gcRoot, "gcRoot");
                Intrinsics.checkParameterIsNotNull(libraryLeakReferenceMatcher, "matcher");
                this.objectId = j;
                this.gcRoot = gcRoot;
                this.matcher = libraryLeakReferenceMatcher;
            }

            @Override
            public GcRoot getGcRoot() {
                return this.gcRoot;
            }

            @Override
            public LibraryLeakReferenceMatcher getMatcher() {
                return this.matcher;
            }

            @Override
            public long getObjectId() {
                return this.objectId;
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lkshark/internal/ReferencePathNode$RootNode$NormalRootNode;", "Lkshark/internal/ReferencePathNode$RootNode;", "objectId", "", "gcRoot", "Lkshark/GcRoot;", "(JLkshark/GcRoot;)V", "getGcRoot", "()Lkshark/GcRoot;", "getObjectId", "()J", "shark"}, k = 1, mv = {1, 1, 15})
        public static final class NormalRootNode extends RootNode {
            private final GcRoot gcRoot;
            private final long objectId;

            public NormalRootNode(long j, GcRoot gcRoot) {
                super(null);
                Intrinsics.checkParameterIsNotNull(gcRoot, "gcRoot");
                this.objectId = j;
                this.gcRoot = gcRoot;
            }

            @Override
            public GcRoot getGcRoot() {
                return this.gcRoot;
            }

            @Override
            public long getObjectId() {
                return this.objectId;
            }
        }

        private RootNode() {
            super(null);
        }

        public RootNode(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public abstract GcRoot getGcRoot();
    }

    private ReferencePathNode() {
    }

    public ReferencePathNode(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract long getObjectId();
}
