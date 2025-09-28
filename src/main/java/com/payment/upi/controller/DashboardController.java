package com.payment.upi.controller;

import com.payment.upi.model.User;
import com.payment.upi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {
    @Autowired
    UserRepository registerRepository;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        return "dashboard.html";
    }

    @GetMapping("/send")
    public String sendMoneyForm(Model model) {
        model.addAttribute("userAccount", new User());
        // load user list, etc.
        return "send-money";
    }

    @GetMapping("/transactions")
    public String viewTransactions(Model model) {
        return "transactions";
    }

    @RequestMapping("/account")
    public String displayAccountDetails(Authentication authentication, Model model) {
        model.addAttribute("user", registerRepository.findByUsername(authentication.getName()));
        return "account";
    }
}
