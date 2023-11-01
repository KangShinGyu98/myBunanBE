package com.cuttingEdge.bunan.service;

import com.cuttingEdge.bunan.config.jwt.JwtUtil;
import com.cuttingEdge.bunan.entity.User;
import com.cuttingEdge.bunan.exception.AppException;
import com.cuttingEdge.bunan.exception.ErrorCode;
import com.cuttingEdge.bunan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public String join(String nickname, String email, String password) { //닉네임 로직 분리해야할듯?

        // username, email 중복 check
        userRepository.existsUserByEmail(email)
                .ifPresent(user -> {throw new AppException(ErrorCode.USERNAME_DUPLICATED, email + "은(는) 이미 존재합니다.");
                });
        userRepository.findByNickname(nickname)
                .ifPresent(user -> {throw new AppException(ErrorCode.USERNAME_DUPLICATED, nickname + "은(는) 이미 존재합니다.");
                });
        // 저장

        User user = User.builder()
                .nickname(nickname)
                .email(email)
                .password(encoder.encode(password))
                .build();
        userRepository.save(user);

        return "SUCCESS";
    }

    public String login(String email, String password) {
        //username 없음
        User selectedUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, email + "이 없습니다."));

        //password 틀림
        if (!encoder.matches(password, selectedUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "잘못된 패스워드입니다.");
        }

        String token = jwtUtil.createToken(selectedUser.getEmail());
        // 앞에서 Exception 안났으면 토큰 발행
        return token;
    }

    @Transactional
    public void resetPassword(String email, String password, String newPassword) {
        //username 확인
        User selectedUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, email + "이 없습니다."));

        //password 확인
        if (!encoder.matches(password, selectedUser.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD, "잘못된 패스워드입니다.");
        }

        selectedUser.updatePassword(encoder.encode(newPassword));
    }

    public void resetDB(){
        userRepository.deleteAll();
    }


}
