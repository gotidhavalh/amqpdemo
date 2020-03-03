package com.rm.amqpdemo.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class AmqpExchangeConfig {
	
	@Autowired
	private RabbitTemplate template;
	
	@Bean
	AsyncRabbitTemplate shortUrlAsyncTemplate() {
		template.setUseDirectReplyToContainer(false);
		template.setUseTemporaryReplyQueues(true);
		return new AsyncRabbitTemplate(template);
	}

	
	@Value("${amqp.exchange}")
	private String exchange;
	
	@Bean
	public DirectExchange exchange() {
		return new DirectExchange(exchange, true, false);
	}



}