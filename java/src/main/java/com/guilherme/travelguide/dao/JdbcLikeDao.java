package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.model.*;
import com.guilherme.travelguide.security.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcLikeDao implements LikeDao {

    private final JdbcTemplate jdbcTemplate;
    Hotel hotel = new Hotel();

    public JdbcLikeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Like> getLikesByUserId(int userId) {
        List<Like> likeList = new ArrayList<>();
        String sql = "SELECT * FROM likes " +
                     "JOIN user_likes USING (like_id) " +
                     "WHERE user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                Like like = mapRowToLike(results);
                likeList.add(like);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return likeList;
    }

    @Override
    public Like getLikeById(int likeId) {
        Like like = null;
        String sql = "SELECT * FROM likes WHERE like_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, likeId);
            if (results.next()) {
                like = mapRowToLike(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return like;
    }

    // user ID is not present
    @Override
    public Like addHotelToLikeList(User user, Like like, Hotel hotel) {
        int hotelId = hotel.getHotelId();

        if (hotelId == 0) {
            throw new DaoException("Hotel information not found for hotelId: " + hotel.getHotelId());
        }

        // Create a new Like object and set its properties
        Like newLike = new Like();
        newLike.setLikeCityId(hotel.getHotelCityId());
        newLike.setLikePicture(hotel.getHotelPicture());
        newLike.setLikeCityName(hotel.getHotelCityName());
        newLike.setLikeName(hotel.getHotelName());
        newLike.setLikeWebsite(hotel.getHotelWebsite());
        newLike.setLikeAddress(hotel.getHotelAddress());
        newLike.setLikePhone(hotel.getHotelPhone());
        newLike.setLikePrice(hotel.getHotelPrice());

        // Set the hotel object in the newLike object
        newLike.setHotel(hotel);

        try {
            String insertLikeSql = "INSERT INTO likes (like_city_id, like_hotel_id, like_picture, like_city_name, like_name, like_address, like_phone, like_price, like_website) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING like_id;";
            int newLikeId = jdbcTemplate.queryForObject(insertLikeSql, int.class,
                    newLike.getLikeCityId(), hotelId, newLike.getLikePicture(),
                    newLike.getLikeCityName(), newLike.getLikeName(), newLike.getLikeAddress(),
                    newLike.getLikePhone(), newLike.getLikePrice(), newLike.getLikeWebsite());

            // Now, insert the association into the user_likes table
            String insertUserLikesSql = "INSERT INTO user_likes (user_id, like_id) VALUES (?, ?)";
            jdbcTemplate.update(insertUserLikesSql, user.getUserId(), newLikeId);

            newLike.setLikeId(newLikeId); // Set the generated like_id in the newLike object

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (Exception e) {
            throw new DaoException("An error occurred while adding the like", e);
        }

        return newLike;
    }

    public Like addBarToLikeList(User user, Like like, Bar bar) {
        int barId = bar.getBarId();

        if (barId == 0) {
            throw new DaoException("Bar information not found for barId: " + bar.getBarId());
        }

        // Create a new Like object and set its properties
        Like newLike = new Like();
        newLike.setLikeCityId(bar.getBarCityId());
        newLike.setLikePicture(bar.getBarPicture());
        newLike.setLikeCityName(bar.getBarCityName());
        newLike.setLikeName(bar.getBarName());
        newLike.setLikeWebsite(bar.getBarWebsite());
        newLike.setLikeAddress(bar.getBarAddress());
        newLike.setLikePhone(bar.getBarPhone());
        newLike.setLikePrice(bar.getBarPrice());

        // Set the hotel object in the newLike object
        newLike.setBar(bar);

        try {
            String insertLikeSql = "INSERT INTO likes (like_city_id, like_bar_id, like_picture, like_city_name, like_name, like_address, like_phone, like_price, like_website) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING like_id;";
            int newLikeId = jdbcTemplate.queryForObject(insertLikeSql, int.class,
                    newLike.getLikeCityId(), barId, newLike.getLikePicture(),
                    newLike.getLikeCityName(), newLike.getLikeName(), newLike.getLikeAddress(),
                    newLike.getLikePhone(), newLike.getLikePrice(), newLike.getLikeWebsite());

            // Now, insert the association into the user_likes table
            String insertUserLikesSql = "INSERT INTO user_likes (user_id, like_id) VALUES (?, ?)";
            jdbcTemplate.update(insertUserLikesSql, user.getUserId(), newLikeId);

            newLike.setLikeId(newLikeId); // Set the generated like_id in the newLike object

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (Exception e) {
            throw new DaoException("An error occurred while adding the like", e);
        }

        return newLike;
    }

    public Like addRestaurantToLikeList(User user, Like like, Restaurant restaurant) {
        int restaurantId = restaurant.getRestaurantId();

        if (restaurantId == 0) {
            throw new DaoException("Restaurant information not found for restaurantId: " + restaurant.getRestaurantId());
        }

        // Create a new Like object and set its properties
        Like newLike = new Like();
        newLike.setLikeCityId(restaurant.getRestaurantCityId());
        newLike.setLikePicture(restaurant.getRestaurantPicture());
        newLike.setLikeCityName(restaurant.getRestaurantCityName());
        newLike.setLikeName(restaurant.getRestaurantName());
        newLike.setLikeWebsite(restaurant.getRestaurantWebsite());
        newLike.setLikeAddress(restaurant.getRestaurantAddress());
        newLike.setLikePhone(restaurant.getRestaurantPhone());
        newLike.setLikePrice(restaurant.getRestaurantPrice());

        // Set the hotel object in the newLike object
        newLike.setRestaurant(restaurant);

        try {
            String insertLikeSql = "INSERT INTO likes (like_city_id, like_restaurant_id, like_picture, like_city_name, like_name, like_address, like_phone, like_price, like_website) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING like_id;";
            int newLikeId = jdbcTemplate.queryForObject(insertLikeSql, int.class,
                    newLike.getLikeCityId(), restaurantId, newLike.getLikePicture(),
                    newLike.getLikeCityName(), newLike.getLikeName(), newLike.getLikeAddress(),
                    newLike.getLikePhone(), newLike.getLikePrice(), newLike.getLikeWebsite());

            // Now, insert the association into the user_likes table
            String insertUserLikesSql = "INSERT INTO user_likes (user_id, like_id) VALUES (?, ?)";
            jdbcTemplate.update(insertUserLikesSql, user.getUserId(), newLikeId);

            newLike.setLikeId(newLikeId); // Set the generated like_id in the newLike object

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (Exception e) {
            throw new DaoException("An error occurred while adding the like", e);
        }

        return newLike;
    }

    @Override
    public Like addEventToLikeList(User user, Like like, Event event) {
        int eventId = event.getEventId();

        if (eventId == 0) {
            throw new DaoException("Event information not found for eventId: " + event.getEventId());
        }

        // Create a new Like object and set its properties
        Like newLike = new Like();
        newLike.setLikeCityId(event.getEventCityId());
        newLike.setLikePicture(event.getEventPicture());
        newLike.setLikeCityName(event.getEventCityName());
        newLike.setLikeName(event.getEventName());
        newLike.setLikeWebsite(event.getEventWebsite());
        newLike.setLikeAddress(event.getEventAddress());
        newLike.setLikePhone(event.getEventPhone());
        newLike.setLikePrice(event.getEventPrice());

        // Set the hotel object in the newLike object
        newLike.setEvent(event);

        try {
            String insertLikeSql = "INSERT INTO likes (like_city_id, like_event_id, like_picture, like_city_name, like_name, like_address, like_phone, like_price, like_website) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING like_id;";
            int newLikeId = jdbcTemplate.queryForObject(insertLikeSql, int.class,
                    newLike.getLikeCityId(), eventId, newLike.getLikePicture(),
                    newLike.getLikeCityName(), newLike.getLikeName(), newLike.getLikeAddress(),
                    newLike.getLikePhone(), newLike.getLikePrice(), newLike.getLikeWebsite());

            // Now, insert the association into the user_likes table
            String insertUserLikesSql = "INSERT INTO user_likes (user_id, like_id) VALUES (?, ?)";
            jdbcTemplate.update(insertUserLikesSql, user.getUserId(), newLikeId);

            newLike.setLikeId(newLikeId); // Set the generated like_id in the newLike object

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (Exception e) {
            throw new DaoException("An error occurred while adding the like", e);
        }

        return newLike;
    }

    @Override
    public boolean deleteLike(int likeId, int userId) {
        try {
            // Check if the like belongs to the user
            String checkOwnershipSql = "SELECT COUNT(*) FROM user_likes WHERE like_id = ? AND user_id = ?";
            int count = jdbcTemplate.queryForObject(checkOwnershipSql, Integer.class, likeId, userId);

            if (count == 0) {
                // The like doesn't belong to the user, so it cannot be deleted
                return false;
            }

            // Delete from user_likes table
            String deleteUserLikesSql = "DELETE FROM user_likes WHERE like_id = ?";
            int deletedUserLikesCount = jdbcTemplate.update(deleteUserLikesSql, likeId);

            // Delete from likes table
            String deleteLikesSql = "DELETE FROM likes WHERE like_id = ?";
            int deletedLikesCount = jdbcTemplate.update(deleteLikesSql, likeId);

            // Check if at least one row was deleted from each table
            return deletedUserLikesCount > 0 && deletedLikesCount > 0;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (Exception e) {
            throw new DaoException("An error occurred while deleting the like", e);
        }
    }


    private Like mapRowToLike(SqlRowSet rs) {
        Like like = new Like();
        like.setLikeId(rs.getInt("like_id"));
        like.setLikeCityId(rs.getInt("like_city_id"));
        like.setLikeBarId(rs.getInt("like_bar_id"));
        like.setLikeHotelId(rs.getInt("like_hotel_id"));
        like.setLikeRestaurantId(rs.getInt("like_restaurant_id"));
        like.setLikeEventId(rs.getInt("like_event_id"));
        like.setLikeTourId(rs.getInt("like_tour_id"));
        like.setLikePicture(rs.getString("like_picture"));
        like.setLikeCityName(rs.getString("like_city_name"));
        like.setLikeName(rs.getString("like_name"));
        like.setLikeAddress(rs.getString("like_address"));
        like.setLikePhone(rs.getString("like_phone"));
        like.setLikePrice(rs.getString("like_price"));
        like.setLikeWebsite(rs.getString("like_website"));

        return like;
    }

    private Hotel mapRowToHotel(SqlRowSet rs) {
        Hotel hotel = new Hotel();
        hotel.setHotelId(rs.getInt("hotel_id"));
        hotel.setHotelCityId(rs.getInt("hotel_city_id"));
        hotel.setHotelPicture(rs.getString("hotel_picture"));
        hotel.setHotelCityName(rs.getString("hotel_city_name"));
        hotel.setHotelName(rs.getString("hotel_name"));
        hotel.setHotelWebsite(rs.getString("hotel_website"));
        hotel.setHotelAddress(rs.getString("hotel_address"));
        hotel.setHotelPhone(rs.getString("hotel_phone"));
        hotel.setHotelPrice(rs.getString("hotel_price"));

        return hotel;
    }

    private Bar mapRowToBar(SqlRowSet rs) {
        Bar bar = new Bar();
        bar.setBarId(rs.getInt("bar_id"));
        bar.setBarCityId(rs.getInt("bar_city_id"));
        bar.setBarPicture(rs.getString("bar_picture"));
        bar.setBarName(rs.getString("bar_name"));
        bar.setBarWebsite(rs.getString("bar_website"));
        bar.setBarAddress(rs.getString("bar_address"));
        bar.setBarPhone(rs.getString("bar_phone"));
        bar.setBarPrice(rs.getString("bar_price"));
        return bar;
    }

    private Restaurant mapRowToRestaurant(SqlRowSet rs) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(rs.getInt("restaurant_id"));
        restaurant.setRestaurantCityId(rs.getInt("restaurant_city_id"));
        restaurant.setRestaurantPicture(rs.getString("restaurant_picture"));
        restaurant.setRestaurantCityName(rs.getString("restaurant_city_name"));
        restaurant.setRestaurantName(rs.getString("restaurant_name"));
        restaurant.setRestaurantWebsite(rs.getString("restaurant_website"));
        restaurant.setRestaurantAddress(rs.getString("restaurant_address"));
        restaurant.setRestaurantPhone(rs.getString("restaurant_phone"));
        restaurant.setRestaurantPrice(rs.getString("restaurant_price"));

        return restaurant;
    }
}
