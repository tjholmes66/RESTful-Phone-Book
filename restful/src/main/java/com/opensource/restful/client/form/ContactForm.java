package com.opensource.restful.client.form;

import java.util.Date;

import com.opensource.restful.client.datasource.ContactDataSource;
import com.opensource.restful.client.widget.DateItemWidget;
import com.opensource.restful.shared.Constants;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ContactForm extends VLayout
{
    private final static int GRID_PADDING = 10;

    private ContactDataSource contactDS = ContactDataSource.getInstance();

    private final DynamicForm contactForm = new DynamicForm();
    private TextItem userIdField;
    private TextItem IdField;
    private TextItem prefixField = new TextItem(Constants.CONTACT_PREFIX);
    private TextItem firstnameField = new TextItem(Constants.CONTACT_FIRST_NAME);
    private TextItem middlenameField = new TextItem(Constants.CONTACT_MIDDLE_NAME);
    private TextItem lastnameField = new TextItem(Constants.CONTACT_LAST_NAME);
    private TextItem suffixField = new TextItem(Constants.CONTACT_SUFFIX);
    private TextItem address1Field = new TextItem(Constants.CONTACT_ADDRESS1);
    private TextItem address2Field = new TextItem(Constants.CONTACT_ADDRESS2);
    private TextItem cityField = new TextItem(Constants.CONTACT_CITY);
    private TextItem stateField = new TextItem(Constants.CONTACT_STATE);
    private TextItem zipField = new TextItem(Constants.CONTACT_ZIP);
    private DateItemWidget birthdateField = new DateItemWidget(Constants.CONTACT_BIRTHDATE,
        Constants.TITLE_CONTACT_BIRTHDATE);

    private TextItem enteredByField = new TextItem(Constants.CONTACT_ENTERED_BY);
    private DateItem enteredDateField = new DateItem(Constants.CONTACT_ENTERED_DATE);
    private TextItem editedByField = new TextItem(Constants.CONTACT_EDITED_BY);
    private DateItem editedDateField = new DateItem(Constants.CONTACT_EDITED_DATE);

    private String contactId = "-1";

    private HLayout buttonLayout = new HLayout();
    private IButton saveButton = new IButton("Save");
    private IButton addnewButton = new IButton("New");

    public ContactForm()
    {
        setWidth(500);
        setHeight("50%");
        setBorder("1px solid green");

        addMember(getContactForm());
        addMember(getButtonLayout());
    }

    private HLayout getButtonLayout()
    {
        // buttonLayout.setBorder("5px solid red");
        buttonLayout.setAlign(Alignment.CENTER);
        buttonLayout.setLayoutAlign(VerticalAlignment.CENTER);

        saveButton.setAlign(Alignment.CENTER);
        saveButton.setValign(VerticalAlignment.CENTER);
        saveButton.addClickHandler(new ClickHandler()
        {

            @Override
            public void onClick(ClickEvent event)
            {
                String contactValidationMessage = getContactValidation(contactForm);
                if (contactValidationMessage == null || "".equals(contactValidationMessage))
                {
                    long contactId = Long.parseLong(IdField.getValueAsString());
                    long userId = Long.parseLong(userIdField.getValueAsString());

                    Record record = new ListGridRecord();
                    record.setAttribute(Constants.CONTACT_ID, contactId);
                    record.setAttribute(Constants.CONTACT_USER_ID, userId);

                    record.setAttribute(Constants.CONTACT_PREFIX, prefixField.getValueAsString());
                    record.setAttribute(Constants.CONTACT_FIRST_NAME, firstnameField.getValueAsString());
                    record.setAttribute(Constants.CONTACT_MIDDLE_NAME, middlenameField.getValueAsString());
                    record.setAttribute(Constants.CONTACT_LAST_NAME, lastnameField.getValueAsString());
                    record.setAttribute(Constants.CONTACT_SUFFIX, suffixField.getValueAsString());

                    record.setAttribute(Constants.CONTACT_ADDRESS1, address1Field.getValueAsString());
                    record.setAttribute(Constants.CONTACT_ADDRESS2, address2Field.getValueAsString());
                    record.setAttribute(Constants.CONTACT_CITY, cityField.getValueAsString());
                    record.setAttribute(Constants.CONTACT_STATE, stateField.getValueAsString());
                    record.setAttribute(Constants.CONTACT_ZIP, zipField.getValueAsString());

                    record.setAttribute(Constants.CONTACT_ENTERED_BY, userId);
                    record.setAttribute(Constants.CONTACT_EDITED_BY, userId);
                    Date today = new Date();
                    record.setAttribute(Constants.CONTACT_ENTERED_DATE, today);
                    record.setAttribute(Constants.CONTACT_EDITED_DATE, today);

                    record.setAttribute(Constants.CONTACT_BIRTHDATE, birthdateField.getValueAsDate());
                    record.setAttribute(Constants.CONTACT_COMPANY_ID, 0);

                    if (contactId == 0)
                    {
                        contactDS.addData(record);
                    }
                    else
                    {
                        contactDS.updateData(record);
                    }
                }
                else
                {
                    SC.say("Update Contact", contactValidationMessage);
                }
            }

        });

        addnewButton.setAlign(Alignment.CENTER);
        addnewButton.setValign(VerticalAlignment.CENTER);
        addnewButton.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                contactForm.editNewRecord();
            }
        });

        IButton deleteButton = new IButton("Delete");
        deleteButton.setAlign(Alignment.CENTER);
        deleteButton.setValign(VerticalAlignment.CENTER);
        deleteButton.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                long contactId = Long.parseLong(IdField.getValueAsString());
                long userId = Long.parseLong(userIdField.getValueAsString());

                Record record = new ListGridRecord();
                record.setAttribute(Constants.CONTACT_ID, contactId);
                record.setAttribute(Constants.CONTACT_USER_ID, userId);

                if (contactId != 0)
                {
                    contactDS.removeData(record);
                    contactForm.clearValues();
                }

            }
        });

        buttonLayout.addMember(addnewButton);
        buttonLayout.addMember(getSpacerLayout());
        buttonLayout.addMember(saveButton);
        buttonLayout.addMember(getSpacerLayout());
        buttonLayout.addMember(deleteButton);

        return buttonLayout;
    }

    private DynamicForm getContactForm()
    {
        // contactForm.setBorder("1px solid blue");
        contactForm.setPadding(GRID_PADDING);
        contactForm.setDataSource(contactDS);
        contactForm.setAutoFetchData(true);
        contactForm.setInitialCriteria(new Criteria(Constants.CONTACT_ID, "5"));
        contactForm.setTitleWidth(200);
        contactForm.setWidth(400);

        userIdField = new TextItem(Constants.CONTACT_USER_ID);
        userIdField.setIconVAlign(VerticalAlignment.CENTER);
        userIdField.setTabIndex(0);
        userIdField.setRequired(true);
        userIdField.setSelectOnFocus(true);
        userIdField.setDefaultValue(contactId);
        userIdField.setVisible(false);

        IdField = new TextItem(Constants.CONTACT_ID);
        IdField.setIconVAlign(VerticalAlignment.CENTER);
        IdField.setTabIndex(1);
        IdField.setRequired(true);
        IdField.setSelectOnFocus(true);
        IdField.setDefaultValue(0);
        IdField.setVisible(false);

        prefixField.setTitle(Constants.TITLE_CONTACT_PREFIX);
        prefixField.setIconVAlign(VerticalAlignment.CENTER);
        prefixField.setTabIndex(2);
        prefixField.setSelectOnFocus(true);

        firstnameField.setTitle(Constants.TITLE_CONTACT_FIRST_NAME);
        firstnameField.setIconVAlign(VerticalAlignment.CENTER);
        firstnameField.setRequired(true);
        firstnameField.setTabIndex(3);

        middlenameField.setTitle(Constants.TITLE_CONTACT_MIDDLE_NAME);
        middlenameField.setIconVAlign(VerticalAlignment.CENTER);
        middlenameField.setTabIndex(4);

        lastnameField.setTitle(Constants.TITLE_CONTACT_LAST_NAME);
        lastnameField.setIconVAlign(VerticalAlignment.CENTER);
        lastnameField.setRequired(true);
        lastnameField.setTabIndex(5);

        suffixField.setTitle(Constants.TITLE_CONTACT_SUFFIX);
        suffixField.setIconVAlign(VerticalAlignment.CENTER);
        suffixField.setTabIndex(6);

        address1Field.setTitle(Constants.TITLE_CONTACT_ADDRESS1);
        address1Field.setIconVAlign(VerticalAlignment.CENTER);
        address1Field.setRequired(true);
        address1Field.setTabIndex(7);

        address2Field.setTitle(Constants.TITLE_CONTACT_ADDRESS2);
        address2Field.setIconVAlign(VerticalAlignment.CENTER);
        address2Field.setTabIndex(8);

        cityField.setTitle(Constants.TITLE_CONTACT_CITY);
        cityField.setIconVAlign(VerticalAlignment.CENTER);
        cityField.setRequired(true);
        cityField.setTabIndex(9);

        stateField.setTitle(Constants.TITLE_CONTACT_STATE);
        stateField.setIconVAlign(VerticalAlignment.CENTER);
        stateField.setRequired(true);
        stateField.setTabIndex(10);
        stateField.setLength(2);

        zipField.setTitle(Constants.TITLE_CONTACT_ZIP);
        zipField.setIconVAlign(VerticalAlignment.CENTER);
        zipField.setRequired(true);
        zipField.setTabIndex(11);
        zipField.setLength(10);

        birthdateField.setTitle(Constants.TITLE_CONTACT_BIRTHDATE);
        birthdateField.setIconVAlign(VerticalAlignment.CENTER);
        birthdateField.setRequired(true);
        birthdateField.setTabIndex(12);

        enteredByField.setTitle(Constants.TITLE_CONTACT_ENTERED_BY);
        enteredByField.setIconVAlign(VerticalAlignment.CENTER);
        enteredByField.setRequired(true);
        enteredByField.setTabIndex(13);
        enteredByField.setDefaultValue(contactId);
        enteredByField.setVisible(false);

        enteredDateField.setTitle(Constants.TITLE_CONTACT_ENTERED_DATE);
        enteredDateField.setIconVAlign(VerticalAlignment.CENTER);
        enteredDateField.setRequired(true);
        enteredDateField.setTabIndex(14);
        enteredDateField.setDefaultValue(new Date());
        enteredDateField.setVisible(false);

        editedByField.setTitle(Constants.TITLE_CONTACT_EDITED_BY);
        editedByField.setIconVAlign(VerticalAlignment.CENTER);
        editedByField.setRequired(true);
        editedByField.setTabIndex(15);
        editedByField.setDefaultValue(contactId);
        editedByField.setVisible(false);

        editedDateField.setTitle(Constants.TITLE_CONTACT_EDITED_DATE);
        editedDateField.setIconVAlign(VerticalAlignment.CENTER);
        editedDateField.setRequired(true);
        editedDateField.setTabIndex(16);
        editedDateField.setDefaultValue(new Date());
        editedDateField.setVisible(false);

        contactForm.setFields(IdField, userIdField, prefixField, firstnameField, middlenameField, lastnameField,
            suffixField, address1Field, address2Field, cityField, stateField, zipField, birthdateField, enteredByField,
            enteredDateField, editedByField, editedDateField);

        return contactForm;
    }

    private HLayout getSpacerLayout()
    {
        HLayout spacerLayout = new HLayout();
        // spacerLayout.setBorder("5px solid black");
        Label spacerLabel = new Label();
        spacerLabel.setContents("&nbsp;");
        spacerLayout.addMember(spacerLabel);
        return spacerLayout;
    }

    private String getContactValidation(DynamicForm contactForm)
    {
        String contactValidationMessage = null;
        StringBuffer sb = new StringBuffer();
        if (firstnameField.getValue() == null && !"".equals(firstnameField.getValue()))
        {
            sb.append("Contact First Name cannot be left blank!<br/>");
        }
        if (lastnameField.getValue() == null && !"".equals(lastnameField.getValue()))
        {
            sb.append("Contact Last Name cannot be left blank!<br/>");
        }
        if (address1Field.getValue() == null && !"".equals(address1Field.getValue()))
        {
            sb.append("Contact Address cannot be left blank!<br/>");
        }
        if (cityField.getValue() == null && !"".equals(cityField.getValue()))
        {
            sb.append("Contact City cannot be left blank!<br/>");
        }
        if (stateField.getValue() == null && !"".equals(stateField.getValue()))
        {
            sb.append("Contact State cannot be left blank!<br/>");
        }
        if (zipField.getValue() == null && !"".equals(zipField.getValue()))
        {
            sb.append("Contact Zip cannot be left blank!<br/>");
        }
        if (birthdateField.getValue() == null && !"".equals(birthdateField.getValue()))
        {
            sb.append("Contact Birth Date cannot be left blank!<br/>");
        }
        contactValidationMessage = sb.toString();
        return contactValidationMessage;
    }
}
