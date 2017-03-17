package com.drac.dao;

import com.drac.model.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by anil on 3/17/17.
 */
@RepositoryRestResource
public interface IAppUserDao extends CrudRepository<AppUser, Integer> {
    @Query("SELECT au FROM AppUser au WHERE LOWER(au.userName) = LOWER(:userName) AND au.password= :password")
    AppUser getAppUserByUserNameAndPwd(@Param("userName") String lastName, @Param("password") String password);


}
