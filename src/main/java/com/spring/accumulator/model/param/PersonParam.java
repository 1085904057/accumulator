package com.spring.accumulator.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "人员信息")
public class PersonParam {
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private Integer male;

    @ApiModelProperty("年龄")
    private Integer age;
}
