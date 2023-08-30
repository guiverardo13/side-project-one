package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.model.Hotel;
import com.guilherme.travelguide.security.exception.DaoException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcHotelDao implements HotelDao {

    private final JdbcTemplate jdbcTemplate;
    public JdbcHotelDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Hotel> getHotelsByCityName(String cityName) {
       List<Hotel> hotelList = new ArrayList<>();
       String sql = "SELECT * FROM hotel JOIN city ON hotel_city_id = city_id WHERE city_name = ?;";
       try {
           SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityName);
           while (results.next()) {
               Hotel hotel = mapRowToHotel(results);
               hotelList.add(hotel);
           }
       } catch (CannotGetJdbcConnectionException e) {
           throw new DaoException("Unable to connect to server or database", e);
       }
       return hotelList;
    }

    @Override
    public Hotel getHotelById(int hotelId) {
        Hotel hotel = null;
        String sql = "SELECT * FROM hotel WHERE hotel_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, hotelId);
            if(results.next()) {
                hotel = mapRowToHotel(results);
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return hotel;
    }

    @Override
    public String getHotelPictureById(int hotelId) {
        String hotelPicture = null;
        String sql = "SELECT hotel_picture FROM hotel WHERE hotel_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, hotelId);
            if(results.next()) {
                hotelPicture = results.getString("hotel_picture");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return hotelPicture;
    }

    @Override
    public String getHotelWebsiteById(int hotelId) {
        String hotelWebsite = null;
        String sql = "SELECT hotel_website FROM hotel WHERE hotel_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, hotelId);
            if(results.next()) {
                hotelWebsite = results.getString("hotel_website");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return hotelWebsite;
    }

    @Override
    public String getHotelAddressById(int hotelId) {
        String hotelAddress = null;
        String sql = "SELECT hotel_address FROM hotel WHERE hotel_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, hotelId);
            if(results.next()) {
                hotelAddress = results.getString("hotel_address");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return hotelAddress;
    }

    @Override
    public String getHotelPhoneById(int hotelId) {
        String hotelPhone = null;
        String sql = "SELECT hotel_phone FROM hotel WHERE hotel_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, hotelId);
            if(results.next()) {
                hotelPhone = results.getString("hotel_phone");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return hotelPhone;
    }

    @Override
    public String getHotelPriceById(int hotelId) {
        String hotelPrice = null;
        String sql = "SELECT hotel_price FROM hotel WHERE hotel_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, hotelId);
            if(results.next()) {
                hotelPrice = results.getString("hotel_price");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return hotelPrice;
    }

    private Hotel mapRowToHotel(SqlRowSet rs) {
        Hotel hotel = new Hotel();
        hotel.setHotelId(rs.getInt("hotel_id"));
        hotel.setHotelCityId(rs.getInt("hotel_city_id"));
        hotel.setHotelPicture(rs.getString("hotel_picture"));
        hotel.setHotelName(rs.getString("hotel_name"));
        hotel.setHotelWebsite(rs.getString("hotel_website"));
        hotel.setHotelAddress(rs.getString("hotel_address"));
        hotel.setHotelPhone(rs.getString("hotel_phone"));
        hotel.setHotelPrice(rs.getString("hotel_price"));

        return hotel;
    }
}
