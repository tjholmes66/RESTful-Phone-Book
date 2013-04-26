package com.opensource.restful.client.datasource;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.http.client.URL;
import com.opensource.restful.shared.Constants;
import com.opensource.restful.shared.dto.ContactDTO;
import com.opensource.restful.shared.dto.PositionDTO;
import com.opensource.restful.shared.dto.UserDTO;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.util.JSOHelper;

public class LoginDataSource extends AbstractRestDataSource
{
    private static LoginDataSource instance = null;

    public static LoginDataSource getInstance()
    {
        if (instance == null)
        {
            instance = new LoginDataSource("restLoginDS");
        }
        return instance;
    }

    private LoginDataSource(String id)
    {
        super(id);
    }

    protected void init()
    {
        System.out.println("init: START");

        setDataFormat(DSDataFormat.JSON);
        setJsonRecordXPath("/");

        // set the values for the datasource
        DataSourceTextField Id = new DataSourceTextField(Constants.USER_ID, Constants.TITLE_USER_ID);
        Id.setPrimaryKey(true);
        Id.setCanEdit(false);

        DataSourceTextField username = new DataSourceTextField(Constants.USER_USERNAME, Constants.TITLE_USER_USERNAME);
        username.setCanEdit(false);

        DataSourceTextField password = new DataSourceTextField(Constants.USER_PASSWORD, Constants.TITLE_USER_PASSWORD);
        password.setCanEdit(false);

        System.out.println("init: FINISH");
        setFields(Id, username, password);
    }

    @Override
    protected String getServiceRoot()
    {
        return "rest/login/";
    }

    @Override
    protected String getPrimaryKeyProperty()
    {
        return "username";
    }

    /*
     * Implementers can override this method to create a 
     * different override.
     */
    @SuppressWarnings("rawtypes")
    protected void postProcessTransform(DSRequest request)
    {
        System.out.println("LoginDataSource: postProcessTransform: START");

        StringBuilder url = new StringBuilder(getServiceRoot());
        System.out.println("postProcessTransform: url=" + url);

        Map dataMap = request.getAttributeAsMap("data");
        System.out.println("postProcessTransform: dataMap=" + dataMap.toString());
        if (request.getOperationType() == DSOperationType.FETCH && dataMap.size() > 0)
        {
            url.append("user/" + dataMap.get(Constants.USER_USERNAME));
            url.append("/pwd/" + dataMap.get(Constants.USER_PASSWORD));
        }

        System.out.println("LoginDataSource: postProcessTransform: url=" + url.toString());
        request.setActionURL(URL.encode(url.toString()));
    }

    protected void transformResponse(DSResponse response, DSRequest request, Object jsonData)
    {
        System.out.println("LoginDataSource: transformResponse: START");
        JavaScriptObject jsObj = (JavaScriptObject) jsonData;

        long userId = 0;
        String strUserId = JSOHelper.getAttribute(jsObj, "userId");
        if (strUserId != null)
        {
            userId = Long.parseLong(strUserId);
        }
        UserDTO userDto = null;
        System.out.println("LoginDataSource: transformResponse: userDto=" + userDto);

        if (userId != 0)
        {
            userDto = new UserDTO();

            String[] test = JSOHelper.getProperties(jsObj);
            String attrs = response.getAttribute("data");

            userDto.setUserActive(JSOHelper.getAttributeAsBoolean(jsObj, Constants.USER_ACTIVE));

            String strDob = JSOHelper.getAttribute(jsObj, Constants.USER_BIRTHDATE);
            Date birthdate = null;
            if (strDob != null)
            {
                long longDob = Long.parseLong(strDob);
                birthdate = new Date(longDob);
            }

            userDto.setUserBirthDate(birthdate);

            userDto.setUserEmail(JSOHelper.getAttribute(jsObj, Constants.USER_EMAIL));
            userDto.setUserFirstName(JSOHelper.getAttribute(jsObj, Constants.USER_FIRST_NAME));
            userDto.setUserId(userId);
            userDto.setUserLastName(JSOHelper.getAttribute(jsObj, Constants.USER_FIRST_NAME));
            userDto.setPassword(JSOHelper.getAttribute(jsObj, Constants.USER_LAST_NAME));

            JavaScriptObject jsObjPosition = (JavaScriptObject) JSOHelper.getAttributeAsObject(jsObj, "position");
            if (jsObjPosition != null)
            {
                PositionDTO positionDto = new PositionDTO();
                positionDto.setActive(JSOHelper.getAttributeAsBoolean(jsObjPosition, "active"));
                positionDto.setCode(JSOHelper.getAttribute(jsObjPosition, "code"));
                positionDto.setDescription(JSOHelper.getAttribute(jsObjPosition, "description"));
                positionDto.setId(JSOHelper.getAttributeAsInt(jsObjPosition, "id"));
                userDto.setPosition(positionDto);
            }

            ArrayList<ContactDTO> contactList = new ArrayList<ContactDTO>();
            JavaScriptObject[] jsObjContacts = JSOHelper.getAttributeAsJavaScriptObjectArray(jsObj, "contacts");
            for (JavaScriptObject jsObjContact : jsObjContacts)
            {
                String[] test1 = JSOHelper.getProperties(jsObjContact);
                System.out.println("test1=" + test1);

                ContactDTO contactDto = new ContactDTO();
                contactDto.setAddress1(JSOHelper.getAttribute(jsObjContact, "address1"));
                contactDto.setAddress2(JSOHelper.getAttribute(jsObjContact, "address2"));
                contactDto.setBirthDate(getDate(jsObjContact, "birthDate"));
                contactDto.setCity(JSOHelper.getAttribute(jsObjContact, "city"));
                contactDto.setCompanyId(JSOHelper.getAttributeAsInt(jsObjContact, "companyId"));
                contactDto.setContactId(JSOHelper.getAttributeAsInt(jsObjContact, "contactId"));
                contactDto.setEditedBy(JSOHelper.getAttributeAsInt(jsObjContact, "editedBy"));
                contactDto.setEditedDate(getDate(jsObjContact, "editedDate"));
                // contactDto.setEmails(emails)
                // contactDto.setEmails(phones)
                // contactDto.setEmails(links)
                contactDto.setEnteredBy(JSOHelper.getAttributeAsInt(jsObjContact, "enteredBy"));
                contactDto.setEnteredDate(getDate(jsObjContact, "enteredDate"));
                contactDto.setFirstName(JSOHelper.getAttribute(jsObjContact, "firstName"));
                contactDto.setLastName(JSOHelper.getAttribute(jsObjContact, "lastName"));
                contactDto.setMiddleName(JSOHelper.getAttribute(jsObjContact, "middleName"));
                contactDto.setPrefix(JSOHelper.getAttribute(jsObjContact, "prefix"));
                contactDto.setState(JSOHelper.getAttribute(jsObjContact, "state"));
                contactDto.setSuffix(JSOHelper.getAttribute(jsObjContact, "suffix"));
                contactDto.setUserId(JSOHelper.getAttributeAsInt(jsObjContact, "userId"));
                contactDto.setZip(JSOHelper.getAttribute(jsObjContact, "zip"));
                contactList.add(contactDto);
            }
            userDto.setContacts(contactList);

            userDto.setUserSecurityAnswer1(JSOHelper.getAttribute(jsObj, Constants.USER_SECURITY_ANSWER_1));
            userDto.setUserSecurityAnswer2(JSOHelper.getAttribute(jsObj, Constants.USER_SECURITY_ANSWER_2));
            userDto.setUserSecurityQuestion1(JSOHelper.getAttribute(jsObj, Constants.USER_SECURITY_QUESTION_1));
            userDto.setUserSecurityQuestion2(JSOHelper.getAttribute(jsObj, Constants.USER_SECURITY_QUESTION_2));
            userDto.setUsername(JSOHelper.getAttribute(jsObj, "username"));
        }

        System.out.println("LoginDataSource: transformResponse: finished: userDto=" + userDto);
        response.setAttribute("user", userDto);

        System.out.println("LoginDataSource: super.transformResponse: jsonData=" + jsonData);
        super.transformResponse(response, request, jsonData);
    }

    private Date getDate(JavaScriptObject jsObj, String attr)
    {
        String str = JSOHelper.getAttribute(jsObj, attr);
        Date date = null;
        if (str != null)
        {
            long longDate = Long.parseLong(str);
            date = new Date(longDate);
        }
        return date;
    }
}
