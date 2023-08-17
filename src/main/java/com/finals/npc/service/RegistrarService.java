package com.finals.npc.service;

import com.finals.npc.model.ExternalRegistrar;
import com.finals.npc.model.RealRegistrar;
import com.finals.npc.model.Registrar;
import com.finals.npc.repository.ExternalRegistrarRepo;
import com.finals.npc.repository.RealRegistrarRepo;
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
    private final RealRegistrarRepo realRegistrarRepo;
    public void addNewRegistrar(Registrar registrar){
        Optional<Registrar> registrarByUsername = registrarRepository.findRegistrarByUsername(registrar.getUsername());
        if (registrarByUsername.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account already exist.");
        }
        registrarRepository.save(registrar);
    }

    public Optional<Registrar> getRegistrarByUsername (String username){
        return registrarRepository.findRegistrarByUsername(username);
    }

    public Optional<RealRegistrar> getRalRegistrarByUsername (String username){
        return realRegistrarRepo.findRegistrarByUsername(username);
    }


//    REAL REGISTRAR REGISTRATION SERVICE
    public void addNewRealRegistrar(RealRegistrar registrar){
        Optional<RealRegistrar> registrarByUsername = realRegistrarRepo.findRegistrarByUsername(registrar.getUsername());
        if (registrarByUsername.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account already exist.");
        }
        realRegistrarRepo.save(registrar);
    }

        public Optional<RealRegistrar> getRealRegistrarByUsername (String username){
            return realRegistrarRepo.findRegistrarByUsername(username);
        }
}


