package com.openclassroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.openclassroom.model.DBUser;
import org.springframework.stereotype.Repository;

@Repository
public interface DBUserRepository extends JpaRepository<DBUser, Integer> {
    public DBUser findByUsername(String username);
}