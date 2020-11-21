package ru.job4j.design.lsp.parking;

public class StatisticsCollectorImpl implements StatisticsCollector {
    private Parking parking;

    public StatisticsCollectorImpl(Parking parking) {
        this.parking = parking;
    }

    @Override
    public int getTotalCarPlaces() {
        return parking.getCars().length;
    }

    @Override
    public int getTotalTrackPlaces() {
        return parking.getTracks().length;
    }

    @Override
    public int getBusyCarPlaces() {
        return parking.getCarIndex();
    }

    @Override
    public int getBusyTrackPlaces() {
        return parking.getTrackIndex();
    }
}
