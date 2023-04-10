package entity;

public class Room {
    private String id;
    private String type;
    private int capacity;
    private String amenities;
    private int price;
    private String view_type;
    private String can_be_extended;
    private String problems;
    private int hotel_id;
    private boolean isActive;

    public Room(String id, String type, int capacity, int price, String view_type, String can_be_extended, int hotel_id) {
        this.id = id;
        this.type = type;
        this.capacity = capacity;
        this.price = price;
        this.view_type = view_type;
        this.can_be_extended = can_be_extended;
        this.hotel_id = hotel_id;

        amenities = null;
        problems = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getView_type() {
        return view_type;
    }

    public void setView_type(String view_type) {
        this.view_type = view_type;
    }

    public String isCan_be_extended() {
        return can_be_extended;
    }

    public void setCan_be_extended(String can_be_extended) {
        this.can_be_extended = can_be_extended;
    }

    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
