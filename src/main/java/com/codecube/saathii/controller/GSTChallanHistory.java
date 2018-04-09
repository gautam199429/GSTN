package com.codecube.saathii.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.fabric.xmlrpc.base.Data;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/satthi")
@CrossOrigin
@Api(value="GST Challan History", description="Get Your Challan History")
public class GSTChallanHistory {
	
	public final static String BASE_URL="https://devapi.gstsystem.co.in/taxpayerapi/v0.2/payment/";
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value ="/challanhistory", method= RequestMethod.GET)
	public JSONObject getchallanhistory(@RequestParam("action") String action,@RequestParam("fm_dt") String fm_dt,@RequestParam("to_dt") String to_dt,@RequestParam("gstin") String gstin,
			@RequestHeader("clientid") String clientid,
			@RequestHeader("client-secret") String clientSecret,
			@RequestHeader("ip-usr") String ipAddr,
			@RequestHeader("state-cd") String state,
			@RequestHeader("txn") String txnno,
			@RequestHeader("UserId") String userid)
			throws IOException, ParseException
	{
		String result = "{\"error\":\"Please Enter Valid userId or Password\"}";
		try
		{
			URL url = new URL("https://devapi.gstsystem.co.in/taxpayerapi/v0.2/payment/?action="+action+"&fr_dt="+fm_dt+"&to_date="+to_dt);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("clientid", clientid);
			conn.setRequestProperty("Content-Type", javax.ws.rs.core.MediaType.APPLICATION_JSON);
			conn.setRequestProperty("client-secret", clientSecret);
			conn.setRequestProperty("ip-usr", ipAddr);
			conn.setRequestProperty("state-cd", state);
			conn.setRequestProperty("txn", txnno);
			conn.setRequestProperty("UserId", userid);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			BufferedReader in = new BufferedReader(
			          new InputStreamReader(conn.getInputStream()));
			  String output;
			  StringBuffer response = new StringBuffer();
			 
			  while ((output = in.readLine()) != null) {
			   response.append(output);
			  }
			  in.close();
			 
			  //printing result from response
			  System.out.println(response.toString());
		}
		catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
		JSONParser parser1 = new JSONParser();
		JSONObject json = (JSONObject) parser1.parse(result);
		return json;
	}
}
