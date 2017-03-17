package com.drac.controller;

import com.drac.model.AppUser;
import com.drac.model.JwtToken;
import com.drac.model.LoginUser;
import com.drac.model.ResponseObj;
import com.drac.service.IAppUserService;
import com.drac.service.impl.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by anil on 3/17/17.
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    private IAppUserService iAppUserService;
    private JwtServiceImpl jwtService;

    @Autowired
    public HomeController(IAppUserService iAppUserService) {
        this.iAppUserService = iAppUserService;
    }

    public JwtServiceImpl getJwtService() {
        return jwtService;
    }

    @Autowired
    public void setJwtService(JwtServiceImpl jwtService) {
        this.jwtService = jwtService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody LoginUser loginUser) {
        AppUser appUser = new AppUser();
        appUser.setUserName(loginUser.getUserName());
        appUser.setPassword(loginUser.getPassword());
        if (this.iAppUserService.authenticate(appUser)) {
            JwtToken jwtToken = new JwtToken();
            jwtToken.setUserName(appUser.getUserName());
            ResponseObj data = new ResponseObj(jwtService.getEncodedToken(jwtToken));
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseObj(false, null), HttpStatus.OK);
    }

}
