package com.split.splitthebill.mappers;

import com.split.splitthebill.dtos.GroupDto;
import com.split.splitthebill.dtos.UserDto;
import com.split.splitthebill.entities.Group;
import com.split.splitthebill.requests.CreateGroupRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class GroupMapper {
    public static GroupDto mapFromReq(CreateGroupRequest createGroupRequest) {
        List<UserDto> members = createGroupRequest.getMembers().stream()
                .map(UserMapper::mapFromUserUuid)
                .toList();
        return GroupDto.builder()
                .name(createGroupRequest.getName())
                .members(members)
                .build();
    }

    public static GroupDto mapFrom(Group group, List<UserDto> members) {
        return GroupDto.builder()
                .name(group.getName())
                .members(members)
                .build();
    }

    public static Group mapTo(GroupDto groupDto, String uuid) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        return Group.builder()
                .name(groupDto.getName())
                .groupUuid(uuid)
                .createdAt(formattedDateTime)
                .build();
    }
}
