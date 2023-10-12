package com.example.duanmau.model;

public class login {
    private int idtaikhoan;
    private int idnhanvien;
    private String gmail;
    private String pass;
    private int idchucvu;

    public login (){

    }

    public login(int idtaikhoan, int idnhanvien, String gmail, String pass, int idchucvu) {
        this.idtaikhoan = idtaikhoan;
        this.idnhanvien = idnhanvien;
        this.gmail = gmail;
        this.pass = pass;
        this.idchucvu = idchucvu;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getIdchucvu() {
        return idchucvu;
    }

    public void setIdchucvu(int idchucvu) {
        this.idchucvu = idchucvu;
    }
}
