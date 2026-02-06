package com.obs.services.model;

import java.util.ArrayList;
import java.util.List;

public class WebsiteConfiguration extends HeaderResponse {
    private String key;
    private RedirectAllRequest redirectAllRequestsTo;
    private List<RouteRule> routeRules;
    private String suffix;

    public String getKey() {
        return this.key;
    }

    public RedirectAllRequest getRedirectAllRequestsTo() {
        return this.redirectAllRequestsTo;
    }

    public List<RouteRule> getRouteRules() {
        if (this.routeRules == null) {
            this.routeRules = new ArrayList();
        }
        return this.routeRules;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setRedirectAllRequestsTo(RedirectAllRequest redirectAllRequest) {
        this.redirectAllRequestsTo = redirectAllRequest;
    }

    public void setRouteRules(List<RouteRule> list) {
        this.routeRules = list;
    }

    public void setSuffix(String str) {
        this.suffix = str;
    }

    @Override
    public String toString() {
        return "WebsiteConfigration [suffix=" + this.suffix + ", key=" + this.key + ", redirectAllRequestsTo=" + this.redirectAllRequestsTo + ", routeRules=" + this.routeRules + "]";
    }
}
