package com.opensource.restful.client;

import com.opensource.restful.client.datasource.ContactDataSource;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class MainPage extends Canvas
{
    ContactDataSource contactDS = ContactDataSource.getInstance();

    public MainPage()
    {
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

        ListGrid contactList = new ListGrid();
        contactList.setAutoFetchData(true);
        contactList.setDataSource(contactDS);

        leftLayout.addMember(contactList);
        return leftLayout;
    }

    private VLayout getRightSideLayout()
    {
        VLayout rightLayout = new VLayout();
        rightLayout.setWidth100();
        rightLayout.setHeight100();
        rightLayout.setBorder("5px solid orange");

        return rightLayout;
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
