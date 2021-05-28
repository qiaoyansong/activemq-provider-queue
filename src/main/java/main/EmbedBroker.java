package main;

import org.apache.activemq.broker.BrokerService;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/5/28 14:59
 * description：一个内嵌式的broker
 */
public class EmbedBroker {


    public static void main(String[] args) {
        BrokerService brokerService = new BrokerService();
        try {
            // 这里不再是Linux的地址，而是我本机的地址
            brokerService.addConnector("tcp://localhost:61616");
            brokerService.setUseJmx(true);
            brokerService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
