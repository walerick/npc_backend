package com.finals.npc.service;

import com.finals.npc.model.Users;
import com.finals.npc.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepository;
//REGISTER NEW USER SERVICE
    public void addNewUser(Users user) {
        Optional<Users> userByNin = userRepository.findUsersByNin(user.getNin());
        if (userByNin.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "NIN already exists");
        }
        userRepository.save(user);
        throw new ResponseStatusException(HttpStatus.CREATED, "Account Successfully Created.");
    }


//GET ALL USERS SERVICE
    public List<Users> getUser() {
        return userRepository.findAll();
    }

//    GET USER BY NIN
    public Optional<Users> getUsersByNin(String nin) {
        return userRepository.findUsersByNin(nin);
    }

// GET USER BY BIRTH-ID
    public Optional<Users> getUserByBirthId(String birthId) {
        return userRepository.findUsersByBirthId(birthId);
    }

//    GET USER BY ATTEST-ID
    public Optional<Users> getUserByAttestId(String attestId) {
        return userRepository.findUsersByAttestId(attestId);
    }


    public Users updateBirthDetails(String nin,
                                    String childName,
                                    Date birthDate,
                                    String gender,
                                    String placeOfBirth,
                                    String stateOfOrigin,
                                    String fatherName,
                                    String motherName,
                                    String birthId,
                                    String birthStatus

    ) {
        Optional<Users> userOptional = userRepository.findUsersByNin(nin);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            // Update the birth details
            user.setChildName(childName);
            user.setBirthDate(birthDate);
            user.setGender(gender);
            user.setPlaceOfBirth(placeOfBirth);
            user.setStateOfOrigin(stateOfOrigin);
            user.setFatherName(fatherName);
            user.setMotherName(motherName);
            user.setBirthId(birthId);
            user.setBirthStatus(birthStatus);
            return userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public Users updateDeathDetails(String nin,
                                    String deathName,
                                    Date dateAtDeath,
                                    String deathGender,
                                    String placeOfDeath,
                                    String stateOfOrigin,
                                    String deathFather,
                                    String deathMother,
                                    String deathId,
                                    String deathStatus

    ) {
        Optional<Users> userOptional = userRepository.findUsersByNin(nin);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            // Update the death details
            user.setDeathName(deathName);
            user.setDateAtDeath(dateAtDeath);
            user.setDeathGender(deathGender);
            user.setPlaceOfDeath(placeOfDeath);
            user.setStateOfOrigin(stateOfOrigin);
            user.setDeathFather(deathFather);
            user.setDeathMother(deathMother);
            user.setDeathId(deathId);
            user.setDeathStatus(deathStatus);
            return userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public Users updateAttestDetails(String nin,
                                     String attestName,
                                     Date attestDate,
                                     String attestLg,
                                     String attestId,
                                     String attestationStatus,
                                     String attestationByStaffStatus) {
        Optional<Users> usersOptional = userRepository.findUsersByNin(nin);
        if (usersOptional.isPresent()) {
            Users user = usersOptional.get();
            //Update the attest details
            user.setAttestName(attestName);
//            user.setAttestAge(attestAge);
            user.setAttestDate(attestDate);
            user.setAttestLg(attestLg);
            user.setAttestId(attestId);
            user.setAttestationStatus(attestationStatus);
            user.setAttestByStaffStatus(attestationByStaffStatus);
            return userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @Transactional
    public void updateUserBirthStatus(String nin, String status) {
        Optional<Users> usersOptional = getUsersByNin(nin);
        Users users = userRepository.findById(usersOptional.get().getUser_id())
                .orElseThrow(() -> new IllegalStateException("user not found"));
        users.setBirthStatus(status);
    }

    @Transactional
    public void updateUserDeathStatus(String nin, String status) {
        Optional<Users> user = getUsersByNin(nin);
        Users users = userRepository.findById(user.get().getUser_id())
                .orElseThrow(() -> new IllegalStateException("user not found"));
        users.setDeathStatus(status);
    }

    @Transactional
    public void updateUserAttestStatus(String nin, String status) {
        Optional<Users> user = getUsersByNin(nin);
        Users users = userRepository.findById(user.get().getUser_id())
                .orElseThrow(() -> new IllegalStateException("user not found"));
        users.setAttestationStatus(status);
    }

    @Transactional
    public void updateAttestByStaffStatus(String nin, String status) {
        Optional<Users> user = getUsersByNin(nin);
        Users users = userRepository.findById(user.get().getUser_id())
                .orElseThrow(() -> new IllegalStateException("user not found"));
        users.setAttestByStaffStatus(status);
    }


    public boolean checkIfNinExists(String nin) {
        Optional<Users> existingUser = userRepository.findUsersByNin(nin);

        // If existing User is not null, it means a user with the provided NIN already exists
        return existingUser.isPresent();
    }
}
