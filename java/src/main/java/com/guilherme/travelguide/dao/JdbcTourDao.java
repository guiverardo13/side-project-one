package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.model.Hotel;
import com.guilherme.travelguide.model.Tour;
import com.guilherme.travelguide.security.exception.DaoException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcTourDao implements TourDao {

    private final JdbcTemplate jdbcTemplate;
    public JdbcTourDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tour> getToursByCityName(String cityName) {
        List<Tour> tourList = new ArrayList<>();
        String sql = "SELECT * from tourism JOIN city ON tour_city_id = city_id WHERE city_name = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityName);
            while (results.next()) {
                Tour tour = mapRowToTour(results);
                tourList.add(tour);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return tourList;
    }

    @Override
    public Tour getTourById(int tourId) {
        Tour tour = null;
        String sql = "SELECT * from tourism WHERE tour_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tourId);
            if (results.next()) {
                tour = mapRowToTour(results);
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return tour;
    }

    @Override
    public String getTourPictureById(int tourId) {
        String tourPicture = null;
        String sql = "SELECT tour_picture FROM tourism WHERE tour_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tourId);
            if (results.next()) {
                tourPicture = results.getString("tour_picture");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return tourPicture;
    }

    @Override
    public String getTourWebsiteById(int tourId) {
        String tourWebsite = null;
        String sql = "SELECT tour_website FROM tourism WHERE tour_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tourId);
            if(results.next()) {
                tourWebsite = results.getString("tour_website");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return tourWebsite;
    }

    @Override
    public String getTourAddressById(int tourId) {
        String tourAddress = null;
        String sql = "SELECT tour_address FROM tourism WHERE tour_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tourId);
            if(results.next()) {
                tourAddress = results.getString("tour_address");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return tourAddress;
    }

    @Override
    public String getTourPhoneById(int tourId) {
        String tourPhone = null;
        String sql = "SELECT tour_phone FROM tourism WHERE tour_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tourId);
            if(results.next()) {
                tourPhone = results.getString("tour_phone");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return tourPhone;
    }

    @Override
    public String getTourPriceById(int tourId) {
        String tourPrice = null;
        String sql = "SELECT tour_price FROM tourism WHERE tour_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tourId);
            if(results.next()) {
                tourPrice = results.getString("tour_price");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return tourPrice;
    }

    private Tour mapRowToTour(SqlRowSet rs) {
        Tour tour = new Tour();
        tour.setTourId(rs.getInt("tour_id"));
        tour.setTourCityId(rs.getInt("tour_city_id"));
        tour.setTourPicture(rs.getString("tour_picture"));
        tour.setTourCityName(rs.getString("tour_city_name"));
        tour.setTourName(rs.getString("tour_name"));
        tour.setTourWebsite(rs.getString("tour_website"));
        tour.setTourAddress(rs.getString("tour_address"));
        tour.setTourPhone(rs.getString("tour_phone"));
        tour.setTourPrice(rs.getString("tour_price"));

        return tour;
    }
}
