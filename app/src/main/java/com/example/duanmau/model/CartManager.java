package com.example.duanmau.model;

import static okhttp3.internal.Internal.instance;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private ArrayList<GioHang> gioHangList;
    private int maxId;

    public CartManager() {
        gioHangList = new ArrayList<>();
        maxId = 0;
    }

    public void themSanPham(GioHang gioHang) {
        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        for (GioHang item : gioHangList) {
            if (item.getMasp() == gioHang.getMasp()) {
                // Sản phẩm đã tồn tại, tăng số lượng
                item.setSoluong(item.getSoluong() + 1);
                return;
            }
        }
        // Sản phẩm chưa tồn tại, thêm sản phẩm mới
        maxId++; // Tăng maxId lên một đơn vị
        gioHang.setMasp(maxId); // Đặt id cho sản phẩm mới
        gioHangList.add(gioHang);
    }

    public void xoaSanPham(int masp) {
        for (GioHang gioHang : gioHangList) {
            if (gioHang.getMasp() == masp) {
                // Xóa sản phẩm khỏi giỏ hàng
                gioHangList.remove(gioHang);
                break;
            }
        }
    }

}
