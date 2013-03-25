package com.opensource.restful.shared.service;

import java.util.List;

import com.opensource.restful.domain.UserEntity;
import com.opensource.restful.shared.dto.UserDTO;

public interface IUserService
{
    public List<UserEntity> getAllUsers();

    UserEntity getUserById(long userId);

    UserEntity add(UserDTO userDto);

    void remove(UserDTO record);

    UserEntity update(UserDTO userDto);

    void remove(long userId);
}
