package com.opensource.restful.client.widget;

import com.opensource.restful.client.datasource.LoginDataSource;
import com.opensource.restful.client.datasource.UserDataSource;
import com.opensource.restful.shared.Constants;
import com.opensource.restful.shared.dto.UserDTO;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
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
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

public class WindowForgotPassword extends Window
{
    private final LoginDataSource loginDS = LoginDataSource.getInstance();
    private final UserDataSource userDS = UserDataSource.getInstance();

    private DynamicForm searchSecurityForm = new DynamicForm();
    private TextItem securityAnswer1Field = new TextItem(Constants.USER_SECURITY_ANSWER_1);
    private TextItem securityAnswer2Field = new TextItem(Constants.USER_SECURITY_ANSWER_2);
    private final Button searchSecurityButton = new Button(Constants.BUTTON_SUBMIT);

    private DynamicForm passwordForm = new DynamicForm();
    private PasswordItem newPasswordField = new PasswordItem(Constants.USER_NEW_PASSWORD,
        Constants.TITLE_USER_NEW_PASSWORD);
    private PasswordItem confirmPasswordField = new PasswordItem(Constants.USER_OTHER_PASSWORD,
        Constants.TITLE_USER_OTHER_PASSWORD);
    private final Button passwordButton = new Button(Constants.BUTTON_SUBMIT);

    private UserDTO _userDto;

    public WindowForgotPassword(UserDTO userDto)
    {
        this._userDto = userDto;

        searchSecurityForm = getSecurityForm();
        passwordForm = getPasswordForm();

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

        VLayout formLayout = new VLayout();
        formLayout.setHeight100();
        formLayout.setWidth100();
        formLayout.setBorder("3px solid blue");
        formLayout.setLayoutAlign(VerticalAlignment.CENTER);
        formLayout.setAlign(VerticalAlignment.CENTER);

        formLayout.addMember(searchSecurityForm);
        formLayout.addMember(searchSecurityButton);

        searchSecurityButton.addClickHandler(new ClickHandler()
        {

            @Override
            public void onClick(ClickEvent event)
            {
                String msg = null;
                if (securityAnswer1Field.getValueAsString() == null || securityAnswer2Field.getValueAsString() == null
                    || !securityAnswer1Field.getValueAsString().equals(_userDto.getUserSecurityAnswer1())
                    || !securityAnswer2Field.getValueAsString().equals(_userDto.getUserSecurityAnswer2()))
                {
                    msg = new String("Security Questions do not match!!!");
                }
                if (msg != null)
                {
                    SC.say(msg);
                }
                else
                {
                    searchSecurityForm.hide();
                    searchSecurityButton.hide();

                    passwordForm.show();
                    passwordButton.show();

                }
            }

        });

        passwordForm.hide();
        passwordButton.hide();
        formLayout.addMember(passwordForm);
        formLayout.addMember(passwordButton);

        passwordButton.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                String passwordValidationMessage = getPasswordValidation(passwordForm);
                if (passwordValidationMessage == null || "".equals(passwordValidationMessage))
                {
                    Record record = new ListGridRecord();
                    record.setAttribute(Constants.USER_ID, _userDto.getUserId());
                    record.setAttribute(Constants.USER_ACTIVE, true);
                    record.setAttribute(Constants.USER_NEW_PASSWORD, newPasswordField.getValueAsString());
                    // userDS.updateData(record);

                    userDS.updateData(record, new DSCallback()
                    {

                        @Override
                        public void execute(DSResponse response, Object rawData, DSRequest request)
                        {
                            // JavaScriptObject jso = (JavaScriptObject) rawData;
                            // String jsoText1 = JSON.encode(jso);
                            // SC.say("Update Password", "jso=" + jsoText1);

                            SC.say("Update Password", "Password Updated!");
                            destroy();

                        }

                    });
                    // SC.say("Update Password", "valid new password");
                }
                else
                {
                    SC.say("Update Password", passwordValidationMessage);
                }
            }

        });

        addItem(spacer);
        addItem(formLayout);
        addItem(spacer);

        show();
    }

    private String getPasswordValidation(DynamicForm passwordForm)
    {
        String passwordValidationMessage = null;
        StringBuffer sb = new StringBuffer();
        if (newPasswordField.getValue() == null && !"".equals(newPasswordField.getValue()))
        {
            sb.append("New Password cannot be left blank!<br/>");
        }
        if (confirmPasswordField.getValue() == null && !"".equals(confirmPasswordField.getValue()))
        {
            sb.append("Retype Password cannot be left blank!<br/>");
        }
        if (newPasswordField.getValue() != null && confirmPasswordField.getValue() != null
            && !"".equals(confirmPasswordField.getValue()))
        {
            if (!newPasswordField.getValue().equals(confirmPasswordField.getValue()))
            {
                sb.append("ReTyped Password does not match new password!<br/>");
            }
        }
        passwordValidationMessage = sb.toString();
        return passwordValidationMessage;
    }

    private DynamicForm getSecurityForm()
    {
        searchSecurityForm = new DynamicForm();
        searchSecurityForm.setDataSource(loginDS);
        searchSecurityForm.setVisible(true);

        securityAnswer1Field.setTitle(_userDto.getUserSecurityQuestion1());
        securityAnswer1Field.setRequired(true);

        securityAnswer2Field.setTitle(_userDto.getUserSecurityQuestion2());
        securityAnswer2Field.setRequired(true);

        searchSecurityForm.setFields(securityAnswer1Field, securityAnswer2Field);

        return searchSecurityForm;
    }

    private DynamicForm getPasswordForm()
    {
        passwordForm = new DynamicForm();
        passwordForm.setDataSource(userDS);
        passwordForm.setVisible(true);
        passwordForm.setTitleWidth(200);
        passwordForm.setWidth(400);

        newPasswordField.setRequired(true);
        confirmPasswordField.setRequired(true);

        passwordForm.setFields(newPasswordField, confirmPasswordField);

        passwordForm.hide();

        return passwordForm;
    }
}
