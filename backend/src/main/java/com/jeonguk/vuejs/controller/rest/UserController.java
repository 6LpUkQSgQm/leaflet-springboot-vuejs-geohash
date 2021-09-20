package com.jeonguk.vuejs.controller.rest;

import com.jeonguk.vuejs.dto.ResponseUser;
import com.jeonguk.vuejs.dto.UserDTO;
import com.jeonguk.vuejs.entity.User;
import com.jeonguk.vuejs.repository.UserRepository;
import com.jeonguk.vuejs.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";

    @GetMapping("/message")
    public Response sayHello() {
        return new Response(HELLO_TEXT);
    }


    //inner class
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    class Response {
        private String data;
    }
}
