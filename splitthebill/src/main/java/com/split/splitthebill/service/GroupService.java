package com.split.splitthebill.service;

import com.split.splitthebill.Utils;
import com.split.splitthebill.dtos.GroupBaseDto;
import com.split.splitthebill.dtos.GroupDto;
import com.split.splitthebill.dtos.UserDto;
import com.split.splitthebill.dtos.UserGroupsDto;
import com.split.splitthebill.entities.*;
import com.split.splitthebill.mappers.GroupMapper;
import com.split.splitthebill.mappers.UserMapper;
import com.split.splitthebill.repositories.GroupRepository;
import com.split.splitthebill.repositories.GroupUserMappingRepository;
import com.split.splitthebill.repositories.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Log
@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupUserMappingRepository groupUserMappingRepository;

    public void createGroup(GroupDto groupDto) {
        String groupUuid;
        do {
            groupUuid = Utils.generateRandomUuid();
        } while (groupRepository.existsByGroupUuid(groupUuid));
        Group group = GroupMapper.mapTo(groupDto, groupUuid);
        Group savedGroup = groupRepository.save(group);
        List<String> members = groupDto.getMembers().stream().map(UserDto::getUuid).toList();
        for (String member : members) {
            User user = userRepository.findByUuid(member).orElseThrow();
            groupUserMappingRepository.save(
                GroupUserMapping.builder()
                        .id(
                            GroupUserMappingId.builder()
                                .group(savedGroup)
                                .user(user)
                                .build()
                        )
                        .build()
            );
        }
    }

    public UserGroupsDto getUserGroups(String userUuid) {
        User user = userRepository.findByUuid(userUuid).orElseThrow();
        List<GroupBaseDto> groups = groupUserMappingRepository.findAllByIdUser(user)
                .stream()
                .map(mapping -> GroupMapper.mapToGroupBaseDto(mapping.getId().getGroup()))
                .toList();
        return UserGroupsDto.builder().groups(groups).build();
    }

    public GroupDto getGroup(String groupUuid) {
        Group group = groupRepository.findByGroupUuid(groupUuid);
        List<UserDto> members = groupUserMappingRepository.findAllByIdGroup(group)
                .stream()
                .map(mapping -> UserMapper.mapToRes(mapping.getId().getUser()))
                .toList();
        return GroupMapper.mapFrom(group, members);
    }
}
