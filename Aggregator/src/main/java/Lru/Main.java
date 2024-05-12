package Lru;

import static java.lang.Integer.MAX_VALUE;

/*Реализовать фабрику (или фабричный метод), которая позволяет
получить объекты LruCache с указанными типами параметризации.
При этом возращаться должен экземпляр класса-прокси, который
добавляет к обычному классу следующее поведение:
• Каждый раз при достижении максимального размера кеша во
время вызова метода set() удаляемый элемент
(неиспользованный дольше всех) должен выводиться в консоль с
соотвествующим сообщением.
• При получении значения по ключу, т.е. при вызове get() , в
консоль должна выводиться история последних 10 обращений к
ключам, а именно - хронологическая последовательность ключей,
к которым были обращения.
• Кроме того, в сообщениях из предыдущих двух требований
должно содержаться время (в миллисекундах или наносекундах),
которое потребовалось обработку операции.*/


public class Main {

    public static void main(String[] args) {
        int lruCacheType;
        int lruCacheSize;
        LruCacheFabric<Integer, Integer> fabric = new LruCacheFabric<>();

        System.out.println("Выберите пункт меню:");
        System.out.println("0 - обычный LRU кеш");
        System.out.println("1 - прокси LRU кеш");
        lruCacheType = Menu.inputNumber("Ваш выбор:", 0, 1);

        lruCacheSize = Menu.inputNumber("Введите размер LRU кеш:", 1, MAX_VALUE);
        ILruCache<Integer, Integer> lruCache = fabric.CreateLruCache(lruCacheType, lruCacheSize);

        Menu menu = new Menu(lruCache);
        menu.Start();
    }
}
