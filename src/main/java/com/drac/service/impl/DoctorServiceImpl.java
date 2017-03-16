package com.drac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drac.dao.IDoctorDao;
import com.drac.model.Doctor;
import com.drac.service.IDoctorService;

@Service
public class DoctorServiceImpl extends CrudServiceImpl<Doctor, Integer> implements IDoctorService {
	
	@Autowired
	public DoctorServiceImpl(IDoctorDao doctorDao) {
		super(doctorDao);
		
	}

}
