package lru_cache;

public class Elem<K, V> {
    K key;
    V value;
    int priority;

    //печать
    public void printElem()
    {
        System.out.println(key + "\t\t" + value);
    }
}
