package com.split.splitthebill.service;

import com.split.splitthebill.Utils;
import com.split.splitthebill.dtos.GroupDto;
import com.split.splitthebill.entities.Group;
import com.split.splitthebill.entities.GroupUserMapping;
import com.split.splitthebill.entities.GroupUserMappingId;
import com.split.splitthebill.entities.User;
import com.split.splitthebill.mappers.GroupMapper;
import com.split.splitthebill.repositories.GroupRepository;
import com.split.splitthebill.repositories.GroupUserMappingRepository;
import com.split.splitthebill.repositories.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
        Group group = GroupMapper.mapFrom(groupDto, groupUuid);
        Group savedGroup = groupRepository.save(group);
        List<String> members = groupDto.getMembers();
        for (String member : members) {
            User user = userRepository.findByUuid(member).orElseThrow();
            GroupUserMapping groupUserMapping = groupUserMappingRepository.save(
                GroupUserMapping.builder()
                        .id(
                            GroupUserMappingId.builder()
                                .groupId(savedGroup)
                                .userId(user)
                                .build()
                        )
                        .build()
            );
        }
    }
}
