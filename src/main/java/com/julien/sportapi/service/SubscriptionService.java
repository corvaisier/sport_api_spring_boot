package com.julien.sportapi.service;

import com.julien.sportapi.dao.Subscription.SubscriptionDao;
import com.julien.sportapi.domain.Subscription;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
    
    private SubscriptionDao subscriptionDao;

    public SubscriptionService(SubscriptionDao subscriptionDao) {
        this.subscriptionDao = subscriptionDao;
    }

    public void add() {
        Subscription subscription = new Subscription();
        subscriptionDao.add(subscription);
    }

    public List<Subscription> findAll() {
        return subscriptionDao.findAll();
    }

    public Subscription findById(Integer id) {
        return subscriptionDao.findById(id);
    }
}
