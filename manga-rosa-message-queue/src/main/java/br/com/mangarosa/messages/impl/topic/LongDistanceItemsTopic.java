package br.com.mangarosa.messages.impl.topic;

import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.List;

public class LongDistanceItemsTopic implements Topic {
    private final String topicName = "queue/long-distance-items"; // Nome do tópico
    private final List<Consumer> consumers = new ArrayList<>(); // Lista de consumidores
    private final MessageRepository repository; // Repositório de mensagens
    private final LinkedQueue<Message> messageQueue; // Fila de mensagens

    // Construtor que inicializa o repositório e a fila
    public LongDistanceItemsTopic(MessageRepository repository) {
        this.repository = repository;
        this.messageQueue = new LinkedQueue<>(); // Inicializa a fila de mensagens
    }

    @Override
    public String name() {
        return topicName; // Retorna o nome do tópico
    }

    @Override
    public void addMessage(Message message) {
        // Adiciona a mensagem ao repositório
        repository.append(topicName, message);
        // Adiciona a mensagem à fila
        messageQueue.enqueue(message);
        // Notifica os consumidores
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
