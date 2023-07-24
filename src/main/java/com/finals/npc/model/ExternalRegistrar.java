package com.finals.npc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ExternalRegistrar {
    @Id
    private int Id;
    private String username;
    private String password;
}
