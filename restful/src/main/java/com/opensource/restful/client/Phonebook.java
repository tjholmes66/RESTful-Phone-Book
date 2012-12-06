package com.opensource.restful.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Phonebook implements EntryPoint
{

    /**
     * This is the entry point method.
     */
    public void onModuleLoad()
    {
        LoginDialog.getInstance().displayIfRequired(null);
    }

}
