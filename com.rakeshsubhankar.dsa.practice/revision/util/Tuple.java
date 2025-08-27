package revision.util;

public class Tuple {
    public int totalStops;
    public int currentCity;
    public int cost;

    public Tuple(int totalStops, int currentCity, int cost) {
        this.totalStops = totalStops;
        this.currentCity = currentCity;
        this.cost = cost;
    }

    public int getTotalStops() {
        return totalStops;
    }

    public void setTotalStops(int totalStops) {
        this.totalStops = totalStops;
    }

    public int getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(int currentCity) {
        this.currentCity = currentCity;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
