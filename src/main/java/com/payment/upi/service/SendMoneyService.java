package com.payment.upi.service;

import com.payment.upi.model.User;
import com.payment.upi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Service
public class SendMoneyService {

    @Autowired
    UserRepository userRepository;

    public User findUserByAccountId(String accountId) {
        User findUser = userRepository.findByAccountId(accountId);
        if(findUser != null && findUser.getId() > 0)
            return findUser;
        return null;
    }

    public int updateBalanceByUsername(BigDecimal balance, String toAddUsername, String toSubusername) {
        User addBalanceUser = userRepository.findByUsername(toAddUsername);
        User subBalanceUser = userRepository.findByUsername(toSubusername);
        System.out.println(addBalanceUser + "/nand ..." + subBalanceUser);
        System.out.println("balance: " + addBalanceUser.getBalance().add(balance));
        if(addBalanceUser != null && addBalanceUser.getId() > 0
                && subBalanceUser != null && subBalanceUser.getId() > 0) {
            addBalanceUser.setBalance(addBalanceUser.getBalance().add(balance));
            subBalanceUser.setBalance(subBalanceUser.getBalance().subtract(balance));
            userRepository.save(addBalanceUser);
            userRepository.save(subBalanceUser);
            return 1;
        }
        return -1;
    }
}
