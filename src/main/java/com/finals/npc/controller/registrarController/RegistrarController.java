package com.finals.npc.controller.registrarController;

import com.finals.npc.model.Registrar;
import com.finals.npc.service.RegistrarService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/registrar")
@AllArgsConstructor
public class RegistrarController {
    private final RegistrarService registrarService;

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

}


