package com.future.hazelcastcaching.service;

import com.future.hazelcastcaching.client.CacheClient;
import com.future.hazelcastcaching.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserCacheImpl implements CacheManager<User> {
    private CacheClient <User> cacheClient;
    private static final String USER = "users";

    @Autowired
    public UserCacheImpl(CacheClient<User> cacheClient) {
        this.cacheClient = cacheClient;
        cacheClient.createClient(USER);
    }

    @Override
    public User put(User user) {
        return cacheClient.put(user.getId(),user,USER);
    }

    @Override
    public User get(long key) {
        return cacheClient.get(key,USER);
    }

    @Override
    public Map<Long, User> getList() {
        return cacheClient.getList(USER);
    }
}
