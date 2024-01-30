package com.rosendo.paymentSystem.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_STOREKEEPER")
public class StorekeeperModel implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID StorekeeperId;
    private String StorekepperName;
    private String StorekepperCPF;
    private String StorekepperEmail;
    private String StorekepperPassword;



}
