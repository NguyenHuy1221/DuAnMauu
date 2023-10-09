package com.example.duanmau.model;

import com.google.firebase.Timestamp;

public class HoaDon {
    private int idhoadon;
    private int idkhachhang;
    private int idnhanvien;
    private String ngay;
    private String tongtien;


    public HoaDon(){

    }


    public HoaDon(int idhoadon, int idkhachhang, int idnhanvien, String ngay, String tongtien) {
        this.idhoadon = idhoadon;
        this.idkhachhang = idkhachhang;
        this.idnhanvien = idnhanvien;
        this.ngay = ngay;
        this.tongtien = tongtien;
    }

    public int getIdhoadon() {
        return idhoadon;
    }

    public void setIdhoadon(int idhoadon) {
        this.idhoadon = idhoadon;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getIdkhachhang() {
        return idkhachhang;
    }

    public void setIdkhachhang(int idkhachhang) {
        this.idkhachhang = idkhachhang;
    }

    public int getIdnhanvien() {
        return idnhanvien;
    }

    public void setIdnhanvien(int idnhanvien) {
        this.idnhanvien = idnhanvien;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }
}
