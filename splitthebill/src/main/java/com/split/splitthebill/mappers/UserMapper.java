package com.split.splitthebill.mappers;

import com.split.splitthebill.Utils;
import com.split.splitthebill.dtos.UserDetailedDto;
import com.split.splitthebill.entities.User;
import com.split.splitthebill.requests.CreateUserRequest;
import com.split.splitthebill.dtos.UserDto;

import java.security.NoSuchAlgorithmException;

public abstract class UserMapper {
    public static UserDetailedDto mapFrom(User user) {
        return UserDetailedDto.builder()
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .uuid(user.getUuid())
                .build();
    }

    public static User mapTo(UserDetailedDto userDetailedDto, String salt, String uuid) throws NoSuchAlgorithmException {
        String encryptedPassword = Utils.createSha256Hash(userDetailedDto.getPassword() + salt);
        return User.builder()
                .name(userDetailedDto.getName())
                .phoneNumber(userDetailedDto.getPhoneNumber())
                .salt(salt)
                .uuid(uuid)
                .encryptedPassword(encryptedPassword)
                .build();
    }

    public static User mapOverTo(UserDetailedDto userDetailedDto, User user) throws NoSuchAlgorithmException {
        User updatedUser = mapTo(userDetailedDto, user.getSalt(), user.getUuid());
        updatedUser.setId(user.getId());
        return updatedUser;
    }

    public static UserDto mapFromUserUuid(String userUuid) {
        return UserDto.builder()
                .uuid(userUuid)
                .build();
    }

    public static UserDetailedDto mapFromReq(CreateUserRequest createUserRequest) {
        return UserDetailedDto.builder()
                .name(createUserRequest.getName())
                .phoneNumber(createUserRequest.getPhoneNumber())
                .password(createUserRequest.getPassword())
                .build();
    }

    public static UserDto mapToRes(User user) {
        return UserDto.builder()
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .uuid(user.getUuid())
                .build();
    }
}
