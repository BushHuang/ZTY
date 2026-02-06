package com.analysys.allgro.plugin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import com.analysys.allgro.a;
import com.analysys.process.AgentProcess;
import com.analysys.process.PathGeneral;
import com.analysys.utils.AnalysysUtil;
import com.analysys.utils.AnsReflectUtils;
import com.analysys.utils.ExceptionUtil;
import java.util.HashMap;
import java.util.Map;

public class ViewClickProbe extends ASMHookAdapter {
    private void autoTrackClick(Object obj, Map<String, Object> map, boolean z, long j) {
        if (obj != null) {
            map.putAll(a.a(obj, true));
        }
        AgentProcess.getInstance().autoTrackViewClick(map, j);
    }

    private boolean checkTrackClickEnable(Object obj, Object obj2, boolean z) {
        AgentProcess agentProcess = AgentProcess.getInstance();
        if (agentProcess.isThisViewInAutoClickBlackList(obj2) || (obj2 != null && agentProcess.isThisViewTypeInAutoClickBlackList(obj2.getClass())) || (obj != null && agentProcess.isThisPageInAutoClickBlackList(obj.getClass().getName()))) {
            return false;
        }
        return !AgentProcess.getInstance().hasAutoClickWhiteList() || agentProcess.isThisViewInAutoClickWhiteList(obj2) || (obj2 != null && agentProcess.isThisViewTypeInAutoClickWhiteList(obj2.getClass())) || (obj != null && agentProcess.isThisPageInAutoClickWhiteList(obj.getClass().getName()));
    }

    private static boolean isTrackClickSwitchClose() {
        return !AgentProcess.getInstance().getConfig().isAutoTrackClick();
    }

    @Override
    public void trackDialog(DialogInterface dialogInterface, int i, boolean z, long j) {
        Class<?> classByName;
        Class<?> classByName2;
        try {
            if (isTrackClickSwitchClose()) {
                return;
            }
            Button button = null;
            Object obj = dialogInterface instanceof Dialog ? (Dialog) dialogInterface : null;
            if (obj == null) {
                return;
            }
            try {
                if (checkTrackClickEnable(obj, obj, z)) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("$element_type", "Dialog");
                    if (obj instanceof AlertDialog) {
                        AlertDialog alertDialog = (AlertDialog) obj;
                        Button button2 = alertDialog.getButton(i);
                        if (button2 != null) {
                            String string = button2.getText().toString();
                            if (!TextUtils.isEmpty(string)) {
                                map.put("$element_content", string);
                            }
                            String strA = a.a((View) button2);
                            if (!TextUtils.isEmpty(strA)) {
                                map.put("$element_id", strA);
                            }
                        } else {
                            ListView listView = alertDialog.getListView();
                            if (listView != null) {
                                Object item = listView.getAdapter().getItem(i);
                                if (item instanceof String) {
                                    map.put("$element_content", item);
                                }
                                map.put("$element_position", Integer.valueOf(i));
                            }
                        }
                    } else {
                        try {
                            classByName = AnsReflectUtils.getClassByName("androidx.appcompat.app.AlertDialog");
                        } catch (Throwable th) {
                            ExceptionUtil.exceptionThrow(th);
                            classByName = null;
                        }
                        try {
                            classByName2 = AnsReflectUtils.getClassByName("androidx.appcompat.app.AlertDialog");
                        } catch (Throwable th2) {
                            ExceptionUtil.exceptionThrow(th2);
                            classByName2 = null;
                        }
                        if (classByName == null && classByName2 == null) {
                            return;
                        }
                        if (classByName == null) {
                            classByName = classByName2;
                        }
                        if (classByName != null && classByName.isInstance(obj)) {
                            try {
                                button = (Button) obj.getClass().getMethod("getButton", Integer.TYPE).invoke(obj, Integer.valueOf(i));
                            } catch (Throwable th3) {
                                ExceptionUtil.exceptionThrow(th3);
                            }
                            if (button != null) {
                                String string2 = button.getText().toString();
                                if (!TextUtils.isEmpty(string2)) {
                                    map.put("$element_content", string2);
                                }
                                String strA2 = a.a((View) button);
                                if (!TextUtils.isEmpty(strA2)) {
                                    map.put("$element_id", strA2);
                                }
                            } else {
                                try {
                                    ListView listView2 = (ListView) obj.getClass().getMethod("getListView", new Class[0]).invoke(obj, new Object[0]);
                                    if (listView2 != null) {
                                        Object item2 = listView2.getAdapter().getItem(i);
                                        if (item2 instanceof String) {
                                            map.put("$element_content", item2);
                                        }
                                        map.put("$element_position", Integer.valueOf(i));
                                    }
                                } catch (Throwable th4) {
                                    ExceptionUtil.exceptionThrow(th4);
                                }
                            }
                        }
                    }
                    autoTrackClick(obj, map, z, j);
                }
            } catch (Throwable th5) {
                th = th5;
                ExceptionUtil.exceptionThrow(th);
            }
        } catch (Throwable th6) {
            th = th6;
        }
    }

    @Override
    public void trackDrawerSwitch(View view, boolean z, boolean z2, long j) {
        try {
            Object objC = a.c(view);
            if (checkTrackClickEnable(objC, view, z2)) {
                HashMap map = new HashMap();
                map.put("$element_type", "DrawerLayout");
                map.put("$element_content", z ? "Open" : "Close");
                autoTrackClick(objC, map, z2, j);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    @Override
    public void trackExpListViewChildClick(ExpandableListView expandableListView, View view, int i, int i2, boolean z, long j) {
        try {
            if (isTrackClickSwitchClose()) {
                return;
            }
            Object objC = a.c((View) expandableListView);
            if (checkTrackClickEnable(objC, view, z) && checkTrackClickEnable(objC, expandableListView, z)) {
                Map<String, Object> map = new HashMap<>();
                String[] strArrB = a.b(view);
                map.put("$element_type", "ExpandableListViewChildItem:" + strArrB[0]);
                map.put("$element_content", strArrB[1]);
                map.put("$element_position", i + ":" + i2);
                String strA = a.a(view);
                if (!TextUtils.isEmpty(strA)) {
                    map.put("$element_id", strA);
                }
                autoTrackClick(objC, map, z, j);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    @Override
    public void trackExpListViewGroupClick(ExpandableListView expandableListView, View view, int i, boolean z, long j) {
        try {
            if (isTrackClickSwitchClose()) {
                return;
            }
            Object objC = a.c((View) expandableListView);
            if (checkTrackClickEnable(objC, view, z) && checkTrackClickEnable(objC, expandableListView, z)) {
                HashMap map = new HashMap();
                String[] strArrB = a.b(view);
                map.put("$element_type", "ExpandableListViewGroupItem:" + strArrB[0]);
                map.put("$element_content", strArrB[1]);
                map.put("$element_position", Integer.valueOf(i));
                String strA = a.a(view);
                if (!TextUtils.isEmpty(strA)) {
                    map.put("$element_id", strA);
                }
                trackListView(expandableListView, view, i, z, j);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    @Override
    public void trackListView(AdapterView<?> adapterView, View view, int i, boolean z, long j) {
        try {
            if (isTrackClickSwitchClose()) {
                return;
            }
            Object objC = a.c((View) adapterView);
            if (checkTrackClickEnable(objC, view, z) && checkTrackClickEnable(objC, adapterView, z)) {
                Map<String, Object> map = new HashMap<>();
                String[] strArrB = a.b(view);
                Object obj = "ListViewItem:" + strArrB[0];
                String str = strArrB[1];
                map.put("$element_type", obj);
                map.put("$element_content", str);
                map.put("$element_position", i + "");
                String strA = a.a(view);
                if (!TextUtils.isEmpty(strA)) {
                    map.put("$element_id", strA);
                }
                autoTrackClick(objC, map, z, j);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    @Override
    public void trackMenuItem(MenuItem menuItem, boolean z, long j) {
        try {
            if (isTrackClickSwitchClose()) {
                return;
            }
            trackMenuItem(null, menuItem, z, j);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    @Override
    public void trackMenuItem(Object obj, MenuItem menuItem, boolean z, long j) {
        try {
            if (isTrackClickSwitchClose()) {
                return;
            }
            Object objC = a.a(obj) ? null : a.c((View) null);
            if (checkTrackClickEnable(objC, menuItem, z)) {
                Map<String, Object> map = new HashMap<>();
                map.put("$element_type", "MenuItem");
                CharSequence title = menuItem.getTitle();
                if (!TextUtils.isEmpty(title)) {
                    map.put("$element_content", title);
                }
                String strA = a.a(menuItem.getItemId());
                if (!TextUtils.isEmpty(strA)) {
                    map.put("$element_id", strA);
                }
                autoTrackClick(objC, map, z, j);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    @Override
    public void trackRadioGroup(RadioGroup radioGroup, int i, boolean z, long j) {
        try {
            if (isTrackClickSwitchClose()) {
                return;
            }
            Object objC = a.c((View) radioGroup);
            View viewFindViewById = radioGroup.findViewById(i);
            if (checkTrackClickEnable(objC, viewFindViewById, z) && checkTrackClickEnable(objC, radioGroup, z)) {
                HashMap map = new HashMap();
                String[] strArrB = a.b(viewFindViewById);
                map.put("$element_type", strArrB[0]);
                map.put("$element_content", strArrB[1]);
                map.put("$element_position", radioGroup.indexOfChild(viewFindViewById) + "");
                autoTrackClick(objC, map, z, j);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    @Override
    public void trackTabHost(String str, boolean z, long j) {
        try {
            if (isTrackClickSwitchClose()) {
                return;
            }
            Object objC = a.c((View) null);
            if (AnsReflectUtils.getClassByName(TabHost.class.getName()) == null || !checkTrackClickEnable(objC, (TabHost) TabHost.class.getConstructor(Context.class).newInstance(AnalysysUtil.getContext()), z)) {
                return;
            }
            HashMap map = new HashMap();
            map.put("$element_type", "TabHost");
            map.put("$element_content", str);
            autoTrackClick(objC, map, z, j);
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    @Override
    public void trackTabLayout(Object obj, Object obj2, boolean z, long j) {
        Object obj3;
        Object objC;
        try {
            if (isTrackClickSwitchClose()) {
                return;
            }
            Class<?> classByName = AnsReflectUtils.getClassByName("com.google.android.material.tabs.TabLayout$Tab");
            boolean zIsInstance = classByName != null ? classByName.isInstance(obj2) : false;
            Class<?> classByName2 = AnsReflectUtils.getClassByName("com.google.android.material.tabs.TabLayout$Tab");
            if (classByName2 != null) {
                zIsInstance = classByName2.isInstance(obj2);
            }
            if (zIsInstance) {
                Object field = AnsReflectUtils.getField(obj2, "view");
                if (field == null) {
                    field = AnsReflectUtils.getField(obj2, "mView");
                }
                Map<String, Object> map = new HashMap<>();
                map.put("$element_type", "TabLayout");
                if (a.a(obj)) {
                    obj3 = null;
                } else {
                    if (obj instanceof View) {
                        objC = a.c((View) obj);
                    } else {
                        if (field instanceof View) {
                            View view = (View) field;
                            objC = a.c(view);
                            String strA = a.a(view);
                            if (!TextUtils.isEmpty(strA)) {
                                map.put("$element_id", strA);
                            }
                        }
                        obj3 = null;
                    }
                    obj3 = objC;
                }
                if (checkTrackClickEnable(obj3, obj2, z)) {
                    Object objInvokeMethod = AnsReflectUtils.invokeMethod(obj2, "getText");
                    if (objInvokeMethod instanceof CharSequence) {
                        map.put("$element_content", objInvokeMethod.toString());
                    }
                    autoTrackClick(obj3, map, z, j);
                }
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    @Override
    public void trackViewOnClick(View view, boolean z, long j) {
        try {
            if (isTrackClickSwitchClose()) {
                return;
            }
            Object objC = a.c(view);
            if (checkTrackClickEnable(objC, view, z)) {
                Map<String, Object> map = new HashMap<>();
                Object[] objArrB = a.b(view);
                map.put("$element_type", objArrB[0]);
                map.put("$element_content", objArrB[1]);
                map.put("$element_path", PathGeneral.getInstance().general(view));
                String strA = a.a(view);
                if (!TextUtils.isEmpty(strA)) {
                    map.put("$element_id", strA);
                }
                autoTrackClick(objC, map, z, j);
            }
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }
}
