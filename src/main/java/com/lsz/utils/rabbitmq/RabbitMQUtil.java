package com.lsz.utils.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class RabbitMQUtil {
	private static ConnectionFactory factory = new ConnectionFactory(); 
	public static ThreadLocal connectionThread = new ThreadLocal(); 
    {
    	factory.setHost("192.168.43.141");  
    }
	
    /**
     * 
     * @return client connection
     * @throws IOException
     */
    public static Connection getConnection() throws IOException{
    	 Connection connection = (Connection)connectionThread.get();
	     if(connection == null){
	    	 connection = factory.newConnection();
	    	 connectionThread.set(connection); 
	     }
    	 
	     return connection;
	}
    /**
     * close Connection
     * @throws IOException
     */
    public static void closeConnection() throws IOException{
    	 Connection connection = (Connection)connectionThread.get();
    	 connectionThread.set(null);
	     if(connection != null){
	    	 connection.close();
	     }
    	 
    }
    /**
     * 
     * @return client thransport channel
     * @throws IOException
     */
    public static Channel getChannel() throws IOException{
    	return getConnection().createChannel();
    }
  
    /**
     * close channel
     * @param channel
     * @throws IOException
     */
    public static void closeChannel(Channel channel) throws IOException{
    	if(channel != null){
    		channel.close();
    	}
    }
}
