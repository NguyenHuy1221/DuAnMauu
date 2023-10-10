package com.example.duanmau.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.Adapter.NhanVienAdapter;
import com.example.duanmau.DAO.NhanVienDao;
import com.example.duanmau.DAO.sanPhamDAO;
import com.example.duanmau.R;
import com.example.duanmau.model.sanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

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

    private ImageView ivHinhNV;
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
//        configCloudinary();



        return view;
    }
//    public void themNV() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_nhanvien_them,null);
//        builder.setView(view);
//        AlertDialog dialog = builder.create();
//
//        // ánh xạ
//        EditText edtten = view.findViewById(R.id.txt_tenNV);
//        EditText edtsdt = view.findViewById(R.id.txt_sdtNV);
//        EditText edtdiachi = view.findViewById(R.id.txt_diachiNV);
//        EditText edtngayvaolam = view.findViewById(R.id.txt_ngayvaolamNV);
//        EditText edtidchucvu = view.findViewById(R.id.txt_chucvuNV);
//         ivHinhNV = view.findViewById(R.id.iv_hinhnv);
//
//        Button btnThoat = view.findViewById(R.id.btn_Thoat);
//        Button btnLuu = view.findViewById(R.id.btn_Luu);
//
//        //
//        nhanVienDao = new NhanVienDao(getContext());
//
//        //bắt sự kiện cho nút lưu
//        btnLuu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String ten = edtten.getText().toString().trim();
//
//                int sdt = Integer.parseInt(edtsdt.getText().toString().trim());
//                String diachi = edtdiachi.getText().toString().trim();
//                String ngayvaolam = edtngayvaolam.getText().toString().trim();
//                int idchucvu = Integer.parseInt(edtidchucvu.getText().toString().trim());
//
//
//
//
//
//                String regexTen = "[^\\d]{1,}";
//                String regexGia = "\\d{1,}";
//
//
//                if(ten.isEmpty()&&diachi.isEmpty()&&ngayvaolam.isEmpty()) {
//                    Toast.makeText(getContext(), "Vui Lòng Nhập Đủ Dữ Liệu", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                // kiểm tra tên
//                if (ten.equals("")){
//                    Toast.makeText(getContext(), "Chưa Nhập Tên Sản Phẩm", Toast.LENGTH_SHORT).show();
//                    return;
//                }else if (ten.matches(regexTen)){
//
//                }else {
//                    Toast.makeText(getContext(), "Tên Không Hợp Lệ", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                //kiểm  tra số điện thoại
//                String regexSodienthoai = "^(0|84)(2[0-9]|3[2-9]|5[5-9]|7[0-9]|8[0-6|8|9]|9[0-4|6-9])([0-9]{7})$\n";
//                 if (sdt.matches(regexSodienthoai)) {
//                    int soluongInt = (sdt);
//                    if (soluongInt <= 0){
//                        Toast.makeText(getContext(), "Nhập Số điện thoại không Hợp Lệ ", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }else {
//                    Toast.makeText(getContext(), "Nhập Số điện thoại không Hợp Lệ ", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                // kiểm tra giá
//                if (giaSP.equals("")){
//                    Toast.makeText(getContext(), "Chưa Nhập Giá Sản Phẩm", Toast.LENGTH_SHORT).show();
//                    return;
//                } else if (giaSP.matches(regexGia)) {
//                    int soluongInt = Integer.parseInt(giaSP);
//                    if (soluongInt <= 0){
//                        Toast.makeText(getContext(), "Giá Không Hợp Lệ ", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }else {
//                    Toast.makeText(getContext(), "Giá Không Hợp Lệ ", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                sanPham mSanPham = new sanPham(tenSP, Integer.parseInt(giaSP), Integer.parseInt(soLuong),linkHinh);
//                boolean check = sanPhamDAO.addSP(mSanPham);
//                if (check){
//                    Toast.makeText(getContext(), "Thêm Sản Phẩm Thành Công", Toast.LENGTH_SHORT).show();
//                    ArrayList<sanPham> capnhat = sanPhamDAO.getDS();
//                    sanPhamAdapter.updatelist(capnhat);
//                    dialog.dismiss();
//                }else {
//                    Toast.makeText(getContext(), "Thêm Sản Phẩm Thất Bại", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });
//
//        // bắt sự kiện cho nút thoát
//        btnThoat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        ivHinhSP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                accessTheGallery();
//            }
//        });
//
//        dialog.setCancelable(false);
//        dialog.show();
//    }
}