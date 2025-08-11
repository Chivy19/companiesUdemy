package com.udemy.companies_crud.dto;

import com.udemy.companies_crud.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebSiteDTO {
    private Long id;
    private String name;
    @Builder.Default
    private Category category = Category.NONE;
    private String description;
}
