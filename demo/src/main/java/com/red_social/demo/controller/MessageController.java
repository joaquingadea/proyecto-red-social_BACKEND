package com.red_social.demo.controller;

import com.red_social.demo.dto.CreateMessageRequestDTO;
import com.red_social.demo.dto.CreateMessageResponseDTO;
import com.red_social.demo.dto.EditMessageRequestDTO;
import com.red_social.demo.dto.GetEditMessageResponseDTO;
import com.red_social.demo.service.IMessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private IMessageService messageService;

    @PreAuthorize("hasAuthority('MESSAGE_CREATE')")
    @PostMapping
    public ResponseEntity<CreateMessageResponseDTO> createMessage(@RequestBody CreateMessageRequestDTO message, Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK)
                .body(messageService
                        .create(message,authentication.getName()));
    }
    @PreAuthorize("hasAuthority('MESSAGE_READ')")
    @GetMapping("/history")
    public ResponseEntity getOwnMessages(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK)
                .body(messageService.getOwnMessages(authentication.getName()));
    }
    @PreAuthorize("hasAuthority('MESSAGE_READ')")
    @GetMapping
    public ResponseEntity getAllMessages(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(messageService.getAllMessages());
    }

    @PreAuthorize("hasAuthority('MESSAGE_DELETE') and @messageSecurity.isOwner(#id,authentication)")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        messageService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted message");
    }
    @PreAuthorize("hasAuthority('MESSAGE_UPDATE') and @messageSecurity.isOwner(#id,authentication)")
    @PatchMapping("/edit/{id}")
    public ResponseEntity editById(@PathVariable Long id,
                                   @RequestBody EditMessageRequestDTO requestEdit,
                                   Authentication authentication){
        messageService.edit(id, requestEdit, authentication);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully edited message");
    }
    @PreAuthorize("hasAuthority('MESSAGE_UPDATE') and @messageSecurity.isOwner(#id,authentication)")
    @GetMapping("/get-edit-message/{id}")
    public ResponseEntity editById(@PathVariable Long id,Authentication authentication){
        GetEditMessageResponseDTO messageResponseDTO = messageService.findById(id,authentication);
        return ResponseEntity.status(HttpStatus.OK).body(messageResponseDTO);
    }
}
