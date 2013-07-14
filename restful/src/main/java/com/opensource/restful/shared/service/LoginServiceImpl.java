package com.opensource.restful.shared.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensource.restful.domain.UserEntity;
import com.opensource.restful.server.dao.UserDao;

public class LoginServiceImpl implements ILoginService
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
    public UserEntity login(String username, String password)
    {
        List<UserEntity> userEntitys = userDao.getUserEntityByLogin(username, password);
        UserEntity userEntity = null;
        if (userEntitys != null && userEntitys.size() > 0)
        {
            userEntity = userEntitys.get(0);
        }
        return userEntity;
    }

    @Override
    public UserEntity loginByEmail(String email)
    {
        List<UserEntity> userEntitys = userDao.getUserEntityByEmail(email);
        UserEntity userEntity = null;
        if (userEntitys != null && userEntitys.size() > 0)
        {
            userEntity = userEntitys.get(0);
        }
        return userEntity;
    }

    @Override
    public UserEntity loginByUsername(String username)
    {
        List<UserEntity> userEntitys = userDao.getUserEntityByUsername(username);
        UserEntity userEntity = null;
        if (userEntitys != null && userEntitys.size() > 0)
        {
            userEntity = userEntitys.get(0);
        }
        return userEntity;
    }

}
