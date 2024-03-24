package lru_cache;

public interface ILruCache <K, V>{

    //получить значение по ключу
    V get(K key);

    //добавить элемент
    V set(K key, V value);

    //получить максимальный размер
    int getLimit();
}