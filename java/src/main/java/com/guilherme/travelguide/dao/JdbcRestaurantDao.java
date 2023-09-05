package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.model.Restaurant;
import com.guilherme.travelguide.security.exception.DaoException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcRestaurantDao implements RestaurantDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRestaurantDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Restaurant> getRestaurantsByCityName(String cityName) {
        List<Restaurant> restaurantList = new ArrayList<>();
        String sql = "SELECT * FROM restaurant JOIN city ON restaurant_city_id = city_id WHERE city_name = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityName);
            while (results.next()) {
                Restaurant restaurant = mapRowToRestaurant(results);
                restaurantList.add(restaurant);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return restaurantList;
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        Restaurant restaurant = null;
        String sql = "SELECT * FROM restaurant WHERE restaurant_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, restaurantId);
            if(results.next()) {
                restaurant = mapRowToRestaurant(results);
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return restaurant;
    }

    @Override
    public String getRestaurantPictureById(int restaurantId) {
        String restaurantPicture = null;
        String sql = "SELECT restaurant_picture FROM restaurant WHERE restaurant_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, restaurantId);
            if(results.next()) {
                restaurantPicture = results.getString("restaurant_picture");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return restaurantPicture;
    }

    @Override
    public String getRestaurantWebsiteById(int restaurantId) {
        String restaurantWebsite = null;
        String sql = "SELECT restaurant_website FROM restaurant WHERE restaurant_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, restaurantId);
            if(results.next()) {
                restaurantWebsite = results.getString("restaurant_website");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return restaurantWebsite;
    }

    @Override
    public String getRestaurantAddressById(int restaurantId) {
        String restaurantAddress = null;
        String sql = "SELECT restaurant_address FROM restaurant WHERE restaurant_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, restaurantId);
            if(results.next()) {
                restaurantAddress = results.getString("restaurant_address");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return restaurantAddress;
    }

    @Override
    public String getRestaurantPhoneById(int restaurantId) {
        String restaurantPhone = null;
        String sql = "SELECT restaurant_phone FROM restaurant WHERE restaurant_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, restaurantId);
            if(results.next()) {
                restaurantPhone = results.getString("restaurant_phone");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return restaurantPhone;
    }

    @Override
    public String getRestaurantPriceById(int restaurantId) {
        String restaurantPrice = null;
        String sql = "SELECT restaurant_price FROM restaurant WHERE restaurant_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, restaurantId);
            if(results.next()) {
                restaurantPrice = results.getString("restaurant_price");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return restaurantPrice;
    }

    private Restaurant mapRowToRestaurant(SqlRowSet rs) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(rs.getInt("restaurant_id"));
        restaurant.setRestaurantCityId(rs.getInt("restaurant_city_id"));
        restaurant.setRestaurantPicture(rs.getString("restaurant_picture"));
        restaurant.setRestaurantName(rs.getString("restaurant_name"));
        restaurant.setRestaurantWebsite(rs.getString("restaurant_website"));
        restaurant.setRestaurantAddress(rs.getString("restaurant_address"));
        restaurant.setRestaurantPhone(rs.getString("restaurant_phone"));
        restaurant.setRestaurantPrice(rs.getString("restaurant_price"));

        return restaurant;
    }
}
