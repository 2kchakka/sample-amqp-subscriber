package org._2kchakka.amqp;

import java.io.IOException;

import org._2kchakka.amqp.util.SubscriberUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Subscriber class responsible for receiving any message posted to a queue
 *
 */
public class Subscriber {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) throws IOException {

		Connection connection = null;
		Channel channel = null;
		try {
			// Creating connection to RabbitMQ server
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(SubscriberUtil
					.readFromResource("rabbit.server.host"));
			connection = factory.newConnection();

			// Creating channel - most of the API are here
			channel = connection.createChannel();

			// Declaring a queue - idempotent, i.e. created iff not already
			// created
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			System.out
					.println(" [*] Waiting for messages. To exit press CTRL+C");

			/**
			 *  Registering callback in the form of an instance of 
			 *  QueuingConsumer that buffers the messages until ready to be
			 *  used
			 */
			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(QUEUE_NAME, true, consumer);

			while (true) {
				/**
				 *  Call to nextDelivery blocks the execution until another
				 *  message has been delivered from the server 
				 */
				
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());
				System.out.println(" [x] Received '" + message + "'");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (channel != null)
				channel.close();
			if (connection != null)
				connection.close();
		}
	}
}
