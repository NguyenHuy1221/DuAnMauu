package com.example.duanmau.model;

import java.util.ArrayList;
import java.util.List;

public class HoaDon {
    private int idhoadon;
    private int idkhachhang;
    private int idnhanvien;
    private String ngay;
    private String tongtien;

    private List<sanPham> dsGioHang = new ArrayList<>();



    public HoaDon(int idhoadon, int idkhachhang, int idnhanvien, String ngay, String tongtien, List<sanPham> dsGioHang) {
        this.idhoadon = idhoadon;
        this.idkhachhang = idkhachhang;
        this.idnhanvien = idnhanvien;
        this.ngay = ngay;
        this.tongtien = tongtien;
        this.dsGioHang = dsGioHang;
    }

    public HoaDon() {
        dsGioHang = new ArrayList<>();
    }

    public List<sanPham> getDsGioHang() {
        return dsGioHang;
    }

    public void setDsGioHang(List<sanPham> dsGioHang) {
        this.dsGioHang = dsGioHang;
    }

    public void addSanPham(sanPham gioHang) {
        dsGioHang.add(gioHang);
    }


//    public HoaDon(){
//
//    }


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
