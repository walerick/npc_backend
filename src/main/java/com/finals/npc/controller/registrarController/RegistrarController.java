package com.finals.npc.controller.registrarController;

import com.finals.npc.model.Registrar;
import com.finals.npc.model.Users;
import com.finals.npc.repository.UserRepo;
import com.finals.npc.service.BasicResponse;
import com.finals.npc.service.RegistrarService;
import com.finals.npc.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/registrar")
@AllArgsConstructor
public class RegistrarController {
    private final RegistrarService registrarService;
    private final UserService userService;
    private final UserRepo userRepo;

    @PostMapping("/register")
    public void addNewRegistrar(@RequestBody Registrar registrar) {
        registrarService.addNewRegistrar(registrar);
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

    @GetMapping("/view-birth")
    public List<Users> viewBirth() {
        List<Users> users = userService.getUser();
        Stream<Users> usersStream =  users.stream().filter(x-> x.getBirthid() != null);
        return usersStream.toList();
    }

    @PutMapping("/view-birth/approve/{nin}")
    public ResponseEntity<BasicResponse> approveBirth(@PathVariable("nin") String nin){
        userService.updateUserBirthStatus(nin, "approved");
        return ResponseEntity.ok(new BasicResponse("Success"));
    }

    @PutMapping("/view-birth/decline/{nin}")
    public ResponseEntity<BasicResponse> declineBirth(@PathVariable("nin") String nin){
        userService.updateUserBirthStatus(nin, "declined");
        return ResponseEntity.ok(new BasicResponse("Success"));
    }



}


