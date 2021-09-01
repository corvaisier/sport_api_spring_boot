package com.julien.sportapi.dao.Subscription;

import com.julien.sportapi.domain.Subscription;

import java.util.List;


public interface SubscriptionDao {
    List<Subscription> findAll();
    Subscription findById(Integer id);
    void add(Subscription subscription);
}
