package com.drac.service.impl;

import com.drac.model.AppUser;
import com.drac.service.IAppUserService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by anil on 3/19/17.
 */
public class AppUserServiceTest {

    private IAppUserService appUserService;
    @Mock
    private AppUser user;

    @BeforeMethod
    public void setUp() throws Exception {
        appUserService = Mockito.mock(AppUserService.class);
    }

    @Test
    public void testAuthenticate() throws Exception {
        when(appUserService.authenticate(user)).thenReturn(true);
        assertTrue(appUserService.authenticate(user));
        AppUser anotherAppUser = new AppUser();
        anotherAppUser.setUserName("Ramesh");
        anotherAppUser.setPassword("ramesh");
        //Username and password shouldn't match
        assertFalse(appUserService.authenticate(anotherAppUser));

    }

}