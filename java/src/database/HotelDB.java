package database;

import database.SQLUtil.querySQL;
import entity.Hotel;
import entity.HotelChain;
import entity.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class HotelDB extends BaseDB {
    private static final int FLAG_UPDATE = 0;
    private static final int FLAG_INSERT = 1;
    public HotelDB() { super(); }

    @Override
    public void close() {
        super.close();
    }

    public Hotel findHotel(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Hotel hotel = null;

        try {
            preparedStatement = connection.prepareStatement(querySQL.FIND_HOTEL_VIA_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String address = resultSet.getString(2);
                String category = resultSet.getString(3);
                String email = resultSet.getString(4);
                String area = resultSet.getString(7);
                int chain_id = resultSet.getInt(5);
                String name = resultSet.getString(6);
                hotel = new Hotel(id, address, category, email, area, chain_id, name);
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

        return hotel;
    }

    public List<Hotel> findHotel(int chain_id, String category, String area) {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Hotel> hotels = new ArrayList<>();
        String query = querySQL.FIND_HOTEL_VIA_INFO;
        int flag = 0;

        if (chain_id != -1) {
            query += " chain_ID = " + chain_id;
            ++flag;
        }

        if (!category.equals("")) {
            if (flag != 0) {
                query += " AND";
            }
            query += " category = '" + category + "'";
            ++flag;
        }

        if (!area.equals("")) {
            if (flag != 0) {
                query += " AND";
            }
            query += " area = '" + area + "'";
            ++flag;
        }

        query += ";";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String address = resultSet.getString(2);
                category = resultSet.getString(3);
                String email = resultSet.getString(4);
                area = resultSet.getString(7);
                chain_id = resultSet.getInt(5);
                String name = resultSet.getString(6);
                hotels.add(new Hotel(id, address, category, email, area, chain_id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return hotels;
    }

    public List<Room> findRoom(Hotel hotel, int capacity, String priceRange) {
        if (hotel == null) {
            throw new IllegalArgumentException("Invalid hotel");
        }
        Statement statement = null;
        ResultSet resultSet = null;
        List<Room> rooms = new ArrayList<>();
        String query = querySQL.FIND_ROOM_VIA_INFO;

        query += " hotel_ID = " + hotel.getHotel_id();

        if (capacity != -1) {
            query += " AND capacity = " + capacity;
        }

        if (!priceRange.equals("")) {
            switch(priceRange) {
                case "<200":
                    query += " AND price < 200";
                    break;
                case "200-500":
                    query += " AND price >= 200 AND price < 500";
                    break;
                case "500-1000":
                    query += " AND price >= 500 AND price < 1000";
                    break;
                case ">1000":
                    query += " AND price > 1000";
                    break;
            }
        }

        query += ";";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String type = resultSet.getString(2);
                capacity = resultSet.getInt(3);
                String amenities = resultSet.getString(4);
                int price = resultSet.getInt(5);
                String view = resultSet.getString(6);
                String extend = resultSet.getString(7);
                String problems = resultSet.getString(8);

                if (amenities == null) {
                    amenities = "";
                }

                if (problems == null) {
                    problems = "";
                }

                Room new_room = new Room(id, type, capacity, price, view, extend, hotel.getHotel_id());
                new_room.setAmenities(amenities);
                new_room.setProblems(problems);
                rooms.add(new_room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return rooms;
    }

    public HotelChain findChain(String name) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HotelChain chain = null;

        try {
            preparedStatement = connection.prepareStatement(querySQL.FIND_CHAIN_VIA_NAME);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String email = resultSet.getString(3);
                String office_addr = resultSet.getString(4);
                int num_hotels = resultSet.getInt(6);
                String phone = resultSet.getString(5);
                chain = new HotelChain(id, name, email, office_addr, phone, num_hotels);
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

        return chain;
    }

    public List<String> getHotelInfo_list(int flag) {
        List<String> info_list = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query;

        if (flag == 0) {
            query = querySQL.FIND_ALL_CHAIN_AS_NAME;
        } else {
            query = querySQL.FIND_ALL_HOTEL_CATEGORY;
        }

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                info_list.add(resultSet.getString(1));
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

        if (info_list.isEmpty()) {
            throw new NoSuchElementException("An error occured when pulling hotel info");
        }

        return info_list;
    }

    public boolean updateDB(String idStr, String name, String email, String address, String phone, String num_hotels) {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;

        if (idStr.equals("")) {
            throw new IllegalArgumentException("id cannot be empty");
        }

        int id = Integer.parseInt(idStr);
        int Flag = FLAG_INSERT;
        if (name.equals("") || email.equals("") || address.equals("") || phone.equals("") || num_hotels.equals("")) {
            Flag = FLAG_UPDATE;
        }

        String search_query = "SELECT * FROM hotelChain WHERE chain_ID = " + id +";";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(search_query);
            if (resultSet.next()) {
                if (Flag == FLAG_INSERT) {
                    Flag = FLAG_UPDATE;
                }
            } else {
                if (Flag == FLAG_UPDATE) {
                    return false;
                }
            }

            if (Flag == FLAG_INSERT) {
                preparedStatement = connection.prepareStatement(querySQL.INSERT_NEW_CHAIN);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, address);
                preparedStatement.setString(5, phone);
                preparedStatement.setInt(6, Integer.parseInt(num_hotels));

                preparedStatement.executeUpdate();
            } else {
                String updateQuery = "UPDATE hotelChain SET";
                int flag = 0;

                if (!name.equals("")) {
                    updateQuery += " chain_name = '" + name + "'";
                    ++flag;
                }

                if (!email.equals("")) {
                    if (flag != 0) {
                        updateQuery += ",";
                    }
                    updateQuery += " email = '" + email + "'";
                    ++flag;
                }

                if (!address.equals("")) {
                    if (flag != 0) {
                        updateQuery += ",";
                    }
                    updateQuery += " office_address = '" + address + "'";
                    ++flag;
                }

                if (!phone.equals("")) {
                    if (flag != 0) {
                        updateQuery += ",";
                    }
                    updateQuery += " phone_num = '" + phone + "'";
                    ++flag;
                }

                if (!num_hotels.equals("")) {
                    if (flag != 0) {
                        updateQuery += ",";
                    }
                    updateQuery += " number_of_hotels = " + Integer.parseInt(num_hotels);
                    ++flag;
                }

                updateQuery += " WHERE chain_ID = " + id;

                statement.executeUpdate(updateQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (Flag == FLAG_INSERT) {
                    preparedStatement.close();
                }
                statement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public boolean updateDB(String chain_idStr, String hotel_idStr, String name, String email, String address, String category, String area) {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;

        if (chain_idStr.equals("") || hotel_idStr.equals("")) {
            throw new IllegalArgumentException("ids cannot be empty");
        }

        int chain_id = Integer.parseInt(chain_idStr);
        int hotel_id = Integer.parseInt(hotel_idStr);
        int Flag = FLAG_INSERT;
        if (name.equals("") || email.equals("") || address.equals("") || category.equals("") || area.equals("")) {
            Flag = FLAG_UPDATE;
        }

        String search_query = "SELECT * FROM hotel WHERE hotel_ID = " + hotel_id +";";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(search_query);
            if (resultSet.next()) {
                if (Flag == FLAG_INSERT) {
                    Flag = FLAG_UPDATE;
                }
            } else {
                if (Flag == FLAG_UPDATE) {
                    return false;
                }
            }

            if (Flag == FLAG_INSERT) {
                search_query = "SELECT * FROM hotelChain WHERE chain_ID = " + chain_id +";";
                resultSet = statement.executeQuery(search_query);
                if (!resultSet.next()) {
                    throw new UnsupportedOperationException("Chain does not exist!");
                }

                preparedStatement = connection.prepareStatement(querySQL.INSERT_NEW_HOTEL);
                preparedStatement.setInt(1, hotel_id);
                preparedStatement.setString(2, address);
                preparedStatement.setString(3, category);
                preparedStatement.setString(4, email);
                preparedStatement.setInt(5, chain_id);
                preparedStatement.setString(6, name);
                preparedStatement.setString(7, area);

                preparedStatement.executeUpdate();
            } else {
                String updateQuery = "UPDATE hotel SET";
                int flag = 0;

                if (!name.equals("")) {
                    updateQuery += " hotel_name = '" + name + "'";
                    ++flag;
                }

                if (!email.equals("")) {
                    if (flag != 0) {
                        updateQuery += ",";
                    }
                    updateQuery += " email = '" + email + "'";
                    ++flag;
                }

                if (!address.equals("")) {
                    if (flag != 0) {
                        updateQuery += ",";
                    }
                    updateQuery += " hotel_address = '" + address + "'";
                    ++flag;
                }

                if (!category.equals("")) {
                    if (flag != 0) {
                        updateQuery += ",";
                    }
                    updateQuery += " category = '" + category + "'";
                    ++flag;
                }

                if (!area.equals("")) {
                    if (flag != 0) {
                        updateQuery += ",";
                    }
                    updateQuery += " area = '" + area + "'";
                    ++flag;
                }

                updateQuery += " WHERE hotel_ID = " + hotel_id;

                statement.executeUpdate(updateQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (Flag == FLAG_INSERT) {
                    preparedStatement.close();
                }
                statement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public boolean updateDB(String room_id, String hotel_idStr, String type, String capacityStr, String amenities, String priceStr, String view, String CBE, String problems) {
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;

        if (room_id.equals("") || hotel_idStr.equals("")) {
            throw new IllegalArgumentException("ids cannot be empty");
        }

        int hotel_id = Integer.parseInt(hotel_idStr);
        int Flag = FLAG_INSERT;
        if (type.equals("") || capacityStr.equals("") || priceStr.equals("") || view.equals("") || CBE.equals("")) {
            Flag = FLAG_UPDATE;
        }

        String search_query = "SELECT * FROM room WHERE room_ID = '" + room_id +"'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(search_query);
            if (resultSet.next()) {
                if (Flag == FLAG_INSERT) {
                    Flag = FLAG_UPDATE;
                }
            } else {
                if (Flag == FLAG_UPDATE) {
                    return false;
                }
            }

            if (Flag == FLAG_INSERT) {
                search_query = "SELECT * FROM hotel WHERE hotel_ID = " + hotel_id +";";
                resultSet = statement.executeQuery(search_query);
                if (!resultSet.next()) {
                    throw new UnsupportedOperationException("Chain does not exist!");
                }

                preparedStatement = connection.prepareStatement(querySQL.INSERT_NEW_ROOM);
                preparedStatement.setString(1, room_id);
                preparedStatement.setString(2, type);
                preparedStatement.setInt(3, Integer.parseInt(capacityStr));
                preparedStatement.setString(4, amenities);
                preparedStatement.setInt(5, Integer.parseInt(priceStr));
                preparedStatement.setString(6, view);
                preparedStatement.setString(7, CBE);
                preparedStatement.setString(8, problems);
                preparedStatement.setInt(9, hotel_id);

                preparedStatement.executeUpdate();
            } else {
                String updateQuery = "UPDATE room SET";
                int flag = 0;

                if (!type.equals("")) {
                    updateQuery += " room_type = '" + type + "'";
                    ++flag;
                }

                if (!capacityStr.equals("")) {
                    if (flag != 0) {
                        updateQuery += ",";
                    }
                    updateQuery += " capacity = " + Integer.parseInt(capacityStr);
                    ++flag;
                }

                if (!amenities.equals("")) {
                    if (flag != 0) {
                        updateQuery += ",";
                    }
                    updateQuery += " amenities = '" + amenities + "'";
                    ++flag;
                }

                if (!priceStr.equals("")) {
                    if (flag != 0) {
                        updateQuery += ",";
                    }
                    updateQuery += " price = " + Integer.parseInt(priceStr);
                    ++flag;
                }

                if (!view.equals("")) {
                    if (flag != 0) {
                        updateQuery += ",";
                    }
                    updateQuery += " view_type = '" + view + "'";
                    ++flag;
                }

                if (!CBE.equals("")) {
                    if (flag != 0) {
                        updateQuery += ",";
                    }
                    updateQuery += " can_be_extended = '" + CBE + "'";
                    ++flag;
                }

                if (!problems.equals("")) {
                    if (flag != 0) {
                        updateQuery += ",";
                    }
                    updateQuery += " problems = '" + problems + "'";
                    ++flag;
                }

                updateQuery += " WHERE room_ID = '" + room_id + "'";

                statement.executeUpdate(updateQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (Flag == FLAG_INSERT) {
                    preparedStatement.close();
                }
                statement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
