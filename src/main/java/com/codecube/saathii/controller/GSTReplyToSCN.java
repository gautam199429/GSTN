package com.codecube.saathii.controller;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/saathii")
@CrossOrigin
@Api(value="ReplySCN", description="ReplyToSCN")
public class GSTReplyToSCN {
	
	
	
	@RequestMapping(value="/validation",method= RequestMethod.PUT)
	public JSONObject ReplySCN(
			@RequestAttribute("stcd") String state_cd, 
			@RequestAttribute("arn") String arn,
			@RequestHeader("clientid") String clientid,
			@RequestHeader("client-secret") String clientSecret,
			@RequestHeader("ip-usr") String ipAddr,
			@RequestHeader("state-cd") String state,
			@RequestHeader("txn") String txnno,
			@RequestHeader("UserId") String userid
			) throws Exception
	{
		String result = "{\"error\":\"Please Enter Valid userId or Password\"}"; 
		try {
			URL url = new URL("https://devapi.gstsystem.co.in/v0.1/taxpayerapi/application");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("clientid", clientid);
			conn.setRequestProperty("Content-Type", javax.ws.rs.core.MediaType.APPLICATION_JSON);
			conn.setRequestProperty("client-secret", clientSecret);
			conn.setRequestProperty("ip-usr", ipAddr);
			conn.setRequestProperty("state-cd", state);
			conn.setRequestProperty("txn", txnno);
			conn.setRequestProperty("UserId", userid);
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			result = conn.getResponseMessage();	
			System.out.println(result);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception Occur");
		}
		JSONParser parser1 = new JSONParser();
		JSONObject json = (JSONObject) parser1.parse(result);
		return json;
	}

}
