package com.codecube.saathii.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.codecube.saathii.entity.GSTR1RETSAVEEntity;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/satthi")
@CrossOrigin
@Api(value="RETSAVE", description="Save Entire GSTR1 Invoices.")
public class GSTR1RetSave {
	private static final String BASE_URL="https://devapi.gstsystem.co.in/taxpayerapi/v0.2/returns/gstr1";
	
	
	@RequestMapping(value ="/savegstr1invoice", method= RequestMethod.PUT, headers = "Content-type=application/json")
	public JSONObject GSTRETsave(@RequestBody GSTR1RETSAVEEntity RequestPayload,
			@RequestHeader("clientid") String clientid,
			@RequestHeader("client-secret") String clientSecret,
			@RequestHeader("ip-usr") String ipAddr,
			@RequestHeader("state-cd") String state,
			@RequestHeader("txn") String txnno,
			@RequestHeader("username") String username,
			@RequestHeader("gstin") String gstin,
			@RequestHeader("ret_period") String ret_period,
			@RequestHeader("auth-token") String auth_token
			) throws Exception
	{
		String result;
		try
		{
			URL url = new URL(BASE_URL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			String request ="{\"action\":\""+RequestPayload.getAction()+"\",\"data\":\""+RequestPayload.getData()+"\",\"hmac\":\""+RequestPayload.getHmac()+"\"}";
			byte[] out = request.getBytes(StandardCharsets.UTF_8);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("clientid", clientid);
			conn.setRequestProperty("Content-Type", javax.ws.rs.core.MediaType.APPLICATION_JSON);
			conn.setRequestProperty("client-secret", clientSecret);
			conn.setRequestProperty("ip-usr", ipAddr);
			conn.setRequestProperty("state-cd", state);
			conn.setRequestProperty("txn", txnno);
			conn.setRequestProperty("username", username);
			conn.setRequestProperty("auth-token", auth_token);
			conn.setRequestProperty("ret_period", ret_period);
			conn.setRequestProperty("gstin", gstin);	
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.connect();
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
		catch(IOException e)
		{
			System.err.println("Error creating HTTP connection");
	        e.printStackTrace();
	        throw e;
		}	
		JSONParser parser1 = new JSONParser();
		JSONObject json = (JSONObject) parser1.parse(result);
		return json;
		
	}

}
