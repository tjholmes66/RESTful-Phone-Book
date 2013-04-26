package com.opensource.restful.shared.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("serial")
public class UserDTO implements Serializable
{
    private long userId;
    private boolean userActive;
    private PositionDTO position;
    private String username;
    private String password;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userSecurityQuestion1;
    private String userSecurityAnswer1;
    private String userSecurityQuestion2;
    private String userSecurityAnswer2;
    private Date userBirthDate;
    private ArrayList<ContactDTO> contacts;

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public boolean isUserActive()
    {
        return userActive;
    }

    public void setUserActive(boolean userActive)
    {
        this.userActive = userActive;
    }

    public PositionDTO getPosition()
    {
        return position;
    }

    public void setPosition(PositionDTO position)
    {
        this.position = position;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUserFirstName()
    {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName)
    {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName()
    {
        return userLastName;
    }

    public void setUserLastName(String userLastName)
    {
        this.userLastName = userLastName;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    public String getUserSecurityQuestion1()
    {
        return userSecurityQuestion1;
    }

    public void setUserSecurityQuestion1(String userSecurityQuestion1)
    {
        this.userSecurityQuestion1 = userSecurityQuestion1;
    }

    public String getUserSecurityAnswer1()
    {
        return userSecurityAnswer1;
    }

    public void setUserSecurityAnswer1(String userSecurityAnswer1)
    {
        this.userSecurityAnswer1 = userSecurityAnswer1;
    }

    public String getUserSecurityQuestion2()
    {
        return userSecurityQuestion2;
    }

    public void setUserSecurityQuestion2(String userSecurityQuestion2)
    {
        this.userSecurityQuestion2 = userSecurityQuestion2;
    }

    public String getUserSecurityAnswer2()
    {
        return userSecurityAnswer2;
    }

    public void setUserSecurityAnswer2(String userSecurityAnswer2)
    {
        this.userSecurityAnswer2 = userSecurityAnswer2;
    }

    public ArrayList<ContactDTO> getContacts()
    {
        return contacts;
    }

    public void setContacts(ArrayList<ContactDTO> contacts)
    {
        this.contacts = contacts;
    }

    public Date getUserBirthDate()
    {
        return userBirthDate;
    }

    public void setUserBirthDate(Date userBirthDate)
    {
        this.userBirthDate = userBirthDate;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contacts == null) ? 0 : contacts.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + (userActive ? 1231 : 1237);
        result = prime * result + ((userBirthDate == null) ? 0 : userBirthDate.hashCode());
        result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
        result = prime * result + ((userFirstName == null) ? 0 : userFirstName.hashCode());
        result = prime * result + (int) (userId ^ (userId >>> 32));
        result = prime * result + ((userLastName == null) ? 0 : userLastName.hashCode());
        result = prime * result + ((userSecurityAnswer1 == null) ? 0 : userSecurityAnswer1.hashCode());
        result = prime * result + ((userSecurityAnswer2 == null) ? 0 : userSecurityAnswer2.hashCode());
        result = prime * result + ((userSecurityQuestion1 == null) ? 0 : userSecurityQuestion1.hashCode());
        result = prime * result + ((userSecurityQuestion2 == null) ? 0 : userSecurityQuestion2.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDTO other = (UserDTO) obj;
        if (contacts == null)
        {
            if (other.contacts != null)
                return false;
        }
        else if (!contacts.equals(other.contacts))
            return false;
        if (password == null)
        {
            if (other.password != null)
                return false;
        }
        else if (!password.equals(other.password))
            return false;
        if (position == null)
        {
            if (other.position != null)
                return false;
        }
        else if (!position.equals(other.position))
            return false;
        if (userActive != other.userActive)
            return false;
        if (userBirthDate == null)
        {
            if (other.userBirthDate != null)
                return false;
        }
        else if (!userBirthDate.equals(other.userBirthDate))
            return false;
        if (userEmail == null)
        {
            if (other.userEmail != null)
                return false;
        }
        else if (!userEmail.equals(other.userEmail))
            return false;
        if (userFirstName == null)
        {
            if (other.userFirstName != null)
                return false;
        }
        else if (!userFirstName.equals(other.userFirstName))
            return false;
        if (userId != other.userId)
            return false;
        if (userLastName == null)
        {
            if (other.userLastName != null)
                return false;
        }
        else if (!userLastName.equals(other.userLastName))
            return false;
        if (userSecurityAnswer1 == null)
        {
            if (other.userSecurityAnswer1 != null)
                return false;
        }
        else if (!userSecurityAnswer1.equals(other.userSecurityAnswer1))
            return false;
        if (userSecurityAnswer2 == null)
        {
            if (other.userSecurityAnswer2 != null)
                return false;
        }
        else if (!userSecurityAnswer2.equals(other.userSecurityAnswer2))
            return false;
        if (userSecurityQuestion1 == null)
        {
            if (other.userSecurityQuestion1 != null)
                return false;
        }
        else if (!userSecurityQuestion1.equals(other.userSecurityQuestion1))
            return false;
        if (userSecurityQuestion2 == null)
        {
            if (other.userSecurityQuestion2 != null)
                return false;
        }
        else if (!userSecurityQuestion2.equals(other.userSecurityQuestion2))
            return false;
        if (username == null)
        {
            if (other.username != null)
                return false;
        }
        else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "UserDTO [userId=" + userId + ", userActive=" + userActive + ", position=" + position + ", username="
            + username + ", password=" + password + ", userFirstName=" + userFirstName + ", userLastName="
            + userLastName + ", userEmail=" + userEmail + ", userSecurityQuestion1=" + userSecurityQuestion1
            + ", userSecurityAnswer1=" + userSecurityAnswer1 + ", userSecurityQuestion2=" + userSecurityQuestion2
            + ", userSecurityAnswer2=" + userSecurityAnswer2 + ", userBirthDate=" + userBirthDate + ", contacts="
            + contacts + "]";
    }

}
