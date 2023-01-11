package com.QoreMigration.ServiceImpl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import com.QoreMigration.Model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.subscriber.PubSubSubscriberTemplate;
import com.google.cloud.spring.pubsub.reactive.PubSubReactiveFactory;
import com.google.cloud.spring.pubsub.support.converter.ConvertedAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import com.google.cloud.spring.pubsub.support.converter.PubSubMessageConverter;

@Service
public class PublishPullCall {
	Logger logger = Logger.getLogger(PublishPullCall.class.getName());
	@Bean
	public PubSubMessageConverter pubSubMessageConverter() {
	    return new JacksonPubSubMessageConverter(new ObjectMapper());
	}
	
	@Bean
	ThreadPoolTaskScheduler pubsubSubscriberThreadPool() {
	  return new ThreadPoolTaskScheduler();
	}
	
	@Bean
	ApplicationRunner runner(PubSubSubscriberTemplate subscriberTemplate) {
	  return (args) -> {
	   List<ConvertedAcknowledgeablePubsubMessage<Order>> msgs = subscriberTemplate
	        .pullAndConvert("qpm-publish-sub", 1, true, Order.class);
	    msgs.forEach(msg -> {
	      logger.info(msg.getPayload().getId());
	      msg.ack();
	    });
	  };
	}
	@Bean
	ApplicationRunner subscribeRunner(PubSubSubscriberTemplate subscriberTemplate) {
	  return (args) -> {
	    subscriberTemplate.subscribeAndConvert("qpm-publish-sub", msg -> {
	      System.out.println(msg.getPayload().getId());
	      msg.ack();
	    }, Order.class);
	  };
	}
	
//	@Bean
//	ApplicationRunner reactiveSubscriber(PubSubReactiveFactory reactiveFactory, PubSubMessageConverter converter) {
//	  return (args) -> {
//	    reactiveFactory.poll("orders-subscription", 250L)
//	      // Convert a JSON payload into an object
//	      .map(msg -> converter.fromPubSubMessage(msg.getPubsubMessage(), Order.class))
//	      .doOnNext(order -> System.out.println(order.getId()))
//	      // Mannually acknowledge the message
//	      .doOnNext(AcknowledgeablePubsubMessage::ack);
//	      .subscribe();
//	  };
//	}
}
