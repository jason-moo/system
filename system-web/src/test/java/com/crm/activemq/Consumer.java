package com.crm.activemq;

import javax.jms.*;

/**
 * Created by jason_moo on 2018/2/11.
 */
public class Consumer {

//    public static void main(String[] args) throws Exception{
//        ConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
//        Connection connection = factory.createConnection();
//        connection.start();
//        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
////        Destination destination = session.createQueue("text-msg");
//        Destination destination = session.createTopic("daa");
//        MessageConsumer consumer = session.createConsumer(destination);
//        consumer.setMessageListener(new MessageListener() {
//
//            @Override
//            public void onMessage(Message message){
//                try {
//                    TextMessage textMessage = (TextMessage)message;
//                    System.out.println(textMessage.getText());
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//
//        });
//
//    }
}
