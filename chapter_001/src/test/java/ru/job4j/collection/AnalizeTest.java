package ru.job4j.collection;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizeTest {

    @Test
    public void whenNoAddNoChangeNoDel() {
        Analize analize = new Analize();
        List<Analize.User> list1 = List.of(new Analize.User(1, "Dima"),
                new Analize.User(2, "Petr"),
                new Analize.User(3, "Sergey"),
                new Analize.User(4, "Sasha"));
        List<Analize.User> list2 = List.of(new Analize.User(1, "Dima"),
                new Analize.User(2, "Petr"),
                new Analize.User(3, "Sergey"),
                new Analize.User(4, "Sasha"));
        Analize.Info rsl = analize.diff(list1, list2);
        Analize.Info expected = new Analize.Info(0, 0, 0);
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenNoAddYesChangeNoDel() {
        Analize analize = new Analize();
        List<Analize.User> list1 = List.of(new Analize.User(1, "Dima"),
                new Analize.User(2, "Petr"),
                new Analize.User(3, "Sergey"),
                new Analize.User(4, "Sasha"));
        List<Analize.User> list2 = List.of(new Analize.User(1, "Dima"),
                new Analize.User(2, "Andrei"),
                new Analize.User(3, "Sergey"),
                new Analize.User(4, "Stas"));
        Analize.Info rsl = analize.diff(list1, list2);
        Analize.Info expected = new Analize.Info(0, 2, 0);
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenNoAddNoChangeYesDel() {
        Analize analize = new Analize();
        List<Analize.User> list1 = List.of(new Analize.User(1, "Dima"),
                new Analize.User(2, "Petr"),
                new Analize.User(3, "Sergey"),
                new Analize.User(4, "Sasha"));
        List<Analize.User> list2 = List.of(new Analize.User(1, "Dima"),
                new Analize.User(2, "Petr"));
        Analize.Info rsl = analize.diff(list1, list2);
        Analize.Info expected = new Analize.Info(0, 0, 2);
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenNoAddYesChangeYesDel() {
        Analize analize = new Analize();
        List<Analize.User> list1 = List.of(new Analize.User(1, "Dima"),
                new Analize.User(2, "Petr"),
                new Analize.User(3, "Sergey"),
                new Analize.User(4, "Sasha"));
        List<Analize.User> list2 = List.of(new Analize.User(2, "Petr"),
                new Analize.User(3, "Stas"));
        Analize.Info rsl = analize.diff(list1, list2);
        Analize.Info expected = new Analize.Info(0, 1, 2);
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenYesAddNoChangeNoDel() {
        Analize analize = new Analize();
        List<Analize.User> list1 = List.of(new Analize.User(1, "Dima"),
                new Analize.User(3, "Sergey"));
        List<Analize.User> list2 = List.of(new Analize.User(1, "Dima"),
                new Analize.User(2, "Petr"),
                new Analize.User(3, "Sergey"),
                new Analize.User(4, "Sasha"));
        Analize.Info rsl = analize.diff(list1, list2);
        Analize.Info expected = new Analize.Info(2, 0, 0);
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenYesAddNoChangeYesDel() {
        Analize analize = new Analize();
        List<Analize.User> list1 = List.of(new Analize.User(1, "Dima"),
                new Analize.User(2, "Petr"),
                new Analize.User(4, "Sasha"));
        List<Analize.User> list2 = List.of(new Analize.User(1, "Dima"),
                new Analize.User(2, "Petr"),
                new Analize.User(3, "Sergey"));
        Analize.Info rsl = analize.diff(list1, list2);
        Analize.Info expected = new Analize.Info(1, 0, 1);
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenYesAddYesChangeNoDel() {
        Analize analize = new Analize();
        List<Analize.User> list1 = List.of(new Analize.User(1, "Dima"),
                new Analize.User(4, "Sasha"));
        List<Analize.User> list2 = List.of(new Analize.User(1, "Stas"),
                new Analize.User(2, "Petr"),
                new Analize.User(3, "Sergey"),
                new Analize.User(4, "Sasha"));
        Analize.Info rsl = analize.diff(list1, list2);
        Analize.Info expected = new Analize.Info(2, 1, 0);
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenYesAddYesChangeYesDel() {
        Analize analize = new Analize();
        List<Analize.User> list1 = List.of(new Analize.User(1, "Dima"),
                new Analize.User(2, "Petr"),
                new Analize.User(4, "Sasha"));
        List<Analize.User> list2 = List.of(new Analize.User(1, "Stas"),
                new Analize.User(2, "Petr"),
                new Analize.User(3, "Sergey"));
        Analize.Info rsl = analize.diff(list1, list2);
        Analize.Info expected = new Analize.Info(1, 1, 1);
        assertThat(rsl, is(expected));
    }

}