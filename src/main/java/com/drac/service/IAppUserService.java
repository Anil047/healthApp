package com.drac.service;

import com.drac.model.AppUser;

/**
 * Created by anil on 3/17/17.
 */
public interface IAppUserService extends ICrudService<AppUser, Integer> {
    boolean authenticate(AppUser appUser);
}
