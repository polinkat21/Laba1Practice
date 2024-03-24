package menu;

import lru_cache.ILruCache;

import java.util.Scanner;

public class Menu {
    private final ILruCache lruCache;
    public Menu(ILruCache lruCache)
    {
        this.lruCache=lruCache;
    }

    public static int inputNumber(String txt, int min, int max)
    {
        System.out.println(txt);
        int num;
        do {
            Scanner in = new Scanner(System.in);
            num = in.nextInt();
            if (num < min || num > max)
                System.out.println("Ошибка. Вы должны ввести число в диапазоне [" + min + ".." + max + "]. Повторите ввод!");
        } while (num < min || num > max);
        return num;
    }

    //ввод строки
    public static String inputString(String txt)
    {
        System.out.println(txt);
        String str;
        Scanner in = new Scanner(System.in);
        str = in.nextLine();
        return str;
    }

    private void setElem()
    {
        Integer key = inputNumber("Введите ключ элемента, который вы хотите добавить: ", Integer.MIN_VALUE, Integer.MAX_VALUE);
        String value = inputString("Ввведите значение элемента");
        lruCache.set(key, value);
        System.out.println("Элемент с ключом " + key + " и значением " + value + " был добавлен в LRU кеш");
    }

    private void getElem()
    {
        Integer key = inputNumber("Введите ключ элемента, который вы хотите найти: ", Integer.MIN_VALUE, Integer.MAX_VALUE);

        String el;
        try {
            el = lruCache.get(key).toString();
            System.out.println("Значение элемента с ключом " + key + ": " + el);
        }
        catch(Exception ex)
        {
            System.out.println("Элемента с ключом " + key + " нет в LRU кеш");
        }
    }

    public void Start()
    {
        int choice;
        do {
            System.out.println("0 - завершить");
            System.out.println("1 - добавить элемент");
            System.out.println("2 - получить значение по ключу");
            choice = inputNumber("Ваш выбор: ", 0, 2);
            switch (choice)
            {
                case 1 -> {
                    setElem();
                    break;
                }
                case 2->{
                    getElem();
                    break;
                }
            }
        } while(choice != 0);
    }
}
