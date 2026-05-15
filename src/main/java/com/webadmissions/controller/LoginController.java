package com.webadmissions.controller;

import com.webadmissions.service.AuthService;
import jakarta.servlet.http.HttpSession;
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
        if (authService.isValidLogin(username, password)) {
            session.setAttribute("userCccd", username.trim());
            session.setAttribute("loginSuccess", Boolean.TRUE);
            return "redirect:/ket-qua";
        }

        model.addAttribute("error", "Sai CCCD hoac mat khau.");
        return "login";
    }
}
