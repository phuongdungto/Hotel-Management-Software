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
public class HangHoaDTO {
    public String MaHang;
    public String TenHang;
    public String LoaiHang;
    public int SL;
    public int DonGia;
    public int TinhTrang;

    public HangHoaDTO(String MaHang, String TenHang, String LoaiHang, int SL,int DonGia ,int TinhTrang) {
        this.MaHang = MaHang;
        this.TenHang = TenHang;
        this.LoaiHang = LoaiHang;
        this.SL = SL;
        this.DonGia=DonGia;
        this.TinhTrang = TinhTrang;
    }
    
    public HangHoaDTO() {
        this.MaHang = null;
        this.TenHang = null;
        this.LoaiHang = null;
        this.SL = 0;
        this.DonGia =0;
        this.TinhTrang = 0;
    }

    public String getMaHang() {
        return MaHang;
    }

    public void setMaHang(String MaHang) {
        this.MaHang = MaHang;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String TenHang) {
        this.TenHang = TenHang;
    }

    public String getLoaiHang() {
        return LoaiHang;
    }

    public void setLoaiHang(String LoaiHang) {
        this.LoaiHang = LoaiHang;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int SL) {
        this.SL = SL;
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
