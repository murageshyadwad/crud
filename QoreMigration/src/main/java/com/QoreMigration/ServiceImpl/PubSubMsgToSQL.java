package com.QoreMigration.ServiceImpl;

import org.apache.beam.sdk.io.gcp.pubsub.PubsubMessage;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.DoFn.ProcessContext;
import org.apache.beam.sdk.transforms.DoFn.ProcessElement;
import org.joda.time.DateTime;

import com.google.gson.Gson;
import com.pubsub.demo.DemoApplication;
import com.pubsub.demo.dataflow.MsgProcessor;

public class PubSubMsgToSQL extends DoFn<PubsubMessage, Integer> {

	private static final long serialVersionUID = 1L;

	@ProcessElement
	public void processElement(ProcessContext c) {
		Integer rows = 0;
		PubsubMessage msg = null;
		
		DateTime publishtime;
		org.json.JSONArray jsonPaylod = null;
		MsgProcessor msgProc = null;
		try {
			
				msg = c.element();
				publishtime = c.timestamp().toDateTime();
				String payload = new String(msg.getPayload());
				Gson g = new Gson();
				String str = g.toJson(payload);
				System.out.println(payload);
				System.out.println("----------------------------------************************************--------------------------------------------------------");
				
				
				
			
			
			
//	            JSONArray  jsonArray = new JSONArray();
//	            jsonArray.add(payload);

			//String str1 = "[{\"No\":\"17\",\"Name\":\"Andrew\"},{\"No\":\"18\",\"Name\":\"Peter\"}, {\"No\":\"19\",\"Name\":\"Tom\"}]";
			//String str2 = str.replace("'\"'", "");
			///jsonPaylod = new org.json.JSONArray(payload);

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			msg = null;
			jsonPaylod = null;
			publishtime = null;
			msgProc = null;
		}
		// c.output(rows);
	}
}
