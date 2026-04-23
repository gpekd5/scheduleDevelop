package com.example.scheduledevelopproject.user.repository;

import com.example.scheduledevelopproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 사용자 데이터 접근 리포지토리
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 이메일 기준 사용자 조회
     *
     * @param email 이메일
     * @return 사용자 조회 결과
     */
    Optional<User> findByEmail(String email);

    /**
     * 이메일 중복 여부 확인
     *
     * @param email 이메일
     * @return 이메일 존재 여부
     */
    boolean existsByEmail(String email);

}
