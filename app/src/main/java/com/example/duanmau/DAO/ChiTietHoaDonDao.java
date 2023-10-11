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

public class ChiTietHoaDonDao {

    DbHelper dbHelper;

    public ChiTietHoaDonDao(Context context){
        this.dbHelper = new DbHelper(context);
    }

    public long themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("masp", chiTietHoaDon.getMasp());
        values.put("idhoadon", chiTietHoaDon.getIdhoadon());
        values.put("soluong", chiTietHoaDon.getSoluong());
        values.put("dongia", chiTietHoaDon.getDongia());

        long result = database.insert("CTHD", null, values);

        return result;
    }

//    public ArrayList<ChiTietHoaDon> getDSHD() {
//        ArrayList<ChiTietHoaDon> list = new ArrayList<>();
//        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
//        String query = ("SELECT CTHD.idcthd ,SPDC.masp,SPDC.tensp,SPDC.giasp,SPDC.soluong,SPDC.imagesp,SPDC.size,HOA_DON.idhoadon, HOA_DON.idkhachhang, HOA_DON.idnhanvien, HOA_DON.ngay, HOA_DON.tongtien,CTHD.soluong,CTHD.dongia FROM CTHD INNER JOIN SPDC ON CTHD.masp = SPDC.masp INNER JOIN HOA_DON on CTHD.idhoadon = HOA_DON.idhoadon");
//        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
//
//        if (cursor.moveToFirst()){
//            do {
//                int idcthd = cursor.getInt(0);
//                int masp = cursor.getInt(1);
//                String tensp = cursor.getString(2);
//                int giasp = cursor.getInt(3);
//                int solg = cursor.getInt(4);
//                String img = cursor.getString(5);
//                String size = cursor.getString(6);
//                int idhd = cursor.getInt(7);
//                int idkhachhang = cursor.getInt(8);
//                int idnhanvien = cursor.getInt(9);
//                String ngay = cursor.getString(10);
//                String tongtien = cursor.getString(11);
//                int soluong = cursor.getInt(12);
//                double dongia = cursor.getDouble(13);
//
//                // Tạo đối tượng HoaDon
//                HoaDon hoaDon = new HoaDon(idhd, idkhachhang, idnhanvien, ngay, tongtien);
//                GioHang gioHang = new GioHang(masp,tensp,giasp,solg,img,size);
//                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(idcthd,gioHang,hoaDon,soluong,dongia);
//
//            }while (cursor.moveToNext());
//        }
//
//
//        return list;
//    }


}
