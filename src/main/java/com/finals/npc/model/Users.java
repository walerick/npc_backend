package com.finals.npc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    @NotNull
    private String name;
    @NotNull
    @Column(unique = true)
    private String nin;
    @Column
    private String password;

    @Column
    private String childname;

    @Column
    private Date birthdate;

    @Column
    private String gender;

    @Column
    private String placeofbirth;

    @Column
    private String fathername;

    @Column
    private String mothername;
    @Column
    private String birthid;

//    Attestation
    @Column
    private String attest_id;
    @Column
    private String attest_name;
    @Column
    private int attest_age;
    @Column
    private Date attest_date;
    @Column
    private String attest_lg;
}
