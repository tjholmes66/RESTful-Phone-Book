package com.opensource.restful.client.datasource;

import java.util.Date;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.http.client.URL;
import com.opensource.restful.shared.Constants;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.JSON;

public class ContactDataSource extends RestDataSource
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
        // addProps.setContentType("application/json");
        add.setRequestProperties(addProps);

        // set up UPDATE to use PUT
        OperationBinding update = new OperationBinding();
        update.setOperationType(DSOperationType.UPDATE);
        update.setDataProtocol(DSProtocol.POSTMESSAGE);
        // ===========================================
        DSRequest updateProps = new DSRequest();
        updateProps.setHttpMethod("PUT");
        // updateProps.setContentType("application/json");
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

    private DataSourceIntegerField contactIdField;
    // ===================================================
    private DataSourceTextField prefixField;
    private DataSourceTextField firstNameField;
    private DataSourceTextField middleNameField;
    private DataSourceTextField lastNameField;
    private DataSourceTextField suffixField;
    // ===================================================
    private DataSourceTextField address1Field;
    private DataSourceTextField address2Field;
    private DataSourceTextField cityField;
    private DataSourceTextField stateField;
    private DataSourceTextField zipField;
    // ===================================================
    private DataSourceIntegerField editedByField;
    private DataSourceDateField editedDateField;
    private DataSourceIntegerField enteredByField;
    private DataSourceDateField enteredDateField;
    // ===================================================
    private DataSourceDateField birthDateField;

    // ===================================================

    protected void init()
    {
        setDataFormat(DSDataFormat.JSON);
        setJsonRecordXPath("/");

        // set the values for the datasource
        contactIdField = new DataSourceIntegerField(Constants.CONTACT_ID, Constants.TITLE_CONTACT_ID);
        contactIdField.setPrimaryKey(true);
        contactIdField.setCanEdit(false);

        prefixField = new DataSourceTextField(Constants.CONTACT_PREFIX, Constants.TITLE_CONTACT_PREFIX);
        firstNameField = new DataSourceTextField(Constants.CONTACT_FIRST_NAME, Constants.TITLE_CONTACT_FIRST_NAME);
        middleNameField = new DataSourceTextField(Constants.CONTACT_MIDDLE_NAME, Constants.TITLE_CONTACT_MIDDLE_NAME);
        lastNameField = new DataSourceTextField(Constants.CONTACT_LAST_NAME, Constants.TITLE_CONTACT_LAST_NAME);
        suffixField = new DataSourceTextField(Constants.CONTACT_SUFFIX, Constants.TITLE_CONTACT_SUFFIX);

        // ================================================================================================================

        address1Field = new DataSourceTextField(Constants.CONTACT_ADDRESS1, Constants.TITLE_CONTACT_ADDRESS1);
        address2Field = new DataSourceTextField(Constants.CONTACT_ADDRESS2, Constants.TITLE_CONTACT_ADDRESS2);
        cityField = new DataSourceTextField(Constants.CONTACT_CITY, Constants.TITLE_CONTACT_CITY);
        stateField = new DataSourceTextField(Constants.CONTACT_STATE, Constants.TITLE_CONTACT_STATE);
        zipField = new DataSourceTextField(Constants.CONTACT_ZIP, Constants.TITLE_CONTACT_ZIP);

        // ================================================================================================================

        editedByField = new DataSourceIntegerField(Constants.CONTACT_EDITED_BY, Constants.TITLE_CONTACT_EDITED_BY);
        editedDateField = new DataSourceDateField(Constants.CONTACT_EDITED_DATE, Constants.TITLE_CONTACT_EDITED_DATE);
        enteredByField = new DataSourceIntegerField(Constants.CONTACT_ENTERED_BY, Constants.TITLE_CONTACT_ENTERED_BY);
        enteredDateField =
            new DataSourceDateField(Constants.CONTACT_ENTERED_DATE, Constants.TITLE_CONTACT_ENTERED_DATE);

        // ================================================================================================================

        birthDateField = new DataSourceDateField(Constants.CONTACT_BIRTHDATE, Constants.TITLE_CONTACT_BIRTHDATE);

        // ================================================================================================================

        setFields(contactIdField, prefixField, firstNameField, middleNameField, lastNameField, suffixField,
            address1Field, address2Field, cityField, stateField, zipField, editedByField, editedDateField,
            enteredByField, enteredDateField, birthDateField);

        // setFetchDataURL(getServiceRoot() + "/userId/{id}");
        // setFetchDataURL(getServiceRoot() + "/contactId/{id}");
        setAddDataURL(getServiceRoot() + "/create");
        setUpdateDataURL(getServiceRoot() + "/update");
        setRemoveDataURL(getServiceRoot() + "/remove/{id}");
    }

    protected String getServiceRoot()
    {
        return "rest/contacts";
    }

    protected String getPrimaryKeyProperty()
    {
        return "contactId";
    }

    /*
     * Implementers can override this method to create a different override.
     */
    @SuppressWarnings("rawtypes")
    protected void postProcessTransform(DSRequest request)
    {
        System.out.println("ContactDataSource: postProcessTransform: START");

        StringBuilder url = new StringBuilder(getServiceRoot());
        System.out.println("postProcessTransform: url=" + url);

        Map dataMap = request.getAttributeAsMap("data");
        System.out.println("postProcessTransform: dataMap=" + dataMap.toString());
        if (DSOperationType.FETCH.equals(request.getOperationType()) && dataMap.size() > 0)
        {
            if (dataMap.get(Constants.USER_ID) != null)
            {
                url.append("/userId/" + dataMap.get(Constants.USER_ID));
            }
            else if (dataMap.get(Constants.CONTACT_ID) != null)
            {
                url.append("/contactId/" + dataMap.get(Constants.CONTACT_ID));
            }
        }
        else if (DSOperationType.UPDATE.equals(request.getOperationType()))
        {
            url.append("/update");
        }
        else if (DSOperationType.ADD.equals(request.getOperationType()))
        {
            url.append("/create");
        }
        else if (DSOperationType.REMOVE.equals(request.getOperationType()))
        {
            url.append("/delete/" + dataMap.get(Constants.CONTACT_ID));
        }

        System.out.println("ContactDataSource: postProcessTransform: url=" + url.toString());
        request.setActionURL(URL.encode(url.toString()));
    }

    @Override
    protected Object transformRequest(DSRequest dsRequest)
    {
        // now post process the request for our own means
        postProcessTransform(dsRequest);

        System.out.println("ContactDataSource: transformRequest: START");
        dsRequest.setContentType("application/json");
        JavaScriptObject jso = dsRequest.getData();

        String jsoText = JSON.encode(jso);
        System.out.println("ContactDataSource: transformRequest: START: jsoText=" + jsoText);

        String s1 = JSON.encode(jso);
        System.out.println("ContactDataSource: transformRequest: FINISH: s1=" + s1);
        return s1;
    }

    protected void transformResponse(DSResponse response, DSRequest request, Object data)
    {
        System.out.println("ContactDataSource: transformResponse: START");
        JavaScriptObject jso = (JavaScriptObject) data;
        String jsoText1 = JSON.encode(jso);
        System.out.println("ContactDataSource: transformResponse: START: jsoText1=" + jsoText1);

        String jsoText2 = JSON.encode(jso);
        System.out.println("ContactDataSource: transformResponse: FINISH: jsoText2=" + jsoText2);

        super.transformResponse(response, request, data);
        System.out.println("ContactDataSource: transformResponse: FINISH");
    }

    private Date getDate(JavaScriptObject jsObj, String attr)
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