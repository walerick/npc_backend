package com.finals.npc.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Registrar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column
    private String username;
    @Column
    private String password;
}
