package com.example.iQLDT;

public class item {
    private String url, tieude, thoigian, diadiem;

    public item(String url, String tieude, String thoigian, String diadiem) {
        this.url = url;
        this.tieude = tieude;
        this.thoigian = thoigian;
        this.diadiem = diadiem;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getDiadiem() {
        return diadiem;
    }

    public void setDiadiem(String diadiem) {
        this.diadiem = diadiem;
    }
}
