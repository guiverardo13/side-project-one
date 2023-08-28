package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.security.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.dao.DataAccessException;
import com.guilherme.travelguide.model.City;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcCityDao implements CityDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCityDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<City> getCities() {
        List<City> cityList = new ArrayList<>();
        String sql = "SELECT * FROM city";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                City city = mapRowToCity(results);
                cityList.add(city);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return cityList;
    }

    @Override
    public City getCityById(int cityId) {
        City city = null;
        String sql = "SELECT * FROM city WHERE city_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityId);
            if (results.next()) {
                city = mapRowToCity(results);
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return city;
    }

    @Override
    public City getCityByName(String cityName) {
        City city = null;
        String sql = "SELECT * FROM city WHERE city_name = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityName);
            if (results.next()) {
                city = mapRowToCity(results);
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return city;
    }

    @Override
    public String getCoverPictureByCityName(String cityName) {
        String coverPicture = null;
        String sql = "SELECT cover_picture FROM city WHERE city_name = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityName);
            if (results.next()) {
                coverPicture = results.getString("cover_picture");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return coverPicture;
    }

    @Override
    public String getDescriptionByCityName(String cityName) {
        String cityDescription = null;
        String sql = "SELECT city_description FROM city WHERE city_name = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityName);
            if (results.next()) {
                cityDescription = results.getString("city_description");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return cityDescription;
    }

    @Override
    public String getVideoByCityName(String cityName) {
        String cityVideo = null;
        String sql = "SELECT city_video FROM city WHERE city_name = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityName);
            if (results.next()) {
                cityVideo = results.getString("city_video");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return cityVideo;
    }

    private City mapRowToCity(SqlRowSet rs) {
        City city = new City();
        city.setCityID(rs.getInt("city_id"));
        city.setCityName(rs.getString("city_name"));
        city.setStateName(rs.getString("state_name"));
        city.setCoverPicture(rs.getString("cover_picture"));
        city.setCityDescription(rs.getString("city_description"));
        city.setCityVideo(rs.getString("city_video"));
        return city;
    }
}
