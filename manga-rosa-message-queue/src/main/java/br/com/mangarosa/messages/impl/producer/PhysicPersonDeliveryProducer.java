package br.com.mangarosa.messages.impl.producer;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Producer;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.List;

public class PhysicPersonDeliveryProducer implements Producer {
    private final List<Topic> topics;

    public PhysicPersonDeliveryProducer() {
        this.topics = new ArrayList<>();
    }

    @Override
    public void addTopic(Topic topic) {
        if (!topics.contains(topic)) {
            topics.add(topic);
        }
    }

    @Override
    public void removeTopic(Topic topic) {
        topics.remove(topic);
    }

    @Override
    public void sendMessage(String messageContent) {
        for (Topic topic : topics) {
            topic.addMessage(new Message(this, messageContent));
        }
    }

    @Override
    public String name() {
        return "PhysicPersonDeliveryProducer";
    }
}
