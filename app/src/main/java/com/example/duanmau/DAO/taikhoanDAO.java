package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Database.DbHelper;
import com.example.duanmau.model.ThemTaiKhoan;
import com.example.duanmau.model.sanPham;
import com.example.duanmau.model.taiKhoan;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class taikhoanDAO {
    DbHelper dbHelper;

    public taikhoanDAO(Context context) {
        this.dbHelper = new DbHelper(context);
    }


    public ArrayList<taiKhoan> queryData() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<taiKhoan> listSP = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT NHAN_VIEN.idnhanvien, NHAN_VIEN.tennhanvien, CHUC_VU.tenchucvu, TAI_KHOAN.gmail, TAI_KHOAN.matkhau,NHAN_VIEN.imagesp,TAI_KHOAN.idtaikhoan FROM NHAN_VIEN INNER JOIN CHUC_VU ON NHAN_VIEN.idchucvu = CHUC_VU.idchucvu INNER JOIN TAI_KHOAN ON NHAN_VIEN.idnhanvien = TAI_KHOAN.idnhanvien",null);

        if (cursor.getCount() >0) {
            cursor.moveToFirst();
            do {
                listSP.add(new taiKhoan(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3) ,cursor.getString(4),cursor.getString(5),cursor.getInt(6) ));

            }while (cursor.moveToNext());
        }
        return listSP;


    }

    public boolean addSP(ThemTaiKhoan themTaiKhoan) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idnhanvien",themTaiKhoan.getIdnhanvien());
        contentValues.put("gmail",themTaiKhoan.getGmail());
        contentValues.put("matkhau",themTaiKhoan.getMatkhau());


        long check = sqLiteDatabase.insert("TAI_KHOAN",null,contentValues);

        if (check == -1){
            return false;
        }
        return true;
    }

    public boolean updateSP(taiKhoan taiKhoan) {

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idnhanvien",taiKhoan.getIdtk());
        contentValues.put("gmail",taiKhoan.getGmail());
        contentValues.put("matkhau",taiKhoan.getMatkhau());

        long check = sqLiteDatabase.update("TAI_KHOAN",contentValues,"idtaikhoan=?",new String[]{String.valueOf(taiKhoan.getIdtaikhoan())});
        if (check <=0 ){
            return false;
        }
        return true;
    }

    public boolean deleteSP(String matk) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete("TAI_KHOAN","idtaikhoan=?",new String[]{String.valueOf(matk)});
        if (check != -1 ){
            return true;
        }
        return false;
    }
}
