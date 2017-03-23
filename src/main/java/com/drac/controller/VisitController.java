package com.drac.controller;

import com.drac.model.Visit;
import com.drac.service.IVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Visit")

public class VisitController {
	
	private IVisitService visitService;
		
	@Autowired
	public VisitController(IVisitService visitService) {
		this.visitService = visitService;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestBody Visit visit) {
		visitService.save(visit);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Visit> getVisit(@PathVariable("id") int id) {
		Visit visit = visitService.getById(id);
		if (visit == null) {
			return new ResponseEntity<Visit>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Visit>(visit, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Visit visit) {
		Visit existingVisit = visitService.getById(visit.getId());
		if (existingVisit == null) {

			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			visitService.save(visit);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<Visit>> getAll() {
		List<Visit> visitList = (List<Visit>) visitService.getAll();
		if (visitList.isEmpty()) {
			return new ResponseEntity<List<Visit>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Visit>>(visitList, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		Visit visit = visitService.getById(id);
		if (visit == null) {

			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			visitService.delete(id);

			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

}
