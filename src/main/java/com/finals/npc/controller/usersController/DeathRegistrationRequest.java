package com.finals.npc.controller.usersController;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DeathRegistrationRequest {
    private String deathname;
    private String deathgender;
    private Date dateofdeath;
    private String placeofdeath;
    private String deathid;
}
