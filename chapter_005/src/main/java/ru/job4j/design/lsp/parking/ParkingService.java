package ru.job4j.design.lsp.parking;

public interface ParkingService {

    void addCar(Car car);

    void addTrack(Track track);

    void addTrackToCarPlace(Track track, int carPlaces);
}
