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
 * @Description: ����ʽ��Ϣ
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
			 
	         //�ڶ�����ͨ��ConnectionFactory�����������Ǵ���һ��Connection���ӣ����ҵ���Connection��start�����������ӣ�ConnectionĬ���ǹرյġ�
	         Connection connection = connectionFactory.createConnection();
	         connection.start();
	         
	         //��������ͨ��Connection���󴴽�Session�Ự�������Ļ������󣩣����ڽ�����Ϣ����������1Ϊ�Ƿ����������񣬲�������2Ϊǩ��ģʽ��һ�����������Զ�ǩ�ա�
	         Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
	         
	         //���Ĳ���ͨ��Session����Destination����ָ����һ���ͻ�������ָ��������ϢĿ���������Ϣ��Դ�Ķ�����PTPģʽ�У�Destination������Queue�����У���Pub/Subģʽ��Destination������Topic�����⡣�ڳ����п���ʹ�ö��Queue��Topic��
	         Destination destination = session.createQueue("first");
	         
	         //���岽��������Ҫͨ��Session���󴴽���Ϣ�ķ��ͺͽ��ն��������ߺ������ߣ�MessageProducer/MessageConsumer��
	         MessageProducer producer = session.createProducer(destination);
	         //6.ʹ��session������Ϣ������ʹ��TEXT���͵���Ϣ
	         TextMessage textMessage = session.createTextMessage("hello world!");
	         //7.�����߷�����Ϣ
	         producer.send(textMessage);
	         //8.�ύ����
	         session.commit();
	         //9.�ر���Դ
	         session.close();
	         connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
         
	}
}
