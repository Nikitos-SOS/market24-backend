package com.market.marketplace.repo;

import com.market.marketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id);

    void deleteUserById(Long id);

    Optional<User> findUserByUsername(String username);
}
