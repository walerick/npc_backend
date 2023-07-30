package com.finals.npc.controller;


import com.finals.npc.model.Users;
import com.finals.npc.service.BasicResponse;
import com.finals.npc.service.ExternalRegistrarService;
import com.finals.npc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

// CONTROLLER FOR REGISTRAR
@CrossOrigin
@RestController
@RequestMapping("/api/v1/externalRegistrar")
@AllArgsConstructor
public class ExternalRegistrarController {
    private ExternalRegistrarService externalRegistrarService;
    private final UserService userService;

//    VIEW ATTESTATION BY REGISTRAR
    @GetMapping("/view-attestation")
    public List<Users> viewAttestation() {
        List<Users> users = userService.getUser();
        Stream<Users> usersStream =  users.stream().filter(x-> x.getAttestId() != null);
        return usersStream.toList();
    }

//    APPROVE ATTESTATION BY REGISTRAR
    @PutMapping("/view-attestation/approve/{nin}")
    public ResponseEntity<BasicResponse> approveAttestation(@PathVariable("nin") String nin){
        userService.updateUserAttestStatus(nin, "approved");
        return ResponseEntity.ok(new BasicResponse("Success"));
    }

    //    APPROVE ATTESTATION BY REGISTRAR
    @PutMapping("/view-attestation/decline/{nin}")
    public ResponseEntity<BasicResponse> declineAttestation(@PathVariable("nin") String nin){
        userService.updateUserAttestStatus(nin, "declined");
        return ResponseEntity.ok(new BasicResponse("Success"));
    }

}
