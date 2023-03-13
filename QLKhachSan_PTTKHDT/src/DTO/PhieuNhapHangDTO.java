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
public class PhieuNhapHangDTO {
    public String MaPNH;
    public String MaNV;
    public String MaNCC;
    public String NgayNhap;
    public int TongTien;
    public int TinhTrang;

    public PhieuNhapHangDTO(String MaPNH, String MaNV, String MaNCC, String NgayNhap, int TongTien, int TinhTrang) {
        this.MaPNH = MaPNH;
        this.MaNV = MaNV;
        this.MaNCC = MaNCC;
        this.NgayNhap = NgayNhap;
        this.TongTien = TongTien;
        this.TinhTrang = TinhTrang;
    }
    
    public PhieuNhapHangDTO() {
        this.MaPNH = null;
        this.MaNV = null;
        this.MaNCC = null;
        this.NgayNhap = null;
        this.TongTien = 0;
        this.TinhTrang = 0;
    }

    public String getMaPNH() {
        return MaPNH;
    }

    public void setMaPNH(String MaPNH) {
        this.MaPNH = MaPNH;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public String getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(String NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
}
