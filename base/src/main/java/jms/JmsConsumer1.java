package jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * JMS 消费者
 * @Author: rj
 * @Date: 2020-11-27 9:24
 * @Version: 1.0
 */
public class JmsConsumer1 {
    public static void main(String[] args) throws JMSException, IOException {
        String brokerUrl = "nio://8.131.71.92:61618";
        String queueName = "第一个消息队列";
        // 1、创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        // 2、创建连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // 3、创建会话session  第一个参数：是否开启事务；第二个参数：应答模式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4、创建目的地
        Queue queue = session.createQueue(queueName);
        // 5、创建消息消费者
        MessageConsumer consumer = session.createConsumer(queue);
        // 设置异步非阻塞消息监听器获取消息
        consumer.setMessageListener((message)->{
            TextMessage message1 = (TextMessage) message;
            try {
                System.out.println("我是消费者1："+message1.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
        // 阻塞，放着未获取消息就结束了
        System.in.read();
        // 8、关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
