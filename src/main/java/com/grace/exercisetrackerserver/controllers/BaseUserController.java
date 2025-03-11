package com.grace.exercisetrackerserver.controllers;

import com.grace.exercisetrackerserver.models.BaseUser;
import com.grace.exercisetrackerserver.models.Workout;
import com.grace.exercisetrackerserver.services.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class BaseUserController {

    private final BaseUserService baseUserService;

    @Autowired
    public BaseUserController(BaseUserService baseUserService) {
        this.baseUserService = baseUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<BaseUser> registerUser(@RequestBody BaseUser baseUser) {
        try {
            BaseUser createdUser = baseUserService.create(baseUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(baseUser);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<BaseUser> loginUser(@RequestBody BaseUser baseUser) {
        try {
            Optional<BaseUser> user = baseUserService.authenticateUser(baseUser.getEmail(), baseUser.getPassword());
            if (user.isPresent()){
                return new  ResponseEntity<>(user.get(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

////            return user.map(ResponseEntity::ok)
//            return new ResponseEntity<>(user.get() ,HttpStatus.OK);

//                    .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/{userId}/workouts")
    public ResponseEntity<List<Workout>> getWorkouts(@PathVariable Long userId){
        Optional<BaseUser> user = baseUserService.findBaseUserById(userId);
        if (user.isPresent()) {
            List<Workout> workouts = user.get().getWorkouts();
            return ResponseEntity.ok(workouts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //✅ Handles user registration and login.
    // todo logout
    // setCurrentUser(null)
    // take you to landing or login page


//    @GetMapping("/{id}")
//    public ResponseEntity<BaseUser> getUserById(@PathVariable Long id) {
//        return baseUserService.getUserById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
    @GetMapping("/email/{email}")
    public ResponseEntity<BaseUser> getUserByEmail(@PathVariable String email){
        return baseUserService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        baseUserService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(baseUserService.getAllUsers());
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
        }
    }
}
/**
 * ✅ /users/login API Endpoint
 * Accepts email and password in the request.
 * Checks if a user exists in the database.
 * Returns 200 OK if successful, or 401 Unauthorized if credentials are incorrect.
 */