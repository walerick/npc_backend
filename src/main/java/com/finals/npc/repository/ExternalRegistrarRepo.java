package com.finals.npc.repository;

import com.finals.npc.model.ExternalRegistrar;
import com.finals.npc.model.Registrar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExternalRegistrarRepo extends JpaRepository<ExternalRegistrar, Integer> {

    Optional<ExternalRegistrar> findRegistrarById(long id);

    Optional<ExternalRegistrar> findRegistrarByUsername(String username);
}
