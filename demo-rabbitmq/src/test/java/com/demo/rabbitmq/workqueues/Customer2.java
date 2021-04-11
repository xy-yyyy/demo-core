package com.demo.rabbitmq.workqueues;

import com.demo.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 16:45 2021/4/9
 */
@Slf4j
public class Customer2 {
		//消费消息
		public static void main(String[] args) throws IOException, TimeoutException {
				//获取连接对象
				Connection connection = RabbitMQUtils.getConnection();
				//获取连接中通道
				Channel channel = connection.createChannel();
				//每次只能消费一条消息
				channel.basicQos(1);
				//通道绑定对应消息队列
				channel.queueDeclare("work", true, false, false, null);
				//消费消息
				channel.basicConsume("work", false, new DefaultConsumer(channel) {
						@SneakyThrows
						@Override
						public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
								super.handleDelivery(consumerTag, envelope, properties, body);
								log.info("body-2,{}", new String(body));
								//手动确认消息
								channel.basicAck(envelope.getDeliveryTag(),false);
								Thread.sleep(1000);
						}
				});
				//RabbitMQUtils.closeConnectionAndChanel(channel,connection);
		}


}
