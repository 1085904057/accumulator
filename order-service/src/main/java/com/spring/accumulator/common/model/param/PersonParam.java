package com.spring.accumulator.common.model.param;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonParam {
    private Long id;

    private String name;

    private Integer male;

    private Integer age;
}
