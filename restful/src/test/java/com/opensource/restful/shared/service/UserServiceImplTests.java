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
import com.opensource.restful.shared.dto.PositionDTO;
import com.opensource.restful.shared.dto.UserDTO;

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

    private final static int id = 0;
    private final static String email = "tom@tomholmes.new";
    private final static int positionId = 2;
    private final static String firstname = "tom_new";
    private final static String lastname = "holmes_new";
    private final static String username = "user1new";
    private final static String password = "pass1new";
    private final static String securityQuestion1 = "question1";
    private final static String securityQuestion2 = "question2";
    private final static String securityAnswer1 = "answer1";
    private final static String securityAnswer2 = "answer2";

    private UserDTO createUserDto()
    {
        UserDTO userDto = new UserDTO();
        userDto.setUserId(id);
        userDto.setUserActive(true);
        userDto.setUserEmail(email);
        PositionDTO position = new PositionDTO();
        position.setId(positionId);
        position.setActive(true);
        userDto.setPosition(position);
        userDto.setUserFirstName(firstname);
        userDto.setUserLastName(lastname);
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setUserSecurityQuestion1(securityQuestion1);
        userDto.setUserSecurityAnswer1(securityAnswer1);
        userDto.setUserSecurityQuestion2(securityQuestion2);
        userDto.setUserSecurityAnswer2(securityAnswer2);
        return userDto;
    }

    @Test
    public void testCreateUser() throws Exception
    {
        UserDTO userDto = createUserDto();
        // ==================================================
        UserEntity newUserEntity = service.add(userDto);
        assertNotNull(newUserEntity);
        if (newUserEntity != null)
        {
            assertEquals(email, newUserEntity.getEmail());
        }
        // ==================================================
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

    // @Test
    public void testUpdateById() throws Exception
    {
        long userId = 6;
        // ==================================================
        String email = "tom@tomholmes.upd";
        int positionId = 2;
        String firstname = "tom_upd";
        String lastname = "holmes_upd";
        String username = "user1upd";
        String password = "pass1upd";
        String securityQuestion1 = "question11";
        String securityQuestion2 = "question22";
        String securityAnswer1 = "answer11";
        String securityAnswer2 = "answer22";
        // ==================================================
        UserDTO userDto = new UserDTO();
        userDto.setUserId(userId);
        userDto.setUserActive(false);
        userDto.setUserEmail(email);
        PositionDTO position = new PositionDTO();
        position.setId(positionId);
        position.setActive(true);
        userDto.setPosition(position);
        userDto.setUserFirstName(firstname);
        userDto.setUserLastName(lastname);
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setUserSecurityQuestion1(securityQuestion1);
        userDto.setUserSecurityAnswer1(securityAnswer1);
        userDto.setUserSecurityQuestion2(securityQuestion2);
        userDto.setUserSecurityAnswer2(securityAnswer2);
        // ==================================================
        UserEntity user = service.update(userDto);
        assertNotNull(user);
        if (user != null)
        {
            assertEquals(email, user.getEmail());
            assertEquals(firstname, user.getFirstname());
        }
        // ==================================================
        if (user != null)
        {
            System.out.println("users=" + user.toString());
        }
    }

}
