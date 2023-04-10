package entity;

import java.util.Date;
import java.util.Random;

public class BaseUser implements IBaseUser {
    protected String first_name, last_name;
    protected int id;
    protected String email;
    protected String phone_num;
    protected String password;
    protected String ssn;
    protected Date register_date;
    protected boolean active;

    public BaseUser(String first_name, String last_name, String email, String phone_num, String ssn, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_num = phone_num;
        this.ssn = ssn;
        this.password = password;

        this.id = new Random().nextInt(100000);
        this.register_date = new Date();
        this.active = true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int banUser() {
        if (false) {
            return -1;
        }
        if (!active) {
            return 0;
        }

        active = false;
        return 1;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
