package com.webflux.www.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table("User")
public class User extends Auditor {

    @Column("user_id")
    private Long userId;

    @Column("name")
    private String name;

    @Column("age")
    private int age;

    @Column("gender")
    private String gender;

    @Column("delete_yn")
    private String deleteYn;

    public User(Long userId, String name, int age, String gender, String deleteYn, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.deleteYn = deleteYn;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
