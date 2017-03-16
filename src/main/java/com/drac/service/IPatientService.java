package com.drac.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.drac.model.Patient;

public interface IPatientService extends ICrudService<Patient, Integer> {
	boolean upload(String base64EncodedStream,String path, String filename) ;

}
