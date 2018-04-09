package com.codecube.saathii.dao;

import java.util.List;

import com.codecube.saathii.entity.ViewInformation;

public interface ViewInformationDao {

	public List<ViewInformation> loadInformations(ViewInformation viewInformation);
	public ViewInformation getInformationById(String userId);
	
}
