package com.drac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drac.dao.IBedDao;
import com.drac.model.Bed;
import com.drac.service.IBedService;

@Service
public class BedServiceImpl extends CrudServiceImpl<Bed, Integer> implements IBedService {
	
	@Autowired
	public BedServiceImpl(IBedDao bedDao) {
		super(bedDao);
		
	}

}
