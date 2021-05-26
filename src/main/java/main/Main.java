package main;

import util.ActiveMqUtil;

import javax.jms.*;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/5/26 9:39
 * description：
 */
public class Main {

    private static final String QUEUE_NAME = "queue1";
    private static final int MESSAGE_NUM = 6;
    public static void main(String[] args) {
        XAConnection connection = null;
        Session session = null;
        MessageProducer producer = null;
        try {
            connection = ActiveMqUtil.getConnection();
            connection.start();
            // 创建Session，有两个参数，分别是是否开启事务、签收
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建目的地（可以是队列也可以是Topic）
            Queue queue = session.createQueue(QUEUE_NAME);
            // 创建消息提供者
            producer = session.createProducer(queue);
            for (int i = 1; i <= MESSAGE_NUM; i++) {
                // 创建消息
                TextMessage textMessage = session.createTextMessage("message" + i);
                // 发送消息
                producer.send(textMessage);
            }
            System.out.println("发送结束");
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if(producer != null){
                try {
                    producer.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if(session != null){
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
