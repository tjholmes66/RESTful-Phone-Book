package com.opensource.restful.client.widget;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class DateItemWidget extends DateItem
{
    private static String INVALID_DATE_STRING_MESSAGE = "Invalid date format";
    private static String DATE_HINT = "MM/DD/YYYY";
    private static TextItem dateTextItem = new TextItem();

    public DateItemWidget(String name, String title)
    {
        super();
        setName(name);
        setTitle(title);

        setEnforceDate(true);
        setUseTextField(true);
        setHoverWidth(150);
        setTextAlign(Alignment.LEFT);
        setInvalidDateStringMessage(INVALID_DATE_STRING_MESSAGE);

        dateTextItem.setDateFormatter(DateDisplayFormat.TOUSSHORTDATE);
        dateTextItem.setHint(DATE_HINT);
        dateTextItem.setShowHint(true);
        dateTextItem.setShowHintInField(true);
        dateTextItem.setBrowserSpellCheck(false);

        setTextFieldProperties(dateTextItem);
    }

    public DateItemWidget(String name, String title, TextItem dateTextItem)
    {
        super();
        setName(name);
        setTitle(title);

        setEnforceDate(true);
        setUseTextField(true);
        setHoverWidth(150);
        setTextAlign(Alignment.LEFT);
        setInvalidDateStringMessage(INVALID_DATE_STRING_MESSAGE);
        if (dateTextItem != null)
        {
            dateTextItem.setDateFormatter(DateDisplayFormat.TOUSSHORTDATE);
            dateTextItem.setHint(DATE_HINT);
            dateTextItem.setShowHint(true);
            dateTextItem.setBrowserSpellCheck(false);

            setTextFieldProperties(dateTextItem);
        }
        else
        {
            setUseMask(true);
        }
    }
}
