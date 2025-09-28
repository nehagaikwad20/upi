package com.payment.upi.controller;

import com.payment.upi.model.User;
import com.payment.upi.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @RequestMapping("/register")
    public String displayRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register.html";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        registerService.saveUserDetails(user);
        model.addAttribute("message", "User successfully created");
        return "login";
    }
}
