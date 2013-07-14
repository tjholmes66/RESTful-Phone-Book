package com.opensource.restful.client.widget;

import com.google.gwt.user.client.Cookies;
import com.opensource.restful.shared.CalendarUtil;
import com.opensource.restful.shared.Constants;
import com.opensource.restful.shared.dto.UserDTO;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class HeaderWidget extends HLayout
{
    private final static int HEADER_LABEL_SIZE_1 = 230;
    private final static int HEADER_LABEL_SIZE_2 = 170;

    private final static String HEADER_TITLE_STYLE = "headerTitle";
    private final static String HEADER_LABEL_STYLE = "headerLabel";

    private final static int HEADER_BOX_WIDTH = 200;

    // Box 1
    private String _fullName = "";
    private Label nameLabel = new Label("nameLabel");
    private Label usernameLabel = new Label("username");
    private Label dobLabel = new Label("dob");
    // Box 2
    private Label emailLabel = new Label("email");
    private Label rolesLabel = new Label("roles");
    private int _age = 0;
    private String _status = "";
    private Label ageLabel = new Label("age");
    // Box 3
    private Label homePhoneLabel = new Label("homePhone");
    private Label roleCountLabel = new Label("rolesCount");

    private UserDTO _userDto;
    private String userId = "-1";

    public HeaderWidget(UserDTO userDto)
    {
        super();
        this._userDto = userDto;

        userId = Long.toString(userDto.getUserId());

        setBorder("1px solid black");
        setWidth100();
        setHeight(90);
        setPadding(10);
        setBackgroundColor("#cecece");
        setMembersMargin(5);
        setLayoutMargin(10);

        addMember(createBox1());
        addMember(createBox2());
        addMember(createBox3());
        // addMember(createBox4());
    }

    private String getDob()
    {
        String dob = CalendarUtil.getStringFromDate(_userDto.getUserBirthDate());
        return dob;
    }

    private void getAgeStatus()
    {
        _age = CalendarUtil.getAge(_userDto.getUserBirthDate());
        if (_age >= 18)
        {
            _status = " (Adult)";
        }
        else
        {
            _status = " (Youth)";
        }
    }

    private VLayout createBox1()
    {
        int labelLength = 90;
        VLayout box1 = new VLayout();
        // box1.setBorder("5px solid pink");
        box1.setAutoHeight();
        box1.setAutoWidth();

        HLayout line1 = new HLayout();
        Label nameTitle = new Label(Constants.USER_FULL_NAME);
        nameTitle.setStyleName(HEADER_TITLE_STYLE);
        nameTitle.setContents(Constants.TITLE_USER_FULL_NAME + ":&nbsp;");
        nameTitle.setWidth(labelLength);
        // nameTitle.setBorder("1px solid black");

        getFullName();
        nameLabel.setStyleName(HEADER_LABEL_STYLE);
        nameLabel.setContents(_fullName + "&nbsp;");
        nameLabel.setWidth(HEADER_LABEL_SIZE_1);
        // nameLabel.setBorder("1px solid red");

        line1.addMember(nameTitle);
        line1.addMember(nameLabel);

        HLayout line2 = new HLayout();
        Label usernameTitle = new Label("usernameTitle");
        usernameTitle.setStyleName(HEADER_TITLE_STYLE);
        usernameTitle.setContents("Username:&nbsp");
        usernameTitle.setWidth(labelLength);
        // usernameTitle.setBorder("1px solid orange");

        usernameLabel.setStyleName(HEADER_LABEL_STYLE);
        usernameLabel.setContents(_userDto.getUsername());
        usernameLabel.setWidth(HEADER_LABEL_SIZE_1);
        // usernameLabel.setBorder("1px solid green");

        line2.addMember(usernameTitle);
        line2.addMember(usernameLabel);

        HLayout line3 = new HLayout();
        Label dobTitle = new Label("dobTitle");
        dobTitle.setStyleName(HEADER_TITLE_STYLE);
        dobTitle.setContents("Birth Date:&nbsp;");
        dobTitle.setWidth(labelLength);
        // dobTitle.setBorder("1px solid black");

        dobLabel.setStyleName(HEADER_LABEL_STYLE);
        dobLabel.setContents(getDob());
        dobLabel.setAutoWidth();
        // dobLabel.setBorder("1px solid red");

        line3.addMember(dobTitle);
        line3.addMember(dobLabel);

        box1.addMember(line1);
        box1.addMember(line2);
        box1.addMember(line3);

        return box1;
    }

    /*
    private String getRoles()
    {
        StringBuffer sb = new StringBuffer();
        for (UserRoleDTO role : _userDto.getRoles())
        {
            sb.append(role.getRole().getRoleName() + ", ");
        }
        return sb.toString();
    }
    */

    private VLayout createBox2()
    {
        int labelLength = 100;
        VLayout box2 = new VLayout();
        // box2.setBorder("5px solid green");
        box2.setAutoHeight();
        box2.setAutoWidth();

        HLayout line1 = new HLayout();
        Label emailTitle = new Label("emailTitle");
        emailTitle.setStyleName(HEADER_TITLE_STYLE);
        emailTitle.setContents("Home Email:&nbsp;&nbsp;&nbsp;");
        emailTitle.setWidth(labelLength);
        // emailTitle.setBorder("1px solid black");
        emailTitle.setAlign(Alignment.RIGHT);

        emailLabel.setStyleName(HEADER_LABEL_STYLE);
        emailLabel.setContents(_userDto.getUserEmail());
        emailLabel.setWidth(HEADER_LABEL_SIZE_1);
        // emailLabel.setBorder("1px solid red");

        line1.addMember(emailTitle);
        line1.addMember(emailLabel);

        HLayout line2 = new HLayout();
        Label rolesTitle = new Label("rolesTitle");
        rolesTitle.setStyleName(HEADER_TITLE_STYLE);
        rolesTitle.setContents("Roles:&nbsp;&nbsp;&nbsp;");
        rolesTitle.setWidth(labelLength);
        // rolesTitle.setBorder("1px solid black");
        rolesTitle.setAlign(Alignment.RIGHT);

        rolesLabel.setStyleName(HEADER_LABEL_STYLE);
        rolesLabel.setContents("");
        rolesLabel.setWidth(HEADER_LABEL_SIZE_1);

        line2.addMember(rolesTitle);
        line2.addMember(rolesLabel);

        HLayout line3 = new HLayout();
        Label ageTitle = new Label("ageTitle");
        ageTitle.setStyleName(HEADER_TITLE_STYLE);
        ageTitle.setContents("Age:&nbsp;&nbsp;&nbsp;");
        ageTitle.setWidth(labelLength);
        // ageTitle.setBorder("1px solid black");
        ageTitle.setAlign(Alignment.RIGHT);

        getAgeStatus();

        ageLabel.setStyleName(HEADER_LABEL_STYLE);
        ageLabel.setContents(Integer.toString(_age) + _status);
        ageLabel.setWidth(HEADER_LABEL_SIZE_1);

        line3.addMember(ageTitle);
        line3.addMember(ageLabel);

        box2.addMember(line1);
        box2.addMember(line2);
        box2.addMember(line3);

        return box2;
    }

    private VLayout createBox3()
    {

        int labelLength = 110;
        VLayout box3 = new VLayout();
        // box3.setBorder("5px solid orange");
        box3.setAutoHeight();
        box3.setAutoWidth();

        IButton logoutButton = new IButton(Constants.LOGOUT);
        logoutButton.setLayoutAlign(VerticalAlignment.CENTER);
        logoutButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                Cookies.removeCookie(Constants.COOKIES_USERNAME);
                Cookies.removeCookie(Constants.COOKIES_PHONEBOOK_ID);
                Cookies.removeCookie(Constants.COOKIES_USER_TYPE);
                Cookies.removeCookie(Constants.COOKIES_VALID_PHONEBOOK_ID);
                hide();
                // LoginDialog.getInstance().displayIfRequired(null);
                _userDto = null;
            }
        });

        HLayout line2 = new HLayout();
        Label homePhoneTitle = new Label("homePhoneTitle");
        homePhoneTitle.setStyleName(HEADER_TITLE_STYLE);
        homePhoneTitle.setContents("Home Phone:&nbsp;&nbsp;&nbsp;");
        homePhoneTitle.setWidth(labelLength);
        // homePhoneTitle.setBorder("1px solid black");
        homePhoneTitle.setAlign(Alignment.RIGHT);

        homePhoneLabel.setStyleName(HEADER_LABEL_STYLE);
        homePhoneLabel.setContents("");
        homePhoneLabel.setWidth(HEADER_LABEL_SIZE_2);
        // homePhoneLabel.setBorder("3px solid green");

        line2.addMember(homePhoneTitle);
        line2.addMember(homePhoneLabel);

        HLayout line3 = new HLayout();
        Label roleCountTitle = new Label("rolesCountTitle");
        roleCountTitle.setStyleName(HEADER_TITLE_STYLE);
        roleCountTitle.setContents("Role Count:&nbsp;&nbsp;&nbsp;");
        roleCountTitle.setWidth(labelLength);
        // roleCountTitle.setBorder("1px solid grey");
        roleCountTitle.setAlign(Alignment.RIGHT);

        roleCountLabel.setStyleName(HEADER_LABEL_STYLE);
        roleCountLabel.setContents(Integer.toString(0));
        roleCountLabel.setWidth(HEADER_LABEL_SIZE_2);
        // roleCountLabel.setBorder("3px solid red");

        line3.addMember(roleCountTitle);
        line3.addMember(roleCountLabel);

        box3.addMember(logoutButton);
        box3.addMember(line2);
        box3.addMember(line3);

        return box3;

    }

    private VLayout createBox4()
    {
        VLayout box4 = new VLayout();
        box4.setBorder("5px solid red");
        box4.setAutoHeight();
        box4.setAutoWidth();

        IButton logoutButton = new IButton(Constants.LOGOUT);
        logoutButton.setLayoutAlign(VerticalAlignment.CENTER);
        logoutButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                Cookies.removeCookie(Constants.COOKIES_USERNAME);
                Cookies.removeCookie(Constants.COOKIES_PHONEBOOK_ID);
                Cookies.removeCookie(Constants.COOKIES_USER_TYPE);
                Cookies.removeCookie(Constants.COOKIES_VALID_PHONEBOOK_ID);
                hide();
                // LoginDialog.getInstance().displayIfRequired(null);
            }
        });

        box4.addMember(logoutButton);

        return box4;
    }

    private void getFullName()
    {
        _fullName = "";
        StringBuffer sb = new StringBuffer();
        if (_userDto.getUserFirstName() != null && !"".equals(_userDto.getUserFirstName()))
        {
            sb.append(_userDto.getUserFirstName() + " ");
        }
        if (_userDto.getUserLastName() != null && !"".equals(_userDto.getUserLastName()))
        {
            sb.append(_userDto.getUserLastName() + " ");
        }
        _fullName = sb.toString();
    }

}
