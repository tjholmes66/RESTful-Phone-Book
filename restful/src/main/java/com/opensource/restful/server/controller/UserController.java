package com.opensource.restful.server.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opensource.restful.domain.UserEntity;
import com.opensource.restful.server.service.IUserService;
import com.opensource.restful.shared.Mapping;
import com.opensource.restful.shared.dto.UserDTO;

@Controller
@RequestMapping("/users")
public class UserController
{
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private IUserService service;

    @RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    ArrayList<UserDTO> getAllUsers()
    {
        ArrayList<UserEntity> userEntityList = (ArrayList) service.getAllUsers();

        ArrayList<UserDTO> userDTOList = new ArrayList<UserDTO>();
        for (UserEntity userEntity : userEntityList)
        {
            UserDTO userDto = new UserDTO();
            userDto.setUserId(userEntity.getUserId());
            userDto.setUserFirstName(userEntity.getFirstname());
            userDto.setUserLastName(userEntity.getLastname());
            userDTOList.add(userDto);
        }
        return userDTOList;
    }

    @RequestMapping(value = "/userId/{userId}", method = RequestMethod.GET, headers = "Accept=application/json",
        produces = "application/json")
    public @ResponseBody
    UserEntity getUserById(@PathVariable("userId") long userId)
    {
        System.out.println("UserController: getUserById: userId=" + userId);
        UserEntity userEntity = service.getUserById(userId);
        System.out.println("UserController: getUserById: userEntity=" + userEntity);
        return userEntity;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json",
        headers = "content-type=application/json")
    public @ResponseBody
    UserDTO createUser(@RequestBody UserDTO user)
    {
        System.out.println("UserController: createUser: user=" + user);
        UserEntity userEntity = service.add(user);
        UserDTO userDto = Mapping.mappingUser(userEntity);
        System.out.println("UserController: createUser: userDto=" + userDto);
        return userDto;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json",
        headers = "content-type=application/json")
    public @ResponseBody
    UserDTO updateUser(@RequestBody UserDTO user)
    {
        System.out.println("UserController: START: updateUser: user=" + user);
        UserEntity userEntity = service.update(user);
        UserDTO userDto = Mapping.mappingUser(userEntity);
        System.out.println("UserController: FINISH: updateUser: userDto=" + userDto);
        return userDto;
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public @ResponseBody
    void deleteUser(@PathVariable("userId") long userId)
    {
        service.remove(userId);
    }

}