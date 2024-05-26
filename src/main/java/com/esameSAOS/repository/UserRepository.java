package com.esameSAOS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.esameSAOS.dto.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
