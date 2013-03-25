package com.opensource.restful.shared.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.opensource.restful.shared.dto.PositionDTO;
import com.opensource.restful.shared.dto.UserDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations =
{ "classpath*:applicationContext.xml" })
public class UserControllerTests
{
    public final static String BASE_URL = "http://127.0.0.1:8888/";

    private final static Log logger = LogFactory.getLog(UserControllerTests.class);

    private final static int id = 0;
    private final static String email = "tom@tomholmes.new";
    private final static int positionId = 2;
    private final static String firstname = "tom4_new";
    private final static String lastname = "holmes4_new";
    private final static String username = "user4new";
    private final static String password = "pass4new";
    private final static String securityQuestion1 = "question1";
    private final static String securityQuestion2 = "question2";
    private final static String securityAnswer1 = "answer1";
    private final static String securityAnswer2 = "answer2";

    private UserDTO createUserDto()
    {
        UserDTO userDto = new UserDTO();
        // =============================================
        userDto.setUserId(id);
        userDto.setActive(true);
        userDto.setEmail(email);
        PositionDTO position = new PositionDTO();
        position.setId(positionId);
        position.setActive(true);
        userDto.setPosition(position);
        userDto.setFirstName(firstname);
        userDto.setLastName(lastname);
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setSecurityQuestion1(securityQuestion1);
        userDto.setSecurityAnswer1(securityAnswer1);
        userDto.setSecurityQuestion2(securityQuestion2);
        userDto.setSecurityAnswer2(securityAnswer2);
        // =============================================
        return userDto;
    }

    private final static int updId = 9;
    private final static String updEmail = "tom@tomholmes.new";
    private final static int updPositionId = 3;
    private final static String updFirstname = "tom4_upd";
    private final static String updLastname = "holmes4_upd";
    private final static String updPassword = "pass4upd";
    private final static String updSecurityQuestion1 = "question1upd";
    private final static String updSecurityQuestion2 = "question2upd";
    private final static String updSecurityAnswer1 = "answer1upd";
    private final static String updSecurityAnswer2 = "answer2upd";

    private UserDTO updateUserDto(UserDTO userDto)
    {
        userDto.setUserId(updId);
        userDto.setActive(false);
        userDto.setEmail(updEmail);
        // ------------------------------------------
        PositionDTO position = new PositionDTO();
        position.setId(updPositionId);
        position.setActive(true);
        userDto.setPosition(position);
        // ------------------------------------------
        userDto.setFirstName(updFirstname);
        userDto.setLastName(updLastname);
        userDto.setPassword(updPassword);
        userDto.setSecurityQuestion1(updSecurityQuestion1);
        userDto.setSecurityAnswer1(updSecurityAnswer1);
        userDto.setSecurityQuestion2(updSecurityQuestion2);
        userDto.setSecurityAnswer2(updSecurityAnswer2);
        return userDto;
    }

    /*
    I have the following URIs:

        /users - Returns all users
        /users/user/{id} - Return a specific user
        /users/create/{user} - Add user to db
        /users/update/{user} - Update user
        /users/delete/{id} - Delete User
    */

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    private RestTemplate restTemplate = new RestTemplate();

    @Before
    public void setUp()
    {
        this.mockMvc = webAppContextSetup(ctx).build();
    }

    // @Test
    public void testMockGetUserByUserId() throws Exception
    {
        mockMvc.perform(get("/users/userId/1").accept(MediaType.APPLICATION_JSON));
        // .andExpect(status().isOk());
    }

    @Test
    public void testGetUserByUserId() throws Exception
    {
        String url = BASE_URL + "/rest/users/userId/1";
        UserDTO oldUserDto = restTemplate.getForObject(url, UserDTO.class);
        System.out.println("=======================================================================");
        System.out.println("testGetUserByUserId: oldUserDto=" + oldUserDto.toString());
        System.out.println("=======================================================================");
    }

    // @Test
    public void testCreateUser() throws Exception
    {
        UserDTO userDto = createUserDto();
        String url = BASE_URL + "/rest/users/create";
        UserDTO newUserDto = restTemplate.postForObject(url, userDto, UserDTO.class, new Object[]
        {});
        System.out.println("=======================================================================");
        System.out.println("testCreateUser: newUserDto=" + newUserDto.toString());
        System.out.println("=======================================================================");
    }

    @Test
    public void testUpdateUser() throws Exception
    {
        String urlGet = BASE_URL + "/rest/users/userId/9";
        UserDTO oldUserDto = restTemplate.getForObject(urlGet, UserDTO.class);
        System.out.println("=======================================================================");
        System.out.println("testUpdateUser: oldUserDto=" + oldUserDto.toString());
        System.out.println("=======================================================================");

        UserDTO userDto = updateUserDto(oldUserDto);
        HttpEntity<UserDTO> entity = new HttpEntity<UserDTO>(userDto);

        String urlUpdate = BASE_URL + "/rest/users/update";
        restTemplate.put(urlUpdate, entity, UserDTO.class);

        System.out.println("=======================================================================");
        System.out.println("testUpdateUser: newUserDto=");
        System.out.println("=======================================================================");
    }

    // @Test
    public void testDeleteUser() throws Exception
    {
        String url = BASE_URL + "/rest/users/delete/7";
        restTemplate.delete(url);
    }

    /*
    this.mockMvc.perform(
        post("/data/entity")
            .contentType(MediaType.TEXT_PLAIN)
            .content("foo".getBytes()))
        .andExpect(content().string(
                "Posted request body 'foo'; headers = {Content-Type=[text/plain], Content-Length=[3]}"));
     */

    /*
    @Test
    public void UpdateUser() throws Exception
    {
        mockMvc
        .perform(
            put("/users/create").contentType(MediaType.APPLICATION_JSON).body("".getBytes())
            .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(content().type(MediaType.APPLICATION_JSON)).andExpect(content().string(userString));
    }
    */

    @Configuration
    public static class TestConfiguration
    {
        @Bean
        public UserController userController()
        {
            return new UserController();
        }
    }

}
