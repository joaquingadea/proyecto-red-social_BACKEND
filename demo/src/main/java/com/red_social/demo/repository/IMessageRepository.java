package com.red_social.demo.repository;

import com.red_social.demo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IMessageRepository extends JpaRepository<Message,Long> {
    List<Message> findAllByProfileUserUsernameOrderByCreationDateDesc(String name);
}


