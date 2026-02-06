package com.jamesmurty.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.NamespaceContext;
import org.w3c.dom.Element;

public class NamespaceContextImpl implements NamespaceContext {
    protected Element element;
    protected Map<String, Set<String>> nsUriToPrefixesMap;
    protected Map<String, String> prefixToNsUriMap;

    public NamespaceContextImpl() {
        this.element = null;
        this.prefixToNsUriMap = new HashMap();
        this.nsUriToPrefixesMap = new HashMap();
    }

    public NamespaceContextImpl(Element element) {
        this.element = null;
        this.prefixToNsUriMap = new HashMap();
        this.nsUriToPrefixesMap = new HashMap();
        this.element = element;
    }

    public void addNamespace(String str, String str2) {
        this.prefixToNsUriMap.put(str, str2);
        if (this.nsUriToPrefixesMap.get(str2) == null) {
            this.nsUriToPrefixesMap.put(str2, new HashSet());
        }
        this.nsUriToPrefixesMap.get(str2).add(str);
    }

    @Override
    public String getNamespaceURI(String str) {
        String str2 = this.prefixToNsUriMap.get(str);
        if (str2 != null || this.element == null) {
            return str2;
        }
        if (str != null && str.length() == 0) {
            str = null;
        }
        return this.element.lookupNamespaceURI(str);
    }

    @Override
    public String getPrefix(String str) {
        Set<String> set = this.nsUriToPrefixesMap.get(str);
        if (set != null && set.size() > 0) {
            return set.iterator().next();
        }
        Element element = this.element;
        if (element != null) {
            return element.lookupPrefix(str);
        }
        return null;
    }

    @Override
    public Iterator getPrefixes(String str) {
        return Collections.EMPTY_LIST.iterator();
    }
}
