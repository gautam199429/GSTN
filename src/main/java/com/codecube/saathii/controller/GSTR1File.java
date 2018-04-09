package com.codecube.saathii.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import springfox.documentation.schema.Model;

@RestController
@RequestMapping("/saathii")
@CrossOrigin
@Api(value="GSTR1 FILE", description="Amended Advance Tax")
public class GSTR1File {
	
	
	//private static final String BASE_URL = "https://devapi.gstsystem.co.in/taxpayerapi/v1.1/returns/gstr1";
	@RequestMapping(value="/ata", method=RequestMethod.GET)
	private void SendGet(@RequestParam("action") String action, @RequestParam("gstin") String gstin, @RequestParam("ret_period") String ret_period,
			@RequestHeader("clientid") String clientid,
			@RequestHeader("client-secret") String clientSecret,
			@RequestHeader("ip-usr") String ipAddr,
			@RequestHeader("state-cd") String state,
			@RequestHeader("txn") String txnno,
			@RequestHeader("UserId") String userid,
			@RequestHeader("auth_token") String auth_token,
			@RequestHeader("gstin") String gstin1,
			@RequestHeader("ret_period") String ret_period1,
			@RequestHeader("app_key") String appkey
			) throws Exception
	{
		try 
		{
			System.out.println("Under Try Block");
			URL url = new URL("https://devapi.gstsystem.co.in/taxpayerapi/v1.1/returns/gstr1?action="+action+"&gstin="+gstin+"&ret_period="+ret_period+"");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			System.out.println("Making Connection");
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON);
			conn.setRequestProperty("clientid", clientid);
			conn.setRequestProperty("client-secret", clientSecret);
			conn.setRequestProperty("ip-usr", ipAddr);
			conn.setRequestProperty("state-cd", state);
			conn.setRequestProperty("txn", txnno);
			conn.setRequestProperty("UserId", userid);
			System.out.println("Header Sent");
			String urlParameters = "action="+action+"&gstin="+gstin+"&ret_period="+ret_period+"";
			byte[] out = urlParameters.getBytes(StandardCharsets.UTF_8);
			System.out.println(urlParameters);
			conn.setDoOutput(true);
			conn.connect();
			System.out.println("connected");
			OutputStream os = conn.getOutputStream();
			os.write(out);
			os.flush();
			os.close();	
			System.out.println(out);
			System.out.println("ERROR");
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			int result2 = bis.read();
			System.out.println("ERROR2");
			while(result2 != -1) {
			    buf.write((byte) result2);
			    result2 = bis.read();
			}
			String result = buf.toString();
			System.out.println(result);
			
		} 
		catch(ProtocolException e)
		{
			System.out.println("PROTOCOL EXCEPTION");
			
		}
		catch (IOException e) 
		{
			System.out.println("INPUT OUTPUT EXCEPTION");
		}
	}

}
