package com.example.duanmau.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duanmau.DAO.ChiTietHoaDonDao;
import com.example.duanmau.R;

import java.text.DecimalFormat;


public class Fragment_doanh_thu extends Fragment {


    public Fragment_doanh_thu() {
        // Required empty public constructor
    }


    TextView tvNgay, tvThang, tvNam;
    ChiTietHoaDonDao hoaDonChiTietDAO;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);

        tvNgay = (TextView) view.findViewById(R.id.tvThongKeNgay);
        tvThang = (TextView) view.findViewById(R.id.tvThongKeThang);
        tvNam = (TextView) view.findViewById(R.id.tvThongKeNam);
        hoaDonChiTietDAO = new ChiTietHoaDonDao(getContext());
        // Lấy doanh thu từ DAO
        double doanhThu = hoaDonChiTietDAO.getDoanhThuTheoNgay();

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.##");
        String doanhThuFormatted = decimalFormat.format(doanhThu);
        tvNgay.setText("Hôm nay: " + doanhThuFormatted);


        return view;

    }
}