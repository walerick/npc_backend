package com.finals.npc.controller.usersController;

import lombok.Data;

@Data

public class LoginRequest {
    private String nin;
    private String password;
}
