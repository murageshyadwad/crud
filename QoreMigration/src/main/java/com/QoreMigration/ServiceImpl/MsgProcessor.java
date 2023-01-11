package com.QoreMigration.ServiceImpl;



import java.math.BigInteger;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import org.slf4j.Logger;

import com.pubsub.demo.DemoApplication;
import com.pubsub.demo.dataflow.table_mapping;

public class MsgProcessor {

	Long MessageID;
	JSONArray jsonPaylod;
	String username;
	String password;
	String qoreurl;
	String instance;

	Map<String, List<table_mapping>> mappings;

	private String[] customProcessing = { "provider", "guarantor_mst", "allergyintolerance" };
	private String[] provColArr = { "sproviderfirstname", "sproviderfitstname", "sprovidermiddlename",
			"sproviderlastname", "sprovidernpi" };

	public MsgProcessor() {
		super();
	}

	public MsgProcessor(Long MessageID,JSONArray jsonPaylod, String username, String password, String qoreurl, String instance) {
		super();
		this.MessageID=MessageID;
		this.jsonPaylod = jsonPaylod;
		this.username = username;
		this.password = password;
		this.qoreurl = qoreurl;
		this.instance = instance;
	}

	public Long getMessageID() {
		return MessageID;
	}

	public void setMessageID(Long messageID) {
		MessageID = messageID;
	}

	public JSONArray getJsonPaylod() {
		return jsonPaylod;
	}

	public void setJsonPaylod(JSONArray jsonPaylod) {
		this.jsonPaylod = jsonPaylod;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQoreurl() {
		return qoreurl;
	}

	public void setQoreurl(String qoreurl) {
		this.qoreurl = qoreurl;
	}

	String remark = "";
	String errormsg = "";
	Boolean reprocess_foreignkey = false;

	public Integer processor(boolean isFromDataFlow, Logger logger) {
////        logger.info("Processing payload" + jsonPaylod);
		Integer rows = 0;
		String outcome = "success";
		System.out.println("json");
		System.out.println(jsonPaylod);
//        		ClientModel oclient = new ClientModel();
//        		if(jsonPaylod.has("Id") && jsonPaylod.get("Id") != null && !jsonPaylod.get("Id").toString().equals(""))
//      			{
//        			
//        			oclient.setId((Integer) jsonPaylod.get("Id"));
//      			}
//
//        		if(jsonPaylod.has("Name") && jsonPaylod.get("Name") != null)
//      			{
//        			oclient.setName(jsonPaylod.get("Name").toString());
//      			}
//        		if(jsonPaylod.has("Address") && jsonPaylod.get("Address") != null && !jsonPaylod.get("Address").toString().equals(""))
//      			{
//        			oclient.setAddress(jsonPaylod.get("Address").toString());
//      			}
//
//       		
//        		Connection conngrp = null;
//        		Statement statgrp = null;
//        		ResultSet resultgrp = null;
////        		boolean gprAdded = false;
//        		try 
//        		{
//        			conngrp = getConnection("BANQ_DEV", isFromDataFlow);
//        			statgrp = conngrp.createStatement();
//        		    PreparedStatement stmt=conngrp.prepareStatement("INSERT into br.clientdemo values(?,?,?)");
//        		    		 stmt.setLong(1,oclient.getId());
//        		    		stmt.setString(2,oclient.getName());
//        		    		stmt.setString(3,oclient.getAddress());
//        		    				
//        		    		int i=stmt.executeUpdate();  
//        		    		System.out.println(i+" records inserted"); 
//        		    		conngrp.close();  
//        		}
//        		catch(Exception ex)
//        		{
//        			System.out.println(ex.toString());
//        		}
//				finally {
//					
//				 if (conngrp!=null)
//				 {
//					 conngrp=null;
//				 }
//				}
		return rows;

//			oTransaction.setNtransactionproviderid((int) jsonPaylod.get("ntransactionproviderid"));
//			oTransaction.setNclinicid((int) jsonPaylod.get("nclinicid"));
//			oTransaction.setNhopitalizationdatefrom((int) jsonPaylod.get("nhopitalizationdatefrom"));
//			oTransaction.setNhopitalizationdateto((int) jsonPaylod.get("nhopitalizationdateto"));
//			oTransaction.setNaccidentdate((int) jsonPaylod.get("naccidentdate"));
//			oTransaction.setNauthorizationid((int) jsonPaylod.get("nauthorizationid"));
//			oTransaction.setNreferralid((int) jsonPaylod.get("nreferralid"));
//			oTransaction.setNlaststatusid((int) jsonPaylod.get("nlaststatusid"));
//			oTransaction.setNuserid((int) jsonPaylod.get("nuserid"));
//			oTransaction.setHeraccidentdate((int) jsonPaylod.get("heraccidentdate"));
//			oTransaction.setNchargesdaytrayid((int) jsonPaylod.get("nchargesdaytrayid"));
//			oTransaction.setDtcreatedate((Date) jsonPaylod.get("dtcreatedate"));
//			oTransaction.setDtmodifydate((Date) jsonPaylod.get("dtmodifydate"));
//			oTransaction.setDtvoiddate((Date) jsonPaylod.get("dtvoiddate"));
//			oTransaction.setNvoiduserid((int) jsonPaylod.get("nvoiduserid"));
//			oTransaction.setDtdayclosedon((Date) jsonPaylod.get("dtdayclosedon"));
//			oTransaction.setNdaycloseduserid((int) jsonPaylod.get("ndaycloseduserid"));
//			oTransaction.setNvoidclosedate((int) jsonPaylod.get("nvoidclosedate"));
//			oTransaction.setNvoidtrayid((int) jsonPaylod.get("nvoidtrayid"));
//			oTransaction.setNfeescheduleid((int) jsonPaylod.get("nfeescheduleid"));
//			oTransaction.setNreferralproviderid((int) jsonPaylod.get("nreferralproviderid"));
//			oTransaction.setNillnessdate((int) jsonPaylod.get("nillnessdate"));
//			oTransaction.setNpaccountid((int) jsonPaylod.get("npaccountid"));
//			oTransaction.setNguarantorid((int) jsonPaylod.get("nguarantorid"));
//			oTransaction.setNaccountpatientid((int) jsonPaylod.get("naccountpatientid"));
//			oTransaction.setNcaseid((int) jsonPaylod.get("ncaseid"));
//			oTransaction.setDtinittreatmentdate((Date) jsonPaylod.get("dtinittreatmentdate"));
//			oTransaction.setNreplacedbytransmasterid((int) jsonPaylod.get("nreplacedbytransmasterid"));
//			oTransaction.setNreplacingtransmasterid((int) jsonPaylod.get("nreplacingtransmasterid"));
//			oTransaction.setNclaimreportingcategoryid((int) jsonPaylod.get("nclaimreportingcategoryid"));
//			oTransaction.setNlastseendate((int) jsonPaylod.get("nlastseendate"));
//			oTransaction.setDtbox15date((Date) jsonPaylod.get("dtbox15date"));
//			}

//		Connection conngrp = null;
//		Statement statgrp = null;
//		ResultSet resultgrp = null;
////		boolean gprAdded = false;
//		try {
//			conngrp = getConnection("BANQ_DEV", isFromDataFlow);
//			statgrp = conngrp.createStatement();
//
//			long Randomno = GenerateNo();
//
//			PreparedStatement stmt1 = conngrp.prepareStatement("INSERT into public.PubSub_Metadata values(?,?,?,?,?)");
//			stmt1.setLong(1, Randomno);
//			stmt1.setLong(2, this.MessageID);
//			stmt1.setLong(3, 0);
//			stmt1.setString(4, jsonPaylod.toString());
//			stmt1.setBoolean(5, false);
//			int k = stmt1.executeUpdate();
//
//			for (int i = 0; i < jsonPaylod.length(); i++) 
//			{
//				JSONObject JsonObject = jsonPaylod.getJSONObject(i);
//
//				//bl_transaction_mst oTransaction = new bl_transaction_mst();
//				if (JsonObject.has("nTransactionID") && JsonObject.get("nTransactionID") != null
//						&& !JsonObject.get("nTransactionID").toString().equals("")) {
//					oTransaction.setNtransactionid((long) JsonObject.get("nTransactionID"));
//					oTransaction.setNmasterappointmentid((int) JsonObject.get("nMasterAppointmentID"));
//					oTransaction.setNappointmentid((int) JsonObject.get("nAppointmentID"));
//					oTransaction.setNvisitid((int) JsonObject.get("nVisitID"));
//					oTransaction.setNonsitedate((int) JsonObject.get("nOnsiteDate"));
//					if (JsonObject.has("nInjuryDate")) {
//						oTransaction.setNinjurydate((int) JsonObject.get("nInjuryDate"));
//					}
//					oTransaction.setNunabletoworkfromdate((int) JsonObject.get("nUnableToWorkFromDate"));
//					oTransaction.setNtransactiondate((int) JsonObject.get("nTransactionDate"));
//					oTransaction.setScasenoprefix((String) JsonObject.get("sCaseNoPrefix"));
//					oTransaction.setNclaimno((int) JsonObject.get("nClaimNo"));
//					oTransaction.setNpatientid((long) JsonObject.get("nPatientID"));
//					PreparedStatement stmt = conngrp
//							.prepareStatement("INSERT into public.bl_transaction_mst values(?,?,?,?,?,?,?,?,?,?,?)");
//					stmt.setLong(1, oTransaction.getNtransactionid());
//					stmt.setLong(2, oTransaction.getNmasterappointmentid());
//					stmt.setLong(3, oTransaction.getNappointmentid());
//					stmt.setLong(4, oTransaction.getNvisitid());
//					stmt.setLong(5, oTransaction.getNonsitedate());
//					stmt.setLong(6, oTransaction.getNinjurydate());
//					stmt.setLong(7, oTransaction.getNunabletoworkfromdate());
//					stmt.setLong(8, oTransaction.getNtransactiondate());
//					stmt.setString(9, oTransaction.getScasenoprefix());
//					stmt.setLong(10, oTransaction.getNclaimno());
//					stmt.setLong(11, oTransaction.getNpatientid());
//
//					int j = stmt.executeUpdate();
//					System.out.println(j + " records inserted");
//				}
////		    		String sql = "UPDATE public.bl_transaction_mst " +
////		    	            "SET ismessagesubscribed = true WHERE nid="+Randomno;
////		    	         stmt.executeUpdate(sql);
//			}
//			PreparedStatement stmt3 = conngrp
//					.prepareStatement("UPDATE public.PubSub_Metadata set ismessagesubscribed=? where nid=?");
//			stmt3.setBoolean(1, true);// 1 specifies the first parameter in the query i.e. name
//			stmt3.setLong(2, Randomno);
//
//			int l = stmt3.executeUpdate();
//			System.out.println("" + " records updated");
//
//			conngrp.close();
//
//		} catch (Exception ex) {
//			System.out.println(ex.toString());
//		} finally {
//
//			if (conngrp != null) {
//				conngrp = null;
//			}
//		}
//
//		return 1;
//	}
	}
	public Connection getConnection(String dbname, boolean isFromDataFlow) throws SQLException {
		Connection oConnection = null;
		try {
			String sslpara = "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory&sslmode=require";
			if (isFromDataFlow) {

				oConnection = DriverManager.getConnection("jdbc:postgresql://google/" + dbname + "?cloudSqlInstance="
						+ this.instance + "&socketFactory=com.google.cloud.sql.postgres.SocketFactory&user=" + username
						+ "&password=" + password);

			} else {
				oConnection = DriverManager.getConnection(this.instance + "/" + dbname + sslpara, username, password);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return oConnection;

	}

	long GenerateNo() {
		BigInteger maxLimit = new BigInteger("5000000000000");
		BigInteger minLimit = new BigInteger("25000000000");
		BigInteger bigInteger = maxLimit.subtract(minLimit);
		Random randNum = new Random();
		int len = maxLimit.bitLength();
		BigInteger res = new BigInteger(len, randNum);
		if (res.compareTo(minLimit) < 0)
			res = res.add(minLimit);
		if (res.compareTo(bigInteger) >= 0)
			res = res.mod(bigInteger).add(minLimit);
		System.out.println("The random BigInteger = " + res);
		return res.longValue();
	}
}

