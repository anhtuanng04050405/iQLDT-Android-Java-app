package com.example.iQLDT;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class item_api_adapter extends ArrayAdapter<item_api> {
    private Context context;
    private int resource;
    private List<item_api> objects;
    public item_api_adapter(@NonNull Context context, int resource, @NonNull List<item_api> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }

    private static class ViewHolder{
        TextView id, post_tieude, post_giobatdau, post_batdau, post_gioketthuc, post_ketthuc, post_diadiem, post_noidung, post_hinhanhminhhoa;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if(convertView==null){
            // Nạp giao diện
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);

            holder = new ViewHolder();

            // Ánh xạ
            holder.id = convertView.findViewById(R.id.id);
            holder.post_tieude = convertView.findViewById(R.id.post_tieude);
            holder.post_giobatdau = convertView.findViewById(R.id.post_giobatdau);
            holder.post_batdau = convertView.findViewById(R.id.post_batdau);
            holder.post_gioketthuc = convertView.findViewById(R.id.post_gioketthuc);
            holder.post_ketthuc = convertView.findViewById(R.id.post_ketthuc);
            holder.post_diadiem = convertView.findViewById(R.id.post_diadiem);
            holder.post_noidung = convertView.findViewById(R.id.post_noidung);
            holder.post_hinhanhminhhoa = convertView.findViewById(R.id.post_hinhanhminhhoa);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        //Lấy dữ liệu
        item_api item = objects.get(position);
        //Đổ dữ liệu
        holder.id.setText(item.getId());
        holder.post_tieude.setText(item.getPost_tieude());
        holder.post_giobatdau.setText(item.getPost_giobatdau());
        holder.post_batdau.setText(item.getPost_batdau());
        holder.post_gioketthuc.setText(item.getPost_gioketthuc());
        holder.post_ketthuc.setText(item.getPost_ketthuc());
        holder.post_diadiem.setText(item.getPost_diadiem());
        holder.post_noidung.setText(item.getPost_noidung());
        holder.post_hinhanhminhhoa.setText(item.getPost_hinhanhminhhoa());

        return convertView;
    }
}