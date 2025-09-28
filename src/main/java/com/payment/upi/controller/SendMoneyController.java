package com.payment.upi.controller;

import com.payment.upi.model.User;
import com.payment.upi.repository.UserRepository;
import com.payment.upi.service.SendMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class SendMoneyController {

    @Autowired
    SendMoneyService sendMoneyService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/findaccountid")
    public String findAccount(@ModelAttribute("userAccount") User user, Model model) {
        User finduser = sendMoneyService.findUserByAccountId(user.getAccountId());
        System.out.println("controller: " + finduser);
        if(finduser == null) {
            model.addAttribute("message", "User not found");
        }
        model.addAttribute("beneficiary", finduser);
        return "send-money";
    }

    @RequestMapping("/sendMoneyToOtherAccount")
    public String sendMoneyToAccount(@RequestParam BigDecimal amount, @RequestParam String username, Model model, Authentication authentication) {
        System.out.println("amount: " + amount);
        int res = sendMoneyService.updateBalanceByUsername(amount, username, authentication.getName());
        System.out.println("res: " + res);
        if (res > 0) {
            model.addAttribute("MoneySentMessage", "Money sent successfully");
            model.addAttribute("userAccount", new User());
            return "send-money";
        }
        model.addAttribute("errormessage", "Something went wrong");
        return "send-money";
    }
}
