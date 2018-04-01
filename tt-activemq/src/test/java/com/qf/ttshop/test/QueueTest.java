package com.qf.ttshop.test;

/**
 * User: DHC
 * Date: 2018/1/25
 * Time: 15:30
 * Version:V1.0
 */
//public class QueueTest {
//    //生产者
//    @Test
//    public void testProducer() throws Exception{
//        //1 创建连接工厂
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://101.132.38.253:61616");
//        //2 获取连接
//        Connection connection = connectionFactory.createConnection();
//        //3 开启连接
//        connection.start();
//        //4 获取会话
//        //如果第一个参数为true的话，第二个参数可以忽略
//        //如果第一个参数为false的话，第二个可以选择自动应答，一般都选择自动应答，不需要干预
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        //5 通过会话创建queue
//        Queue queue = session.createQueue("mysecondqueue");
//        //6 创建生产者
//        MessageProducer producer = session.createProducer(queue);
//        //7 创建要发送的消息
//        Message message  = session.createTextMessage("hello activemq queue");
//        //8 发送消息
//        producer.send(message);
//        //9  释放资源
//        producer.close();
//        session.close();
//        connection.close();
//    }
//
//    //消费者
//    @Test
//    public void testConsumer() throws Exception{
//        //1 创建连接工厂
//        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://10.31.157.27:61616");
//        //2 获取连接
//        Connection connection = connectionFactory.createConnection();
//        //3 开启连接
//        connection.start();
//        //4 获取会话
//        //如果第一个参数为true的话，第二个参数可以忽略
//        //如果第一个参数为false的话，第二个可以选择自动应答，一般都选择自动应答，不需要干预
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        //5 通过会话创建queue
//        Queue queue = session.createQueue("myfirstqueue");
//        //6 创建消费者
//        MessageConsumer consumer = session.createConsumer(queue);
//        //7 接收消息
//        consumer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                try {
//                    TextMessage textMessage = (TextMessage)message;
//                    String text = textMessage.getText();
//                    System.out.println(text);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        });
//        System.in.read();
//        //8 释放资源
//        consumer.close();
//        session.close();
//        connection.close();
//    }
//}
