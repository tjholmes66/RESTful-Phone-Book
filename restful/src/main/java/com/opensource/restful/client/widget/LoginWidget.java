package com.opensource.restful.client.widget;

import com.opensource.restful.shared.Constants;
import com.opensource.restful.shared.dto.UserDTO;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class LoginWidget extends VLayout
{
    private DynamicForm loginForm;

    private TextItem loginUserField = new TextItem(Constants.LOGIN_USERNAME, Constants.TITLE_LOGIN_USERNAME);
    private PasswordItem loginPasswordField =
        new PasswordItem(Constants.LOGIN_PASSWORD, Constants.TITLE_LOGIN_PASSWORD);

    private LinkItem loginForgotUsernameField = new LinkItem(Constants.LOGIN_FORGOT_USERNAME);
    private LinkItem loginForgotPasswordField = new LinkItem(Constants.LOGIN_FORGOT_PASSWORD);

    WindowForgotUsername winModalForgotUsername;
    WindowForgotPassword winModalForgotPassword;

    private UserDTO _userDto;

    private String username;

    public LoginWidget()
    {
        super();
        setAutoHeight();
        setWidth(400);
        setLayoutAlign(Alignment.CENTER);
        // setBorder("3px solid blue");

        HLayout formLayout = new HLayout();
        // formLayout.setBorder("3px solid yellow");
        formLayout.setHeight100();
        formLayout.setWidth100();
        formLayout.addMember(getLoginForm());

        addMember(formLayout);
    }

    private DynamicForm getLoginForm()
    {
        loginForm = new DynamicForm();

        loginUserField.setIconVAlign(VerticalAlignment.CENTER);
        loginUserField.setTabIndex(0);
        loginUserField.setRequired(true);
        loginUserField.setSelectOnFocus(true);
        loginUserField.setWidth(150);

        loginForm.setFields(loginUserField, loginForgotUsernameField, loginPasswordField, loginForgotPasswordField);
        loginForm.setAutoFocus(true);

        return loginForm;
    }

    public String getLoginUsername()
    {
        return loginUserField.getValueAsString();
    }

    public String getLoginPassword()
    {
        return loginPasswordField.getValueAsString();
    }

    public void setUsernameFocus()
    {
        loginUserField.focusInItem();
    }

    private void setupForgtUsernameFormWindow()
    {
        winModalForgotUsername = new WindowForgotUsername();
    }

    private void setupForgtPasswordFormWindow()
    {
        winModalForgotPassword = new WindowForgotPassword(_userDto);
    }

    public UserDTO get_userDto()
    {
        return _userDto;
    }

    public void set_userDto(UserDTO _userDto)
    {
        this._userDto = _userDto;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
