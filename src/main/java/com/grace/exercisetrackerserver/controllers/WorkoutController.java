package com.grace.exercisetrackerserver.controllers;

import com.grace.exercisetrackerserver.models.Workout;
import com.grace.exercisetrackerserver.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/workouts")
@CrossOrigin("*")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/user/{userId}/new")
    public ResponseEntity<Workout> createWorkout(@PathVariable Long userId, @RequestBody Workout workout){
        System.out.println(workout);
        Workout savedWorkout = workoutService.createWorkout(userId, workout);
        System.out.println(savedWorkout);

        return ResponseEntity.ok(savedWorkout);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable Long id, @RequestBody Workout updatedWorkout) {
        try {
            Workout workout = workoutService.updateWorkout(id, updatedWorkout);
            return ResponseEntity.ok(workout);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{workoutId}")
    public ResponseEntity<Void> deleteWorkoutById(@PathVariable Long workoutId) {
        workoutService.deleteWorkoutById(workoutId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Workout>> getUsersWorkouts(@PathVariable Long userId){
        List<Workout> workouts = workoutService.getAllWorkoutsByUserId(userId);
        return workouts.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(workouts);

    }


    //✅ Allows fetching, creating, updating, and deleting workouts.
//    @PutMapping("/workouts/{id}")
//    public ResponseEntity<Workout> updateWorkout(@PathVariable Long id, @RequestBody Workout updatedWorkout) {
////        Workout workout = workoutService.updateWorkout(updatedWorkout);
//        if(workout == null){
//            return ResponseEntity.notFound().build();
//        }else{
//            return ResponseEntity.ok(workout);
//        }
//    }





//    @PostMapping("/new")
//    public ResponseEntity<?> postWorkout(@RequestBody WorkoutDTO workoutDTO){
//        try{
//            //return ResponseEntity.ok(workoutService.postWorkout(workoutDTO));
//            return null; // todo, delete
//        }catch(Exception e){
//
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(workoutDTO);
//        }
//    }

//    @GetMapping("/getAll")
//    public ResponseEntity<List<Workout>> getUserWorkouts(){
//            return ResponseEntity.ok(workoutService.getWorkoutsForCurrentUser());
//    }

//    @GetMapping("/users/{userId}/workouts")
//    public ResponseEntity<List<Workout>> getUserWorkouts(@PathVariable Long userId){
//        List<Workout> workouts = UserAuthHelper.getCurrentUser().getWorkouts();
//        if(workouts.isEmpty()){
//            return ResponseEntity.notFound().build();
//        }else{
//            return ResponseEntity.ok(workouts);
//        }
//    }


}
