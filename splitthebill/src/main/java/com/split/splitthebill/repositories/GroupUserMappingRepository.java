package com.split.splitthebill.repositories;

import com.split.splitthebill.entities.GroupUserMapping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupUserMappingRepository extends CrudRepository<GroupUserMapping, Long> {
}
