package com.QoreMigration.ServiceImpl;

import com.google.cloud.pubsub.v1.AckReplyConsumer;

import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.google.cloud.pubsub.v1.stub.GrpcSubscriberStub;
import com.google.cloud.pubsub.v1.stub.SubscriberStub;
import com.google.cloud.pubsub.v1.stub.SubscriberStubSettings;
import com.google.pubsub.v1.AcknowledgeRequest;
import com.google.pubsub.v1.PullRequest;
import com.google.pubsub.v1.PullResponse;
import com.google.pubsub.v1.ReceivedMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
@Service
public class SubscribeAsyncandSyncExample {
//  public static void main(String... args) throws Exception {
//    // TODO(developer): Replace these variables before running the sample.
//	  String projectId = "qpathways-dev";
//		String topicId = "qpm-publish1";
//		String subscriptionId="qpm-publish-sub";
//
//    subscribeAsyncExample(projectId, subscriptionId);
	private static final Logger LOG = Logger.getLogger(SubscribeAsyncandSyncExample.class.getName());
	
	public static void subscribeAsyncExample(String projectId, String subscriptionId) {
		ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);
		
	
		// Instantiate an asynchronous message receiver.
		MessageReceiver receiver = (PubsubMessage message, AckReplyConsumer consumer) -> {
			// Handle incoming message, then ack the received message.
			System.out.println("Id: " + message.getMessageId());
			System.out.println("Data: " + message.getData().toStringUtf8());
			 
			consumer.ack();
		};
		

		Subscriber subscriber = null;
		try {
			subscriber = Subscriber.newBuilder(subscriptionName, receiver).build();
			// Start the subscriber.
			subscriber.startAsync().awaitRunning();
			System.out.printf("Listening for messages on %s:\n", subscriptionName.toString());
			
			// Allow the subscriber to run for 30s unless an unrecoverable error occurs.
			subscriber.awaitTerminated(60, TimeUnit.SECONDS);
		} catch (TimeoutException timeoutException) {
			// Shut down the subscriber after 30s. Stop receiving messages.
			subscriber.stopAsync();
		}
	}

	public static void subscribeSyncExample(String projectId, String subscriptionId, Integer numOfMessages)
			throws IOException {
		SubscriberStubSettings subscriberStubSettings = SubscriberStubSettings.newBuilder()
				.setTransportChannelProvider(SubscriberStubSettings.defaultGrpcTransportProviderBuilder()
						.setMaxInboundMessageSize(20 * 1024 * 1024) // 20MB (maximum message size).
						.build())
				.build();

		try (SubscriberStub subscriber = GrpcSubscriberStub.create(subscriberStubSettings)) {
			String subscriptionName = ProjectSubscriptionName.format(projectId, subscriptionId);
			PullRequest pullRequest = PullRequest.newBuilder().setMaxMessages(numOfMessages)
					.setSubscription(subscriptionName).build();

			// Use pullCallable().futureCall to asynchronously perform this operation.
			PullResponse pullResponse = subscriber.pullCallable().call(pullRequest);
			
			// Stop the program if the pull response is empty to avoid acknowledging
			// an empty list of ack IDs.
			if (pullResponse.getReceivedMessagesList().isEmpty()) {
				System.out.println("No message was pulled. Exiting.");
				return;
			}

			List<String> ackIds = new ArrayList<>();
			for (ReceivedMessage message : pullResponse.getReceivedMessagesList()) {
				// Handle received message
					
				
				ackIds.add(message.getAckId());

			}

			// Acknowledge received messages.
			AcknowledgeRequest acknowledgeRequest = AcknowledgeRequest.newBuilder().setSubscription(subscriptionName)
					.addAllAckIds(ackIds).build();

			// Use acknowledgeCallable().futureCall to asynchronously perform this
			// operation.
			subscriber.acknowledgeCallable().call(acknowledgeRequest);
			System.out.println(pullResponse.getReceivedMessagesList());
			System.out.println("Received Messages:"+pullResponse.getReceivedMessagesList().size());
		}
		

		

//		public static void subscribeWithExactlyOnceConsumerWithResponseExample(String projectId, String subscriptionId) {
//			    ProjectSubscriptionName subscriptionName =
//			        ProjectSubscriptionName.of(projectId, subscriptionId);
//
//			    // Instantiate an asynchronous message receiver using `AckReplyConsumerWithResponse`
//			    // instead of `AckReplyConsumer` to get a future that tracks the result of the ack call.
//			    // When exactly once delivery is enabled on the subscription, the message is guaranteed
//			    // to not be delivered again if the ack future succeeds.
//			    MessageReceiverWithAckResponse receiverWithResponse = (PubsubMessage message, AckReplyConsumerWithResponse consumerWithResponse) -> {
//			          try {
//			            // Handle incoming message, then ack the message, and receive an ack response.
//			            System.out.println("Message received: " + message.getData().toStringUtf8());
//			            Future<AckResponse> ackResponseFuture = consumerWithResponse.ack();
//
//			            // Retrieve the completed future for the ack response from the server.
//			            AckResponse ackResponse = ackResponseFuture.get();
//
//			            switch (ackResponse) {
//			              case SUCCESSFUL:
//			                // Success code means that this MessageID will not be delivered again.
//			                System.out.println("Message successfully acked: "  message.getMessageId());
//			                break;
//			              case INVALID:
//			                System.out.println(
//			                    "Message failed to ack with a response of Invalid. Id: "
//			                        + message.getMessageId());
//			                break;
//			              case PERMISSION_DENIED:
//			                System.out.println(
//			                    "Message failed to ack with a response of Permission Denied. Id: "
//			                        + message.getMessageId());
//			                break;
//			              case FAILED_PRECONDITION:
//			                System.out.println(
//			                    "Message failed to ack with a response of Failed Precondition. Id: "
//			                        + message.getMessageId());
//			                break;
//			              case OTHER:
//			                System.out.println(
//			                    "Message failed to ack with a response of Other. Id: "
//			                        + message.getMessageId());
//			                break;
//			              default:
//			                break;
//			            }
//			          } catch (InterruptedException | ExecutionException e) {
//			            System.out.println(
//			                "MessageId: " + message.getMessageId() + " failed when retrieving future");
//			          } catch (Throwable t) {
//			            System.out.println("Throwable caught" + t.getMessage());
//			          }
//			        };
//
//			    Subscriber subscriber = null;
//			    try {
//			      subscriber = Subscriber.newBuilder(subscriptionName, (MessageReceiver) receiverWithResponse).build();
//			      // Start the subscriber.
//			      subscriber.startAsync().awaitRunning();
//			      System.out.printf("Listening for messages on %s:\n", subscriptionName.toString());
//			      // Allow the subscriber to run for 30s unless an unrecoverable error occurs.
//			      subscriber.awaitTerminated(30, TimeUnit.SECONDS);
//			    } catch (TimeoutException timeoutException) {
//			      // Shut down the subscriber after 30s. Stop receiving messages.
//			      subscriber.stopAsync();
//			    }
//			  }
//			}

	

	
	}
	
}
