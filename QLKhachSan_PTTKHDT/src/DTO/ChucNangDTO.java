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
public class ChucNangDTO {
    public String MaChucNang;
    public String TenChucNang;
    public String hinhanh;
    public int stt;

    public ChucNangDTO(String MaChucNang, String TenChucNang,String hinhanh,int stt) {
        this.MaChucNang = MaChucNang;
        this.TenChucNang = TenChucNang;
        this.hinhanh=hinhanh;
        this.stt=stt;
    }
    
    public ChucNangDTO() {
        this.MaChucNang = null;
        this.TenChucNang = null;
        this.hinhanh=null;
        this.stt=0;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMaChucNang() {
        return MaChucNang;
    }

    public void setMaChucNang(String MaChucNang) {
        this.MaChucNang = MaChucNang;
    }

    public String getTenChucNang() {
        return TenChucNang;
    }

    public void setTenChucNang(String TenChucNang) {
        this.TenChucNang = TenChucNang;
    }
    
}
