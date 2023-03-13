/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author minhd
 */
public class PhongDTO {
    public String MaPhong;
    public String MaLoai;
    public int Tang;
    public int SLNguoi;
    public int DonGia;
    public int TinhTrang;

    public PhongDTO(String MaPhong, String MaLoai, int Tang, int SLNguoi, int DonGia, int TinhTrang) {
        this.MaPhong = MaPhong;
        this.MaLoai = MaLoai;
        this.Tang = Tang;
        this.SLNguoi = SLNguoi;
        this.DonGia = DonGia;
        this.TinhTrang = TinhTrang;
    }
    
    public PhongDTO() {
        this.MaPhong = null;
        this.MaLoai = null;
        this.Tang = 0;
        this.SLNguoi = 0;
        this.DonGia = 0;
        this.TinhTrang = 0;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public int getTang() {
        return Tang;
    }

    public void setTang(int Tang) {
        this.Tang = Tang;
    }

    public int getSLNguoi() {
        return SLNguoi;
    }

    public void setSLNguoi(int SLNguoi) {
        this.SLNguoi = SLNguoi;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int DonGia) {
        this.DonGia = DonGia;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
}
