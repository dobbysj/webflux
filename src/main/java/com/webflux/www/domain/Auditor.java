package com.webflux.www.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Auditor {

    @CreatedDate
    @Column("created_date")
    public LocalDateTime createdDate;

    @LastModifiedDate
    @Column("modified_date")
    public LocalDateTime modifiedDate;

}
