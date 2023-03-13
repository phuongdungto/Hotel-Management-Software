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
public class NhaCungCapDTO {
    public String MaNCC;
    public String TenNCC;
    public String DiaChi;
    public String sdt;
    public int TinhTrang;

    public NhaCungCapDTO(String MaNCC, String TenNCC, String DiaChi, String sdt, int TinhTrang) {
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
        this.DiaChi = DiaChi;
        this.sdt = sdt;
        this.TinhTrang = TinhTrang;
    }
    
    public NhaCungCapDTO() {
        this.MaNCC = null;
        this.TenNCC = null;
        this.DiaChi = null;
        this.sdt = null;
        this.TinhTrang = 0;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
}
