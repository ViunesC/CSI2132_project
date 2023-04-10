package entity;

public class Customer extends BaseUser {
    private String address;

    public Customer(String first_name, String last_name, String email, String phone_num, String ssn, String password, String address) {
        super(first_name, last_name, email, phone_num, ssn, password);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
