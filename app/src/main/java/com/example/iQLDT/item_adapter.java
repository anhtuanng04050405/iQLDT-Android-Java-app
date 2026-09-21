package com.example.iQLDT;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class item_adapter extends RecyclerView.Adapter<item_adapter.ItemViewHolder> {

    private Context context;
    private List<item> itemList;

    public interface OnItemClickListener {
        void onItemClick(item item, int position);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public item_adapter(Context context, List<item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        item currentItem = itemList.get(position);

        if (currentItem == null) return;

        holder.tvTitle.setText(currentItem.getTieude());
        holder.tvTime.setText(currentItem.getThoigian());
        holder.tvLocation.setText(currentItem.getDiadiem());

        Glide.with(context)
                .load(currentItem.getUrl())
                .into(holder.imgBanner);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(currentItem, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBanner;
        TextView tvTitle, tvTime, tvLocation;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBanner = itemView.findViewById(R.id.banner);
            tvTitle = itemView.findViewById(R.id.tieude);
            tvTime = itemView.findViewById(R.id.diadiem);
            tvLocation = itemView.findViewById(R.id.diadiem);
        }
    }
}