package com.example.duanmau.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.duanmau.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class FragmentThanhToan extends Fragment {

    Button btnXacNhan;
    Button btnTiepTuc;
    private RecyclerView recyclerViewMua;

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

        nameKH = view.findViewById(R.id.edt_nameKH);
        phoneKH = view.findViewById(R.id.edt_phoneKH);
        address = view.findViewById(R.id.edt_address);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        recyclerViewMua.setLayoutManager(gridLayoutManager);
//        hoaDonAdapter = new HoaDonAdapter(getContext(),listSP,database);
//        recyclerViewMua.setAdapter(hoaDonAdapter);

        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameKH.getText().toString();
                String phone = phoneKH.getText().toString();
                String addres = address.getText().toString();

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


}