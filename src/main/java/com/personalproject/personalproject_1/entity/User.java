package com.personalproject.personalproject_1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z]).{4,10}", message = "name은 4~10자 영문 소문자, 숫자 사용하세요.")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,15}", message = "password는 8~15자 영문 대 소문자, 숫자 사용하세요.")
    @Column(name = "password", nullable = false)
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
