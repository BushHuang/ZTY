package org.apache.commons.io.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class Tailer implements Runnable {
    private final long delay;
    private final boolean end;
    private final File file;
    private final TailerListener listener;
    private volatile boolean run;

    public Tailer(File file, TailerListener tailerListener) {
        this(file, tailerListener, 1000L);
    }

    public Tailer(File file, TailerListener tailerListener, long j) {
        this(file, tailerListener, j, false);
    }

    public Tailer(File file, TailerListener tailerListener, long j, boolean z) {
        this.run = true;
        this.file = file;
        this.delay = j;
        this.end = z;
        this.listener = tailerListener;
        tailerListener.init(this);
    }

    public static Tailer create(File file, TailerListener tailerListener) {
        return create(file, tailerListener, 1000L, false);
    }

    public static Tailer create(File file, TailerListener tailerListener, long j) {
        return create(file, tailerListener, j, false);
    }

    public static Tailer create(File file, TailerListener tailerListener, long j, boolean z) {
        Tailer tailer = new Tailer(file, tailerListener, j, z);
        Thread thread = new Thread(tailer);
        thread.setDaemon(true);
        thread.start();
        return tailer;
    }

    private String readLine(RandomAccessFile randomAccessFile) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = false;
        while (true) {
            int i = randomAccessFile.read();
            if (i == -1) {
                return null;
            }
            if (i == 10) {
                return stringBuffer.toString();
            }
            if (i != 13) {
                if (z) {
                    stringBuffer.append('\r');
                    z = false;
                }
                stringBuffer.append((char) i);
            } else {
                z = true;
            }
        }
    }

    private long readLines(RandomAccessFile randomAccessFile) throws IOException {
        long filePointer = randomAccessFile.getFilePointer();
        String line = readLine(randomAccessFile);
        while (line != null) {
            filePointer = randomAccessFile.getFilePointer();
            this.listener.handle(line);
            line = readLine(randomAccessFile);
        }
        randomAccessFile.seek(filePointer);
        return filePointer;
    }

    public long getDelay() {
        return this.delay;
    }

    public File getFile() {
        return this.file;
    }

    @Override
    public void run() throws Throwable {
        RandomAccessFile randomAccessFile = null;
        long jCurrentTimeMillis = 0;
        long lines = 0;
        while (this.run && randomAccessFile == null) {
            try {
                try {
                    try {
                        randomAccessFile = new RandomAccessFile(this.file, "r");
                    } catch (FileNotFoundException unused) {
                        this.listener.fileNotFound();
                    }
                    if (randomAccessFile == null) {
                        try {
                            Thread.sleep(this.delay);
                        } catch (InterruptedException unused2) {
                        }
                    } else {
                        lines = this.end ? this.file.length() : 0L;
                        jCurrentTimeMillis = System.currentTimeMillis();
                        randomAccessFile.seek(lines);
                    }
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e) {
                e = e;
            }
        }
        while (this.run) {
            long length = this.file.length();
            if (length < lines) {
                this.listener.fileRotated();
                try {
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(this.file, "r");
                    try {
                        IOUtils.closeQuietly(randomAccessFile);
                        lines = 0;
                        randomAccessFile = randomAccessFile2;
                    } catch (FileNotFoundException unused3) {
                        lines = 0;
                        randomAccessFile = randomAccessFile2;
                        this.listener.fileNotFound();
                    } catch (Exception e2) {
                        e = e2;
                        randomAccessFile = randomAccessFile2;
                        this.listener.handle(e);
                        IOUtils.closeQuietly(randomAccessFile);
                    } catch (Throwable th2) {
                        th = th2;
                        randomAccessFile = randomAccessFile2;
                        IOUtils.closeQuietly(randomAccessFile);
                        throw th;
                    }
                } catch (FileNotFoundException unused4) {
                }
            } else {
                if (length > lines) {
                    jCurrentTimeMillis = System.currentTimeMillis();
                    lines = readLines(randomAccessFile);
                } else if (FileUtils.isFileNewer(this.file, jCurrentTimeMillis)) {
                    randomAccessFile.seek(0L);
                    jCurrentTimeMillis = System.currentTimeMillis();
                    lines = readLines(randomAccessFile);
                }
                try {
                    Thread.sleep(this.delay);
                } catch (InterruptedException unused5) {
                }
            }
        }
        IOUtils.closeQuietly(randomAccessFile);
    }

    public void stop() {
        this.run = false;
    }
}
