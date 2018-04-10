package com.codecube.saathii.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.codecube.saathii.entity.GstEntityOtp;
import com.codecube.saathii.entity.GstErrorCodes;
import com.codecube.saathii.service.ViewInformationService;
import com.codecube.saathii.utility.PasswordUtility;
import com.codecube.saathii.utility.UserPasswordAuthentication;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/satthi")
@CrossOrigin
@Api(value="OTP", description="GST OTP Authentication")
public class GstOtpController {
	private static final String BASE_URL = "http://devapi.gstsystem.co.in/taxpayerapi/v0.2/authenticate";
    
    @Autowired
	ViewInformationService viewInformationService;
    
    @Autowired
	PasswordUtility pas;
     
    @Autowired
	SessionFactory sessionFactory;
    
    @Autowired
    UserPasswordAuthentication upa;
    
	@RequestMapping(value ="/otprequest/{userId}/{password}", method= RequestMethod.POST, headers = "Content-type=application/json")
    public JSONObject OTPRequest(@RequestBody GstEntityOtp RequestPayload,@PathVariable("userId") String userId,@PathVariable("password") String password,
			@RequestHeader("clientid") String clientid,
			@RequestHeader("client-secret") String clientSecret,
			@RequestHeader("ip-usr") String ipAddr,
			@RequestHeader("state-cd") String state,
			@RequestHeader("txn") String txnno,
			@RequestHeader("UserId") String userid) throws Exception
    {
		String result = "{\"error\":\"Please Enter Valid userId or Password\"}";
		
		if(upa.IsAuthenticate(userId, password)==true)
		{
		try {
		 URL url = new URL(BASE_URL);
		 System.out.println("Before Creating creating HTTP connection");
		 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		 System.out.println("After creating HTTP connection");
		System.out.println("After Connection .......");
		String request ="{\"action\":\""+RequestPayload.getAction()+"\",\"username\":\""+RequestPayload.getUsername()+"\",\"app_key\":\""+RequestPayload.getApp_key()+"\"}";
		byte[] out = request.getBytes(StandardCharsets.UTF_8);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("clientid", clientid);
		conn.setRequestProperty("Content-Type", javax.ws.rs.core.MediaType.APPLICATION_JSON);
		conn.setRequestProperty("client-secret", clientSecret);
		conn.setRequestProperty("ip-usr", ipAddr);
		conn.setRequestProperty("state-cd", state);
		conn.setRequestProperty("txn", txnno);
		conn.setRequestProperty("UserId", userid);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		System.out.println("connected........");
		conn.connect();
		System.out.println("connected........");
		OutputStream os = conn.getOutputStream();
		os.write(out);
		os.flush();
		os.close();	
		BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		int result2 = bis.read();
		while(result2 != -1) {
		    buf.write((byte) result2);
		    result2 = bis.read();
		}
		result = buf.toString();
		System.out.println(result);
		
		}
		catch (IOException e) {
			System.err.println("Error creating HTTP connection");
	        e.printStackTrace();
	        throw e;
		}
		catch(NullPointerException e)
		{
			System.out.println("NULL POINTER EXCEPTION");
		}
		JSONParser parser1 = new JSONParser();
		JSONObject json = (JSONObject) parser1.parse(result);
		String status_cd = (String) json.get("status_cd");
		if(status_cd.equals("1"))
		{
			return json;
		}
		else
		{	
			JSONObject error = (JSONObject) json.get("error");
			String error_cd = (String) error.get("error_cd");
			System.out.println(error_cd);
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			String SQL_QUERY ="select * from gsterrorcodes m where m.error_cd = :error_cd";
			Query query1 = session.createNativeQuery(SQL_QUERY);
			query1.setParameter("error_cd",error_cd);
			List<GstErrorCodes> gsterrorcodes = (List<GstErrorCodes>) query1.list(); 
			Iterator itr = gsterrorcodes.iterator();
			while(itr.hasNext()){
			   Object[] obj = (Object[]) itr.next();
			   String error_code = String.valueOf(obj[0]);
			   System.out.println("ERROR DESCRIPTION"+error_code);
			   String error_description = String.valueOf(obj[1]);
			   System.out.println("ERROR DESCRIPTION"+error_description);
			   String error_message = String.valueOf(obj[2]);
			   System.out.println("ERROR MESSAGE"+error_message);
			   
			}
			return json;
		}
		}
		else
		{
			System.out.println("\t\tENTER VALID USERID OR PASSWORD PROVIDED BY GSP");
		}
		JSONParser parser1 = new JSONParser();
		JSONObject json = (JSONObject) parser1.parse(result);
		return json;
	}
//	private boolean userAuthentication(String userId, String passord) throws Exception
//	{
//	String pasenc = pas.encryption(passord);
//	System.out.println(pasenc);
//	Session session = sessionFactory.openSession();
//	Transaction tx = session.beginTransaction();
//	boolean userFound = false;
//	String SQL_QUERY ="select * from usertbls u where u.userid = :userid and password = :password";
//	Query query = session.createNativeQuery(SQL_QUERY);
//	query.setParameter("userid",userId);
//	query.setParameter("password",pasenc);
//	List list = query.list();
//	if ((list != null) && (list.size() > 0)) {
//		userFound= true;
//		System.out.println("=============================================================================================");
//		System.out.println("\t\tUSER FOUND");
//		System.out.println("=============================================================================================");
//	}
//	else
//	{
//		System.out.println("=============================================================================================");
//		System.out.println("\t\tUSER NOT FOUND");
//		System.out.println("=============================================================================================");
//	}
//	tx.commit();
//	session.close();
//	return userFound;    	
//	}
}



////System.out.println("RESPONSE CODE:--- "+conn.getResponseCode()+  
////		"\nCONTENT LENGHT:---"+conn.getContentLength()+
////		"\nERROR STREAM:---"+conn.getErrorStream()+
////		"\nclientid:---"+conn.getRequestProperty("clientid")+
////		"\ncontent-type:---"+conn.getHeaderField("Content-Type")+
////		"\nclient-sec:---"+conn.getRequestProperty("client-secret")+
////		"\nIpadd:---"+conn.getRequestProperty("ip-usr")+
////		"\nstate:---"+conn.getRequestProperty("state-cd")+
////		"\nuserid:---"+conn.getRequestProperty("userId")+
////		"\ntxn:---"+conn.getRequestProperty("txn")+
////		"\nREQUEST METHOD:---"+conn.getRequestMethod()+
////		"\nFIELDS:---"+conn.getHeaderFields()+
////		"\nResponse Message:---"+conn.getResponseMessage());	
//
//
//
//
//
////@ApiImplicitParams({
////		@ApiImplicitParam(name = "clientid", value = "Client Identification for authentication", required = true, dataType = "string", paramType = "header"),
////		@ApiImplicitParam(name = "client-sercret", value = "Secret code for the API client", required = true, dataType = "string", paramType = "header"),
////		@ApiImplicitParam(name = "state-cd", value = "State code to which the Tax Payer belongs to", required = true, dataType = "string", paramType = "header"),
////		@ApiImplicitParam(name = "ip-usr", value = "Public IP Address of end user", required = true, dataType = "string", paramType = "header"),
////		@ApiImplicitParam(name = "txn", value = "Unique Transaction ID associated with the request", required = true, dataType = "string", paramType = "header"),
////		@ApiImplicitParam(name = "UserId", value = "User ID of Tax Payer", required = true, dataType = "string", paramType = "body"),
////		@ApiImplicitParam(name = "password", value = "password Of Tax Payer", required = true, dataType = "string", paramType = "header")
////	})
////	@CrossOrigin
//
//
//
////	private String clientId ="l7xx5edefdd923ad438eb5b332a73269f812";
////    private String clientSecret="383dc1f4835f43ad80f80f6cf284cd7b";
////    private String state = "33";
////    private String ipAddr ="192.168.0.5";
////    private String txnno="LAPN24235325555";    
////    private String userid="balaji.tn.1";
