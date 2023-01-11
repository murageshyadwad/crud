package com.QoreMigration;

import java.io.IOException;

import java.util.concurrent.ExecutionException;

import org.apache.beam.runners.dataflow.DataflowRunner;
import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.runners.direct.DirectRunner;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubMessage;
//import org.apache.beam.sdk.options.PipelineOptions.DirectRunner;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.QoreMigration.ServiceImpl.PubSubMsgToSQL;
import com.QoreMigration.ServiceImpl.PublisherService;
import com.QoreMigration.ServiceImpl.SubscribeAsyncandSyncExample;
import com.pubsub.demo.dataflow.PubsubMsgToPostgres;

@SpringBootApplication
public class QoreMigrationApplication {

	public static String instancename = "qpathways-dev:us-central1:qpathwaysdb-dev";
	public static final Logger logger = null;

//private static String miginstancename = instancename;

//	private static String rxinstancename = instancename;
	@Autowired
	static PublisherService publisherService;
	public static String qoreurl = "https://qore-dev.myqone.com";

//	private static String qdrugs_instancename = "qpathways-staging:us-central1:qpathways-staging";

	public static String username = "postgres";
	public static String password = "scroll!1234";

	public static void main(String[] args) throws IOException {
		final String projectId = "qpathways-dev";
		final String topicId = "qpm-publish1";
		final String subscriptionId = "qpm-publish-sub";
		final int numOfMessages = 10;

		//try {
			
				//PublisherService.publisherExample(projectId, topicId);
			
				// TODO Auto-generated catch block
				
			 //Used to send message to topic
			//SubscribeAsyncandSyncExample.subscribeSyncExample(projectId, subscriptionId, numOfMessages); //Receive Message from subsciber  Accepts only single request at a time and we can limit noMessages   

			//SubscribeAsyncandSyncExample.subscribeAsyncExample(projectId, subscriptionId); //Receive Message from subsciber  multiple request  at a time

		//} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}



	 try {
			DataflowPipelineOptions options = PipelineOptionsFactory.as(DataflowPipelineOptions.class);
			options.setRunner(DirectRunner.class);// mandatory//DataflowRunner//DirectRunner
			options.setProject("qpathways-dev");// mandatory
			options.setStreaming(true);// depneds on ur use case
			options.setTempLocation("gs://qpm-demo/temp"); // mandatory 
			options.setStagingLocation("gs://qpm-demo/staging");// mandatory
			options.setRegion("us-central1");// mandatory
//      options.setNumWorkers(4);
			options.setMaxNumWorkers(2);// optional but i would recommend to define it before deploying
			options.setWorkerMachineType("n1-standard-1");// optional but by default it will use n1-standard-1 //larger
//		    options.setUpdate(true);											// - n2-standard-4
			options.setJobName("qpmdocs8-job");// optional
			options.setNetwork("triarqhealth-dev");
			options.setSubnetwork("https://www.googleapis.com/compute/v1/projects/qpathways-dev/regions/us-central1/subnetworks/vm-sql");
//      options.setGcpCredential(GoogleCredentials.fromStream(new FileInputStream("F:\\Working Folder\\Java\\MyProjects\\Dataflow\\pubsub_dev.json")).createScoped(scopes));
//      options.setServiceAccount("qoremigration@qpathways-dev.iam.gserviceaccount.com");



			Pipeline pipeline = Pipeline.create(options);
			// Pull data from PubSub
			PCollection<PubsubMessage> pubsubMessage = pipeline.apply("ReadMessages", PubsubIO
					.readMessagesWithAttributes().fromSubscription("projects/qpathways-dev/subscriptions/qpm-publish-sub"));
			//System.out.println(pubsubMessage);
			//pubsubMessage.apply(ParDo.of(new PubsubMsgToPostgres()));

			
			
			PCollection<Integer> writeResult;
			writeResult = pubsubMessage.apply(ParDo.of(new PubsubMsgToPostgres()));
			
//			System.out.println("instancename : " + instancename);
			pipeline.run().waitUntilFinish();
		} catch (Exception e) {
			System.out.println("exception : " + e.getMessage());
		}
	}

}
