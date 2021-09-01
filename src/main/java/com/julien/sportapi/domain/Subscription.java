package com.julien.sportapi.domain;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

public class Subscription {
    private Date subscription_start_date;
    private Map<Integer, String> type_of_subscription;

    public Subscription() {
        this.subscription_start_date = new Date();
        this.type_of_subscription = new HashMap<>();
        type_of_subscription.put(1, "abonnement 1");
        type_of_subscription.put(2, "abonnement 2");
    }

    public Date getSubscription_start_date() {
        return subscription_start_date;
    }

    public void setSubscription_start_date(Date subscription_start_date) {
        this.subscription_start_date = subscription_start_date;
    }

    public Map<Integer, String> getType_of_subscription() {
        return type_of_subscription;
    }

    public void setType_of_subscription(Map<Integer, String> type_of_subscription) {
        this.type_of_subscription = type_of_subscription;
    }

    public String toString() {
        return "Subscription{" +
                "start=" + subscription_start_date +
                ", typeOf='" + type_of_subscription + '\'' +
                '}';
    }
    
}
