package com.finals.npc.controller.registrarController;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor

public class AttestationRequest {

    private int attest_id;

    private String attest_name;

    private int attest_age;

    private Date attest_date;

    private String attest_lg;
}
