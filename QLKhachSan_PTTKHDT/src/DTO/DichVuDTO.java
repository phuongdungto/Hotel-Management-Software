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
public class DichVuDTO {
    public String MaDV;
    public String TenDV;
    public int DonGia;
    public int TinhTrang;

    public DichVuDTO(String MaDV, String TenDV, int DonGia, int TinhTrang) {
        this.MaDV = MaDV;
        this.TenDV = TenDV;
        this.DonGia = DonGia;
        this.TinhTrang = TinhTrang;
    }
    
    public DichVuDTO() {
        this.MaDV = null;
        this.TenDV = null;
        this.DonGia = 0;
        this.TinhTrang = 0;
    }

    public String getMaDV() {
        return MaDV;
    }

    public void setMaDV(String MaDV) {
        this.MaDV = MaDV;
    }

    public String getTenDV() {
        return TenDV;
    }

    public void setTenDV(String TenDV) {
        this.TenDV = TenDV;
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
