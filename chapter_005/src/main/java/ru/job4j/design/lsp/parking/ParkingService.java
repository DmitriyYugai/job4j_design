package ru.job4j.design.lsp.parking;

public interface ParkingService {
    int getTotalCarPlaces();

    int getTotalTrackPlaces();

    int getFreeCarPlaces();

    int getFreeTrackPlaces();
}
