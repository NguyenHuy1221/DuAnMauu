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
import com.example.duanmau.R;
import com.example.duanmau.model.ThemTaiKhoan;
import com.example.duanmau.model.taiKhoan;

import java.util.ArrayList;

public class taiKhoanAdapter extends RecyclerView.Adapter<taiKhoanAdapter.ViewHolder> {

    private Context context;
    private ArrayList<taiKhoan> listTK;
    private com.example.duanmau.DAO.taikhoanDAO taikhoanDAO;
    private taiKhoan taiKhoan2;

    public taiKhoanAdapter(Context context, ArrayList<taiKhoan> listTK, com.example.duanmau.DAO.taikhoanDAO taikhoanDAO) {
        this.context = context;
        this.listTK = listTK;
        this.taikhoanDAO = taikhoanDAO;
    }
    public void updatelist(ArrayList<taiKhoan> newlist){
        this.listTK = newlist;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public taiKhoanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =  ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_ql_taikhoan,parent,false);
        return new taiKhoanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull taiKhoanAdapter.ViewHolder holder, int position) {

        holder.idtk.setText(Integer.toString(listTK.get(position).getIdtk()));
        holder.nametk.setText(listTK.get(position).getName());
        holder.matkhau.setText(listTK.get(position).getMatkhau());
        holder.chucvutk.setText(listTK.get(position).getChucvu());
        holder.gmail.setText(listTK.get(position).getGmail());

        // xử lí hhinhf ảnh
        Glide.with(context).load(listTK.get(position).getImageTK()).into(holder.imgTK);

        holder.suaTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuaSP(listTK.get(holder.getAbsoluteAdapterPosition()));
            }
        });

        holder.xoaTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoaSP(listTK.get(holder.getAbsoluteAdapterPosition()).getName()
                        ,listTK.get(holder.getAbsoluteAdapterPosition()).getIdtaikhoan());
            }
        });

    }

    @Override
    public int getItemCount() {
        return listTK.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idtk,nametk,chucvutk,gmail,matkhau;
        ImageView imgTK;
        private TextView suaTK;
        private TextView xoaTK;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idtk = itemView.findViewById(R.id.id_qltk);
            nametk = itemView.findViewById(R.id.ten_qltk);
            chucvutk = itemView.findViewById(R.id.cv_qltk);
            gmail = itemView.findViewById(R.id.gmail_qltk);

            matkhau = itemView.findViewById(R.id.mk_qltk);
            imgTK = itemView.findViewById(R.id.img_qltk);
            suaTK = itemView.findViewById(R.id.sua_qltk);
            xoaTK = itemView.findViewById(R.id.xoa_qltk);
        }
    }






    private void SuaSP(taiKhoan taiKhoan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua_tai_khoan,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();

        // ánh xạ
        EditText edtid = view.findViewById(R.id.them_id_tk);
        EditText edtgmail = view.findViewById(R.id.them_gmail_tk);
        EditText edtmk = view.findViewById(R.id.them_mk_tk);
        EditText edtnlmk = view.findViewById(R.id.nhap_lai_mk);
        Button btnThoat = view.findViewById(R.id.btn_Thoat);
        Button btnLuu = view.findViewById(R.id.btn_Luu);



        edtid.setText(String.valueOf(taiKhoan.getIdtk()));
        edtgmail.setText(taiKhoan.getGmail());
        edtmk.setText(taiKhoan.getMatkhau());
        edtnlmk.setText(taiKhoan.getMatkhau());



        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idtk = taiKhoan.getIdtaikhoan();
                int id = Integer.parseInt(edtid.getText().toString().trim());
                String gmail = edtgmail.getText().toString().trim();
                String mk = edtmk.getText().toString().trim();
                String nlmk = edtnlmk.getText().toString().trim();

                if (gmail.isEmpty() || mk.isEmpty() || nlmk.isEmpty() ) {
                    Toast.makeText(context, "Vui Lòng Nhập Đủ Dữ Liệu", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!mk.equals(nlmk)) {
                    Toast.makeText(context, "Vui Lòng Nhập Mật khẩu phải giống với nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    taiKhoan taiKhoan = new taiKhoan(id,null,null,gmail,mk,null,idtk);
                    boolean check = taikhoanDAO.updateSP(taiKhoan);
                    if (check) {
                        Toast.makeText(context, "sửa tài khoản thành công", Toast.LENGTH_SHORT).show();
                        ArrayList<taiKhoan> capnhat = taikhoanDAO.queryData();
                        updatelist(capnhat);
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "sửa tài khoản thất bài", Toast.LENGTH_SHORT).show();
                    }
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


    private void xoaSP(String taikhoan,int masp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có chắc muốn xóa\""+ taikhoan + "\" ");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean check = taikhoanDAO.deleteSP(String.valueOf(masp));
                if (check) {
                    Toast.makeText(context, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    ArrayList<taiKhoan> capnhat = taikhoanDAO.queryData();
                    updatelist(capnhat);
                }else {
                    Toast.makeText(context, "Xóa sản phẩm thất bại", Toast.LENGTH_SHORT).show();
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
