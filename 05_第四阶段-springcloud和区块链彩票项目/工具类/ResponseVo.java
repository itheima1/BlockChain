package com.itheima.lottery.order.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseVo<T> {
    private boolean flag;
    private String msg;
    private T data;

    public static <T> ResponseVo<T> success(T data) {
        return (ResponseVo<T>) builder().flag(true).data(data).build();
    }

    public static ResponseVo error(String msg) {
        return builder().flag(false).msg(msg).build();
    }
}
