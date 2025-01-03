package com.split.splitthebill.repositories;

import com.split.splitthebill.entities.Group;
import com.split.splitthebill.entities.GroupUserMapping;
import com.split.splitthebill.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupUserMappingRepository extends CrudRepository<GroupUserMapping, Long> {
    List<GroupUserMapping> findAllByIdUser(User user);
    List<GroupUserMapping> findAllByIdGroup(Group group);
}
