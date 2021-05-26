package com.cosine.demo.common;

/**
 * 类描述：封装response
 *
 * @ClassName ResResult
 * @Description TODO
 * @Author cosine
 * @Date 2021/5/25 19:00
 * @Version 1.0
 */
public class ResResult<T> {
    /** 状态码 */
    private Integer code;
    /** 返回信息 */
    private String msg;
    /** data数据：本次返回的数据 */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
