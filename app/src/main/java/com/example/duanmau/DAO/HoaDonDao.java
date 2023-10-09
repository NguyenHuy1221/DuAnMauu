package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Database.DbHelper;
import com.example.duanmau.model.HoaDon;

import java.util.ArrayList;

public class HoaDonDao {

    private DbHelper dbHelper;

    public HoaDonDao(Context Context) {
        dbHelper = new DbHelper(Context);
    }

    public boolean themHoaDon(HoaDon hoaDon) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("idkhachhang",hoaDon.getIdkhachhang());
        contentValues.put("idnhanvien",hoaDon.getIdnhanvien());
        contentValues.put("ngay",hoaDon.getNgay());
        contentValues.put("tongtien",hoaDon.getTongtien());

        long check = sqLiteDatabase.insert("HOA_DON",null,contentValues);
        if (check == -1 ){
            return false;
        }
        return true;
    }



}
