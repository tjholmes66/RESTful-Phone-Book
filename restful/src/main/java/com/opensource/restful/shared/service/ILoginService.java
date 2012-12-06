package com.opensource.restful.shared.service;

import com.opensource.restful.domain.UserEntity;

public interface ILoginService
{

    UserEntity login(String username, String password);

}
