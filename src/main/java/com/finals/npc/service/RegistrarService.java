package com.finals.npc.service;

import com.finals.npc.model.ExternalRegistrar;
import com.finals.npc.model.Registrar;
import com.finals.npc.repository.ExternalRegistrarRepo;
import com.finals.npc.repository.RegistrarRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.Optional;

@Data
@AllArgsConstructor
@Service
public class RegistrarService {
    private final RegistrarRepository registrarRepository;
    public void addNewRegistrar(Registrar registrar){
        Optional<Registrar> registrarById = registrarRepository.findRegistrarById(registrar.getId());
        if (registrarById.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account already exist.");
        }
        registrarRepository.save(registrar);
    }

    public Optional<Registrar> getRegistrarByUsername (String username){
        return registrarRepository.findRegistrarByUsername(username);
    }
}


