package com.red_social.demo.controller;

import com.red_social.demo.dto.CreateMessageRequestDTO;
import com.red_social.demo.dto.CreateMessageResponseDTO;
import com.red_social.demo.dto.EditMessageRequestDTO;
import com.red_social.demo.service.IMessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasAuthority('ROLE_USER')")
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @PreAuthorize("hasAuthority('MESSAGE_CREATE')")
    @PostMapping
    public ResponseEntity<CreateMessageResponseDTO> createMessage(@Valid @RequestBody CreateMessageRequestDTO message, Authentication authentication){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(messageService
                        .create(message,authentication.getName()));
    }
    @PreAuthorize("hasAuthority('MESSAGE_READ')")
    @GetMapping("/history")
    public ResponseEntity getOwnMessages(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK)
                .body(messageService.getOwnMessages(authentication.getName()));
    }

    @PreAuthorize("hasAuthority('MESSAGE_DELETE') and @messageSecurity.isOwner(#id,authentication)")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        messageService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted message");
    }
    @PreAuthorize("hasAuthority('MESSAGE_EDIT') and @messageSecurity.isOwner(#id,authentication)")
    @PatchMapping("/edit/{id}")
    public ResponseEntity editById(@PathVariable Long id,
                                   @RequestBody EditMessageRequestDTO requestEdit){
        messageService.edit(id, requestEdit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Successfully edited message");
    }
}
