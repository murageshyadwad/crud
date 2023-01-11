package com.QoreMigration.ServiceImpl;

import java.io.IOException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.PushConfig;
import com.google.pubsub.v1.Subscription;
import com.google.pubsub.v1.SubscriptionName;
import com.google.pubsub.v1.TopicName;
//import com.pubsub.demo.model.PubSubModel;

@Service
public class PublisherService {

	public static void publisherExample(String projectId, String topicId)
			throws IOException, ExecutionException, InterruptedException {
		TopicName topicName = TopicName.of(projectId, topicId);

		Publisher publisher = null;
		try {
			// Create a publisher instance with default settings bound to the topic
			publisher = Publisher.newBuilder(topicName).build();
			
for (int i = 0; i < 1010; i++) { {
	String message = "{\r\n" + 
			"  \"ausid\": \"MHPBIOTECH01\",\r\n" + 
			"  \"neobid\": 0,\r\n" + 
			"  \"sideno\": \"\",\r\n" + 
			"  \"sstate\": \"\",\r\n" + 
			"  \"bsorted\": true,\r\n" + 
			"  \"ncaseid\": 0,\r\n" + 
			"  \"nstatus\": 2,\r\n" + 
			
			"  \"schargestraydescription\": \"Surgery Charges\",\r\n" + 
			"  \"smedicaidresubmissioncode\": \"\"\r\n" + 
			"}";
			ByteString data = ByteString.copyFromUtf8(message);
			PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

			// Once published, returns a server-assigned message id (unique within the
			// topic)
			ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
		
			System.out.println("Message sent :" +pubsubMessage.toString());
			String messageId = messageIdFuture.get();
			System.out.println("Published message ID: " + messageId);
}
}
} finally {
			if (publisher != null) {
				// When finished with the publisher, shutdown to free up resources.
				publisher.shutdown();
				publisher.awaitTermination(1, TimeUnit.MINUTES);
				System.out.println("1010 Messages Published");
			}
		}

		//createPullSubscriptionExample(projectId, "qpm-publish-sub", topicId);
	}

//	public static void createPullSubscriptionExample(
//		      String projectId, String subscriptionId, String topicId) throws IOException {
//		    try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
//		      TopicName topicName = TopicName.of(projectId, topicId);
//		      SubscriptionName subscriptionName = SubscriptionName.of(projectId, subscriptionId);
//		      // Create a pull subscription with default acknowledgement deadline of 10 seconds.
//		      // Messages not successfully acknowledged within 10 seconds will get resent by the server.
//		      Subscription subscription = subscriptionAdminClient.createSubscription(
//		              subscriptionName, topicName, PushConfig.getDefaultInstance(), 10);
//		      System.out.println("Created pull subscription: " + subscription.getName());
//		    }
}
