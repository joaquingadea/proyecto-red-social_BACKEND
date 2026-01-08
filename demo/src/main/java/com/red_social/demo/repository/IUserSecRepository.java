package com.red_social.demo.repository;

import com.red_social.demo.model.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserSecRepository extends JpaRepository<UserSec,Long> {
    Optional<UserSec> findUserSecEntityByUsername(String username);
}
