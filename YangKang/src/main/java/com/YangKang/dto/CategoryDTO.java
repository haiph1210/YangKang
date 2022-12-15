package com.YangKang.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryDTO {
    private Integer id;
    private String name;
    private Integer amount;
    private LocalDate createdDate;
    private LocalDateTime updateAt;

}
