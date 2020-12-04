package jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: rj
 * @Date: 2020-11-27 11:11
 * @Version: 1.0
 */
public class JmsTopicConsumer {
    public static void main(String[] args) throws Exception{
        String brokerUrl = "nio://8.131.71.92:61618";
        String topicName = "topic消息队列";
        // 1、创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        // 2、创建连接
        Connection connection = connectionFactory.createConnection();
        // 3、设置持久订阅消费者ID
        connection.setClientID("持久化消费者ID1");
        // 4、创建会话session  第一个参数：是否开启事务；第二个参数：应答模式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4、创建目的地
        Topic topic = session.createTopic(topicName);
        // 5、创建持久订阅者
        TopicSubscriber c1 = session.createDurableSubscriber(topic, "持久化topic消费者1");
        // 6、启动连接
        connection.start();
        // 7、设置异步非阻塞消息监听器获取消息
        c1.setMessageListener((message)->{
            TextMessage message1 = (TextMessage) message;
            try {
                System.out.println("我是topic消费者："+message1.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
        System.in.read();
        // 8、关闭资源
        c1.close();
        session.close();
        connection.close();
    }
}
