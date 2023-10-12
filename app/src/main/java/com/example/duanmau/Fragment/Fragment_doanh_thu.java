package com.example.duanmau.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duanmau.DAO.ChiTietHoaDonDao;
import com.example.duanmau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_doanh_thu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_doanh_thu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_doanh_thu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_doanh_thu.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_doanh_thu newInstance(String param1, String param2) {
        Fragment_doanh_thu fragment = new Fragment_doanh_thu();
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
        tvNgay.setText("Hôm nay:   " + hoaDonChiTietDAO.getDoanhThuTheoNgay());
//        tvThang.setText("Tháng này: " + hoaDonChiTietDAO.getDoanhThuTheoThang());
//        tvNam.setText("Năm này:   " + hoaDonChiTietDAO.getDoanhThuTheoNam());

        return view;

    }
}