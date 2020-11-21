package ru.job4j.design.lsp.parking;

public class StatisticsCollectorImpl implements StatisticsCollector {
    private Parking parking;

    public StatisticsCollectorImpl(Parking parking) {
        this.parking = parking;
    }

    @Override
    public int getTotalCarPlaces() {
        return 0;
    }

    @Override
    public int getTotalTrackPlaces() {
        return 0;
    }

    @Override
    public int getBusyCarPlaces() {
        return 0;
    }

    @Override
    public int getBusyTrackPlaces() {
        return 0;
    }
}
