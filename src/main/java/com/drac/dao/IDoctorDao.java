package com.drac.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.drac.model.Doctor;
@RepositoryRestResource
public interface IDoctorDao extends CrudRepository<Doctor, Integer> {

}
