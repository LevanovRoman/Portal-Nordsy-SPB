package com.myapp.portalnordsyspb.auth.repositories;

import com.myapp.portalnordsyspb.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String username);

    boolean existsByEmail(String email);
}
