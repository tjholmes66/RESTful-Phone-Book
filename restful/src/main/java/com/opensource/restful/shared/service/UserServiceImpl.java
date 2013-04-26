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
        newUser.setActive(userDto.isUserActive());
        newUser.setEmail(userDto.getUserEmail());
        newUser.setFirstname(userDto.getUserFirstName());
        newUser.setLastname(userDto.getUserLastName());
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userDto.getPassword());
        newUser.setBirthdate(userDto.getUserBirthDate());
        // ***************************************************
        newUser.setSecurityQuestion1(userDto.getUserSecurityQuestion1());
        newUser.setSecurityAnswer1(userDto.getUserSecurityAnswer1());
        newUser.setSecurityQuestion2(userDto.getUserSecurityQuestion2());
        newUser.setSecurityAnswer2(userDto.getUserSecurityAnswer2());
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
        newUser.setActive(userDto.isUserActive());
        if (userDto.getUserEmail() != null)
        {
            newUser.setEmail(userDto.getUserEmail());
        }
        if (userDto.getUserFirstName() != null)
        {
            newUser.setFirstname(userDto.getUserFirstName());
        }
        if (userDto.getUserLastName() != null)
        {
            newUser.setLastname(userDto.getUserLastName());
        }
        if (userDto.getUsername() != null)
        {
            newUser.setUsername(userDto.getUsername());
        }
        if (userDto.getPassword() != null)
        {
            newUser.setPassword(userDto.getPassword());
        }
        if (userDto.getUserBirthDate() != null)
        {
            newUser.setBirthdate(userDto.getUserBirthDate());
        }
        // ***************************************************
        if (userDto.getUserSecurityQuestion1() != null)
        {
            newUser.setSecurityQuestion1(userDto.getUserSecurityQuestion1());
        }
        if (userDto.getUserSecurityAnswer1() != null)
        {
            newUser.setSecurityAnswer1(userDto.getUserSecurityAnswer1());
        }
        // ***************************************************
        if (userDto.getUserSecurityQuestion2() != null)
        {
            newUser.setSecurityQuestion2(userDto.getUserSecurityQuestion2());
        }
        if (userDto.getUserSecurityAnswer2() != null)
        {
            newUser.setSecurityAnswer2(userDto.getUserSecurityAnswer2());
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
