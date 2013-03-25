package com.opensource.restful.shared.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensource.restful.domain.PositionEntity;
import com.opensource.restful.domain.UserEntity;
import com.opensource.restful.server.dao.UserDao;
import com.opensource.restful.shared.dto.UserDTO;

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

    @Override
    public UserEntity add(UserDTO userDto)
    {
        UserEntity newUser = new UserEntity();
        newUser.setUserId(userDto.getUserId());
        newUser.setActive(userDto.isActive());
        newUser.setEmail(userDto.getEmail());
        newUser.setFirstname(userDto.getFirstName());
        newUser.setLastname(userDto.getLastName());
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());
        newUser.setBirthdate(userDto.getBirthdate());
        // ***************************************************
        newUser.setSecurityQuestion1(userDto.getSecurityQuestion1());
        newUser.setSecurityAnswer1(userDto.getSecurityAnswer1());
        newUser.setSecurityQuestion2(userDto.getSecurityQuestion2());
        newUser.setSecurityAnswer2(userDto.getSecurityAnswer2());
        // ***************************************************
        PositionEntity positionEntity = new PositionEntity();
        positionEntity.setId(userDto.getPosition().getId());
        newUser.setPosition(positionEntity);
        // ***************************************************
        UserEntity userEntity = userDao.createUserEntity(newUser);
        return userEntity;
    }

    @Override
    public void remove(UserDTO userDto)
    {
        userDao.deleteUserEntity(userDto.getUserId());
    }

    @Override
    public void remove(long userId)
    {
        userDao.deleteUserEntity(userId);
    }

    @Override
    public UserEntity update(UserDTO userDto)
    {
        UserEntity newUser = userDao.getUserEntity(userDto.getUserId());
        newUser.setUserId(userDto.getUserId());
        newUser.setActive(userDto.isActive());
        if (userDto.getEmail() != null)
        {
            newUser.setEmail(userDto.getEmail());
        }
        if (userDto.getFirstName() != null)
        {
            newUser.setFirstname(userDto.getFirstName());
        }
        if (userDto.getLastName() != null)
        {
            newUser.setLastname(userDto.getLastName());
        }
        if (userDto.getUsername() != null)
        {
            newUser.setUsername(userDto.getUsername());
        }
        if (userDto.getPassword() != null)
        {
            newUser.setPassword(userDto.getPassword());
        }
        if (userDto.getBirthdate() != null)
        {
            newUser.setBirthdate(userDto.getBirthdate());
        }
        // ***************************************************
        if (userDto.getSecurityQuestion1() != null)
        {
            newUser.setSecurityQuestion1(userDto.getSecurityQuestion1());
        }
        if (userDto.getSecurityAnswer1() != null)
        {
            newUser.setSecurityAnswer1(userDto.getSecurityAnswer1());
        }
        // ***************************************************
        if (userDto.getSecurityQuestion2() != null)
        {
            newUser.setSecurityQuestion2(userDto.getSecurityQuestion2());
        }
        if (userDto.getSecurityAnswer2() != null)
        {
            newUser.setSecurityAnswer2(userDto.getSecurityAnswer2());
        }
        // ***************************************************
        if (userDto.getPosition() != null)
        {
            PositionEntity positionEntity = new PositionEntity();
            positionEntity.setId(userDto.getPosition().getId());
            newUser.setPosition(positionEntity);
        }
        // ***************************************************
        UserEntity updatedUserEntity = userDao.updateUserEntity(newUser);
        return updatedUserEntity;
    }
}
