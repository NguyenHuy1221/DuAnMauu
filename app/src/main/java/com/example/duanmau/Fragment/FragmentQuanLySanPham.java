package com.example.duanmau.Fragment;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.duanmau.Adapter.sanPhamAdapter;
import com.example.duanmau.DAO.sanPhamDAO;
import com.example.duanmau.R;
import com.example.duanmau.model.sanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FragmentQuanLySanPham extends Fragment {

    private sanPhamDAO sanPhamDAO;
    RecyclerView recyclerView;
    private sanPhamAdapter sanPhamAdapter;
    private FloatingActionButton floatAdd;

    private ImageView ivHinhSP;
    private String filePath = "";
    private TextView tvTrangThai;
    private String linkHinh;
    private static boolean mediaManagerInitialized = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quan_ly_san_pham, container, false);

        //ánh xạ
        recyclerView = view.findViewById(R.id.recycle_qlsp);
        floatAdd = view.findViewById(R.id.floatAdd);

        if (!mediaManagerInitialized) {
            configCloudinary();
            mediaManagerInitialized = true;
        }

        // lâấy danh sách sản phẩm database
        sanPhamDAO = new sanPhamDAO(getContext());
        ArrayList<sanPham> listsp = sanPhamDAO.getDS();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        sanPhamAdapter = new sanPhamAdapter(getContext(),listsp,sanPhamDAO);
        recyclerView.setAdapter(sanPhamAdapter);
        SharedPreferences sharedPreferences2 = getContext().getSharedPreferences("data", MODE_PRIVATE);

//        int idchucvu = sharedPreferences2.getInt("chucvu",3);
//        boolean check = true;
//        if (idchucvu != 1){
//        check = false;
//
//        }
//            if (check == true){
                // thêm san phẩm
                floatAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        themSP();
                    }
                });
//            } else {
//                Toast.makeText(getContext(), "bạn không có quyền sử dụng chức năng này", Toast.LENGTH_SHORT).show();
//            }


//aaa
        return view;
    }

    public void themSP() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_san_pham,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();

        // ánh xạ
        Spinner spinner = view.findViewById(R.id.spiner);
        EditText edtSoLuong = view.findViewById(R.id.txt_soLuong);
        EditText edtTen = view.findViewById(R.id.txt_tenSP);
        EditText edtGia = view.findViewById(R.id.txt_GiaSP);
        Button btnThoat = view.findViewById(R.id.btn_Thoat);
        Button btnLuu = view.findViewById(R.id.btn_Luu);
        ivHinhSP = view.findViewById(R.id.iv_hinhsp);
        tvTrangThai = view.findViewById(R.id.tv_trang_thai);

        List<String> size = new ArrayList<>();
        size.add("38");
        size.add("39");
        size.add("40");
        size.add("41");
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, size);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(sizeAdapter);


        //
        sanPhamDAO = new sanPhamDAO(getContext());

        //bắt sự kiện cho nút lưu
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String tenSP = edtTen.getText().toString();
                String giaSP = edtGia.getText().toString();
                String soLuong = edtSoLuong.getText().toString();
                String selectedSize = spinner.getSelectedItem().toString();


                String regexTen = "[^\\d]{1,}";
                String regexGia = "\\d{1,}";
                String regexSoLuong = "\\d{1,}";

                if(soLuong.isEmpty()&&tenSP.isEmpty()&&giaSP.isEmpty()) {
                    Toast.makeText(getContext(), "Vui Lòng Nhập Đủ Dữ Liệu", Toast.LENGTH_SHORT).show();
                    return;
                }
                // kiểm tra tên
                if (tenSP.equals("")){
                    Toast.makeText(getContext(), "Chưa Nhập Tên Sản Phẩm", Toast.LENGTH_SHORT).show();
                    return;
                }else if (tenSP.matches(regexTen)){

                }else {
                    Toast.makeText(getContext(), "Tên Không Hợp Lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                //kiểm  tra số lượng
                if (soLuong.equals("")){
                    Toast.makeText(getContext(), "Chưa Nhập Số Lượng Sản Phẩm", Toast.LENGTH_SHORT).show();
                    return;
                } else if (soLuong.matches(regexSoLuong)) {
                    int soluongInt = Integer.parseInt(soLuong);
                    if (soluongInt <= 0){
                        Toast.makeText(getContext(), "Nhập Số Lượng không Hợp Lệ ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else {
                    Toast.makeText(getContext(), "Nhập Số Lượng không Hợp Lệ ", Toast.LENGTH_SHORT).show();
                    return;
                }
                // kiểm tra giá
                if (giaSP.equals("")){
                    Toast.makeText(getContext(), "Chưa Nhập Giá Sản Phẩm", Toast.LENGTH_SHORT).show();
                    return;
                } else if (giaSP.matches(regexGia)) {
                    int soluongInt = Integer.parseInt(giaSP);
                    if (soluongInt <= 0){
                        Toast.makeText(getContext(), "Giá Không Hợp Lệ ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else {
                    Toast.makeText(getContext(), "Giá Không Hợp Lệ ", Toast.LENGTH_SHORT).show();
                    return;
                }

                sanPham mSanPham = new sanPham(tenSP, Integer.parseInt(giaSP), Integer.parseInt(soLuong),linkHinh,selectedSize);
                boolean check = sanPhamDAO.addSP(mSanPham);
                    if (check){
                        Toast.makeText(getContext(), "Thêm Sản Phẩm Thành Công", Toast.LENGTH_SHORT).show();
                        ArrayList<sanPham> capnhat = sanPhamDAO.getDS();
                        sanPhamAdapter.updatelist(capnhat);
                        dialog.dismiss();
                    }else {
                        Toast.makeText(getContext(), "Thêm Sản Phẩm Thất Bại", Toast.LENGTH_SHORT).show();
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

//    private void configCloudinary() {
//        config.put("cloud_name", "db6fm3nna");
//        config.put("api_key", "275634911119596");
//        config.put("api_secret", "hInLl-uSG0iim9jDRjGjT-huhv0");
//        MediaManager.init(getActivity(), config);
//    }

    private void configCloudinary() {
        if (!mediaManagerInitialized) {
            config.put("cloud_name", "db6fm3nna");
            config.put("api_key", "275634911119596");
            config.put("api_secret", "hInLl-uSG0iim9jDRjGjT-huhv0");
            MediaManager.init(getActivity(), config);
            mediaManagerInitialized = true;
        }
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