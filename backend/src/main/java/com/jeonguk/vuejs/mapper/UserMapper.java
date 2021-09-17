package com.jeonguk.vuejs.mapper;

import com.jeonguk.vuejs.dto.ResponseUser;
import com.jeonguk.vuejs.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    ResponseUser from(User user);
}
