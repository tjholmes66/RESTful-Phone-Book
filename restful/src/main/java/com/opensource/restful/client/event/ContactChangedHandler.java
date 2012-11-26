package com.opensource.restful.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface ContactChangedHandler extends EventHandler
{
    void onContactChanged(ContactChangedEvent contact);
}
