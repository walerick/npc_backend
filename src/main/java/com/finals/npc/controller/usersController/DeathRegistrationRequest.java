package com.finals.npc.controller.usersController;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DeathRegistrationRequest {
    private String deathName;
    private String deathGender;
    private Date dateOfDeath;
    private String placeOfDeath;
    private String deathId;
}
