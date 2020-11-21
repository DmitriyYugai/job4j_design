package ru.job4j.design.lsp.parking;

public interface StatisticsCollector {
    void addCar(Car car);

    void addTrack(Track track);

    void addTrackToCarPlace(Track track);
}
