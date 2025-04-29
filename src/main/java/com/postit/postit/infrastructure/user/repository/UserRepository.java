package com.postit.postit.infrastructure.user.repository;

import com.postit.postit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Long, User> {
}
