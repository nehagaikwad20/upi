package com.payment.upi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "user_details")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false)
    private Timestamp updatedAt;

    @Column(nullable = false)
    private String roles;

    @Column(name = "account_id", nullable = false, unique = true, length = 10)
    private String accountId;

    @Column(precision = 21, scale = 6)
    private BigDecimal balance;

    @Transient
    private String confirmPassword;

}
