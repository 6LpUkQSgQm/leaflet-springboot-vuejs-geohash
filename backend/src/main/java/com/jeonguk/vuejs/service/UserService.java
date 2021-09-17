package com.jeonguk.vuejs.service;

import com.jeonguk.vuejs.dto.ResponseUser;

public interface UserService {
    ResponseUser getUser(Long id);
}
