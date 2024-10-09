package br.com.mangarosa.messages.impl.topic;

import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.List;

public class FastDeliveryItemsTopic implements Topic {
    private final String topicName = "queue/fast-delivery-items";
    private final List<Consumer> consumers = new ArrayList<>();
    private final MessageRepository repository; // Adicione um repositório de mensagens se necessário

    public FastDeliveryItemsTopic(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public String name() {
        return topicName;
    }

    @Override
    public void addMessage(Message message) {

        repository.append(topicName, message);
        notifyConsumers(message);
    }

    @Override
    public void subscribe(Consumer consumer) {
        if (!consumers.contains(consumer)) {
            consumers.add(consumer);
        }
    }

    @Override
    public void unsubscribe(Consumer consumer) {
        consumers.remove(consumer);
    }

    @Override
    public List<Consumer> consumers() {
        return consumers;
    }

    @Override
    public MessageRepository getRepository() {
        return repository;
    }

}
