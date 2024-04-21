package Lru;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class LruCacheTest {

    @Test
    public void findElemExists() {
        LruCache c = new LruCache(5);
        Integer key = 1;
        String value = "txt";
        c.set(key, value);

        int actual = c.findElem(1);
        int expected = 0;

        assertEquals(expected, actual);
    }

    @Test
    public void findElemNotExists() {
        LruCache c = new LruCache(5);
        Integer key = 1;
        String value = "txt";
        c.set(key, value);

        int actual = c.findElem(2);
        int expected = -1;

        assertEquals(expected, actual);
    }

    @Test
    public void getExists() {
        LruCache c = new LruCache(5);
        Integer key = 1;
        String value = "txt";
        c.set(key, value);

        Object actual = c.get(1);
        Object expected = "txt";

        assertEquals(expected, actual);
    }

    @Test
    public void getNotExists() {
        LruCache c = new LruCache(5);
        Integer key = 1;
        String value = "txt";
        c.set(key, value);

        Object actual = c.get(3);
        Object expected = null;

        assertEquals(expected, actual);
    }

    @Test
    public void setNotExistsOne() {
        LruCache c = new LruCache(5);
        Integer key = 1;
        String value = "txt";
        c.set(key, value);

        Object actual = c.findElem(1);
        Object expected = 0;

        assertEquals(expected, actual);
    }

    @Test
    public void setNotExistsOneValue() {
        LruCache c = new LruCache(5);
        Integer key = 1;
        String value = "txt";
        c.set(key, value);

        Object actual = c.get(1);
        Object expected = "txt";

        assertEquals(expected, actual);
    }

    @Test
    public void setNotExistsMoreOne() {
        LruCache c = new LruCache(5);
        Integer key = 1;
        String value = "txt";
        c.set(key, value);

        key = 5;
        value = "txt2";
        c.set(key, value);

        Object actual = c.findElem(5);
        Object expected = 1;

        assertEquals(expected, actual);
    }

    @Test
    public void setExists() {
        LruCache c = new LruCache(5);
        //условно - элемент существовал
        Integer key = 1;
        String value = "txt";
        c.set(key, value);

        //изменяем существующий элемент
        key = 1;
        value = "txt2";
        c.set(key, value);

        Object actual = c.get(1);
        Object expected = "txt2";

        assertEquals(expected, actual);
    }

    @Test
    public void getLimit() {
        LruCache c = new LruCache(5);
        int actual = c.getLimit();
        int expected = 5;

        assertEquals(expected, actual);
    }

    @Test
    public void getSize() {
        LruCache c = new LruCache(5);
        Integer key = 1;
        String value = "txt";
        c.set(key, value);

        key = 2;
        value = "txt2";
        c.set(key, value);

        int actual = c.getSize();
        int expected = 2;

        assertEquals(expected, actual);
    }

    @Test
    public void getTenLastKeys() {
        LruCache c = new LruCache(5);

        Integer key = 1;
        String value = "txt";
        c.set(key, value);

        key = 2;
        value = "txt2";
        c.set(key, value);

        key = 3;
        value = "txt3";
        c.set(key, value);

        key = 4;
        value = "txt4";
        c.set(key, value);

        Object tmp = c.get(3);
        tmp = c.get(1);

        Object actual = c.getTenLastKeys();
        LinkedList<Integer> result = new LinkedList();
        result.add(3);
        result.add(1);
        Object expected = result;

        assertEquals(expected, actual);
    }

    @Test
    public void printNotExists() {
        LruCache c = new LruCache(5);
        String actual = c.print();
        String expected = "На данный момент элементов нет";
        assertEquals(expected, actual);
    }

    @Test
    public void printExists() {
        LruCache c = new LruCache(5);
        Integer key = 1;
        String value = "txt";
        c.set(key, value);

        key = 2;
        value = "txt2";
        c.set(key, value);

        String actual = c.print();
        String expected = "Все элементы:\nКлюч: 1 Значение: txt\nКлюч: 2 Значение: txt2\n";
        assertEquals(expected, actual);
    }

    @Test
    public void findMinPriorityElem() {
        LruCache c = new LruCache(5);
        Integer key = 1;
        String value = "txt";
        c.set(key, value);

        key = 2;
        value = "txt2";
        c.set(key, value);

        key = 3;
        value = "txt3";
        c.set(key, value);

        Object tmp = c.get(3);
        tmp = c.get(1);
        tmp = c.get(2);
        tmp = c.get(3);
        tmp = c.get(2);

        int actual = c.findMinPriorityElem();
        int expected = 0;

        assertEquals(expected, actual);
    }
}