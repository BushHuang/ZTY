package androidx.lifecycle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ViewModelStore {
    private final HashMap<String, ViewModel> mMap = new HashMap<>();

    public final void clear() {
        Iterator<ViewModel> it = this.mMap.values().iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
        this.mMap.clear();
    }

    final ViewModel get(String str) {
        return this.mMap.get(str);
    }

    Set<String> keys() {
        return new HashSet(this.mMap.keySet());
    }

    final void put(String str, ViewModel viewModel) {
        ViewModel viewModelPut = this.mMap.put(str, viewModel);
        if (viewModelPut != null) {
            viewModelPut.onCleared();
        }
    }
}
