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
import com.opensource.restful.shared.Mapping;
import com.opensource.restful.shared.dto.ContactDTO;
import com.opensource.restful.shared.service.IContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController
{
    @Autowired
    private IContactService service;

    public IContactService getService()
    {
        return service;
    }

    public void setService(IContactService service)
    {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    ArrayList<ContactDTO> getAllContacts()
    {
        ArrayList<ContactEntity> contactEntityList = (ArrayList) service.getAllContacts();

        ArrayList<ContactDTO> contactDTOList = new ArrayList<ContactDTO>();
        for (ContactEntity contactEntity : contactEntityList)
        {
            ContactDTO contactDto = new ContactDTO();
            contactDto.setContactId(contactEntity.getContactId());
            contactDto.setFirstName(contactEntity.getFirstName());
            contactDto.setLastName(contactEntity.getLastName());
            contactDTOList.add(contactDto);
        }

        return contactDTOList;
    }

    @RequestMapping(value = "/contactId/{contactId}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    ContactDTO getContactById(@PathVariable("contactId") long contactId)
    {
        ContactEntity contactEntity = service.getContactById(contactId);
        ContactDTO contactDto = Mapping.mappingContact(contactEntity);
        System.out.println("ContactController: retrieveContact: contactDto=" + contactDto);
        return contactDto;
    }

    @RequestMapping(value = "/userId/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    ArrayList<ContactDTO> getContactsByUserId(@PathVariable("userId") long userId)
    {
        ArrayList<ContactEntity> contactEntityList = (ArrayList) service.getContactsByUserId(userId);

        ArrayList<ContactDTO> contactDTOList = new ArrayList<ContactDTO>();
        for (ContactEntity contactEntity : contactEntityList)
        {
            ContactDTO contactDto = Mapping.mappingContact(contactEntity);
            System.out.println("ContactController: getContactsByUserId: contactDto=" + contactDto);
            contactDTOList.add(contactDto);
        }

        return contactDTOList;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json",
        headers = "content-type=application/json")
    public @ResponseBody
    ContactDTO createContact(@RequestBody ContactDTO contact)
    {
        System.out.println("ContactController: createContact: contact=" + contact);
        ContactEntity contactEntity = service.add(contact);
        ContactDTO contactDto = Mapping.mappingContact(contactEntity);
        System.out.println("ContactController: createContact: contactDto=" + contactDto);
        return contactDto;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json",
        headers = "content-type=application/json")
    public @ResponseBody
    ContactDTO updateContact(@RequestBody ContactDTO contact)
    {
        System.out.println("ContactController: START: updateContact: contact=" + contact);
        ContactEntity contactEntity = service.update(contact);
        ContactDTO contactDto = Mapping.mappingContact(contactEntity);
        System.out.println("ContactController: FINISH: updateContact: contactDto=" + contactDto);
        return contactDto;
    }

    @RequestMapping(value = "/delete/{contactId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public @ResponseBody
    ContactDTO deleteContact(@PathVariable("contactId") long contactId)
    {
        System.out.println("ContactController: START: deleteContact: contactId=" + contactId);
        service.remove(contactId);
        System.out.println("ContactController: FINISH: deleteContact:");
        return new ContactDTO(contactId);
    }

}
