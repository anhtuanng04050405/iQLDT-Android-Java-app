package com.example.iQLDT;

public class item_api {
    private String id, post_tieude, post_giobatdau, post_batdau, post_gioketthuc, post_ketthuc, post_diadiem, post_noidung, post_hinhanhminhhoa;

    public item_api(String id, String post_tieude, String post_giobatdau, String post_batdau, String post_gioketthuc, String post_ketthuc, String post_diadiem, String post_noidung, String post_hinhanhminhhoa) {
        this.id = id;
        this.post_tieude = post_tieude;
        this.post_giobatdau = post_giobatdau;
        this.post_batdau = post_batdau;
        this.post_gioketthuc = post_gioketthuc;
        this.post_ketthuc = post_ketthuc;
        this.post_diadiem = post_diadiem;
        this.post_noidung = post_noidung;
        this.post_hinhanhminhhoa = post_hinhanhminhhoa;
    }

    public String getPost_giobatdau() {
        return post_giobatdau;
    }

    public void setPost_giobatdau(String post_giobatdau) {
        this.post_giobatdau = post_giobatdau;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost_tieude() {
        return post_tieude;
    }

    public void setPost_tieude(String post_tieude) {
        this.post_tieude = post_tieude;
    }

    public String getPost_batdau() {
        return post_batdau;
    }

    public void setPost_batdau(String post_batdau) {
        this.post_batdau = post_batdau;
    }

    public String getPost_gioketthuc() {
        return post_gioketthuc;
    }

    public void setPost_gioketthuc(String post_gioketthuc) {
        this.post_gioketthuc = post_gioketthuc;
    }

    public String getPost_ketthuc() {
        return post_ketthuc;
    }

    public void setPost_ketthuc(String post_ketthuc) {
        this.post_ketthuc = post_ketthuc;
    }

    public String getPost_diadiem() {
        return post_diadiem;
    }

    public void setPost_diadiem(String post_diadiem) {
        this.post_diadiem = post_diadiem;
    }

    public String getPost_noidung() {
        return post_noidung;
    }

    public void setPost_noidung(String post_noidung) {
        this.post_noidung = post_noidung;
    }

    public String getPost_hinhanhminhhoa() {
        return post_hinhanhminhhoa;
    }

    public void setPost_hinhanhminhhoa(String post_hinhanhminhhoa) {
        this.post_hinhanhminhhoa = post_hinhanhminhhoa;
    }
}
