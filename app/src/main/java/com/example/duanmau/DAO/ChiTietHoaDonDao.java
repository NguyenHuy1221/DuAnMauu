package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Database.DbHelper;
import com.example.duanmau.model.ChiTietHoaDon;

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


}
