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
public class QuyenTaiKhoanDTO {
    public String MaQuyen;
    public String TenQuyen;

    public QuyenTaiKhoanDTO(String MaQuyen, String TenQuyen) {
        this.MaQuyen = MaQuyen;
        this.TenQuyen = TenQuyen;
    }
    
    public QuyenTaiKhoanDTO() {
        this.MaQuyen = null;
        this.TenQuyen = null;
    }

    public String getMaQuyen() {
        return MaQuyen;
    }

    public void setMaQuyen(String MaQuyen) {
        this.MaQuyen = MaQuyen;
    }

    public String getTenQuyen() {
        return TenQuyen;
    }

    public void setTenQuyen(String TenQuyen) {
        this.TenQuyen = TenQuyen;
    }
    
}
