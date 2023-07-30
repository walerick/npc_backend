package com.finals.npc.repository;
import com.finals.npc.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    Optional<Users> findUsersByNin(String nin) ;
    Optional<Users> findUsersByBirthId(String birthId);
    Optional<Users> findUsersByAttestId(String attestId);
}
