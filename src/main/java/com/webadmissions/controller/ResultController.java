package com.webadmissions.controller;

import com.webadmissions.model.NguyenVongResult;
import com.webadmissions.model.ThisSinh;
import com.webadmissions.service.NguyenVongService;
import com.webadmissions.service.ThisSinhService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ResultController {
    private final NguyenVongService nguyenVongService;
    private final ThisSinhService thisSinhService;

    public ResultController(NguyenVongService nguyenVongService, ThisSinhService thisSinhService) {
        this.nguyenVongService = nguyenVongService;
        this.thisSinhService = thisSinhService;
    }

    @GetMapping({"/", "/ket-qua"})
    public String ketQua(HttpSession session, Model model) {
        String cccd = (String) session.getAttribute("userCccd");
        if (cccd == null || cccd.isBlank()) {
            return "redirect:/login";
        }

        ThisSinh thisSinh = thisSinhService.findByCccd(cccd).orElse(null);
        List<NguyenVongResult> results = nguyenVongService.findByCccd(cccd);
        model.addAttribute("thisSinh", thisSinh);
        model.addAttribute("results", results);

        Object loginSuccess = session.getAttribute("loginSuccess");
        if (Boolean.TRUE.equals(loginSuccess)) {
            model.addAttribute("loginSuccess", true);
            session.removeAttribute("loginSuccess");
        }

        return "ketqua";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
