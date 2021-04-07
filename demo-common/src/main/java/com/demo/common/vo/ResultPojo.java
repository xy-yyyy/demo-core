package com.demo.common.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 14:53 2020/8/27
 */
@Data
@Builder
public class ResultPojo {
    private Integer code = 200;

    private String message = "SUCCESS";

    private Object object;

    public ResultPojo() {
    }

    public ResultPojo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultPojo(Integer code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }

}
