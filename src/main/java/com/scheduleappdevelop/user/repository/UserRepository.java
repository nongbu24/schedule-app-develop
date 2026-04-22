package com.scheduleappdevelop.user.repository;

import com.scheduleappdevelop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // user에서 email을 찾는 메서드, 예외 처리를 위해 Optional 사용
    Optional<User> findByEmail(String email);
}
