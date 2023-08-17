package com.finals.npc.repository;

import com.finals.npc.model.RealRegistrar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RealRegistrarRepo extends JpaRepository<RealRegistrar, Integer> {
    Optional<RealRegistrar> findRegistrarByUsername(String username);
}
