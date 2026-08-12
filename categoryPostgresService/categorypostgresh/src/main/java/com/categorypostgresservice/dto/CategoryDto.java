package com.categorypostgresservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryDto {

    private String title;
    private String description;
    private boolean active;

}
