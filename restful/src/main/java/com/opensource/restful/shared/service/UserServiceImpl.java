package com.opensource.restful.shared.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensource.restful.domain.UserEntity;
import com.opensource.restful.server.dao.UserDao;

public class UserServiceImpl implements IUserService
{
    @Autowired
    private UserDao userDao;

    public UserDao getUserDao()
    {
        return userDao;
    }

    public void setUserDao(UserDao userDao)
    {
        this.userDao = userDao;
    }

    @Override
    public List<UserEntity> getAllUsers()
    {
        List<UserEntity> userList = userDao.getAllUserEntitys();
        return userList;
    }

    @Override
    public UserEntity getUserById(long userId)
    {
        UserEntity userEntity = userDao.getUserEntity(userId);
        return userEntity;
    }
}
