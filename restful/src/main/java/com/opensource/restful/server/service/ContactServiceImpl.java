package com.opensource.restful.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.restful.domain.ContactEntity;
import com.opensource.restful.domain.UserEntity;
import com.opensource.restful.server.dao.ContactDao;
import com.opensource.restful.shared.dto.ContactDTO;

@Transactional
@Service("contactService")
public class ContactServiceImpl implements IContactService
{
    @Autowired
    private ContactDao contactDao;

    @Override
    public List<ContactEntity> getAllContacts()
    {
        List<ContactEntity> contactList = contactDao.getAllContactEntitys();
        return contactList;
    }

    @Override
    public List<ContactEntity> getContactsByUserId(long userId)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        List<ContactEntity> contactList = contactDao.getContactEntityByUser(userEntity);
        return contactList;
    }

    @Override
    public ContactEntity getContactById(long contactId)
    {
        ContactEntity contactEntity = contactDao.getContactEntity(contactId);
        return contactEntity;
    }

    @Override
    public ContactEntity add(ContactDTO contactDto)
    {
        ContactEntity newContact = new ContactEntity();
        newContact.setContactId(contactDto.getContactId());
        // ***************************************************
        newContact.setPrefix(contactDto.getPrefix());
        newContact.setFirstName(contactDto.getFirstName());
        newContact.setMiddleName(contactDto.getMiddleName());
        newContact.setLastName(contactDto.getLastName());
        newContact.setSuffix(contactDto.getSuffix());
        // ***************************************************
        newContact.setAddress1(contactDto.getAddress1());
        newContact.setAddress2(contactDto.getAddress2());
        newContact.setCity(contactDto.getCity());
        newContact.setState(contactDto.getState());
        newContact.setZip(contactDto.getZip());
        // ***************************************************
        newContact.setBirthDate(contactDto.getBirthDate());
        newContact.setCompanyId(contactDto.getCompanyId());
        // ***************************************************
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(contactDto.getUserId());
        newContact.setUser(userEntity);
        // ***************************************************
        newContact.setEditedBy(contactDto.getEditedBy());
        newContact.setEditedDate(contactDto.getEditedDate());
        newContact.setEnteredBy(contactDto.getEnteredBy());
        newContact.setEnteredDate(contactDto.getEnteredDate());
        // ***************************************************
        ContactEntity contactEntity = contactDao.createContactEntity(newContact);
        return contactEntity;
    }

    @Override
    public ContactEntity update(ContactDTO contactDto)
    {
        ContactEntity newContact = contactDao.getContactEntity(contactDto.getContactId());
        newContact.setContactId(contactDto.getContactId());
        // ***************************************************
        newContact.setPrefix(contactDto.getPrefix());
        newContact.setFirstName(contactDto.getFirstName());
        newContact.setMiddleName(contactDto.getMiddleName());
        newContact.setLastName(contactDto.getLastName());
        newContact.setSuffix(contactDto.getSuffix());
        // ***************************************************
        newContact.setAddress1(contactDto.getAddress1());
        newContact.setAddress2(contactDto.getAddress2());
        newContact.setCity(contactDto.getCity());
        newContact.setState(contactDto.getState());
        newContact.setZip(contactDto.getZip());
        // ***************************************************
        newContact.setEditedBy(contactDto.getEditedBy());
        newContact.setEditedDate(contactDto.getEditedDate());
        newContact.setEnteredBy(contactDto.getEnteredBy());
        newContact.setEnteredDate(contactDto.getEnteredDate());
        // ***************************************************
        newContact.setBirthDate(contactDto.getBirthDate());
        newContact.setCompanyId(contactDto.getCompanyId());
        // ***************************************************
        // newContact.setEmails(emails);
        // newContact.setLinks(links);
        // newContact.setPhones(phones);
        // ***************************************************
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(contactDto.getUserId());
        newContact.setUser(userEntity);
        // ***************************************************
        ContactEntity updatedContactEntity = contactDao.updateContactEntity(newContact);
        return updatedContactEntity;
    }

    @Override
    public void remove(long contactId)
    {
        System.out.println("remove: contactId=" + contactId);
        contactDao.deleteContactEntity(contactId);
    }
}
