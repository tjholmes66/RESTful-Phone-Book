package com.opensource.restful.server.dao;

import java.util.List;

import com.opensource.restful.domain.UserEntity;

public interface UserDao
{
    public UserEntity createUserEntity(UserEntity user);

    public UserEntity updateUserEntity(UserEntity user);

    public void deleteUserEntity(long userId);

    public void deleteUserEntity(UserEntity user);

    public List<UserEntity> getAllUserEntitys();

    // Retrieve
    public UserEntity getUserEntity(long id);

    public List<UserEntity> getUsersEntity(UserEntity exampleEntity);

    public List<UserEntity> getUserEntityByLogin(String username, String password);

}
