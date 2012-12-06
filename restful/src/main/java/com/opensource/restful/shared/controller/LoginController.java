package com.opensource.restful.shared.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opensource.restful.domain.ContactEntity;
import com.opensource.restful.domain.UserEntity;
import com.opensource.restful.shared.dto.ContactDTO;
import com.opensource.restful.shared.dto.PositionDTO;
import com.opensource.restful.shared.dto.UserDTO;
import com.opensource.restful.shared.service.ILoginService;

@Controller
@RequestMapping("/login")
public class LoginController
{
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");

    @Autowired
    private ILoginService service;

    public ILoginService getService()
    {
        return service;
    }

    public void setService(ILoginService service)
    {
        this.service = service;
    }

    @RequestMapping(value = "/user/{username}/pwd/{password}", method = RequestMethod.GET)
    public @ResponseBody
    UserDTO login(@PathVariable("username") String username, @PathVariable("password") String password)
    {
        UserEntity userEntity = service.login(username, password);
        UserDTO userDto = new UserDTO();

        if (userEntity != null)
        {
            // userDto = new UserDTO();

            userDto.setActive(userEntity.isActive());
            userDto.setBirthdate(userEntity.getBirthdate());
            userDto.setEmail(userEntity.getEmail());
            userDto.setFirstName(userEntity.getFirstname());
            userDto.setUserId(userEntity.getUserId());
            userDto.setLastName(userEntity.getLastname());
            userDto.setPassword(userEntity.getPassword());

            if (userEntity.getContacts() != null)
            {
                ArrayList<ContactDTO> contactList = new ArrayList<ContactDTO>();
                for (ContactEntity contactEntity : userEntity.getContacts())
                {
                    ContactDTO contactDto = new ContactDTO();
                    contactDto.setAddress1(contactEntity.getAddress1());
                    contactDto.setAddress2(contactEntity.getAddress2());
                    contactDto.setBirthDate(contactEntity.getBirthDate());
                    contactDto.setCity(contactEntity.getCity());
                    contactDto.setCompanyId(contactEntity.getCompanyId());
                    contactDto.setContactId(contactEntity.getContactId());
                    contactDto.setEditedBy(contactEntity.getEditedBy());
                    contactDto.setEditedDate(contactEntity.getEditedDate());
                    // contactDto.setEmails(emails);
                    contactDto.setEnteredBy(contactEntity.getEnteredBy());
                    contactDto.setEnteredDate(contactEntity.getEnteredDate());
                    contactDto.setFirstName(contactEntity.getFirstName());
                    contactDto.setLastName(contactEntity.getLastName());
                    // contactDto.setLinks(links);
                    contactDto.setMiddleName(contactEntity.getMiddleName());
                    // contactDto.setPhones(phones);
                    contactDto.setPrefix(contactEntity.getPrefix());
                    contactDto.setState(contactEntity.getState());
                    contactDto.setSuffix(contactEntity.getSuffix());
                    contactDto.setUserId(contactEntity.getUser().getUserId());
                    contactDto.setZip(contactEntity.getZip());
                    contactList.add(contactDto);
                }
                userDto.setContacts(contactList);
            }

            PositionDTO positionDto = new PositionDTO();
            positionDto.setActive(userEntity.getPosition().isActive());
            positionDto.setCode(userEntity.getPosition().getCode());
            positionDto.setDescription(userEntity.getPosition().getDescription());
            positionDto.setId(userEntity.getPosition().getId());
            userDto.setPosition(positionDto);

            userDto.setSecurityAnswer1(userEntity.getSecurityAnswer1());
            userDto.setSecurityAnswer2(userEntity.getSecurityAnswer2());
            userDto.setSecurityQuestion1(userEntity.getSecurityQuestion1());
            userDto.setSecurityQuestion2(userEntity.getSecurityQuestion2());
            userDto.setUsername(userEntity.getUsername());
        }
        return userDto;
    }
}
