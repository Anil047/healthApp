package com.drac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.drac.model.Doctor;
import com.drac.service.IDoctorService;

@RestController
@RequestMapping("/Doctor")

public class DoctorController {
	
	private IDoctorService doctorService;
		
	@Autowired
	public DoctorController(IDoctorService doctorService) {
		this.doctorService = doctorService;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestBody Doctor doctor) {
		doctorService.save(doctor);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Doctor> getDoctor(@PathVariable("id") int id) {
		Doctor doctor = doctorService.getById(id);
		if (doctor == null) {
			return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Doctor doctor) {
		Doctor existingDoctor = doctorService.getById(doctor.getId());
		if (existingDoctor == null) {

			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			doctorService.save(doctor);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/List", method = RequestMethod.GET)
	public ResponseEntity<List<Doctor>> getAll() {
		List<Doctor> doctorList = (List<Doctor>) doctorService.getAll();
		if (doctorList.isEmpty()) {
			return new ResponseEntity<List<Doctor>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Doctor>>(doctorList, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		Doctor doctor = doctorService.getById(id);
		if (doctor == null) {

			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			doctorService.delete(id);

			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}

}
