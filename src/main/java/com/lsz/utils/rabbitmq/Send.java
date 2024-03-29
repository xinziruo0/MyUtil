package com.lsz.utils.rabbitmq;

 import java.io.IOException;  
import java.net.Inet4Address;
  
import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
  
public class Send {  
    private final static String QUEUE_NAME = "hello";  
  
    public static void main(String[] args) throws IOException {  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("192.168.43.142");
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
  
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);  
        String message = "Hello World!1";  
        for(int i = 0;i<1000000;i++)
        	channel.basicPublish("", QUEUE_NAME, null, (message+i).getBytes());  
        System.out.println(" [x] Sent '" + message + "'");  
  
        channel.close();  
        connection.close();  
    }  
}  