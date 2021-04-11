package com.demo.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;


/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 17:26 2021/4/9
 */
@Slf4j
public class RabbitMQUtils {
		private static ConnectionFactory connectionFactory;
		static{
				//创建连接mq的连接工厂对象
				connectionFactory=new ConnectionFactory();
				//设置连接rabbitmq主机
				connectionFactory.setHost("localhost");
				//设置端口号
				connectionFactory.setPort(5672);
				//设置连接哪个虚拟主机
				connectionFactory.setVirtualHost("/demo-rabbitmq");
				//设置虚拟主机的用户名密码
				connectionFactory.setUsername("demo-rabbitmq");
				connectionFactory.setPassword("123");
		}
		public static Connection getConnection() {
				try {
						return connectionFactory.newConnection();
				} catch (Exception e) {
						e.printStackTrace();
				}
				return null;
		}

		public static void closeConnectionAndChanel(Channel channel, Connection conn) {
				try {
						if (null != channel) channel.close();
						if (null != conn) conn.close();
				} catch (Exception e) {
						e.printStackTrace();
				}
		}


}
