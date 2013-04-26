package com.opensource.restful.shared.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDTOTests extends TestCase
{

    final Logger logger = LoggerFactory.getLogger(UserDTOTests.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");

    protected void setUp() throws Exception
    {
        System.out.println("setup: Loading application context");
        System.out.println("setup: Done loading application context");
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        System.out.println("tearDown: START");
        System.out.println("tearDown: FINISH");
    }

    @Test
    public void testUserCreate() throws Exception
    {
        System.out.println("testUserCreate: START");
        // =================================================================================
        int id = 0;
        String username = "user_name";
        String password = "password";
        boolean active = true;
        boolean admin = true;
        String dob = "11/03/1966";
        String email = "demo@demo.com";
        String securityQuestion1 = "XXX";
        String securityAnswer1 = "XXX";
        String securityQuestion2 = "YYY";
        String securityAnswer2 = "YYY";
        Date birthDate = sdf.parse(dob);
        // =================================================================================
        System.out.println("testUserCreate: START: CREATE");
        UserDTO user = new UserDTO();
        assertNotNull(user);
        // ***************************************************************
        user.setUserId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setUserActive(active);
        user.setUserEmail(email);
        user.setUserSecurityQuestion1(securityQuestion1);
        user.setUserSecurityAnswer1(securityAnswer1);
        user.setUserSecurityQuestion2(securityQuestion2);
        user.setUserSecurityAnswer2(securityAnswer2);
        System.out.println("testUserCreate: user=" + user.toString());
        // ***************************************************************
        assertNotNull(user.getUserId());
        assertNotNull(user.getUsername());
        assertNotNull(user.getPassword());
        assertNotNull(user.isUserActive());
        assertNotNull(user.toString());
        // ***************************************************************
        assertEquals(id, user.getUserId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(active, user.isUserActive());
        assertEquals(email, user.getUserEmail());
        assertEquals(securityQuestion1, user.getUserSecurityQuestion1());
        assertEquals(securityAnswer1, user.getUserSecurityAnswer1());
        assertEquals(securityQuestion2, user.getUserSecurityQuestion2());
        assertEquals(securityAnswer2, user.getUserSecurityAnswer2());
        // =================================================================================
        System.out.println("testUserCreate: FINISH: CREATE");
    }

}
