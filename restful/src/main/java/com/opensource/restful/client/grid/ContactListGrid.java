package com.opensource.restful.client.grid;

import com.opensource.restful.client.datasource.ContactDataSource;
import com.opensource.restful.shared.Constants;
import com.opensource.restful.shared.dto.UserDTO;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

public class ContactListGrid extends ListGrid
{
    private final static int GRID_PADDING = 10;

    private final ContactDataSource contactDS = ContactDataSource.getInstance();

    private ListGridField phoneContactIdField = new ListGridField(Constants.C_PHONE_CONTACT_ID,
        Constants.TITLE_C_PHONE_CONTACT_ID);
    private ListGridField phoneIdField = new ListGridField(Constants.C_PHONE_ID, Constants.TITLE_C_PHONE_ID);
    private ListGridField phoneField = new ListGridField(Constants.C_PHONE, Constants.TITLE_C_PHONE);
    private ListGridField phoneTypeField =
        new ListGridField(Constants.C_PHONE_TYPE_ID, Constants.TITLE_C_PHONE_TYPE_ID);
    private ListGridField phoneEnteredDateField = new ListGridField(Constants.C_PHONE_ENTERED_DATE,
        Constants.TITLE_C_PHONE_ENTERED_DATE);

    private ListGridField emailContactIdField = new ListGridField(Constants.C_EMAIL_CONTACT_ID,
        Constants.TITLE_C_EMAIL_CONTACT_ID);
    private ListGridField emailIdField = new ListGridField(Constants.C_EMAIL_ID, Constants.TITLE_C_EMAIL_ID);
    private ListGridField emailField = new ListGridField(Constants.C_EMAIL, Constants.TITLE_C_EMAIL);
    private ListGridField emailTypeField =
        new ListGridField(Constants.C_EMAIL_TYPE_ID, Constants.TITLE_C_EMAIL_TYPE_ID);
    private ListGridField emailEnteredDateField = new ListGridField(Constants.C_EMAIL_ENTERED_DATE,
        Constants.TITLE_C_EMAIL_ENTERED_DATE);

    private ListGridField linkContactIdField = new ListGridField(Constants.C_LINK_CONTACT_ID,
        Constants.TITLE_C_LINK_CONTACT_ID);
    private ListGridField linkIdField = new ListGridField(Constants.C_LINK_ID, Constants.TITLE_C_LINK_ID);
    private ListGridField linkField = new ListGridField(Constants.C_LINK, Constants.TITLE_C_LINK);
    private ListGridField linkDescriptionField = new ListGridField(Constants.C_LINK_DESCRIPTION,
        Constants.TITLE_C_LINK_DESCRIPTION);
    private ListGridField linkTypeField = new ListGridField(Constants.C_LINK_TYPE_ID, Constants.TITLE_C_LINK_TYPE_ID);
    private ListGridField linkEnteredDateField = new ListGridField(Constants.C_LINK_ENTERED_DATE,
        Constants.TITLE_C_LINK_ENTERED_DATE);

    private UserDTO _userDto;
    private String _userId;
    private String _contactId = "0";

    public ContactListGrid(String userId)
    {
        this._userId = userId;

        setWidth100();
        setHeight(200);
        setInitialCriteria(new Criteria(Constants.CONTACT_USER_ID, _userId));
        setDataSource(contactDS);
        setAutoFetchData(true);
        setPadding(GRID_PADDING);

        setBorder("5px solid green");

        ListGridField contactIdField = new ListGridField(Constants.CONTACT_ID, Constants.TITLE_CONTACT_ID);
        contactIdField.setCanEdit(false);
        contactIdField.setHidden(true);

        ListGridField lastnameField = new ListGridField(Constants.CONTACT_LAST_NAME, Constants.TITLE_CONTACT_LAST_NAME);
        ListGridField firstnameField =
            new ListGridField(Constants.CONTACT_FIRST_NAME, Constants.TITLE_CONTACT_FIRST_NAME);
        ListGridField cityField = new ListGridField(Constants.CONTACT_CITY, Constants.TITLE_CONTACT_CITY);
        ListGridField stateField = new ListGridField(Constants.CONTACT_STATE, Constants.TITLE_CONTACT_STATE);
        ListGridField birthdateField =
            new ListGridField(Constants.CONTACT_BIRTHDATE, Constants.TITLE_CONTACT_BIRTHDATE);

        setFields(lastnameField, firstnameField, cityField, stateField, birthdateField, contactIdField);

        addRecordClickHandler(new RecordClickHandler()
        {
            @Override
            public void onRecordClick(RecordClickEvent event)
            {
                _contactId = event.getRecord().getAttribute(Constants.CONTACT_ID);
            }
        });
    }

}
