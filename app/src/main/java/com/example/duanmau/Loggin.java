package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.DAO.taikhoanDAO;
import com.example.duanmau.Database.DbHelper;
import com.example.duanmau.Fragment.Fragment_Trang_Chu;
import com.example.duanmau.model.login;
import com.example.duanmau.model.taiKhoan;

import java.util.ArrayList;

public class Loggin extends AppCompatActivity {

    Context context;
    com.example.duanmau.DAO.taikhoanDAO taikhoanDAO ;
    EditText user,pass;
    Button login;

    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);
        user = findViewById(R.id.login_user);
        pass = findViewById(R.id.login_pass);
        login = findViewById(R.id.loggin_button);
        login taikhoan = new login();

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);

        String User = user.getText().toString();
        String Pass = pass.getText().toString();
        String chucvu;
        taikhoanDAO = new taikhoanDAO(this);
        ArrayList<login> listSP = taikhoanDAO.login1();
//        listSP.get(1).getIdtaikhoan();
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

//                ArrayList<com.example.duanmau.model.login> listsp = taikhoanDAO.login1(User,Pass);

                for (login taikhoan : listSP) {
                    String tentaikhoan = taikhoan.getGmail();
                    String matkhau = taikhoan.getPass();

                    if (user.getText().toString().equals(tentaikhoan)) {
                        if (pass.getText().toString().equals(matkhau)) {
                            // Lưu trữ thông tin tài khoản vào SharedPreferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("u", taikhoan.getGmail());
                            editor.putString("p", taikhoan.getPass());
                            editor.putInt("chucvu", taikhoan.getIdchucvu());
                            editor.apply();
                            Intent intent = new Intent(Loggin.this, Navigation_Drawer.class);
                            startActivity(intent);
//                            setContentView(R.layout.activity_navigation_drawer);
//                            // ánh xạ
//                            mDrawerLayout = findViewById(R.id.drawerlayout);
//                            // Chuyển sang fragment trang chủ
//                            Fragment_Trang_Chu fragment = new Fragment_Trang_Chu();
//                            getSupportFragmentManager()
//                                    .beginTransaction()
//                                    .replace(R.id.layout_navigation,fragment)
//                                    .commit();
//
//                            mDrawerLayout.closeDrawer(GravityCompat.START);
                            // Thoát khỏi vòng lặp

                            break;
                        } else {
                            // Nếu mật khẩu không khớp, hiển thị thông báo lỗi và thoát khỏi hàm
//                            Toast.makeText(Loggin.this, "sai tài khoản hoặc mật khẩu ", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } else {
//                        Toast.makeText(Loggin.this, "sai tài khoản hoặc mật khẩu ", Toast.LENGTH_SHORT).show();

                    }


                }











//                String user3 = "admin";
//                String password3 = "112233";
//
//
////                if (bundle != null) {
//                if (!user.getText().toString().equals("")) {
//                    if (!pass.getText().toString().equals("")) {
//
//
//                        if (user3.equals(user.getText().toString())) {
//                            if (password3.equals(pass.getText().toString())) {
//                                SharedPreferences.Editor editor = sharedPreferences.edit();
//                                editor.putString("u", user3);
//                                editor.putString("p", password3);
//                                editor.putInt("chucvu", taikhoan.getIdtaikhoan());
//                                editor.apply();
////                                Intent i2 = new Intent(Loggin.this, .class);
////                                startActivity(i2);
//
//                            }
//                        } else
//
//
//
//
//
//
////                        if (bundle != null) {
////                            String user = bundle.getString("u");
////                            String password = bundle.getString("p");
////                            boolean u = user.equals(edt1.getText().toString());
////                            boolean p = password.equals(edt2.getText().toString());
////
////                            if (u && p) {
////
////                                SharedPreferences.Editor editor = sharedPreferences.edit();
////                                editor.putString("u", user);
////                                editor.putString("p", password);
////                                editor.putBoolean("checklogin",chkluu.isChecked());
////                                editor.apply();
////                                ArrayList<lab81> lab81ArrayList = new ArrayList<>();
////
////
//////                                lab888888888888888888888888888888888
////
////                                lab811 lab811 = new lab811(context);
////                                lab811.writeUser(context,"uset.txt",
////                                        new lab81(user,password));
////
////                                Toast.makeText(login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
////                                Toast.makeText(login.this, "Lưu thành công ", Toast.LENGTH_SHORT).show();
////                                Intent i = new Intent(login.this, MainActivity.class);
////                                startActivity(i);
////
////                            } else {
////                                Toast.makeText(login.this, "sai tài khoản hoặc mật khẩu ", Toast.LENGTH_SHORT).show();
////                                return;
////                            }
////                        } else
//
//
//
//                        if (User.equals("n")){
//                                if (Pass.equals("n")){
//
//                                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                                    editor.putString("u", User);
//                                    editor.putString("p", Pass);
//                                    editor.putInt("chucvu", taikhoan.getIdtaikhoan());
//                                    editor.apply();
////                                    Fragment_Trang_Chu fragment = new Fragment_Trang_Chu();
////                                    getSupportFragmentManager()
////                                            .beginTransaction()
////                                            .replace(R.id.layout_navigation,fragment)
////                                            .commit();
////
////                                    mDrawerLayout.closeDrawer(GravityCompat.START);
//
//
//                                }else {
//                                Toast.makeText(Loggin.this, "sai tài khoản hoặc mật khẩu ", Toast.LENGTH_SHORT).show();
//                                return;
//                           }
//                        } Toast.makeText(Loggin.this, "sai tài khoản "+taikhoan.getGmail(), Toast.LENGTH_SHORT).show();
//                        return;
//
//                    }
//                    else {
//                        Toast.makeText(Loggin.this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                } else {
//
//                    Toast.makeText(Loggin.this, "vui lòng nhập gmail", Toast.LENGTH_SHORT).show();
//                    return;
//                }







            }
        });
    }

//    public ArrayList<login> login1(String user, String pass) {
//        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
//        ArrayList<login> listSP = new ArrayList<>();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM TAI_KHOAN WHERE gmail = ? AND matkhau = ?",new String[]{user, pass});
//
//        if (cursor.moveToFirst()) {
//            // Nếu cursor chứa dữ liệu, lấy ra thông tin của tài khoản
//            do {
//                listSP.add(new login(cursor.getInt(0),cursor.getInt(1), cursor.getString(2), cursor.getString(3)) );
//            }while (cursor.moveToNext());
//        }
//        // Đóng cursor và cơ sở dữ liệu
//        cursor.close();
//        sqLiteDatabase.close();
//        return listSP;
//    }
}