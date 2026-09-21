package com.example.iQLDT;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import me.relex.circleindicator.CircleIndicator3;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class trangchu extends Fragment {
    private TextView tvTrangThai;
    private ViewPager2 viewPager2;
    private CircleIndicator3 indicator;
    private item_adapter adapter;
    private List<item> itemList;
    private List<item_api> apiItemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);

        // 1. Ánh xạ View
        tvTrangThai = view.findViewById(R.id.trangthai);
        viewPager2 = view.findViewById(R.id.viewPager2);
        indicator = view.findViewById(R.id.indicator);

        // 2. Khởi tạo danh sách rỗng
        itemList = new ArrayList<>();
        apiItemList = new ArrayList<>();

        // 3. Khởi tạo Adapter trước (sẽ cập nhật khi có dữ liệu API)
        adapter = new item_adapter(requireContext(), itemList);
        viewPager2.setAdapter(adapter);

        // 4. Kết nối Indicator với ViewPager2
        indicator.setViewPager(viewPager2);

        // 5. Đăng ký lắng nghe sự kiện chuyển trang để cập nhật trạng thái sự kiện
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateEventStatus(position);
            }
        });

        // 6. Gọi API lấy danh sách sự kiện
        loadDataFromApi();

        return view;
    }

    private void loadDataFromApi() {
        RetrofitClient.getApiService().getPosts().enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<item_api> apiList = response.body().getData();
                    if (apiList != null && !apiList.isEmpty()) {
                        apiItemList.clear();
                        apiItemList.addAll(apiList);

                        itemList.clear();
                        for (item_api apiItem : apiList) {
                            String url      = apiItem.getPost_hinhanhminhhoa();
                            String tieude   = apiItem.getPost_tieude();
                            String gioKetThuc = (apiItem.getPost_gioketthuc() != null && !apiItem.getPost_gioketthuc().trim().isEmpty())
                                    ? apiItem.getPost_gioketthuc() : apiItem.getPost_giobatdau();
                            String thoigian = apiItem.getPost_giobatdau() + " " + apiItem.getPost_batdau() + " - " + gioKetThuc + " "  + apiItem.getPost_ketthuc();
                            String diadiem  = apiItem.getPost_diadiem();

                            itemList.add(new item(url, tieude, thoigian, diadiem));
                        }
                        adapter.notifyDataSetChanged();
                        indicator.setViewPager(viewPager2);

                        // Cập nhật trạng thái cho sự kiện đầu tiên
                        updateEventStatus(viewPager2.getCurrentItem());
                    }

                    adapter.setOnItemClickListener(new item_adapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(item item, int position) {
                            String tieuDe = item.getTieude();
                            Toast.makeText(requireContext(), "Bạn vừa bấm vào item: " + position + " - " + tieuDe, Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {
                    Log.e("trangchu", "API lỗi: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.e("trangchu", "Gọi API thất bại: " + t.getMessage());
            }
        });
    }

    private void updateEventStatus(int position) {
        if (tvTrangThai == null || apiItemList == null || position < 0 || position >= apiItemList.size()) {
            return;
        }

        item_api currentApiItem = apiItemList.get(position);

        String gioBatDau = currentApiItem.getPost_giobatdau();
        String ngayBatDau = currentApiItem.getPost_batdau();
        String gioKetThuc = (currentApiItem.getPost_gioketthuc() != null && !currentApiItem.getPost_gioketthuc().trim().isEmpty())
                ? currentApiItem.getPost_gioketthuc() : currentApiItem.getPost_giobatdau();
        String ngayKetThuc = currentApiItem.getPost_ketthuc();

        Date startDate = parseDateTimeGMT7(gioBatDau, ngayBatDau);
        Date endDate = parseDateTimeGMT7(gioKetThuc, ngayKetThuc);

        // Lấy thời gian thực hiện tại theo múi giờ GMT+7
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+7"));
        Date now = calendar.getTime();

        if (endDate != null && now.after(endDate)) {
                    // Hết thời gian -> Đã kết thúc và màu đỏ
                    tvTrangThai.setText("- Đã kết thúc -");
                    if (isAdded()) {
                        tvTrangThai.setTextColor(ContextCompat.getColor(requireContext(), R.color.status_ended));
                    } else {
                        tvTrangThai.setTextColor(Color.parseColor("#E53935"));
            }
        }
        else if (startDate != null && now.before(startDate)) {
            // Chưa đến thời gian -> Sắp diễn ra
            tvTrangThai.setText("- Sắp diễn ra -");
            if (isAdded()) {
                tvTrangThai.setTextColor(ContextCompat.getColor(requireContext(), R.color.status_upcoming));
            } else {
                tvTrangThai.setTextColor(Color.parseColor("#FF9800"));
            }
        }
        else {
            // Trong thời gian diễn ra -> Đang diễn ra và màu xanh lá cây
            tvTrangThai.setText("- Đang diễn ra -");
            if (isAdded()) {
                tvTrangThai.setTextColor(ContextCompat.getColor(requireContext(), R.color.status_ongoing));
            } else {
                tvTrangThai.setTextColor(Color.parseColor("#2E7D32"));
            }
        }
    }

    private Date parseDateTimeGMT7(String timeStr, String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd", Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
            return sdf.parse(timeStr + " " + dateStr);
        } catch (Exception e) {
            return null;
        }
    }
}