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
public class KhuyenMaiDichVuDTO {
    public String MaKMDV;
    public String Ten;
    public String NgayBD;
    public String NgayKT;
    public int TinhTrang;

    public KhuyenMaiDichVuDTO(String MaKMDV, String Ten, String NgayBD, String NgayKT, int TinhTrang) {
        this.MaKMDV = MaKMDV;
        this.Ten = Ten;
        this.NgayBD = NgayBD;
        this.NgayKT = NgayKT;
        this.TinhTrang = TinhTrang;
    }
    
    public KhuyenMaiDichVuDTO() {
        this.MaKMDV = null;
        this.Ten = null;
        this.NgayBD = null;
        this.NgayKT = null;
        this.TinhTrang = 0;
    }

    public String getMaKMDV() {
        return MaKMDV;
    }

    public void setMaKMDV(String MaKMDV) {
        this.MaKMDV = MaKMDV;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String Ten) {
        this.Ten = Ten;
    }

    public String getNgayBD() {
        return NgayBD;
    }

    public void setNgayBD(String NgayBD) {
        this.NgayBD = NgayBD;
    }

    public String getNgayKT() {
        return NgayKT;
    }

    public void setNgayKT(String NgayKT) {
        this.NgayKT = NgayKT;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
}
