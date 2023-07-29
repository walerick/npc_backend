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
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public void addNewUser(Users user) {
        Optional<Users> userByNin =  userRepo.findUsersByNin(user.getNin());
        if(userByNin.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "NIN already exists");
        }
        userRepo.save(user);
    }

    public List<Users> getUser(){
       return userRepo.findAll();
    }

    public Optional<Users> getUsersByNin(String nin) {
        return userRepo.findUsersByNin(nin);
    }
    public Optional<Users> getUserByBirthid(String birthid){
        return userRepo.findUsersByBirthid(birthid);
    }

    public Optional<Users> getUserByAttestid(String attestid) {
        return userRepo.findUsersByAttestid(attestid);
    }

    public Users saveUser(Users user) {
        return userRepo.save(user);
    }

    public Users updateBirthDetails(String nin,
                                    String childname,
                                    Date birthdate,
                                    String gender,
                                    String placeofbirth,
                                    String fathername,
                                    String mothername,
                                    String birthid,
                                    String birthstatus

    ) {
        Optional<Users> userOptional = userRepo.findUsersByNin(nin);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            // Update the birth details
            user.setChildname(childname);
            user.setBirthdate(birthdate);
            user.setGender(gender);
            user.setPlaceofbirth(placeofbirth);
            user.setFathername(fathername);
            user.setMothername(mothername);
            user.setBirthid(birthid);
            user.setBirthstatus(birthstatus);
            return userRepo.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public Users updateDeathDetails(String nin,
                                    String deathname,
                                    Date dateofdeath,
                                    String deathgender,
                                    String placeofdeath,
                                    String deathid,
                                    String deathstatus

    ) {
        Optional<Users> userOptional = userRepo.findUsersByNin(nin);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            // Update the death details
            user.setDeathname(deathname);
            user.setDateatdeath(dateofdeath);
            user.setDeathgender(deathgender);
            user.setPlaceofdeath(placeofdeath);
            user.setDeathid(deathid);
            user.setDeathstatus(deathstatus);
            return userRepo.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public Users updateAttestDetails(String nin, String attestname, int attestage, Date attestdate, String attestlg, String attestid, String attestationstatus, String attestationbystaffstatus) {
        Optional<Users> usersOptional = userRepo.findUsersByNin(nin);
        if (usersOptional.isPresent()){
            Users user = usersOptional.get();
            //Update the attest details
            user.setAttestname(attestname);
            user.setAttestage(attestage);
            user.setAttestdate(attestdate);
            user.setAttestlg(attestlg);
            user.setAttestid(attestid);
            user.setAttestationstatus(attestationstatus);
            user.setAttestbystaffstatus(attestationbystaffstatus);
            return userRepo.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
    @Transactional
    public void updateUserBirthStatus(String nin, String status){
        Optional<Users>  user = getUsersByNin(nin);
        Users users = userRepo.findById(user.get().getUser_id())
                .orElseThrow(() -> new IllegalStateException("user not found"));
        users.setBirthstatus(status);
    }

    @Transactional
    public void updateUserDeathStatus(String nin, String status){
        Optional<Users>  user = getUsersByNin(nin);
        Users users = userRepo.findById(user.get().getUser_id())
                .orElseThrow(() -> new IllegalStateException("user not found"));
        users.setDeathstatus(status);
    }

    @Transactional
    public void updateUserAttestStatus(String nin, String status){
        Optional<Users>  user = getUsersByNin(nin);
        Users users = userRepo.findById(user.get().getUser_id())
                .orElseThrow(() -> new IllegalStateException("user not found"));
        users.setAttestationstatus(status);
    }

    @Transactional
    public void updateAttestByStaffStatus(String nin, String status){
        Optional<Users>  user = getUsersByNin(nin);
        Users users = userRepo.findById(user.get().getUser_id())
                .orElseThrow(() -> new IllegalStateException("user not found"));
        users.setAttestbystaffstatus(status);
    }


    public boolean checkIfNinExists(String nin) {
        Optional<Users> existingUser = userRepo.findUsersByNin(nin);

        // If existingUser is not null, it means a user with the provided NIN already exists
        return existingUser.isPresent();
    }
}
