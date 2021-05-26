package com.cosine.demo.common;

/**
 * 类描述：返回信息的工具类
 *
 * @ClassName ResResultUtil
 * @Description TODO
 * @Author cosine
 * @Date 2021/5/26 9:35
 * @Version 1.0
 */
public class ResResultUtil {
    /**
     * 有数据的成功
     * @param o
     * @return 成功的信息，包含data
     */
    public static ResResult success(Object o) {
        ResResult result = new ResResult();
        result.setData(o);
        result.setCode(200);
        result.setMsg("成功");
        return result;
    }

    /**
     * 不带数据的成功
     * @return
     */
    public static ResResult success() {
        ResResult result = new ResResult();
        result.setCode(200);
        result.setMsg("成功");
        return result;
    }

    /**
     * 失败
     * @param code 状态码
     * @param msg 信息
     * @return
     */
    public static ResResult error(Integer code, String msg) {
        ResResult result = new ResResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
