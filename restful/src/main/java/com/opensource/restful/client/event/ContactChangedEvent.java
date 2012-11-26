package com.opensource.restful.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.opensource.restful.shared.dto.ContactDTO;
import com.smartgwt.client.data.Record;

public class ContactChangedEvent extends GwtEvent<ContactChangedHandler>
{

    private Record contactRecord;
    private String contactId;

    public ContactChangedEvent(ContactDTO contact)
    {
        super();
    }

    public ContactChangedEvent(String contactid)
    {
        super();
        this.contactId = contactId;
    }

    public ContactChangedEvent(String contactId, Record record)
    {
        super();
        this.contactRecord = record;
        this.contactId = contactId;
    }

    public static final Type<ContactChangedHandler> TYPE = new Type<ContactChangedHandler>();

    @Override
    protected void dispatch(ContactChangedHandler handler)
    {
        handler.onContactChanged(this);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<ContactChangedHandler> getAssociatedType()
    {
        return TYPE;
    }

    public Record getRecord()
    {
        return this.contactRecord;
    }

    public String getContactId()
    {
        return this.contactId;
    }

}
