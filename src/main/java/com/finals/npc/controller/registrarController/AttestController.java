package com.finals.npc.controller.registrarController;

import com.finals.npc.model.Users;
import com.finals.npc.service.RandomNumberGenerator;
import com.finals.npc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/attest")
public class AttestController {
    private final UserService userService;

    @GetMapping("/attestid")
    public ResponseEntity<Map<String,Object>> getAttestLetter(
            @RequestParam String attest_id) {
        Optional<Users> userOptional = userService.getUserByAttestId(attest_id);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            Map<String, Object> attestDetails = new HashMap<>();
            attestDetails.put("attestId", user.getAttestId());
            attestDetails.put("attestName", user.getAttestName());
//            attestDetails.put("attestAge", user.getAttestAge());
            attestDetails.put("attestDate", user.getAttestDate());
            attestDetails.put("attestLg", user.getAttestLg());
            return ResponseEntity.ok(attestDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
