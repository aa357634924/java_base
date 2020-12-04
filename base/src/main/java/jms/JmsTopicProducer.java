package jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: rj
 * @Date: 2020-11-27 11:09
 * @Version: 1.0
 */
public class JmsTopicProducer {
    public static void main(String[] args) throws Exception{
        String brokerUrl = "nio://8.131.71.92:61618";
        String topicName = "topic消息队列";
        // 1、创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        // 2、创建连接
        Connection connection = connectionFactory.createConnection();
        // 3、创建会话session  第一个参数：是否开启事务；第二个参数：应答模式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4、创建目的地
        Topic topic = session.createTopic(topicName);
        // 5、创建消息生产者
        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);//持久化
        connection.start();
        // 6、创建3条消息
        for (int i = 1; i <= 5; i++) {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("第"+i+"条消息");
            // 7、生产者发送消息
            producer.send(textMessage);
            textMessage.acknowledge();
        }
        // 8、关闭资源
//        session.commit();
        producer.close();
        session.close();
        connection.close();
    }
}
