package com.guilherme.travelguide.dao;

import com.guilherme.travelguide.model.Event;
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
public class JdbcEventDao implements EventDao {

    private final JdbcTemplate jdbcTemplate;
    public JdbcEventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Event> getEventsByCityName(String cityName) {
        List<Event> eventList = new ArrayList<>();
        String sql = "SELECT * FROM events JOIN city ON event_city_id = city_id WHERE city_name = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cityName);
            while (results.next()) {
                Event event = mapRowToEvent(results);
                eventList.add(event);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return eventList;
    }

    @Override
    public Event getEventById(int eventId) {
        Event event = null;
        String sql = "SELECT * FROM events WHERE event_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
            if(results.next()) {
                event = mapRowToEvent(results);
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return event;
    }

    @Override
    public String getEventPictureById(int eventId) {
        String eventPicture = null;
        String sql = "SELECT event_picture FROM events WHERE event_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
            if(results.next()) {
                eventPicture = results.getString("event_picture");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return eventPicture;
    }

    @Override
    public String getEventWebsiteById(int eventId) {
        String eventWebsite = null;
        String sql = "SELECT event_website FROM events WHERE event_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
            if(results.next()) {
                eventWebsite = results.getString("event_website");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return eventWebsite;
    }

    @Override
    public String getEventAddressById(int eventId) {
        String eventAddress = null;
        String sql = "SELECT event_address FROM events WHERE event_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
            if(results.next()) {
                eventAddress = results.getString("event_address");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return eventAddress;
    }

    @Override
    public String getEventPhoneById(int eventId) {
        String eventPhone = null;
        String sql = "SELECT event_phone FROM events WHERE event_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
            if(results.next()) {
                eventPhone = results.getString("event_phone");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return eventPhone;
    }

    @Override
    public String getEventPriceById(int eventId) {
        String eventPrice = null;
        String sql = "SELECT event_price FROM events WHERE event_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
            if(results.next()) {
                eventPrice = results.getString("event_price");
            }
        } catch (DataAccessException e) {
            throw new DaoException("Error accessing data", e);
        }
        return eventPrice;
    }

    private Event mapRowToEvent(SqlRowSet rs) {
        Event event = new Event();
        event.setEventId(rs.getInt("event_id"));
        event.setEventCityId(rs.getInt("event_city_id"));
        event.setEventPicture(rs.getString("event_picture"));
        event.setEventCityName(rs.getString("event_city_name"));
        event.setEventName(rs.getString("event_name"));
        event.setEventWebsite(rs.getString("event_website"));
        event.setEventAddress(rs.getString("event_address"));
        event.setEventPhone(rs.getString("event_phone"));
        event.setEventPrice(rs.getString("event_price"));

        return event;
    }
}
