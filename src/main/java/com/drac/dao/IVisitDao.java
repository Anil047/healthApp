package com.drac.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.drac.model.Visit;

@RepositoryRestResource
public interface IVisitDao extends CrudRepository<Visit, Integer> {

}
