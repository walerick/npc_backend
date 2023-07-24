package com.finals.npc.controller.usersController;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.AccessType;

import java.util.Date;
@Data
@AllArgsConstructor
public class BirthRegistrationRequest {
    private String childname;
    private Date birthdate;
    private String gender;
    private String placeofbirth;
    private String fathername;
    private String mothername;
    private String birthid;
}
