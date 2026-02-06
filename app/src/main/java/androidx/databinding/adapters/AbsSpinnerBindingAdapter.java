package androidx.databinding.adapters;

import android.widget.AbsSpinner;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import java.util.List;

public class AbsSpinnerBindingAdapter {
    public static <T> void setEntries(AbsSpinner absSpinner, List<T> list) {
        if (list == null) {
            absSpinner.setAdapter((SpinnerAdapter) null);
            return;
        }
        SpinnerAdapter adapter = absSpinner.getAdapter();
        if (adapter instanceof ObservableListAdapter) {
            ((ObservableListAdapter) adapter).setList(list);
        } else {
            absSpinner.setAdapter((SpinnerAdapter) new ObservableListAdapter(absSpinner.getContext(), list, 17367048, 17367049, 0));
        }
    }

    public static <T extends CharSequence> void setEntries(AbsSpinner absSpinner, T[] tArr) {
        if (tArr == null) {
            absSpinner.setAdapter((SpinnerAdapter) null);
            return;
        }
        SpinnerAdapter adapter = absSpinner.getAdapter();
        boolean z = false;
        if (adapter == null || adapter.getCount() != tArr.length) {
            z = true;
            break;
        }
        for (int i = 0; i < tArr.length; i++) {
            if (!tArr[i].equals(adapter.getItem(i))) {
                z = true;
                break;
            }
        }
        if (z) {
            ArrayAdapter arrayAdapter = new ArrayAdapter(absSpinner.getContext(), 17367048, tArr);
            arrayAdapter.setDropDownViewResource(17367049);
            absSpinner.setAdapter((SpinnerAdapter) arrayAdapter);
        }
    }
}
