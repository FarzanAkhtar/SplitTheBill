package com.split.splitthebill.service;

import com.split.splitthebill.Utils;
import com.split.splitthebill.dtos.UserDetailedDto;
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

    public UserDto createUser(UserDetailedDto userDetailedDto) {
        String salt = Utils.generateRandomSalt();
        String uuid;
        do {
            uuid = Utils.generateRandomUuid();
        } while (userRepository.existsByUuid(uuid));
        try {
            User user = UserMapper.mapTo(userDetailedDto, salt, uuid);
            User savedUser = userRepository.save(user);
            return UserMapper.mapToRes(savedUser);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage(), e.getCause());
        }
    }

    public UserDto updateUser(UserDetailedDto userDetailedDto) {
        try {
            User savedUser = userRepository.findByUuid(userDetailedDto.getUuid()).orElseThrow();
            User updatedUser = userRepository.save(UserMapper.mapOverTo(userDetailedDto, savedUser));
            return UserMapper.mapToRes(updatedUser);
        } catch (NoSuchAlgorithmException e) {
            throw new InternalServerException(e.getMessage(), e.getCause());
        }
    }

    public void deleteUser(UserDetailedDto userDetailedDto) {
        try {
            User savedUser = userRepository.findByUuid(userDetailedDto.getUuid()).orElseThrow();
            String inputPasswordHash = Utils.createSha256Hash(userDetailedDto.getPassword() + savedUser.getSalt());
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
        return UserMapper.mapToRes(savedUser);
    }

    public UserDto signIn(UserDetailedDto userDetailedDto) {
        try {
            User savedUser = userRepository.findByPhoneNumber(userDetailedDto.getPhoneNumber()).orElseThrow();
            String inputPasswordHash = Utils.createSha256Hash(userDetailedDto.getPassword() + savedUser.getSalt());
            if (inputPasswordHash.equals(savedUser.getEncryptedPassword())) {
                return UserMapper.mapToRes(savedUser);
            } else {
                throw new UnauthorisedAccessException("Wrong Password");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new InternalServerException(e.getMessage(), e.getCause());
        }
    }
}
