package com.grace.exercisetrackerserver.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.grace.exercisetrackerserver.DTO.WorkoutDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "workout")
@Data

public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // e.g., Strength, Cardio, Yoga, etc.

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(nullable = false)
    private int duration;

    @Column(name = "calories_burned", nullable = false)
    private int caloriesBurned;

    // 🔹 make visible, so that new workouts can store their userId,
    @Column(name = "base_user_id", nullable = false)
    private Long baseUserId;




//    public WorkoutDTO getWorkoutDto(){
//        WorkoutDTO workoutDTO = new WorkoutDTO();
//        workoutDTO.setId(id);
//        workoutDTO.setType(type);
//        workoutDTO.setDate(date);
//        workoutDTO.setDuration(duration);
//        workoutDTO.setCaloriesBurned(caloriesBurned);
//
//        return workoutDTO;
//    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Workout workout = (Workout) o;
        return Objects.equals(id, workout.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
