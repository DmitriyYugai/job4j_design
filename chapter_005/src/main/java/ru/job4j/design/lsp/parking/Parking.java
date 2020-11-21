package ru.job4j.design.lsp.parking;

public class Parking {
    private final Car[] cars;
    private final Track[] tracks;
    private int carIndex = 0;
    private int trackIndex = 0;

    public Parking(int carSize, int trackSize) {
        cars = new Car[carSize];
        tracks = new Track[trackSize];
    }

    public Car[] getCars() {
        return cars;
    }

    public Track[] getTracks() {
        return tracks;
    }

    public int getCarIndex() {
        return carIndex;
    }

    public void setCarIndex(int carIndex) {
        this.carIndex = carIndex;
    }

    public int getTrackIndex() {
        return trackIndex;
    }

    public void setTrackIndex(int trackIndex) {
        this.trackIndex = trackIndex;
    }
}
