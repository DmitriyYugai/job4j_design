package ru.job4j.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RowColSumTest {

    @Test
    public void whenSeqSum() {
        RowColSum.Sums[] rsl = RowColSum.sum(new int[][]{
                {2, 4, 6},
                {3, 5, 7},
                {4, 7, 9}
        });
        assertThat(rsl, is(new RowColSum.Sums[]{
                new RowColSum.Sums(12, 9),
                new RowColSum.Sums(15, 16),
                new RowColSum.Sums(20, 22)
        }));
    }

    @Test
    public void whenAsyncSum() throws InterruptedException, ExecutionException {
        RowColSum.Sums[] rsl = RowColSum.asyncSum(new int[][]{
                {2, 4, 6},
                {3, 5, 7},
                {4, 7, 9}
        });
        assertThat(rsl, is(new RowColSum.Sums[]{
                new RowColSum.Sums(12, 9),
                new RowColSum.Sums(15, 16),
                new RowColSum.Sums(20, 22)
        }));
    }
}