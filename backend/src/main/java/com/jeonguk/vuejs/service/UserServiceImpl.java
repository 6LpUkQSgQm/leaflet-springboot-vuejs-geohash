package com.jeonguk.vuejs.service;

import com.jeonguk.vuejs.dto.ResponseUser;
import com.jeonguk.vuejs.entity.User;
import com.jeonguk.vuejs.mapper.UserMapper;
import com.jeonguk.vuejs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseUser getUser(Long id) {
        Optional<User> user = userRepository.findById(1L);
        if (user.isPresent()) {
            log.info("user service get User MapStruct mapped");
            return UserMapper.INSTANCE.from(user.get());
        }
        return new ResponseUser();
    }
}
