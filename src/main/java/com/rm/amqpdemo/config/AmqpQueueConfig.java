package com.rm.amqpdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

import com.rm.amqpdemo.service.HandlerService;


@Configuration
@EnableIntegration
@IntegrationComponentScan
public class AmqpQueueConfig {

	@Autowired
	@Qualifier("exchange")
	private DirectExchange exchange;
	
	@Value("${amqp.queue}")
	private String queueName;
	
	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}
	
	@Value("${amqp.route}")
	private String routingKey;
	
	@Bean
	public Binding shortUrlDlrBinding() {
		return BindingBuilder.bind(queue()).to(exchange).with(routingKey);
	}
	
	@Bean
	public HandlerService handler() {
		return new HandlerService();
	}
}