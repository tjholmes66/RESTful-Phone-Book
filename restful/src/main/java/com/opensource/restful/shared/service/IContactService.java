package com.opensource.restful.shared.service;

import java.util.List;

import com.opensource.restful.domain.ContactEntity;

public interface IContactService
{
    public List<ContactEntity> getAllContacts();

    ContactEntity getContactById(long contactId);
}
