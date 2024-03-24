package fabric;

import lru_cache.ILruCache;
import lru_cache.LruCache;
import lru_cache.LruCacheInvocationHandler;
import java.lang.reflect.Proxy;

public class LruCacheFabric<K, V> {
    public ILruCache<K,V> CreateProxyLruCache(int maxSize) {
        LruCache<K, V> lruCache = new LruCache<>(maxSize);
        ClassLoader lruCacheClassLoader = lruCache.getClass().getClassLoader();
        Class[] interfaces = lruCache.getClass().getInterfaces();
        return (ILruCache<K,V>) Proxy.newProxyInstance(lruCacheClassLoader,
                interfaces,
                new LruCacheInvocationHandler<>(lruCache));
    }

    public ILruCache<K,V> CreateLruCache(int lruCacheType, int maxSize) {
        switch (lruCacheType) {
            case 0 -> {
                return new LruCache<>(maxSize);
            }
            case 1 -> {
                return CreateProxyLruCache(maxSize);
            }
        }
        return null;
    }
}
