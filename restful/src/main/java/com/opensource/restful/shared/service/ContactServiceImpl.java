package com.opensource.restful.shared.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensource.restful.domain.ContactEntity;
import com.opensource.restful.server.dao.ContactDao;

public class ContactServiceImpl implements IContactService
{
    @Autowired
    private ContactDao contactDao;

    public ContactDao getContactDao()
    {
        return contactDao;
    }

    public void setContactDao(ContactDao contactDao)
    {
        this.contactDao = contactDao;
    }

    @Override
    public List<ContactEntity> getAllContacts()
    {
        List<ContactEntity> contactList = contactDao.getAllContactEntitys();
        return contactList;
    }

}
