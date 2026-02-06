package com.analysys.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Binder;
import android.os.Process;
import android.text.TextUtils;
import com.analysys.AnsRamControl;
import com.analysys.d;
import com.analysys.f;
import com.analysys.ipc.IAnalysysMain;
import com.analysys.ipc.IpcManager;
import com.analysys.j;
import com.analysys.utils.ExceptionUtil;

public class AnsContentProvider extends ContentProvider {
    private static UriMatcher uriMatcher = new UriMatcher(-1);
    private Context mContext = null;
    private f sharedPreferencesHelp = null;

    private void checkDb() {
        d.a(this.mContext).b(this.mContext);
    }

    private void checkSp() {
        if (this.sharedPreferencesHelp == null) {
            this.sharedPreferencesHelp = new f();
        }
    }

    private void dataCorruptException() {
        this.mContext.deleteDatabase("analysys.data");
        dbReset();
        try {
            checkDb();
        } catch (Throwable th) {
            ExceptionUtil.exceptionThrow(th);
        }
    }

    private void dbReset() {
        d.a(this.mContext).a();
    }

    private int deleteDb(Uri uri, String str, String[] strArr) {
        SQLiteDatabase sQLiteDatabaseB = d.a(this.mContext).b(this.mContext);
        if (sQLiteDatabaseB != null) {
            return sQLiteDatabaseB.delete(" e_fz ", str, strArr);
        }
        return -3;
    }

    private Uri insertDb(Uri uri, ContentValues contentValues) {
        SQLiteDatabase sQLiteDatabaseB = d.a(this.mContext).b(this.mContext);
        return ContentUris.withAppendedId(uri, sQLiteDatabaseB != null ? sQLiteDatabaseB.insert(" e_fz ", null, contentValues) : -2L);
    }

    private Cursor queryDb(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteDatabase sQLiteDatabaseB = d.a(this.mContext).b(this.mContext);
        if (sQLiteDatabaseB != null) {
            return sQLiteDatabaseB.query(" e_fz ", strArr, str, strArr2, null, null, str2);
        }
        return null;
    }

    private void tableException(SQLiteException sQLiteException) {
        if (sQLiteException == null || sQLiteException.getMessage() == null || !sQLiteException.getMessage().contains("no such table")) {
            return;
        }
        dbReset();
    }

    private int updateDb(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        SQLiteDatabase sQLiteDatabaseB = d.a(this.mContext).b(this.mContext);
        if (sQLiteDatabaseB != null) {
            return sQLiteDatabaseB.update(" e_fz ", contentValues, str, strArr);
        }
        return -3;
    }

    @Override
    public int delete(Uri uri, String str, String[] strArr) {
        if (uri == null) {
            return -2;
        }
        if (Process.myUid() != Binder.getCallingUid()) {
            return -4;
        }
        int iDeleteDb = -3;
        if (uriMatcher.match(uri) == 0) {
            try {
                iDeleteDb = deleteDb(uri, str, strArr);
            } catch (SQLiteDatabaseCorruptException unused) {
                dataCorruptException();
            } catch (SQLiteException e) {
                tableException(e);
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
            }
        }
        Context context = this.mContext;
        if (context != null && context.getContentResolver() != null) {
            this.mContext.getContentResolver().notifyChange(uri, null);
        }
        return iDeleteDb;
    }

    @Override
    public String getType(Uri uri) {
        int iMatch = uriMatcher.match(uri);
        return iMatch != 0 ? iMatch != 1 ? iMatch != 2 ? iMatch != 3 ? "vnd.android.cursor.dir/vnd.analysys.e_fz" : "vnd.android.cursor.item/vnd.analysys.ipc" : "vnd.android.cursor.item/vnd.analysys.ram" : "vnd.android.cursor.item/vnd.analysys.sp" : "vnd.android.cursor.dir/vnd.analysys.e_fz";
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        Uri uriInsertDb;
        Context context;
        SharedPreferences.Editor editorB;
        if (uri == null || uriMatcher == null || Process.myUid() != Binder.getCallingUid()) {
            return uri;
        }
        int iMatch = uriMatcher.match(uri);
        if (iMatch == 0) {
            synchronized (this) {
                if (contentValues != null) {
                    try {
                        try {
                            try {
                            } catch (SQLiteException e) {
                                tableException(e);
                                try {
                                    uriInsertDb = insertDb(uri, contentValues);
                                } catch (Throwable th) {
                                    ExceptionUtil.exceptionThrow(th);
                                    uriInsertDb = uri;
                                    context = this.mContext;
                                    if (context != null) {
                                    }
                                    return uriInsertDb;
                                }
                            }
                        } catch (Throwable th2) {
                            ExceptionUtil.exceptionThrow(th2);
                            uriInsertDb = uri;
                            context = this.mContext;
                            if (context != null) {
                            }
                            return uriInsertDb;
                        }
                    } catch (SQLiteDatabaseCorruptException unused) {
                        dataCorruptException();
                        try {
                            uriInsertDb = insertDb(uri, contentValues);
                        } catch (Throwable th3) {
                            ExceptionUtil.exceptionThrow(th3);
                            uriInsertDb = uri;
                            context = this.mContext;
                            if (context != null) {
                                this.mContext.getContentResolver().notifyChange(uri, null);
                            }
                            return uriInsertDb;
                        }
                    }
                    if (contentValues.size() != 0) {
                        uriInsertDb = insertDb(uri, contentValues);
                    }
                }
                return uri;
            }
        }
        if (iMatch == 1) {
            try {
                checkSp();
                if (this.mContext != null && this.sharedPreferencesHelp != null && (editorB = this.sharedPreferencesHelp.b(this.mContext)) != null) {
                    if (contentValues.getAsString("type").equals("boolean")) {
                        editorB.putBoolean(contentValues.getAsString("key"), contentValues.getAsBoolean("values").booleanValue()).commit();
                    } else if (contentValues.getAsString("type").equals("int")) {
                        editorB.putInt(contentValues.getAsString("key"), contentValues.getAsInteger("values").intValue()).commit();
                    } else if (contentValues.getAsString("type").equals("float")) {
                        editorB.putFloat(contentValues.getAsString("key"), contentValues.getAsFloat("values").floatValue()).commit();
                    } else if (contentValues.getAsString("type").equals("long")) {
                        editorB.putLong(contentValues.getAsString("key"), contentValues.getAsLong("values").longValue()).commit();
                    } else if (contentValues.getAsString("type").equals("string")) {
                        editorB.putString(contentValues.getAsString("key"), contentValues.getAsString("values")).commit();
                    }
                }
            } catch (Throwable th4) {
                ExceptionUtil.exceptionThrow(th4);
            }
        } else if (iMatch == 2) {
            try {
                AnsRamControl.a.a().b().put(contentValues.getAsString("key"), contentValues.getAsString("values"));
            } catch (Throwable th5) {
                ExceptionUtil.exceptionThrow(th5);
            }
        }
        uriInsertDb = uri;
        context = this.mContext;
        if (context != null && context.getContentResolver() != null) {
            this.mContext.getContentResolver().notifyChange(uri, null);
        }
        return uriInsertDb;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        this.mContext = context;
        if (context != null) {
            String str = this.mContext.getPackageName() + ".AnsContentProvider";
            uriMatcher.addURI(str, " e_fz ", 0);
            uriMatcher.addURI(str, "sp", 1);
            uriMatcher.addURI(str, "RAM", 2);
            uriMatcher.addURI(str, "ipc", 3);
        }
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        MatrixCursor matrixCursor;
        String str3;
        SharedPreferences sharedPreferencesA;
        String str4 = null;
        if (uri == null || Process.myUid() != Binder.getCallingUid()) {
            return null;
        }
        int iMatch = uriMatcher.match(uri);
        if (iMatch == 0) {
            try {
                return queryDb(uri, strArr, str, strArr2, str2);
            } catch (SQLiteDatabaseCorruptException unused) {
                dataCorruptException();
                return null;
            } catch (SQLiteException e) {
                tableException(e);
                return null;
            } catch (Throwable th) {
                ExceptionUtil.exceptionThrow(th);
                return null;
            }
        }
        if (iMatch != 1) {
            if (iMatch != 2) {
                if (iMatch != 3) {
                    return null;
                }
                IAnalysysMain mainBinder = IpcManager.getInstance().getMainBinder();
                return new j(mainBinder != null ? mainBinder.asBinder() : null);
            }
            try {
                String str5 = strArr[0];
                String str6 = strArr[1];
                String str7 = AnsRamControl.a.a().b().get(str5);
                MatrixCursor matrixCursor2 = new MatrixCursor(new String[]{"column_name"});
                if (str7 != null) {
                    matrixCursor2.addRow(new Object[]{str7});
                } else {
                    matrixCursor2.addRow(new Object[]{str6});
                }
                return matrixCursor2;
            } catch (Throwable th2) {
                ExceptionUtil.exceptionThrow(th2);
                return null;
            }
        }
        String string = "";
        if (strArr != null) {
            try {
                if (strArr.length >= 2) {
                    str4 = strArr[0];
                    str3 = strArr[1];
                } else {
                    str3 = null;
                }
                checkSp();
                if (this.mContext != null && (sharedPreferencesA = this.sharedPreferencesHelp.a(this.mContext)) != null && str4 != null && str3 != null) {
                    if (str3.equals("boolean")) {
                        if (sharedPreferencesA.contains(str4)) {
                            string = String.valueOf(sharedPreferencesA.getBoolean(str4, false));
                        }
                    } else if (str3.equals("int")) {
                        if (sharedPreferencesA.contains(str4)) {
                            string = String.valueOf(sharedPreferencesA.getInt(str4, 0));
                        }
                    } else if (str3.equals("float")) {
                        if (sharedPreferencesA.contains(str4)) {
                            string = String.valueOf(sharedPreferencesA.getFloat(str4, 0.0f));
                        }
                    } else if (str3.equals("long")) {
                        if (sharedPreferencesA.contains(str4)) {
                            string = String.valueOf(sharedPreferencesA.getLong(str4, 0L));
                        }
                    } else if (str3.equals("string") && sharedPreferencesA.contains(str4)) {
                        string = sharedPreferencesA.getString(str4, str);
                    }
                }
                matrixCursor = new MatrixCursor(new String[]{"column_name"});
                matrixCursor.addRow(new Object[]{string});
            } catch (Throwable th3) {
                try {
                    ExceptionUtil.exceptionThrow(th3);
                    matrixCursor = new MatrixCursor(new String[]{"column_name"});
                    matrixCursor.addRow(new Object[]{""});
                } catch (Throwable th4) {
                    new MatrixCursor(new String[]{"column_name"}).addRow(new Object[]{""});
                    throw th4;
                }
            }
        }
        return matrixCursor;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        SharedPreferences.Editor editorB;
        if (uri == null || contentValues == null) {
            return -2;
        }
        if (Process.myUid() != Binder.getCallingUid()) {
            return -4;
        }
        int iUpdateDb = -3;
        try {
            int iMatch = uriMatcher.match(uri);
            if (iMatch == 0) {
                try {
                    iUpdateDb = updateDb(uri, contentValues, str, strArr);
                } catch (SQLiteDatabaseCorruptException unused) {
                    dataCorruptException();
                } catch (SQLiteException e) {
                    tableException(e);
                } catch (Throwable th) {
                    ExceptionUtil.exceptionThrow(th);
                }
            } else if (iMatch == 1) {
                checkSp();
                if (this.mContext != null && (editorB = this.sharedPreferencesHelp.b(this.mContext)) != null) {
                    String asString = contentValues.getAsString("key");
                    if (!TextUtils.isEmpty(asString)) {
                        editorB.remove(asString).commit();
                    }
                }
            }
            if (this.mContext != null && this.mContext.getContentResolver() != null) {
                this.mContext.getContentResolver().notifyChange(uri, null);
            }
        } catch (Throwable th2) {
            ExceptionUtil.exceptionThrow(th2);
        }
        return iUpdateDb;
    }
}
