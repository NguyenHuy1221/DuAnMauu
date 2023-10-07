package com.example.duanmau.model;

public class SanPhamDaChon {

    private int masp;
    private String tensp;
    private String giasp;
    private String soluong;
    private String imagesp;
    private String size;

    public SanPhamDaChon(int masp, String tensp, String giasp, String soluong, String imagesp, String size) {
        this.masp = masp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluong = soluong;
        this.imagesp = imagesp;
        this.size = size;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getGiasp() {
        return giasp;
    }

    public void setGiasp(String giasp) {
        this.giasp = giasp;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getImagesp() {
        return imagesp;
    }

    public void setImagesp(String imagesp) {
        this.imagesp = imagesp;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
