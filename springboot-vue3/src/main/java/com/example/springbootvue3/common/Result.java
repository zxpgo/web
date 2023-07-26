package com.example.springbootvue3.common;
/*
* Controller 统一返回的包装类*/
public class Result {
    private  static final String SUCESSS_CODE = "200";
    private  static final String FAILURE_CODE = "500";
    private String code; //返回的错误码
    private String msg; //错误信息
    private Object data; //数据

    public Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success(Object object)
    {
        return new Result(SUCESSS_CODE, "", object);
    }

    public static Result success()
    {
        return new Result(SUCESSS_CODE, "");
    }

    public static Result error(String msg)
    {
        return new Result(FAILURE_CODE, msg);
    }
}
