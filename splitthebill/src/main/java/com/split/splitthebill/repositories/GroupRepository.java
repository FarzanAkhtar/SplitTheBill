package com.split.splitthebill.repositories;

import com.split.splitthebill.entities.Group;
import com.split.splitthebill.entities.GroupUserMapping;
import com.split.splitthebill.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {
    boolean existsByGroupUuid(String groupUuid);
}

