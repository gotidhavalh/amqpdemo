package com.rm.amqpdemo.service;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;

import com.rm.amqpdemo.domain.MyDomain;


public class HandlerService {

	@RabbitListener(queues = "${amqp.queue}")
	public void consumeMessageFromDlrRoute(final Message<?> message)
	{
		System.out.println(" message - "+message);
		try {

			ByteArrayInputStream in = new ByteArrayInputStream((byte[]) message.getPayload());
		    ObjectInputStream is = new ObjectInputStream(in);
		    MyDomain domain = (MyDomain) is.readObject();
		    
		    // code changes related to this service.
		    
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
