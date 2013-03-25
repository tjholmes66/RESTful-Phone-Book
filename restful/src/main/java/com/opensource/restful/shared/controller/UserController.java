package com.opensource.restful.shared.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opensource.restful.domain.ContactEntity;
import com.opensource.restful.domain.UserEntity;
import com.opensource.restful.shared.dto.ContactDTO;
import com.opensource.restful.shared.dto.PositionDTO;
import com.opensource.restful.shared.dto.UserDTO;
import com.opensource.restful.shared.service.IUserService;

@Controller
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private IUserService service;

    public IUserService getService()
    {
        return service;
    }

    public void setService(IUserService service)
    {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    ArrayList<UserDTO> getAllUsers()
    {
        ArrayList<UserEntity> userEntityList = (ArrayList) service.getAllUsers();

        ArrayList<UserDTO> userDTOList = new ArrayList<UserDTO>();
        for (UserEntity userEntity : userEntityList)
        {
            UserDTO userDto = new UserDTO();
            userDto.setUserId(userEntity.getUserId());
            userDto.setFirstName(userEntity.getFirstname());
            userDto.setLastName(userEntity.getLastname());
            userDTOList.add(userDto);
        }
        return userDTOList;
    }

    @RequestMapping(value = "/userId/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    UserDTO getUserById(@PathVariable("userId") long userId)
    {
        UserEntity userEntity = service.getUserById(userId);
        return mappingUser(userEntity);
    }

    /*
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody
    UserDTO updateUser(@ModelAttribute UserDTO person)
    {
        // The person object will only have the fields that are
        // being updated populated + the primary key.
        // The method should return a full object with the same primary key.
        return null;
    }
    */

    /*
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json",
        headers = "content-type=application/json")
    public @ResponseBody
    UserDTO updateUser(@RequestBody UserDTO user)
    {
        UserEntity userEntity = service.update(user);
        return mappingUser(userEntity);
    }
    */

    /*
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody
    UserDTO createUser(UserDTO user)
    {
        // This should create a new person with a new primary key
        UserEntity userEntity = service.add(user);

        UserDTO userDto = new UserDTO();
        return userDto;
    }
    */

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json",
        headers = "content-type=application/json")
    public @ResponseBody
    UserDTO createUser(@RequestBody UserDTO user)
    {
        UserEntity userEntity = service.add(user);
        return mappingUser(userEntity);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json",
        headers = "content-type=application/json")
    public @ResponseBody
    UserDTO updateUser(@RequestBody UserDTO user)
    {
        UserEntity userEntity = service.update(user);
        return mappingUser(userEntity);
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public @ResponseBody
    void deleteUser(@PathVariable("userId") long userId)
    {
        service.remove(userId);
    }

    private UserDTO mappingUser(UserEntity userEntity)
    {
        UserDTO userDto = new UserDTO();

        if (userEntity != null)
        {
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