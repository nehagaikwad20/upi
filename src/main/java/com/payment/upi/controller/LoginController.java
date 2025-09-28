package com.payment.upi.controller;

import com.payment.upi.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String displayLoginPage(@RequestParam(value = "logout", required = false) String logout,
                                          @RequestParam(value = "error", required = false) String error,
                                          Model model) {
        String message = null;
        if (logout != null) {
            message = "You have logged out successfully";
            System.out.println(message);
        }
        if (error != null) {
            message = "Username or Password is incorrect";
            System.out.println(message);
        }
        model.addAttribute("message", message);
        return "login.html";
    }

    @RequestMapping("/logout")
    public String displayLogoutPage() {
        return "redirect:/login?logout=true";
    }


}
