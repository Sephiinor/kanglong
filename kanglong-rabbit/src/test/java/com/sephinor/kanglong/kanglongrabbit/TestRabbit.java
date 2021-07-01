package com.sephinor.kanglong.kanglongrabbit;

import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class TestRabbit {


    /**
     * 消息生产者
     */
    @Test
    public void sendMessage() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");	//rabbit-server ip
        factory.setPort(5672);				//rabbit-server port
        factory.setVirtualHost("/");		//虚拟主机
        factory.setUsername("guest");		//用户名
        factory.setPassword("guest");		//
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //channel.queueDeclare("myqueue" , false ,false ,false ,null) ;
        // 消息内容
        String message = "how are you?";
        channel.basicPublish("", "myqueue", null, message.getBytes());

        //关闭通道和连接
        channel.close();
        connection.close();
    }

    /**
     * 接受消息
     */
    @Test
    public void recvMessage() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");    //rabbit-server ip
        factory.setPort(5672);                //rabbit-server port
        factory.setVirtualHost("/");        //虚拟主机
        factory.setUsername("guest");        //用户名
        factory.setPassword("guest");        //
        Connection connection = factory.newConnection();

        //通道
        Channel ch = connection.createChannel() ;
        DefaultConsumer consumer = new DefaultConsumer(ch){
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body) ;
                System.out.println("收到消息 ： " + msg);
            }
        } ;
        //
        ch.basicConsume("myqueue" , false ,consumer) ;
        //主线程不能结束。
        Thread.sleep(300000);
    }
}
