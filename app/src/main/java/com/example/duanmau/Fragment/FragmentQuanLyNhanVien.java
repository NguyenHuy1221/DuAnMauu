package com.example.duanmau.Fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.duanmau.Adapter.NhanVienAdapter;
import com.example.duanmau.Adapter.taiKhoanAdapter;
import com.example.duanmau.DAO.NhanVienDao;
import com.example.duanmau.DAO.sanPhamDAO;
import com.example.duanmau.DAO.taikhoanDAO;
import com.example.duanmau.R;
import com.example.duanmau.model.NhanVien;
import com.example.duanmau.model.ThemNhanVien;
import com.example.duanmau.model.sanPham;
import com.example.duanmau.model.taiKhoan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentQuanLyNhanVien#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentQuanLyNhanVien extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public FragmentQuanLyNhanVien() {
        // Required empty public constructor
    }


    public static FragmentQuanLyNhanVien newInstance(String param1, String param2) {
        FragmentQuanLyNhanVien fragment = new FragmentQuanLyNhanVien();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private NhanVienDao nhanVienDao;
    private NhanVienAdapter nhanVienAdapter;
    RecyclerView recyclerView;

    private ImageView ivHinhSP;
    private String filePath = "";
    private TextView tvTrangThai;
    private String linkHinh;
    private FloatingActionButton floatAdd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quan_ly_nhan_vien, container, false);

        recyclerView = view.findViewById(R.id.recycle_qlnv);
        floatAdd = view.findViewById(R.id.floatAddNV);
        configCloudinary();
        nhanVienDao = new NhanVienDao(getContext());
        ArrayList<NhanVien> listnv = nhanVienDao.queryData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        nhanVienAdapter = new NhanVienAdapter(getContext(),listnv,nhanVienDao);
        recyclerView.setAdapter(nhanVienAdapter);

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themNV();
            }
        });
        return view;
    }
    public void themNV() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_nhanvien_them,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();


        // ánh xạ
        EditText edtten = view.findViewById(R.id.txt_tenNV);
        EditText edtsdt = view.findViewById(R.id.txt_sdtNV);
        EditText edtdiachi = view.findViewById(R.id.txt_diachiNV);
        EditText edtngayvaolam = view.findViewById(R.id.txt_ngayvaolamNV);
        EditText edtidchucvu = view.findViewById(R.id.txt_chucvuNV);
        ivHinhSP = view.findViewById(R.id.iv_hinhnv);
        tvTrangThai = view.findViewById(R.id.tv_trang_thai);


        Button btnThoat = view.findViewById(R.id.btn_Thoat);
        Button btnLuu = view.findViewById(R.id.btn_Luu);

        //
        nhanVienDao = new NhanVienDao(getContext());

        //bắt sự kiện cho nút lưu
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ten = edtten.getText().toString().trim();

                String sdt = edtsdt.getText().toString();
                String diachi = edtdiachi.getText().toString().trim();
                String ngayvaolam = edtngayvaolam.getText().toString().trim();
                String idchucvu = edtidchucvu.getText().toString().trim();
                int  trangthai = 1;

                java.util.Date currentDate = new java.util.Date();
                java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDate.getTime());
                if (ngayvaolam.equals("")){
                    ngayvaolam = String.valueOf(timestamp);
                }



                String regexTen = "[^\\d]{1,}";
                String regexidchucvu = "^[12]+$";
                String regexSodienthoai = "^0\\d{9}$";


                if(ten.isEmpty() || diachi.isEmpty() ) {
                    Toast.makeText(getContext(), "Vui Lòng Nhập Đủ Dữ Liệu", Toast.LENGTH_SHORT).show();
                    return;
                }
                // kiểm tra tên
                if (ten.equals("")){
                    Toast.makeText(getContext(), "Chưa Nhập Tên nhân viên", Toast.LENGTH_SHORT).show();
                    return;
                }else if (ten.matches(regexTen)){

                }else {
                    Toast.makeText(getContext(), "Tên Không Hợp Lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                //kiểm  tra số điện thoại
                 if (sdt.matches(regexSodienthoai)) {


                }else {
                    Toast.makeText(getContext(), "Nhập Số điện thoại không Hợp Lệ ", Toast.LENGTH_SHORT).show();
                    return;
                }
                 if (idchucvu.matches(regexidchucvu)){

                 } else {
                     Toast.makeText(getContext(), "Nhập id chức vụ không hợp lệ ", Toast.LENGTH_SHORT).show();
                     return;
                 }
//                Integer.parseInt(giaSP)

                ThemNhanVien themNhanVien = new ThemNhanVien(ten,linkHinh,Integer.parseInt(sdt),diachi,ngayvaolam,Integer.parseInt(idchucvu),trangthai);
                boolean check = nhanVienDao.addSP(themNhanVien);
                if (check){
                    Toast.makeText(getContext(), "Thêm nhân viên Thành Công", Toast.LENGTH_SHORT).show();
                    ArrayList<NhanVien> capnhat = nhanVienDao.queryData();
                    nhanVienAdapter.updatelist(capnhat);
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Thêm nhân viên Thất Bại", Toast.LENGTH_SHORT).show();
                }


            }
        });

        // bắt sự kiện cho nút thoát
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ivHinhSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accessTheGallery();
            }
        });

        dialog.setCancelable(false);
        dialog.show();
    }



    public void accessTheGallery() {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        myLauncher.launch(i);
    }

    private ActivityResultLauncher<Intent> myLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            //get the image's file location
            filePath = getRealPathFromUri(result.getData().getData(), getActivity());

            if (result.getResultCode() == RESULT_OK) {
                try {
                    //set picked image to the mProfile
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), result.getData().getData());
                    ivHinhSP.setImageBitmap(bitmap);
                    uploadToCloudinary(filePath);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    private String getRealPathFromUri(Uri imageUri, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(imageUri, null, null, null, null);

        if (cursor == null) {
            return imageUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }


    HashMap<String, String> config = new HashMap<>();

    private void configCloudinary() {
        config.put("cloud_name", "db6fm3nna");
        config.put("api_key", "275634911119596");
        config.put("api_secret", "hInLl-uSG0iim9jDRjGjT-huhv0");
        MediaManager.init(getActivity(), config);
    }

    private void uploadToCloudinary(String filePath) {
        Log.d("A", "sign up uploadToCloudinary- ");
        MediaManager.get().upload(filePath).callback(new UploadCallback() {
            @Override
            public void onStart(String requestId) {
                tvTrangThai.setText("Bắt đầu upload");
            }

            @Override
            public void onProgress(String requestId, long bytes, long totalBytes) {
                tvTrangThai.setText("Đang upload... ");
            }

            @Override
            public void onSuccess(String requestId, Map resultData) {
                tvTrangThai.setText("Thành công: " + resultData.get("url").toString());
                linkHinh = resultData.get("url").toString();
            }

            @Override
            public void onError(String requestId, ErrorInfo error) {
                tvTrangThai.setText("Lỗi " + error.getDescription());
            }

            @Override
            public void onReschedule(String requestId, ErrorInfo error) {
                tvTrangThai.setText("Reshedule " + error.getDescription());
            }
        }).dispatch();
    }



}