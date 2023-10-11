package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Database.DbHelper;
import com.example.duanmau.model.ChiTietHoaDon;
import com.example.duanmau.model.GioHang;
import com.example.duanmau.model.HoaDon;
import com.example.duanmau.model.KhachHang;
import com.example.duanmau.model.SanPhamDaChon;
import com.example.duanmau.model.sanPham;

import java.util.ArrayList;

public class ChiTietHoaDonDao {

    DbHelper dbHelper;

    public ChiTietHoaDonDao(Context context){
        this.dbHelper = new DbHelper(context);
    }

    public long themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("masp", chiTietHoaDon.getSanPham().getMasp());
        values.put("idhoadon", chiTietHoaDon.getHoaDon().getIdhoadon());
        values.put("soluong", chiTietHoaDon.getSoluong());
        values.put("dongia", chiTietHoaDon.getDongia());

        long result = sqLiteDatabase.insert("CTHD", null, values);
        sqLiteDatabase.close();
        return result;
    }





    public ArrayList<ChiTietHoaDon> layDanhSachChiTietHoaDon() {
        ArrayList<ChiTietHoaDon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        String query = ("SELECT CTHD.idcthd, SPDC.masp, SPDC.tensp, SPDC.giasp, SPDC.soluong, SPDC.imagesp, SPDC.size, HOA_DON.idhoadon, HOA_DON.idkhachhang, HOA_DON.idnhanvien, HOA_DON.ngay, HOA_DON.tongtien, CTHD.soluong, CTHD.dongia, KHACH_HANG.tenkhachhang, KHACH_HANG.sdt, KHACH_HANG.diachi " +
                "FROM CTHD " +
                "INNER JOIN SPDC ON CTHD.masp = SPDC.masp " +
                "INNER JOIN HOA_DON ON CTHD.idhoadon = HOA_DON.idhoadon " +
                "INNER JOIN KHACH_HANG ON HOA_DON.idkhachhang = KHACH_HANG.idkhachhang");
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int idcthd = cursor.getInt(0);
                int masp = cursor.getInt(1);
                String tensp = cursor.getString(2);
                int giasp = cursor.getInt(3);
                int solg = cursor.getInt(4);
                String img = cursor.getString(5);
                String size = cursor.getString(6);
                int idhd = cursor.getInt(7);
                int idkhachhang = cursor.getInt(8);
                int idnhanvien = cursor.getInt(9);
                String ngay = cursor.getString(10);
                String tongtien = cursor.getString(11);
                int soluong = cursor.getInt(12);
                int dongia = cursor.getInt(13);
                String tenKhachHang = cursor.getString(14);
                String sdtKhachHang = cursor.getString(15);
                String diaChiKhachHang = cursor.getString(16);

                sanPham sanPham = new sanPham(masp, tensp, giasp, solg, img, size);
                HoaDon hoaDon = new HoaDon(idhd, idkhachhang, idnhanvien, ngay, tongtien);
                KhachHang khachHang = new KhachHang(tenKhachHang, sdtKhachHang, diaChiKhachHang);
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(idcthd, sanPham, hoaDon, khachHang, soluong, dongia);

                list.add(chiTietHoaDon);
            } while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();
        return list;
    }




}
