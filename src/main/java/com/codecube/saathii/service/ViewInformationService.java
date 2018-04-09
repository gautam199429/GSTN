package com.codecube.saathii.service;

import java.util.List;
import com.codecube.saathii.entity.ViewInformation;


public interface ViewInformationService {
	
	
	public List<ViewInformation> loadInformations(ViewInformation viewInformation);
	public ViewInformation getInformationById(String userId);
	
}
