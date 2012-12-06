package com.opensource.restful.shared;

import java.util.ArrayList;

import com.opensource.restful.domain.ContactEmailEntity;
import com.opensource.restful.domain.ContactEntity;
import com.opensource.restful.domain.ContactLinkEntity;
import com.opensource.restful.domain.ContactPhoneEntity;
import com.opensource.restful.domain.EmailTypeEntity;
import com.opensource.restful.domain.LinkTypeEntity;
import com.opensource.restful.domain.PhoneTypeEntity;
import com.opensource.restful.domain.PositionEntity;
import com.opensource.restful.domain.UserEntity;
import com.opensource.restful.shared.dto.ContactDTO;
import com.opensource.restful.shared.dto.ContactEmailDTO;
import com.opensource.restful.shared.dto.ContactLinkDTO;
import com.opensource.restful.shared.dto.ContactPhoneDTO;
import com.opensource.restful.shared.dto.EmailTypeDTO;
import com.opensource.restful.shared.dto.LinkTypeDTO;
import com.opensource.restful.shared.dto.PhoneTypeDTO;
import com.opensource.restful.shared.dto.PositionDTO;
import com.opensource.restful.shared.dto.UserDTO;

public class Mapping
{

    final static public UserDTO createUser(UserEntity userEntity)
    {
        UserDTO userDTO = null;
        if (userEntity != null)
        {
            userDTO = new UserDTO();
            userDTO.setActive(userEntity.isActive());
            // =========================================================
            ArrayList<ContactDTO> contactDTOArrayList = null;
            if (userEntity.getContacts() != null)
            {
                contactDTOArrayList = new ArrayList<ContactDTO>();
                for (ContactEntity contactEntity : userEntity.getContacts())
                {
                    ContactDTO contactDTO = createContact(contactEntity);
                    if (contactDTO != null)
                    {
                        contactDTOArrayList.add(contactDTO);
                    }
                }
            }
            userDTO.setContacts((ArrayList<ContactDTO>) contactDTOArrayList);
            // =========================================================
            userDTO.setEmail(userEntity.getEmail());
            userDTO.setFirstName(userEntity.getFirstname());
            userDTO.setUserId(userEntity.getUserId());
            userDTO.setLastName(userEntity.getLastname());
            userDTO.setPassword(userEntity.getPassword());
            userDTO.setPosition(createPosition(userEntity.getPosition()));
            userDTO.setSecurityAnswer1(userEntity.getSecurityAnswer1());
            userDTO.setSecurityAnswer2(userEntity.getSecurityAnswer2());
            userDTO.setSecurityQuestion1(userEntity.getSecurityQuestion1());
            userDTO.setSecurityQuestion2(userEntity.getSecurityQuestion2());
            userDTO.setUsername(userEntity.getUsername());
            userDTO.setBirthdate(userEntity.getBirthdate());
        }
        ;
        return userDTO;
    }

    final static public ContactDTO createContact(ContactEntity contactEntity)
    {
        ContactDTO contactDTO = null;
        if (contactEntity != null)
        {
            contactDTO = new ContactDTO();
            contactDTO.setUserId(contactEntity.getUser().getUserId());
            contactDTO.setContactId(contactEntity.getContactId());

            contactDTO.setPrefix(contactEntity.getPrefix());
            contactDTO.setFirstName(contactEntity.getFirstName());
            contactDTO.setMiddleName(contactEntity.getMiddleName());
            contactDTO.setLastName(contactEntity.getLastName());
            contactDTO.setSuffix(contactEntity.getSuffix());

            contactDTO.setAddress1(contactEntity.getAddress1());
            contactDTO.setAddress2(contactEntity.getAddress2());
            contactDTO.setCity(contactEntity.getCity());
            contactDTO.setState(contactEntity.getState());
            contactDTO.setZip(contactEntity.getZip());

            contactDTO.setEnteredBy(contactEntity.getEnteredBy());
            contactDTO.setEnteredDate(contactEntity.getEnteredDate());
            contactDTO.setEditedBy(contactEntity.getEditedBy());
            contactDTO.setEditedDate(contactEntity.getEditedDate());

            contactDTO.setBirthDate(contactEntity.getBirthDate());
            contactDTO.setCompanyId(contactEntity.getCompanyId());
            // ==============================================================
            ArrayList<ContactEmailDTO> contactEmailDTOArrayList = null;
            if (contactEntity.getEmails() != null)
            {
                contactEmailDTOArrayList = new ArrayList<ContactEmailDTO>();
                for (ContactEmailEntity contactEmailEntity : contactEntity.getEmails())
                {
                    ContactEmailDTO contactEmailDTO = createContactEmail(contactEmailEntity);
                    if (contactEmailDTO != null)
                    {
                        contactEmailDTOArrayList.add(contactEmailDTO);
                    }
                }
            }
            contactDTO.setEmails((ArrayList<ContactEmailDTO>) contactEmailDTOArrayList);
            // ==============================================================
            ArrayList<ContactLinkDTO> contactLinkDTOArrayList = null;
            if (contactEntity.getLinks() != null)
            {
                contactLinkDTOArrayList = new ArrayList<ContactLinkDTO>();
                for (ContactLinkEntity contactLinkEntity : contactEntity.getLinks())
                {
                    ContactLinkDTO contactLinkDTO = createContactLink(contactLinkEntity);
                    if (contactLinkDTO != null)
                    {
                        contactLinkDTOArrayList.add(contactLinkDTO);
                    }
                }
            }
            contactDTO.setLinks((ArrayList<ContactLinkDTO>) contactLinkDTOArrayList);
            // ==============================================================
            ArrayList<ContactPhoneDTO> contactPhoneDTOArrayList = null;
            if (contactEntity.getPhones() != null)
            {
                contactPhoneDTOArrayList = new ArrayList<ContactPhoneDTO>();
                for (ContactPhoneEntity contactPhoneEntity : contactEntity.getPhones())
                {
                    ContactPhoneDTO contactPhoneDTO = createContactPhone(contactPhoneEntity);
                    if (contactPhoneDTO != null)
                    {
                        contactPhoneDTOArrayList.add(contactPhoneDTO);
                    }
                }
            }
            contactDTO.setPhones((ArrayList<ContactPhoneDTO>) contactPhoneDTOArrayList);
            // ==============================================================
            contactDTO.setEnteredBy(contactEntity.getEnteredBy());
            contactDTO.setEnteredDate(contactEntity.getEnteredDate());
            contactDTO.setFirstName(contactEntity.getFirstName());
            contactDTO.setContactId(contactEntity.getContactId());
            contactDTO.setLastName(contactEntity.getLastName());
            contactDTO.setMiddleName(contactEntity.getMiddleName());
            contactDTO.setPrefix(contactEntity.getPrefix());
            contactDTO.setState(contactEntity.getState());
            contactDTO.setSuffix(contactEntity.getSuffix());
            // contactDTO.setUser(contactEntity.getUser());
            contactDTO.setZip(contactEntity.getZip());
        }
        return contactDTO;
    }

    final static public EmailTypeDTO createEmailType(EmailTypeEntity emailTypeEntity)
    {
        EmailTypeDTO emailTypeDTO = null;
        if (emailTypeEntity != null)
        {
            emailTypeDTO = new EmailTypeDTO();
            emailTypeDTO.setActive(emailTypeEntity.isActive());
            emailTypeDTO.setDescription(emailTypeEntity.getDescription());
            emailTypeDTO.setId(emailTypeEntity.getId());
        }
        return emailTypeDTO;
    }

    final static public ContactEmailDTO createContactEmail(ContactEmailEntity contactEmailEntity)
    {
        ContactEmailDTO contactEmailDTO = null;
        if (contactEmailEntity != null)
        {
            contactEmailDTO = new ContactEmailDTO();
            contactEmailDTO.setContactId(contactEmailEntity.getContact().getContactId());
            contactEmailDTO.setEmail(contactEmailEntity.getEmail());
            contactEmailDTO.setEmailId(contactEmailEntity.getEmailId());
            contactEmailDTO.setEmailType(createEmailType(contactEmailEntity.getEmailType()));
            contactEmailDTO.setEnteredDate(contactEmailEntity.getEnteredDate());
        }
        return contactEmailDTO;
    }

    final static public LinkTypeDTO createLinkType(LinkTypeEntity linkTypeEntity)
    {
        LinkTypeDTO linkTypeDTO = null;
        if (linkTypeEntity != null)
        {
            linkTypeDTO = new LinkTypeDTO();
            linkTypeDTO.setActive(linkTypeEntity.isActive());
            linkTypeDTO.setDescription(linkTypeEntity.getDescription());
            linkTypeDTO.setId(linkTypeEntity.getId());
        }
        return linkTypeDTO;
    }

    final static public ContactLinkDTO createContactLink(ContactLinkEntity contactLinkEntity)
    {
        ContactLinkDTO contactLinkDTO = null;
        if (contactLinkEntity != null)
        {
            contactLinkDTO = new ContactLinkDTO();
            contactLinkDTO.setContactId(contactLinkEntity.getContact().getContactId());
            contactLinkDTO.setLink(contactLinkEntity.getLink());
            contactLinkDTO.setLinkId(contactLinkEntity.getLinkId());
            contactLinkDTO.setLinkDescription(contactLinkEntity.getLinkDescription());
            contactLinkDTO.setLinkType(createLinkType(contactLinkEntity.getLinkType()));
            contactLinkDTO.setEnteredDate(contactLinkEntity.getEnteredDate());
        }
        return contactLinkDTO;
    }

    final static public PhoneTypeDTO createPhoneType(PhoneTypeEntity phoneTypeEntity)
    {
        PhoneTypeDTO phoneTypeDTO = null;
        if (phoneTypeEntity != null)
        {
            phoneTypeDTO = new PhoneTypeDTO();
            phoneTypeDTO.setActive(phoneTypeEntity.isActive());
            phoneTypeDTO.setDescription(phoneTypeEntity.getDescription());
            phoneTypeDTO.setId(phoneTypeEntity.getId());
        }
        return phoneTypeDTO;
    }

    final static public ContactPhoneDTO createContactPhone(ContactPhoneEntity contactPhoneEntity)
    {
        ContactPhoneDTO contactPhoneDTO = null;
        if (contactPhoneEntity != null)
        {
            contactPhoneDTO = new ContactPhoneDTO();
            contactPhoneDTO.setContactId(contactPhoneEntity.getContact().getContactId());
            contactPhoneDTO.setPhone(contactPhoneEntity.getPhone());
            contactPhoneDTO.setPhoneId(contactPhoneEntity.getPhoneId());
            contactPhoneDTO.setPhoneType(createPhoneType(contactPhoneEntity.getPhoneType()));
            contactPhoneDTO.setEnteredDate(contactPhoneEntity.getEnteredDate());
        }
        return contactPhoneDTO;
    }

    final static public PositionDTO createPosition(PositionEntity positionEntity)
    {
        PositionDTO positionDTO = null;
        if (positionEntity != null)
        {
            positionDTO = new PositionDTO();
            positionDTO.setActive(positionEntity.isActive());
            positionDTO.setCode(positionEntity.getCode());
            positionDTO.setDescription(positionEntity.getDescription());
            positionDTO.setId(positionEntity.getId());
        }
        return positionDTO;
    }
}
