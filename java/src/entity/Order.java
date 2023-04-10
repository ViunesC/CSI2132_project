package entity;

import java.sql.Date;

public class Order {
    private String id;
    private int type;
    private String room_id;
    private int customer_id;
    private int employee_id;
    private Date start, end;
    private boolean isActive;

    public Order(String id, String room_id, int customer_id, Date start, Date end) {
        // Booking
        this.id = id;
        this.room_id = room_id;
        this.customer_id = customer_id;
        this.start = start;
        this.end = end;

        this.employee_id = -1;
        type = 0;
        isActive = true;
    }

    public Order(String id, String room_id, int customer_id, int employee_id, Date start, Date end) {
        // Renting
        this.id = id;
        this.room_id = room_id;
        this.customer_id = customer_id;
        this.start = start;
        this.end = end;

        this.employee_id = employee_id;
        type = 1;
        isActive = true;
    }

    public String getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getRoom_id() {
        return room_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public boolean isActive() {
        return isActive;
    }

    public int deactivate() {
        if (false) {
            return -1;
        }
        if (!isActive) {
            return 0;
        }

        isActive = false;
        return 1;
    }
}
