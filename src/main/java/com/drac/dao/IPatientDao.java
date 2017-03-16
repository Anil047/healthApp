package com.drac.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.drac.model.Patient;


@RepositoryRestResource
public interface IPatientDao extends CrudRepository<Patient, Integer> {

}
