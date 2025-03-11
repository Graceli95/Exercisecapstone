//package com.grace.exercisetrackerserver.services;
//
//
//import com.grace.exercisetrackerserver.DTO.ActivityDTO;
//import com.grace.exercisetrackerserver.models.Activity;
//import com.grace.exercisetrackerserver.repositories.ActivityRepository;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class ActivityServiceImpl implements ActivityService {
//
//    private final ActivityRepository activityRepository;
//
//    @Autowired
//    public ActivityServiceImpl(ActivityRepository activityRepository) {
//        this.activityRepository = activityRepository;
//    }
//
//
//    public ActivityDTO postActivity(ActivityDTO dto) { //this method will return activity dto
//
//        //in the body of this method, we need to get the data from the dto
//        // first we need create an object of this activity entity
//        Activity activity = new Activity();
//
//        activity.setDate(dto.getDate());
//        activity.setSteps(dto.getSteps());
//        activity.setDistance(dto.getDistance());
//        activity.setCaloriesBurned(dto.getCaloriesBurned());
//
//        return activityRepository.save(activity).getActivityDTO();     //why here double checking DTO
//
//    }
//
//    public List<ActivityDTO> getAllActivities() {
//        List<Activity> activities = activityRepository.findAll();
//        return activities.stream().map(Activity::getActivityDTO).collect(Collectors.toList());   //- the method reference `Activity::getActivityDTO` is called for each `Activity` object in the list. - **Effect**: For every `Activity` entity in the list, the `getActivityDTO()` method is invoked. This method creates an `ActivityDTO` object and maps the entity's fields (e.g., `date`, `steps`, etc.) to the DTO.
//
//
//    }
//
//}
//
////Service Layer handles business logic and converts Entity → DTO.
//
///**
// *
// * ### Breakdown of the Code:
// * #### **1. `activities.stream()`**
// * - **Input**: A `List<Activity>` called `activities` is retrieved from the repository using `activityRepository.findAll()`. This list contains all the `Activity` entity objects from the database.
// * - **Action**: The `stream()` method converts the `List` into a **Java Stream**. A stream lets you process data in a functional style (e.g., map, filter, reduce, etc.).
// *
// * #### **2. `.map(Activity::getActivityDTO)`**
// * - **Action**: The `map` function transforms each element of the stream. In this case, the method reference `Activity::getActivityDTO` is called for each `Activity` object in the list.
// * - **Effect**: For every `Activity` entity in the list, the `getActivityDTO()` method is invoked. This method creates an `ActivityDTO` object and maps the entity's fields (e.g., `date`, `steps`, etc.) to the DTO.
// *
// * #### **3. `.collect(Collectors.toList())`**
// * - **Action**: The `collect` method converts the stream (which now contains `ActivityDTO` objects) back into a `List<ActivityDTO>`. This transforms the functional pipeline back into a `List` structure.
// *
// * ### Purpose of This Code:
// * This code is used to **convert a list of entity objects (`Activity`) into their corresponding DTOs (`ActivityDTO`)**. The transformation is necessary because:
// * - **Separation of Concerns**: The `Activity` entity is tied to the database structure, while `ActivityDTO` is designed for transferring data (e.g., to clients via APIs) without exposing internal data structures.
// * - **Decoupling**: Using a DTO allows more flexibility in structuring the data sent to the client. For example, you can include or exclude fields, rename fields, modify data formats, and so on without altering the database schema.
// *
// * In this context, the `getActivityDTO()` method in `Activity` simplifies this transformation.
// *
// *
// * ### Analogy:
// * Imagine your `Activity` entity represents a private journal containing raw exercise data (database-specific). The `ActivityDTO` is like a public report summarizing/selecting the relevant details to share with others. The `map` operation here is like transforming these raw journal entries into polished reports.
// * ### Simplified Explanation:
// * This line converts a list of `Activity` objects (raw database entities) into a list of simpler `ActivityDTO` objects (data transfer objects) to:
// * - Decouple database entities from API responses.
// * - Reduce overexposure of database structure.
// * - Improve maintainability and flexibility of the system.
// */
