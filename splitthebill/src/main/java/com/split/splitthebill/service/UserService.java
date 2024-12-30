package com.split.splitthebill.service;

import com.split.splitthebill.Utils;
import com.split.splitthebill.dtos.UserDto;
import com.split.splitthebill.entities.User;
import com.split.splitthebill.exceptions.InternalServerException;
import com.split.splitthebill.exceptions.UnauthorisedAccessException;
import com.split.splitthebill.mappers.UserMapper;
import com.split.splitthebill.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto userDto) {
        String salt = Utils.generateRandomSalt();
        String uuid;
        do {
            uuid = Utils.generateRandomUuid();
        } while (userRepository.existsByUuid(uuid));
        try {
            User user = UserMapper.mapTo(userDto, salt, uuid);
            User savedUser = userRepository.save(user);
            return UserMapper.mapFrom(savedUser);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage(), e.getCause());
        }
    }

    public UserDto updateUser(UserDto userDto) {
        try {
            User savedUser = userRepository.findByUuid(userDto.getUuid()).orElseThrow();
            User updatedUser = userRepository.save(UserMapper.mapOverTo(userDto, savedUser));
            return UserMapper.mapFrom(updatedUser);
        } catch (NoSuchAlgorithmException e) {
            throw new InternalServerException(e.getMessage(), e.getCause());
        }
    }

    public void deleteUser(UserDto userDto) {
        try {
            User savedUser = userRepository.findByUuid(userDto.getUuid()).orElseThrow();
            String inputPasswordHash = Utils.createSha256Hash(userDto.getPassword() + savedUser.getSalt());
            if (inputPasswordHash.equals(savedUser.getEncryptedPassword())) {
                userRepository.delete(savedUser);
            } else {
                throw new UnauthorisedAccessException("Wrong Password");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new InternalServerException(e.getMessage(), e.getCause());
        }
    }

    public UserDto getUser(String uuid) {
        User savedUser = userRepository.findByUuid(uuid).orElseThrow();
        return UserMapper.mapFrom(savedUser);
    }

    public UserDto signIn(UserDto userDto) {
        try {
            User savedUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber()).orElseThrow();
            String inputPasswordHash = Utils.createSha256Hash(userDto.getPassword() + savedUser.getSalt());
            if (inputPasswordHash.equals(savedUser.getEncryptedPassword())) {
                return UserMapper.mapFrom(savedUser);
            } else {
                throw new UnauthorisedAccessException("Wrong Password");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new InternalServerException(e.getMessage(), e.getCause());
        }
    }
}
