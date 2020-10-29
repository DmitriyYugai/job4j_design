package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenAddThenOk() {
        SimpleArray<Integer> array = new SimpleArray<>(Integer.class, 2);
        array.add(1);
        array.add(2);
        assertThat(array.get(0), is(1));
        assertThat(array.get(1), is(2));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddThenException() {
        SimpleArray<Integer> array = new SimpleArray<>(Integer.class, 2);
        array.add(1);
        array.add(2);
        array.add(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetThenException() {
        SimpleArray<Animal> array = new SimpleArray<>(Animal.class, 2);
        array.get(2);
    }

    @Test
    public void wnehSetThenOk() {
        SimpleArray<Character> array = new SimpleArray<>(Character.class, 2);
        array.add('r');
        array.add('s');
        array.set(1, 'k');
        assertThat(array.get(1), is('k'));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void wnehSetThenException() {
        SimpleArray<Character> array = new SimpleArray<>(Character.class, 2);
        array.add('r');
        array.add('s');
        array.set(4, 'k');
    }

    @Test
    public void whenRemoveThenOk() {
        SimpleArray<Integer> array = new SimpleArray<>(Integer.class, 4);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.remove(1);
        Integer[] expected = {1, 3, 4};
        assertThat(array.getArray(), is(expected));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveThenException() {
        SimpleArray<Integer> array = new SimpleArray<>(Integer.class, 2);
        array.add(1);
        array.add(2);
        array.remove(2);
    }

    @Test
    public void whenIteratorHasNextThenOk() {
        SimpleArray<Integer> array = new SimpleArray<>(Integer.class, 2);
        array.add(1);
        array.add(2);
        Iterator<Integer> it = array.iterator();
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test
    public void whenIteratorNextThenOk() {
        SimpleArray<Integer> array = new SimpleArray<>(Integer.class, 2);
        array.add(1);
        array.add(2);
        Iterator<Integer> it = array.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenIteratorThenOk() {
        SimpleArray<Integer> array = new SimpleArray<>(Integer.class, 2);
        array.add(1);
        array.add(2);
        Iterator<Integer> it = array.iterator();
        it.hasNext();
        int rsl1 = it.next();
        it.hasNext();
        int rsl2 = it.next();
        assertFalse(it.hasNext());
        assertThat(rsl1, is(1));
        assertThat(rsl2, is(2));
    }

}