package entity;

public class Hotel {
    private int hotel_id;
    private String address;
    private String category;
    private String email;
    private String area;
    private int chain_id;
    private String hotel_name;
    private boolean is_Open;

    public Hotel(int hotel_id, String address, String category, String email, String area, int chain_id, String hotel_name) {
        this.hotel_id = hotel_id;
        this.address = address;
        this.category = category;
        this.email = email;
        this.area = area;
        this.chain_id = chain_id;
        this.hotel_name = hotel_name;

        is_Open = true;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getChain_id() {
        return chain_id;
    }

    public void setChain_id(int chain_id) {
        this.chain_id = chain_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public boolean isOpen() {
        return is_Open;
    }

    public int close() {
        if (false) {
            return -1;
        }
        if (!is_Open) {
            return 0;
        }

        is_Open = false;
        return 1;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getArea() {
        return area;
    }
}
