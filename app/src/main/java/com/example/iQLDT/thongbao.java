package com.example.iQLDT;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class thongbao extends Fragment {
    private ListView lvThongBao;
    private item_api_adapter adapter;
    private List<item_api> apiItemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongbao, container, false);

        // 1. Ánh xạ ListView
        lvThongBao = view.findViewById(R.id.lvThongBao);
        apiItemList = new ArrayList<>();

        // 2. Khởi tạo Adapter
        adapter = new item_api_adapter(requireContext(), R.layout.item_api, apiItemList);
        lvThongBao.setAdapter(adapter);

        // 3. Lấy dữ liệu bài viết từ DataRepository (dùng cache)
        loadDataFromApi();

        // 4. Bắt sự kiện bấm vào từng item để chuyển sang hienthibaiviet
        lvThongBao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0 && position < apiItemList.size()) {
                    item_api item = apiItemList.get(position);

                    String gioKetThuc = (item.getPost_gioketthuc() != null && !item.getPost_gioketthuc().trim().isEmpty())
                            ? item.getPost_gioketthuc() : item.getPost_giobatdau();
                    String thoigian = item.getPost_giobatdau() + " " + item.getPost_batdau() + " - " + gioKetThuc + " " + item.getPost_ketthuc();

                    Intent hienthibaiviet = new Intent(getContext(), hienthibaiviet.class);
                    hienthibaiviet.putExtra("tieude", item.getPost_tieude());
                    hienthibaiviet.putExtra("thoigian", thoigian);
                    hienthibaiviet.putExtra("diadiem", item.getPost_diadiem());
                    hienthibaiviet.putExtra("url", item.getPost_hinhanhminhhoa());
                    hienthibaiviet.putExtra("noidung", item.getPost_noidung());
                    startActivity(hienthibaiviet);
                }
            }
        });

        return view;
    }

    private void loadDataFromApi() {
        DataRepository.getInstance().getData(new DataRepository.OnDataLoadedListener() {
            @Override
            public void onSuccess(List<item_api> data) {
                if (!isAdded()) return;
                if (data != null) {
                    apiItemList.clear();
                    apiItemList.addAll(data);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e("thongbao", errorMessage);
            }
        });
    }
}