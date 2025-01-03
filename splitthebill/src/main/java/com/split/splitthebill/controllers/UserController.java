package com.split.splitthebill.controllers;

import com.split.splitthebill.dtos.SuccessResponse;
import com.split.splitthebill.dtos.UserDetailedDto;
import com.split.splitthebill.mappers.UserMapper;
import com.split.splitthebill.requests.CreateUserRequest;
import com.split.splitthebill.dtos.UserDto;
import com.split.splitthebill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/create_user")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest) {
        UserDetailedDto userDetailedDto = UserMapper.mapFromReq(createUserRequest);
        UserDto userDtoResponse = userService.createUser(userDetailedDto);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
    }

    @GetMapping("/get_user")
    public ResponseEntity<UserDto> getUser(@RequestParam("uuid") String uuid) {
        UserDto userDtoResponse = userService.getUser(uuid);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
    }

    @PutMapping("/update_user")
    public ResponseEntity<UserDto> updateUser(@RequestBody CreateUserRequest createUserRequest) {
        UserDetailedDto userDetailedDto = UserMapper.mapFromReq(createUserRequest);
        UserDto userDtoResponse = userService.updateUser(userDetailedDto);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete_user")
    public ResponseEntity<SuccessResponse> deleteUser(@RequestBody UserDetailedDto userDetailedDto) {
        userService.deleteUser(userDetailedDto);
        return new ResponseEntity<>(new SuccessResponse("Deleted Successfully", HttpStatus.OK.value()), HttpStatus.OK);
    }

    @PostMapping("/sign_in")
    public ResponseEntity<UserDto> signIn(@RequestBody UserDetailedDto userDetailedDto) {
        UserDto userDtoResponse = userService.signIn(userDetailedDto);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
    }
}
