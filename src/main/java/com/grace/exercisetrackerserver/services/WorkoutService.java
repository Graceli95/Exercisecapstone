package com.grace.exercisetrackerserver.services;

import com.grace.exercisetrackerserver.models.BaseUser;
import com.grace.exercisetrackerserver.models.Workout;
import com.grace.exercisetrackerserver.repositories.BaseUserRepository;
import com.grace.exercisetrackerserver.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final BaseUserRepository baseUserRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository, BaseUserRepository baseUserRepository) {
        this.workoutRepository = workoutRepository;
        this.baseUserRepository = baseUserRepository;
    }

    public Workout createWorkout(Long userId, Workout workout) {
        Optional<BaseUser> baseUserOptional = baseUserRepository.findById(userId);

        if (baseUserOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        BaseUser baseUser = baseUserOptional.get();

        // 🔹 Associate workout with user
        workout.setBaseUserId(baseUser.getId());

        // 🔹 Save workout to database
        return workoutRepository.save(workout);
    }


    public List<Workout> getAllWorkoutsByUserId(Long userId) {
        Optional<BaseUser> baseUser = baseUserRepository.findBaseUserById(userId);
        if (baseUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return baseUser.get().getWorkouts();
    }

    public Workout updateWorkout(Long id, Workout updatedWorkout) {
        return workoutRepository.findById(id)
                .map(workout -> {
                    workout.setCaloriesBurned(updatedWorkout.getCaloriesBurned());
                    workout.setType(updatedWorkout.getType());
                    workout.setDuration(updatedWorkout.getDuration());
                    workout.setDate(updatedWorkout.getDate());
                    return workoutRepository.save(workout);
                })
                .orElseThrow(() -> new RuntimeException("workout not found"));
    }

    public void deleteWorkoutById(Long id) {
        workoutRepository.deleteById(id);
    }






    public Workout getWorkoutById(Long id) {
        return workoutRepository.findById(id).orElseThrow(()->new RuntimeException("Workout not found"));
    }
}
//✅ Handles fetching, updating, deleting, and saving workouts.
