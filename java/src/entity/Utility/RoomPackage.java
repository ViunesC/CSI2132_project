package entity.Utility;

import entity.Hotel;
import entity.Room;

public class RoomPackage {
    private Hotel hotel;
    private Room room;

    private RoomPackage(Room room, Hotel hotel) {
        this.hotel = hotel;
        this.room = room;
    }

    public static RoomPackage createPackage(Room room, Hotel hotel) {
        if (room == null || hotel == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }

        return new RoomPackage(room, hotel);
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Room getRoom() {
        return room;
    }
}
