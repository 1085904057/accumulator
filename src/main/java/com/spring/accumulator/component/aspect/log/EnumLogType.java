package com.spring.accumulator.component.aspect.log;

/**
 * 日志类型枚举
 *
 * @author wangrubin1
 * @date 2022-08-04
 */
public enum EnumLogType {

    INSERT(1, "新增"),
    DELETE(2, "删除"),
    UPDATE(3, "修改");

    /**
     * 类型编号
     */
    private Integer code;

    /**
     * 类型名称
     */
    private String name;

    EnumLogType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
