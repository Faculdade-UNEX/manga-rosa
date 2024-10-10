package br.com.mangarosa;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.impl.MessageRepositoryImpl;
import br.com.mangarosa.messages.impl.consumer.FastDeliveryConsumer;
import br.com.mangarosa.messages.impl.producer.FoodDeliveryProducer;
import br.com.mangarosa.messages.impl.producer.PhysicPersonDeliveryProducer;
import br.com.mangarosa.messages.impl.topic.FastDeliveryItemsTopic;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.Topic;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //criar repositorio
        createFastDelivery();
    }


    private static void createFastDelivery() {
        Topic fastDeliveryTopic = new FastDeliveryItemsTopic(new MessageRepositoryImpl());

        //adicionar produtores das mensagens
        FoodDeliveryProducer foodDeliveryProducer = new FoodDeliveryProducer();
        PhysicPersonDeliveryProducer physicPersonDeliveryProducer = new PhysicPersonDeliveryProducer();

        foodDeliveryProducer.addTopic(fastDeliveryTopic);
        physicPersonDeliveryProducer.addTopic(fastDeliveryTopic);

        //adicionar mensagens
        fastDeliveryTopic.addMessage(new Message(foodDeliveryProducer, "Produtor: foodDeliveryProducer"));
        fastDeliveryTopic.addMessage(new Message(physicPersonDeliveryProducer, "Produtor: physicPersonDeliveryProducer"));

        //adicionar o consumidor das mensagens

        Consumer consumer = new FastDeliveryConsumer();
        fastDeliveryTopic.subscribe(consumer);

        for (Message msn: fastDeliveryTopic.getRepository().getAllNotConsumedMessagesByTopic(fastDeliveryTopic.name())) {
            fastDeliveryTopic.notifyConsumers(msn);
        }

        try { //fazer sistema esperar o tempo para testar se foi expirado
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var mensagensConsumidas = fastDeliveryTopic.getRepository().getAllConsumedMessagesByTopic(fastDeliveryTopic.name());
        System.out.println("Mensagens consumidas: " + mensagensConsumidas);
    }
}