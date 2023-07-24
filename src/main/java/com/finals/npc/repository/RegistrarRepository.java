package com.finals.npc.repository;

import com.finals.npc.model.Registrar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrarRepository extends JpaRepository<Registrar, Long> {
    Optional<Registrar> findRegistrarById(long Id);
    Optional<Registrar> findRegistrarByUsername(String username);
}
