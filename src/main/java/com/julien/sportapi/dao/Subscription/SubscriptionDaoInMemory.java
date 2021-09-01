package com.julien.sportapi.dao.Subscription;

import com.julien.sportapi.domain.Subscription;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SubscriptionDaoInMemory implements SubscriptionDao {
    private List<Subscription> subscriptions = new ArrayList<>();

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<Subscription> findAll() {
        return subscriptions;
    }

    public Subscription findById(Integer id) {
        return subscriptions.get(id);
    }

    public void add(Subscription subscription) {
        subscriptions.add(subscription);
    }

}
