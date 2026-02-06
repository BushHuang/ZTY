package kshark;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0010\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0010\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&¨\u0006'"}, d2 = {"Lkshark/GcRoot;", "", "()V", "id", "", "getId", "()J", "Debugger", "Finalizing", "InternedString", "JavaFrame", "JniGlobal", "JniLocal", "JniMonitor", "MonitorUsed", "NativeStack", "ReferenceCleanup", "StickyClass", "ThreadBlock", "ThreadObject", "Unknown", "Unreachable", "VmInternal", "Lkshark/GcRoot$Unknown;", "Lkshark/GcRoot$JniGlobal;", "Lkshark/GcRoot$JniLocal;", "Lkshark/GcRoot$JavaFrame;", "Lkshark/GcRoot$NativeStack;", "Lkshark/GcRoot$StickyClass;", "Lkshark/GcRoot$ThreadBlock;", "Lkshark/GcRoot$MonitorUsed;", "Lkshark/GcRoot$ThreadObject;", "Lkshark/GcRoot$ReferenceCleanup;", "Lkshark/GcRoot$VmInternal;", "Lkshark/GcRoot$JniMonitor;", "Lkshark/GcRoot$InternedString;", "Lkshark/GcRoot$Finalizing;", "Lkshark/GcRoot$Debugger;", "Lkshark/GcRoot$Unreachable;", "shark"}, k = 1, mv = {1, 1, 15})
public abstract class GcRoot {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkshark/GcRoot$Debugger;", "Lkshark/GcRoot;", "id", "", "(J)V", "getId", "()J", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Debugger extends GcRoot {
        private final long id;

        public Debugger(long j) {
            super(null);
            this.id = j;
        }

        @Override
        public long getId() {
            return this.id;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkshark/GcRoot$Finalizing;", "Lkshark/GcRoot;", "id", "", "(J)V", "getId", "()J", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Finalizing extends GcRoot {
        private final long id;

        public Finalizing(long j) {
            super(null);
            this.id = j;
        }

        @Override
        public long getId() {
            return this.id;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkshark/GcRoot$InternedString;", "Lkshark/GcRoot;", "id", "", "(J)V", "getId", "()J", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class InternedString extends GcRoot {
        private final long id;

        public InternedString(long j) {
            super(null);
            this.id = j;
        }

        @Override
        public long getId() {
            return this.id;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lkshark/GcRoot$JavaFrame;", "Lkshark/GcRoot;", "id", "", "threadSerialNumber", "", "frameNumber", "(JII)V", "getFrameNumber", "()I", "getId", "()J", "getThreadSerialNumber", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class JavaFrame extends GcRoot {
        private final int frameNumber;
        private final long id;
        private final int threadSerialNumber;

        public JavaFrame(long j, int i, int i2) {
            super(null);
            this.id = j;
            this.threadSerialNumber = i;
            this.frameNumber = i2;
        }

        public final int getFrameNumber() {
            return this.frameNumber;
        }

        @Override
        public long getId() {
            return this.id;
        }

        public final int getThreadSerialNumber() {
            return this.threadSerialNumber;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lkshark/GcRoot$JniGlobal;", "Lkshark/GcRoot;", "id", "", "jniGlobalRefId", "(JJ)V", "getId", "()J", "getJniGlobalRefId", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class JniGlobal extends GcRoot {
        private final long id;
        private final long jniGlobalRefId;

        public JniGlobal(long j, long j2) {
            super(null);
            this.id = j;
            this.jniGlobalRefId = j2;
        }

        @Override
        public long getId() {
            return this.id;
        }

        public final long getJniGlobalRefId() {
            return this.jniGlobalRefId;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lkshark/GcRoot$JniLocal;", "Lkshark/GcRoot;", "id", "", "threadSerialNumber", "", "frameNumber", "(JII)V", "getFrameNumber", "()I", "getId", "()J", "getThreadSerialNumber", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class JniLocal extends GcRoot {
        private final int frameNumber;
        private final long id;
        private final int threadSerialNumber;

        public JniLocal(long j, int i, int i2) {
            super(null);
            this.id = j;
            this.threadSerialNumber = i;
            this.frameNumber = i2;
        }

        public final int getFrameNumber() {
            return this.frameNumber;
        }

        @Override
        public long getId() {
            return this.id;
        }

        public final int getThreadSerialNumber() {
            return this.threadSerialNumber;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Lkshark/GcRoot$JniMonitor;", "Lkshark/GcRoot;", "id", "", "stackTraceSerialNumber", "", "stackDepth", "(JII)V", "getId", "()J", "getStackDepth", "()I", "getStackTraceSerialNumber", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class JniMonitor extends GcRoot {
        private final long id;
        private final int stackDepth;
        private final int stackTraceSerialNumber;

        public JniMonitor(long j, int i, int i2) {
            super(null);
            this.id = j;
            this.stackTraceSerialNumber = i;
            this.stackDepth = i2;
        }

        @Override
        public long getId() {
            return this.id;
        }

        public final int getStackDepth() {
            return this.stackDepth;
        }

        public final int getStackTraceSerialNumber() {
            return this.stackTraceSerialNumber;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkshark/GcRoot$MonitorUsed;", "Lkshark/GcRoot;", "id", "", "(J)V", "getId", "()J", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class MonitorUsed extends GcRoot {
        private final long id;

        public MonitorUsed(long j) {
            super(null);
            this.id = j;
        }

        @Override
        public long getId() {
            return this.id;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lkshark/GcRoot$NativeStack;", "Lkshark/GcRoot;", "id", "", "threadSerialNumber", "", "(JI)V", "getId", "()J", "getThreadSerialNumber", "()I", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class NativeStack extends GcRoot {
        private final long id;
        private final int threadSerialNumber;

        public NativeStack(long j, int i) {
            super(null);
            this.id = j;
            this.threadSerialNumber = i;
        }

        @Override
        public long getId() {
            return this.id;
        }

        public final int getThreadSerialNumber() {
            return this.threadSerialNumber;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkshark/GcRoot$ReferenceCleanup;", "Lkshark/GcRoot;", "id", "", "(J)V", "getId", "()J", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class ReferenceCleanup extends GcRoot {
        private final long id;

        public ReferenceCleanup(long j) {
            super(null);
            this.id = j;
        }

        @Override
        public long getId() {
            return this.id;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkshark/GcRoot$StickyClass;", "Lkshark/GcRoot;", "id", "", "(J)V", "getId", "()J", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class StickyClass extends GcRoot {
        private final long id;

        public StickyClass(long j) {
            super(null);
            this.id = j;
        }

        @Override
        public long getId() {
            return this.id;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lkshark/GcRoot$ThreadBlock;", "Lkshark/GcRoot;", "id", "", "threadSerialNumber", "", "(JI)V", "getId", "()J", "getThreadSerialNumber", "()I", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class ThreadBlock extends GcRoot {
        private final long id;
        private final int threadSerialNumber;

        public ThreadBlock(long j, int i) {
            super(null);
            this.id = j;
            this.threadSerialNumber = i;
        }

        @Override
        public long getId() {
            return this.id;
        }

        public final int getThreadSerialNumber() {
            return this.threadSerialNumber;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Lkshark/GcRoot$ThreadObject;", "Lkshark/GcRoot;", "id", "", "threadSerialNumber", "", "stackTraceSerialNumber", "(JII)V", "getId", "()J", "getStackTraceSerialNumber", "()I", "getThreadSerialNumber", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class ThreadObject extends GcRoot {
        private final long id;
        private final int stackTraceSerialNumber;
        private final int threadSerialNumber;

        public ThreadObject(long j, int i, int i2) {
            super(null);
            this.id = j;
            this.threadSerialNumber = i;
            this.stackTraceSerialNumber = i2;
        }

        @Override
        public long getId() {
            return this.id;
        }

        public final int getStackTraceSerialNumber() {
            return this.stackTraceSerialNumber;
        }

        public final int getThreadSerialNumber() {
            return this.threadSerialNumber;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkshark/GcRoot$Unknown;", "Lkshark/GcRoot;", "id", "", "(J)V", "getId", "()J", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Unknown extends GcRoot {
        private final long id;

        public Unknown(long j) {
            super(null);
            this.id = j;
        }

        @Override
        public long getId() {
            return this.id;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkshark/GcRoot$Unreachable;", "Lkshark/GcRoot;", "id", "", "(J)V", "getId", "()J", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Unreachable extends GcRoot {
        private final long id;

        public Unreachable(long j) {
            super(null);
            this.id = j;
        }

        @Override
        public long getId() {
            return this.id;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkshark/GcRoot$VmInternal;", "Lkshark/GcRoot;", "id", "", "(J)V", "getId", "()J", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class VmInternal extends GcRoot {
        private final long id;

        public VmInternal(long j) {
            super(null);
            this.id = j;
        }

        @Override
        public long getId() {
            return this.id;
        }
    }

    private GcRoot() {
    }

    public GcRoot(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract long getId();
}
