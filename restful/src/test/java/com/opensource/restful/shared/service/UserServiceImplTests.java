package com.opensource.restful.shared.service;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opensource.restful.domain.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "classpath*:applicationContext.xml" })
public class UserServiceImplTests extends TestCase
{

    private static final Log logger = LogFactory.getLog(UserServiceImplTests.class);

    @Autowired
    private IUserService service;

    @org.junit.Before
    public void setUp() throws Exception
    {
        System.out.println("setUp: service: " + service);
    }

    @org.junit.After
    public void tearDown()
    {
        service = null;
        System.out.println("tearDown: context set null.");
    }

    @Test
    public void testFetchById() throws Exception
    {
        long userId = 2;
        // ==================================================
        UserEntity user = service.getUserById(userId);
        assertNotNull(user);
        // ==================================================
        if (user != null)
        {
            System.out.println("users=" + user.toString());
        }
    }

}
