package com.finals.npc.controller.usersController;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AttestRegistrationRequest {
    private String attestname;
    private int attestage;
    private Date attestdate;
    private String attestlg;
    private String attestid;
}
