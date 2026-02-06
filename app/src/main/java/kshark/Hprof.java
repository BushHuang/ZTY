package kshark;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kshark.SharkLog;
import okio.BufferedSource;
import okio.Okio;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u00192\u00020\u0001:\u0002\u0019\u001aB7\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\t¢\u0006\u0002\u0010\rJ\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lkshark/Hprof;", "Ljava/io/Closeable;", "channel", "Ljava/nio/channels/FileChannel;", "source", "Lokio/BufferedSource;", "reader", "Lkshark/HprofReader;", "heapDumpTimestamp", "", "hprofVersion", "Lkshark/Hprof$HprofVersion;", "fileLength", "(Ljava/nio/channels/FileChannel;Lokio/BufferedSource;Lkshark/HprofReader;JLkshark/Hprof$HprofVersion;J)V", "getFileLength", "()J", "getHeapDumpTimestamp", "getHprofVersion", "()Lkshark/Hprof$HprofVersion;", "getReader", "()Lkshark/HprofReader;", "close", "", "moveReaderTo", "newPosition", "Companion", "HprofVersion", "shark"}, k = 1, mv = {1, 1, 15})
public final class Hprof implements Closeable {

    public static final Companion INSTANCE = new Companion(null);
    private static final Map<String, HprofVersion> supportedVersions;
    private final FileChannel channel;
    private final long fileLength;
    private final long heapDumpTimestamp;
    private final HprofVersion hprofVersion;
    private final HprofReader reader;
    private final BufferedSource source;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkshark/Hprof$Companion;", "", "()V", "supportedVersions", "", "", "Lkshark/Hprof$HprofVersion;", "open", "Lkshark/Hprof;", "hprofFile", "Ljava/io/File;", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Hprof open(File hprofFile) throws IOException {
            Intrinsics.checkParameterIsNotNull(hprofFile, "hprofFile");
            long length = hprofFile.length();
            if (length == 0) {
                throw new IllegalArgumentException("Hprof file is 0 byte length");
            }
            FileInputStream fileInputStream = new FileInputStream(hprofFile);
            FileChannel channel = fileInputStream.getChannel();
            BufferedSource bufferedSourceBuffer = Okio.buffer(Okio.source(fileInputStream));
            long jIndexOf = bufferedSourceBuffer.indexOf((byte) 0);
            String utf8 = bufferedSourceBuffer.readUtf8(jIndexOf);
            HprofVersion hprofVersion = (HprofVersion) Hprof.supportedVersions.get(utf8);
            if (!(hprofVersion != null)) {
                throw new IllegalArgumentException(("Unsupported Hprof version [" + utf8 + "] not in supported list " + Hprof.supportedVersions.keySet()).toString());
            }
            bufferedSourceBuffer.skip(1L);
            int i = bufferedSourceBuffer.readInt();
            SharkLog.Logger logger = SharkLog.INSTANCE.getLogger();
            if (logger != null) {
                logger.d("identifierByteSize:" + i);
            }
            long j = bufferedSourceBuffer.readLong();
            Intrinsics.checkExpressionValueIsNotNull(bufferedSourceBuffer, "source");
            HprofReader hprofReader = new HprofReader(bufferedSourceBuffer, i, jIndexOf + 1 + 4 + 8);
            Intrinsics.checkExpressionValueIsNotNull(channel, "channel");
            return new Hprof(channel, bufferedSourceBuffer, hprofReader, j, hprofVersion, length, null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lkshark/Hprof$HprofVersion;", "", "versionString", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getVersionString", "()Ljava/lang/String;", "JDK1_2_BETA3", "JDK1_2_BETA4", "JDK_6", "ANDROID", "shark"}, k = 1, mv = {1, 1, 15})
    public enum HprofVersion {
        JDK1_2_BETA3("JAVA PROFILE 1.0"),
        JDK1_2_BETA4("JAVA PROFILE 1.0.1"),
        JDK_6("JAVA PROFILE 1.0.2"),
        ANDROID("JAVA PROFILE 1.0.3");

        private final String versionString;

        HprofVersion(String str) {
            this.versionString = str;
        }

        public final String getVersionString() {
            return this.versionString;
        }
    }

    static {
        HprofVersion[] hprofVersionArrValues = HprofVersion.values();
        ArrayList arrayList = new ArrayList(hprofVersionArrValues.length);
        for (HprofVersion hprofVersion : hprofVersionArrValues) {
            arrayList.add(TuplesKt.to(hprofVersion.getVersionString(), hprofVersion));
        }
        supportedVersions = MapsKt.toMap(arrayList);
    }

    private Hprof(FileChannel fileChannel, BufferedSource bufferedSource, HprofReader hprofReader, long j, HprofVersion hprofVersion, long j2) {
        this.channel = fileChannel;
        this.source = bufferedSource;
        this.reader = hprofReader;
        this.heapDumpTimestamp = j;
        this.hprofVersion = hprofVersion;
        this.fileLength = j2;
    }

    public Hprof(FileChannel fileChannel, BufferedSource bufferedSource, HprofReader hprofReader, long j, HprofVersion hprofVersion, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(fileChannel, bufferedSource, hprofReader, j, hprofVersion, j2);
    }

    @Override
    public void close() {
        this.source.close();
    }

    public final long getFileLength() {
        return this.fileLength;
    }

    public final long getHeapDumpTimestamp() {
        return this.heapDumpTimestamp;
    }

    public final HprofVersion getHprofVersion() {
        return this.hprofVersion;
    }

    public final HprofReader getReader() {
        return this.reader;
    }

    public final void moveReaderTo(long newPosition) {
        if (this.reader.getPosition() == newPosition) {
            return;
        }
        this.source.buffer().clear();
        this.channel.position(newPosition);
        this.reader.setPosition$shark(newPosition);
    }
}
