package com.opensource.restful.client.handler;

import com.opensource.restful.client.tab.ProfileTabPresenter;
import com.opensource.restful.client.tab.ProfileTabView;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class ProfileSaveButtonClickHandler implements ClickHandler
{
    ProfileTabView _view;
    ProfileTabPresenter _presenter;

    public ProfileSaveButtonClickHandler(ProfileTabView view, ProfileTabPresenter presenter)
    {
        this._view = view;
        this._presenter = presenter;
    }

    @Override
    public void onClick(ClickEvent event)
    {
        _view.saveProfileForm();
    }

}
