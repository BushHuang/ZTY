package com.google.zxing.client.android;

import android.content.Intent;
import com.google.zxing.BarcodeFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public final class DecodeFormatManager {
    private static final Map<String, Set<BarcodeFormat>> FORMATS_FOR_MODE;
    private static final Set<BarcodeFormat> ONE_D_FORMATS;
    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    static final Set<BarcodeFormat> QR_CODE_FORMATS = EnumSet.of(BarcodeFormat.QR_CODE);
    static final Set<BarcodeFormat> DATA_MATRIX_FORMATS = EnumSet.of(BarcodeFormat.DATA_MATRIX);
    static final Set<BarcodeFormat> AZTEC_FORMATS = EnumSet.of(BarcodeFormat.AZTEC);
    static final Set<BarcodeFormat> PDF417_FORMATS = EnumSet.of(BarcodeFormat.PDF_417);
    static final Set<BarcodeFormat> PRODUCT_FORMATS = EnumSet.of(BarcodeFormat.UPC_A, BarcodeFormat.UPC_E, BarcodeFormat.EAN_13, BarcodeFormat.EAN_8, BarcodeFormat.RSS_14, BarcodeFormat.RSS_EXPANDED);
    static final Set<BarcodeFormat> INDUSTRIAL_FORMATS = EnumSet.of(BarcodeFormat.CODE_39, BarcodeFormat.CODE_93, BarcodeFormat.CODE_128, BarcodeFormat.ITF, BarcodeFormat.CODABAR);

    static {
        EnumSet enumSetCopyOf = EnumSet.copyOf((Collection) PRODUCT_FORMATS);
        ONE_D_FORMATS = enumSetCopyOf;
        enumSetCopyOf.addAll(INDUSTRIAL_FORMATS);
        HashMap map = new HashMap();
        FORMATS_FOR_MODE = map;
        map.put("ONE_D_MODE", ONE_D_FORMATS);
        FORMATS_FOR_MODE.put("PRODUCT_MODE", PRODUCT_FORMATS);
        FORMATS_FOR_MODE.put("QR_CODE_MODE", QR_CODE_FORMATS);
        FORMATS_FOR_MODE.put("DATA_MATRIX_MODE", DATA_MATRIX_FORMATS);
        FORMATS_FOR_MODE.put("AZTEC_MODE", AZTEC_FORMATS);
        FORMATS_FOR_MODE.put("PDF417_MODE", PDF417_FORMATS);
    }

    private DecodeFormatManager() {
    }

    public static Set<BarcodeFormat> parseDecodeFormats(Intent intent) {
        String stringExtra = intent.getStringExtra("SCAN_FORMATS");
        return parseDecodeFormats(stringExtra != null ? Arrays.asList(COMMA_PATTERN.split(stringExtra)) : null, intent.getStringExtra("SCAN_MODE"));
    }

    private static Set<BarcodeFormat> parseDecodeFormats(Iterable<String> iterable, String str) {
        if (iterable != null) {
            EnumSet enumSetNoneOf = EnumSet.noneOf(BarcodeFormat.class);
            try {
                Iterator<String> it = iterable.iterator();
                while (it.hasNext()) {
                    enumSetNoneOf.add(BarcodeFormat.valueOf(it.next()));
                }
                return enumSetNoneOf;
            } catch (IllegalArgumentException unused) {
            }
        }
        if (str != null) {
            return FORMATS_FOR_MODE.get(str);
        }
        return null;
    }
}
