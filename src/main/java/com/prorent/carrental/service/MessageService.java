package com.prorent.carrental.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prorent.carrental.domain.Message;
import com.prorent.carrental.exception.ResourceNotFoundException;
import com.prorent.carrental.repository.MessageRespository;

import lombok.AllArgsConstructor;
@Transactional
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
	public List<Message> getAllMessages() {
		return messageRespository.findAll();
		
	}
	
	public void deleteMessage(Long id) throws ResourceNotFoundException{
		messageRespository.deleteById(id);
	}
	
	
	public Message updateMessage(Message message) {
		return messageRespository.save(message);
	}
}
