package ru.job4j.collection;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class SimpleHashTableTest {

    @Test
    public void whenGetThenNull() {
        SimpleHashTable<Integer, Integer> map = new SimpleHashTable<>(2);
        map.insert(1, 3);
        map.insert(2, 6);
        map.insert(3, 5);
        assertThat(map.get(5), nullValue());
    }

    @Test
    public void whenInsertThenOk() {
        SimpleHashTable<Integer, Integer> map = new SimpleHashTable<>();
        boolean rsl1 = map.insert(1, 3);
        Integer rsl2 = map.get(1);
        assertTrue(rsl1);
        assertThat(rsl2, is(3));
    }

    @Test
    public void whenInsertThenFail() {
        SimpleHashTable<Integer, Integer> map = new SimpleHashTable<>();
        map.insert(1, 3);
        boolean rsl1 = map.insert(1, 12);
        Integer rsl2 = map.get(1);
        assertFalse(rsl1);
        assertThat(rsl2, is(3));
    }

    @Test
    public void whenDeleteThenOk() {
        SimpleHashTable<Integer, Integer> map = new SimpleHashTable<>();
        map.insert(1, 3);
        map.insert(2, 6);
        boolean rsl1 = map.delete(2);
        Integer rsl2 = map.get(2);
        assertTrue(rsl1);
        assertThat(rsl2, nullValue());
    }

    @Test
    public void whenDeleteThenFail() {
        SimpleHashTable<Integer, Integer> map = new SimpleHashTable<>(2);
        map.insert(1, 3);
        map.insert(2, 6);
        map.insert(3, 5);
        boolean rsl1 = map.delete(5);
        assertFalse(rsl1);
    }

    @Test
    public void whenNextThenOk() {
        SimpleHashTable<Integer, Integer> map = new SimpleHashTable<>();
        map.insert(1, 8);
        map.insert(2, 6);
        map.insert(3, 5);
        Iterator<Integer> it = map.iterator();
        assertThat(it.next(), notNullValue());
        assertThat(it.next(), notNullValue());
        assertThat(it.next(), notNullValue());
    }

    @Test
    public void whenHasNextThenOk() {
        SimpleHashTable<Integer, Integer> map = new SimpleHashTable<>();
        map.insert(1, 8);
        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextThenNoElements() {
        SimpleHashTable<Integer, Integer> map = new SimpleHashTable<>();
        map.insert(1, 8);
        Iterator<Integer> it = map.iterator();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenNextThenModficationException() {
        SimpleHashTable<Integer, Integer> map = new SimpleHashTable<>();
        Iterator<Integer> it = map.iterator();
        map.insert(1, 6);
        it.next();
    }
}