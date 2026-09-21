package com.example.iQLDT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class hienthibaiviet extends AppCompatActivity {
    ImageButton back;
    TextView tieude, thoigian, diadiem, noidung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hienthibaiviet);

        back = findViewById(R.id.back);
        tieude = findViewById(R.id.tieude);
        thoigian = findViewById(R.id.thoigian);
        diadiem = findViewById(R.id.diadiem);
        noidung = findViewById(R.id.noidung);

        Intent hienthi = getIntent();

        if (hienthi != null) {
            if (hienthi.getStringExtra("tieude") != null) {
                tieude.setText(hienthi.getStringExtra("tieude"));
            }
            if (hienthi.getStringExtra("thoigian") != null) {
                thoigian.setText(hienthi.getStringExtra("thoigian"));
            }
            if (hienthi.getStringExtra("diadiem") != null) {
                diadiem.setText(hienthi.getStringExtra("diadiem"));
            }
            if (hienthi.getStringExtra("noidung") != null) {
                noidung.setText(hienthi.getStringExtra("noidung"));
            }
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}