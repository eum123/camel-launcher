package net.mj.camel.launcher.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
	public static void main(String[] args) throws Exception {
		
		String QUEUE_NAME = "TEST";
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("172.70.11.71");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "Hello World!";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");
		
		channel.close();
		connection.close();
	}
}
