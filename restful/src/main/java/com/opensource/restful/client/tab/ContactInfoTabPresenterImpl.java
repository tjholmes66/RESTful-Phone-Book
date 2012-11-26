package com.opensource.restful.client.tab;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.opensource.restful.client.event.ContactChangedEvent;
import com.opensource.restful.client.event.ContactChangedHandler;

public class ContactInfoTabPresenterImpl implements ContactInfoTabPresenter
{
    private final EventBus _eventBus;
    private final ContactInfoTabView _view;

    public ContactInfoTabPresenterImpl(EventBus eventBus, ContactInfoTabView view)
    {
        super();
        this._eventBus = eventBus;
        this._view = view;

        eventBus.addHandler(ContactChangedEvent.TYPE, new ContactChangedHandler()
        {

            @Override
            public void onContactChanged(ContactChangedEvent event)
            {
                _view.editContact(event.getContactId(), event.getRecord());
            }

        });

        setHandlers();
    }

    public void setHandlers()
    {
        // getView().getMembersGrid().addRecordClickHandler(new MemberRecordClickHandler(getView(), this));
        // getView().getNewButton().addClickHandler(new MemberButtonNewClickHandler(getView(), this));
        // getView().getSaveButton().addClickHandler(new MemberButtonSaveClickHandler(getView(), this));
        // getView().getRefreshButton().addClickHandler(new MemberButtonRefreshClickHandler(getView(), this));
        // getView().getResetButton().addClickHandler(new MemberButtonResetClickHandler(getView(), this));
    }

    public void go(HasWidgets container)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public ContactInfoTabView getView()
    {
        return _view;
    }

}
