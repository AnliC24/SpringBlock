package tern.block.web.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



public class mqConfig {
	
	    @Bean
	    public Queue helloQueue(){
	        return new Queue("hello");
	    }
	    

//	    @Bean
//	    public RabbitAdmin rabbitTemplate(ConnectionFactory connectionFactory) {
//	        return new RabbitAdmin(connectionFactory);
//	    }
//
//	    @Bean
//	    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
//	        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//	        template.setMessageConverter(messageConverter);
//	        return template;
//	    }
//
//	 
//
//	    @Bean
//	    public MessageConverter messageConverter() {
//	        return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter());
//	    }



}
