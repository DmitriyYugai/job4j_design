package ru.job4j.design.lsp.parking;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ParkingServiceTest {

    @Test
    public void whenAddCar() {
        Parking parking = new Parking(20, 10);
        ParkingService ps = new ParkingServiceImpl(parking);
        StatisticsCollector collector = new StatisticsCollectorImpl(parking);
        ps.addCar(new Car());
        ps.addCar(new Car());
        assertThat(collector.getBusyCarPlaces(), is(2));
    }

    @Test
    public void whenAddTrack() {
        Parking parking = new Parking(20, 10);
        ParkingService ps = new ParkingServiceImpl(parking);
        StatisticsCollector collector = new StatisticsCollectorImpl(parking);
        ps.addTrack(new Track());
        ps.addTrack(new Track());
        ps.addTrack(new Track());
        assertThat(collector.getBusyTrackPlaces(), is(3));
    }

    @Test
    public void whenAddTrackToCarPlace() {
        Parking parking = new Parking(20, 10);
        ParkingService ps = new ParkingServiceImpl(parking);
        StatisticsCollector collector = new StatisticsCollectorImpl(parking);
        ps.addTrackToCarPlace(new Track(), 3);
        ps.addTrackToCarPlace(new Track(), 2);
        assertThat(collector.getBusyCarPlaces(), is(5));
    }
}