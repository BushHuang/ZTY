package com.obs.services.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LifecycleConfiguration extends HeaderResponse {
    private List<Rule> rules;

    public class Expiration extends TimeEvent {
        public Expiration() {
            super();
        }

        public Expiration(Integer num) {
            super(num);
        }

        public Expiration(Date date) {
            super(date);
        }

        public Date getDate() {
            return this.date;
        }

        public Integer getDays() {
            return this.days;
        }

        public void setDate(Date date) {
            this.date = date;
            this.days = null;
        }

        public void setDays(Integer num) {
            this.days = num;
            this.date = null;
        }

        public String toString() {
            return "Expiration [days=" + this.days + ", date=" + this.date + "]";
        }
    }

    public class NoncurrentVersionExpiration extends TimeEvent {
        public NoncurrentVersionExpiration() {
            super();
        }

        public NoncurrentVersionExpiration(Integer num) {
            super();
            setDays(num);
        }

        public Integer getDays() {
            return this.days;
        }

        public void setDays(Integer num) {
            this.days = num;
        }

        public String toString() {
            return "NoncurrentVersionExpiration [days=" + this.days + "]";
        }
    }

    public class NoncurrentVersionTransition extends TimeEvent {
        public NoncurrentVersionTransition() {
            super();
        }

        public NoncurrentVersionTransition(Integer num, StorageClassEnum storageClassEnum) {
            super();
            setDays(num);
            this.storageClass = storageClassEnum;
        }

        @Deprecated
        public NoncurrentVersionTransition(Integer num, String str) {
            super();
            setDays(num);
            this.storageClass = StorageClassEnum.getValueFromCode(str);
        }

        public Integer getDays() {
            return this.days;
        }

        public StorageClassEnum getObjectStorageClass() {
            return this.storageClass;
        }

        @Deprecated
        public String getStorageClass() {
            if (this.storageClass != null) {
                return this.storageClass.getCode();
            }
            return null;
        }

        public void setDays(Integer num) {
            this.days = num;
        }

        public void setObjectStorageClass(StorageClassEnum storageClassEnum) {
            this.storageClass = storageClassEnum;
        }

        @Deprecated
        public void setStorageClass(String str) {
            this.storageClass = StorageClassEnum.getValueFromCode(str);
        }

        public String toString() {
            return "NoncurrentVersionTransition [days=" + this.days + ", storageClass=" + this.storageClass + "]";
        }
    }

    public class Rule {
        protected Boolean enabled;
        protected Expiration expiration;
        protected String id;
        protected NoncurrentVersionExpiration noncurrentVersionExpiration;
        protected List<NoncurrentVersionTransition> noncurrentVersionTransitions;
        protected String prefix;
        protected List<Transition> transitions;

        public Rule() {
        }

        public Rule(String str, String str2, Boolean bool) {
            this.id = str;
            this.prefix = str2;
            this.enabled = bool;
        }

        private LifecycleConfiguration getOuterType() {
            return LifecycleConfiguration.this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Rule rule = (Rule) obj;
            if (!getOuterType().equals(rule.getOuterType())) {
                return false;
            }
            Boolean bool = this.enabled;
            if (bool == null) {
                if (rule.enabled != null) {
                    return false;
                }
            } else if (!bool.equals(rule.enabled)) {
                return false;
            }
            Expiration expiration = this.expiration;
            if (expiration == null) {
                if (rule.expiration != null) {
                    return false;
                }
            } else if (!expiration.equals(rule.expiration)) {
                return false;
            }
            String str = this.id;
            if (str == null) {
                if (rule.id != null) {
                    return false;
                }
            } else if (!str.equals(rule.id)) {
                return false;
            }
            NoncurrentVersionExpiration noncurrentVersionExpiration = this.noncurrentVersionExpiration;
            if (noncurrentVersionExpiration == null) {
                if (rule.noncurrentVersionExpiration != null) {
                    return false;
                }
            } else if (!noncurrentVersionExpiration.equals(rule.noncurrentVersionExpiration)) {
                return false;
            }
            List<NoncurrentVersionTransition> list = this.noncurrentVersionTransitions;
            if (list == null) {
                if (rule.noncurrentVersionTransitions != null) {
                    return false;
                }
            } else if (!list.equals(rule.noncurrentVersionTransitions)) {
                return false;
            }
            String str2 = this.prefix;
            if (str2 == null) {
                if (rule.prefix != null) {
                    return false;
                }
            } else if (!str2.equals(rule.prefix)) {
                return false;
            }
            List<Transition> list2 = this.transitions;
            if (list2 == null) {
                if (rule.transitions != null) {
                    return false;
                }
            } else if (!list2.equals(rule.transitions)) {
                return false;
            }
            return true;
        }

        public Boolean getEnabled() {
            return this.enabled;
        }

        public Expiration getExpiration() {
            return this.expiration;
        }

        public String getId() {
            return this.id;
        }

        public NoncurrentVersionExpiration getNoncurrentVersionExpiration() {
            return this.noncurrentVersionExpiration;
        }

        public List<NoncurrentVersionTransition> getNoncurrentVersionTransitions() {
            if (this.noncurrentVersionTransitions == null) {
                this.noncurrentVersionTransitions = new ArrayList();
            }
            return this.noncurrentVersionTransitions;
        }

        public String getPrefix() {
            return this.prefix;
        }

        public List<Transition> getTransitions() {
            if (this.transitions == null) {
                this.transitions = new ArrayList();
            }
            return this.transitions;
        }

        public int hashCode() {
            int iHashCode = (getOuterType().hashCode() + 31) * 31;
            Boolean bool = this.enabled;
            int iHashCode2 = (iHashCode + (bool == null ? 0 : bool.hashCode())) * 31;
            Expiration expiration = this.expiration;
            int iHashCode3 = (iHashCode2 + (expiration == null ? 0 : expiration.hashCode())) * 31;
            String str = this.id;
            int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
            NoncurrentVersionExpiration noncurrentVersionExpiration = this.noncurrentVersionExpiration;
            int iHashCode5 = (iHashCode4 + (noncurrentVersionExpiration == null ? 0 : noncurrentVersionExpiration.hashCode())) * 31;
            List<NoncurrentVersionTransition> list = this.noncurrentVersionTransitions;
            int iHashCode6 = (iHashCode5 + (list == null ? 0 : list.hashCode())) * 31;
            String str2 = this.prefix;
            int iHashCode7 = (iHashCode6 + (str2 == null ? 0 : str2.hashCode())) * 31;
            List<Transition> list2 = this.transitions;
            return iHashCode7 + (list2 != null ? list2.hashCode() : 0);
        }

        public Expiration newExpiration() {
            Expiration expiration = LifecycleConfiguration.this.new Expiration();
            this.expiration = expiration;
            return expiration;
        }

        public NoncurrentVersionExpiration newNoncurrentVersionExpiration() {
            NoncurrentVersionExpiration noncurrentVersionExpiration = LifecycleConfiguration.this.new NoncurrentVersionExpiration();
            this.noncurrentVersionExpiration = noncurrentVersionExpiration;
            return noncurrentVersionExpiration;
        }

        public NoncurrentVersionTransition newNoncurrentVersionTransition() {
            if (this.noncurrentVersionTransitions == null) {
                this.noncurrentVersionTransitions = new ArrayList();
            }
            NoncurrentVersionTransition noncurrentVersionTransition = LifecycleConfiguration.this.new NoncurrentVersionTransition();
            this.noncurrentVersionTransitions.add(noncurrentVersionTransition);
            return noncurrentVersionTransition;
        }

        public Transition newTransition() {
            if (this.transitions == null) {
                this.transitions = new ArrayList();
            }
            Transition transition = LifecycleConfiguration.this.new Transition();
            this.transitions.add(transition);
            return transition;
        }

        public void setEnabled(Boolean bool) {
            this.enabled = bool;
        }

        public void setExpiration(Expiration expiration) {
            this.expiration = expiration;
        }

        public void setId(String str) {
            this.id = str;
        }

        public void setNoncurrentVersionExpiration(NoncurrentVersionExpiration noncurrentVersionExpiration) {
            this.noncurrentVersionExpiration = noncurrentVersionExpiration;
        }

        public void setNoncurrentVersionTransitions(List<NoncurrentVersionTransition> list) {
            this.noncurrentVersionTransitions = list;
        }

        public void setPrefix(String str) {
            this.prefix = str;
        }

        public void setTransitions(List<Transition> list) {
            this.transitions = list;
        }

        public String toString() {
            return "Rule [id=" + this.id + ", prefix=" + this.prefix + ", enabled=" + this.enabled + ", expiration=" + this.expiration + ", noncurrentVersionExpiration=" + this.noncurrentVersionExpiration + ", transitions=" + this.transitions + ", noncurrentVersionTransitions=" + this.noncurrentVersionTransitions + "]";
        }
    }

    public abstract class TimeEvent {
        protected Date date;
        protected Integer days;
        protected StorageClassEnum storageClass;

        public TimeEvent() {
        }

        protected TimeEvent(Integer num) {
            this.days = num;
        }

        protected TimeEvent(Date date) {
            this.date = date;
        }

        private LifecycleConfiguration getOuterType() {
            return LifecycleConfiguration.this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            TimeEvent timeEvent = (TimeEvent) obj;
            if (!getOuterType().equals(timeEvent.getOuterType())) {
                return false;
            }
            Date date = this.date;
            if (date == null) {
                if (timeEvent.date != null) {
                    return false;
                }
            } else if (!date.equals(timeEvent.date)) {
                return false;
            }
            Integer num = this.days;
            if (num == null) {
                if (timeEvent.days != null) {
                    return false;
                }
            } else if (!num.equals(timeEvent.days)) {
                return false;
            }
            return this.storageClass == timeEvent.storageClass;
        }

        public int hashCode() {
            int iHashCode = (getOuterType().hashCode() + 31) * 31;
            Date date = this.date;
            int iHashCode2 = (iHashCode + (date == null ? 0 : date.hashCode())) * 31;
            Integer num = this.days;
            int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
            StorageClassEnum storageClassEnum = this.storageClass;
            return iHashCode3 + (storageClassEnum != null ? storageClassEnum.hashCode() : 0);
        }
    }

    public class Transition extends TimeEvent {
        public Transition() {
            super();
        }

        public Transition(Integer num, StorageClassEnum storageClassEnum) {
            super(num);
            this.storageClass = storageClassEnum;
        }

        @Deprecated
        public Transition(Integer num, String str) {
            super(num);
            this.storageClass = StorageClassEnum.getValueFromCode(str);
        }

        public Transition(Date date, StorageClassEnum storageClassEnum) {
            super(date);
            this.storageClass = storageClassEnum;
        }

        @Deprecated
        public Transition(Date date, String str) {
            super(date);
            this.storageClass = StorageClassEnum.getValueFromCode(str);
        }

        public Date getDate() {
            return this.date;
        }

        public Integer getDays() {
            return this.days;
        }

        public StorageClassEnum getObjectStorageClass() {
            return this.storageClass;
        }

        @Deprecated
        public String getStorageClass() {
            if (this.storageClass != null) {
                return this.storageClass.getCode();
            }
            return null;
        }

        public void setDate(Date date) {
            this.date = date;
            this.days = null;
        }

        public void setDays(Integer num) {
            this.days = num;
            this.date = null;
        }

        public void setObjectStorageClass(StorageClassEnum storageClassEnum) {
            this.storageClass = storageClassEnum;
        }

        @Deprecated
        public void setStorageClass(String str) {
            this.storageClass = StorageClassEnum.getValueFromCode(str);
        }

        public String toString() {
            return "Transition [days=" + this.days + ", date=" + this.date + ", storageClass=" + this.storageClass + "]";
        }
    }

    public LifecycleConfiguration() {
    }

    public LifecycleConfiguration(List<Rule> list) {
        this.rules = list;
    }

    public static void setDate(TimeEvent timeEvent, Date date) {
        if (timeEvent != null) {
            timeEvent.date = date;
        }
    }

    public static void setDays(TimeEvent timeEvent, Integer num) {
        if (timeEvent != null) {
            timeEvent.days = num;
        }
    }

    public static void setStorageClass(TimeEvent timeEvent, StorageClassEnum storageClassEnum) {
        if (timeEvent != null) {
            timeEvent.storageClass = storageClassEnum;
        }
    }

    public void addRule(Rule rule) {
        if (getRules().contains(rule)) {
            return;
        }
        getRules().add(rule);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        List<Rule> list = this.rules;
        List<Rule> list2 = ((LifecycleConfiguration) obj).rules;
        return list == null ? list2 == null : list.equals(list2);
    }

    public List<Rule> getRules() {
        if (this.rules == null) {
            this.rules = new ArrayList();
        }
        return this.rules;
    }

    public int hashCode() {
        List<Rule> list = this.rules;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    public Rule newRule(String str, String str2, Boolean bool) {
        Rule rule = new Rule(str, str2, bool);
        getRules().add(rule);
        return rule;
    }

    @Override
    public String toString() {
        return "LifecycleConfiguration [rules=" + this.rules + "]";
    }
}
