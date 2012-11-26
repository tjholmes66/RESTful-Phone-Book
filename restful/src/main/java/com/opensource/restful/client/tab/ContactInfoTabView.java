package com.opensource.restful.client.tab;

import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.tab.Tab;

public interface ContactInfoTabView
{

    Tab getContactInfoTab();

    void editContact(String contactId, Record record);

}
