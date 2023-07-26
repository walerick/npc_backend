package com.finals.npc.service;

import com.finals.npc.model.ExternalRegistrar;
import com.finals.npc.repository.ExternalRegistrarRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Data
@AllArgsConstructor
@Service
public class ExternalRegistrarService {
    private final ExternalRegistrarRepo externalRegistrarRepository;

    public void addNewExternalRegistrar(ExternalRegistrar registrar) {
        Optional<ExternalRegistrar> registrarById = externalRegistrarRepository.findRegistrarById(registrar.getId());
        if (registrarById.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account already exist.");
        }
        externalRegistrarRepository.save(registrar);
    }

    public Optional<ExternalRegistrar> getRegistrarByUsername(String username) {
        return externalRegistrarRepository.findRegistrarByUsername(username);
    }
}
