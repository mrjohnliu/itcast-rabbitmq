package cn.itcast.rabbitmq.util;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

public class ConnectionUtil {

    public static Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("liukev");
        factory.setUsername("liuke");
        factory.setPassword("liuke");
        Connection connection = factory.newConnection();
        return connection;
    }

}
