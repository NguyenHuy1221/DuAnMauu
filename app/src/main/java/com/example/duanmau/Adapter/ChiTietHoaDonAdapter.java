package com.example.duanmau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.model.ChiTietHoaDon;

import java.util.List;

public class ChiTietHoaDonAdapter extends RecyclerView.Adapter<ChiTietHoaDonAdapter.ViewHolder> {
    private List<ChiTietHoaDon> chiTietHoaDonList;

    public ChiTietHoaDonAdapter(List<ChiTietHoaDon> chiTietHoaDonList) {
        this.chiTietHoaDonList = chiTietHoaDonList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoa_don, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChiTietHoaDon chiTietHoaDon = chiTietHoaDonList.get(position);

        holder.txtTenKH.setText("Tên Khách hàng : "+chiTietHoaDon.getKhachHang().getTenkhachhang());
        holder.txtSDT.setText("Số điện thoại : "+chiTietHoaDon.getKhachHang().getSdt());
        holder.txtDC.setText("Địa chỉ : "+chiTietHoaDon.getKhachHang().getDiachi());
        holder.txtNgay.setText("Ngày : " +chiTietHoaDon.getHoaDon().getNgay());

        holder.txtTenSanPham.setText("Tên sản phẩm : "+chiTietHoaDon.getSanPham().getTensp());
        holder.txtSoLuong.setText("Số lượng :" +chiTietHoaDon.getSoluong());
        holder.txtDonGia.setText("Đơn giá : "+chiTietHoaDon.getDongia());
        holder.txtTongTien.setText("Tổng tiền : "+chiTietHoaDon.getSoluong() * chiTietHoaDon.getDongia());

    }

    @Override
    public int getItemCount() {
        return chiTietHoaDonList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenKH,txtSDT,txtDC,txtNgay;
        TextView txtTenSanPham, txtSoLuong, txtDonGia, txtTongTien;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTenKH = itemView.findViewById(R.id.txtTenKhachHang);
            txtSDT = itemView.findViewById(R.id.txtsdt);
            txtDC = itemView.findViewById(R.id.txtdiachi);
            txtNgay = itemView.findViewById(R.id.txtNgay);
            txtTenSanPham = itemView.findViewById(R.id.txtTenSanPham);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            txtDonGia = itemView.findViewById(R.id.txtDonGia);
            txtTongTien = itemView.findViewById(R.id.txtTongTien);
        }
    }
}

