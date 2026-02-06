package com.obs.services.model;

import com.obs.services.internal.ObsConvertor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AbstractNotification extends HeaderResponse {
    protected List<EventTypeEnum> events;
    protected Filter filter;
    protected String id;

    public static class Filter {
        private List<FilterRule> filterRules;

        public static class FilterRule {
            private String name;
            private String value;

            public FilterRule() {
            }

            public FilterRule(String str, String str2) {
                this.name = str;
                this.value = str2;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                FilterRule filterRule = (FilterRule) obj;
                String str = this.name;
                if (str == null) {
                    if (filterRule.name != null) {
                        return false;
                    }
                } else if (!str.equals(filterRule.name)) {
                    return false;
                }
                String str2 = this.value;
                if (str2 == null) {
                    if (filterRule.value != null) {
                        return false;
                    }
                } else if (!str2.equals(filterRule.value)) {
                    return false;
                }
                return true;
            }

            public String getName() {
                return this.name;
            }

            public String getValue() {
                return this.value;
            }

            public int hashCode() {
                String str = this.name;
                int iHashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
                String str2 = this.value;
                return iHashCode + (str2 != null ? str2.hashCode() : 0);
            }

            public void setName(String str) {
                this.name = str;
            }

            public void setValue(String str) {
                this.value = str;
            }

            public String toString() {
                return "FilterRule [name=" + this.name + ", value=" + this.value + "]";
            }
        }

        public void addFilterRule(String str, String str2) {
            getFilterRules().add(new FilterRule(str, str2));
        }

        public List<FilterRule> getFilterRules() {
            if (this.filterRules == null) {
                this.filterRules = new ArrayList();
            }
            return this.filterRules;
        }

        public void setFilterRules(List<FilterRule> list) {
            this.filterRules = list;
        }

        public String toString() {
            return "Filter [fileterRules=" + this.filterRules + "]";
        }
    }

    public AbstractNotification() {
    }

    public AbstractNotification(String str, Filter filter, List<EventTypeEnum> list) {
        this.id = str;
        this.filter = filter;
        this.events = list;
    }

    public List<EventTypeEnum> getEventTypes() {
        if (this.events == null) {
            this.events = new ArrayList();
        }
        return this.events;
    }

    @Deprecated
    public List<String> getEvents() {
        ArrayList arrayList = new ArrayList();
        Iterator<EventTypeEnum> it = getEventTypes().iterator();
        while (it.hasNext()) {
            arrayList.add(ObsConvertor.transEventTypeStatic(it.next()));
        }
        return arrayList;
    }

    public Filter getFilter() {
        return this.filter;
    }

    public String getId() {
        return this.id;
    }

    public void setEventTypes(List<EventTypeEnum> list) {
        this.events = list;
    }

    @Deprecated
    public void setEvents(List<String> list) {
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                EventTypeEnum valueFromCode = EventTypeEnum.getValueFromCode(it.next());
                if (valueFromCode != null) {
                    getEventTypes().add(valueFromCode);
                }
            }
        }
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public void setId(String str) {
        this.id = str;
    }
}
