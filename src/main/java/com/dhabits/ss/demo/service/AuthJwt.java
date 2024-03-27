package com.dhabits.ss.demo.service;

import com.dhabits.ss.demo.domain.model.web.AccessToken;
import com.dhabits.ss.demo.domain.model.web.LoginRq;

public interface AuthJwt {
    AccessToken authenticate(LoginRq loginRq);
}
