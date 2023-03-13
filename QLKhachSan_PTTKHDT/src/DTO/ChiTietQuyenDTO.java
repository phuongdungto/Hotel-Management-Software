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
public class ChiTietQuyenDTO {
    public String MaQuyen;
    public String MaChucNang;

    public ChiTietQuyenDTO(String MaQuyen, String MaChucNang) {
        this.MaQuyen = MaQuyen;
        this.MaChucNang = MaChucNang;
    }
    
    public ChiTietQuyenDTO() {
        this.MaQuyen = null;
        this.MaChucNang = null;
    }

    public String getMaQuyen() {
        return MaQuyen;
    }

    public void setMaQuyen(String MaQuyen) {
        this.MaQuyen = MaQuyen;
    }

    public String getMaChucNang() {
        return MaChucNang;
    }

    public void setMaChucNang(String MaChucNang) {
        this.MaChucNang = MaChucNang;
    }
    
}
