package com.prorent.carrental.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prorent.carrental.domain.Message;
import com.prorent.carrental.exception.ResourceNotFoundException;
import com.prorent.carrental.repository.MessageRespository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MessageService {

	private MessageRespository messageRespository;

	public Message createMessage(Message message) {
		return messageRespository.save(message);
	}

	public Message getMessage(Long id) {
		Message foundMessage = messageRespository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("id not found : " + id));
		return foundMessage;
	}
}
