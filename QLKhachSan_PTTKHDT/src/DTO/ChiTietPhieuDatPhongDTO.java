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
public class ChiTietPhieuDatPhongDTO {
    public String MaPDP;
    public String MaPhong;
    public String MaKMPhong;
    public int SLNguoi;
    public int DonGia;
    public int TienKM;

    public ChiTietPhieuDatPhongDTO(String MaPDP, String MaPhong, String MaKMPhong, int SLNguoi, int DonGia, int TienKM) {
        this.MaPDP = MaPDP;
        this.MaPhong = MaPhong;
        this.MaKMPhong = MaKMPhong;
        this.SLNguoi = SLNguoi;
        this.DonGia = DonGia;
        this.TienKM = TienKM;
    }
    public ChiTietPhieuDatPhongDTO() {
        this.MaPDP = null;
        this.MaPhong = null;
        this.MaKMPhong = null;
        this.SLNguoi = 0;
        this.DonGia = 0;
        this.TienKM = 0;
    }

    public String getMaPDP() {
        return MaPDP;
    }

    public void setMaPDP(String MaPDP) {
        this.MaPDP = MaPDP;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public String getMaKMPhong() {
        return MaKMPhong;
    }

    public void setMaKMPhong(String MaKMPhong) {
        this.MaKMPhong = MaKMPhong;
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

    public int getTienKM() {
        return TienKM;
    }

    public void setTienKM(int TienKM) {
        this.TienKM = TienKM;
    }
    
}
