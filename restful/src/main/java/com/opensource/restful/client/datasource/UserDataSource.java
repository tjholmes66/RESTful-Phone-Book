package com.opensource.restful.client.datasource;

import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.http.client.URL;
import com.opensource.restful.shared.Constants;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.util.JSOHelper;

public class UserDataSource extends AbstractRestDataSource
{
    private static UserDataSource instance = null;

    public static UserDataSource getInstance()
    {
        if (instance == null)
        {
            instance = new UserDataSource("restUserDS");
        }

        return instance;
    }

    private UserDataSource(String id)
    {
        super(id);
    }

    private DataSourceIntegerField userIdField;
    private DataSourceBooleanField userActiveField;
    private DataSourceTextField usernameField;
    private DataSourceTextField passwordField;
    private DataSourceTextField firstnameField;
    private DataSourceTextField lastnameField;
    private DataSourceTextField emailField;
    private DataSourceTextField securityQuestion1Field;
    private DataSourceTextField securityAnswer1Field;
    private DataSourceTextField securityQuestion2Field;
    private DataSourceTextField securityAnswer2Field;
    private DataSourceDateField birthdateField;

    private DataSourceIntegerField positionIdField;

    // private DataSourceBooleanField positionActiveField;
    // private DataSourceTextField positionCodeField;
    // private DataSourceTextField positionDescriptionField;

    protected void init()
    {
        setDataFormat(DSDataFormat.JSON);
        setJsonRecordXPath("/");

        // set the values for the datasource
        userIdField = new DataSourceIntegerField(Constants.USER_ID, Constants.TITLE_USER_ID);
        userIdField.setPrimaryKey(true);
        userIdField.setCanEdit(false);

        userActiveField = new DataSourceBooleanField(Constants.USER_ACTIVE, Constants.TITLE_USER_ACTIVE);

        usernameField = new DataSourceTextField(Constants.USER_USERNAME, Constants.TITLE_USER_USERNAME);
        passwordField = new DataSourceTextField(Constants.USER_PASSWORD, Constants.TITLE_USER_PASSWORD);

        firstnameField = new DataSourceTextField(Constants.USER_FIRST_NAME, Constants.TITLE_USER_FIRST_NAME);
        lastnameField = new DataSourceTextField(Constants.USER_LAST_NAME, Constants.TITLE_USER_LAST_NAME);

        emailField = new DataSourceTextField(Constants.USER_EMAIL, Constants.TITLE_USER_EMAIL);

        securityQuestion1Field =
            new DataSourceTextField(Constants.USER_SECURITY_QUESTION_1, Constants.TITLE_USER_SECURITY_QUESTION_1);
        securityAnswer1Field =
            new DataSourceTextField(Constants.USER_SECURITY_ANSWER_1, Constants.TITLE_USER_SECURITY_ANSWER_1);
        securityQuestion2Field =
            new DataSourceTextField(Constants.USER_SECURITY_QUESTION_2, Constants.TITLE_USER_SECURITY_QUESTION_2);
        securityAnswer2Field =
            new DataSourceTextField(Constants.USER_SECURITY_ANSWER_2, Constants.TITLE_USER_SECURITY_ANSWER_2);

        birthdateField = new DataSourceDateField(Constants.USER_BIRTHDATE, Constants.TITLE_USER_BIRTHDATE);

        positionIdField = new DataSourceIntegerField(Constants.USER_POSITION_ID, Constants.TITLE_USER_POSITION_ID);
        // positionActiveField = new DataSourceBooleanField(Constants.USER_ACTIVE, Constants.TITLE_USER_ACTIVE);
        // positionCodeField;
        // positionDescriptionField;

        setFields(userIdField, userActiveField, usernameField, passwordField, firstnameField, lastnameField,
            emailField, birthdateField, securityQuestion1Field, securityAnswer1Field, securityQuestion2Field,
            securityAnswer2Field, positionIdField);
    }

    protected String getServiceRoot()
    {
        return "rest/users/";
    }

    protected String getPrimaryKeyProperty()
    {
        return "userId";
    }

    @Override
    protected Object transformRequest(DSRequest request)
    {
        System.out.println("UserDataSource: transformRequest: ");
        JavaScriptObject jsObjOld = (JavaScriptObject) request.getData();
        String[] test1 = JSOHelper.getProperties(jsObjOld);

        Map valueMap = (Map) JSOHelper.convertToMap(jsObjOld);
        valueMap.remove("__ref");

        JavaScriptObject jsObjNew = JSOHelper.convertMapToJavascriptObject(valueMap);
        request.setAttribute("data", jsObjNew);
        request.setData(jsObjNew);

        String[] test2 = JSOHelper.getProperties(jsObjNew);

        super.transformRequest(request);

        // now post process the request for our own means
        postProcessTransform(request);

        return request.getData();
    }

    /*
     * Implementers can override this method to create a 
     * different override.
     */
    @SuppressWarnings("rawtypes")
    protected void postProcessTransform(DSRequest request)
    {
        StringBuilder url = new StringBuilder(getServiceRoot());

        Map dataMap = request.getAttributeAsMap("data");
        if (request.getOperationType() == DSOperationType.FETCH && dataMap.size() > 0)
        {
            url.append("user/" + dataMap.get(Constants.USER_USERNAME));
            url.append("/pwd/" + dataMap.get(Constants.USER_PASSWORD));
        }

        request.setActionURL(URL.encode(url.toString()));
    }
}
