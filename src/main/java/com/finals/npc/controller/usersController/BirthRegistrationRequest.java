package com.finals.npc.controller.usersController;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class BirthRegistrationRequest {
    private String childName;
    private Date birthDate;
    private String gender;
    private String placeOfBirth;
    private String fatherName;
    private String motherName;
    private String birthId;
}
