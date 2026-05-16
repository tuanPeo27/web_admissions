package com.webadmissions.repository;

import com.webadmissions.model.NguyenVongResult;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class NguyenVongRepository {
    private final JdbcTemplate jdbcTemplate;

    public NguyenVongRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<NguyenVongResult> findByCccd(String cccd) {
        String sql = "SELECT nv.idnv, nv.nn_cccd, nv.nv_manganh, nv.nv_tt, "
            + "nv.diem_thxt, nv.diem_utqd, nv.diem_cong, nv.diem_xettuyen, "
            + "nv.nv_ketqua, nv.tt_phuongthuc, nv.tt_thm, "
            + "n.tennganh, th.mon1, th.mon2, th.mon3, th.tentohop "
            + "FROM xt_nguyenvongxettuyen nv "
            + "LEFT JOIN xt_nganh n ON "
            + "CONVERT(n.manganh USING utf8mb4) COLLATE utf8mb4_unicode_ci = "
            + "CONVERT(nv.nv_manganh USING utf8mb4) COLLATE utf8mb4_unicode_ci "
            + "LEFT JOIN xt_tohop_monthi th ON "
            + "CONVERT(th.matohop USING utf8mb4) COLLATE utf8mb4_unicode_ci = "
            + "CONVERT(nv.tt_thm USING utf8mb4) COLLATE utf8mb4_unicode_ci "
            + "WHERE CONVERT(nv.nn_cccd USING utf8mb4) COLLATE utf8mb4_unicode_ci = "
            + "CONVERT(? USING utf8mb4) COLLATE utf8mb4_unicode_ci "
            + "ORDER BY nv.nv_tt ASC";
        return jdbcTemplate.query(sql, new NguyenVongRowMapper(), cccd);
    }

    private static class NguyenVongRowMapper implements RowMapper<NguyenVongResult> {
        @Override
        public NguyenVongResult mapRow(ResultSet rs, int rowNum) throws SQLException {
            NguyenVongResult result = new NguyenVongResult();
            result.setId(rs.getInt("idnv"));
            result.setCccd(rs.getString("nn_cccd"));
            result.setMaNganh(rs.getString("nv_manganh"));
            result.setThuTu(rs.getInt("nv_tt"));
            result.setDiemThxt(rs.getBigDecimal("diem_thxt"));
            result.setDiemUtqd(rs.getBigDecimal("diem_utqd"));
            result.setDiemCong(rs.getBigDecimal("diem_cong"));
            result.setDiemXetTuyen(rs.getBigDecimal("diem_xettuyen"));
            result.setKetQua(rs.getString("nv_ketqua"));
            result.setPhuongThuc(rs.getString("tt_phuongthuc"));
            result.setToHop(rs.getString("tt_thm"));
            result.setTenNganh(rs.getString("tennganh"));
            result.setMon1(rs.getString("mon1"));
            result.setMon2(rs.getString("mon2"));
            result.setMon3(rs.getString("mon3"));
            result.setTenToHop(rs.getString("tentohop"));
            return result;
        }
    }
}
