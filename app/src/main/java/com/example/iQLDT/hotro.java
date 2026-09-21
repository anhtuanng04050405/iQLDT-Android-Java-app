package com.example.iQLDT;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class hotro extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotro, container, false);

        view.findViewById(R.id.btnDiemRenLuyen).setOnClickListener(v ->
                openWebUrl("https://www.facebook.com/groups/DiemRenLuyenHUST/"));

        view.findViewById(R.id.btnTimDoThatLac).setOnClickListener(v ->
                openWebUrl("https://www.facebook.com/groups/DiemRenLuyenHUST/"));

        view.findViewById(R.id.btnIctsv).setOnClickListener(v ->
                openWebUrl("https://www.facebook.com/groups/DiemRenLuyenHUST/"));

        view.findViewById(R.id.btnBanCTSV).setOnClickListener(v ->
                openWebUrl("https://www.facebook.com/groups/DiemRenLuyenHUST/"));

        view.findViewById(R.id.btnTrangCTSV).setOnClickListener(v ->
                openWebUrl("https://www.facebook.com/groups/DiemRenLuyenHUST/"));

        view.findViewById(R.id.btnCongThongTinDaoTao).setOnClickListener(v ->
                openWebUrl("https://www.facebook.com/groups/DiemRenLuyenHUST/"));


        view.findViewById(R.id.btnQuytacungxu).setOnClickListener(v ->
                openWebUrl("https://www.facebook.com/groups/DiemRenLuyenHUST/"));

        view.findViewById(R.id.btnSotaysinhvien).setOnClickListener(v ->
                openWebUrl("https://www.facebook.com/groups/DiemRenLuyenHUST/"));


        return view;
    }

    private void openWebUrl(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
        catch (Exception e) {
            if (getContext() != null) {
                Toast.makeText(getContext(), "Không thể mở liên kết: " + url, Toast.LENGTH_SHORT).show();
            }
        }
    }
}