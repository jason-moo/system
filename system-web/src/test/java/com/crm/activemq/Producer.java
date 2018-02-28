package com.crm.activemq;


import javax.jms.*;

/**
 * Created by jason_moo on 2018/2/11.
 */
public class Producer {

//    public static void main(String[] args) throws Exception{
//        ConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
//        Connection connection = factory.createConnection();
//        connection.start();
//        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
////        Destination destination = session.createQueue("text-msg");
//        Destination destination = session.createTopic("daa");
//        MessageProducer producer = session.createProducer(destination);
//        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
//        TextMessage textMsg = session.createTextMessage("呵呵");
//        for(int i = 0 ; i < 100 ; i ++){
//            //发送一条消息
//            producer.send(textMsg);
//        }
//        System.out.println("发送消息成功");
//        //即便生产者的对象关闭了，程序还在运行哦
//        producer.close();
//    }
}
