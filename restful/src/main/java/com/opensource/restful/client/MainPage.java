package com.opensource.restful.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.opensource.restful.client.datasource.ContactDataSource;
import com.opensource.restful.client.event.ContactChangedEvent;
import com.opensource.restful.client.tab.ContactInfoTabPresenterImpl;
import com.opensource.restful.client.tab.ContactInfoTabViewImpl;
import com.opensource.restful.shared.ClientResources;
import com.opensource.restful.shared.Constants;
import com.opensource.restful.shared.dto.UserDTO;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.TabSet;

public class MainPage extends Canvas
{
    private ContactDataSource contactDS = ContactDataSource.getInstance();

    private final TabSet topTabSet = new TabSet();

    private final EventBus eventBus;
    private UserDTO userDto;

    private ClientResources resources = (ClientResources) GWT.create(ClientResources.class);

    private String userId = "-1";

    private ListGrid contactList = new ListGrid();

    public MainPage(EventBus eventBus)
    {
        super();
        this.eventBus = eventBus;

        setHeight100();
        setWidth100();
        setBorder("5px solid black");

        VLayout mainBody = new VLayout();
        mainBody.setWidth100();
        mainBody.setHeight100();
        mainBody.setBorder("5px solid green");

        mainBody.addMember(getHeader());
        mainBody.addMember(getBody());
        mainBody.addMember(getFooter());

        addChild(mainBody);
    }

    private VLayout getHeader()
    {
        VLayout headerLayout = new VLayout();
        headerLayout.setWidth100();
        headerLayout.setHeight("10%");
        headerLayout.setBorder("5px solid red");

        Label nameLabel = new Label("name");
        nameLabel.setAutoHeight();
        nameLabel.setAutoWidth();
        nameLabel.setBorder("1px solid black");
        nameLabel.setContents("test");

        headerLayout.addMember(nameLabel);
        return headerLayout;
    }

    private VLayout getBody()
    {
        VLayout mainBodyLayout = new VLayout();
        mainBodyLayout.setWidth100();
        mainBodyLayout.setHeight("80%");
        mainBodyLayout.setBorder("5px solid blue");

        HLayout subBodyLayout = new HLayout();
        subBodyLayout.addMember(getLeftSideLayout());
        subBodyLayout.addMember(getRightSideLayout());

        mainBodyLayout.addMember(subBodyLayout);
        return mainBodyLayout;
    }

    private VLayout getLeftSideLayout()
    {
        VLayout leftLayout = new VLayout();
        leftLayout.setWidth("30%");
        leftLayout.setHeight100();
        leftLayout.setBorder("5px solid pink");

        contactList.setAutoFetchData(true);
        contactList.setDataSource(contactDS);

        contactList.addRecordClickHandler(new RecordClickHandler()
        {

            @Override
            public void onRecordClick(RecordClickEvent event)
            {
                Record record = event.getRecord();
                String contactId = record.getAttributeAsString(Constants.CONTACT_ID);

                eventBus.fireEvent(new ContactChangedEvent(contactId, record));
            }

        });

        leftLayout.addMember(contactList);
        return leftLayout;
    }

    private VLayout getRightSideLayout()
    {
        VLayout rightLayout = new VLayout();
        rightLayout.setWidth100();
        rightLayout.setHeight100();
        rightLayout.setBorder("5px solid orange");

        rightLayout.addMember(getTabs());

        return rightLayout;
    }

    private TabSet getTabs()
    {
        topTabSet.setTabBarPosition(Side.TOP);
        topTabSet.setWidth100();
        topTabSet.setHeight100();

        ContactInfoTabPresenterImpl contactInfoTabPresenter =
            new ContactInfoTabPresenterImpl(eventBus, new ContactInfoTabViewImpl(resources.tabTitle_ContactInfoTab()));
        topTabSet.addTab(contactInfoTabPresenter.getView().getContactInfoTab());

        return topTabSet;
    }

    private VLayout getFooter()
    {
        VLayout footerLayout = new VLayout();
        footerLayout.setWidth100();
        footerLayout.setHeight("10%");
        footerLayout.setBorder("5px solid red");
        return footerLayout;
    }

}
