package com.opensource.restful.client.tab;

import com.google.gwt.event.shared.EventBus;
import com.opensource.restful.client.datasource.UserDataSource;
import com.opensource.restful.client.widget.ContactWidget;
import com.opensource.restful.shared.dto.UserDTO;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class MemberTabViewImpl implements MemberTabView
{
    private Tab memberTab = new Tab();

    private UserDataSource userDS = UserDataSource.getInstance();

    private UserDTO userDto;
    private EventBus eventBus;

    public MemberTabViewImpl(String ID, EventBus eventBus, UserDTO userDto)
    {
        super();
        this.userDto = userDto;
        this.eventBus = eventBus;

        memberTab.setTitle(ID);

        VStack mainLayout = new VStack();
        mainLayout.setWidth100();
        mainLayout.setHeight100();
        mainLayout.setBackgroundColor("#3300ff");
        mainLayout.setBorder("1px solid black");
        mainLayout.setAlign(Alignment.CENTER);

        final TabSet topTabSet = new TabSet();
        topTabSet.setTabBarPosition(Side.TOP);
        topTabSet.setWidth100();
        topTabSet.setHeight100();

        // Tab tTab1 = new Tab("Your Info");
        // tTab1.setPane(new ProfileWidget(userDto));
        // topTabSet.addTab(tTab1);

        ProfileTabPresenter memberTabPresenter =
            new ProfileTabPresenterImpl(eventBus, new ProfileTabViewImpl("Profiles", eventBus, userDto));
        topTabSet.addTab(memberTabPresenter.getView().getProfileTab());

        Tab tTab2 = new Tab("Contacts");
        tTab2.setPane(new ContactWidget(userDto));
        topTabSet.addTab(tTab2);

        mainLayout.addMember(topTabSet);

        memberTab.setPane(mainLayout);
    }

    @Override
    public Tab getMemberTab()
    {
        return memberTab;
    }

}
