package com.future.hazelcastcaching.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.collection.IList;
import com.hazelcast.config.NearCacheConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.stereotype.Component;

@Component
public class CacheClient <T> {

    private HazelcastInstance client;

    public void createClient (String mapName) {
        client = HazelcastClient.newHazelcastClient(createClientConfig(mapName));
    }

    public T put(long key, T object,String mapName) {
        IMap<Long, T> map = client.getMap(mapName);
        return map.putIfAbsent(key, object);
    }

    public T get(long key,String mapName) {
        IMap<Long, T> map = client.getMap(mapName);
        return map.get(key);
    }

    public IMap<Long, T> getList (String mapName) {
        IMap<Long, T> multiMap = client.getMap(mapName);
        return multiMap;
    }

    private ClientConfig createClientConfig(String mapName) {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.addNearCacheConfig(createNearCacheConfig(mapName));
        return clientConfig;
    }

    private NearCacheConfig createNearCacheConfig(String mapName) {
        NearCacheConfig nearCacheConfig = new NearCacheConfig();
        nearCacheConfig.setName(mapName);
        nearCacheConfig.setTimeToLiveSeconds(360);
        nearCacheConfig.setMaxIdleSeconds(60);
        return nearCacheConfig;
    }
}