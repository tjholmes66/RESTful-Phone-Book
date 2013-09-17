package com.opensource.restful.server.service;

import java.util.List;

import com.opensource.restful.domain.ContactEmailEntity;
import com.opensource.restful.shared.dto.ContactDTO;

public interface IContactEmailService
{

    List<ContactEmailEntity> getAllEmailsByContactId(long contactId);

    ContactEmailEntity getEmailContactById(long emailId);

    List<ContactEmailEntity> getAllEmailsByContactId(ContactDTO contactDto);

}
