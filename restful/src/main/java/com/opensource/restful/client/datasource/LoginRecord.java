package com.opensource.restful.client.datasource;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class LoginRecord extends ListGridRecord
{
    // User Form Constants
    public final static String USER_ID = "userId";
    public final static String USER_USERNAME = "username";
    public final static String USER_PASSWORD = "password";

    public LoginRecord()
    {
    }

    public LoginRecord(int userId)
    {
        setUserId(userId);
    }

    public LoginRecord(String username, String password)
    {
        setUsername(username);
        setPassword(password);
    }

    public void setUserId(int userId)
    {
        setAttribute(USER_ID, userId);
    }

    public int getUserId()
    {
        return getAttributeAsInt(USER_ID);
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

}
