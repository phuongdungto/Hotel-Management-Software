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
public class ChiTietKMDichVuDTO {
    public String MaKMDV;
    public String MaDV;
    public int PhanTramKM;

    public ChiTietKMDichVuDTO(String MaKMDV, String MaDV, int PhanTramKM) {
        this.MaKMDV = MaKMDV;
        this.MaDV = MaDV;
        this.PhanTramKM = PhanTramKM;
    }
    
    public ChiTietKMDichVuDTO() {
        this.MaKMDV = null;
        this.MaDV = null;
        this.PhanTramKM = 0;
    }

    public String getMaKMDV() {
        return MaKMDV;
    }

    public void setMaKMDV(String MaKMDV) {
        this.MaKMDV = MaKMDV;
    }

    public String getMaDV() {
        return MaDV;
    }

    public void setMaDV(String MaDV) {
        this.MaDV = MaDV;
    }

    public int getPhanTramKM() {
        return PhanTramKM;
    }

    public void setPhanTramKM(int PhanTramKM) {
        this.PhanTramKM = PhanTramKM;
    }
    
}
