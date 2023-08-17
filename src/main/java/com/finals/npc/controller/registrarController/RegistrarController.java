package com.finals.npc.controller.registrarController;

import com.finals.npc.model.RealRegistrar;
import com.finals.npc.model.Registrar;
import com.finals.npc.model.Users;
import com.finals.npc.repository.UserRepo;
import com.finals.npc.service.BasicResponse;
import com.finals.npc.service.RegistrarService;
import com.finals.npc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

//CONTROLLER FOR NPC STAFF
@CrossOrigin
@RestController
@RequestMapping("/api/v1/registrar")
@AllArgsConstructor
public class RegistrarController {
    private final RegistrarService registrarService;
    private final UserService userService;
    private final UserRepo userRepo;

//    REGISTER STAFF/VISITOR
    @PostMapping("/register")
    public void addNewRegistrar(@RequestBody Registrar registrar) {

        registrarService.addNewRegistrar(registrar);
    }

    @PostMapping("/register-registrar")
    public void addNewRealRegistrar(@RequestBody RealRegistrar registrar){
        registrarService.addNewRealRegistrar(registrar);
    }

    @PostMapping("/login")
    public ResponseEntity<RegistrarResponse> login(@RequestBody RegistrarRequest registrarRequest) {
        String username = registrarRequest.getUsername();
        String password = registrarRequest.getPassword();

        Optional<Registrar> registrars = registrarService.getRegistrarByUsername(username);
        if (registrars.isPresent()) {
            Registrar registrar = registrars.get();
            if (registrar.getPassword().equals(password)) {
                RegistrarResponse registrarResponse = new RegistrarResponse(registrar.getUsername(),
                        registrar.getPassword());
                System.out.println(registrarResponse);
                return ResponseEntity.ok(registrarResponse);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

//    REAL REGISTRAR LOGIN
@PostMapping("/registrar-login")
public ResponseEntity<RegistrarResponse> realRegisterLogin(@RequestBody RegistrarRequest registrarRequest) {
    String username = registrarRequest.getUsername();
    String password = registrarRequest.getPassword();

    Optional<RealRegistrar> registrars = registrarService.getRealRegistrarByUsername(username);
    if (registrars.isPresent()) {
        RealRegistrar registrar = registrars.get();
        if (registrar.getPassword().equals(password)) {
            RegistrarResponse registrarResponse = new RegistrarResponse(registrar.getUsername(),
                    registrar.getPassword());
            System.out.println(registrarResponse);
            return ResponseEntity.ok(registrarResponse);
        }
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
}

//    VIEW BIRTH REG REQUEST BY STAFF
    @GetMapping("/view-birth")
    public List<Users> viewBirth() {
        List<Users> users = userService.getUser();
        Stream<Users> usersStream =  users.stream().filter(x-> x.getBirthId() != null);
        return usersStream.toList();
    }

//    APPROVE BIRTH REG REQUEST BY STAFF
    @PutMapping("/view-birth/approve/{nin}")
    public ResponseEntity<BasicResponse> approveBirth(@PathVariable("nin") String nin){
        userService.updateUserBirthStatus(nin, "approved");
        return ResponseEntity.ok(new BasicResponse("Success"));
    }

    //    APPROVE BIRTH REG REQUEST BY STAFF
    @PutMapping("/view-birth/decline/{nin}")
    public ResponseEntity<BasicResponse> declineBirth(@PathVariable("nin") String nin){
        userService.updateUserBirthStatus(nin, "declined");
        return ResponseEntity.ok(new BasicResponse("Success"));
    }

//   VIEW ATTESTATION FROM STAFF
    @GetMapping("/view-attest")
    public List<Users> viewAgeAttest() {
        List<Users> users = userService.getUser();
        Stream<Users> usersStream =  users.stream().filter(x-> x.getAttestId() != null);
        return usersStream.toList();
    }

// APPROVE ATTESTATION BY STAFF
    @PutMapping("/view-attest/approve/{nin}")
    public ResponseEntity<BasicResponse> approveAgeAttest(@PathVariable("nin") String nin){
        userService.updateAttestByStaffStatus(nin, "approved");
        return ResponseEntity.ok(new BasicResponse("Success"));
    }

// DECLINE ATTESTATION BY STAFF
    @PutMapping("/view-attest/decline/{nin}")
    public ResponseEntity<BasicResponse> declineAgeAttest(@PathVariable("nin") String nin){
        userService.updateAttestByStaffStatus(nin, "declined");
        return ResponseEntity.ok(new BasicResponse("Success"));
    }






    @GetMapping("/view-death")
    public List<Users> viewDeath() {
        List<Users> users = userService.getUser();
        Stream<Users> usersStream =  users.stream().filter(x-> x.getDeathId() != null);
        return usersStream.toList();
    }

    @PutMapping("/view-death/approve/{nin}")
    public ResponseEntity<BasicResponse> approveDeath(@PathVariable("nin") String nin){
        userService.updateUserDeathStatus(nin, "approved");
        return ResponseEntity.ok(new BasicResponse("Success"));
    }

    @PutMapping("/view-death/decline/{nin}")
    public ResponseEntity<BasicResponse> declineDeath(@PathVariable("nin") String nin){
        userService.updateUserBirthStatus(nin, "declined");
        return ResponseEntity.ok(new BasicResponse("Success"));
    }









}


