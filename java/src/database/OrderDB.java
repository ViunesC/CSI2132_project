package database;

import database.SQLUtil.querySQL;
import entity.Order;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDB extends BaseDB {
    public OrderDB() { super(); }

    @Override
    public void close() {
        super.close();
    }

    public boolean isAvailable(String room_id, Date start, Date end) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Date start_date_order, end_date_order;
        boolean flag = true;
        try {
            preparedStatement = connection.prepareStatement(querySQL.FIND_BOOKING_VIA_ROOM);
            preparedStatement.setString(1, room_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                start_date_order = resultSet.getDate(2);
                end_date_order = resultSet.getDate(3);

                if (start.before(end_date_order) && end.after(start_date_order)) {
                    flag = false;
                    break;
                }
            }

            preparedStatement = connection.prepareStatement(querySQL.FIND_RENTING_VIA_ROOM);
            while (resultSet.next()) {
                start_date_order = resultSet.getDate(2);
                end_date_order = resultSet.getDate(3);

                if (start.before(end_date_order) && end.after(start_date_order)) {
                    flag = false;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return flag;
    }

    public boolean insertToDB(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }

        PreparedStatement preparedStatement = null;
        String query;
        if (order.getType() == 0) {
            query = querySQL.INSERT_NEW_BOOKING;
        } else {
            query = querySQL.INSERT_NEW_RENTING;
        }

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, order.getId());
            preparedStatement.setDate(2, order.getStart());
            preparedStatement.setDate(3, order.getEnd());
            preparedStatement.setString(4, order.getRoom_id());
            preparedStatement.setInt(5, order.getCustomer_id());
            if (order.getType() == 1) {
                preparedStatement.setInt(6, order.getEmployee_id());
            }

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public List<Order> findOrder(int customer_id) {
        List<Order> orders = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(querySQL.FIND_BOOKING_VIA_CUSTOMER);
            preparedStatement.setInt(1, customer_id);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String id = resultSet.getString(1);
                Date start = resultSet.getDate(2);
                Date end = resultSet.getDate(3);
                String room_id = resultSet.getString(4);
                orders.add(new Order(id, room_id, customer_id, start, end));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return orders;
    }
}
