package br.com.mangarosa.messages.impl.producer;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Producer;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.List;

public class PyMarketPlaceProducer implements Producer {
    private final List<Topic> topics = new ArrayList<>();

    @Override
    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    @Override
    public void removeTopic(Topic topic) {
        topics.remove(topic);
    }

    @Override
    public void sendMessage(String message) {
        for (Topic topic : topics) {
            topic.addMessage(new Message(this,"PyMarketPlace: " + message));
        }
    }

    @Override
    public String name() {
        return "PyMarketPlaceProducer";
    }
}
