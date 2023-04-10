package entity;

public class Employee extends BaseUser {
    private String role;
    private int hotel_id;
    private String position;

    public Employee(String first_name, String last_name, String email, String phone_num, String ssn, String password, String role, int hotel_id, String position) {
        super(first_name, last_name, email, phone_num, ssn, password);
        this.role = role;
        this.hotel_id = hotel_id;
        this.position = position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
