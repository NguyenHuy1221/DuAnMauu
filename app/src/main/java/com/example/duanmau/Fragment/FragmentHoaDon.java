package com.example.duanmau.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau.Adapter.ChiTietHoaDonAdapter;
import com.example.duanmau.DAO.ChiTietHoaDonDao;
import com.example.duanmau.R;
import com.example.duanmau.model.ChiTietHoaDon;

import java.util.ArrayList;


public class FragmentHoaDon extends Fragment {

    private RecyclerView recyclerView;
    private ChiTietHoaDonAdapter chiTietHoaDonAdapter;
    private ChiTietHoaDonDao chiTietHoaDonDao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hoa_don, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chiTietHoaDonDao = new ChiTietHoaDonDao(getContext());

        ArrayList<ChiTietHoaDon> chiTietHoaDonList = chiTietHoaDonDao.layDanhSachChiTietHoaDon();

        chiTietHoaDonAdapter = new ChiTietHoaDonAdapter(chiTietHoaDonList);
        recyclerView.setAdapter(chiTietHoaDonAdapter);

        return view;
    }
}