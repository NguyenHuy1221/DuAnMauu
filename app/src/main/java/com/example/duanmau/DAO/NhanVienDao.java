package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Database.DbHelper;
import com.example.duanmau.model.NhanVien;
import com.example.duanmau.model.ThemTaiKhoan;
import com.example.duanmau.model.taiKhoan;

import java.util.ArrayList;

public class NhanVienDao {

    DbHelper dbHelper;

    public NhanVienDao(Context context) {
        this.dbHelper = new DbHelper(context);
    }


    public ArrayList<NhanVien> queryData() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<NhanVien> listNV = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT NHAN_VIEN.idnhanvien, NHAN_VIEN.imagesp,NHAN_VIEN.tennhanvien, NHAN_VIEN.sdt, NHAN_VIEN.diachi, NHAN_VIEN.ngayvaolam, NHAN_VIEN.idchucvu, NHAN_VIEN.trangthai, CHUC_VU.tenchucvu\n" +
                "FROM NHAN_VIEN\n" +
                "JOIN CHUC_VU\n" +
                "ON NHAN_VIEN.idchucvu = CHUC_VU.idchucvu;",null);

        if (cursor.getCount() >0) {
            cursor.moveToFirst();
            do {
                listNV.add(new NhanVien(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getInt(3) ,cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getInt(7),cursor.getString(8)  ));

            }while (cursor.moveToNext());
        }
        return listNV;


    }

//    public boolean addSP(NhanVien nhanVien) {
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("idnhanvien",themTaiKhoan.getIdnhanvien());
//        contentValues.put("gmail",themTaiKhoan.getGmail());
//        contentValues.put("matkhau",themTaiKhoan.getMatkhau());
//
//
//        long check = sqLiteDatabase.insert("TAI_KHOAN",null,contentValues);
//
//        if (check == -1){
//            return false;
//        }
//        return true;
//    }

//    public boolean updateNV(NhanVien nhanVien) {
//
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("idnhanvien",taiKhoan.getIdtk());
//        contentValues.put("gmail",taiKhoan.getGmail());
//        contentValues.put("matkhau",taiKhoan.getMatkhau());
//
//        long check = sqLiteDatabase.update("TAI_KHOAN",contentValues,"idtaikhoan=?",new String[]{String.valueOf(taiKhoan.getIdtaikhoan())});
//        if (check <=0 ){
//            return false;
//        }
//        return true;
//    }

    public boolean deleteSP(String matk) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int check = sqLiteDatabase.delete("TAI_KHOAN","idtaikhoan=?",new String[]{String.valueOf(matk)});
        if (check != -1 ){
            return true;
        }
        return false;
    }


}
