package com.kwai.koom.javaoom.report;

import java.util.List;

public class HeapReport {
    public Boolean analysisDone;
    public List<ClassInfo> classInfos;
    public List<GCPath> gcPaths;
    public Integer reAnalysisTimes;
    public RunningInfo runningInfo;

    public static class ClassInfo {
        public String className;
        public Integer instanceCount;
        public Integer leakInstanceCount;
    }

    public static class GCPath {
        public String gcRoot;
        public Integer instanceCount;
        public String leakReason;
        public List<PathItem> path;
        public String signature;

        public static class PathItem {
            String declaredClass;
            String reference;
            String referenceType;
        }
    }

    public static class RunningInfo {
        public String analysisReason;
        public String appVersion;
        public String buildModel;
        public String currentPage;
        public String dumpReason;
        public String filterInstanceTime;
        public String findGCPathTime;
        public Integer jvmMax;
        public Integer jvmUsed;
        public Integer koomVersion;
        public String manufacture;
        public String nowTime;
        public Integer pss;
        public Integer rss;
        public Integer sdkInt;
        public Integer threadCount;
        public Integer usageSeconds;
        public Integer vss;
    }
}
