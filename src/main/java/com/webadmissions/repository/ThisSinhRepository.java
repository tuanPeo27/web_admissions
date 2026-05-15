package com.webadmissions.repository;

import com.webadmissions.model.ThisSinh;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ThisSinhRepository {
    private final JdbcTemplate jdbcTemplate;

    public ThisSinhRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<ThisSinh> findByCccd(String cccd) {
        String sql = "SELECT cccd, ho, ten, ngay_sinh FROM xt_thisinhxettuyen25 WHERE cccd = ?";
        return jdbcTemplate.query(sql, new ThisSinhRowMapper(), cccd)
            .stream()
            .findFirst();
    }

    private static class ThisSinhRowMapper implements RowMapper<ThisSinh> {
        @Override
        public ThisSinh mapRow(ResultSet rs, int rowNum) throws SQLException {
            ThisSinh ts = new ThisSinh();
            ts.setCccd(rs.getString("cccd"));
            ts.setHo(rs.getString("ho"));
            ts.setTen(rs.getString("ten"));
            Date ngaySinh = rs.getDate("ngay_sinh");
            ts.setNgaySinh(ngaySinh == null ? null : ngaySinh.toLocalDate());
            return ts;
        }
    }
}
