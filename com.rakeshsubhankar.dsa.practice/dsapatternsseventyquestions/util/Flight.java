package dsapatternsseventyquestions.util;

public class Flight {
    public int toCity;
    public int price;

    public Flight(int toCity,int price) {
        this.toCity = toCity;
        this.price=price;
    }

    public int getToCity() {
        return toCity;
    }

    public void setToCity(int toCity) {
        this.toCity = toCity;
    }
}
