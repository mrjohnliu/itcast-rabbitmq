package cn.itcast.rabbitmq.routing;

import cn.itcast.rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class Recv2 {

    private final static String QUEUE_NAME = "test_queue_work2";

    private final static String EXCHANGE_NAME = "test_exchange_direct1";

    public static void main(String[] argv) throws Exception {

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "key2");

        channel.basicQos(1);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, false, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" ¿Í»§¶Ë2Sent Received '" + message + "'");
            Thread.sleep(10);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}