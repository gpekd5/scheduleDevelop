package com.example.scheduledevelopproject.global.encoder;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * 비밀번호 암호화 처리 컴포넌트
 */
@Component
public class PasswordEncoder {
    /**
     * 비밀번호 암호화
     *
     * @param rawPassword 원본 비밀번호
     * @return 암호화 비밀번호
     */
    public String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    /**
     * 비밀번호 일치 여부 검증
     *
     * @param rawPassword 원본 비밀번호
     * @param encodedPassword 암호화 비밀번호
     * @return 비밀번호 일치 여부
     */
    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }
}
