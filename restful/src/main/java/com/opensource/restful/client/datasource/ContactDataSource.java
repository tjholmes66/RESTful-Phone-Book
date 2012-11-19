package com.opensource.restful.client.datasource;

import com.opensource.restful.shared.AbstractRestDataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;

public class ContactDataSource extends AbstractRestDataSource
{
    private static ContactDataSource instance = null;

    public static ContactDataSource getInstance()
    {
        if (instance == null)
        {
            instance = new ContactDataSource("contactEditDS");
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

        DataSourceField idField = new DataSourceField("id", FieldType.INTEGER, "ID");
        idField.setPrimaryKey(true);
        idField.setCanEdit(false);
        DataSourceField nameField = new DataSourceField("name", FieldType.TEXT, "Name");

        setFields(idField, nameField);
    }

    @Override
    protected String getServiceRoot()
    {
        return "contact/";
    }
}