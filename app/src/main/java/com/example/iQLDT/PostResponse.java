package com.example.iQLDT;

import java.util.List;

public class PostResponse {
    private String status;
    private List<item_api> data;

    public String getStatus() {
        return status;
    }

    public List<item_api> getData() {
        return data;
    }
}