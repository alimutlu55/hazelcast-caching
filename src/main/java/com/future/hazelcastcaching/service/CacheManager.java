package com.future.hazelcastcaching.service;

import com.future.hazelcastcaching.model.User;

import java.util.List;
import java.util.Map;

public interface CacheManager<T> {
    T put(T object);
    T get(long key);
    Map<Long, T> getList();
}
