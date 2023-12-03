package com.personalproject.personalproject_1.service;

import com.personalproject.personalproject_1.dto.SignupRequestDto;
import com.personalproject.personalproject_1.entity.User;
import com.personalproject.personalproject_1.exception.UserDuplicateException;
import com.personalproject.personalproject_1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final MessageSource messageSource;

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new UserDuplicateException(messageSource.getMessage(
                    "duplicate.user.name",
                    null,
                    "Duplicate User name",
                    Locale.getDefault()
            ));
        }
        // 사용자 등록
        User user = new User(username, password);
        userRepository.save(user);
    }
}
