package com.opensource.restful.shared.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensource.restful.domain.ContactEmailEntity;
import com.opensource.restful.server.dao.ContactEmailDao;
import com.opensource.restful.shared.dto.ContactDTO;

public class ContactEmailServiceImpl implements IContactEmailService
{
    @Autowired
    private ContactEmailDao contactEmailDao;

    public ContactEmailDao getContactEmailDao()
    {
        return contactEmailDao;
    }

    public void setContactEmailDao(ContactEmailDao contactEmailDao)
    {
        this.contactEmailDao = contactEmailDao;
    }

    @Override
    public List<ContactEmailEntity> getAllEmailsByContactId(long contactId)
    {
        List<ContactEmailEntity> contactEmailList = contactEmailDao.getContactEmailEntityByContactId(contactId);
        return contactEmailList;
    }

    @Override
    public List<ContactEmailEntity> getAllEmailsByContactId(ContactDTO contactDto)
    {
        long contactId = contactDto.getContactId();
        List<ContactEmailEntity> contactEmailList = contactEmailDao.getContactEmailEntityByContactId(contactId);
        return contactEmailList;
    }

    @Override
    public ContactEmailEntity getEmailContactById(long emailId)
    {
        ContactEmailEntity contactEmailEntity = contactEmailDao.getContactEmailEntity(emailId);
        return contactEmailEntity;
    }
}
