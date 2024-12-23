package com.split.splitthebill.controllers;

import com.split.splitthebill.dtos.SuccessResponse;
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
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto userDtoResponse = userService.createUser(userDto);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
    }

    @GetMapping("/get_user")
    public ResponseEntity<UserDto> getUser(@RequestParam("uuid") String uuid) {
        UserDto userDtoResponse = userService.getUser(uuid);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
    }

    @PutMapping("/update_user")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        UserDto userDtoResponse = userService.updateUser(userDto);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete_user")
    public ResponseEntity<SuccessResponse> deleteUser(@RequestBody UserDto userDto) {
        userService.deleteUser(userDto);
        return new ResponseEntity<>(new SuccessResponse("Deleted Successfully", HttpStatus.OK.value()), HttpStatus.OK);
    }

    @PostMapping("/sign_in")
    public ResponseEntity<UserDto> signIn(@RequestBody UserDto userDto) {
        UserDto userDtoResponse = userService.signIn(userDto);
        return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
    }
}
