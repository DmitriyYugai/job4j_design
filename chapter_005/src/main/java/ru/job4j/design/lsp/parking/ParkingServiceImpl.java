package ru.job4j.design.lsp.parking;

public class ParkingServiceImpl implements ParkingService {
    private Parking parking;

    public ParkingServiceImpl(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void addCar(Car car) {
        Car[] cars = parking.getCars();
        int carIndex = parking.getCarIndex();
        cars[carIndex] = car;
        parking.setCarIndex(carIndex + 1);
    }

    @Override
    public void addTrack(Track track) {
        Track[] tracks = parking.getTracks();
        int trackIndex = parking.getTrackIndex();
        tracks[trackIndex] = track;
        parking.setTrackIndex(trackIndex + 1);
    }

    @Override
    public void addTrackToCarPlace(Track track, int carPlaces) {
        Car[] cars = parking.getCars();
        int carIndex = parking.getCarIndex();
        parking.setCarIndex(carIndex + carPlaces);
    }
}
