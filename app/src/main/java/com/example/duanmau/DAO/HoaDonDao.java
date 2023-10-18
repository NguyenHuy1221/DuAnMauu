package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Database.DbHelper;
import com.example.duanmau.model.ChiTietHoaDon;
import com.example.duanmau.model.GioHang;
import com.example.duanmau.model.HoaDon;

import java.util.ArrayList;

public class HoaDonDao {

    private DbHelper dbHelper;

    public HoaDonDao(Context Context) {
        dbHelper = new DbHelper(Context);
    }

    public long themHoaDon(HoaDon hoaDon) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("idkhachhang",hoaDon.getIdkhachhang());
        contentValues.put("idnhanvien",hoaDon.getIdnhanvien());
        contentValues.put("ngay",hoaDon.getNgay());
        contentValues.put("tongtien",hoaDon.getTongtien());
        contentValues.put("idmkm",hoaDon.getIdmkm());

        long check = sqLiteDatabase.insert("HOA_DON",null,contentValues);
        return check;
    }





//    public double getDoanhThuTheoNgay(){
//        double doanhThu = 0;
//        String sSQL ="SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien' " +
//                "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
//                "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach where HoaDon.ngayMua = date('now') " +
//                "GROUP BY HoaDonChiTiet.maSach)tmp";
//        Cursor c = db.rawQuery(sSQL, null);
//        c.moveToFirst();
//        while (c.isAfterLast()==false){
//            doanhThu = c.getDouble(0);
//            c.moveToNext();
//        }
//        c.close();
//        return doanhThu;
//
//    }
//    public double getDoanhThuTheoThang(){
//        double doanhThu = 0;
//        String sSQL ="SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien' " +
//                "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
//                "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach where strftime('%m',HoaDon.ngayMua) = " +
//                "strftime('%m','now') GROUP BY HoaDonChiTiet.maSach)tmp";
//        Cursor c = db.rawQuery(sSQL, null);
//        c.moveToFirst();
//        while (c.isAfterLast()==false){
//            doanhThu = c.getDouble(0);
//            c.moveToNext();
//        }
//        c.close();
//        return doanhThu;
//
//    }
//    public double getDoanhThuTheoNam(){
//        double doanhThu = 0;
//        String sSQL ="SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien' " +
//                "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
//                "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach where strftime('%Y',HoaDon.ngayMua)" +
//                " = strftime('%Y','now') GROUP BY HoaDonChiTiet.maSach)tmp";
//        Cursor c = db.rawQuery(sSQL, null);
//        c.moveToFirst();
//        while (c.isAfterLast()==false){
//            doanhThu = c.getDouble(0);
//            c.moveToNext();
//        }
//        c.close();
//        return doanhThu;
//
//    }



}
