package com.finals.npc.repository;

import com.finals.npc.model.ExternalRegistrar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalRegistrarRepo extends JpaRepository<ExternalRegistrar, Integer> {
    
}
