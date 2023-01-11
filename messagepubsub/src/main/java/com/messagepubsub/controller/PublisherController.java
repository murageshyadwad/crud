package com.messagepubsub.controller;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.json.Json;
import com.google.gson.JsonObject;
import com.messagepubsub.model.PubSubModel;
import com.messagepubsub.model.PubSubModel1;
import com.messagepubsub.repository.PubSubRepository;
import com.messagepubsub.service.PublisherService;


@RestController
public class PublisherController {

	@Autowired
	PublisherService oservice;
	
	@Autowired
	PubSubRepository orepository;

	@GetMapping("/1")
	public String homePage() {

		return "Welcome to Publisher";
	}

	@GetMapping("/")
	private ResponseEntity<?> publishMessage() 
	{
		String projectId = "qpathways-dev";
		String topicId = "qpm-publish";
		
		try 
		{
			String oListM=GetMessage();
		
			PublisherService.publisherExample(projectId, topicId, oListM);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/2")
	private ResponseEntity<?> publishMessage1() 
	{
		String projectId = "qpathways-dev";
		String topicId = "qpm-publish1";
		
		try 
		{
			
			String oListM1=GetMessage1();
			PublisherService.publisherExample(projectId, topicId,oListM1);
			
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ExecutionException e) 
		{
			e.printStackTrace();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	private String GetMessage()
	{
		 //String[] result = new String[20];
		String json ="";
		try {
			
			List<PubSubModel> oModel=new ArrayList<PubSubModel>();
			PubSubModel p = new PubSubModel( 16,"Roman","Nashik");
//			oModel.setId((long) 3);
//			oModel.setName("Andrew");
//			oModel.setAddress("Chennai");
			oModel.add(p);
			
			ObjectMapper mapper = new ObjectMapper();
			  json = mapper.writeValueAsString(oModel);
			 System.out.println("ResultingJSONstring = " + json);
//			List<PubSubModel> tutorials = new ArrayList<PubSubModel>();
//
//		
//				orepository.findAll().forEach(tutorials::add);
//			
//
//			if (tutorials.isEmpty()) {
//				return tutorials;
//			}
//
//			return tutorials;
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//
//	        String userName = "sa";
//	        String password = "saINISKDDEV1";
//	        String url = "jdbc:sqlserver://localhost:1433"+";databaseName=glo9050Demo";
//	        try {
//	        	Connection con = DriverManager.getConnection(url, userName, password);
//	        	Statement s1 = con.createStatement();
//		        ResultSet rs = s1.executeQuery("SELECT  * FROM BL_PubSub");
//		       
//		        if(rs!=null){
//		            while (rs.next()){
//		                for(int i = 0; i <result.length ;i++)
//		                {
//		                    for(int j = 0; j <result.length;j++)
//		                    {
//		                        result[j]=rs.getString(i);
//		                    System.out.println(result[j]);
//		                }
//		                }
//		            }
//		        }
//			} catch (SQLException e) {
//				// TODO: handle exception
//			}
	        
	        
			return json;
		} catch (Exception e) {
//			return null;
		
		
		}		
		return json;
	}
	private String GetMessage1()
	{
		 
		String json ="";
		try {
			
			List<PubSubModel1> oModel1=new ArrayList<PubSubModel1>();
			PubSubModel1 p = new PubSubModel1( 18,"Marley","Chennai",2119875);

			oModel1.add(p);
			
			ObjectMapper mapper = new ObjectMapper();
			  json = mapper.writeValueAsString(oModel1);
			 System.out.println("ResultingJSONstring = " + json);

	        
			return json;
		} catch (Exception e) {

		
		
		}		
		return json;
	}
}
