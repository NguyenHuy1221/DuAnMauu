package com.example.duanmau.model;

public class KhachHang {
    private int idkhachhang;
    private String tenkhachhang;
    private String sdt;
    private String diachi;

    public KhachHang(int idkhachhang, String tenkhachhang, String sdt, String diachi) {
        this.idkhachhang = idkhachhang;
        this.tenkhachhang = tenkhachhang;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public int getIdkhachhang() {
        return idkhachhang;
    }

    public void setIdkhachhang(int idkhachhang) {
        this.idkhachhang = idkhachhang;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public KhachHang(String tenkhachhang, String sdt, String diachi) {
        this.tenkhachhang = tenkhachhang;
        this.sdt = sdt;
        this.diachi = diachi;
    }
}
