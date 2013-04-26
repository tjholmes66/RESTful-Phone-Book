package com.opensource.restful.client.datasource;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.opensource.restful.shared.Constants;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.JSON;

public class UserDataSource extends RestDataSource
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
        setID(id);
        setClientOnly(false);

        // set up FETCH to use GET requests
        OperationBinding fetch = new OperationBinding();
        fetch.setOperationType(DSOperationType.FETCH);
        fetch.setDataProtocol(DSProtocol.GETPARAMS);
        DSRequest fetchProps = new DSRequest();
        fetchProps.setHttpMethod("GET");
        fetch.setRequestProperties(fetchProps);

        // set up ADD to use POST requests
        OperationBinding add = new OperationBinding();
        add.setOperationType(DSOperationType.ADD);
        add.setDataProtocol(DSProtocol.POSTMESSAGE);
        // ===========================================
        DSRequest addProps = new DSRequest();
        addProps.setHttpMethod("POST");
        addProps.setContentType("application/json");
        add.setRequestProperties(addProps);

        // set up UPDATE to use PUT
        OperationBinding update = new OperationBinding();
        update.setOperationType(DSOperationType.UPDATE);
        update.setDataProtocol(DSProtocol.POSTMESSAGE);
        // ===========================================
        DSRequest updateProps = new DSRequest();
        updateProps.setHttpMethod("PUT");
        updateProps.setContentType("application/json");
        update.setRequestProperties(updateProps);

        // set up REMOVE to use DELETE
        OperationBinding remove = new OperationBinding();
        remove.setOperationType(DSOperationType.REMOVE);
        DSRequest removeProps = new DSRequest();
        removeProps.setHttpMethod("DELETE");
        remove.setRequestProperties(removeProps);

        // apply all the operational bindings
        setOperationBindings(fetch, add, update, remove);

        init();
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

        setFetchDataURL(getServiceRoot() + "/userId/{id}");
        setAddDataURL(getServiceRoot() + "/add");
        setUpdateDataURL(getServiceRoot() + "/update");
        setRemoveDataURL(getServiceRoot() + "/remove");
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
    protected Object transformRequest(DSRequest dsRequest)
    {
        System.out.println("UserDataSource: transformRequest: START");
        dsRequest.setContentType("application/json");
        JavaScriptObject jso = dsRequest.getData();
        String jsoText = JSON.encode(jso);
        System.out.println("UserDataSource: transformRequest: START: jsoText=" + jsoText);

        // get the user position id which comes from the UI
        // the name of this field from the UI 'userPositionId'
        String userPositionId = JSOHelper.getAttribute(jso, Constants.USER_POSITION_ID);

        // create a small JavaScriptObject to be used for the position
        // the JSON string would look like {"id":x} x = userPositionId
        Map mapPositionId = new HashMap();
        mapPositionId.put("id", userPositionId);
        JavaScriptObject jsoPositionId = JSOHelper.convertMapToJavascriptObject(mapPositionId);

        // This creates the new JSON attribute:
        // ... , "position":{"id":x}
        JSOHelper.setAttribute(jso, "position", jsoPositionId);

        // remove the JSON Attribute: ... , "userPositionId":x
        JSOHelper.deleteAttribute(jso, Constants.USER_POSITION_ID);

        String s1 = JSON.encode(jso);
        System.out.println("UserDataSource: transformRequest: FINISH: s1=" + s1);
        return s1;
        // return super.transformRequest(dsRequest);
    }

    @Override
    protected void transformResponse(DSResponse response, DSRequest request, Object data)
    {
        System.out.println("UserDataSource: transformResponse: START");
        super.transformResponse(response, request, data);
        System.out.println("UserDataSource: transformResponse: FINISH");
    }

}
