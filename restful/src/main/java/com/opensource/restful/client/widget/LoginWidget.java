package com.opensource.restful.client.widget;

import com.opensource.restful.client.datasource.LoginDataSource;
import com.opensource.restful.shared.Constants;
import com.opensource.restful.shared.dto.UserDTO;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class LoginWidget extends VLayout
{
    private final LoginDataSource loginDS = LoginDataSource.getInstance();

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

        // loginForm.setBorder("3px solid black");
        loginForm.setPadding(Constants.UI_PADDING_2);
        loginForm.setAutoHeight();
        // loginForm.setDataSource(loginDS);
        loginForm.setNumCols(2);
        loginForm.setAutoFocus(true);
        loginForm.setTitleWidth(200);
        loginForm.setWidth(400);
        loginForm.setAlign(Alignment.CENTER);
        // loginForm.setSaveOnEnter(true);
        loginForm.setLayoutAlign(VerticalAlignment.CENTER);

        loginUserField.setIconVAlign(VerticalAlignment.CENTER);
        loginUserField.setTabIndex(0);
        loginUserField.setRequired(true);
        loginUserField.setSelectOnFocus(true);
        loginUserField.setWidth(150);

        loginForgotUsernameField.setTitle(" ");
        loginForgotUsernameField.setLinkTitle(Constants.TITLE_LOGIN_FORGOT_USERNAME);
        loginForgotUsernameField.addClickHandler(new ClickHandler()
        {

            @Override
            public void onClick(ClickEvent event)
            {
                // SC.say("Hello World: forgot username");
                setupForgotUsernameFormWindow();

            }
        });

        loginPasswordField.setTabIndex(1);
        loginPasswordField.setRequired(true);
        loginPasswordField.setWidth(150);

        loginForgotPasswordField.setTitle(" ");
        loginForgotPasswordField.setLinkTitle(Constants.TITLE_LOGIN_FORGOT_PASSWORD);
        loginForgotPasswordField.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                String username = loginUserField.getValueAsString();

                if (username == null)
                {
                    SC.say("Must enter username first!");
                }
                else
                {
                    // SC.say("Hello World: forgot password");
                    Criteria c = new Criteria();
                    c.addCriteria(Constants.USER_USERNAME, username);

                    // fetch User based on username
                    loginDS.fetchData(c, new DSCallback()
                    {

                        @Override
                        public void execute(DSResponse response, Object rawData, DSRequest request)
                        {
                            // SC.say("test", response.getAttribute("data"));
                            _userDto = (UserDTO) response.getAttributeAsObject("user");

                            if (_userDto == null)
                            {
                                SC.say("Unable to find user with this username");
                            }
                            else
                            {
                                setupForgotPasswordFormWindow();
                            }
                        }

                    });
                }

            }
        });

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

    private void setupForgotUsernameFormWindow()
    {
        winModalForgotUsername = new WindowForgotUsername();
    }

    private void setupForgotPasswordFormWindow()
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
