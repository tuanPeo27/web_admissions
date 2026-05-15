package com.webadmissions.repository;

import com.webadmissions.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<User> findActiveByUsernameAndPassword(String username, String password) {
        String sql = "SELECT id, username, password, role, status "
            + "FROM xt_users "
            + "WHERE username = ? AND password = ? AND (status IS NULL OR status = 1)";
        return jdbcTemplate.query(sql, new UserRowMapper(), username, password)
            .stream()
            .findFirst();
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            Object statusValue = rs.getObject("status");
            user.setStatus(statusValue == null ? null : rs.getBoolean("status"));
            return user;
        }
    }
}
