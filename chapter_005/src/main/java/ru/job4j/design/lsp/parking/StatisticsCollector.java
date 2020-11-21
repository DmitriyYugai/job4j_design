package ru.job4j.design.lsp.parking;

public interface StatisticsCollector {
    int getTotalCarPlaces();

    int getTotalTrackPlaces();

    int getBusyCarPlaces();

    int getBusyTrackPlaces();
}
