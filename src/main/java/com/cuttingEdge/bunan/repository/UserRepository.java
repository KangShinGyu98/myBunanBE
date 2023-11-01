package com.cuttingEdge.bunan.repository;

import com.cuttingEdge.bunan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    Optional<User> existsUserByNickname(String nickname);
    Optional<User> existsUserByEmail(String email);

}
