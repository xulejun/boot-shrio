package com.xlj.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @Author XuLeJun
 * @Date 2020/7/26 15:48
 *
 * 自定义shiro缓存管理器
 */

public class RedisCacheManager implements CacheManager {

    // 参数：认证或者授权缓存的统一名称
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        System.out.println(cacheName);
        return new RedisCache<K, V>(cacheName);
    }
}
