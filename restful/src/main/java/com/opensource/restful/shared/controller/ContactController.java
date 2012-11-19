package com.opensource.restful.shared.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opensource.restful.shared.dto.ContactDTO;

@Controller
@RequestMapping("/contact")
public class ContactController
{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    List<ContactDTO> getAllContacts()
    {
        // Return a list of ContactDTO objects
        List contactList = null;
        return contactList;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public @ResponseBody
    ContactDTO updateContact(@ModelAttribute ContactDTO person)
    {
        // The person object will only have the fields that are
        // being updated populated + the primary key.
        // The method should return a full object with the same primary key.
        return null;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
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
