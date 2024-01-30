package com.rosendo.paymentSystem.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_USER")
public class UserModel implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID UserId;
    private String UserName;
    private String UserCPF;
    private String UserEmail;
    private String UserPassword;

}
