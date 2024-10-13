package task03;

public class Percept {
    private int row, col;
    private Environment.LocationState locationState;

    public Percept(int row, int col, Environment.LocationState locationState) {
        this.row = row;
        this.col = col;
        this.locationState = locationState;
    }

    public Environment.LocationState getLocationState() {
        return locationState;
    }
}