package butterknife.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class ViewBindings {
    private final int id;
    private final Set<FieldViewBinding> fieldBindings = new LinkedHashSet();
    private final LinkedHashMap<ListenerClass, Map<ListenerMethod, Set<MethodViewBinding>>> methodBindings = new LinkedHashMap<>();

    ViewBindings(int i) {
        this.id = i;
    }

    public void addFieldBinding(FieldViewBinding fieldViewBinding) {
        this.fieldBindings.add(fieldViewBinding);
    }

    public void addMethodBinding(ListenerClass listenerClass, ListenerMethod listenerMethod, MethodViewBinding methodViewBinding) {
        Set<MethodViewBinding> linkedHashSet;
        Map<ListenerMethod, Set<MethodViewBinding>> linkedHashMap = this.methodBindings.get(listenerClass);
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap<>();
            this.methodBindings.put(listenerClass, linkedHashMap);
            linkedHashSet = null;
        } else {
            linkedHashSet = linkedHashMap.get(listenerMethod);
        }
        if (linkedHashSet == null) {
            linkedHashSet = new LinkedHashSet<>();
            linkedHashMap.put(listenerMethod, linkedHashSet);
        }
        linkedHashSet.add(methodViewBinding);
    }

    public Collection<FieldViewBinding> getFieldBindings() {
        return this.fieldBindings;
    }

    public int getId() {
        return this.id;
    }

    public Map<ListenerClass, Map<ListenerMethod, Set<MethodViewBinding>>> getMethodBindings() {
        return this.methodBindings;
    }

    public List<ViewBinding> getRequiredBindings() {
        ArrayList arrayList = new ArrayList();
        for (FieldViewBinding fieldViewBinding : this.fieldBindings) {
            if (fieldViewBinding.isRequired()) {
                arrayList.add(fieldViewBinding);
            }
        }
        Iterator<Map<ListenerMethod, Set<MethodViewBinding>>> it = this.methodBindings.values().iterator();
        while (it.hasNext()) {
            Iterator<Set<MethodViewBinding>> it2 = it.next().values().iterator();
            while (it2.hasNext()) {
                for (MethodViewBinding methodViewBinding : it2.next()) {
                    if (methodViewBinding.isRequired()) {
                        arrayList.add(methodViewBinding);
                    }
                }
            }
        }
        return arrayList;
    }

    public boolean hasMethodBinding(ListenerClass listenerClass, ListenerMethod listenerMethod) {
        Map<ListenerMethod, Set<MethodViewBinding>> map = this.methodBindings.get(listenerClass);
        return map != null && map.containsKey(listenerMethod);
    }
}
