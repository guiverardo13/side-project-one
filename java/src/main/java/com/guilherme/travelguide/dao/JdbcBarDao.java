package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.model.Bar;
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
public class JdbcBarDao implements BarDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcBarDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Bar> getBarsByCityName(String cityName) {
        List<Bar> barList = new ArrayList<>();
        String sql = "SELECT * FROM bar JOIN city ON bar_city_id = city_id WHERE city_name = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityName);
            while (results.next()) {
                Bar bar = mapRowToBar(results);
                barList.add(bar);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return barList;
    }

    @Override
    public Bar getBarById(int barId) {
        Bar bar = null;
        String sql = "SELECT * FROM bar WHERE bar_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, barId);
            if(results.next()) {
                bar = mapRowToBar(results);
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return bar;
    }

    @Override
    public String getBarPictureById(int barId) {
        String barPicture = null;
        String sql = "SELECT bar_picture FROM bar WHERE bar_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, barId);
            if(results.next()) {
                barPicture = results.getString("bar_picture");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return barPicture;
    }

    @Override
    public String getBarWebsiteById(int barId) {
        String barWebsite = null;
        String sql = "SELECT bar_website FROM bar WHERE bar_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, barId);
            if(results.next()) {
                barWebsite = results.getString("bar_website");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return barWebsite;
    }

    @Override
    public String getBarAddressById(int barId) {
        String barAddress = null;
        String sql = "SELECT bar_address FROM bar WHERE bar_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, barId);
            if(results.next()) {
                barAddress = results.getString("bar_address");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return barAddress;
    }

    @Override
    public String getBarPhoneById(int barId) {
        String barPhone = null;
        String sql = "SELECT bar_phone FROM bar WHERE bar_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, barId);
            if(results.next()) {
                barPhone = results.getString("bar_phone");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return barPhone;
    }

    @Override
    public String getBarPriceById(int barId) {
        String barPrice = null;
        String sql = "SELECT bar_price FROM bar WHERE bar_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, barId);
            if(results.next()) {
                barPrice = results.getString("bar_price");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return barPrice;
    }

    private Bar mapRowToBar(SqlRowSet rs) {
        Bar bar = new Bar();
        bar.setBarId(rs.getInt("bar_id"));
        bar.setBarCityId(rs.getInt("bar_city_id"));
        bar.setBarPicture(rs.getString("bar_picture"));
        bar.setBarCityName(rs.getString("bar_city_name"));
        bar.setBarName(rs.getString("bar_name"));
        bar.setBarWebsite(rs.getString("bar_website"));
        bar.setBarAddress(rs.getString("bar_address"));
        bar.setBarPhone(rs.getString("bar_phone"));
        bar.setBarPrice(rs.getString("bar_price"));
        return bar;
    }
}
