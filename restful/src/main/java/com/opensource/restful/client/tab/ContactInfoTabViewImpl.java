package com.opensource.restful.client.tab;

import com.google.gwt.event.shared.EventBus;
import com.opensource.restful.client.datasource.ContactDataSource;
import com.opensource.restful.client.grid.ContactListGrid;
import com.opensource.restful.shared.dto.UserDTO;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tab.Tab;

public class ContactInfoTabViewImpl implements ContactInfoTabView
{
    private Tab contactInfoTab = new Tab();

    private ContactDataSource contactDS = ContactDataSource.getInstance();

    private final static int GRID_PADDING = 10;

    private UserDTO _userDto;
    private EventBus _eventBus;

    ContactListGrid contactListGrid;

    // private String userId = "-1";
    private String contactId = "5";

    public ContactInfoTabViewImpl(String ID, EventBus eventBus, UserDTO userDto)
    {
        super();
        this._userDto = userDto;
        this._eventBus = eventBus;

        contactInfoTab.setTitle(ID);

        VStack mainLayout = new VStack();
        mainLayout.setWidth100();
        mainLayout.setHeight100();
        // mainLayout.setBorder("5px solid purple");
        mainLayout.setAlign(Alignment.CENTER);

        contactListGrid = new ContactListGrid(Long.toString(_userDto.getUserId()));
        mainLayout.addMember(getLeftLayout());
        mainLayout.addMember(getRightLayout());

        contactInfoTab.setPane(mainLayout);
    }

    private VLayout getLeftLayout()
    {
        VLayout leftLayout = new VLayout();
        leftLayout.setWidth("50%");
        leftLayout.setHeight("100%");
        leftLayout.setBorder("5px solid orange");
        // leftLayout.setLayoutAlign(VerticalAlignment.TOP);

        leftLayout.addMember(contactListGrid);

        return leftLayout;
    }

    private VLayout getRightLayout()
    {
        VLayout rightLayout = new VLayout();

        return rightLayout;
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

    @Override
    public Tab getContactInfoTab()
    {
        return contactInfoTab;
    }

    @Override
    public void editContact(String contactId, Record contactRecord)
    {
        this.contactId = contactId;
        // contactForm.reset();
        // Criteria c = new Criteria(Constants.CONTACT_ID, contactId);

        // contactForm.fetchData(c);

        // contactForm.editRecord(contactRecord);
    }
}
