package com.opensource.restful.client;

import com.google.gwt.event.shared.EventBus;
import com.opensource.restful.client.tab.MemberTabPresenter;
import com.opensource.restful.client.tab.MemberTabPresenterImpl;
import com.opensource.restful.client.tab.MemberTabViewImpl;
import com.opensource.restful.client.widget.HeaderWidget;
import com.opensource.restful.shared.dto.UserDTO;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class PhoneBookAdmin extends Canvas
{
    private final EventBus eventBus;

    private UserDTO userDto;

    public PhoneBookAdmin(EventBus eventBus, UserDTO userDto)
    {
        super();
        this.eventBus = null;
        this.userDto = userDto;

        Canvas canvas = new Canvas();
        canvas.setBorder("1px solid orange");
        canvas.setHeight100();
        canvas.setWidth100();

        VLayout main = new VLayout();
        main.setBorder("1px solid black");
        main.setHeight100();
        main.setWidth100();
        main.setMembersMargin(5);
        main.setLayoutMargin(10);

        HeaderWidget header = new HeaderWidget(userDto);

        HLayout body = new HLayout();
        body.setBorder("1px solid blue");
        body.setWidth100();
        body.setHeight100();
        body.setPadding(10);
        body.setBackgroundColor("#e0e0e0");
        body.setMembersMargin(5);
        body.setLayoutMargin(10);

        final TabSet topTabSet = new TabSet();
        topTabSet.setTabBarPosition(Side.TOP);
        topTabSet.setWidth100();
        topTabSet.setHeight100();

        MemberTabPresenter memberTabPresenter =
            new MemberTabPresenterImpl(eventBus, new MemberTabViewImpl("Your Info", eventBus, userDto));
        topTabSet.addTab(memberTabPresenter.getView().getMemberTab());

        Tab tTab3 = new Tab("MB Counselor");
        // tTab3.setPane(new AdministrationTab())
        topTabSet.addTab(tTab3);

        body.addMember(topTabSet);

        main.addMember(header);
        main.addMember(body);

        canvas.addChild(main);
        canvas.draw();
    }

}
