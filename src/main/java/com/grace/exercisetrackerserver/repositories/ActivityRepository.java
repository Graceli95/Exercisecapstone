package com.grace.exercisetrackerserver.repositories;

import com.grace.exercisetrackerserver.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
