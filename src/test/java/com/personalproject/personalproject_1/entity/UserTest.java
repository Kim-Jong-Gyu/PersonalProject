package com.personalproject.personalproject_1.entity;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
    User user;
    @BeforeEach
    void setUp(){
        user = new User();
    }

    @Test
    @DisplayName("User 생성 테스트")
    void makeUserTest() {
        // given
        String username = "JongGyu";
        String password = "1234";

        // when
        user.setUsername(username);
        user.setPassword(password);

        // then
        // usingRecursiveComparison() :실제 객체와 expected 객체의 필드를 재귀적으로 비교한다
        assertThat(user).isInstanceOf(User.class);
        assertThat(user).usingRecursiveComparison().isEqualTo(new User("JongGyu", "1234"));
    }

}