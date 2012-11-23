package com.opensource.restful.client.datasource;

import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;

public class ContactDataSource extends AbstractRestDataSource
{
    private static ContactDataSource instance = null;

    public static ContactDataSource getInstance()
    {
        if (instance == null)
        {
            instance = new ContactDataSource("personEditDS");
        }

        return instance;
    }

    private ContactDataSource(String id)
    {
        super(id);
    }

    protected void init()
    {
        setDataFormat(DSDataFormat.JSON);
        setJsonRecordXPath("/");

        // set the values for the datasource
        DataSourceTextField Id = new DataSourceTextField("id", "Id");
        Id.setPrimaryKey(true);
        Id.setCanEdit(false);

        DataSourceTextField lastName = new DataSourceTextField("lastName", "Last Name");
        lastName.setCanEdit(false);

        DataSourceTextField firstName = new DataSourceTextField("firstName", "First Name");
        firstName.setCanEdit(false);

        setFields(Id, lastName, firstName);
    }

    @Override
    protected String getServiceRoot()
    {
        return "rest/contact/";
    }
}