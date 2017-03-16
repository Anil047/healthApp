package com.drac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drac.dao.IVisitDao;
import com.drac.model.Visit;
import com.drac.service.IVisitService;
@Service
public class VisitServiceImpl extends CrudServiceImpl<Visit, Integer> implements IVisitService {
	@Autowired
	public VisitServiceImpl(IVisitDao visitDao) {
		super(visitDao);
		// TODO Auto-generated constructor stub
	}
	

}
