package ru.job4j.generics;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void whenAdd() {
        UserStore store = new UserStore();
        store.add(new User("qwe"));
        User rsl = store.findById("qwe");
        User expected = new User("qwe");
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenFindByIdThenException() {
        UserStore store = new UserStore();
        store.add(new User("qwe"));
        User rsl = store.findById("asd");
        assertNull(rsl);
    }

    @Test
    public void whenReplaceThenOk() {
        UserStore store = new UserStore();
        store.add(new User("qwe"));
        store.add(new User("asd"));
        boolean rsl1 = store.replace("asd", new User("zxc"));
        User rsl2 = store.findById("zxc");
        User expected = new User("zxc");
        assertTrue(rsl1);
        assertThat(rsl2, is(expected));
    }

    @Test
    public void whenReplaceThenFail() {
        UserStore store = new UserStore();
        store.add(new User("qwe"));
        store.add(new User("asd"));
        boolean rsl1 = store.replace("asdfg", new User("zxc"));
        User rsl2 = store.findById("asd");
        User expected = new User("asd");
        assertFalse(rsl1);
        assertThat(rsl2, is(expected));
    }

    @Test
    public void whenDeleteThenOk() {
        UserStore store = new UserStore();
        store.add(new User("qwe"));
        store.add(new User("asd"));
        store.delete("asd");
        assertNull(store.findById("asd"));
    }

    @Test
    public void whenDeleteThenFail() {
        UserStore store = new UserStore();
        store.add(new User("qwe"));
        store.add(new User("asd"));
        boolean rsl1 = store.delete("asdfg");
        User rsl2 = store.findById("asd");
        User expected = new User("asd");
        assertFalse(rsl1);
        assertThat(rsl2, is(expected));
    }

}