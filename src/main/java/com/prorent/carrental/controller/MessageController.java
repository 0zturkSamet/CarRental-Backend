package com.prorent.carrental.controller;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prorent.carrental.domain.Message;
import com.prorent.carrental.service.MessageService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("")
public class MessageController {

	private MessageService messageService;
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(MessageController.class);

	@PostMapping("/message")
	public ResponseEntity<Message> createMessage(@Valid @RequestBody Message message) {
		Message savedMessage = messageService.createMessage(message);
		return new ResponseEntity<Message>(savedMessage, HttpStatus.CREATED);//first method
	}
	@GetMapping("/message/{id}")
	public ResponseEntity<Message>getMessage(@PathVariable Long id){
		Message findMessage = messageService.getMessage(id);
		return ResponseEntity.ok(findMessage); //second method
		
	}
}
