package com.payment.upi.repository;

import com.payment.upi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByAccountId(String accountId);
//    int updateBalanceByUsername(@Param("balance") BigDecimal balance, @Param("username") String username);
}
