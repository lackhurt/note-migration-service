package com.note.migration;

import com.rabbitmq.client.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by lackhurt on 16/5/29.
 */
public class Consumer {

    private Connection connection;
    private ConnectionFactory factory;

    public Consumer() throws IOException, TimeoutException {
        this.factory = new ConnectionFactory();
        this.factory.setHost("pl.me");
        this.factory.setUsername("user");
        this.factory.setPassword("user");
        this.connection = this.factory.newConnection();

    }


    public void registerRPCProcessor(Processor processor) throws IOException, InterruptedException {
        Channel channel = this.connection.createChannel();

        channel.queueDeclare(processor.getQueueName(), false, false, false, null);

        channel.basicQos(1);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(processor.getQueueName(), false, consumer);

        System.out.println(" [x] Awaiting RPC requests");

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();

            BasicProperties props = delivery.getProperties();
            AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                    .Builder()
                    .correlationId(props.getCorrelationId())
                    .build();

            String message = new String(delivery.getBody());

            try {
                channel.basicPublish( "", props.getReplyTo(), replyProps, processor.process(message).getBytes());
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            } catch (Exception exception) {
                exception.printStackTrace();

                channel.basicPublish( "", props.getReplyTo(), replyProps, "fail".getBytes());
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }

        }
    }
}
