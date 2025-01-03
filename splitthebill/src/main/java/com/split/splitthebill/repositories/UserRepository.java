package com.split.splitthebill.repositories;

import com.split.splitthebill.entities.GroupUserMapping;
import com.split.splitthebill.entities.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByUuid(String uuid);
    Boolean existsByUuid(String uuid);
    List<User> findAllByUuidIn(Iterable<String> uuids);
}
