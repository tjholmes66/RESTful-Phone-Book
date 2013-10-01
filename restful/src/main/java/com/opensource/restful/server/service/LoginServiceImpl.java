package com.opensource.restful.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensource.restful.domain.UserEntity;
import com.opensource.restful.server.dao.UserDao;

@Transactional
@Service("loginService")
public class LoginServiceImpl implements ILoginService
{
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
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
