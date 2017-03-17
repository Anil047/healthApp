package com.drac.service.impl;

import com.drac.dao.IAppUserDao;
import com.drac.model.AppUser;
import com.drac.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by anil on 3/17/17.
 */
@Service
public class AppUserService extends CrudServiceImpl<AppUser, Integer> implements IAppUserService {
    private IAppUserDao appUserDao;

    @Autowired
    public AppUserService(IAppUserDao appUserDao) {
        super(appUserDao);
        this.appUserDao = appUserDao;
    }

    @Override
    public boolean authenticate(AppUser appUser) {
        AppUser appUserFromDb = this.appUserDao.getAppUserByUserNameAndPwd(appUser.getUserName(), appUser.getPassword());
        if (appUserFromDb != null) {
            return true;
        }
        return false;

    }
}
