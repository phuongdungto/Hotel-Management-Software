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
public class ChiTietHoaDonDTO {
    public String MaHD;
    public String MaDV;
    public String MaKMDV;
    public int DonGia;
    public int SL;
    public int ThanhTien;
    public int TienKM;

    public ChiTietHoaDonDTO(String MaHD, String MaDV, String MaKMDV, int DonGia, int SL, int ThanhTien, int TienKM) {
        this.MaHD = MaHD;
        this.MaDV = MaDV;
        this.MaKMDV = MaKMDV;
        this.DonGia = DonGia;
        this.SL = SL;
        this.ThanhTien = ThanhTien;
        this.TienKM = TienKM;
    }
    
    public ChiTietHoaDonDTO() {
        this.MaHD = null;
        this.MaDV = null;
        this.MaKMDV = null;
        this.DonGia = 0;
        this.SL = 0;
        this.ThanhTien = 0;
        this.TienKM = 0;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaDV() {
        return MaDV;
    }

    public void setMaDV(String MaDV) {
        this.MaDV = MaDV;
    }

    public String getMaKMDV() {
        return MaKMDV;
    }

    public void setMaKMDV(String MaKMDV) {
        this.MaKMDV = MaKMDV;
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

    public int getTienKM() {
        return TienKM;
    }

    public void setTienKM(int TienKM) {
        this.TienKM = TienKM;
    }
    
}
