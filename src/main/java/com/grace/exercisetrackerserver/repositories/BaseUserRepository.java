package com.grace.exercisetrackerserver.repositories;

import com.grace.exercisetrackerserver.models.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseUserRepository extends JpaRepository<BaseUser, Long> {


    Optional<BaseUser>findBaseUserByEmailAndPassword(String email, String password);

    Optional<BaseUser>findBaseUserByEmail(String email);

    Optional<BaseUser> findBaseUserById(Long id);

}
