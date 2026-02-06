package com.kwai.koom.javaoom.common;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.File;
import java.io.IOException;

public class KHeapFile implements Parcelable {
    public static final Parcelable.Creator<KHeapFile> CREATOR = new Parcelable.Creator<KHeapFile>() {
        @Override
        public KHeapFile createFromParcel(Parcel parcel) {
            return new KHeapFile(parcel);
        }

        @Override
        public KHeapFile[] newArray(int i) {
            return new KHeapFile[i];
        }
    };
    private static final String HPROF_FILE = ".hprof";
    private static final String JSON_FILE = ".json";
    private static final String TAG = "KHeapFile";
    private static KHeapFile kHeapFile;
    public Hprof hprof;
    public Report report;
    private String timeStamp;

    private static class BaseFile implements Parcelable {
        public static final Parcelable.Creator<BaseFile> CREATOR = new Parcelable.Creator<BaseFile>() {
            @Override
            public BaseFile createFromParcel(Parcel parcel) {
                return new BaseFile(parcel);
            }

            @Override
            public BaseFile[] newArray(int i) {
                return new BaseFile[i];
            }
        };
        private File file;
        public String path;

        protected BaseFile(Parcel parcel) {
            this.path = parcel.readString();
        }

        private BaseFile(String str) {
            this.path = str;
        }

        public void delete() {
            File file = file();
            this.file = file;
            if (file != null) {
                file.delete();
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public File file() {
            File file = this.file;
            if (file != null) {
                return file;
            }
            File file2 = new File(this.path);
            this.file = file2;
            return file2;
        }

        public String path() {
            return this.path;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.path);
        }
    }

    public static class Hprof extends BaseFile implements Parcelable {
        public static final Parcelable.Creator<Hprof> CREATOR = new Parcelable.Creator<Hprof>() {
            @Override
            public Hprof createFromParcel(Parcel parcel) {
                return new Hprof(parcel);
            }

            @Override
            public Hprof[] newArray(int i) {
                return new Hprof[i];
            }
        };
        public boolean stripped;

        protected Hprof(Parcel parcel) {
            super(parcel);
            this.stripped = parcel.readByte() != 0;
        }

        public Hprof(String str) {
            super(str);
        }

        public static Hprof file(String str) {
            return new Hprof(str);
        }

        @Override
        public void delete() {
            super.delete();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public File file() {
            return super.file();
        }

        @Override
        public String path() {
            return super.path();
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.stripped ? (byte) 1 : (byte) 0);
        }
    }

    public static class Report extends BaseFile {
        public static final Parcelable.Creator<Report> CREATOR = new Parcelable.Creator<Report>() {
            @Override
            public Report createFromParcel(Parcel parcel) {
                return new Report(parcel);
            }

            @Override
            public Report[] newArray(int i) {
                return new Report[i];
            }
        };

        protected Report(Parcel parcel) {
            super(parcel);
        }

        public Report(String str) {
            super(str);
        }

        public static Report file(String str) {
            return new Report(str);
        }

        @Override
        public void delete() {
            super.delete();
        }

        @Override
        public int describeContents() {
            return super.describeContents();
        }

        @Override
        public File file() {
            return super.file();
        }

        @Override
        public String path() {
            return super.path();
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
        }
    }

    public KHeapFile() {
    }

    protected KHeapFile(Parcel parcel) {
        this.hprof = (Hprof) parcel.readParcelable(Hprof.class.getClassLoader());
        this.report = (Report) parcel.readParcelable(Report.class.getClassLoader());
    }

    public static KHeapFile buildInstance(File file, File file2) {
        KHeapFile kHeapFile2 = getKHeapFile();
        kHeapFile = kHeapFile2;
        kHeapFile2.hprof = new Hprof(file.getAbsolutePath());
        kHeapFile.report = new Report(file2.getAbsolutePath());
        return kHeapFile;
    }

    public static void buildInstance(KHeapFile kHeapFile2) {
        kHeapFile = kHeapFile2;
    }

    public static void delete() {
        KHeapFile kHeapFile2 = kHeapFile;
        if (kHeapFile2 == null) {
            return;
        }
        kHeapFile2.report.file().delete();
        kHeapFile.hprof.file().delete();
    }

    private void generateDir(String str) {
        File file = new File(str);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    private Hprof generateHprofFile() {
        String str = getTimeStamp() + ".hprof";
        generateDir(KGlobalConfig.getHprofDir());
        return new Hprof(KGlobalConfig.getHprofDir() + File.separator + str);
    }

    private Report generateReportFile() throws IOException {
        String str = getTimeStamp() + ".json";
        generateDir(KGlobalConfig.getReportDir());
        Report report = new Report(KGlobalConfig.getReportDir() + File.separator + str);
        if (!report.file().exists()) {
            try {
                report.file().createNewFile();
                report.file().setWritable(true);
                report.file().setReadable(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return report;
    }

    public static KHeapFile getKHeapFile() {
        KHeapFile kHeapFile2 = kHeapFile;
        if (kHeapFile2 != null) {
            return kHeapFile2;
        }
        KHeapFile kHeapFile3 = new KHeapFile();
        kHeapFile = kHeapFile3;
        return kHeapFile3;
    }

    private String getTimeStamp() {
        String str = this.timeStamp;
        if (str != null) {
            return str;
        }
        String timeStamp = KUtils.getTimeStamp();
        this.timeStamp = timeStamp;
        return timeStamp;
    }

    public void buildFiles() {
        this.hprof = generateHprofFile();
        this.report = generateReportFile();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.hprof, i);
        parcel.writeParcelable(this.report, i);
    }
}
