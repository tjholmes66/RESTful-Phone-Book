package com.opensource.restful.shared;

import java.util.ArrayList;

import com.opensource.restful.domain.ContactEntity;
import com.opensource.restful.domain.UserEntity;
import com.opensource.restful.shared.dto.ContactDTO;
import com.opensource.restful.shared.dto.PositionDTO;
import com.opensource.restful.shared.dto.UserDTO;

public class Mapping
{
    public final static UserDTO mappingUser(UserEntity userEntity)
    {
        UserDTO userDto = new UserDTO();

        if (userEntity != null)
        {
            userDto.setUserActive(userEntity.isActive());
            userDto.setUserBirthDate(userEntity.getBirthdate());
            userDto.setUserEmail(userEntity.getEmail());
            userDto.setUserFirstName(userEntity.getFirstname());
            userDto.setUserId(userEntity.getUserId());
            userDto.setUserLastName(userEntity.getLastname());
            userDto.setPassword(userEntity.getPassword());

            if (userEntity.getContacts() != null)
            {
                ArrayList<ContactDTO> contactList = new ArrayList<ContactDTO>();
                for (ContactEntity contactEntity : userEntity.getContacts())
                {
                    ContactDTO contactDto = mappingContact(contactEntity);
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

            userDto.setUserSecurityAnswer1(userEntity.getSecurityAnswer1());
            userDto.setUserSecurityAnswer2(userEntity.getSecurityAnswer2());
            userDto.setUserSecurityQuestion1(userEntity.getSecurityQuestion1());
            userDto.setUserSecurityQuestion2(userEntity.getSecurityQuestion2());
            userDto.setUsername(userEntity.getUsername());
        }
        return userDto;
    }

    public final static ContactDTO mappingContact(ContactEntity contactEntity)
    {
        ContactDTO contactDto = new ContactDTO();
        if (contactEntity != null)
        {
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
        }
        return contactDto;
    }

}
