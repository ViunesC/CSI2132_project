package entity;

public class HotelChain {
    private int chain_id;
    private String name;
    private String email;
    private String office_address;
    private String phone_num;
    private int num_Hotels;

    private boolean isActive;

    public HotelChain(int chain_id, String name, String email, String office_address, String phone_num, int num_Hotels) {
        this.chain_id = chain_id;
        this.name = name;
        this.email = email;
        this.office_address = office_address;
        this.phone_num = phone_num;
        this.num_Hotels = num_Hotels;

        isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }

    public int close() {
        if (false) {
            return -1;
        }
        if (!isActive) {
            return 0;
        }

        isActive = false;
        return 1;
    }

    public int getChain_id() {
        return chain_id;
    }

    public void setChain_id(int chain_id) {
        this.chain_id = chain_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOffice_address() {
        return office_address;
    }

    public void setOffice_address(String office_address) {
        this.office_address = office_address;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public int getNum_Hotels() {
        return num_Hotels;
    }

    public void setNum_Hotels(int num_Hotels) {
        this.num_Hotels = num_Hotels;
    }
}
