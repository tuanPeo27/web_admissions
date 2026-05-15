package com.webadmissions.service;

import com.webadmissions.model.NguyenVongResult;
import com.webadmissions.repository.NguyenVongRepository;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NguyenVongService {
    private final NguyenVongRepository nguyenVongRepository;

    public NguyenVongService(NguyenVongRepository nguyenVongRepository) {
        this.nguyenVongRepository = nguyenVongRepository;
    }

    public List<NguyenVongResult> findByCccd(String cccd) {
        if (cccd == null || cccd.isBlank()) {
            return Collections.emptyList();
        }
        return nguyenVongRepository.findByCccd(cccd.trim());
    }
}
