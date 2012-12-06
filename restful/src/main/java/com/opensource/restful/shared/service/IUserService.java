package com.opensource.restful.shared.service;

import java.util.List;

import com.opensource.restful.domain.UserEntity;

public interface IUserService
{
    public List<UserEntity> getAllUsers();

    UserEntity getUserById(long userId);
}
