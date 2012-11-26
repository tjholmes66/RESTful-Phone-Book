package com.opensource.restful.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface MemberChangedHandler extends EventHandler
{
    void onMemberChange(MemberChangedEvent event);
}
