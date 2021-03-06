package com.opensource.restful.server.dao;

import java.util.List;

import com.opensource.restful.domain.ContactEntity;
import com.opensource.restful.domain.UserEntity;

public interface ContactDao
{
    public ContactEntity createContactEntity(ContactEntity contact);

    public void deleteContactEntity(ContactEntity contact);

    public void deleteContactEntity(long contactId);

    public ContactEntity updateContactEntity(ContactEntity contactEntity);

    public List<ContactEntity> getAllContactEntitys();

    public List<ContactEntity> getContactEntity(ContactEntity exampleEntity);

    public List<ContactEntity> getContactEntityByUser(UserEntity exampleEntity);

    public ContactEntity getContactEntity(long id);
}
