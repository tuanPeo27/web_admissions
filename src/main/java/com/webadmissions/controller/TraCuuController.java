package com.webadmissions.controller;

import com.webadmissions.model.NguyenVongResult;
import com.webadmissions.service.NguyenVongService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TraCuuController {
    private final NguyenVongService nguyenVongService;

    public TraCuuController(NguyenVongService nguyenVongService) {
        this.nguyenVongService = nguyenVongService;
    }

    @GetMapping("/tra-cuu/phuong-thuc")
    public String traCuuPhuongThucForm() {
        return "tracuuphuongthuc";
    }

    @PostMapping("/tra-cuu/phuong-thuc")
    public String traCuuPhuongThuc(@RequestParam("cccd") String cccd, Model model) {
        if (cccd == null || cccd.isBlank()) {
            model.addAttribute("error", "Vui long nhap CCCD.");
            return "tracuuphuongthuc";
        }

        List<NguyenVongResult> results = nguyenVongService.findByCccd(cccd);
        model.addAttribute("cccd", cccd.trim());
        model.addAttribute("results", results);
        return "tracuuphuongthuc";
    }

    @GetMapping("/tra-cuu/to-hop")
    public String traCuuToHopForm() {
        return "tracuutohop";
    }

    @PostMapping("/tra-cuu/to-hop")
    public String traCuuToHop(@RequestParam("cccd") String cccd, Model model) {
        if (cccd == null || cccd.isBlank()) {
            model.addAttribute("error", "Vui long nhap CCCD.");
            return "tracuutohop";
        }

        List<NguyenVongResult> results = nguyenVongService.findByCccd(cccd);
        model.addAttribute("cccd", cccd.trim());
        model.addAttribute("results", results);
        return "tracuutohop";
    }
}
