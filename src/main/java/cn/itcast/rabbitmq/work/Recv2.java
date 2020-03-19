package cn.itcast.rabbitmq.work;

import cn.itcast.rabbitmq.util.ConnectionUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

public class Recv2 {

    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] argv) throws Exception {

        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.basicQos(1);//���߶��� �øɵ����ľ�������Ϣ

        QueueingConsumer consumer = new QueueingConsumer(channel);
        //false��ʾ�����Զ�ȷ����Ϣ��������
        channel.basicConsume(QUEUE_NAME, false, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
            Thread.sleep(1000);
            //ȷ����Ϣ����
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}