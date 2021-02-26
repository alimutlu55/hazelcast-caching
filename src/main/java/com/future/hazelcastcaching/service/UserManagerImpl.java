package com.future.hazelcastcaching.service;

import com.future.hazelcastcaching.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserManagerImpl implements UserManager {
    private CacheManager cacheManager;

    @Autowired
    public UserManagerImpl(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    @Override
    public User save(User user) {
        return (User) cacheManager.put(user);
    }

    @Override
    public User findById(long id) {
        return (User) cacheManager.get(id);
    }

    @Override
    public Map<Long,User> getList() {
        return cacheManager.getList();
    }
}
