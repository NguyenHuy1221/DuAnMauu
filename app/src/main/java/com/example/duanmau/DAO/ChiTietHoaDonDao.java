package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.Database.DbHelper;
import com.example.duanmau.model.ChiTietHoaDon;
import com.example.duanmau.model.HoaDon;
import com.example.duanmau.model.KhachHang;
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
        String query = ("SELECT CTHD.idcthd, SAN_PHAM.masp, SAN_PHAM.tensp, SAN_PHAM.giasp, SAN_PHAM.soluong, SAN_PHAM.imagesp, SAN_PHAM.size, HOA_DON.idhoadon, HOA_DON.idkhachhang, HOA_DON.idnhanvien, HOA_DON.ngay, HOA_DON.tongtien, CTHD.soluong, CTHD.dongia, KHACH_HANG.tenkhachhang, KHACH_HANG.sdt, KHACH_HANG.diachi\n" +
                "                FROM CTHD \n" +
                "                INNER JOIN SAN_PHAM ON CTHD.masp = SAN_PHAM.masp \n" +
                "                INNER JOIN HOA_DON ON CTHD.idhoadon = HOA_DON.idhoadon \n" +
                "                INNER JOIN KHACH_HANG ON HOA_DON.idkhachhang = KHACH_HANG.idkhachhang");

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



    public double getDoanhThuTheoNgay() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        double doanhThu = 0;
        String sSQL = "SELECT SUM(total_price) FROM ( " +
                "    SELECT SPDC.masp, SUM(CTHD.soluong * SPDC.giasp) as total_price " +
                "    FROM CTHD " +
                "    INNER JOIN SPDC ON CTHD.masp = SPDC.masp " +
                "    INNER JOIN HOA_DON ON CTHD.idhoadon = HOA_DON.idhoadon " +
                "    WHERE date(HOA_DON.ngay) = date('now') " +
                "    GROUP BY SPDC.masp " +
                ") AS tmp";

        Cursor c = sqLiteDatabase.rawQuery(sSQL, null);

        if (c.moveToFirst()) {


            int columnIndex = c.getColumnIndex("SUM(total_price)");

            if (columnIndex != -1) {
                doanhThu = c.getDouble(columnIndex);
            } else {
                // Handle the case where the column doesn't exist
                Log.e("Error", "Column 'SUM(total_price)' not found");
            }
        }

        c.close();
        return doanhThu;
    }






    public double getDoanhThuTheoThang(int month, int year) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        double doanhThu = 0;

        String sSQL = "SELECT SUM(total_price) FROM (\n" +
                "    SELECT SPDC.masp, SUM(CTHD.soluong * SPDC.giasp) as total_price\n" +
                "    FROM CTHD\n" +
                "    INNER JOIN SPDC ON CTHD.masp = SPDC.masp\n" +
                "    INNER JOIN HOA_DON ON CTHD.idhoadon = HOA_DON.idhoadon\n" +
                "    WHERE strftime('%Y-%m', HOA_DON.ngay) = '" + year + "-" + (month < 10 ? "0" + month : month) + "'\n" +
                "    GROUP BY SPDC.masp\n" +
                ") AS tmp";

        Cursor c = sqLiteDatabase.rawQuery(sSQL, null);

        if (c.moveToFirst()) {
            doanhThu = c.getDouble(0);
        }

        c.close();
        return doanhThu;
    }

    public double getDoanhThuTheoNam(int year) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        double doanhThu = 0;

        String sSQL = "SELECT SUM(total_price) FROM (\n" +
                "    SELECT SPDC.masp, SUM(CTHD.soluong * SPDC.giasp) as total_price\n" +
                "    FROM CTHD\n" +
                "    INNER JOIN SPDC ON CTHD.masp = SPDC.masp\n" +
                "    INNER JOIN HOA_DON ON CTHD.idhoadon = HOA_DON.idhoadon\n" +
                "    WHERE strftime('%Y', HOA_DON.ngay) = '" + year + "'\n" +
                "    GROUP BY SPDC.masp\n" +
                ") AS tmp";

        Cursor c = sqLiteDatabase.rawQuery(sSQL, null);

        if (c.moveToFirst()) {
            doanhThu = c.getDouble(0);
        }

        c.close();
        return doanhThu;
    }


}
