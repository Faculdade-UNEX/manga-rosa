package br.com.mangarosa.messages.impl.consumer;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;

public class FastDeliveryConsumer implements Consumer {
    @Override
    public boolean consume(Message message) {

        System.out.println("Consuming fast delivery message: " + message.getMessage());

        message.setConsumed(true);

        message.addConsumption(this);

        return true;
    }

    @Override
    public String name() {
        return "FastDeliveryConsumer";
    }
}
