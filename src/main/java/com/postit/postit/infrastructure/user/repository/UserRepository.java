package com.postit.postit.infrastructure.user.repository;

import com.postit.postit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Long, User> {

    Optional<User> findByEmailContainsIgnoreCase(String email);
}
