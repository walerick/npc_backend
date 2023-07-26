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
    private String birthstatus;
    @Column
    private String deathstatus;
    @Column
    private String attestationstatus;
    @Column
    private String attestbystaffstatus;





    @Column
    private String childname;

    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
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

//    Death registration

    @Column
    private String deathname;
    @Column
    private String deathgender;
    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateatdeath;
    @Column
    private String placeofdeath;
    @Column
    private String deathid;



//    Attestation
    @Column
    private String attestid;
    @Column
    private String attestname;
    @Column
    private int attestage;
    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date attestdate;
    @Column
    private String attestlg;
}
