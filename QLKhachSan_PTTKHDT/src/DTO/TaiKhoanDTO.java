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
public class TaiKhoanDTO {
    public String MaTK;
    public String MaNV;
    public String MaQuyen;
    public String Username;
    public String Password;
    public int TinhTrang;

    public TaiKhoanDTO(String MaTK, String MaNV, String MaQuyen, String Username, String Password, int TinhTrang) {
        this.MaTK = MaTK;
        this.MaNV = MaNV;
        this.MaQuyen = MaQuyen;
        this.Username = Username;
        this.Password = Password;
        this.TinhTrang = TinhTrang;
    }
    
    public TaiKhoanDTO(){
        this.MaTK = null;
        this.MaNV = null;
        this.MaQuyen = null;
        this.Username = null;
        this.Password = null;
        this.TinhTrang = 0;
    }
    
    public String getMaTK() {
        return MaTK;
    }

    public void setMaTK(String MaTK) {
        this.MaTK = MaTK;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getMaQuyen() {
        return MaQuyen;
    }

    public void setMaQuyen(String MaQuyen) {
        this.MaQuyen = MaQuyen;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
}
