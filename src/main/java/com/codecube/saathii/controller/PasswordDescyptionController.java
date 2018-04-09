package com.codecube.saathii.controller;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import com.codecube.saathii.service.ViewInformationService;
import com.codecube.saathii.utility.PasswordUtility;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/satthi")
@CrossOrigin
@Api(value="PasswordEncryptionDecryption", description="Decryption Of Password")
public class PasswordDescyptionController {

    
    @Autowired
	ViewInformationService viewInformationService;
    
    @Autowired
    PasswordUtility passutility;
    
    @Autowired
	SessionFactory sessionFactory;
    
	@RequestMapping(value ="/desc/{passworde}", method= RequestMethod.POST)
	@Produces(MediaType.APPLICATION_JSON)
    public String Descryption(@PathVariable("passworde") String passworde) throws Exception
    {
		String passe = passutility.decryption(passworde);
		System.out.println(passe);
		return passe;
		
    }
	
	
}
