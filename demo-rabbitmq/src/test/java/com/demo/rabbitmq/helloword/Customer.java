package com.demo.rabbitmq.helloword;

import com.demo.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 16:45 2021/4/9
 */
@Slf4j
public class Customer {
		//消费消息
		public static void main(String[] args) throws IOException, TimeoutException {
				//获取连接对象
				Connection connection = RabbitMQUtils.getConnection();
				//获取连接中通道
				Channel channel = connection.createChannel();
				//通道绑定对应消息队列
				channel.queueDeclare("hello", false, false, false, null);
				//消费消息
				channel.basicConsume("hello", true, new DefaultConsumer(channel) {
						@Override
						public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
								super.handleDelivery(consumerTag, envelope, properties, body);
								log.info("body,{}", new String(body));
						}
				});
				//RabbitMQUtils.closeConnectionAndChanel(channel,connection);
		}


}
