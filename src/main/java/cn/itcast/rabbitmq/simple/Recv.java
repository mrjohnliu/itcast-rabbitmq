package cn.itcast.rabbitmq.simple;

import cn.itcast.rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class Recv {

    private final static String QUEUE_NAME = "test_queue";

    public static void main(String[] argv) throws Exception {

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        
        //true表示可以自动确认消息被消费了
        channel.basicConsume(QUEUE_NAME, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }
    }
}