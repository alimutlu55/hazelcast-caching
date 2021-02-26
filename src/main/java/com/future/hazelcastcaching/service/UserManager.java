package com.future.hazelcastcaching.service;

import com.future.hazelcastcaching.model.User;

import java.util.Map;

public interface UserManager {
    User save(User user);
    User findById(long id);
    Map<Long, User> getList();
}
