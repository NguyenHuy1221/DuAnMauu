package com.example.duanmau.model;

public class ThemNhanVien {


        private  String tennhanvien;
        private String imagenv;
        private int sdt;
        private String diachi;
        private String ngayvaolam;
        private int idchucvu;
        private int trangthai;

        public ThemNhanVien(){

        }


    public ThemNhanVien(String tennhanvien, String imagenv, int sdt, String diachi, String ngayvaolam, int  idchucvu, int trangthai) {
        this.tennhanvien = tennhanvien;
        this.imagenv = imagenv;
        this.sdt = sdt;
        this.diachi = diachi;
        this.ngayvaolam = ngayvaolam;
        this.idchucvu = idchucvu;
        this.trangthai = trangthai;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

    public String getImagenv() {
        return imagenv;
    }

    public void setImagenv(String imagenv) {
        this.imagenv = imagenv;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNgayvaolam() {
        return ngayvaolam;
    }

    public void setNgayvaolam(String ngayvaolam) {
        this.ngayvaolam = ngayvaolam;
    }

    public int getIdchucvu() {
        return idchucvu;
    }

    public void setIdchucvu(int idchucvu) {
        this.idchucvu = idchucvu;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
