package com.opensource.restful.client.datasource;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class UserRecord extends ListGridRecord
{
    // User Form Constants
    public final static String USER_ID = "userId";
    public final static String USER_ACTIVE = "userActive";
    public final static String USER_POSITION_ID = "userPositionId";
    public final static String USER_USERNAME = "username";
    public final static String USER_PASSWORD = "password";
    public final static String USER_OTHER_PASSWORD = "otherPassword";
    public final static String USER_FIRST_NAME = "userFirstName";
    public final static String USER_LAST_NAME = "userLastName";
    public final static String USER_EMAIL = "userEmail";
    public static final String USER_SECURITY_QUESTION_1 = "userSecurityQuestion1";
    public static final String USER_SECURITY_ANSWER_1 = "userSecurityAnswer1";
    public static final String USER_SECURITY_QUESTION_2 = "userSecurityQuestion2";
    public static final String USER_SECURITY_ANSWER_2 = "userSecurityAnswer2";
    public static final String USER_BIRTHDATE = "userBirthDate";

    public UserRecord()
    {
    }

    public UserRecord(int userId, boolean userActive, String username, String password)
    {
        setUserId(userId);
        setUserActive(userActive);
        setUsername(username);
        setPassword(password);
    }

    public UserRecord(int userId, boolean userActive, String username, String password, String otherPassword,
        int positionId, String firstName, String lastName, String userEmail, String birthDate,
        String securityQuestion1, String securityAnswer1, String securityQuestion2, String securityAnswer2)
    {
        setUserId(userId);
        setUserActive(userActive);
        setUsername(username);
        setPassword(password);
        setOtherPassword(otherPassword);
        setPositionId(positionId);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(userEmail);
        setSecurityQuestion1(securityQuestion1);
        setSecurityAnswer1(securityAnswer1);
        setSecurityQuestion2(securityQuestion2);
        setSecurityAnswer2(securityAnswer2);
    }

    public void setUserId(int userId)
    {
        setAttribute(USER_ID, userId);
    }

    public int getUserId()
    {
        return getAttributeAsInt(USER_ID);
    }

    public void setUserActive(boolean active)
    {
        setAttribute(USER_ACTIVE, active);
    }

    public boolean getUserActive()
    {
        return getAttributeAsBoolean(USER_ACTIVE);
    }

    public void setUsername(String username)
    {
        setAttribute(USER_USERNAME, username);
    }

    public String getUsername()
    {
        return getAttributeAsString(USER_USERNAME);
    }

    public void setPassword(String password)
    {
        setAttribute(USER_PASSWORD, password);
    }

    public String getPassword()
    {
        return getAttributeAsString(USER_PASSWORD);
    }

    public void setOtherPassword(String otherPassword)
    {
        setAttribute(USER_OTHER_PASSWORD, otherPassword);
    }

    public String getOtherPassword()
    {
        return getAttributeAsString(USER_OTHER_PASSWORD);
    }

    public void setPositionId(int positionId)
    {
        setAttribute(USER_POSITION_ID, positionId);
    }

    public int getPositionId()
    {
        return getAttributeAsInt(USER_POSITION_ID);
    }

    public void setFirstName(String firstname)
    {
        setAttribute(USER_FIRST_NAME, firstname);
    }

    public String getFirstName()
    {
        return getAttributeAsString(USER_FIRST_NAME);
    }

    public void setLastName(String lastname)
    {
        setAttribute(USER_LAST_NAME, lastname);
    }

    public String getLastName()
    {
        return getAttributeAsString(USER_LAST_NAME);
    }

    public void setEmail(String email)
    {
        setAttribute(USER_EMAIL, email);
    }

    public String getEmail()
    {
        return getAttributeAsString(USER_EMAIL);
    }

    public void setSecurityQuestion1(String question1)
    {
        setAttribute(USER_SECURITY_QUESTION_1, question1);
    }

    public String getSecurityQuestion1()
    {
        return getAttributeAsString(USER_SECURITY_QUESTION_1);
    }

    public void setSecurityAnswer1(String answer1)
    {
        setAttribute(USER_SECURITY_ANSWER_1, answer1);
    }

    public String getSecurityAnswer1()
    {
        return getAttributeAsString(USER_SECURITY_ANSWER_1);
    }

    public void setSecurityQuestion2(String question2)
    {
        setAttribute(USER_SECURITY_QUESTION_2, question2);
    }

    public String getSecurityQuestion2()
    {
        return getAttributeAsString(USER_SECURITY_QUESTION_2);
    }

    public void setSecurityAnswer2(String answer2)
    {
        setAttribute(USER_SECURITY_ANSWER_2, answer2);
    }

    public String getSecurityAnswer2()
    {
        return getAttributeAsString(USER_SECURITY_ANSWER_2);
    }

}
