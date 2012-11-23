package com.opensource.restful.shared.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opensource.restful.domain.ContactEntity;
import com.opensource.restful.shared.dto.ContactDTO;
import com.opensource.restful.shared.service.IContactService;

@Controller
@RequestMapping("/contact")
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
    ArrayList<ContactDTO> getAllPersons()
    {
        ArrayList<ContactEntity> contactEntityList = (ArrayList) service.getAllContacts();

        ArrayList<ContactDTO> contactDTOList = new ArrayList<ContactDTO>();
        for (ContactEntity contactEntity : contactEntityList)
        {
            ContactDTO contactDto = new ContactDTO();
            contactDto.setId(contactEntity.getId());
            contactDto.setFirstName(contactEntity.getFirstName());
            contactDto.setLastName(contactEntity.getLastName());
            contactDTOList.add(contactDto);
        }

        return contactDTOList;
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public @ResponseBody
    ContactDTO updateContact(@ModelAttribute ContactDTO person)
    {
        // The person object will only have the fields that are
        // being updated populated + the primary key.
        // The method should return a full object with the same primary key.
        return null;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    ContactDTO createContact()
    {
        // This should create a new person with a new primary key
        return null;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ContactDTO deleteContact(@PathVariable("id") Integer id)
    {
        // This should delete a person and return the deleted person object
        return null;
    }
}
