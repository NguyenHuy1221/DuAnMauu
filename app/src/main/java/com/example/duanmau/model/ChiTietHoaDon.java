package com.example.duanmau.model;

public class ChiTietHoaDon {
    private int idcthd;
    private int masp;
    private int idhoadon;
    private int soluong;
    private double dongia;

    public ChiTietHoaDon(){

    }

    public ChiTietHoaDon(int idcthd, int masp, int idhoadon, int soluong, double dongia) {
        this.idcthd = idcthd;
        this.masp = masp;
        this.idhoadon = idhoadon;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public int getIdcthd() {
        return idcthd;
    }

    public void setIdcthd(int idcthd) {
        this.idcthd = idcthd;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getIdhoadon() {
        return idhoadon;
    }

    public void setIdhoadon(int idhoadon) {
        this.idhoadon = idhoadon;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }
}
