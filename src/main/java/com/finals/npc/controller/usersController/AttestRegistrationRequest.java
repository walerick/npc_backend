package com.finals.npc.controller.usersController;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AttestRegistrationRequest {
    private String attestName;
    private int attestAge;
    private Date attestDate;
    private String attestLg;
    private String attestId;
}
