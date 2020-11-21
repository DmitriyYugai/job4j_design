package ru.job4j.design.lsp.parking;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StatisticsCollectorTest {

    @Test
    public void getTotalCarPlaces() {
        Parking parking = new Parking(20, 10);
        StatisticsCollector collector = new StatisticsCollectorImpl(parking);
        assertThat(collector.getTotalCarPlaces(), is(20));
    }

    @Test
    public void getTotalTrackPlaces() {
        Parking parking = new Parking(20, 10);
        StatisticsCollector collector = new StatisticsCollectorImpl(parking);
        assertThat(collector.getTotalTrackPlaces(), is(10));
    }
}