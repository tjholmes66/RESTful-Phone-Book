package com.opensource.restful.shared;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.opensource.restful.shared.dto.ContactDTO;
import com.opensource.restful.shared.dto.PositionDTO;
import com.opensource.restful.shared.dto.UserDTO;
import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.JSON;

public class JSOMapping
{

    public final static UserDTO convertUser(JavaScriptObject jsObj)
    {
        String jsoText1 = JSON.encode(jsObj);
        System.out.println("JSOMapping: convertUser: START: jsoText=" + jsoText1);

        long userId = 0;
        String strUserId = JSOHelper.getAttribute(jsObj, Constants.USER_ID);
        if (strUserId != null)
        {
            userId = Long.parseLong(strUserId);
        }
        UserDTO userDto = null;

        if (userId != 0)
        {
            userDto = new UserDTO();

            userDto.setUserActive(JSOHelper.getAttributeAsBoolean(jsObj, Constants.USER_ACTIVE));

            Date dateDob2 = getDate(jsObj, Constants.USER_BIRTHDATE);
            userDto.setUserBirthDate(dateDob2);

            userDto.setUserEmail(JSOHelper.getAttribute(jsObj, Constants.USER_EMAIL));
            userDto.setUserFirstName(JSOHelper.getAttribute(jsObj, Constants.USER_FIRST_NAME));
            userDto.setUserId(userId);
            userDto.setUserLastName(JSOHelper.getAttribute(jsObj, Constants.USER_LAST_NAME));
            userDto.setPassword(JSOHelper.getAttribute(jsObj, Constants.USER_PASSWORD));

            userDto.setPosition(convertPosition(jsObj));

            ArrayList<ContactDTO> contactList = new ArrayList<ContactDTO>();
            JavaScriptObject[] jsObjContacts = JSOHelper.getAttributeAsJavaScriptObjectArray(jsObj, "contacts");
            for (JavaScriptObject jsObjContact : jsObjContacts)
            {
                ContactDTO contactDto = convertContact(jsObjContact);
                contactList.add(contactDto);
            }
            userDto.setContacts(contactList);

            userDto.setUserSecurityAnswer1(JSOHelper.getAttribute(jsObj, Constants.USER_SECURITY_ANSWER_1));
            userDto.setUserSecurityAnswer2(JSOHelper.getAttribute(jsObj, Constants.USER_SECURITY_ANSWER_2));
            userDto.setUserSecurityQuestion1(JSOHelper.getAttribute(jsObj, Constants.USER_SECURITY_QUESTION_1));
            userDto.setUserSecurityQuestion2(JSOHelper.getAttribute(jsObj, Constants.USER_SECURITY_QUESTION_2));
            userDto.setUsername(JSOHelper.getAttribute(jsObj, Constants.USER_USERNAME));
        }

        System.out.println("JSOMapping: convertUser: FINISH: userDto=" + userDto);
        return userDto;
    }

    private static ContactDTO convertContact(JavaScriptObject jsObjContact)
    {
        System.out.println("JSOMapping: convertContact: START");

        String[] test1 = JSOHelper.getProperties(jsObjContact);
        System.out.println("JSOMapping: convertContact: test1=" + test1);

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

        System.out.println("JSOMapping: convertContact: FINISH: contactDto=" + contactDto);
        return contactDto;
    }

    private static PositionDTO convertPosition(JavaScriptObject jsObj)
    {
        PositionDTO positionDto = null;
        JavaScriptObject jsObjPosition = (JavaScriptObject) JSOHelper.getAttributeAsObject(jsObj, "position");
        if (jsObjPosition != null)
        {
            positionDto = new PositionDTO();
            positionDto.setActive(JSOHelper.getAttributeAsBoolean(jsObjPosition, "active"));
            positionDto.setCode(JSOHelper.getAttribute(jsObjPosition, "code"));
            positionDto.setDescription(JSOHelper.getAttribute(jsObjPosition, "description"));
            positionDto.setId(JSOHelper.getAttributeAsInt(jsObjPosition, "id"));
        }
        return positionDto;
    }

    private static Date getDate(JavaScriptObject jsObj, String attr)
    {
        String str = JSOHelper.getAttribute(jsObj, attr);
        Date date = null;
        if (str != null)
        {
            String year = str.substring(0, 4);
            String month = str.substring(5, 7);
            String day = str.substring(8, 10);
            date = DateUtil.parseInput(month + "/" + day + "/" + year);
        }
        return date;
    }

}
