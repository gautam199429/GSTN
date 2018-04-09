package com.codecube.saathii.controller;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.codecube.saathii.entity.ViewInformation;
import com.codecube.saathii.resModel.ResponseObj;
import com.codecube.saathii.service.ViewInformationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("/saathii/viewinformation")
@CrossOrigin
@Api(value="ViewInformation", description="View Information Of All Users")
public class ViewInformationController  {

	@Autowired
	ViewInformationService viewInformationService;
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
		})
	@CrossOrigin
    @RequestMapping(value = "/laod", method = RequestMethod.POST)
	public ResponseEntity<?> loadinformations(@RequestBody ViewInformation viewInformation) {
		List<ViewInformation> informations = viewInformationService.loadInformations(viewInformation);
		ResponseObj obj = new ResponseObj();
		obj.setMessage("information loaded");
		obj.setObject(informations);
		obj.setStatus_code(200);
		obj.setStatus("Success");
		return ResponseEntity.ok(obj);
	}	
	
	@CrossOrigin
	@RequestMapping(value="/load/{userId}/{password}", method=RequestMethod.POST)
	@Produces(MediaType.APPLICATION_JSON)
		public ResponseEntity<ViewInformation> getArticleById(@PathVariable("userId") String userId,@PathVariable("password") String password) {
		ViewInformation viewInfo = viewInformationService.getInformationById(userId);
		return new ResponseEntity<ViewInformation>(viewInfo, HttpStatus.OK);
	}
   }
	
