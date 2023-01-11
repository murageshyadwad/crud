package com.messagepubsub.service;

import java.io.IOException;


import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.common.collect.ImmutableMap;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import com.messagepubsub.model.PubSubModel;

@Service
public class PublisherService {

	public static void publisherExample(String projectId, String topicId,String omessage)
		      throws IOException, ExecutionException, InterruptedException {
		    TopicName topicName = TopicName.of(projectId, topicId);

		    Publisher publisher = null;
		    try {
		      // Create a publisher instance with default settings bound to the topic
		      publisher = Publisher.newBuilder(topicName).build();

		      String message = omessage;
		     
		      ByteString data = ByteString.copyFromUtf8(message);
		     
		   
		     PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).putAllAttributes(ImmutableMap.of("MessageID",topicName.toString())).build(); 
		     
		     // PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();
		     

		      // Once published, returns a server-assigned message id (unique within the topic)
		      ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
		     
		      String messageId = messageIdFuture.get();
		     
		      System.out.println("Published message ID: " + messageId);
		     
		    } finally {
		      if (publisher != null) {
		        // When finished with the publisher, shutdown to free up resources.
		        publisher.shutdown();
		        publisher.awaitTermination(1, TimeUnit.MINUTES);
		      }
		    }
		  }

	
	
		  }

