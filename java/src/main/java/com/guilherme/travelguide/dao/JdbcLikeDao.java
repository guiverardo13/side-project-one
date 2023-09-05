package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.model.City;
import com.guilherme.travelguide.model.Like;
import com.guilherme.travelguide.security.exception.DaoException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcLikeDao implements LikeDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcLikeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Like> getLikesByUsername(String username) {
        List<Like> likeList = new ArrayList<>();
        String sql = "SELECT * FROM likes JOIN user_likes USING (like_id) " +
                "JOIN users USING (user_id) WHERE username = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
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
    public String getCityNameByLikeCityId(int likeCityId, String username) {
        String cityName = null;
        String sql = "SELECT city_name FROM likes " +
                "JOIN city ON likes.like_city_id = city.city_id " +
                "JOIN user_likes USING (like_id) " +
                "JOIN users ON user_likes.user_id = users.user_id " +
                "WHERE likes.like_city_id = ? AND users.username = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, likeCityId, username);
            if (results.next()) {
                cityName = results.getString("city_name");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return cityName;
    }

    @Override
    public Like getHotelByLikeHotelId(int likeHotelId, String username) {
        Like like = null;
        String sql = "SELECT hotel_picture, hotel_name, hotel_website, hotel_address, hotel_phone, hotel_price FROM likes " +
                "JOIN hotel on hotel_id = like_hotel_id " +
                "JOIN user_likes USING (like_id) " +
                "JOIN users USING (user_id) " +
                "WHERE like_hotel_id = ? AND username = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, likeHotelId, username);
            if(results.next()) {
                like = mapRowToLike(results);
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return like;
    }

    @Override
    public Like getBarByLikeBarId(int likeBarId, String username) {
        Like like = null;
        String sql = "SELECT bar_picture, bar_name, bar_website, bar_address, bar_phone, bar_price FROM likes " +
                "JOIN bar on bar_id = like_bar_id " +
                "JOIN user_likes USING (like_id) " +
                "JOIN users USING (user_id) " +
                "WHERE like_bar_id = ? AND username = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, likeBarId, username);
            if(results.next()) {
                like = mapRowToLike(results);
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return like;
    }

    @Override
    public Like getRestaurantByLikeRestaurantId(int likeRestaurantId, String username) {
        Like like = null;
        String sql = "SELECT restaurant_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price FROM likes " +
                "JOIN restaurant on restaurant_id = like_restaurant_id " +
                "JOIN user_likes USING (like_id) " +
                "JOIN users USING (user_id) " +
                "WHERE like_restaurant_id = ? AND username = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, likeRestaurantId, username);
            if(results.next()) {
                like = mapRowToLike(results);
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return like;
    }

//    @Override
//    public Like getEventByLikeEventId(int likeEventId, String username) {
//        Like like = null;
//        String sql = "SELECT event_picture, restaurant_name, restaurant_website, restaurant_address, restaurant_phone, restaurant_price FROM likes " +
//                "JOIN restaurant on restaurant_id = like_restaurant_id " +
//                "JOIN user_likes USING (like_id) " +
//                "JOIN users USING (user_id) " +
//                "WHERE like_restaurant_id = ? AND username = ?;";
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, likeEventId, username);
//            if(results.next()) {
//                like = mapRowToLike(results);
//            }
//        } catch (DataAccessException e) {
//            throw new DaoException("Error accessing data", e);
//        }
//        return like;
//    }
//
//    @Override
//    public Like getTourByLikeTourId(int likeTourId, String username) {
//        return null;
//    }

    private Like mapRowToLike(SqlRowSet rs) {
        Like like = new Like();
        like.setLikeCityId(rs.getInt("like_city_id"));
        like.setLikeBarId(rs.getInt("like_bar_id"));
        like.setLikeHotelId(rs.getInt("like_hotel_id"));
        like.setLikeRestaurantId(rs.getInt("like_restaurant_id"));
        like.setLikeEventId(rs.getInt("like_event_id"));
        like.setLikeTourId(rs.getInt("like_tour_id"));

        return like;
    }
}
