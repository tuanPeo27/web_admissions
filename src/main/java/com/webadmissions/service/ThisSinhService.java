package com.webadmissions.service;

import com.webadmissions.model.ThisSinh;
import com.webadmissions.repository.ThisSinhRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ThisSinhService {
    private final ThisSinhRepository thisSinhRepository;

    public ThisSinhService(ThisSinhRepository thisSinhRepository) {
        this.thisSinhRepository = thisSinhRepository;
    }

    public Optional<ThisSinh> findByCccd(String cccd) {
        if (cccd == null || cccd.isBlank()) {
            return Optional.empty();
        }
        return thisSinhRepository.findByCccd(cccd.trim());
    }
}
