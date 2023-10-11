package com.example.duanmau.model;

public class sanPham {

    private int masp;
    private String tensp;
    private int giasp;
    private int soluong;
    private String imagesp;
    private String size;

    public sanPham(int masp, String tensp, int giasp,int soluong ,String imagesp,String size) {
        this.masp = masp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluong = soluong;
        this.imagesp = imagesp;
        this.size = size;
    }

    public sanPham(){

    }

    public sanPham(String tensp, int giasp, int soluong, String imagesp) {
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluong = soluong;
        this.imagesp = imagesp;
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

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public String getImagesp() {
        return imagesp;
    }

    public void setImagesp(String imagesp) {
        this.imagesp = imagesp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
