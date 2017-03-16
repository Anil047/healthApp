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

import com.drac.model.Bed;
import com.drac.service.IBedService;

@RestController
@RequestMapping("/Bed")

public class BedController {
	
	private IBedService bedService;
		
	@Autowired
	public BedController(IBedService bedService) {
		this.bedService = bedService;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestBody Bed bed) {
		bedService.save(bed);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Bed> getBed(@PathVariable("id") int id) {
		Bed bed = bedService.getById(id);
		if (bed == null) {
			return new ResponseEntity<Bed>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Bed>(bed, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Bed bed) {
		Bed existingBed = bedService.getById(bed.getId());
		if (existingBed == null) {

			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			bedService.save(bed);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/List", method = RequestMethod.GET)
	public ResponseEntity<List<Bed>> getAll() {
		List<Bed> bedList = (List<Bed>) bedService.getAll();
		if (bedList.isEmpty()) {
			return new ResponseEntity<List<Bed>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Bed>>(bedList, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		Bed bed = bedService.getById(id);
		if (bed == null) {

			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			bedService.delete(id);

			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
	}

}
