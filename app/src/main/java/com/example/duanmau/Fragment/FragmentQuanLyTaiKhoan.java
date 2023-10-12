package com.example.duanmau.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.Adapter.sanPhamAdapter;
import com.example.duanmau.Adapter.taiKhoanAdapter;
import com.example.duanmau.DAO.sanPhamDAO;
import com.example.duanmau.DAO.taikhoanDAO;
import com.example.duanmau.R;
import com.example.duanmau.model.ThemTaiKhoan;
import com.example.duanmau.model.sanPham;
import com.example.duanmau.model.taiKhoan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentQuanLyTaiKhoan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentQuanLyTaiKhoan extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentQuanLyTaiKhoan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentQuanLyTaiKhoan.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentQuanLyTaiKhoan newInstance(String param1, String param2) {
        FragmentQuanLyTaiKhoan fragment = new FragmentQuanLyTaiKhoan();
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

    private com.example.duanmau.DAO.taikhoanDAO taikhoanDAO;
    RecyclerView recyclerView;
    private com.example.duanmau.Adapter.taiKhoanAdapter taiKhoanAdapter;
    private FloatingActionButton floatAdd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quan_ly_tai_khoan, container, false);
        recyclerView = view.findViewById(R.id.recycle_qltk);
        floatAdd = view.findViewById(R.id.floatAddTK);
        configCloudinary();


        // lâấy danh sách tai khoan database
        taikhoanDAO = new taikhoanDAO(getContext());
        ArrayList<taiKhoan> listsp = taikhoanDAO.queryData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        taiKhoanAdapter = new taiKhoanAdapter(getContext(),listsp,taikhoanDAO);
        recyclerView.setAdapter(taiKhoanAdapter);
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themTK();
            }
        });
        return view;
    }


    public void themTK() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_tai_khoan, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();

        // ánh xạ
        EditText edtid = view.findViewById(R.id.them_id_tk);
        EditText edtgmail = view.findViewById(R.id.them_gmail_tk);
        EditText edtmk = view.findViewById(R.id.them_mk_tk);
        EditText edtnlmk = view.findViewById(R.id.nhap_lai_mk);
        Button btnThoat = view.findViewById(R.id.btn_Thoat);
        Button btnLuu = view.findViewById(R.id.btn_Luu);


        //
        taikhoanDAO = new taikhoanDAO(getContext());

        //bắt sự kiện cho nút lưu
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = Integer.parseInt(edtid.getText().toString().trim());
                String gmail = edtgmail.getText().toString().trim();
                String mk = edtmk.getText().toString().trim();
                String nlmk = edtnlmk.getText().toString().trim();


                if (gmail.isEmpty() || mk.isEmpty() || nlmk.isEmpty() || Integer.toString(id).isEmpty()) {
                    Toast.makeText(getContext(), "Vui Lòng Nhập Đủ Dữ Liệu", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!mk.equals(nlmk)) {
                    Toast.makeText(getContext(), "Vui Lòng Nhập Mật khẩu phải giống với nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    ThemTaiKhoan themTaiKhoan = new ThemTaiKhoan(id, gmail, mk);
                    boolean check = taikhoanDAO.addSP(themTaiKhoan);
                    if (check) {
                        Toast.makeText(getContext(), "Thêm tài khoản Thành Công", Toast.LENGTH_SHORT).show();
                        ArrayList<taiKhoan> capnhat = taikhoanDAO.queryData();
                        taiKhoanAdapter.updatelist(capnhat);
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Thêm tài khoản Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



//
        // bắt sự kiện cho nút thoát
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

//        ivHinhSP.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                accessTheGallery();
//            }
//        });

        dialog.setCancelable(false);
        dialog.show();
    }
    private void configCloudinary() {
    }
}