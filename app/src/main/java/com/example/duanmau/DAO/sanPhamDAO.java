package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Database.DbHelper;
import com.example.duanmau.model.sanPham;

import java.util.ArrayList;

import javax.xml.transform.Result;

public class sanPhamDAO {
    DbHelper dbHelper;

    public sanPhamDAO(Context context) {
        this.dbHelper = new DbHelper(context);
    }

    public ArrayList<sanPham> getDS() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<sanPham> listSP = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SAN_PHAM",null);

        if (cursor.getCount() >0) {
            cursor.moveToFirst();
            do {
                listSP.add(new sanPham(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),cursor.getInt(3) ,cursor.getString(4),cursor.getString(5) ));

            }while (cursor.moveToNext());
        }
        return listSP;
    }

    public boolean addSP(sanPham sanPham) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp",sanPham.getTensp());
        contentValues.put("giasp",sanPham.getGiasp());
        contentValues.put("soluong",sanPham.getSoluong());
        contentValues.put("imagesp",sanPham.getImagesp());
        contentValues.put("size",sanPham.getSize());

        long check = sqLiteDatabase.insert("SAN_PHAM",null,contentValues);

        if (check == -1){
            return false;
        }
        return true;
    }

    public boolean updateSP(sanPham sanPham) {

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp",sanPham.getTensp());
        contentValues.put("giasp",sanPham.getGiasp());
        contentValues.put("soluong",sanPham.getSoluong());
        contentValues.put("size",sanPham.getSize());


        long check = sqLiteDatabase.update("SAN_PHAM",contentValues,"masp=?",new String[]{String.valueOf(sanPham.getMasp())});
        if (check <=0 ){
            return false;
        }
        return true;
    }

    public boolean deleteSP(String masp) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete("SAN_PHAM","masp=?",new String[]{String.valueOf(masp)});
        if (check != -1 ){
            return true;
        }
        return false;
    }

    public ArrayList<sanPham> timKiemSP(String keyword) {
        ArrayList<sanPham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM SAN_PHAM WHERE tensp LIKE '%" + keyword + "%'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new sanPham(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;
    }

}
