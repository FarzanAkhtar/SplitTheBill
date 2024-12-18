package com.split.splitthebill;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @PostMapping("/create_user")
    public Boolean createUser() {
        return Boolean.FALSE;
    }

    @GetMapping("/get_user")
    public String getUser() {
        return "Hello bitches";
    }

    @PutMapping("/update_user")
    public Boolean updateUser() {
        return Boolean.FALSE;
    }

    @DeleteMapping("/delete_user")
    public Boolean deleteUser() {
        return Boolean.FALSE;
    }
}
