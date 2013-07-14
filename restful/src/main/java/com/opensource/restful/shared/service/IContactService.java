package com.opensource.restful.shared.service;

import java.util.List;

import com.opensource.restful.domain.ContactEntity;
import com.opensource.restful.shared.dto.ContactDTO;

public interface IContactService
{
    List<ContactEntity> getAllContacts();

    List<ContactEntity> getContactsByUserId(long userId);

    ContactEntity getContactById(long contactId);

    ContactEntity add(ContactDTO contactDto);

    ContactEntity update(ContactDTO contactDto);

    void remove(long contactId);
}
