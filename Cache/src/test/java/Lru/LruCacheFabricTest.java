package Lru;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class LruCacheFabricTest {
    @Test
    public void testCreateLruCache() {
        LruCacheFabric<Integer, String> lruCacheFabric = new LruCacheFabric<>();

        ILruCache<Integer, String> lruCache = lruCacheFabric.CreateLruCache(0, 5);
        assertNotNull(lruCache);
        assertEquals(LruCache.class, lruCache.getClass());

        ILruCache<Integer, String> proxyLruCache = lruCacheFabric.CreateLruCache(1, 5);
        assertNotNull(proxyLruCache);
    }
}
