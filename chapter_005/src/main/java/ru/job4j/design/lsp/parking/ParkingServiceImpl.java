package ru.job4j.design.lsp.parking;

public class ParkingServiceImpl implements ParkingService {
    private Parking parking;

    public ParkingServiceImpl(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void addCar(Car car) {

    }

    @Override
    public void addTrack(Track track) {

    }

    @Override
    public void addTrackToCarPlace(Track track, int carPlaces) {

    }
}
