package lru_cache;

import java.util.*;

public class LruCache <K,V> implements ILruCache<K,V> {
    private Elem<K, V>[] lruCache;
    private ArrayList<K>lastTenKeys;
    int indexFirstGet;
    private int maxSize;
    private int priority;
    private int currentSize;

    //конструктор
    public LruCache(int size) {
        maxSize = size;
        lruCache = new Elem[maxSize];
        priority = 0;
        lastTenKeys = new ArrayList<>(10);
        indexFirstGet = 0;
    }

    //поиск элемента
    public int findElem(K key) {
        for (int i = 0; i < currentSize; i++)
            if (lruCache[i].key.equals(key))
                return i;
        return -1;
    }

    //получение элемента по ключу
    public V get(K key) {
        if (lastTenKeys.size() != 10)
            lastTenKeys.add(key);
        else
        {
            lastTenKeys.set(indexFirstGet, key);
            indexFirstGet = (indexFirstGet+1)%10;
        }
        int index = findElem(key);
        if (index == -1)
            return null;
        V result = lruCache[index].value;
        lruCache[index].priority = priority;
        priority++;

        return result;
    }

    //добвление/обновление элемента
    public V set(K key, V value) {
        int index = findElem(key);
        V result = null;
        //если элемент с таким ключом есть
        if (index != -1) {
            //обновляем значение
            lruCache[index].value = value;
            lruCache[index].priority = priority;
            priority++;
        }
        //если элемента с таким ключом нет
        else {
            //если не достигли максимального размера
            if (currentSize < getLimit()) {
                lruCache[currentSize] = new Elem<K,V>();
                lruCache[currentSize].key = key;
                lruCache[currentSize].value = value;
                lruCache[currentSize].priority = priority;
                priority++;
                currentSize++;
            }
            //если достигнут максимальный размер
            else {
                int deleteElemIndex = findMinPriorityElem();
                result = lruCache[deleteElemIndex].value;
                lruCache[deleteElemIndex].key = key;
                lruCache[deleteElemIndex].value = value;
                lruCache[deleteElemIndex].priority = priority;
                priority++;
            }
        }
        return result;
    }

    private int findMinPriorityElem() {
        int result = 0;
        for (int i = 1; i < currentSize; i++) {
            if (lruCache[i].priority < lruCache[result].priority)
                result = i;
        }
        return result;
    }

    //получение максимального размера
    public int getLimit() {
        return maxSize;
    }

    public int getSize() {
        return currentSize;
    }

    public LinkedList<K> getTenLastKeys() {
        LinkedList<K> result = new LinkedList();
        for (int i = 0; i < lastTenKeys.size(); i++) {
            int index = (indexFirstGet + i) % lastTenKeys.size();
            result.add(lastTenKeys.get(index));
        }
        return result;
    }
}