package com.example.duanmau.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duanmau.Adapter.GioHangAdapter;
import com.example.duanmau.DAO.GioHangDao;
import com.example.duanmau.R;
import com.example.duanmau.TotalPriceUpdateListener;
import com.example.duanmau.model.GioHang;
import com.example.duanmau.model.KhachHang;
import com.example.duanmau.model.sanPham;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FragmentThanhToan extends Fragment implements TotalPriceUpdateListener {

    TextView tongtien;
    Button btnXacNhan;
    Button btnTiepTuc;
    private RecyclerView recyclerViewMua;
    private GioHangAdapter gioHangAdapter;
    private List<GioHang> gioHangList;
    private GioHangDao gioHangDao;

    private EditText nameKH;
    private EditText phoneKH;
    private EditText address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thanh_toan, container, false);
        btnXacNhan = view.findViewById(R.id.btnXacNhanThanhToan);
        recyclerViewMua = view.findViewById(R.id.rcv_thanhtoan);
        btnTiepTuc = view.findViewById(R.id.tieptuc);
        tongtien = view.findViewById(R.id.tv_tongTien);
        nameKH = view.findViewById(R.id.edt_nameKH);
        phoneKH = view.findViewById(R.id.edt_phoneKH);
        address = view.findViewById(R.id.edt_address);

        gioHangDao = new GioHangDao(getContext());

        gioHangList = gioHangDao.getDS();
        ArrayList<GioHang> listGH = gioHangDao.getDS();
        updateTotalPrice();

        gioHangAdapter = new GioHangAdapter(getContext(),listGH,gioHangDao);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerViewMua.setLayoutManager(gridLayoutManager);
        recyclerViewMua.setAdapter(gioHangAdapter);

        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameKH.getText().toString();
                String phone = phoneKH.getText().toString();
                String addres = address.getText().toString();

                KhachHang khachHang = new KhachHang(name,phone,addres);


                Fragment_Trang_Chu fragmentTrangChu = new Fragment_Trang_Chu();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_navigation, fragmentTrangChu);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            tenSanPham = bundle.getString("tenSanPham");
//            giaSanPham = bundle.getString("giaSanPham");
//            soLuongSanPham = bundle.getInt("soLuongSanPham");
//            anhSanPham = bundle.getInt("anhSanPham");
//        }
//
//
//        TextView txtTenSanPham = view.findViewById(R.id.txtTenSanPham);
//        TextView txtGiaSanPham = view.findViewById(R.id.txtGiaSanPham);
//        TextView txtSoLuongSanPham = view.findViewById(R.id.txtSoLuongSanPham);
//        ImageView imgSanPham = view.findViewById(R.id.imgSanPham);
//
//        txtTenSanPham.setText("Tên SP: "+tenSanPham);
//        txtGiaSanPham.setText("Giá SP: "+giaSanPham);
//        txtSoLuongSanPham.setText("Số Lượng: "+soLuongSanPham);
//        imgSanPham.setImageResource(anhSanPham);
//
//        // Tính tổng tiền
//        int gia = Integer.parseInt(giaSanPham.replaceAll("[^\\d]", ""));
//        tongTien = gia * soLuongSanPham;
//
//        // Hiển thị tổng tiền
//        TextView txtTongTien = view.findViewById(R.id.txtTongTien);
//        txtTongTien.setText("Tổng tiền: " + formatTien(tongTien) + " ₫");

//        btnXacNhan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                sendNotification();
//                Intent intent = new Intent(getContext(), ThanhToanThanhCong.class);
//                startActivity(intent);
//            }
//        });

        return view;
    }


    private String formatTien(int tien) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(tien);
    }
//
//    private void sendNotification() {
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),anhSanPham);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), myAplication.CHANNEL_ID)
//                .setSmallIcon(R.drawable.huy)
//                .setContentTitle("CẢM ƠN BẠN ĐÃ QUAN TÂM VÀ ỦNG HỘ")
//                .setContentText("Bạn Đã Mua "+tenSanPham +" Với Tổng Tiền " + formatTien(tongTien) + " ₫")
//                .setStyle(new NotificationCompat.BigPictureStyle()
//                        .bigPicture(bitmap)
//                        .bigLargeIcon(null)
//                )
//                .setLargeIcon(bitmap)
//                .setColor(Color.RED)
//                .setAutoCancel(true);
//        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());
//        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
//
//            notificationManagerCompat.notify(getNotificationID(), builder.build());
//
//        }else {
//            ActivityCompat.requestPermissions(getActivity(),
//                    new String[]{android.Manifest.permission.POST_NOTIFICATIONS},7979);
//        }
//    }
//
//    private int getNotificationID() {
//        return (int) new Date().getTime();
//    }

    private void updateTotalPrice() {
        int totalPrice = 0;

        // Lặp qua danh sách sản phẩm trong giỏ hàng
        for (GioHang gioHang : gioHangList) {
            int giaSanPham = gioHang.getGiasp();
            int soLuong = gioHang.getSoluong();
            totalPrice += giaSanPham * soLuong;
        }

        // Định dạng số tiền và hiển thị trong TextView
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String formattedTotalPrice = formatter.format(totalPrice);
        tongtien.setText("Tổng tiền : " +formattedTotalPrice+" đ");
    }


    @Override
    public void onUpdateTotalPrice(int newTotalPrice) {
        updateTotalPriceTextView(newTotalPrice);
    }

    private void updateTotalPriceTextView(int newTotalPrice) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String formattedTotalPrice = formatter.format(newTotalPrice);
        tongtien.setText("Tổng tiền : " + formattedTotalPrice + " đ");
    }
}