package com.marius.vila.authentication.repository;

import com.marius.vila.authentication.model.DbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DbUser, Long> {
    Optional<DbUser> getByEmail(String email);
}
