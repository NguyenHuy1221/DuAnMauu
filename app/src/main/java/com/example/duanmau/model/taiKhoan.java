package com.example.duanmau.model;

public class taiKhoan {
    private int idtk;
    private String name;
    private String chucvu;
    private String gmail;
    private String matkhau;
    private String imageTK;
    private int idtaikhoan;

    public taiKhoan(){

    }

    public taiKhoan(int idtk, String name, String chucvu, String gmail, String matkhau, String imageTK, int idtaikhoan) {
        this.idtk = idtk;
        this.name = name;
        this.chucvu = chucvu;
        this.gmail = gmail;
        this.matkhau = matkhau;
        this.imageTK = imageTK;
        this.idtaikhoan= idtaikhoan;
    }

    public int getIdtk() {
        return idtk;
    }

    public void setIdtk(int idtk) {
        this.idtk = idtk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
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

    public String getImageTK() {
        return imageTK;
    }

    public void setImageTK(String imageTK) {
        this.imageTK = imageTK;
    }

    public int getIdtaikhoan() {
        return idtaikhoan;
    }

    public void setIdtaikhoan(int idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
    }
}
