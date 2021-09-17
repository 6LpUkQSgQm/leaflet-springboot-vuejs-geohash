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

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/hello")
    public Response sayHello() {
        log.info("GET called on /hello resource");
        return new Response(HELLO_TEXT);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User addNewUser (@RequestBody UserDTO request) {
        User user = new User(request.getFirstName(), request.getLastName());
        User saveUser = userRepository.save(user);

        log.info(saveUser.toString() + " successfully saved into DB");

        return saveUser;
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") long id) {
        log.info("Reading user with id " + id + " from database.");
        return userRepository.findById(id).get();
    }

    @GetMapping("/mapping/user")
    public ResponseUser getUser() {
        log.info("controller get User ");
        return userService.getUser(1L);
    }

    //inner class
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    class Response {
        private String data;
    }
}
