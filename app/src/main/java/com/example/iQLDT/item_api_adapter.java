package com.example.iQLDT;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

    private static class ViewHolder {
        TextView post_tieude, post_thoigian, post_noidung;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);

            holder = new ViewHolder();
            holder.post_tieude = convertView.findViewById(R.id.post_tieude);
            holder.post_thoigian = convertView.findViewById(R.id.post_thoigian);
            holder.post_noidung = convertView.findViewById(R.id.post_noidung);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        item_api item = objects.get(position);
        if (item != null) {
            holder.post_tieude.setText(item.getPost_tieude());

            String timeDisplay = item.getPost_giobatdau() + " - " + item.getPost_batdau();
            holder.post_thoigian.setText(timeDisplay);

            holder.post_noidung.setText(item.getPost_noidung());
        }

        return convertView;
    }
}