package com.demo.rabbitmq.workqueues;

import com.demo.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 15:57 2021/4/9
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class Provider {
		//生产消息
		@Test
		public void testSendMessage() throws IOException, TimeoutException {
				//获取连接对象
				Connection connection = RabbitMQUtils.getConnection();
				//获取连接中通道
				Channel channel = connection.createChannel();
				//通道绑定对应消息队列
				channel.queueDeclare("work", true, false, false, null);
				//发布消息
				for (int i=0;i<10;i++){
						channel.basicPublish("", "work", null, (i+"hello work").getBytes());
				}
				RabbitMQUtils.closeConnectionAndChanel(channel,connection);
		}


}
