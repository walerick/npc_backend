package com.finals.npc.controller.usersController;

import com.finals.npc.model.Users;
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
    @CrossOrigin
    @PostMapping("register")
    public void registerNewUser(@RequestBody Users user){
        userService.addNewUser(user);
    }


    @GetMapping
    public List<Users> Users(){
       return userService.getUser();
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
        System.out.println(birthId);



        Users updatedUser = userService.updateBirthDetails(nin, childName, birthDate, gender, placeOfBirth, fatherName, motherName, birthId);

        updatedUser.setBirthid(birthId);

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
