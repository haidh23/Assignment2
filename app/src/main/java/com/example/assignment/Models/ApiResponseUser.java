package com.example.assignment.Models;

import java.util.List;

public class ApiResponseUser {
    private int status;
    private String msg;
    private List<User> data;

    @Override
    public String toString() {
        return "ArrayUsers{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }



    public ApiResponseUser(int status, String msg, List<User> data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ApiResponseUser() {
    }
}
