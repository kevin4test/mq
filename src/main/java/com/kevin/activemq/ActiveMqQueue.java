package com.kevin.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

/**
 * @ClassName: ActiveMqQueue
 * @Description: 队列式消息
 * @author LG
 * @date 2020-02-26 01:46:34
 */
public class ActiveMqQueue {

	@Test
	public void send() {
		try {
			 ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
	                 ActiveMQConnectionFactory.DEFAULT_USER, 
	                 ActiveMQConnectionFactory.DEFAULT_PASSWORD, 
	                 "failover:(tcp://192.168.146.130:61616,tcp://192.168.192.131:61616,tcp://192.168.192.132:61616)?Randomize=false");
			 
	         //第二步：通过ConnectionFactory工厂对象我们创建一个Connection连接，并且调用Connection的start方法开启连接，Connection默认是关闭的。
	         Connection connection = connectionFactory.createConnection();
	         connection.start();
	         
	         //第三步：通过Connection对象创建Session会话（上下文环境对象），用于接收消息，参数配置1为是否启用是事务，参数配置2为签收模式，一般我们设置自动签收。
	         Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
	         
	         //第四步：通过Session创建Destination对象，指的是一个客户端用来指定生产消息目标和消费消息来源的对象，在PTP模式中，Destination被称作Queue即队列；在Pub/Sub模式，Destination被称作Topic即主题。在程序中可以使用多个Queue和Topic。
	         Destination destination = session.createQueue("first");
	         
	         //第五步：我们需要通过Session对象创建消息的发送和接收对象（生产者和消费者）MessageProducer/MessageConsumer。
	         MessageProducer producer = session.createProducer(destination);
	         //6.使用session创建消息，这里使用TEXT类型的消息
	         TextMessage textMessage = session.createTextMessage("hello world!");
	         //7.生产者发送消息
	         producer.send(textMessage);
	         //8.提交事务
	         session.commit();
	         //9.关闭资源
	         session.close();
	         connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
         
	}
}
