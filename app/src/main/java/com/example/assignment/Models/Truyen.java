package com.example.assignment.Models;

import java.util.List;

public class Truyen {

    private String _id,tentruyen,motangan,tentacgia,namxuatban,anhbia;

    private List<String> anhnoidungtruyen;

    @Override
    public String toString() {
        return "Truyen{" +
                "_id='" + _id + '\'' +
                ", tentruyen='" + tentruyen + '\'' +
                ", motangan='" + motangan + '\'' +
                ", tentacgia='" + tentacgia + '\'' +
                ", namxuatban='" + namxuatban + '\'' +
                ", anhbia='" + anhbia + '\'' +
                ", anhnoidungtruyen=" + anhnoidungtruyen +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTentruyen() {
        return tentruyen;
    }

    public void setTentruyen(String tentruyen) {
        this.tentruyen = tentruyen;
    }

    public String getMotangan() {
        return motangan;
    }

    public void setMotangan(String motangan) {
        this.motangan = motangan;
    }

    public String getTentacgia() {
        return tentacgia;
    }

    public void setTentacgia(String tentacgia) {
        this.tentacgia = tentacgia;
    }

    public String getNamxuatban() {
        return namxuatban;
    }

    public void setNamxuatban(String namxuatban) {
        this.namxuatban = namxuatban;
    }

    public String getAnhbia() {
        return anhbia;
    }

    public void setAnhbia(String anhbia) {
        this.anhbia = anhbia;
    }

    public List<String> getAnhnoidungtruyen() {
        return anhnoidungtruyen;
    }

    public void setAnhnoidungtruyen(List<String> anhnoidungtruyen) {
        this.anhnoidungtruyen = anhnoidungtruyen;
    }

    public Truyen(String _id, String tentruyen, String motangan, String tentacgia, String namxuatban, String anhbia, List<String> anhnoidungtruyen) {
        this._id = _id;
        this.tentruyen = tentruyen;
        this.motangan = motangan;
        this.tentacgia = tentacgia;
        this.namxuatban = namxuatban;
        this.anhbia = anhbia;
        this.anhnoidungtruyen = anhnoidungtruyen;
    }

    public Truyen() {
    }
}
