package com.guilherme.travelguide.dao;;
import java.util.Objects;
import com.guilherme.travelguide.model.User;
import com.guilherme.travelguide.security.exception.DaoException;
import com.guilherme.travelguide.security.model.RegisterUserDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;


    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserById(int userId) {
        User user = null;
        String sql = "SELECT user_id, username, first_name, last_name, password_hash, role, activated, email FROM users WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if (results.next()) {
                user = mapRowToUser(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");
        User user = null;
        String sql = "SELECT user_id, username, first_name, last_name, password_hash, role, activated, email FROM users WHERE username = ?;";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
            if (rowSet.next()) {
                user = mapRowToUser(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }

    @Override
    public User createUser(RegisterUserDto user) {
        return createUserWithRole(user, "USER");
    }

    private User createUserWithRole(RegisterUserDto newUser, String role) {
        User createdUser = null;

        String insertUserSql = "INSERT INTO users (username, first_name, last_name, password_hash, role, email) VALUES (?, ?, ?, ?, ?, ?) RETURNING user_id";

        String passwordHash = new BCryptPasswordEncoder().encode(newUser.getPassword());
        String formattedRole = role.startsWith("ROLE_") ? role : "ROLE_" + role;

        try {
            int newUserId = jdbcTemplate.queryForObject(insertUserSql, int.class, newUser.getUsername(), newUser.getFirstName(), newUser.getLastName(), passwordHash, formattedRole, newUser.getEmail());

            createdUser = getUserById(newUserId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return createdUser;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(rs.getBoolean("activated"));
        user.setEmail(rs.getString("email"));

        return user;
    }
}
