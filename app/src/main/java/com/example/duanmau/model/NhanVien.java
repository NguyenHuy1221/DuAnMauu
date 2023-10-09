package com.example.duanmau.model;

public class NhanVien {
    private int idnhanvien;

    private String imagenhanvien;
    private String tennhanvien;
    private int sodienthoai;


    private String diachi;

    private String ngayvaolam;

    private  int idchucvu;
    private int trangthai;
    private String chucvu;



    public NhanVien(){


    }

    public NhanVien(int idnhanvien, String imagenhanvien, String tennhanvien, int sodienthoai, String diachi, String ngayvaolam, int idchucvu, int trangthai, String chucvu) {
        this.idnhanvien = idnhanvien;
        this.imagenhanvien = imagenhanvien;
        this.tennhanvien = tennhanvien;
        this.sodienthoai = sodienthoai;
        this.diachi = diachi;
        this.ngayvaolam = ngayvaolam;
        this.idchucvu = idchucvu;
        this.trangthai = trangthai;
        this.chucvu = chucvu;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getIdchucvu() {
        return idchucvu;
    }

    public void setIdchucvu(int idchucvu) {
        this.idchucvu = idchucvu;
    }

    public int getIdnhanvien() {
        return idnhanvien;
    }

    public void setIdnhanvien(int idnhanvien) {
        this.idnhanvien = idnhanvien;
    }

    public int getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(int sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getNgayvaolam() {
        return ngayvaolam;
    }

    public void setNgayvaolam(String ngayvaolam) {
        this.ngayvaolam = ngayvaolam;
    }

    public String getImagenhanvien() {
        return imagenhanvien;
    }

    public void setImagenhanvien(String imagenhanvien) {
        this.imagenhanvien = imagenhanvien;
    }
}
