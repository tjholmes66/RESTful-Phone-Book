package com.opensource.restful.client.tab;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;

public class MemberTabPresenterImpl implements MemberTabPresenter
{
    private final EventBus _eventBus;
    private final MemberTabView _view;

    public MemberTabPresenterImpl(EventBus eventBus, MemberTabView view)
    {
        super();
        this._eventBus = eventBus;
        this._view = view;

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
    public MemberTabView getView()
    {
        return _view;
    }

}
