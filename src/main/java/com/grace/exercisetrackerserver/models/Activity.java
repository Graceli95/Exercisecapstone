package com.grace.exercisetrackerserver.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.grace.exercisetrackerserver.DTO.ActivityDTO;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "activity")
@Data
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column
//    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private int steps;
    @Column(nullable = false)
    private double distance;
    @Column(nullable = false)
    private int caloriesBurned;

    @Column(name = "base_user_id", nullable = false)
    private Long baseUserId;

//    @ManyToOne
//    @JoinColumn(name = "baseUserId", nullable = false)
//    private BaseUser baseUser;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(id, activity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public ActivityDTO getActivityDTO(){

        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setDate(date);
        activityDTO.setSteps(steps);
        activityDTO.setDistance(distance);
        activityDTO.setCaloriesBurned(caloriesBurned);

        return activityDTO;
    }
}

/**
 * This code is used to **convert entity objects (`Activity`) into their corresponding DTOs (`ActivityDTO`)**. The transformation is necessary because:
 * - **Separation of Concerns**: The `Activity` entity is tied to the database structure, while `ActivityDTO` is designed for transferring data (e.g., to clients via APIs) without exposing internal data structures.
 * - **Decoupling**: Using a DTO allows more flexibility in structuring the data sent to the client. For example, you can include or exclude fields, rename fields, modify data formats, and so on without altering the database schema.
 *
 * In this context, the `getActivityDTO()` method in `Activity` simplifies this transformation.
 */
