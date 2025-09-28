package com.payment.upi.service;

import com.payment.upi.model.User;
import com.payment.upi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;

@Service
public class RegisterService {

    @Autowired
    UserRepository userRepository;


    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomAccountId() {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(ALPHANUMERIC.length());
            sb.append(ALPHANUMERIC.charAt(index));
        }
        return sb.toString();
    }

    public void saveUserDetails(User user) {
        user.setAccountId(generateRandomAccountId());
        user.setBalance(BigDecimal.valueOf(0));
        user.setRoles("CUSTOMER");
        System.out.println(user);
        userRepository.save(user);
    }
}
