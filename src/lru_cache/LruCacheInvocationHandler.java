package lru_cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.LinkedList;

public class LruCacheInvocationHandler<K,V> implements InvocationHandler{
    private LruCache<K,V> lruCache;

    public LruCacheInvocationHandler(LruCache<K,V> lruCache) {
        this.lruCache=lruCache;
    }

    private Object setElem(Method method, Object[] args)throws Throwable{
        long timeBegin = System.nanoTime();
        Object methodResult = method.invoke(lruCache, args);
        long spentTime = System.nanoTime() - timeBegin;
        if (methodResult == null) {
            System.out.println("Удаления элементов не происходило.\nВремя выполнения: " + spentTime);
        }
        else {
            System.out.println("Произошло удаление элемента со значением: "+methodResult +"\nВремя выполнения: " + spentTime);
        }
        return methodResult;
    }

    private Object getElem(Method method, Object[] args)throws Throwable{
        long timeBegin = System.nanoTime();
        Object methodResult = method.invoke(lruCache, args);
        long spentTime = System.nanoTime() - timeBegin;
        LinkedList<K>history=lruCache.getTenLastKeys();
        System.out.println("Время выполнения: " + spentTime);
        System.out.println("История обращений:");
        for (K key:history) {
            System.out.println(key);
        }
        return methodResult;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        if (method.getName().equals("set")) {
            return setElem(method, args);
        }
        if (method.getName().equals("get")) {
            return getElem(method, args);
        }
        return method.invoke(lruCache, args);
    }
}
