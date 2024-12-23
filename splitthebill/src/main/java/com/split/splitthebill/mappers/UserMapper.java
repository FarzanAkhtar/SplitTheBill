package com.split.splitthebill.mappers;

import com.split.splitthebill.Utils;
import com.split.splitthebill.dtos.UserDto;
import com.split.splitthebill.entities.User;
import java.security.NoSuchAlgorithmException;

public abstract class UserMapper {
    public static UserDto mapFrom(User user) {
        return UserDto.builder()
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .uuid(user.getUuid())
                .build();
    }

    public static User mapTo(UserDto userDto, String salt, String uuid) throws NoSuchAlgorithmException {
        String encryptedPassword = Utils.createSha256Hash(userDto.getPassword() + salt);
        return User.builder()
                .name(userDto.getName())
                .phoneNumber(userDto.getPhoneNumber())
                .salt(salt)
                .uuid(uuid)
                .encryptedPassword(encryptedPassword)
                .build();
    }

    public static User mapOverTo(UserDto userDto, User user) throws NoSuchAlgorithmException {
        User updatedUser = mapTo(userDto, user.getSalt(), user.getUuid());
        updatedUser.setId(user.getId());
        return updatedUser;
    }
}
