package com.example.duanmau.model;

public class ThemTaiKhoan {
    private int idtaikhoan;
    private int idnhanvien;
    private String gmail;
    private String matkhau;


    public ThemTaiKhoan(){

    }

    public ThemTaiKhoan( int idnhanvien, String gmail, String matkhau) {
        this.idnhanvien = idnhanvien;
        this.gmail = gmail;
        this.matkhau = matkhau;
    }



    public int getIdtaikhoan() {
        return idtaikhoan;
    }

    public void setIdtaikhoan(int idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
    }

    public int getIdnhanvien() {
        return idnhanvien;
    }

    public void setIdnhanvien(int idnhanvien) {
        this.idnhanvien = idnhanvien;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
