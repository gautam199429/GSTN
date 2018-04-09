package com.codecube.saathii.controller;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codecube.saathii.utility.PasswordUtility;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/satthi")
@CrossOrigin
@Api(value="PasswordEncryptionDecryption", description="Encryption Of Password")
public class PasswordEncryptionController {
	
	@Autowired
    PasswordUtility passutility;
	
	
	@RequestMapping(value ="/enc/{passworde}", method= RequestMethod.POST)
	@Produces(MediaType.APPLICATION_JSON)
    public String Descryption(@PathVariable("passworde") String passworde) throws Exception
    {
		String passe = passutility.encryption(passworde);
		System.out.println(passe);
		return passe;
		
    }

}
