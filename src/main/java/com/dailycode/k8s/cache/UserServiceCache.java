package com.dailycode.k8s.cache;

import com.dailycode.k8s.dao.UserRepository;
import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserServiceCache {
    private final String CACHE_NAME = "devCache";
    @Autowired
    private CacheManager cacheManager;

    // You have to move to another location, inter dependencies....
    private UserRepository repository;

    @Bean
    public CacheManager cacheManager() {
        // spring supports different cacheManager implementations.. this mainly for poc...
        // Comment the spring.cache.cache-names=devCache entry in properties file.
        // Or you can use SimpleCacheManager and set caches.
        return new ConcurrentMapCacheManager(CACHE_NAME);
    }

    @Bean
    public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManagerCustomizer() {
        return cacheManager -> cacheManager.setAllowNullValues(false);
    }

    @Bean
    public Config hazelCastConfig() {
        return new Config().setInstanceName("hazelcast-instance")
                .addMapConfig(new MapConfig().setName(CACHE_NAME)
                        .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                        .setEvictionPolicy(EvictionPolicy.LRU).setTimeToLiveSeconds(2000));
    }

    @Bean("customKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new CustomKeyGenerator();
    }

    public void refreshAllCaches() {
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());

        // reload whole dataset here, dummy example here:
        repository.findAll().forEach(a -> cacheManager.getCache(CACHE_NAME).put(a.getId(), a));
    }

    //@Scheduled(fixedRate = 6000)
    public void refreshAllcachesAtIntervals() {
        refreshAllCaches();
    }
}
