package cn.itcast.rabbitmq.ps;

import cn.itcast.rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {

    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] argv) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        for (int i = 0; i < 10; i++) {
        	 String message = i+": Hello World!";
             channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
             System.out.println(" [x] Sent '" + message + "'");
		}
       

        channel.close();
        connection.close();
    }
}