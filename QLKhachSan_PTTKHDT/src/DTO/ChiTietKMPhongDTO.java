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
public class ChiTietKMPhongDTO {
    public String MaKMPhong;
    public String MaPhong;
    public int PhanTramKM;

    public ChiTietKMPhongDTO(String MaKMPhong, String MaPhong, int PhanTramKM) {
        this.MaKMPhong = MaKMPhong;
        this.MaPhong = MaPhong;
        this.PhanTramKM = PhanTramKM;
    }
    
    public ChiTietKMPhongDTO() {
        this.MaKMPhong = null;
        this.MaPhong = null;
        this.PhanTramKM = 0;
    }

    public String getMaKMPhong() {
        return MaKMPhong;
    }

    public void setMaKMPhong(String MaKMPhong) {
        this.MaKMPhong = MaKMPhong;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public int getPhanTramKM() {
        return PhanTramKM;
    }

    public void setPhanTramKM(int PhanTramKM) {
        this.PhanTramKM = PhanTramKM;
    }
    
}
