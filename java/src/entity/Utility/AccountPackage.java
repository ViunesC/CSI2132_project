package entity.Utility;

public class AccountPackage {
    private String name;
    private String email;
    private String password;
    private int id;
    private AccountFlag role;

    private AccountPackage(String name, String email, String password, int id, int FLAG) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;

        if (FLAG == 0) {
            role = AccountFlag.EMPLOYEE;
        } else if (FLAG == 1) {
            role = AccountFlag.CUSTOMER;
        } else {
            role = AccountFlag.ADMIN;
        }
    }

    public static AccountPackage createPackage(String name, String email, String password, int id, int FLAG) {
        return new AccountPackage(name, email, password, id, FLAG);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public AccountFlag getRole() {
        return role;
    }
}
