package com.finals.npc.controller.registrarController;

import com.finals.npc.controller.usersController.BirthRegistrationRequest;
import com.finals.npc.model.Users;
import com.finals.npc.service.RandomNumberGenerator;
import com.finals.npc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/attest")
public class AttestController {
    private final UserService userService;
    private final RandomNumberGenerator randomNumberGenerator;
//
//    @PutMapping("/{nin}")
//    public ResponseEntity<Users> updateAttestDetail(
//            @PathVariable("nin") String nin,
//            @RequestBody AttestationRequest request
//    ){
//        String attestName = request.getAttest_name();
//        Date attestDate = request.getAttest_date();
//        int age = request.getAttest_age();
//        String attestLg = request.getAttest_lg();
//
//        // Generate random birth_id
//        String attestId = randomNumberGenerator.generateRandomNumber(3);
//        System.out.println(attestId);
//
//
//
//        Users updatedUser = userService.updateAttestDetails(nin, attestName, age, attestDate, attestLg, attestId);
//
//        updatedUser.setBirthid(attestId);
//
//        return ResponseEntity.ok(updatedUser);
//    }

    @GetMapping("/attestid")
    public ResponseEntity<Map<String,Object>> getAttestLetter(
            @RequestParam String attest_id) {
        Optional<Users> userOptional = userService.getUserByAttestid(attest_id);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            Map<String, Object> attestDetails = new HashMap<>();
            attestDetails.put("attestId", user.getAttestid());
            attestDetails.put("attestName", user.getAttestname());
            attestDetails.put("attestAge", user.getAttestage());
            attestDetails.put("attestDate", user.getAttestdate());
            attestDetails.put("attestLg", user.getAttestlg());
            return ResponseEntity.ok(attestDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
