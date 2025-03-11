package com.grace.exercisetrackerserver.controllers;

import com.grace.exercisetrackerserver.models.Activity;
import com.grace.exercisetrackerserver.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController       // It is used to create RESTful web services by combining `@Controller` and `@ResponseBody`.
@RequestMapping("/activities")
@CrossOrigin("*")
public class ActivityController {

    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/user/{userId}/new")
    ResponseEntity<Activity> createActivity(@PathVariable Long userId, @RequestBody Activity activity){
        Activity activityCreated = activityService.createActivity(userId, activity);
        System.out.println(activityCreated);
        return ResponseEntity.ok(activityCreated);

    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Activity>> getUsersActivities(@PathVariable Long userId){
        List<Activity> activities = activityService.getAllActivitiesByUserId(userId);
        return activities.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(activities);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable Long id, @RequestBody Activity updatedActivity){
        try {
            Activity activity = activityService.updateActivity(id, updatedActivity);
            return ResponseEntity.ok(activity);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{activityId}")
    public ResponseEntity<Void> deleteActivityById(@PathVariable Long activityId){
        activityService.deleteActivityById(activityId);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Activity> getActivityById(@PathVariable Long activityId){
        Activity activity = activityService.getActivityById(activityId);
        return ResponseEntity.ok(activity);
    }



}


//    private final ActivityService activityService;

//    //inject ActivityService
//    @Autowired
//    public ActivityController(ActivityService activityService) {
//        this.activityService = activityService;
//    }
//
//
//    @PostMapping("/activity")
//    public ResponseEntity<?> postActivity(@RequestBody ActivityDTO dto){   //Business logic for saving the activity
//
//
//        ActivityDTO createActivity = activityService.postActivity(dto); //create an object of ActivityDTO
//
//        if(createActivity != null){
//            return ResponseEntity.status(HttpStatus.CREATED).body(createActivity);
//        }else{
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
//        }
//    }
//
//
//
//    @GetMapping("/activities")
//    public ResponseEntity<?> getActivities() { // Business logic for fetching all activities
//        try{
//            return ResponseEntity.ok(activityService.getAllActivities());
//        }catch(Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
//        }
//    }


//Controller exposes REST APIs for managing activities.

/**
 * ### What Is `@RestController`?
 * - `@RestController` is a Spring annotation that marks a class as a controller where every method returns a domain object (e.g., JSON, XML) instead of a view (like JSP, Thymeleaf, etc.).
 * - It eliminates the need to add `@ResponseBody` to every method. This means that the return values from the controller methods are automatically serialized into the desired format (e.g., JSON) and sent as the HTTP response body.
 *
 * The `@RestController` annotation implicitly includes:
 * 1. `@Controller`: Marks the class as a web controller (handles HTTP requests).
 * 2. `@ResponseBody`: Indicates that the return value of the controller's methods will be serialized into an HTTP response body automatically.
 *
 *
 * Here’s how `@RestController` affects the behavior of the `ActivityController` class:
 * 1. **Mark the Class as a Spring Controller**
 *     - Spring detects the `ActivityController` class as a component responsible for handling HTTP requests and associates it with the specified base URL (`/api`).
 *
 * 2. **Implicit `@ResponseBody` Behavior**
 *     - The `ResponseEntity<?>` objects returned by the methods are converted into JSON and sent as the body of the HTTP response. For example:
 *         - If the `postActivity` method is called with appropriate input, the response body will include the created `ActivityDTO` in JSON format.
 *         - If there is an error, a string message (JSON-formatted by Spring) indicating the error will be sent as the response body. For instance:
 */