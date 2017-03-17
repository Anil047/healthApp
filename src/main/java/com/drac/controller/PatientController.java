package com.drac.controller;

import com.drac.model.Patient;
import com.drac.service.IPatientService;
import com.drac.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/Patient")

public class PatientController {
	//public static final Logger logger = LoggerFactory.getLogger(PatientController.class);

	private IPatientService patientService;

	@Autowired
	public PatientController(IPatientService patientService) {
		this.patientService = patientService;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestBody Patient patient) {
		// String path = System.getProperty("user.dir");
		if (StringUtils.isNotNull(patient.getEncodedFileInString())) {
			System.out.println("File Extension: " + patient.getExtensionOfFile());
			System.out.println("File in encoded form: " + patient.getEncodedFileInString());
			String fileLocation = new File("src/main/resources/static").getAbsolutePath();
			String fileName = String.valueOf(System.currentTimeMillis()).concat(patient.getExtensionOfFile());
			if (patientService.upload(patient.getEncodedFileInString(), fileLocation, fileName)) {
				patient.setImagePath(fileName);
				patientService.save(patient);
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
		} else {
			patient.setImagePath(null);
			patientService.save(patient);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Patient> getPatient(@PathVariable("id") int id) {
		Patient patient = patientService.getById(id);
		if (patient == null) {
			return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Patient>(patient, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Patient patient) {
		Patient existingPatient = patientService.getById(patient.getId());
		if (existingPatient == null) {

			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			patientService.save(patient);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<Patient>> getAll() {
		//logger.debug("Get All Patient");
		List<Patient> patientList = (List<Patient>) patientService.getAll();
		if (patientList.isEmpty()) {
			return new ResponseEntity<List<Patient>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Patient>>(patientList, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		Patient patient = patientService.getById(id);
		System.out.println("Patient id: "+patient.getId() + " -"+id);
		if (patient == null) {

			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			patientService.delete(id);
			System.out.println("Patient Deleted: "+ patient.getId());
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

}
