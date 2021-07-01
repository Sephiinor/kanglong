package com.sephinor.kanglong.kanglongrabbit;

import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProduceTest {

    private String QUEUE_NAME = "myqueue";
    private  String message = "Hello world";

    @Test
    public  void sendMessage()  {

        // 定义链接工厂
        ConnectionFactory factory = new ConnectionFactory();

        createFactory(factory);

        Connection connection ;
        try {
            connection = factory.newConnection();
            //从连接中创建通道,这是完成大部分API的地方
            Channel channel =connection.createChannel();
            //声明(创建)队列,必须声明队列才能够发送消息,我们可以把消息发送到队列中
            //声明一个队列是幂等的 - 只有当他不存在时才会被创建

            channel.queueDeclare(QUEUE_NAME,true,false,false,null);


            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());

            System.err.println("已发送");

            //关闭通道和链接
            channel.close();
            connection.close();
        } catch (Exception e){
            if(e instanceof IOException ){
                e.printStackTrace();
                System.out.println(e);
            }else if(e instanceof  TimeoutException){
                e.printStackTrace();
                System.out.println(e);
            }

        }

    }


    @Test
    public void recvMessage(){
        // 定义链接工厂
        ConnectionFactory factory = new ConnectionFactory();

        createFactory(factory);

        Connection connection ;

        try {
            connection = factory.newConnection();

            Channel channel = connection.createChannel();

            DefaultConsumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    super.handleDelivery(consumerTag, envelope, properties, body);
                    String msg = new String(body) ;
                    System.out.println("收到消息: "+msg);
                }
            };

            channel.basicConsume(QUEUE_NAME,false,consumer);

            //主线程不能结束
            Thread.sleep(300000);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void createFactory(ConnectionFactory factory){

        factory.setHost("127.0.0.1");    // ip

        factory.setPort(5672);  //port

        factory.setVirtualHost("/");  //虚拟主机

        factory.setUsername("guest");

        factory.setPassword("guest");
    }
}
