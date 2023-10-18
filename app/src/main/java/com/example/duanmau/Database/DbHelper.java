package com.example.duanmau.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context,"QuanLySP",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //thuơng hieeuj
        String qlThuongHieu = "CREATE TABLE THUONG_HIEU(idthuonghieu INTEGER PRIMARY KEY ,tenthuonghieu TEXT)";
        db.execSQL(qlThuongHieu);
        //data thương hiệu
        db.execSQL("INSERT INTO THUONG_HIEU VALUES(1,'NIKE'),(2,'ADIDAS'),(3,'PUMA'),(4,'REEBOK')");

        //sản phẩm
        String qlSanPham = "CREATE TABLE SAN_PHAM(masp INTEGER PRIMARY KEY AUTOINCREMENT,tensp TEXT, giasp INTEGER,soluong INTEGER ,imagesp TEXT,size TEXT,idthuonghieu INTEGER REFERENCES THUONG_HIEU(idthuonghieu))";
        db.execSQL(qlSanPham);
        // data sp
//        db.execSQL("INSERT INTO SAN_PHAM VALUES (1,'Giày NIKE AIR ZOOM',2231000,9,'',1,'39'),(2,'Giày HERRO',3000000,10,'',1,'39')");

        // Khách Hàng
        String qlKhachHang = "CREATE TABLE KHACH_HANG(idkhachhang INTEGER PRIMARY KEY AUTOINCREMENT,tenkhachhang TEXT ,sdt TEXT,diachi TEXT)";
        db.execSQL(qlKhachHang);
        //data kh
        db.execSQL("INSERT INTO KHACH_HANG VALUES (1,'Nguyen Quang Huy','123456789','DakLak'),(2,'Ho Duc Hau','5675689','BMT')");

        // nhan vien
        String qlNhanVien = "CREATE TABLE NHAN_VIEN(idnhanvien INTEGER PRIMARY KEY AUTOINCREMENT,tennhanvien TEXT,imagesp TEXT,sdt TEXT,diachi TEXT,ngayvaolam TEXT,idchucvu INTEGER,trangthai INTEGER,FOREIGN KEY (idchucvu) REFERENCES CHUC_VU(idchucvu))";
        db.execSQL(qlNhanVien);
        //data nhan vien
        db.execSQL("INSERT INTO NHAN_VIEN VALUES (1,'LE THI NO','','67887655','GIALAI','11/12/2022',2,1),(2,'PHAM VAN DO','','443212','HN','11/07/2023',2,1),(3,'admin','','67887655','GIALAI','11/12/2022',1,1)");


        //tai khoan
        String qltaikhoan = "CREATE TABLE TAI_KHOAN(idtaikhoan INTEGER PRIMARY KEY AUTOINCREMENT,idnhanvien INTEGER,gmail TEXT,matkhau TEXT, FOREIGN KEY (idnhanvien) REFERENCES NHAN_VIEN(idnhanvien))";
        db.execSQL(qltaikhoan);
        db.execSQL("INSERT INTO TAI_KHOAN VALUES (1,1,'lethino@gmail.com','no123123'),(2,2,'phanthanhdo@gmail.com','do123123'),(3,3,'admin','admin')");

        //chuc vu
        String chucvu = "CREATE TABLE CHUC_VU(idchucvu INTEGER PRIMARY KEY AUTOINCREMENT,tenchucvu TEXT)";
        db.execSQL(chucvu);
        db.execSQL("INSERT INTO CHUC_VU VALUES (1,'admin'),(2,'Nhân viên')");


        // hoa don
        String qlHoaDon = "CREATE TABLE HOA_DON(idhoadon INTEGER PRIMARY KEY AUTOINCREMENT,idkhachhang INTEGER REFERENCES KHACH_HANG(idkhachhang),idnhanvien INTEGER REFERENCES NHAN_VIEN(idnhanvien) ,ngay date,tongtien TEXT,idmkm INTEGER REFERENCES KHUYEN_MAI(idmkm))";
        db.execSQL(qlHoaDon);
        // data hoadon
//        db.execSQL("INSERT INTO HOA_DON VALUES (1,1,1,'03/10/2023','3000000'),(2,2,2,'10/09/2023','6000000')");

        // chi tiet hoa don
        String ctHoaDon = "CREATE TABLE CTHD(idcthd INTEGER PRIMARY KEY AUTOINCREMENT,masp INTEGER REFERENCES SAN_PHAM(masp),idhoadon INTEGER REFERENCES HOA_DON(idhoadon),soluong INTEGER,dongia REAL)";
        db.execSQL(ctHoaDon);
        //data cthd
//        db.execSQL("INSERT INTO CTHD VALUES(1,1,1,2,300),(2,2,2,3,600)");

        String spdachon = "CREATE TABLE SPDC(masp INTEGER PRIMARY KEY AUTOINCREMENT,tensp TEXT, giasp INTEGER,soluong INTEGER ,imagesp TEXT,size TEXT)";
        db.execSQL(spdachon);

        String khuyenmai = "CREATE TABLE KHUYEN_MAI(idmkm INTEGER PRIMARY KEY AUTOINCREMENT,tenkm TEXT, tienkhuyenmai INTEGER)";
        db.execSQL(khuyenmai);
        db.execSQL("INSERT INTO KHUYEN_MAI VALUES (1,'50km',50000),(2,'100k',100000),(3,'150k',150000),(4,'200k',200000)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS THUONG_HIEU");
            db.execSQL("DROP TABLE IF EXISTS SAN_PHAM");
            db.execSQL("DROP TABLE IF EXISTS KHACH_HANG");
            db.execSQL("DROP TABLE IF EXISTS NHAN_VIEN");
            db.execSQL("DROP TABLE IF EXISTS HOA_DON");
            db.execSQL("DROP TABLE IF EXISTS CTHD");
            db.execSQL("DROP TABLE IF EXISTS SPDC");
            db.execSQL("DROP TABLE IF EXISTS TAI_KHOAN");
            db.execSQL("DROP TABLE IF EXISTS CHUC_VU");
            db.execSQL("DROP TABLE IF EXISTS KHUYEN_MAI");

            onCreate(db);
        }
    }




}
