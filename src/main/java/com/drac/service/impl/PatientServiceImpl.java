package com.drac.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drac.dao.IPatientDao;
import com.drac.model.Patient;
import com.drac.service.IPatientService;
@Service
public class PatientServiceImpl extends CrudServiceImpl<Patient, Integer> implements IPatientService {
	@Autowired
	public PatientServiceImpl(IPatientDao patientDao) {
		super(patientDao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean upload(String base64EncodedStream, String path, String filename) {
		byte[] data = Base64.getDecoder().decode((base64EncodedStream));
		try (OutputStream stream = new FileOutputStream(path.concat("/").concat(filename))) {
		    stream.write(data);
		    return true;
		}catch(FileNotFoundException ef){
			ef.printStackTrace();
			return false; 
		}catch(IOException ie){
			ie.printStackTrace();
			return false;
		}
		
	}

}
