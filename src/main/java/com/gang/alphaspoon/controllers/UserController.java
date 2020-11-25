package com.gang.alphaspoon.controllers;

import com.gang.alphaspoon.converters.UserConverter;
import com.gang.alphaspoon.dtos.LoginRequestDTO;
import com.gang.alphaspoon.dtos.LoginResponseDTO;
import com.gang.alphaspoon.dtos.SignupRequestDTO;
import com.gang.alphaspoon.dtos.UserDTO;
import com.gang.alphaspoon.entity.User;
import com.gang.alphaspoon.services.UserService;
import com.gang.alphaspoon.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;


    @PostMapping("/signup")
    public ResponseEntity<WrapperResponse<UserDTO>> signup(@RequestBody SignupRequestDTO request){
        User user=userService.createUser(userConverter.signup(request));
        return new WrapperResponse<>(true,"success",userConverter.fromEntity(user))
                .createResponse();
    }

    @PostMapping("/login")
    public ResponseEntity<WrapperResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request){
        LoginResponseDTO response=userService.login(request);
        return new WrapperResponse<>(true,"success",response)
                .createResponse();
    }

}
