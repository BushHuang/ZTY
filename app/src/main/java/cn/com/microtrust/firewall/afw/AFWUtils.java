package cn.com.microtrust.firewall.afw;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import cn.com.microtrust.firewall.aidl.AFWRes;
import cn.com.microtrust.firewall.aidl.IAFWService;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AFWUtils {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");
    private static final String TAG = "AFW_TEST";

    public static boolean addAppBlackRule(Context context, IAFWService iAFWService, String str) {
        boolean operationResult = false;
        try {
            if (iAFWService != null) {
                AFWRes aFWResAddAppBlackRule = iAFWService.addAppBlackRule(getUidForPackages(context, str));
                operationResult = aFWResAddAppBlackRule.getOperationResult();
                Log.i("AFW_TEST", "setEnable=" + aFWResAddAppBlackRule.getOperationResult());
            } else {
                Log.i("AFW_TEST", "iafwService=null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("AFW_TEST", e.toString());
        }
        return operationResult;
    }

    public static boolean addAppWhiteRule(Context context, IAFWService iAFWService, String str) {
        boolean operationResult = false;
        try {
            if (iAFWService != null) {
                AFWRes aFWResAddAppWhiteRule = iAFWService.addAppWhiteRule(getUidForPackages(context, str));
                operationResult = aFWResAddAppWhiteRule.getOperationResult();
                Log.i("AFW_TEST", "setEnable=" + aFWResAddAppWhiteRule.getOperationResult());
            } else {
                Log.i("AFW_TEST", "iafwService=null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("AFW_TEST", e.toString());
        }
        return operationResult;
    }

    public static List<String> getAppBlackRules(Context context, IAFWService iAFWService) {
        ArrayList arrayList = new ArrayList();
        try {
            if (iAFWService != null) {
                List<String> data = iAFWService.getBlackRules().getData();
                for (int i = 0; i < data.size(); i++) {
                    if (isNumeric2(data.get(i)) && getPackagesForUid(context, Integer.parseInt(data.get(i))) != null && !TextUtils.isEmpty(getPackagesForUid(context, Integer.parseInt(data.get(i))))) {
                        arrayList.add(getPackagesForUid(context, Integer.parseInt(data.get(i))));
                    }
                }
            } else {
                Log.i("AFW_TEST", "iafwService=null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("AFW_TEST", e.toString());
        }
        return arrayList;
    }

    public static List<String> getAppWhiteRules(Context context, IAFWService iAFWService) {
        ArrayList arrayList = new ArrayList();
        try {
            if (iAFWService != null) {
                List<String> data = iAFWService.getWhiteRules().getData();
                for (int i = 0; i < data.size(); i++) {
                    if (isNumeric2(data.get(i)) && getPackagesForUid(context, Integer.parseInt(data.get(i))) != null && !TextUtils.isEmpty(getPackagesForUid(context, Integer.parseInt(data.get(i))))) {
                        arrayList.add(getPackagesForUid(context, Integer.parseInt(data.get(i))));
                    }
                }
            } else {
                Log.i("AFW_TEST", "iafwService=null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("AFW_TEST", e.toString());
        }
        return arrayList;
    }

    public static List<String> getBlackRules(IAFWService iAFWService) {
        ArrayList arrayList = new ArrayList();
        try {
            if (iAFWService != null) {
                List<String> data = iAFWService.getBlackRules().getData();
                for (int i = 0; i < data.size(); i++) {
                    if (!isNumeric2(data.get(i))) {
                        arrayList.add(data.get(i));
                    }
                }
            } else {
                Log.i("AFW_TEST", "iafwService=null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("AFW_TEST", e.toString());
        }
        return arrayList;
    }

    private static String getPackagesForUid(Context context, int i) {
        try {
            return context.getPackageManager().getPackagesForUid(i)[0];
        } catch (NullPointerException unused) {
            return "";
        }
    }

    private static String getUidForPackages(Context context, String str) {
        try {
            return String.valueOf(context.getPackageManager().getApplicationInfo(str, 1).uid);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getWhiteRules(IAFWService iAFWService) {
        ArrayList arrayList = new ArrayList();
        try {
            if (iAFWService != null) {
                List<String> data = iAFWService.getWhiteRules().getData();
                for (int i = 0; i < data.size(); i++) {
                    if (!isNumeric2(data.get(i))) {
                        arrayList.add(data.get(i));
                    }
                }
            } else {
                Log.i("AFW_TEST", "iafwService=null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("AFW_TEST", e.toString());
        }
        return arrayList;
    }

    private static boolean isNumeric2(String str) {
        return str != null && NUMBER_PATTERN.matcher(str).matches();
    }
}
