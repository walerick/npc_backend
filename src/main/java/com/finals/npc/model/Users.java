package com.finals.npc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String childName;
    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;

    
    
//    BIRTH REGISTRATION
    @Column
    private String gender;
    @Column
    private String placeOfBirth;
    @Column
    private String fatherName;
    @Column
    private String motherName;
    @Column
    private String birthId;
    @Column
    private String birthStatus;
    

//    Death registration

    @Column
    private String deathName;
    @Column
    private String deathGender;
    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateAtDeath;
    @Column
    private String placeOfDeath;
    @Column
    private String deathId;
    @Column
    private String deathStatus;



//    Attestation
    @Column
    private String attestId;
    @Column
    private String attestName;
    @Column
    private int attestAge;
    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date attestDate;
    @Column
    private String attestLg;
    @Column
    private String attestationStatus;
    @Column
    private String attestByStaffStatus;
}
