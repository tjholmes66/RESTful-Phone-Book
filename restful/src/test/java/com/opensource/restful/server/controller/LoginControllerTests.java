package com.opensource.restful.server.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensource.restful.domain.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations =
{ "classpath*:applicationContext.xml" })
public class LoginControllerTests extends TestCase
{
    public final static String BASE_URL = "http://127.0.0.1:8888/";

    public final static String DATE_FORMAT = "yyyy-MM-dd";

    private final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    private final static Log logger = LogFactory.getLog(LoginControllerTests.class);

    @Autowired
    private WebApplicationContext wac;

    public WebApplicationContext getWac()
    {
        return wac;
    }

    public void setWac(WebApplicationContext wac)
    {
        this.wac = wac;
    }

    private MockMvc mockMvc;

    @Autowired
    LoginController loginController;

    private RestTemplate restTemplate = new RestTemplate();

    @Before
    public void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    // @Test
    public void testMockGetInvoices() throws Exception
    {
        // SearchInvoiceDTO searchInvoiceDTO = new SearchInvoiceDTO();
        // searchInvoiceDTO.setCustomerCode("QA");
        // searchInvoiceDTO.setCustomerId(8);
        // searchInvoiceDTO.setDdUserId(301010651);
        // searchInvoiceDTO.setUserId(3);

        // test={"search":null,"userId":3,"ddUserId":301010651,"customerId":8,"customerCode":"QA","customerTypeCode":null}
        // {"search":null,"userId":3,"ddUserId":301010651,"customerId":8,"customerCode":"QA","customerTypeCode":null}

        ObjectMapper mapper = new ObjectMapper();
        System.out.println("======================================================================");
        // mapper.writeValue(System.out, searchInvoiceDTO);
        System.out.println("======================================================================");

        System.out.println("======================================================================");
        // String test = mapper.writeValueAsString(searchInvoiceDTO);
        String test = "";
        System.out.println("test=" + test);
        System.out.println("======================================================================");

        test = "{\"userId\":3, \"ddUserId\":301010651, \"customerCode\":\"QA\", \"customerId\":8}";
        System.out.println("======================================================================");
        System.out.println("test=" + test);
        System.out.println("======================================================================");

        /*
        mockMvc.perform(get("/invoices/searchAndCount/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        
        mockMvc.perform(get("/invoices/searchAndCount/").accept(MediaType.APPLICATION_JSON));
        
        this.mockMvc.perform(get("/invoices/searchAndCount").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andDo(print());
        */

        MockHttpServletRequestBuilder requestBuilder =
            MockMvcRequestBuilders.get("/pendingInvoices/searchAndCount").contentType(MediaType.APPLICATION_JSON)
            .content(test);

        // requestBuilder.header("Access-Control-Allow-Origin", "*");

        this.mockMvc.perform(requestBuilder).andDo(print());
    }

    // App must be runnin for this to work ....
    // @Test
    public void testGetInvoicesusingRest() throws Exception
    {
        String url = BASE_URL + "/rest/pendingInvoices/searchAndCount/";

        Object test = restTemplate.getForEntity(url, UserEntity.class);

        System.out.println("=======================================================================");
        System.out.println("testRetrieveUserJSON: test=" + test);
        System.out.println("=======================================================================");
    }

    @Test
    public void getInvoices()
    {
        assertEquals(true, true);
    }

    // @Test
    public void testUserJSONtoObjectNew() throws IOException, ParseException
    {

        // SearchInvoiceDTO searchInvoiceDTO = new SearchInvoiceDTO();
        // searchInvoiceDTO.setCustomerCode("QA");
        // searchInvoiceDTO.setCustomerId(8);
        // searchInvoiceDTO.setDdUserId(301010651);
        // searchInvoiceDTO.setUserId(3);

        // test={"search":null,"userId":3,"ddUserId":301010651,"customerId":8,"customerCode":"QA","customerTypeCode":null}
        // {"search":null,"userId":3,"ddUserId":301010651,"customerId":8,"customerCode":"QA","customerTypeCode":null}

        ObjectMapper mapper = new ObjectMapper();
        System.out.println("======================================================================");
        // String test = mapper.writeValueAsString(searchInvoiceDTO);
        String test = "";
        System.out.println("test=" + test);
        System.out.println("======================================================================");

        test = "{\"userId\":3, \"ddUserId\":301010651, \"customerCode\":\"QA\", \"customerId\":8}";

        test =
            "{\"search\":null,\"userId\":3,\"ddUserId\":301010651,\"customerId\":8,\"customerCode\":\"QA\",\"customerTypeCode\":null}";
        System.out.println("======================================================================");
        System.out.println("test=" + test);
        System.out.println("======================================================================");

        System.out.println("=======================================================================");
        // SearchInvoiceDTO searchInvoiceDto2 = mapper.readValue(test, SearchInvoiceDTO.class);
        System.out.println("=======================================================================");
        // System.out.println("SearchInvoiceDTO2=" + searchInvoiceDto2.toString());
        System.out.println("=======================================================================");
    }

    // @Test
    public void testMockPostInvoices() throws Exception
    {

        /*
        SearchInvoiceDTO searchInvoiceDTO = new SearchInvoiceDTO();
        searchInvoiceDTO.setCustomerCode("QA");
        searchInvoiceDTO.setCustomerId(8);
        searchInvoiceDTO.setDdUserId(301010651);
        searchInvoiceDTO.setUserId(3);
        */

        // test={"search":null,"userId":3,"ddUserId":301010651,"customerId":8,"customerCode":"QA","customerTypeCode":null}
        // {"search":null,"userId":3,"ddUserId":301010651,"customerId":8,"customerCode":"QA","customerTypeCode":null}

        ObjectMapper mapper = new ObjectMapper();
        System.out.println("======================================================================");
        // mapper.writeValue(System.out, searchInvoiceDTO);
        System.out.println("======================================================================");

        System.out.println("======================================================================");
        // String test = mapper.writeValueAsString(searchInvoiceDTO);
        String test = "";
        System.out.println("test=" + test);
        System.out.println("======================================================================");

        test = "{\"userId\":3, \"ddUserId\":301010651, \"customerCode\":\"QA\", \"customerId\":8}";
        System.out.println("======================================================================");
        System.out.println("test=" + test);
        System.out.println("======================================================================");

        /*
        mockMvc.perform(get("/invoices/searchAndCount/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        
        mockMvc.perform(get("/invoices/searchAndCount/").accept(MediaType.APPLICATION_JSON));
        
        this.mockMvc.perform(get("/invoices/searchAndCount").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andDo(print());
        */

        MockHttpServletRequestBuilder requestBuilder =
            MockMvcRequestBuilders.post("/pendingInvoices/searchAndCount").contentType(MediaType.APPLICATION_JSON)
            .content(test);

        // requestBuilder.header("Access-Control-Allow-Origin", "*");

        this.mockMvc.perform(requestBuilder).andDo(print());
    }

    /*
    @Test
    public void testMockGetUserByUserId() throws Exception
    {
        mockMvc.perform(get("/users/userId/1").accept(MediaType.APPLICATION_JSON)).andDo(print());
        // .andExpect(status().isOk());
    }
    */

    @Test
    public void testMockGetUserByUsernameAndPassword1() throws Exception
    {
        MockHttpServletRequestBuilder requestBuilder =
            MockMvcRequestBuilders.get("/login/user/{username}/pwd/{password}");

        requestBuilder.param("username", "tholmes");
        requestBuilder.param("password", "pass1");

        this.mockMvc.perform(requestBuilder).andDo(print());
    }

    @Test
    public void testMockGetUserByUsernameAndPassword2() throws Exception
    {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/login/user/tholmes/pwd/pass1");
        this.mockMvc.perform(requestBuilder).andDo(print());
    }

    // @Test
    public void testMockGetUserByEmail() throws Exception
    {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/email/tom@tomholmes.net");
        this.mockMvc.perform(requestBuilder).andDo(print());
    }

    // App must be running for this to work ....
    // @Test
    public void testGetInvoicesusingRestNEW() throws Exception
    {
        String url = BASE_URL + "/rest/user/userId/1";

        Object test = restTemplate.getForEntity(url, UserEntity.class);

        System.out.println("=======================================================================");
        System.out.println("testRetrieveUserJSON: test=" + test);
        System.out.println("=======================================================================");
    }

    // @Test
    /*
    public void testUserJSONtoObjectNew() throws IOException, ParseException
    {

        SearchInvoiceDTO searchInvoiceDTO = new SearchInvoiceDTO();
        searchInvoiceDTO.setCustomerCode("QA");
        searchInvoiceDTO.setCustomerId(8);
        searchInvoiceDTO.setDdUserId(301010651);
        searchInvoiceDTO.setUserId(3);

        // test={"search":null,"userId":3,"ddUserId":301010651,"customerId":8,"customerCode":"QA","customerTypeCode":null}
        // {"search":null,"userId":3,"ddUserId":301010651,"customerId":8,"customerCode":"QA","customerTypeCode":null}

        ObjectMapper mapper = new ObjectMapper();
        System.out.println("======================================================================");
        String test = mapper.writeValueAsString(searchInvoiceDTO);
        System.out.println("test=" + test);
        System.out.println("======================================================================");

        test = "{\"userId\":3, \"ddUserId\":301010651, \"customerCode\":\"QA\", \"customerId\":8}";

        test =
            "{\"search\":null,\"userId\":3,\"ddUserId\":301010651,\"customerId\":8,\"customerCode\":\"QA\",\"customerTypeCode\":null}";
        System.out.println("======================================================================");
        System.out.println("test=" + test);
        System.out.println("======================================================================");

        System.out.println("=======================================================================");
        SearchInvoiceDTO searchInvoiceDto2 = mapper.readValue(test, SearchInvoiceDTO.class);
        System.out.println("=======================================================================");
        System.out.println("SearchInvoiceDTO2=" + searchInvoiceDto2.toString());
        System.out.println("=======================================================================");
    }
    */
}
