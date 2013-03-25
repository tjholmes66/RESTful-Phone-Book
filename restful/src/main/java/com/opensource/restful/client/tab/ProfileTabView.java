package com.opensource.restful.client.tab;

import com.opensource.restful.client.handler.ProfileSaveButtonClickHandler;
import com.smartgwt.client.widgets.tab.Tab;

public interface ProfileTabView
{

    Tab getProfileTab();

    void saveProfileForm();

    void addClickHandlerSaveButton(ProfileSaveButtonClickHandler profileSaveButtonClickHandler);

}
