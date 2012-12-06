package com.opensource.restful.client.datasource;

import com.opensource.restful.shared.Constants;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;

public class ContactDataSource extends AbstractRestDataSource
{
    private static ContactDataSource instance = null;

    public static ContactDataSource getInstance()
    {
        if (instance == null)
        {
            instance = new ContactDataSource("restContactDS");
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
        DataSourceTextField Id = new DataSourceTextField(Constants.CONTACT_ID, Constants.TITLE_CONTACT_ID);
        Id.setPrimaryKey(true);
        Id.setCanEdit(false);

        DataSourceTextField lastName =
            new DataSourceTextField(Constants.CONTACT_LAST_NAME, Constants.TITLE_CONTACT_LAST_NAME);
        lastName.setCanEdit(false);

        DataSourceTextField firstName =
            new DataSourceTextField(Constants.CONTACT_FIRST_NAME, Constants.TITLE_CONTACT_FIRST_NAME);
        firstName.setCanEdit(false);

        setFields(Id, lastName, firstName);
    }

    @Override
    protected String getServiceRoot()
    {
        return "rest/contact/";
    }

    @Override
    protected String getPrimaryKeyProperty()
    {
        return "contactId";
    }
}