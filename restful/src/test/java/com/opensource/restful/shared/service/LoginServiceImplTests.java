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
public class LoginServiceImplTests extends TestCase
{

    private static final Log logger = LogFactory.getLog(LoginServiceImplTests.class);

    @Autowired
    private ILoginService service;

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
    public void testFetchByLogin() throws Exception
    {
        String username = "demo";
        String password = "demo";
        // ==================================================
        UserEntity user = service.login(username, password);
        assertNotNull(user);
        // ==================================================
        if (user != null)
        {
            System.out.println("users=" + user.toString());
        }
    }

    @Test
    public void testFetchByEmail() throws Exception
    {
        String email = "tom@tomholmes.net";
        // ==================================================
        UserEntity user = service.loginByEmail(email);
        assertNotNull(user);
        // ==================================================
        if (user != null)
        {
            System.out.println("users=" + user.toString());
        }
    }

    @Test
    public void testFetchByUsername() throws Exception
    {
        String username = "demo";
        // ==================================================
        UserEntity user = service.loginByUsername(username);
        assertNotNull(user);
        // ==================================================
        if (user != null)
        {
            System.out.println("users=" + user.toString());
        }
    }

}
