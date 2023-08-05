package com.example.assignment.Models;

import java.util.List;

public class ApiResponseTruyen {

    private int status;

    private String msg;

    private List<Truyen> data;

    @Override
    public String toString() {
        return "ApiResponseTruyen{" +
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

    public List<Truyen> getData() {
        return data;
    }

    public void setData(List<Truyen> data) {
        this.data = data;
    }

    public ApiResponseTruyen(int status, String msg, List<Truyen> data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ApiResponseTruyen() {
    }
}
