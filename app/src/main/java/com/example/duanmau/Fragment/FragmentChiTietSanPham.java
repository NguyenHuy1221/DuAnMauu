package com.example.duanmau.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.duanmau.Adapter.GioHangAdapter;
import com.example.duanmau.DAO.GioHangDao;
import com.example.duanmau.R;
import com.example.duanmau.model.CartManager;
import com.example.duanmau.model.GioHang;
import com.example.duanmau.model.sanPham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
//import com.example.assigment_adroid2.R;


public class FragmentChiTietSanPham extends Fragment {


    public FragmentChiTietSanPham() {
        // Required empty public constructor
    }

    private ImageView imgSanPham;
    private TextView tvTen, tvGia,tvSize;
    private ImageButton btnAdd;
    private int so = 1;
    private Button bnt38;
    private Button bnt39;
    private Button bnt40;
    private Button bnt41;
    private Button lastClickedButton; // Biến để lưu trạng thái của nút cuối cùng được nhấn
    private String selectedSize = null;
    private int idgh;


    GioHangDao gioHangDao;
    private GioHangAdapter gioHangAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chi_tiet_san_pham, container, false);

        imgSanPham = view.findViewById(R.id.img_chiTiet);
        tvTen = view.findViewById(R.id.tv_tenChiTiet);
        tvGia = view.findViewById(R.id.tv_giaChiTiet);
        btnAdd = view.findViewById(R.id.btn_mua);

        gioHangDao = new GioHangDao(getContext());

        bnt38 = view.findViewById(R.id.bnt38);
        bnt39 = view.findViewById(R.id.bnt39);
        bnt40 = view.findViewById(R.id.bnt40);
        bnt41 = view.findViewById(R.id.bnt41);

        Bundle bundle = new Bundle();
        tvSize = null;

        bnt38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeButtonColor(bnt38);
                selectedSize = "Size: 38";
                updateSizeUI();
            }
        });
        bnt39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeButtonColor(bnt39);
                selectedSize = "Size: 39";
                updateSizeUI();

            }
        });
        bnt40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeButtonColor(bnt40);
                selectedSize = "Size: 40";
                updateSizeUI();


            }
        });
        bnt41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeButtonColor(bnt41);
                selectedSize = "Size: 41";
                updateSizeUI();
            }
        });





        if (getArguments() != null) {


//            String imgSanPham1 = getArguments().getString("image");
            String tvTen1 = getArguments().getString("tensp");
            int giaSanPham1 = getArguments().getInt("giasp");
            String imgUrl = getArguments().getString("image");
            Picasso.get().load(imgUrl).into(imgSanPham);

            // Định dạng số tiền
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            String formattedGia = formatter.format(giaSanPham1);

            String tvGia1 = formattedGia + " đ";


//            // Chuyển đổi giá trị int thành Drawable và hiển thị trong ImageView
//            Drawable imageDrawable = getResources().getDrawable(imgSanPham1);
//            imgSanPham.setImageDrawable(imageDrawable);
//            imgSanPham.setImageResource(Integer.parseInt(imgSanPham1));
            tvTen.setText(tvTen1);
            tvGia.setText(tvGia1);


            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogSanPham();
                }
            });
        }


        return view;
    }

    private void dialogSanPham() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_chi_tiet_giay_1, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();


        ImageView imganh = view.findViewById(R.id.anh_chitiet);
        TextView txtname = view.findViewById(R.id.ten_chitiet);
        TextView txtgia = view.findViewById(R.id.gia_chitiet);
        tvSize = view.findViewById(R.id.size_chitiet);
        Button btntru = view.findViewById(R.id.btn_Tru);
        Button btncong = view.findViewById(R.id.btn_Cong);
        TextView txtSo = view.findViewById(R.id.txtSo);
        Button btnT = view.findViewById(R.id.btnThoat);
        Button btnM = view.findViewById(R.id.btnThem);

        Bundle bundle = getArguments();
        if (bundle != null) {

            String imgUrl = bundle.getString("image");
            String tvTen1 = bundle.getString("tensp");
            int giaSanPham1 = bundle.getInt("giasp");

            // Kiểm tra xem đã chọn kích thước (size) chưa
            if (selectedSize == null) {
                Toast.makeText(getContext(), "Vui lòng chọn size", Toast.LENGTH_SHORT).show();
                return;
            }

            // Định dạng số tiền
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            String formattedGia = formatter.format(giaSanPham1);
            String tvGia1 = formattedGia + " đ";
            Picasso.get().load(imgUrl).into(imganh);
            txtname.setText(tvTen1);
            txtgia.setText(tvGia1);
            tvSize.setText(selectedSize);
        }

        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (so > 1) {
                    so--;
                    txtSo.setText(String.valueOf(so));
                }
            }
        });

        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                so++;
                txtSo.setText(String.valueOf(so));
            }
        });

        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Lấy thông tin từ Bundle
                Bundle bundle = getArguments();
                if (bundle != null) {
                    int id = idgh+1;
                    String tenSanPham = bundle.getString("tensp");
                    int giaSanPham = bundle.getInt("giasp");
                    int soLuongSanPham = Integer.parseInt(txtSo.getText().toString());
                    String imageUrl = bundle.getString("image");
                    String size = selectedSize;

                    GioHang gioHang = new GioHang(tenSanPham,giaSanPham,soLuongSanPham,imageUrl,size);
                    Log.d("HUY",tenSanPham);
                    Log.d("HUY", String.valueOf(giaSanPham));
                    Log.d("HUY", String.valueOf(soLuongSanPham));
                    Log.d("HUY",imageUrl);
                    Log.d("HUY",size);

                    boolean check = gioHangDao.ThemSP(gioHang);
                    if (check){
                        Toast.makeText(getContext(), "Thêm Sản Phẩm Thành Công", Toast.LENGTH_SHORT).show();
//                        ArrayList<GioHang> capnhat = gioHangDao.getDS();
//                        gioHangAdapter.updatelist(capnhat);
                        dialog.dismiss();
                    }else {
                        Toast.makeText(getContext(), "Thêm Sản Phẩm Thất Bại", Toast.LENGTH_SHORT).show();
                    }

                }

//                String tenSanPham = txtname.getText().toString();
//                // Lấy chuỗi giá trị có định dạng tiền  từ TextView
//                String giaSanPhamText = txtgia.getText().toString();
//                // Loại bỏ ký tự "," và "đ" từ chuỗi
//                String giaSanPhamStripped = giaSanPhamText.replaceAll("[^\\d]", "");
//
//                try {
//                    // Chuyển đổi chuỗi đã loại bỏ ký tự không phải số thành số nguyên
//                    int giaSanPham = Integer.parseInt(giaSanPhamStripped);
//                    int soLuongSanPham = Integer.parseInt(txtSo.getText().toString());
//                    int anhSanPham = getArguments().getInt("image"); // Lấy thông tin ảnh từ bundle
//
//
//                    Log.d("HUY", String.valueOf(giaSanPham));
//
//                } catch (NumberFormatException e) {
//                    e.printStackTrace();
//                }

                FragmentThanhToan thanhToanFragment = new FragmentThanhToan();

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_navigation, thanhToanFragment);
                transaction.addToBackStack(null);
                transaction.commit();

                dialog.dismiss();
            }
        });

        dialog.show();
    }


    private void changeButtonColor(Button clickedButton) {
        // Đặt màu cho nút được nhấn
        clickedButton.setBackgroundResource(R.drawable.custom_size);

        // Đặt lại màu cho nút cuối cùng được nhấn
        if (lastClickedButton != null && lastClickedButton != clickedButton) {
            lastClickedButton.setBackgroundResource(R.drawable.button_an);
        }

        // Cập nhật nút cuối cùng được nhấn
        lastClickedButton = clickedButton;
    }

    private void updateSizeUI() {
        if (selectedSize != null && tvSize != null) {
            // Hiển thị size đã chọn lên TextView
            tvSize.setText(selectedSize);
        }
    }

}