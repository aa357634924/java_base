package jms;

import org.apache.activemq.broker.BrokerService;

/**
 * 内嵌一个broker
 * @Author: rj
 * @Date: 2020-11-27 14:18
 * @Version: 1.0
 */
public class DefineBroker {
    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61617");
        brokerService.start();
    }
}
