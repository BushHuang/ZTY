package com.xuehai.provider.core.font;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Environment;
import android.util.LruCache;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xuehai.provider.core.CPVDManager;
import com.xuehai.provider.utils.CPVDUtil;
import com.xuehai.provider.utils.JsonUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FontProvider {
    private static List<FontEntity> currentFonts;
    private static List<FontEntity> latestFonts;
    private static final String KEEP_DIR = Environment.getExternalStorageDirectory() + "/xuehai/keep";
    private static final String FONT_PATH = KEEP_DIR + "/font/";
    private static final String CURRENT_CONFIG_PATH = FONT_PATH + "current_config.ini";
    private static final String LATEST_CONFIG_PATH = FONT_PATH + "latest_config.ini";
    private static LruCache<String, Typeface> cache = new LruCache<>(16);

    private static void deleteFont(FontEntity fontEntity) throws IOException {
        File file = new File(getFontPath(fontEntity));
        if (file.exists()) {
            file.delete();
        }
        List<FontEntity> currentFontConfig = getCurrentFontConfig();
        currentFontConfig.remove(fontEntity);
        setCurrentFontConfig(currentFontConfig);
    }

    private static FontEntity findCurrentFontConfig(String str) {
        for (FontEntity fontEntity : getCurrentConfig()) {
            if (str.equals(fontEntity.getFontCode())) {
                return fontEntity;
            }
        }
        return null;
    }

    private static FontEntity findLatestFontConfig(String str) {
        for (FontEntity fontEntity : getLatestConfig()) {
            if (str.equals(fontEntity.getFontCode())) {
                return fontEntity;
            }
        }
        return null;
    }

    private static List<FontEntity> getCurrentConfig() {
        if (currentFonts == null) {
            currentFonts = getCurrentFontConfig();
        }
        return currentFonts;
    }

    private static List<FontEntity> getCurrentFontConfig() {
        return getFontsConfigByPath(CURRENT_CONFIG_PATH);
    }

    private static Typeface getFontByConfig(final Context context, final FontEntity fontEntity, final FontEntity fontEntity2) throws IOException {
        if (fontEntity2 == null) {
            return Typeface.DEFAULT;
        }
        final File file = new File(getFontPath(fontEntity2));
        if (!file.exists()) {
            deleteFont(fontEntity2);
            if (fontEntity.equals(fontEntity2)) {
                sendDownloadFontBroadcast(context, fontEntity);
            }
            return Typeface.DEFAULT;
        }
        Typeface typefaceCreateFromFile = Typeface.createFromFile(file);
        if (typefaceCreateFromFile != Typeface.DEFAULT) {
            return typefaceCreateFromFile;
        }
        new Thread(new Runnable() {
            @Override
            public void run() throws IOException {
                String fileMD5ToString = CPVDUtil.getFileMD5ToString(file);
                if (fileMD5ToString == null || !fileMD5ToString.equals(fontEntity2.getMd5())) {
                    FontProvider.deleteFont(fontEntity2);
                    FontProvider.sendDownloadFontBroadcast(context, fontEntity);
                }
            }
        }).run();
        return Typeface.DEFAULT;
    }

    private static String getFontPath(FontEntity fontEntity) {
        return FONT_PATH + fontEntity.getMd5();
    }

    private static FontType getFontType(FontEntity fontEntity) {
        for (FontType fontType : FontType.values()) {
            if (fontType.getFontCode().equals(fontEntity.getFontCode())) {
                return fontType;
            }
        }
        return null;
    }

    private static List<FontEntity> getFontsConfigByPath(String str) throws IOException {
        File file = new File(str);
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                fileInputStream.close();
                List<FontEntity> list = (List) new Gson().fromJson(new String(bArr, "UTF-8"), new TypeToken<List<FontEntity>>() {
                }.getType());
                if (list != null) {
                    return list;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return new ArrayList();
    }

    private static List<FontEntity> getLatestConfig() {
        if (latestFonts == null) {
            latestFonts = getLatestFontConfig();
        }
        return latestFonts;
    }

    private static List<FontEntity> getLatestFontConfig() {
        return getFontsConfigByPath(LATEST_CONFIG_PATH);
    }

    public static Typeface getTypeface(FontType fontType) {
        return fontType == null ? Typeface.DEFAULT : getTypefaceById(fontType.getFontCode());
    }

    public static Typeface getTypefaceById(String str) {
        try {
            Context context = CPVDManager.getContext();
            if (str == null) {
                return Typeface.DEFAULT;
            }
            Typeface typeface = cache.get(str);
            if (typeface != null) {
                return typeface;
            }
            FontEntity fontEntityFindLatestFontConfig = findLatestFontConfig(str);
            if (fontEntityFindLatestFontConfig == null) {
                cache.put(str, Typeface.DEFAULT);
                return Typeface.DEFAULT;
            }
            FontEntity fontEntityFindCurrentFontConfig = findCurrentFontConfig(str);
            if (!fontEntityFindLatestFontConfig.equals(fontEntityFindCurrentFontConfig)) {
                sendDownloadFontBroadcast(context, fontEntityFindLatestFontConfig);
            }
            Typeface fontByConfig = getFontByConfig(context, fontEntityFindLatestFontConfig, fontEntityFindCurrentFontConfig);
            cache.put(str, fontByConfig);
            return fontByConfig;
        } catch (Exception unused) {
            return Typeface.DEFAULT;
        }
    }

    private static void sendDownloadFontBroadcast(Context context, FontEntity fontEntity) {
        Intent intent = new Intent();
        intent.setAction("com.xuehai.action.DOWNLOAD_FONT");
        intent.putExtra("fontConfig", fontEntity);
        intent.setPackage("com.xuehai.launcher");
        context.sendBroadcast(intent);
        Intent intent2 = new Intent();
        intent2.setAction("com.xuehai.action.DOWNLOAD_FONT");
        intent2.putExtra("fontConfig", fontEntity);
        intent2.setPackage("com.xuehai.response_launcher_teacher");
        context.sendBroadcast(intent2);
    }

    private static void setCurrentFontConfig(List<FontEntity> list) throws IOException {
        try {
            File file = new File(CURRENT_CONFIG_PATH);
            if (file.exists()) {
                return;
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            String strObjToJson = JsonUtil.objToJson(list);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(strObjToJson);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
