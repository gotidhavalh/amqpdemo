package com.rm.amqpdemo;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import com.rm.amqpdemo.domain.MyDomain;

@SpringBootTest
class AmqpdemoApplicationTests {

	/**
	 * @throws Exception
	 */
	@Test
	public void testShrotUrlDlrByCode() throws Exception {
  	
	  	RabbitTemplate template = new RabbitTemplate();
	  	CachingConnectionFactory factory = new CachingConnectionFactory();
	  	factory.setUsername("admin");
	  	factory.setPassword("rabbitpass");
	  	factory.setHost("localhost");
	  	factory.setPort(5672);
	  	template.setConnectionFactory(factory);
  	
	  	MyDomain domain = new MyDomain();
	  	domain.setId(1);
	
	  	ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(domain);
	     
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setCorrelationId("1f4a13d0-f3b4-4b4b-98b4-50e9d75d6032");
		Message message = new Message(out.toByteArray(), messageProperties);
		template.convertSendAndReceive("Exchange", "Route", message);
		template.stop();
  }


}
