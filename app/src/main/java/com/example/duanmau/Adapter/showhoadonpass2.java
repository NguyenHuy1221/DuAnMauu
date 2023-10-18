package com.example.duanmau.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.model.HoaDon;

import java.util.List;

public class showhoadonpass2 extends RecyclerView.Adapter<showhoadonpass2.viewHolder> {

    private Context context;
    private List<String> thongTinHoaDonList;

    public showhoadonpass2(Context context, List<String> thongTinHoaDonList) {
        this.context = context;
        this.thongTinHoaDonList = thongTinHoaDonList;
    }

    @NonNull
    @Override
    public showhoadonpass2.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoa_don_pass2, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull showhoadonpass2.viewHolder holder, int position) {
        String thongTinHoaDon = thongTinHoaDonList.get(position);
        holder.bind(thongTinHoaDon);
    }

    @Override
    public int getItemCount() {
        return thongTinHoaDonList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTenKhachHang, textViewSDT, textViewDiaChi, textViewNgay, textViewThongTinSanPham, textViewTongTien;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTenKhachHang = itemView.findViewById(R.id.textViewTenKhachHang);
            textViewSDT = itemView.findViewById(R.id.textViewSDT);
            textViewDiaChi = itemView.findViewById(R.id.textViewDiaChi);
            textViewNgay = itemView.findViewById(R.id.textViewNgay);
            textViewThongTinSanPham = itemView.findViewById(R.id.textViewThongTinSanPham);
            textViewTongTien = itemView.findViewById(R.id.textViewTongTien);
        }
        public void bind(String thongTinHoaDon) {
            // Phân tách thông tin hóa đơn và gán vào các TextViews
            String[] thongTinArray = thongTinHoaDon.split("\n");
            textViewTenKhachHang.setText(thongTinArray[0]);
            textViewSDT.setText(thongTinArray[1]);
            textViewDiaChi.setText(thongTinArray[2]);
            textViewNgay.setText(thongTinArray[3]);
            textViewThongTinSanPham.setText(thongTinArray[4]);
            textViewTongTien.setText(thongTinArray[5]);
        }
    }
}
