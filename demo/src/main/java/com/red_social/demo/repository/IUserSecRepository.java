package com.red_social.demo.repository;

import com.red_social.demo.model.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserSecRepository extends JpaRepository<UserSec,Long> {
}
