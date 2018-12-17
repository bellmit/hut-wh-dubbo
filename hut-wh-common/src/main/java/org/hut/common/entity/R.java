package org.hut.common.entity;

import cn.hutool.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hutwanghui on 2018/11/24 15:44.
 * email:zjjhwanhui@163.com
 * qq:472860892
 * desc: SpringMvc统一返回对象
 */

public class R<T> extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int NO_LOGIN = -1;

    public static final int SUCCESS = 1;

    public static final int FAIL = 0;

    public static final int NO_PERMISSION = 2;

    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }

    public R(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public R(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = FAIL;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }



    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }
    public static R error(String msg) {
        R r = new R();
        r.put("code",FAIL);
        r.put("msg", msg);
        return r;
    }
    public static R ok(String msg) {
        R r = new R();
        r.put("code",SUCCESS);
        r.put("msg", msg);
        return r;
    }
    public static R ok() {
        return new R();
    }
    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }
    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
