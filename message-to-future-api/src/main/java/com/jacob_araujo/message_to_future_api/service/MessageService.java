package com.jacob_araujo.message_to_future_api.service;

import com.jacob_araujo.message_to_future_api.entity.Message;
import com.jacob_araujo.message_to_future_api.exception.EntityNotFoundException;
import com.jacob_araujo.message_to_future_api.exception.RecipientAndDataUniqueViolationException;
import com.jacob_araujo.message_to_future_api.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Transactional
    public Message save(Message message) {
        try{
            return messageRepository.save(message);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            throw new RecipientAndDataUniqueViolationException(String.format("Recipient %s already exists with this data", message.getRecipientName()));
        }
    }

    @Transactional(readOnly = true)
    public Message searchById(Long id) {
        return messageRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Message id=%s not found.", id))
        );
    }

    public List<Message> searchAllMessagesFromOneUser(Long userId) {
        return messageRepository.findBySenderUserId(userId);
    }
}
