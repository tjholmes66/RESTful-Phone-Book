package com.opensource.restful.server.service;

import com.opensource.restful.domain.UserEntity;

public interface ILoginService
{
    UserEntity login(String username, String password);

    UserEntity loginByEmail(String email);

    UserEntity loginByUsername(String username);
}
