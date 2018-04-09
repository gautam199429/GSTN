package com.codecube.saathii.controller;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/satthi")
@CrossOrigin
@Api(value="Validation Details", description="GET VALIDATION DETAILS")
public class GSTValidationDetails 
{
	
	@RequestMapping(value="/validation",method= RequestMethod.GET)
	public JSONObject ValidationDetsils(@RequestAttribute("action") String action, 
			@RequestAttribute("stcd") String state_cd, 
			@RequestAttribute("arn") String arn,
			@RequestHeader("clientid") String clientid,
			@RequestHeader("client-secret") String clientSecret,
			@RequestHeader("ip-usr") String ipAddr,
			@RequestHeader("state-cd") String state,
			@RequestHeader("txn") String txnno,
			@RequestHeader("UserId") String userid,
			@RequestHeader("gstn") String gstn,
			@RequestHeader("ret_period") String ret_period) throws Exception
	{
		String result = "{\"error\":\"Please Enter Valid userId or Password\"}";
		try
		{
			URL url = new URL("https://devapi.gstsystem.co.in/v0.1/taxpayerapi/application?action="+action+"&stcd="+state+"&arn="+arn+"");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("clientid", clientid);
			conn.setRequestProperty("client-secret", clientSecret);
			conn.setRequestProperty("ip-usr", ipAddr);
			conn.setRequestProperty("state-cd", state_cd);
			conn.setRequestProperty("txn", txnno);
			conn.setRequestProperty("userId", userid);
			conn.setRequestProperty("gstn", gstn);
			conn.setRequestProperty("ret_period", ret_period);
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			result = conn.getResponseMessage();	
			System.out.println(result);
		}
		catch(Exception e)
		{
			System.out.println("Exception occur");
		}
		JSONParser parser1 = new JSONParser();
		JSONObject json = (JSONObject) parser1.parse(result);
		return json;
	}
	
}