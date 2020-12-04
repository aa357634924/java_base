package jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;
import java.util.UUID;

/**
 * jms 生产者
 * @Author: rj
 * @Date: 2020-11-26 19:39
 * @Version: 1.0
 */
public class JmsProducer1 {
    public static void main(String[] args) throws JMSException {
        String brokerUrl = "tcp://8.131.71.92:61618";
        String queueName = "第一个消息队列";
        // 1、创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        connectionFactory.setAlwaysSyncSend(true);// 设置异步发送
        // 2、创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // 3、创建会话session  第一个参数：是否开启事务；第二个参数：应答模式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4、创建目的地
        Queue queue = session.createQueue(queueName);
        // 5、创建消息生产者
        ActiveMQMessageProducer producer = (ActiveMQMessageProducer)session.createProducer(queue);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);// 发送持久化消息
        // 6、创建3条消息
        for (int i = 1; i <= 5; i++) {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("第"+i+"条消息");
            textMessage.setJMSMessageID(UUID.randomUUID().toString());
            // 消息ID用于记录某条消息的发送状态
            String messageId = textMessage.getJMSMessageID();
            // 7、生产者发送消息,回调函数用于接收消息发送的结果
            producer.send(textMessage, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    // 消息发送成功回调函数
                    System.out.println("消息发送成功，消息ID："+messageId);
                }

                @Override
                public void onException(JMSException e) {
                    // 消息发送失败回调函数
                    System.out.println("消息发送失败，消息ID："+messageId);
                }
            });
        }
        // 8、关闭资源
        producer.close();
        session.close();
        connection.close();
    }
}
