package com.example.duanmau.model;

public class sanPham {

    private int masp;
    private String tensp;
    private int giasp;
    private int soluong;
    private String imagesp;
    private boolean isInCart;

    public sanPham(int masp, String tensp, int giasp,int soluong ,String imagesp) {
        this.masp = masp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluong = soluong;
        this.imagesp = imagesp;
        this.isInCart = false;
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

    public boolean isInCart() {
        return isInCart;
    }

    public void setInCart(boolean inCart) {
        isInCart = inCart;
    }

    // Phương thức kiểm tra số lượng tồn kho
    public boolean isAvailable(int quantity) {
        return this.soluong >= quantity;
    }

    // Phương thức giảm số lượng tồn kho sau khi mua hàng
    public void reduceStock(int quantity) {
        if (isAvailable(quantity)) {
            this.soluong -= quantity;
        }
    }

}
