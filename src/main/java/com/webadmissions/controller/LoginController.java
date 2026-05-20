package com.webadmissions.controller;

import com.webadmissions.model.User;
import com.webadmissions.service.AuthService;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {
        Optional<User> userOpt = authService.authenticate(username, password);
        if (userOpt.isEmpty()) {
            model.addAttribute("error", "Sai CCCD hoặc mật khẩu.");
            return "login";
        }

        User user = userOpt.get();
        String role = user.getRole();
        if (role != null && role.trim().equalsIgnoreCase("admin")) {
            model.addAttribute("error", "Bạn là quản trị viên, vui lòng đăng nhập ở nơi khác.");
            return "login";
        }
        
        if(user.getStatus() == false) {
            model.addAttribute("error", "Tài khoản của bạn đã bị vô hiệu hóa. Vui lòng liên hệ quản trị viên.");
            return "login";
        }

        session.setAttribute("userCccd", user.getUsername().trim());
        session.setAttribute("loginSuccess", Boolean.TRUE);
        return "redirect:/ket-qua";
    }
}
