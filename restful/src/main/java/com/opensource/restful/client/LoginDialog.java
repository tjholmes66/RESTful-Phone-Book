package com.opensource.restful.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Cookies;
import com.opensource.restful.client.datasource.LoginDataSource;
import com.opensource.restful.client.datasource.UserDataSource;
import com.opensource.restful.client.widget.DateItemWidget;
import com.opensource.restful.shared.ClientResources;
import com.opensource.restful.shared.Constants;
import com.opensource.restful.shared.dto.UserDTO;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class LoginDialog extends Canvas
{
    static EventBus eventBus = GWT.create(SimpleEventBus.class);

    private final LoginDataSource loginDS = LoginDataSource.getInstance();
    private final UserDataSource userDS = UserDataSource.getInstance();

    static private LoginDialog instance = null;

    private final ClientResources resources = (ClientResources) GWT.create(ClientResources.class);

    final static public long LOGIN_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // increase later

    private DynamicForm signupForm = new DynamicForm();
    private TextItem idField;
    private TextItem usernameField = new TextItem(Constants.USER_USERNAME);
    private PasswordItem passwordField = new PasswordItem(Constants.USER_PASSWORD);
    private PasswordItem otherPasswordField = new PasswordItem(Constants.USER_OTHER_PASSWORD);
    private TextItem firstnameField = new TextItem(Constants.USER_FIRST_NAME);
    private TextItem lastnameField = new TextItem(Constants.USER_LAST_NAME);
    private TextItem emailField = new TextItem(Constants.USER_EMAIL);
    private TextItem securityQuestion1Field = new TextItem(Constants.USER_SECURITY_QUESTION_1);
    private TextItem securityAnswer1Field = new TextItem(Constants.USER_SECURITY_ANSWER_1);
    private TextItem securityQuestion2Field = new TextItem(Constants.USER_SECURITY_QUESTION_1);
    private TextItem securityAnswer2Field = new TextItem(Constants.USER_SECURITY_ANSWER_1);
    private TextItem positionField = new TextItem(Constants.USER_POSITION_ID);
    private DateItemWidget birthdateField =
        new DateItemWidget(Constants.USER_BIRTHDATE, Constants.TITLE_USER_BIRTHDATE);

    private VLayout loginLayout = new VLayout();
    private DynamicForm loginForm;
    private TextItem loginUserField;
    private PasswordItem loginPasswordField;
    private IButton submitButton = new IButton("Sign In");
    private IButton enrolButton = new IButton("Sign Me Up!");

    private VLayout signupLayout = new VLayout();

    private UserDTO _userDto;

    private LoginDialogCallback callback;

    static public LoginDialog getInstance()
    {
        if (instance == null)
        {
            instance = new LoginDialog();
        }
        return instance;
    }

    private LoginDialog()
    {
        super();

        setHeight100();
        setWidth100();
        setBorder("5px solid black");

        callback = null;

        VLayout mainBody = new VLayout();
        mainBody.setWidth100();
        mainBody.setHeight100();
        mainBody.setBorder("5px solid green");

        signupLayout = getSignupLayout();

        mainBody.addMember(getHeader());
        mainBody.addMember(getWorkArea());
        mainBody.addMember(getFooter());

        addChild(mainBody);
    }

    private HLayout getWorkArea()
    {
        HLayout workArea = new HLayout();
        workArea.setPadding(Constants.UI_PADDING_2);
        workArea.setHeight("80%");
        workArea.setWidth100();
        workArea.setBorder("10px solid purple");

        loginLayout.setPadding(Constants.UI_PADDING_2);
        loginLayout.setAutoHeight();
        loginLayout.setWidth100();
        // loginLayout.setBorder("1px solid yellow");
        loginLayout.setAlign(VerticalAlignment.TOP);

        loginLayout.addMember(getLoginForm());
        loginLayout.addMember(getLoginButtonLayout());

        workArea.addMember(loginLayout);
        workArea.addMember(signupLayout);

        return workArea;
    }

    private DynamicForm getLoginForm()
    {
        // *************Login Form**************
        loginForm = new DynamicForm();
        // loginForm.setBorder("1px solid black");
        loginForm.setPadding(Constants.UI_PADDING_2);
        loginForm.setAutoHeight();
        loginForm.setDataSource(loginDS);
        loginForm.setNumCols(2);
        loginForm.setAutoFocus(true);
        loginForm.setTitleWidth(200);
        loginForm.setWidth(400);
        loginForm.setAlign(Alignment.CENTER);
        loginForm.setSaveOnEnter(true);
        loginForm.setLayoutAlign(VerticalAlignment.CENTER);

        loginUserField = new TextItem("username");
        loginUserField.setIconVAlign(VerticalAlignment.CENTER);
        loginUserField.setTabIndex(0);
        loginUserField.setRequired(true);
        loginUserField.setSelectOnFocus(true);
        loginUserField.setWidth(150);
        loginUserField.setTitle("Username");

        loginPasswordField = new PasswordItem("password");
        loginPasswordField.setTabIndex(1);
        loginPasswordField.setRequired(true);
        loginPasswordField.setWidth(150);
        loginPasswordField.setTitle("Password");

        loginForm.setFields(loginUserField, loginPasswordField);
        loginForm.setAutoFocus(true);

        return loginForm;
    }

    private HLayout getLoginButtonLayout()
    {
        HLayout loginButtonLayout = new HLayout();
        loginButtonLayout.setAutoHeight();
        loginButtonLayout.setWidth100();
        // loginButtonLayout.setBorder("1px solid red");
        loginButtonLayout.setAlign(Alignment.CENTER);

        Label spacerLabel = new Label();
        spacerLabel.setContents("&nbsp;");
        spacerLabel.setHeight(10);
        spacerLabel.setWidth(80);

        submitButton.setTitle(resources.login_submitButtonText());
        submitButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler()
        {
            public void onClick(com.smartgwt.client.widgets.events.ClickEvent event)
            {
                String username = loginUserField.getValueAsString();
                String password = loginPasswordField.getValueAsString();

                Criteria c = new Criteria();
                c.addCriteria(Constants.USER_USERNAME, username);
                c.addCriteria(Constants.USER_PASSWORD, password);

                loginDS.fetchData(c, new DSCallback()
                {

                    @Override
                    public void execute(DSResponse response, Object rawData, DSRequest request)
                    {
                        // SC.say("test", response.getAttribute("data"));
                        _userDto = (UserDTO) response.getAttributeAsObject("user");

                        if (_userDto == null)
                        {
                            SC.say("Unable to authenticate username/password!");
                            loginUserField.focusInItem();
                        }
                        else
                        {
                            login(_userDto);
                        }
                    }

                });
            }
        });

        enrolButton.setHeight(21);
        enrolButton.setStyleName("messagebutton");

        enrolButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                loginLayout.setVisible(false);
                signupLayout.setVisible(true);
            }
        });

        loginButtonLayout.addMember(spacerLabel);
        loginButtonLayout.addMember(submitButton);
        loginButtonLayout.addMember(spacerLabel);
        loginButtonLayout.addMember(enrolButton);
        loginButtonLayout.addMember(spacerLabel);

        return loginButtonLayout;
    }

    private void login(UserDTO userDTO)
    {
        // SC.say("onSuccess: " + userDTO.toString());

        String username = userDTO.getUsername();
        String phoneBookId = Long.toString(userDTO.getUserId());
        String userType = userDTO.getPosition().getCode();
        String validPhoneBookId = Long.toString(userDTO.getUserId());
        long expiresUTC = new Date().getTime() + LOGIN_EXPIRATION;
        Date expiresDate = new Date(expiresUTC);

        if (username != null)
        {
            Cookies.setCookie(Constants.COOKIES_USERNAME, username, expiresDate);
        }
        if (phoneBookId != null)
        {
            Cookies.setCookie(Constants.COOKIES_PHONEBOOK_ID, phoneBookId, expiresDate);
        }
        if (validPhoneBookId != null)
        {
            Cookies.setCookie(Constants.COOKIES_VALID_PHONEBOOK_ID, validPhoneBookId, expiresDate);
        }
        if (userType != null)
        {
            Cookies.setCookie(Constants.COOKIES_USER_TYPE, userType, expiresDate);
        }
        // hide();
        if (callback != null)
        {
            callback.execute();
        }
        else
        {
            if (userType != null)
            {
                Cookies.setCookie(Constants.COOKIES_USER_TYPE, userType, expiresDate);
                if (userType.equalsIgnoreCase(Constants.USER))
                {
                    PhoneBookMain phonebookMain = new PhoneBookMain(eventBus, userDTO);
                    phonebookMain.draw();
                }
                else if (userType.equalsIgnoreCase(Constants.ADMIN))
                {
                    PhoneBookAdmin PhoneBookAdmin = new PhoneBookAdmin(eventBus, userDTO);
                    PhoneBookAdmin.draw();
                }
            }
            else
            {
                // TODO How do we process error?
            }
        }
    }

    private HLayout getFooter()
    {
        HLayout footer = new HLayout();
        footer.setHeight("5%");
        footer.setWidth100();
        // footer.setBorder("1px solid black");
        footer.setBackgroundColor("blue");

        return footer;
    }

    private HLayout getHeader()
    {
        HLayout topHeader = new HLayout();
        topHeader.setPadding(Constants.UI_PADDING_2);
        topHeader.setHeight("15%");
        topHeader.setWidth100();
        // topHeader.setBorder("1px solid green");
        topHeader.setBackgroundColor("blue");

        Label header = new Label("header");
        header.setPadding(Constants.UI_PADDING_2);
        header.setTitle("my header");
        header.setAutoHeight();
        header.setAutoWidth();
        header.setAlign(Alignment.CENTER);
        header.setValign(VerticalAlignment.CENTER);
        header.setBorder("1px solid white");

        topHeader.addMember(header);

        return topHeader;
    }

    private VLayout getSignupLayout()
    {
        // signupLayout.setBorder("5px solid red");

        // signupForm.setBorder("10px solid orange");
        signupForm.setDataSource(userDS);
        signupForm.setTitleWidth(200);
        signupForm.setWidth(400);
        signupForm.setLayoutAlign(Alignment.CENTER);

        idField = new TextItem(Constants.USER_ID);
        idField.setIconVAlign(VerticalAlignment.CENTER);
        idField.setTabIndex(0);
        idField.setRequired(true);
        idField.setSelectOnFocus(true);
        idField.setDefaultValue(0);
        idField.setVisible(false);

        usernameField.setTitle(Constants.TITLE_USER_USERNAME);
        usernameField.setIconVAlign(VerticalAlignment.CENTER);
        usernameField.setRequired(true);

        passwordField.setTitle(Constants.TITLE_USER_PASSWORD);
        passwordField.setIconVAlign(VerticalAlignment.CENTER);
        passwordField.setRequired(true);

        otherPasswordField.setTitle(Constants.TITLE_USER_OTHER_PASSWORD);
        otherPasswordField.setIconVAlign(VerticalAlignment.CENTER);
        otherPasswordField.setRequired(true);

        firstnameField.setTitle(Constants.TITLE_USER_FIRST_NAME);
        firstnameField.setIconVAlign(VerticalAlignment.CENTER);
        firstnameField.setRequired(true);

        lastnameField.setTitle(Constants.TITLE_USER_LAST_NAME);
        lastnameField.setIconVAlign(VerticalAlignment.CENTER);
        lastnameField.setRequired(true);

        emailField.setTitle(Constants.TITLE_USER_EMAIL);
        emailField.setIconVAlign(VerticalAlignment.CENTER);
        emailField.setRequired(true);

        securityQuestion1Field.setTitle(Constants.TITLE_USER_SECURITY_QUESTION_1);
        securityQuestion1Field.setIconVAlign(VerticalAlignment.CENTER);
        securityQuestion1Field.setRequired(true);

        securityAnswer1Field.setTitle(Constants.TITLE_USER_SECURITY_ANSWER_1);
        securityAnswer1Field.setIconVAlign(VerticalAlignment.CENTER);
        securityAnswer1Field.setRequired(true);

        securityQuestion2Field.setTitle(Constants.TITLE_USER_SECURITY_QUESTION_2);
        securityQuestion2Field.setIconVAlign(VerticalAlignment.CENTER);
        securityQuestion2Field.setRequired(true);

        securityAnswer2Field.setTitle(Constants.TITLE_USER_SECURITY_ANSWER_2);
        securityAnswer2Field.setIconVAlign(VerticalAlignment.CENTER);
        securityAnswer2Field.setRequired(true);

        positionField.setTitle(Constants.TITLE_USER_POSITION_ID);
        positionField.setDefaultValue(2);
        positionField.setVisible(false);

        birthdateField.setTitle(Constants.TITLE_USER_BIRTHDATE);
        birthdateField.setVisible(true);
        birthdateField.setRequired(true);

        HLayout spacerLayout = new HLayout();
        // spacerLayout.setBorder("5px solid black");
        Label spacerLabel = new Label();
        spacerLabel.setContents("&nbsp;");
        spacerLayout.addMember(spacerLabel);

        HLayout buttonLayout = new HLayout();
        // buttonLayout.setBorder("5px solid red");
        buttonLayout.setAlign(Alignment.CENTER);
        buttonLayout.setLayoutAlign(VerticalAlignment.CENTER);

        IButton saveBtn = new IButton("Save");
        saveBtn.setAlign(Alignment.CENTER);
        saveBtn.setValign(VerticalAlignment.CENTER);
        saveBtn.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                String signupValidationMessage = getSignupValidation(signupForm);
                if (signupValidationMessage == null || "".equals(signupValidationMessage))
                {
                    Record record = new ListGridRecord();
                    record.setAttribute(Constants.USER_ID, idField.getValue());
                    record.setAttribute(Constants.USER_ACTIVE, true);
                    record.setAttribute(Constants.USER_POSITION_ID, 2);
                    record.setAttribute(Constants.USER_USERNAME, usernameField.getValueAsString());
                    record.setAttribute(Constants.USER_PASSWORD, passwordField.getValueAsString());
                    record.setAttribute(Constants.USER_EMAIL, emailField.getValueAsString());
                    record.setAttribute(Constants.USER_FIRST_NAME, firstnameField.getValueAsString());
                    record.setAttribute(Constants.USER_LAST_NAME, lastnameField.getValueAsString());
                    record.setAttribute(Constants.USER_SECURITY_QUESTION_1, securityQuestion1Field.getValueAsString());
                    record.setAttribute(Constants.USER_SECURITY_QUESTION_2, securityQuestion2Field.getValueAsString());
                    record.setAttribute(Constants.USER_SECURITY_ANSWER_1, securityAnswer1Field.getValueAsString());
                    record.setAttribute(Constants.USER_SECURITY_ANSWER_2, securityAnswer2Field.getValueAsString());
                    record.setAttribute(Constants.USER_BIRTHDATE, birthdateField.getValueAsDate());
                    userDS.addData(record);
                    // signupForm.saveData();

                }
                else
                {
                    SC.say("Signup", signupValidationMessage);
                }
            }
        });

        IButton resetBtn = new IButton("Reset");
        resetBtn.setAlign(Alignment.CENTER);
        resetBtn.setValign(VerticalAlignment.CENTER);
        resetBtn.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                signupForm.clearValues();
            }
        });

        IButton cancelBtn = new IButton("Cancel");
        cancelBtn.setAlign(Alignment.CENTER);
        cancelBtn.setValign(VerticalAlignment.CENTER);
        cancelBtn.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                signupForm.clearValues();
                loginLayout.setVisible(true);
                signupLayout.setVisible(false);
            }
        });

        buttonLayout.addMember(saveBtn);
        buttonLayout.addMember(spacerLabel);
        buttonLayout.addMember(resetBtn);
        buttonLayout.addMember(spacerLabel);
        buttonLayout.addMember(cancelBtn);

        signupForm.setFields(idField, usernameField, passwordField, otherPasswordField, firstnameField, lastnameField,
            emailField, positionField, securityQuestion1Field, securityAnswer1Field, securityQuestion2Field,
            securityAnswer2Field, birthdateField);

        signupLayout.addMember(getSignupMessage());
        signupLayout.addMember(signupForm);
        signupLayout.addMember(spacerLayout);
        signupLayout.addMember(buttonLayout);
        signupLayout.setVisible(false);

        return signupLayout;
    }

    private String getSignupValidation(DynamicForm signupForm)
    {
        String signupValidationMessage = null;
        StringBuffer sb = new StringBuffer();
        if (usernameField.getValue() == null && !"".equals(usernameField.getValue()))
        {
            sb.append("Username cannot be left blank!<br/>");
        }
        if (passwordField.getValue() == null && !"".equals(passwordField.getValue()))
        {
            sb.append("Password cannot be left blank!<br/>");
        }
        if (otherPasswordField.getValue() == null && !"".equals(otherPasswordField.getValue()))
        {
            sb.append("Re-type Password cannot be left blank!<br/>");
        }
        if (firstnameField.getValue() == null && !"".equals(firstnameField.getValue()))
        {
            sb.append("User First Name cannot be left blank!<br/>");
        }
        if (lastnameField.getValue() == null && !"".equals(lastnameField.getValue()))
        {
            sb.append("User Last Name cannot be left blank!<br/>");
        }
        if (emailField.getValue() == null && !"".equals(emailField.getValue()))
        {
            sb.append("User Email Address cannot be left blank!<br/>");
        }
        if (securityQuestion1Field.getValue() == null && !"".equals(securityQuestion1Field.getValue()))
        {
            sb.append("Security Question 1 cannot be left blank!<br/>");
        }
        if (securityAnswer1Field.getValue() == null && !"".equals(securityAnswer1Field.getValue()))
        {
            sb.append("Security Answer 1 cannot be left blank!<br/>");
        }
        if (securityQuestion2Field.getValue() == null && !"".equals(securityQuestion2Field.getValue()))
        {
            sb.append("Security Question 2 cannot be left blank!<br/>");
        }
        if (securityAnswer2Field.getValue() == null && !"".equals(securityAnswer2Field.getValue()))
        {
            sb.append("Security Answer 2 cannot be left blank!<br/>");
        }
        if (birthdateField.getValue() == null && !"".equals(birthdateField.getValue()))
        {
            sb.append("Contact Birth Date cannot be left blank!<br/>");
        }
        signupValidationMessage = sb.toString();
        return signupValidationMessage;
    }

    private Label getSignupMessage()
    {
        Label signupMessageLabel = new Label("signupMessage");
        // signupMessageLabel.setBorder("1px solid green");
        signupMessageLabel.setBackgroundColor("#eeeeee");
        signupMessageLabel.setAlign(Alignment.CENTER);
        signupMessageLabel.setValign(VerticalAlignment.CENTER);
        signupMessageLabel.setBaseStyle("signupHeaderLabel");

        String message = new String();
        message = "<b>Every field is required!<br/>" + "Please enter all fields before submitting!</b>";

        signupMessageLabel.setContents(message);

        return signupMessageLabel;
    }

    private VLayout getMessageLayout()
    {
        VLayout messageLayout = new VLayout();
        messageLayout.setPadding(Constants.UI_PADDING_2);
        // messageLayout.setBorder("1px solid red");

        String contents =
            "Welcome to the Demo Contact Database Application.<br/>"
                + "This application users Spring, Maven, Tomcat, MySQL, and SmartGWT, using GWT-RPC as a way to move code between the client and server.<br/><br/>"
                + "I wrote this application as a start to a CRUD based application; we should be able to create our new contacts;<br/>"
                + "then we should be able to Create a new contact, and multiple web-links, emails, and phone numbers for them.<br/>"
                + "this demonstrates the many-to-one database relationship.<br/><br/>"
                + "There will always be the first contact added, but without links, emails, or phone numbers.<br/>"
                + "The demo name and password is demo/demo.  You can create emails, links, and phone numbers for that user.<br/><br/>"
                + "feel free to signup, and create a username and password, and you'll be to create your own contacts.<br/><br>"
                + "Yes, I know there are alot of other apps in the world to do the same thing.  I personally use Google for my contacts.<br/>"
                + "However, this was a sample code application which could be used as a base for other apps.";

        Label welcomeMsg = new Label("welcome");
        welcomeMsg.setPadding(Constants.UI_PADDING_2);
        welcomeMsg.setContents(contents);
        // welcomeMsg.setBorder("1px solid black");

        messageLayout.addMember(welcomeMsg);

        return messageLayout;
    }

    public void displayIfRequired(final LoginDialogCallback callback)
    {
        // com.google.gwt.user.client.Window.alert("execute");
        String phonebookId = Cookies.getCookie(Constants.COOKIES_PHONEBOOK_ID);
        String username = Cookies.getCookie(Constants.COOKIES_USERNAME);
        String validPhoneBookId = Cookies.getCookie(Constants.COOKIES_VALID_PHONEBOOK_ID);
        final String userType = Cookies.getCookie(Constants.COOKIES_USER_TYPE);

        if (phonebookId != null)
        {
            if (callback != null)
            {
                callback.execute();
            }
            else
            {
// loginService.login(phonebookId, new AsyncCallback<UserDTO>()
// {
//
// @Override
// public void onFailure(Throwable arg0)
// {
// SC.say("Unable to get user!");
// }
//
// @Override
// public void onSuccess(UserDTO userDto)
// {
// if (Constants.USER.equalsIgnoreCase(userType))
// {
// PhoneBookMain phonebookMain = new PhoneBookMain(eventBus, userDto);
// phonebookMain.draw();
// }
// else if (Constants.ADMIN.equalsIgnoreCase(userType))
// {
// PhoneBookAdmin PhoneBookAdmin = new PhoneBookAdmin(eventBus, userDto);
// PhoneBookAdmin.draw();
// }
// else
// {
// // TODO How do we process error?
// }
// }
//
// });
            }
            return;

        }
        else
        {
            show();
        }
    }

}
