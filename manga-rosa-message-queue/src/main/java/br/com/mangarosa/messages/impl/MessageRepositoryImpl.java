package br.com.mangarosa.messages.impl;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import java.util.*;

public class MessageRepositoryImpl implements MessageRepository {

    private final Map<String, Queue<Message>> topicMap = new HashMap<>();

    @Override
    public void append(String topic, Message message) {
        topicMap.computeIfAbsent(topic, k -> new LinkedList<>()).add(message);
    }

    @Override
    public void consumeMessage(String topic, UUID messageId) {
        Queue<Message> messages = topicMap.get(topic);
        if (messages == null) {
            throw new IllegalArgumentException("Topic does not exist");
        }

        boolean found = false;
        for (Message message : messages) {
            if (message.getId().equals(messageId.toString())) {
                message.setConsumed(true);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Mensagem não encontrada no tópico.");
        }
    }

    @Override
    public List<Message> getAllNotConsumedMessagesByTopic(String topic) {
        Queue<Message> messages = topicMap.get(topic);
        if (messages == null) {
            throw new IllegalArgumentException("Topic does not exist.");
        }

        List<Message> notConsumed = new ArrayList<>();
        for (Message message : messages) {
            if (!message.isConsumed() && !message.isExpired()) {
                notConsumed.add(message);
            }
        }

        return notConsumed;
    }

    @Override
    public List<Message> getAllConsumedMessagesByTopic(String topic) {
        Queue<Message> messages = topicMap.get(topic);
        if (messages == null) {
            throw new IllegalArgumentException("Topic does not exist");
        }

        List<Message> consumed = new ArrayList<>();
        for (Message message : messages) {
            if (message.isConsumed() && !message.isExpired()) {
                consumed.add(message);
            }
        }

        return consumed;
    }
}