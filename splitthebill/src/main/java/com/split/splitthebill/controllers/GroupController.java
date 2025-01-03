package com.split.splitthebill.controllers;

import com.split.splitthebill.dtos.GroupDto;
import com.split.splitthebill.dtos.SuccessResponse;
import com.split.splitthebill.dtos.UserGroupsDto;
import com.split.splitthebill.mappers.GroupMapper;
import com.split.splitthebill.requests.CreateGroupRequest;
import com.split.splitthebill.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GroupController {
    @Autowired
    GroupService groupService;

    @PostMapping("/create_group")
    public ResponseEntity<?> createGroup(@RequestBody CreateGroupRequest createGroupRequest) {
        GroupDto groupDto = GroupMapper.mapFromReq(createGroupRequest);
        groupService.createGroup(groupDto);
        return new ResponseEntity<>(new SuccessResponse("Group Created Successfully", HttpStatus.OK.value()), HttpStatus.OK);
    }

    @GetMapping("/get_user_groups")
    public ResponseEntity<UserGroupsDto> getUserGroups(@RequestParam("user_uuid") String userUuid) {
        UserGroupsDto userGroupsDto = groupService.getUserGroups(userUuid);
        return new ResponseEntity<>(userGroupsDto, HttpStatus.OK);
    }

    @GetMapping("/get_group")
    public ResponseEntity<GroupDto> getGroup(@RequestParam("group_uuid") String groupUuid) {
        GroupDto groupDto = groupService.getGroup(groupUuid);
        return new ResponseEntity<>(groupDto, HttpStatus.OK);
    }
}
