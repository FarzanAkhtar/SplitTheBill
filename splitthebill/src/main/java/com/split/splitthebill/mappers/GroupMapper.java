package com.split.splitthebill.mappers;

import com.split.splitthebill.dtos.GroupDto;
import com.split.splitthebill.entities.Group;
import com.split.splitthebill.requests.CreateGroupRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class GroupMapper {
    public static GroupDto mapFromReq(CreateGroupRequest createGroupRequest) {
        return GroupDto.builder()
                .name(createGroupRequest.getName())
                .members(createGroupRequest.getMembers())
                .build();
    }

    public static GroupDto mapFrom(Group group) {
        return GroupDto.builder()
                .name(group.getName())
                .build();
    }

    public static Group mapFrom(GroupDto groupDto, String uuid) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        return Group.builder()
                .name(groupDto.getName())
                .groupUuid(uuid)
                .createdAt(formattedDateTime)
                .build();
    }
}
