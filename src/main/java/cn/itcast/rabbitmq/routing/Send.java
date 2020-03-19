package cn.itcast.rabbitmq.routing;

import cn.itcast.rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {

    private final static String EXCHANGE_NAME = "test_exchange_direct1";

    public static void main(String[] argv) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, "now", null, message.getBytes());
        System.out.println(" server¶Ë2Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}