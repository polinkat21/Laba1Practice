package Lru;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LruCacheInvocationHandlerTest {

    @Test
    public void testSetElem() throws Throwable {
        LruCache<Integer, String> lruCache = new LruCache<>(5);
        LruCacheInvocationHandler<Integer, String> handler = new LruCacheInvocationHandler<>(lruCache);

        // Test set method
        Object result = handler.setElem(LruCache.class.getDeclaredMethod("set", Object.class, Object.class), new Object[]{1, "txt"});
        assertEquals(null, result);

        result = handler.setElem(LruCache.class.getDeclaredMethod("set", Object.class, Object.class), new Object[]{2, "txt2"});
        assertEquals(null, result);
    }

    @Test
    public void testGetElem() throws Throwable {
        LruCache<Integer, String> lruCache = new LruCache<>(5);
        lruCache.set(1, "txt");
        lruCache.set(2, "txt2");
        LruCacheInvocationHandler<Integer, String> handler = new LruCacheInvocationHandler<>(lruCache);

        Object result = handler.getElem(LruCache.class.getDeclaredMethod("get", Object.class), new Object[]{1});
        assertEquals("txt", result);
    }
}
