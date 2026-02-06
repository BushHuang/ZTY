package com.obs.services.model;

import java.util.ArrayList;
import java.util.List;

public class BucketTagInfo extends HeaderResponse {
    private TagSet tagSet;

    public static class TagSet {
        private List<Tag> tags;

        public static class Tag {
            private String key;
            private String value;

            public Tag() {
            }

            public Tag(String str, String str2) {
                this.key = str;
                this.value = str2;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Tag tag = (Tag) obj;
                String str = this.key;
                if (str == null) {
                    if (tag.key != null) {
                        return false;
                    }
                } else if (!str.equals(tag.key)) {
                    return false;
                }
                String str2 = this.value;
                if (str2 == null) {
                    if (tag.value != null) {
                        return false;
                    }
                } else if (!str2.equals(tag.value)) {
                    return false;
                }
                return true;
            }

            public String getKey() {
                return this.key;
            }

            public String getValue() {
                return this.value;
            }

            public int hashCode() {
                String str = this.key;
                int iHashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
                String str2 = this.value;
                return iHashCode + (str2 != null ? str2.hashCode() : 0);
            }

            public void setKey(String str) {
                this.key = str;
            }

            public void setValue(String str) {
                this.value = str;
            }
        }

        public Tag addTag(String str, String str2) {
            Tag tag = new Tag(str, str2);
            getTags().add(tag);
            return tag;
        }

        public List<Tag> getTags() {
            if (this.tags == null) {
                this.tags = new ArrayList();
            }
            return this.tags;
        }

        public Tag removeTag(String str, String str2) {
            Tag tag = new Tag(str, str2);
            getTags().remove(tag);
            return tag;
        }

        public Tag removeTagByKey(String str) {
            for (Tag tag : this.tags) {
                if (tag.getKey().equals(str)) {
                    removeTag(tag.getKey(), tag.getValue());
                    return tag;
                }
            }
            return null;
        }
    }

    public BucketTagInfo() {
    }

    public BucketTagInfo(TagSet tagSet) {
        this.tagSet = tagSet;
    }

    public TagSet getTagSet() {
        if (this.tagSet == null) {
            this.tagSet = new TagSet();
        }
        return this.tagSet;
    }

    public void setTagSet(TagSet tagSet) {
        this.tagSet = tagSet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        TagSet tagSet = this.tagSet;
        if (tagSet != null) {
            int i = 0;
            for (TagSet.Tag tag : tagSet.getTags()) {
                sb.append("[");
                sb.append("key=");
                sb.append(tag.getKey());
                sb.append(",");
                sb.append("value=");
                sb.append(tag.getValue());
                sb.append("]");
                int i2 = i + 1;
                if (i != this.tagSet.getTags().size() - 1) {
                    sb.append(",");
                }
                i = i2;
            }
        }
        sb.append("]");
        return "BucketTagInfo [tagSet=[tags=" + sb.toString() + "]";
    }
}
