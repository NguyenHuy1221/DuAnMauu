package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.Database.DbHelper;
import com.example.duanmau.model.GioHang;

import java.util.ArrayList;

public class GioHangDao {

    DbHelper dbHelper;

    public GioHangDao(Context context) {
        this.dbHelper = new DbHelper(context);
    }

    // lấy danh sách sản phẩm
    public ArrayList<GioHang> getDS(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<GioHang> listsp = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SPDC",null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                listsp.add(new GioHang(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return listsp;
    }

    public boolean ThemSP(GioHang gioHang){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp",gioHang.getTensp());
        contentValues.put("giasp",gioHang.getGiasp());
        contentValues.put("soluong",gioHang.getSoluong());
        contentValues.put("imagesp",gioHang.getImagesp());
        contentValues.put("size",gioHang.getSize());


        long check = sqLiteDatabase.insert("SPDC",null,contentValues);
        if (check == -1){
            Log.e("HUY", "Lỗi khi thêm sản phẩm vào cơ sở dữ liệu");
            return false;
        }
        return true;
    }

    public boolean xoaSP(String masp){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete("SPDC","masp=?",new String[]{String.valueOf(masp)});
        if (check != -1){
            return true;
        }
        return false;

    }

    public boolean xoaTatCaSanPham() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM SPDC");
        db.close();
        return true;
    }

}
