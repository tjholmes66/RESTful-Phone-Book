package com.opensource.restful.client.datasource;

import com.opensource.restful.shared.Constants;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;

public class ContactEmailDataSource extends AbstractRestDataSource
{
    private static ContactEmailDataSource instance = null;

    public static ContactEmailDataSource getInstance()
    {
        if (instance == null)
        {
            instance = new ContactEmailDataSource("restContactEmailDS");
        }

        return instance;
    }

    private ContactEmailDataSource(String id)
    {
        super(id);
    }

    private DataSourceIntegerField emailIdField;
    private DataSourceIntegerField contactIdField;
    private DataSourceTextField emailField;
    private DataSourceDateField enteredDateField;

    private DataSourceIntegerField emailTypeIdField;
    // private DataSourceTextField emailTypeDescriptionField;
    // private DataSourceBooleanField emailTypeActiveField;

    public static final String C_EMAIL_TYPE_ID = "contactEmailEmailTypeId";
    public static final String C_EMAIL_ENTERED_DATE = "contactEmailEnteredDate";
    public static final String C_EMAIL = "contactEmail";

    protected void init()
    {
        setDataFormat(DSDataFormat.JSON);
        setJsonRecordXPath("/");

        // set the values for the datasource
        emailIdField = new DataSourceIntegerField(Constants.C_EMAIL_ID, Constants.TITLE_C_EMAIL_ID);
        emailIdField.setPrimaryKey(true);
        emailIdField.setCanEdit(false);

        contactIdField = new DataSourceIntegerField(Constants.C_EMAIL_CONTACT_ID, Constants.TITLE_C_EMAIL_CONTACT_ID);

        emailField = new DataSourceTextField(Constants.C_EMAIL, Constants.TITLE_C_EMAIL);

        enteredDateField =
            new DataSourceDateField(Constants.C_EMAIL_ENTERED_DATE, Constants.TITLE_C_EMAIL_ENTERED_DATE);

        emailTypeIdField = new DataSourceIntegerField(Constants.C_EMAIL_TYPE_ID, Constants.TITLE_C_EMAIL_TYPE_ID);

        setFields(emailIdField, contactIdField, emailField, enteredDateField, emailTypeIdField);
    }

    @Override
    protected String getServiceRoot()
    {
        return "rest/contact/email";
    }

    @Override
    protected String getPrimaryKeyProperty()
    {
        return "emailId";
    }
}