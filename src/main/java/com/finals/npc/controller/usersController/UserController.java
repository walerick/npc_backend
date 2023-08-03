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

//    REGISTRATION  OF USERS
    @PostMapping("register")
    public ResponseEntity<String> registerNewUser(@RequestBody Users user){
        boolean ninExists = userService.checkIfNinExists(user.getNin());

        if(ninExists){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with the same NIN already exists.");
        }
        userService.addNewUser(user);
        return ResponseEntity.ok("User registered Successfully");
    }

// GET ALL USERS DETAILS
    @GetMapping
    public List<Users> Users(){
       return userService.getUser();
    }

//    GET USER DETAIL BY NIN
    @GetMapping("get-by-nin/{nin}")
    public Users getUserByNin(@PathVariable String nin) {
        return userRepo.findUsersByNin(nin).orElse(null);
    }

    @PutMapping("birth/{nin}")
    public ResponseEntity<Users> updateBirthDetail(
            @PathVariable("nin") String nin,
            @RequestBody BirthRegistrationRequest request
    ){
        String childName = request.getChildName();
        Date birthDate = request.getBirthDate();
        String gender = request.getGender();
        String placeOfBirth = request.getPlaceOfBirth();
        String fatherName = request.getFatherName();
        String motherName = request.getMotherName();
        // Generate random birth_id
        String birthId = randomNumberGenerator.generateRandomNumber(5);
        String birthStatus = "pending";
        System.out.println(birthId);



        Users updatedUser = userService.updateBirthDetails(nin,
                childName,
                birthDate,
                gender,
                placeOfBirth,
                fatherName,
                motherName,
                birthId,
                birthStatus
        );

        updatedUser.setBirthId(birthId);

        return ResponseEntity.ok(updatedUser);

    }

    @PutMapping("death/{nin}")
    public ResponseEntity<Users> updateDeathDetail(
            @PathVariable("nin") String nin,
            @RequestBody DeathRegistrationRequest request
    ){
        String deathName = request.getDeathName();
        Date deathDate = request.getDateOfDeath();
        String deathGender = request.getDeathGender();
        String placeOfDeath = request.getPlaceOfDeath();
        // Generate random birth_id
        String deathId = randomNumberGenerator.generateRandomNumber(5);
        String deathStatus = "pending";
        System.out.println(deathId);



        Users updatedUser = userService.updateDeathDetails(nin,
                deathName,
                deathDate,
                deathGender,
                placeOfDeath,
                deathId,
                deathStatus
        );

        updatedUser.setBirthId(deathId);

        return ResponseEntity.ok(updatedUser);

    }
// CONTROLLER TO INPUT ATTEST DETAILS
    @PutMapping("attest/{nin}")
    public ResponseEntity<Users> updateAttestDetail(
            @PathVariable("nin") String nin,
            @RequestBody AttestRegistrationRequest request
    ){
        String attestName = request.getAttestName();
        int attestAge = request.getAttestAge();
        Date attestDate = request.getAttestDate();

        String attestLg = request.getAttestLg();

        // Generate random birth_id
        String attestId = randomNumberGenerator.generateRandomNumber(3);
        String attestationStatus = "pending";
        String attestationByStaffStatus = "pending";
        System.out.println(attestId);


        Users updatedUser = userService.updateAttestDetails(nin,
                attestName,
                attestAge,
                attestDate,
                attestLg,
                attestId,
                attestationStatus,
                attestationByStaffStatus
        );

        updatedUser.setAttestId(attestId);

        return ResponseEntity.ok(updatedUser);

    }
//-------------------------------------

    @GetMapping("/birth-details")
    public ResponseEntity<Map<String,Object>> getUserBirthDetails(@RequestParam String nin) {
        Optional<Users> userOptional = userService.getUsersByNin(nin);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setBirthStatus("pending");
            Map<String, Object> birthDetails = new HashMap<>();
            birthDetails.put("birthId", user.getBirthId());
            birthDetails.put("childName", user.getChildName());
            birthDetails.put("birthDate", user.getBirthDate());
            birthDetails.put("gender", user.getGender());
            birthDetails.put("placeOfBirth", user.getPlaceOfBirth());
            birthDetails.put("fatherName", user.getFatherName());
            birthDetails.put("motherName", user.getMotherName());
            birthDetails.put("attestId", user.getAttestId());
            birthDetails.put("attestName", user.getAttestName());
            birthDetails.put("attestDate", user.getAttestDate());
            birthDetails.put("attestLg", user.getAttestLg());
            birthDetails.put("attestAge", user.getAttestAge());
            birthDetails.put("birthStatus", user.getBirthStatus());
            return ResponseEntity.ok(birthDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/attest-details")
    public ResponseEntity<Map<String,Object>> getUserAttestDetails(@RequestParam String nin) {
        Optional<Users> userOptional = userService.getUsersByNin(nin);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setAttestationStatus("pending");
            Map<String, Object> attestDetails = new HashMap<>();
            attestDetails.put("attestId", user.getAttestId());
            attestDetails.put("attestName", user.getAttestName());
            attestDetails.put("attestDate", user.getAttestDate());
            attestDetails.put("attestLg", user.getAttestLg());
            attestDetails.put("attestAge", user.getAttestAge());
            attestDetails.put("attestationStatus", user.getAttestationStatus());
            return ResponseEntity.ok(attestDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }





    @GetMapping("/death-details")
    public ResponseEntity<Map<String,Object>> getUserDeathDetails(@RequestParam String nin) {
        Optional<Users> userOptional = userService.getUsersByNin(nin);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setDeathStatus("pending");
            Map<String, Object> deathDetails = new HashMap<>();
            deathDetails.put("deathId", user.getDeathId());
            deathDetails.put("deathName", user.getDeathName());
            deathDetails.put("deathGender", user.getDeathGender());
            deathDetails.put("dateOfDeath", user.getDateAtDeath());
            deathDetails.put("placeOfDeath", user.getPlaceOfDeath());
            deathDetails.put("deathStatus", user.getDeathStatus());
            return ResponseEntity.ok(deathDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/birth-by-id")
    public ResponseEntity<Map<String,Object>> getUserBirthCert(@RequestParam String birthId) {
        Optional<Users> userOptional = userService.getUserByBirthId(birthId);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            Map<String, Object> birthDetails = new HashMap<>();
            birthDetails.put("birthId", user.getBirthId());
            birthDetails.put("childName", user.getChildName());
            birthDetails.put("birthDate", user.getBirthDate());
            birthDetails.put("gender", user.getGender());
            birthDetails.put("placeOfBirth", user.getPlaceOfBirth());
            birthDetails.put("fatherName", user.getFatherName());
            birthDetails.put("motherName", user.getMotherName());
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
