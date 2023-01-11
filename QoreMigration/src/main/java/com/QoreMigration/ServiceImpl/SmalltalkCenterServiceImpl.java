package com.QoreMigration.ServiceImpl;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QoreMigration.Model.ObjectModel;
import com.QoreMigration.Model.SmalltalkCenter;
import com.QoreMigration.Repository.SmalltalkCenterRepository;
import com.QoreMigration.Service.SmalltalkCenterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;

@Service
public class SmalltalkCenterServiceImpl implements SmalltalkCenterService {

	@Autowired
	EntityManager entityManager;

	@Autowired
	private Gson gson;

	public SmalltalkCenterRepository smcRepository;
	private final String url = "jdbc:postgresql://localhost:5433/postgres";
	private final String user = "postgres";
	private final String password = "pass123";

	public SmalltalkCenterServiceImpl(SmalltalkCenterRepository sRepository) {
		this.smcRepository = sRepository;
	}

	public List<SmalltalkCenter> Searchs(String query) {

		List<SmalltalkCenter> smc = smcRepository.SearchChar(query);
		return smc;
	}

	public List<SmalltalkCenter> pageandsearch(Long pagenumber, Long pagesize, String searchdata) {

		List<SmalltalkCenter> oList = null;

		try {
			StoredProcedureQuery spq = entityManager.createStoredProcedureQuery("fu_getserachdata",
					SmalltalkCenter.class);
			spq.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
			spq.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
			spq.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
			spq.setParameter(1, pagenumber);
			spq.setParameter(2, pagesize);
			spq.setParameter(3, searchdata);

			spq.execute();

			oList = spq.getResultList();

			return oList;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		return oList;

	}

	public List<?> getpa_account_details() {

		// List<?> res = null;
		String json = "";
		List<ObjectModel> Obj = null;

		try {
			StoredProcedureQuery spq = entityManager.createStoredProcedureQuery("getpa_account", ObjectModel.class);

			spq.execute();
			Object obj = spq.getSingleResult();
			JSONObject json1 = (JSONObject) new JSONParser().parse((String) obj);
			System.out.println(json1);
//			

			// json = gson.toJson(singleResult);
			// System.out.println(json);
			Obj = (List<ObjectModel>) spq.getResultList();

			ObjectMapper omapper = new ObjectMapper();

			for (ObjectModel objectModel : Obj) {
				JSONObject jobject = (JSONObject) JSONValue
						.parse(new ObjectMapper().writeValueAsString(objectModel.getResult()));
				System.out.println(jobject);
//					PatientModel omodel = omapper.readValue(objectModel.getResult().toString(), PatientModel.class);
//					oPatientmodel.add(omodel);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
		return Obj;

	}

	// To call store procedure from PostgreSQL
	@Override
	public Object getpartition() {
		try {
			Connection conn = null;
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to the PostgreSQL");

			//we can call SP using CallableStatement
			 CallableStatement stmt = conn.prepareCall("call partition()");
			 stmt.execute();

			//we can call SP using PreparedStatement as well
			//PreparedStatement stmt = conn.prepareStatement("call partition()");

			//stmt.execute();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return 1;
	}
	
	public List<?> getFlatFileData(){
		try {
			
			//implment code to get flatfile data
			System.out.println("Hello"); //Changes in getFaltFileData
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return null;
		
	}

}
