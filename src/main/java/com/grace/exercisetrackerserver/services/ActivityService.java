package com.grace.exercisetrackerserver.services;

import com.grace.exercisetrackerserver.models.Activity;
import com.grace.exercisetrackerserver.models.BaseUser;
import com.grace.exercisetrackerserver.repositories.ActivityRepository;
import com.grace.exercisetrackerserver.repositories.BaseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final BaseUserService baseUserService;
    private final BaseUserRepository baseUserRepository;


    @Autowired
    public ActivityService(ActivityRepository activityRepository, BaseUserService baseUserService, BaseUserRepository baseUserRepository) {
        this.activityRepository = activityRepository;
        this.baseUserService = baseUserService;
        this.baseUserRepository = baseUserRepository;
    }

    public Activity createActivity(Long userId, Activity activity) {
        Optional<BaseUser> baseUserOptional = baseUserRepository.findById(userId);

        if(baseUserOptional.isEmpty()){
            throw new RuntimeException("User not found");
        }

        BaseUser baseUser = baseUserOptional.get();

        // 🔹 Associate activity with user
        activity.setBaseUserId(baseUser.getId());

        // 🔹 Save activity to database
        return activityRepository.save(activity);
    }

    public List<Activity> getAllActivitiesByUserId(Long userId) {
        Optional<BaseUser> baseUser = baseUserRepository.findBaseUserById(userId);
        if(baseUser.isEmpty()){
            throw new RuntimeException("User not found");
        }
        return baseUser.get().getActivities();
    }

    public Activity updateActivity(Long id, Activity updatedActivity) {
        return activityRepository.findById(id)
                .map(activity -> {
                    activity.setCaloriesBurned(updatedActivity.getCaloriesBurned());
                    activity.setDate(updatedActivity.getDate());
                    activity.setSteps(updatedActivity.getSteps());
                    return activityRepository.save(activity);
                })
                .orElseThrow(() -> new RuntimeException("Activity not found"));
    }

    public void deleteActivityById(Long id) {
        activityRepository.deleteById(id);
    }

    public Activity getActivityById(Long id) {
        return activityRepository.findById(id).orElseThrow(()->new RuntimeException("Activity not found"));
    }
}

//✅ Handles fetching, updating, deleting, and saving activities.
//Repositories → Handle database operations.
//Services → Handle business logic.
//Controllers → Handle API requests.