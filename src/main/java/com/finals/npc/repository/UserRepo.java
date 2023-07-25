package com.finals.npc.repository;

import com.finals.npc.model.Users;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepo extends JpaRepository<Users, Long> {
//    @Query("Select u from npc u where u.nin = ?1")
    Optional<Users> findUsersByNin(String nin) ;
    Optional<Users> findUsersByBirthid(String birthid);

    Optional<Users> findUsersByAttestid(String attestid);
}
