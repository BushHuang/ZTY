package com.xh.xhcore.common.util;

import com.xh.xhcore.common.http.strategy.LogUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XHFileUtil {
    private XHFileUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean copyDir(File file, File file2) {
        return copyOrMoveDir(file, file2, false);
    }

    public static boolean copyDir(String str, String str2) {
        return copyDir(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean copyFile(File file, File file2) {
        return copyOrMoveFile(file, file2, false);
    }

    public static boolean copyFile(String str, String str2) {
        return copyFile(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean copyOrMoveDir(File file, File file2, boolean z) {
        if (file == null || file2 == null) {
            return false;
        }
        String str = file.getPath() + File.separator;
        String str2 = file2.getPath() + File.separator;
        if (str2.contains(str) || !file.exists() || !file.isDirectory() || !createOrExistsDir(file2)) {
            return false;
        }
        for (File file3 : file.listFiles()) {
            File file4 = new File(str2 + file3.getName());
            if (file3.isFile()) {
                if (!copyOrMoveFile(file3, file4, z)) {
                    return false;
                }
            } else if (file3.isDirectory() && !copyOrMoveDir(file3, file4, z)) {
                return false;
            }
        }
        return !z || deleteDir(file);
    }

    public static boolean copyOrMoveDir(String str, String str2, boolean z) {
        return copyOrMoveDir(getFileByPath(str), getFileByPath(str2), z);
    }

    public static boolean copyOrMoveFile(File file, File file2, boolean z) {
        if (file != null && file2 != null && file.exists() && file.isFile()) {
            if ((file2.exists() && file2.isFile()) || !createOrExistsDir(file2.getParentFile())) {
                return false;
            }
            try {
                if (!writeFileFromIS(file2, (InputStream) new FileInputStream(file), false)) {
                    return false;
                }
                if (z) {
                    if (!deleteFile(file)) {
                        return false;
                    }
                }
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean copyOrMoveFile(String str, String str2, boolean z) {
        return copyOrMoveFile(getFileByPath(str), getFileByPath(str2), z);
    }

    public static boolean createFileByDeleteOldFile(File file) {
        if (file == null) {
            return false;
        }
        if ((file.exists() && file.isFile() && !file.delete()) || !createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createFileByDeleteOldFile(String str) {
        return createFileByDeleteOldFile(getFileByPath(str));
    }

    public static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    public static boolean createOrExistsDir(String str) {
        return createOrExistsDir(getFileByPath(str));
    }

    public static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createOrExistsFile(String str) {
        return createOrExistsFile(getFileByPath(str));
    }

    public static boolean deleteDir(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File file2 : fileArrListFiles) {
                if (file2.isFile()) {
                    if (!deleteFile(file2)) {
                        return false;
                    }
                } else if (file2.isDirectory() && !deleteDir(file2)) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static boolean deleteDir(String str) {
        return deleteDir(getFileByPath(str));
    }

    public static boolean deleteFile(File file) {
        return file != null && (!file.exists() || (file.isFile() && file.delete()));
    }

    public static boolean deleteFile(String str) {
        return deleteFile(getFileByPath(str));
    }

    public static boolean deleteFilesInDir(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File file2 : fileArrListFiles) {
                if (file2.isFile()) {
                    if (!deleteFile(file2)) {
                        return false;
                    }
                } else if (file2.isDirectory() && !deleteDir(file2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean deleteFilesInDir(String str) {
        return deleteFilesInDir(getFileByPath(str));
    }

    public static long getDirLength(File file) {
        if (!isDir(file)) {
            return -1L;
        }
        long dirLength = 0;
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File file2 : fileArrListFiles) {
                dirLength += file2.isDirectory() ? getDirLength(file2) : file2.length();
            }
        }
        return dirLength;
    }

    public static long getDirLength(String str) {
        return getDirLength(getFileByPath(str));
    }

    public static String getDirName(File file) {
        if (file == null) {
            return null;
        }
        return getDirName(file.getPath());
    }

    public static String getDirName(String str) {
        if (XHStringUtil.isSpace(str)) {
            return str;
        }
        int iLastIndexOf = str.lastIndexOf(File.separator);
        return iLastIndexOf == -1 ? "" : str.substring(0, iLastIndexOf + 1);
    }

    public static String getDirSize(File file) {
        long dirLength = getDirLength(file);
        return dirLength == -1 ? "" : XHConvertUtil.byte2FitMemorySize(dirLength);
    }

    public static String getDirSize(String str) {
        return getDirSize(getFileByPath(str));
    }

    public static File getFileByPath(String str) {
        if (XHStringUtil.isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    public static String getFileCharsetSimple(File file) throws Throwable {
        int i = 0;
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                try {
                    int i2 = (bufferedInputStream2.read() << 8) + bufferedInputStream2.read();
                    XHCloseUtil.closeIO(bufferedInputStream2);
                    i = i2;
                } catch (IOException e) {
                    e = e;
                    bufferedInputStream = bufferedInputStream2;
                    e.printStackTrace();
                    XHCloseUtil.closeIO(bufferedInputStream);
                    if (i == 61371) {
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = bufferedInputStream2;
                    XHCloseUtil.closeIO(bufferedInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
        return i == 61371 ? i != 65279 ? i != 65534 ? "GBK" : "Unicode" : "UTF-16BE" : "UTF-8";
    }

    public static String getFileCharsetSimple(String str) {
        return getFileCharsetSimple(getFileByPath(str));
    }

    public static String getFileExtension(File file) {
        if (file == null) {
            return null;
        }
        return getFileExtension(file.getPath());
    }

    public static String getFileExtension(String str) {
        if (XHStringUtil.isSpace(str)) {
            return str;
        }
        int iLastIndexOf = str.lastIndexOf(46);
        return (iLastIndexOf == -1 || str.lastIndexOf(File.separator) >= iLastIndexOf) ? "" : str.substring(iLastIndexOf + 1);
    }

    public static long getFileLastModified(File file) {
        if (file == null) {
            return -1L;
        }
        return file.lastModified();
    }

    public static long getFileLastModified(String str) {
        return getFileLastModified(getFileByPath(str));
    }

    public static long getFileLength(File file) {
        if (isFile(file)) {
            return file.length();
        }
        return -1L;
    }

    public static long getFileLength(String str) {
        return getFileLength(getFileByPath(str));
    }

    public static int getFileLines(File file) throws Throwable {
        int i;
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            } catch (Throwable th) {
                th = th;
            }
            try {
                try {
                    byte[] bArr = new byte[1024];
                    i = 1;
                    while (true) {
                        try {
                            int i2 = bufferedInputStream.read(bArr, 0, 1024);
                            if (i2 == -1) {
                                break;
                            }
                            for (int i3 = 0; i3 < i2; i3++) {
                                if (bArr[i3] == 10) {
                                    i++;
                                }
                            }
                        } catch (IOException e) {
                            e = e;
                            bufferedInputStream2 = bufferedInputStream;
                            e.printStackTrace();
                            XHCloseUtil.closeIO(bufferedInputStream2);
                            return i;
                        }
                    }
                    XHCloseUtil.closeIO(bufferedInputStream);
                } catch (IOException e2) {
                    e = e2;
                    bufferedInputStream2 = bufferedInputStream;
                    i = 1;
                    e.printStackTrace();
                    XHCloseUtil.closeIO(bufferedInputStream2);
                    return i;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream2 = bufferedInputStream;
                XHCloseUtil.closeIO(bufferedInputStream2);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
        }
        return i;
    }

    public static int getFileLines(String str) {
        return getFileLines(getFileByPath(str));
    }

    public static byte[] getFileMD5(File file) throws Throwable {
        DigestInputStream digestInputStream;
        Closeable closeable;
        Closeable closeable2 = null;
        if (file == null) {
            return null;
        }
        try {
            try {
                digestInputStream = new DigestInputStream(new FileInputStream(file), MessageDigest.getInstance("MD5"));
            } catch (IOException e) {
                e = e;
                digestInputStream = null;
                e.printStackTrace();
                XHCloseUtil.closeIO(digestInputStream);
                return null;
            } catch (NoSuchAlgorithmException e2) {
                e = e2;
                digestInputStream = null;
                e.printStackTrace();
                XHCloseUtil.closeIO(digestInputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                XHCloseUtil.closeIO(closeable2);
                throw th;
            }
            try {
                while (digestInputStream.read(new byte[262144]) > 0) {
                }
                byte[] bArrDigest = digestInputStream.getMessageDigest().digest();
                XHCloseUtil.closeIO(digestInputStream);
                return bArrDigest;
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                XHCloseUtil.closeIO(digestInputStream);
                return null;
            } catch (NoSuchAlgorithmException e4) {
                e = e4;
                e.printStackTrace();
                XHCloseUtil.closeIO(digestInputStream);
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            closeable2 = closeable;
            XHCloseUtil.closeIO(closeable2);
            throw th;
        }
    }

    public static byte[] getFileMD5(String str) {
        return getFileMD5(XHStringUtil.isSpace(str) ? null : new File(str));
    }

    public static String getFileMD5ToString(File file) {
        return XHConvertUtil.bytes2HexString(getFileMD5(file));
    }

    public static String getFileMD5ToString(String str) {
        return getFileMD5ToString(XHStringUtil.isSpace(str) ? null : new File(str));
    }

    public static String getFileName(File file) {
        if (file == null) {
            return null;
        }
        return getFileName(file.getPath());
    }

    public static String getFileName(String str) {
        int iLastIndexOf;
        return (XHStringUtil.isSpace(str) || (iLastIndexOf = str.lastIndexOf(File.separator)) == -1) ? str : str.substring(iLastIndexOf + 1);
    }

    public static String getFileNameNoExtension(File file) {
        if (file == null) {
            return null;
        }
        return getFileNameNoExtension(file.getPath());
    }

    public static String getFileNameNoExtension(String str) {
        if (XHStringUtil.isSpace(str)) {
            return str;
        }
        int iLastIndexOf = str.lastIndexOf(46);
        int iLastIndexOf2 = str.lastIndexOf(File.separator);
        return iLastIndexOf2 == -1 ? iLastIndexOf == -1 ? str : str.substring(0, iLastIndexOf) : (iLastIndexOf == -1 || iLastIndexOf2 > iLastIndexOf) ? str.substring(iLastIndexOf2 + 1) : str.substring(iLastIndexOf2 + 1, iLastIndexOf);
    }

    public static String getFileParentPath(String str) {
        int iLastIndexOf;
        return (XHStringUtil.isSpace(str) || (iLastIndexOf = str.lastIndexOf(File.separator)) == -1) ? str : str.substring(0, iLastIndexOf);
    }

    public static String getFileSize(File file) {
        long fileLength = getFileLength(file);
        return fileLength == -1 ? "" : XHConvertUtil.byte2FitMemorySize(fileLength);
    }

    public static String getFileSize(String str) {
        return getFileSize(getFileByPath(str));
    }

    public static boolean isDir(File file) {
        return isFileExists(file) && file.isDirectory();
    }

    public static boolean isDir(String str) {
        return isDir(getFileByPath(str));
    }

    public static boolean isFile(File file) {
        return isFileExists(file) && file.isFile();
    }

    public static boolean isFile(String str) {
        return isFile(getFileByPath(str));
    }

    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    public static boolean isFileExists(String str) {
        return isFileExists(getFileByPath(str));
    }

    public static boolean isFileExistsAndCanRead(File file) {
        return isFileExists(file) && file.isFile() && file.canRead();
    }

    public static List<File> listFilesInDir(File file) {
        if (!isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File file2 : fileArrListFiles) {
                arrayList.add(file2);
                if (file2.isDirectory()) {
                    arrayList.addAll(listFilesInDir(file2));
                }
            }
        }
        return arrayList;
    }

    public static List<File> listFilesInDir(File file, boolean z) {
        if (!isDir(file)) {
            return null;
        }
        if (z) {
            return listFilesInDir(file);
        }
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            Collections.addAll(arrayList, fileArrListFiles);
        }
        return arrayList;
    }

    public static List<File> listFilesInDir(String str) {
        return listFilesInDir(getFileByPath(str));
    }

    public static List<File> listFilesInDir(String str, boolean z) {
        return listFilesInDir(getFileByPath(str), z);
    }

    public static List<File> listFilesInDirWithFilter(File file, FilenameFilter filenameFilter) {
        if (file == null || !isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File file2 : fileArrListFiles) {
                if (filenameFilter.accept(file2.getParentFile(), file2.getName())) {
                    arrayList.add(file2);
                }
                if (file2.isDirectory()) {
                    arrayList.addAll(listFilesInDirWithFilter(file2, filenameFilter));
                }
            }
        }
        return arrayList;
    }

    public static List<File> listFilesInDirWithFilter(File file, FilenameFilter filenameFilter, boolean z) {
        if (z) {
            return listFilesInDirWithFilter(file, filenameFilter);
        }
        if (file == null || !isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File file2 : fileArrListFiles) {
                if (filenameFilter.accept(file2.getParentFile(), file2.getName())) {
                    arrayList.add(file2);
                }
            }
        }
        return arrayList;
    }

    public static List<File> listFilesInDirWithFilter(File file, String str) {
        if (file == null || !isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File file2 : fileArrListFiles) {
                if (file2.getName().toUpperCase().endsWith(str.toUpperCase())) {
                    arrayList.add(file2);
                }
                if (file2.isDirectory()) {
                    arrayList.addAll(listFilesInDirWithFilter(file2, str));
                }
            }
        }
        return arrayList;
    }

    public static List<File> listFilesInDirWithFilter(File file, String str, boolean z) {
        if (z) {
            return listFilesInDirWithFilter(file, str);
        }
        if (file == null || !isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File file2 : fileArrListFiles) {
                if (file2.getName().toUpperCase().endsWith(str.toUpperCase())) {
                    arrayList.add(file2);
                }
            }
        }
        return arrayList;
    }

    public static List<File> listFilesInDirWithFilter(String str, FilenameFilter filenameFilter) {
        return listFilesInDirWithFilter(getFileByPath(str), filenameFilter);
    }

    public static List<File> listFilesInDirWithFilter(String str, FilenameFilter filenameFilter, boolean z) {
        return listFilesInDirWithFilter(getFileByPath(str), filenameFilter, z);
    }

    public static List<File> listFilesInDirWithFilter(String str, String str2) {
        return listFilesInDirWithFilter(getFileByPath(str), str2);
    }

    public static List<File> listFilesInDirWithFilter(String str, String str2, boolean z) {
        return listFilesInDirWithFilter(getFileByPath(str), str2, z);
    }

    public static boolean moveDir(File file, File file2) {
        return copyOrMoveDir(file, file2, true);
    }

    public static boolean moveDir(String str, String str2) {
        return moveDir(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean moveFile(File file, File file2) {
        return copyOrMoveFile(file, file2, true);
    }

    public static boolean moveFile(String str, String str2) {
        return moveFile(getFileByPath(str), getFileByPath(str2));
    }

    public static byte[] readFile2Bytes(File file) {
        if (file == null) {
            return null;
        }
        try {
            return XHConvertUtil.inputStream2Bytes(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] readFile2Bytes(String str) {
        return readFile2Bytes(getFileByPath(str));
    }

    public static List<String> readFile2List(File file, int i, int i2, String str) throws Throwable {
        BufferedReader bufferedReader;
        String str2 = null;
        if (file == null || i > i2) {
            return null;
        }
        try {
            try {
                ArrayList arrayList = new ArrayList();
                bufferedReader = XHStringUtil.isSpace(str) ? new BufferedReader(new FileReader(file)) : new BufferedReader(new InputStreamReader(new FileInputStream(file), str));
                int i3 = 1;
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null || i3 > i2) {
                            break;
                        }
                        if (i <= i3 && i3 <= i2) {
                            arrayList.add(line);
                        }
                        i3++;
                    } catch (IOException e) {
                        e = e;
                        e.printStackTrace();
                        XHCloseUtil.closeIO(bufferedReader);
                        return null;
                    }
                }
                XHCloseUtil.closeIO(bufferedReader);
                return arrayList;
            } catch (IOException e2) {
                e = e2;
                bufferedReader = null;
            } catch (Throwable th) {
                th = th;
                XHCloseUtil.closeIO(str2);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            str2 = str;
            XHCloseUtil.closeIO(str2);
            throw th;
        }
    }

    public static List<String> readFile2List(File file, String str) {
        return readFile2List(file, 0, Integer.MAX_VALUE, str);
    }

    public static List<String> readFile2List(String str, int i, int i2, String str2) {
        return readFile2List(getFileByPath(str), i, i2, str2);
    }

    public static List<String> readFile2List(String str, String str2) {
        return readFile2List(getFileByPath(str), str2);
    }

    public static String readFile2String(File file, String str) throws Throwable {
        BufferedReader bufferedReader;
        String str2 = null;
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            try {
                StringBuilder sb = new StringBuilder();
                bufferedReader = XHStringUtil.isSpace(str) ? new BufferedReader(new InputStreamReader(new FileInputStream(file))) : new BufferedReader(new InputStreamReader(new FileInputStream(file), str));
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                        sb.append("\r\n");
                    } catch (IOException e) {
                        e = e;
                        e.printStackTrace();
                        XHCloseUtil.closeIO(bufferedReader);
                        return null;
                    }
                }
                if (sb.length() < 2) {
                    String string = sb.toString();
                    XHCloseUtil.closeIO(bufferedReader);
                    return string;
                }
                String string2 = sb.delete(sb.length() - 2, sb.length()).toString();
                XHCloseUtil.closeIO(bufferedReader);
                return string2;
            } catch (IOException e2) {
                e = e2;
                bufferedReader = null;
            } catch (Throwable th) {
                th = th;
                XHCloseUtil.closeIO(str2);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            str2 = str;
            XHCloseUtil.closeIO(str2);
            throw th;
        }
    }

    public static String readFile2String(String str, String str2) {
        return readFile2String(getFileByPath(str), str2);
    }

    public static boolean rename(File file, String str) {
        if (file == null || !file.exists() || XHStringUtil.isSpace(str)) {
            return false;
        }
        if (str.equals(file.getName())) {
            return true;
        }
        File file2 = new File(file.getParent() + File.separator + str);
        return !file2.exists() && file.renameTo(file2);
    }

    public static boolean rename(String str, String str2) {
        return rename(getFileByPath(str), str2);
    }

    public static List<File> searchFileInDir(File file, String str) {
        if (file == null || !isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (File file2 : fileArrListFiles) {
                if (file2.getName().toUpperCase().equals(str.toUpperCase())) {
                    arrayList.add(file2);
                }
                if (file2.isDirectory()) {
                    arrayList.addAll(searchFileInDir(file2, str));
                }
            }
        }
        return arrayList;
    }

    public static List<File> searchFileInDir(String str, String str2) {
        return searchFileInDir(getFileByPath(str), str2);
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream, boolean z) throws Throwable {
        if (file == null || inputStream == null || !createOrExistsFile(file)) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file, z));
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = inputStream.read(bArr, 0, 1024);
                        if (i == -1) {
                            XHCloseUtil.closeIO(inputStream, bufferedOutputStream2);
                            return true;
                        }
                        bufferedOutputStream2.write(bArr, 0, i);
                    }
                } catch (IOException e) {
                    e = e;
                    bufferedOutputStream = bufferedOutputStream2;
                    e.printStackTrace();
                    XHCloseUtil.closeIO(inputStream, bufferedOutputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    bufferedOutputStream = bufferedOutputStream2;
                    XHCloseUtil.closeIO(inputStream, bufferedOutputStream);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream, boolean z) {
        return writeFileFromIS(getFileByPath(str), inputStream, z);
    }

    public static void writeFileFromISIfAbsent(String str, InputStream inputStream, boolean z) throws Throwable {
        File fileByPath = getFileByPath(str);
        if (fileByPath == null || !fileByPath.exists()) {
            writeFileFromIS(fileByPath, inputStream, z);
        } else {
            LogUtils.d("文件已经存在，不再写入");
        }
    }

    public static boolean writeFileFromString(File file, String str, boolean z) throws Throwable {
        if (file == null || str == null || !createOrExistsFile(file)) {
            return false;
        }
        BufferedWriter bufferedWriter = null;
        try {
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file, z));
                try {
                    bufferedWriter2.write(str);
                    XHCloseUtil.closeIO(bufferedWriter2);
                    return true;
                } catch (IOException e) {
                    e = e;
                    bufferedWriter = bufferedWriter2;
                    e.printStackTrace();
                    XHCloseUtil.closeIO(bufferedWriter);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    XHCloseUtil.closeIO(bufferedWriter);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean writeFileFromString(String str, String str2, boolean z) {
        return writeFileFromString(getFileByPath(str), str2, z);
    }
}
