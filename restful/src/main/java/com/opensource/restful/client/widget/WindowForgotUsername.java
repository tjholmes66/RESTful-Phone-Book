package com.opensource.restful.client.widget;

import com.opensource.restful.client.datasource.LoginDataSource;
import com.opensource.restful.shared.Constants;
import com.opensource.restful.shared.dto.UserDTO;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class WindowForgotUsername extends Window
{
    private final LoginDataSource loginDS = LoginDataSource.getInstance();

    private final Button searchEmailButton = new Button(Constants.BUTTON_SUBMIT);

    private DynamicForm searchEmailForm = new DynamicForm();
    private TextItem searchEmailField = new TextItem(Constants.USER_EMAIL, Constants.TITLE_USER_EMAIL);

    private UserDTO _userDto;

    public WindowForgotUsername()
    {
        searchEmailForm = getSearchEmailForm();

        setLayoutAlign(VerticalAlignment.CENTER);
        setMargin(10);
        setWidth(550);
        setHeight(250);
        setTitle("Login: Forgot Username!");
        setShowMinimizeButton(false);
        setIsModal(true);
        setShowModalMask(true);
        centerInPage();
        // winModalForgot.setBorder("3px solid red");

        addCloseClickHandler(new CloseClickHandler()
        {
            @Override
            public void onCloseClick(CloseClickEvent event)
            {
                destroy();
            }
        });

        VLayout spacer = new VLayout();
        Label spacerLabel = new Label();
        spacerLabel.setContents("&nbsp;");
        spacerLabel.setHeight(50);
        spacer.addMember(spacerLabel);
        // spacer.setBorder("3px solid green");

        HLayout formLayout = new HLayout();
        formLayout.setHeight100();
        formLayout.setWidth100();
        // formLayout.setBorder("3px solid blue");
        formLayout.setLayoutAlign(VerticalAlignment.CENTER);
        formLayout.setAlign(VerticalAlignment.CENTER);

        formLayout.addMember(searchEmailForm);
        formLayout.addMember(searchEmailButton);

        addItem(spacer);
        addItem(formLayout);
        addItem(spacer);

        searchEmailButton.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                Criteria c = new Criteria();
                c.addCriteria(Constants.USER_EMAIL, searchEmailField.getValueAsString());

                // SC.say("Look For Email=" + searchEmailField.getValueAsString());
                loginDS.fetchData(c, new DSCallback()
                {

                    @Override
                    public void execute(DSResponse response, Object rawData, DSRequest request)
                    {
                        // SC.say("Look For Email=" + searchEmailField.getValueAsString());
                        _userDto = (UserDTO) response.getAttributeAsObject("user");

                        if (_userDto == null)
                        {
                            SC.say("Unable to find user with this email!");
                            searchEmailField.clearValue();
                            searchEmailField.focusInItem();
                        }
                        else
                        {
                            SC.say("An email is being sent with the username!");

                            destroy();
                        }
                    }

                });
            }

        });

        show();
    }

    private DynamicForm getSearchEmailForm()
    {
        searchEmailForm = new DynamicForm();
        searchEmailForm.setDataSource(loginDS);

        searchEmailForm.setFields(searchEmailField);

        return searchEmailForm;
    }
}
