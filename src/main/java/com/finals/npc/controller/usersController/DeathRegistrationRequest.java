package com.finals.npc.controller.usersController;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DeathRegistrationRequest {
    private String deathName;
    private String deathGender;
    private Date dateAtDeath;
    private String placeOfDeath;
    private String stateOfOrigin;
    private String deathFather;
    private String deathMother;
    private String deathId;
}

