package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmau.Database.DbHelper;
import com.example.duanmau.model.GioHang;
import com.example.duanmau.model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDao {
    private SQLiteDatabase database;
    private DbHelper dbHelper;

    public KhachHangDao(Context context) {
        dbHelper = new DbHelper(context);
    }


    // Thêm một khách hàng vào cơ sở dữ liệu và trả về ID của khách hàng đã thêm
    public long insertKhachHang(KhachHang khachHang) {
        ContentValues values = new ContentValues();
        values.put("name", khachHang.getTenkhachhang());
        values.put("phone", khachHang.getSdt());
        values.put("address", khachHang.getDiachi());

        return database.insert("KHACH_HANG", null, values);
    }

    // Lấy danh sách tất cả khách hàng từ cơ sở dữ liệu
    public List<KhachHang> getAllKhachHang() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<KhachHang> listsp = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM KHACH_HANG", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                listsp.add(new KhachHang(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));

            } while (cursor.moveToNext());
        }
        return listsp;

    }
}
