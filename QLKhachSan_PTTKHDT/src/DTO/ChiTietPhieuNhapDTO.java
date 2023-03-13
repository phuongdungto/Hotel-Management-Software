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
public class ChiTietPhieuNhapDTO {
    public String MaPNH;
    public String MaHang;
    public int DonGia;
    public int SL;
    public int ThanhTien;

    public ChiTietPhieuNhapDTO(String MaPNH, String MaHang, int DonGia, int SL, int ThanhTien) {
        this.MaPNH = MaPNH;
        this.MaHang = MaHang;
        this.DonGia = DonGia;
        this.SL = SL;
        this.ThanhTien = ThanhTien;
    }
    
    public ChiTietPhieuNhapDTO() {
        this.MaPNH = null;
        this.MaHang = null;
        this.DonGia = 0;
        this.SL = 0;
        this.ThanhTien = 0;
    }

    public String getMaPNH() {
        return MaPNH;
    }

    public void setMaPNH(String MaPNH) {
        this.MaPNH = MaPNH;
    }

    public String getMaHang() {
        return MaHang;
    }

    public void setMaHang(String MaHang) {
        this.MaHang = MaHang;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int DonGia) {
        this.DonGia = DonGia;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
    
}
