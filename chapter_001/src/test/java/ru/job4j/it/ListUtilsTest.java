package ru.job4j.it;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 0, 2);
        assertThat(Arrays.asList(1, 2, 3), is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 3, 2);
    }

    @Test
    public void whenARemove() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 4, 3, 2));
        ListUtils.removeIf(input, el -> el > 2);
        List<Integer> expected = List.of(1, 2);
        assertThat(input, is(expected));
    }

    @Test
    public void whenAReplace() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 4, 3, 2));
        ListUtils.replaceIf(input, el -> el > 2, 7);
        List<Integer> expected = List.of(1, 7, 7, 2);
        assertThat(input, is(expected));
    }

    @Test
    public void whenARemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 4, 3, 2));
        List<Integer> subInput = new ArrayList<>(Arrays.asList(4, 2));
        ListUtils.removeAll(input, subInput);
        List<Integer> expected = List.of(1, 3);
        assertThat(input, is(expected));
    }

}