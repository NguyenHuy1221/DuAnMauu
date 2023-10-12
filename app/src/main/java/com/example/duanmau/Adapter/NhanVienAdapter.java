package com.example.duanmau.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duanmau.DAO.NhanVienDao;
import com.example.duanmau.DAO.sanPhamDAO;
import com.example.duanmau.R;
import com.example.duanmau.model.NhanVien;
import com.example.duanmau.model.sanPham;
import com.example.duanmau.model.taiKhoan;

import java.util.ArrayList;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.ViewHolder> {
    private Context context;
    private ArrayList<NhanVien> listNV;
    private NhanVienDao nhanVienDao;


    public NhanVienAdapter(Context context, ArrayList<NhanVien> listNV, NhanVienDao nhanVienDao) {
        this.context = context;
        this.listNV = listNV;
        this.nhanVienDao = nhanVienDao;
    }

    public void updatelist(ArrayList<NhanVien> newlist){
        this.listNV = newlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NhanVienAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =  ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_nhan_vien,parent,false);
        return new NhanVienAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhanVienAdapter.ViewHolder holder, int position) {


        holder.idnv.setText(Integer.toString(listNV.get(position).getIdnhanvien()));
        holder.sdtnv.setText(Integer.toString(listNV.get(position).getSodienthoai()));
        holder.tennv.setText(listNV.get(position).getTennhanvien());
        holder.diachinv.setText(listNV.get(position).getDiachi());
        holder.chuvunv.setText(listNV.get(position).getChucvu());
        holder.ngayvaolamnv.setText(listNV.get(position).getNgayvaolam());




        holder.suaNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaSP(listNV.get(holder.getAbsoluteAdapterPosition()));
            }
        });

        holder.xoaNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaSP(listNV.get(holder.getAbsoluteAdapterPosition()).getTennhanvien()
                        ,listNV.get(holder.getAbsoluteAdapterPosition()).getIdnhanvien());
            }
        });

    }

    @Override
    public int getItemCount() {
        return listNV.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView idnv,tennv,sdtnv,diachinv,ngayvaolamnv,chuvunv;
        ImageView imgNV;
        private TextView suaNV;
        private TextView xoaNV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idnv = itemView.findViewById(R.id.id_qlnv);
            tennv = itemView.findViewById(R.id.ten_qlnv);
            sdtnv = itemView.findViewById(R.id.sdt_qlnv);
            diachinv = itemView.findViewById(R.id.dc_qlnv);
            ngayvaolamnv = itemView.findViewById(R.id.ngayvl_qlnv);
            chuvunv = itemView.findViewById(R.id.cv_qlnv);


            imgNV = itemView.findViewById(R.id.img_qlnv);
            suaNV = itemView.findViewById(R.id.sua_qlnv);
            xoaNV = itemView.findViewById(R.id.xoa_qlnv);
        }
    }


    private void SuaSP(NhanVien nhanVien) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_nhanvien_sua,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();

        // ánh xạ


        EditText edtten = view.findViewById(R.id.txt_tenNV_sua);
        EditText edtsdt = view.findViewById(R.id.txt_sdtNV_sua);
        EditText edtdiachi = view.findViewById(R.id.txt_diachiNV_sua);
        EditText edtngayvaolam = view.findViewById(R.id.txt_ngayvaolamNV_sua);
        EditText edtidchucvu = view.findViewById(R.id.txt_chucvuNV_sua);
        ImageView imageView = view.findViewById(R.id.iv_hinhNV_sua);

        Button btnThoat = view.findViewById(R.id.btn_Thoat_sua);
        Button btnLuu = view.findViewById(R.id.btn_Luu_sua);



        edtidchucvu.setText(String.valueOf(nhanVien.getIdchucvu()));
        edtten.setText(nhanVien.getTennhanvien());

        edtsdt.setText("0"+String.valueOf(nhanVien.getSodienthoai()));
        edtdiachi.setText(nhanVien.getDiachi());
        edtngayvaolam.setText(nhanVien.getNgayvaolam());


        String imageUrl = nhanVien.getImagenhanvien();
        Glide.with(context).load(imageUrl).into(imageView);



        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idnv = nhanVien.getIdnhanvien();
                String idchucvu = edtidchucvu.getText().toString().trim();
                String ten = edtten.getText().toString().trim();


                String diachi = edtdiachi.getText().toString().trim();
                String sdt = edtsdt.getText().toString().trim();
                String ngayvaolam = edtngayvaolam.getText().toString().trim();

                java.util.Date currentDate = new java.util.Date();
                java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDate.getTime());
                if (ngayvaolam.equals("")){
                    ngayvaolam = String.valueOf(timestamp);
                }



                String regexTen = "[^\\d]{1,}";
                String regexidchucvu = "^[12]+$";
                String regexSodienthoai = "^0\\d{9}$";


                if(ten.isEmpty() || diachi.isEmpty() ) {
                    Toast.makeText(context, "Vui Lòng Nhập Đủ Dữ Liệu", Toast.LENGTH_SHORT).show();
                    return;
                }
                // kiểm tra tên
                if (ten.equals("")){
                    Toast.makeText(context, "Chưa Nhập Tên nhân viên", Toast.LENGTH_SHORT).show();
                    return;
                }else if (ten.matches(regexTen)){

                }else {
                    Toast.makeText(context, "Tên Không Hợp Lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                //kiểm  tra số điện thoại
                if (sdt.matches(regexSodienthoai)) {


                }else {
                    Toast.makeText(context, "Nhập Số điện thoại không Hợp Lệ ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (idchucvu.matches(regexidchucvu)){

                } else {
                    Toast.makeText(context, "Nhập id chức vụ không hợp lệ ", Toast.LENGTH_SHORT).show();
                    return;
                }

                    NhanVien nhanVien1 = new NhanVien(idnv,null,ten,Integer.parseInt(sdt),diachi,ngayvaolam,Integer.parseInt(idchucvu),1,null);
                    boolean check = nhanVienDao.updateNV(nhanVien1);
                    if (check) {
                        Toast.makeText(context, "sửa tài khoản thành công", Toast.LENGTH_SHORT).show();
                        ArrayList<NhanVien> capnhat = nhanVienDao.queryData();
                        updatelist(capnhat);
                        dialog.dismiss();

                }
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setCancelable(false);
        dialog.show();
    }


    private void xoaSP(String Nhanvien,int manv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có chắc muốn xóa\""+ Nhanvien + "\" ");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean check = nhanVienDao.deleteSP(String.valueOf(manv));
                if (check) {
                    Toast.makeText(context, "Xóa Nhân viên thành công", Toast.LENGTH_SHORT).show();
                    ArrayList<NhanVien> capnhat = nhanVienDao.queryData();
                    updatelist(capnhat);
                }else {
                    Toast.makeText(context, "Xóa Nhân viên thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog =builder.create();
        dialog.show();

    }
}
