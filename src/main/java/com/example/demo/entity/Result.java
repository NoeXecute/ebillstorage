package com.example.demo.entity;

public class Result {
    private int code;
    private String text;
    private Object data;

    @Override
    public String toString() {
        return "EduResult [code=" + code + ", text=" + text + ", data=" + data + "]";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result ok(Object data) {
            Result result = new Result();
            result.code = 200;
            result.text = "成功";
            result.data = data;
            return result;
    }
    public static Result error(String... message){//...代表多个String(这里指exception的多行报错),所以message是一个String集合
        Result result = new Result();
        result.code=500;
        if (message.length>10){//字数超过10个就返回这个
            result.setText("系统出错,请联系管理员劉書霊");
        } else{
            result.setText(message[0]);//10行以内报错就只取第一行返回给页面就行了,而JSR303异常会返回一句话
        }
        result.data=null;
        return result;
    }
}
