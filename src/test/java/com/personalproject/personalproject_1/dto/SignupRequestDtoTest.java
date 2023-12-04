package com.personalproject.personalproject_1.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


class SignupRequestDtoTest {

    @DisplayName("회원 가입 RequestDto 테스트")
    @Nested
    class createRequestDto{
        @DisplayName("SignUpRequestDto 성공")
        @Test
        void signUpRequestDtoSuccess(){
            // given
            SignupRequestDto signupRequestDto = new SignupRequestDto();
            signupRequestDto.setUsername("test1234");
            signupRequestDto.setPassword("testtest1234");

            // when
            Set<ConstraintViolation<SignupRequestDto>> violations = validate(signupRequestDto);

            // then
            assertThat(violations).isEmpty();
        }

        @DisplayName("SignUpRequestDto Username으로 인한 실패")
        @Test
        void signUpRequestDtoFail_username() {
            // given
            SignupRequestDto signupRequestDto = new SignupRequestDto();
            signupRequestDto.setUsername("TestUserTestUserTestUserTestUser1234");
            signupRequestDto.setPassword("test123456");

            // when
            Set<ConstraintViolation<SignupRequestDto>> violations = validate(signupRequestDto);

            // then
            assertThat(violations).hasSize(1);
            assertThat(violations).extracting("message").contains("\"^[a-z0-9]{4,10}$\"와 일치해야 합니다");

        }

        @DisplayName("SignUpRequestDto password으로 인한 실패")
        @Test
        void signUpRequestDtoFail_password() {
            // given
            SignupRequestDto signupRequestDto = new SignupRequestDto();
            signupRequestDto.setUsername("test1234");
            signupRequestDto.setPassword("testtestteQAASDDSWRDSWRSFsTTTTTTTTtest123456");

            // when
            Set<ConstraintViolation<SignupRequestDto>> violations = validate(signupRequestDto);

            // then
            assertThat(violations).hasSize(1);
            assertThat(violations).extracting("message").contains("\"^[a-zA-Z0-9]{8,15}$\"와 일치해야 합니다");

        }



            private Set<ConstraintViolation<SignupRequestDto>> validate(SignupRequestDto signupRequestDto) {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            return validator.validate(signupRequestDto);
        }

    }
}