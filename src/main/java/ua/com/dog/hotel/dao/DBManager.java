package ua.com.dog.hotel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.com.dog.hotel.model.bookingrequest.BookingRequest;
import ua.com.dog.hotel.model.order.Order;
import ua.com.dog.hotel.model.room.RoomCategory;
import ua.com.dog.hotel.model.room.Room;
import ua.com.dog.hotel.model.user.User;
import ua.com.dog.hotel.model.user.UserRole;

/**
 * DB manager. Works with MySQL DB. Only the required DAO methods are defined!
 *
 * @author Iegor
 */
public final class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);

    private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private static DBManager instance;

    private DBManager() {
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOG.error("Cannot connect to data base", e);
        }
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void updateBookingRequestRoomId(int bookingRequestId, int roomId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("UPDATE booking_request SET room_id=?, request_state=2 WHERE id=?");
            int k = 1;
            pstmt.setInt(k++, roomId);
            pstmt.setInt(k++, bookingRequestId);
            pstmt.executeUpdate();
            commit(con);

        } catch (SQLException ex) {
            rollback(con);
            LOG.error("Cannot update a user", ex);
        } finally {
            close(con);
            close(pstmt);

        }
    }

    public List<Room> sortedSelectAllFreeRooms(int sortCriteriaValue) {
        List<Room> roomsList = new ArrayList<Room>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            String sortField = null;
            switch (sortCriteriaValue) {
                case 1:
                    sortField = "";
                    break;
                case 2:
                    sortField = "price";
                    break;
                case 3:
                    sortField = "place_amount";
                    break;
                case 4:
                    sortField = "category_id";
                    break;
            }
            con.prepareStatement("SELECT * FROM rooms INNER JOIN categories ON categories.id = rooms.category_id WHERE rooms.busy_state = 0 ORDER BY " + sortField);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                roomsList.add(extractRoom(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error("Cannot obtain room items", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return roomsList;
    }

    public List<Room> selectRoomByUserRequest(int category, int roomsAmount) {
        List<Room> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con
                    .prepareStatement("SELECT * FROM rooms INNER JOIN categories ON categories.id = rooms.category_id WHERE category_id=? and place_amount=? and rooms.busy_state = 0");
            pstmt.setInt(1, category);
            pstmt.setInt(2, roomsAmount);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(extractRoom(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error("Cannot obtain room items", ex);
        } finally {
            close(rs);
            close(pstmt);
            close(con);
        }
        return list;
    }

    public List<BookingRequest> selectAllbookingRequests() {
        List<BookingRequest> brList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            rs = stmt
                    .executeQuery("SELECT * FROM booking_request INNER JOIN users ON users.id = booking_request.user_id");
            while (rs.next()) {
                brList.add(extractBookingRequest(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            rollback(con);
            LOG.error("Cannot obtain booking request items", ex);
        } finally {
            close(rs);
            close(stmt);
            close(con);
        }
        return brList;
    }

    /**
     * Extracts a room entity from the result set.
     *
     * @param rs Result set from which a user entity will be extracted.
     * @return Room entity
     */
    private Room extractRoom(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getInt("id"));
        room.setNumber(rs.getInt("number"));
        room.setPlaceAmount(rs.getInt("place_amount"));
        room.setCategoryId(rs.getInt("category_id"));
        room.setPrice(rs.getInt("price"));
        room.setBusyStateId(rs.getInt("busy_state"));
        return room;
    }

    /**
     * Extracts an booking request entity from the result set.
     *
     * @param rs Result set from which a booking request entity will be
     *           extracted.
     * @return
     */
    private BookingRequest extractBookingRequest(ResultSet rs) throws SQLException {
        BookingRequest br = new BookingRequest();
        br.setId(rs.getInt("id"));
        br.setRoomsAmount(rs.getInt("rooms_amount"));
        br.setUserId(rs.getInt("user_id"));
        br.setCategoryId(rs.getInt("category_id"));
        br.setDateCheckIn(rs.getDate("date_arrival"));
        br.setDateCheckOut(rs.getDate("date_check_out"));
        return br;
    }

    private void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOG.error("Cannot commit transaction and close connection", ex);
            }
        }
    }

    private void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error("Cannot close a result set", ex);
            }
        }
    }

    private void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOG.error("Cannot close a statement", ex);
            }
        }
    }

    /**
     * Commit the given connection.
     *
     * @param con Connection to be commited.
     */
    private void commit(Connection con) {
        if (con != null) {
            try {
                con.commit();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }

    /**
     * Rollbacks the given connection.
     *
     * @param con Connection to be rollbacked.
     */
    private void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }
}