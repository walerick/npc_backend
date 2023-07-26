package com.finals.npc.controller.usersController;

import com.finals.npc.model.Users;
import com.finals.npc.repository.UserRepo;
import com.finals.npc.service.RandomNumberGenerator;
import com.finals.npc.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final RandomNumberGenerator randomNumberGenerator;
    private final UserRepo userRepo;
//    @CrossOrigin
//    @PostMapping("register")
//    public void registerNewUser(@RequestBody Users user){
//        userService.addNewUser(user);
//    }

    @PostMapping("register")
    public ResponseEntity<String> registerNewUser(@RequestBody Users user){
        boolean ninExists = userService.checkIfNinExists(user.getNin());

        if(ninExists){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with the same NIN already exists.");
        }
        userService.addNewUser(user);
        return ResponseEntity.ok("User registered Successfully");
    }


    @GetMapping
    public List<Users> Users(){
       return userService.getUser();
    }

    @GetMapping("get-by-nin/{nin}")
    public Users getUserByNin(@PathVariable String nin) {
        return userRepo.findUsersByNin(nin).orElse(null);
    }

    @PutMapping("birth/{nin}")
    public ResponseEntity<Users> updateBirthDetail(
            @PathVariable("nin") String nin,
            @RequestBody BirthRegistrationRequest request
    ){
        String childName = request.getChildname();
        Date birthDate = request.getBirthdate();
        String gender = request.getGender();
        String placeOfBirth = request.getPlaceofbirth();
        String fatherName = request.getFathername();
        String motherName = request.getMothername();
        // Generate random birth_id
        String birthId = randomNumberGenerator.generateRandomNumber(5);
        String birthstatus = "pending";
        System.out.println(birthId);



        Users updatedUser = userService.updateBirthDetails(nin,
                childName,
                birthDate,
                gender,
                placeOfBirth,
                fatherName,
                motherName,
                birthId,
                birthstatus
        );

        updatedUser.setBirthid(birthId);

        return ResponseEntity.ok(updatedUser);

    }

    @PutMapping("death/{nin}")
    public ResponseEntity<Users> updateDeathDetail(
            @PathVariable("nin") String nin,
            @RequestBody DeathRegistrationRequest request
    ){
        String deathName = request.getDeathname();
        Date deathDate = request.getDateofdeath();
        String deathgender = request.getDeathgender();
        String placeOfDeath = request.getPlaceofdeath();
        // Generate random birth_id
        String deathId = randomNumberGenerator.generateRandomNumber(5);
        String deathstatus = "pending";
        System.out.println(deathId);



        Users updatedUser = userService.updateDeathDetails(nin,
                deathName,
                deathDate,
                deathgender,
                placeOfDeath,
                deathId,
                deathstatus
        );

        updatedUser.setBirthid(deathId);

        return ResponseEntity.ok(updatedUser);

    }


    @GetMapping("/birth-details")
    public ResponseEntity<Map<String,Object>> getUserBirthDetails(@RequestParam String nin) {
        Optional<Users> userOptional = userService.getUsersByNin(nin);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            Map<String, Object> birthDetails = new HashMap<>();
            birthDetails.put("birthid", user.getBirthid());
            birthDetails.put("childname", user.getChildname());
            birthDetails.put("birthdate", user.getBirthdate());
            birthDetails.put("gender", user.getGender());
            birthDetails.put("placeofbirth", user.getPlaceofbirth());
            birthDetails.put("fathername", user.getFathername());
            birthDetails.put("mothername", user.getMothername());
            birthDetails.put("attestid", user.getAttestid());
            birthDetails.put("attestname", user.getAttestname());
            birthDetails.put("attestdate", user.getAttestdate());
            birthDetails.put("attestage", user.getAttestage());
            birthDetails.put("attestlg", user.getUser_id());
            return ResponseEntity.ok(birthDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/birth-by-id")
    public ResponseEntity<Map<String,Object>> getUserBirthCert(@RequestParam String birthid) {
        Optional<Users> userOptional = userService.getUserByBirthid(birthid);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            Map<String, Object> birthDetails = new HashMap<>();
            birthDetails.put("birthid", user.getBirthid());
            birthDetails.put("childname", user.getChildname());
            birthDetails.put("birthdate", user.getBirthdate());
            birthDetails.put("gender", user.getGender());
            birthDetails.put("placeofbirth", user.getPlaceofbirth());
            birthDetails.put("fathername", user.getFathername());
            birthDetails.put("mothername", user.getMothername());
            return ResponseEntity.ok(birthDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }





    @RestController
    @RequestMapping
    @CrossOrigin
    public class LoginController {

        private final UserService userService;

        public LoginController(UserService userService ) {
            this.userService = userService;
        }

        @PostMapping("/login")
        public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
            String nin = loginRequest.getNin();
            String password = loginRequest.getPassword();

            // Retrieve user by Nin
            Optional<Users> user = userService.getUsersByNin(nin);

            if (user.isPresent()) {
                Users users = user.get();
                if (users.getPassword().equals(password)) {

                    // Create a UserResponse object with user credentials
                    UserResponse userResponse = new UserResponse(users.getNin(), users.getName());
                    System.out.println(userResponse);
                    return ResponseEntity.ok(userResponse);
                }
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }



    }


}
