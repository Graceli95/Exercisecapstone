package com.grace.exercisetrackerserver.repositories;

import com.grace.exercisetrackerserver.models.BaseUser;
import com.grace.exercisetrackerserver.models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    //List<Workout> findAllByBaseUser(BaseUser baseUser); // Fetch workouts for a specific user,✅ Allows fetching workouts by base_user_id

}


//Repositories → Handle database operations.
//Services → Handle business logic.
//Controllers → Handle API requests.