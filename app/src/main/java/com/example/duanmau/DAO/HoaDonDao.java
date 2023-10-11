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

        long check = sqLiteDatabase.insert("HOA_DON",null,contentValues);
        return check;
    }




//    public ArrayList<ChiTietHoaDon> getALL(){
//        ArrayList<ChiTietHoaDon> list = new ArrayList<>();
//        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
//        String query = ("SELECT CTHD.idcthd, HOA_DON.idhoadon, HOA_DON.idkhachhang, HOA_DON.idnhanvien, HOA_DON.ngay, HOA_DON.tongtien,CTHD.masp, KHACH_HANG.tenkhachhang, KHACH_HANG.sdt, KHACH_HANG.diachi, SPDC.tensp, CTHD.soluong\n" +
//                "FROM CTHD\n" +
//                "INNER JOIN HOA_DON ON CTHD.idhoadon = HOA_DON.idhoadon\n" +
//                "INNER JOIN SPDC ON SPDC.masp = CTHD.masp\n" +
//                "INNER JOIN KHACH_HANG ON HOA_DON.idkhachhang = KHACH_HANG.idkhachhang");
//        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                int idcthd = cursor.getInt(0);
//                int idhoadon = cursor.getInt(1);
//                int idkhachhang = cursor.getInt(2);
//                int idnhanvien = cursor.getInt(3);
//                String ngay = cursor.getString(4);
//                String tongtien = cursor.getString(5);
//                int idsp = cursor.getInt(6);
//                String tenkhachhang = cursor.getString(6);
//                String sdt = cursor.getString(7);
//                String diachi = cursor.getString(8);
//                String tensp = cursor.getString(9);
//                int soluong = cursor.getInt(10);
//
//                // Tạo đối tượng HoaDon
//                HoaDon hoaDon = new HoaDon(idhoadon, idkhachhang, idnhanvien, ngay, tongtien);
//
//                // Tạo đối tượng ChiTietHoaDon
//                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(idcthd,idsp,hoaDon,tensp,soluong);
//
//                // Thêm vào danh sách
//                list.add(chiTietHoaDon);
//            } while (cursor.moveToNext());
//        }
//
//
//        cursor.close();
//        sqLiteDatabase.close();
//
//        return list;
//    }

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
