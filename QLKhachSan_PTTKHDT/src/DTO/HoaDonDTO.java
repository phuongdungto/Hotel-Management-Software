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
public class HoaDonDTO {
    public String MaHD;
    public String MaPDP;
    public String MaNV;
    public String NgayLapHD;
    public int TongTienThue;
    public int TongTienDV;
    public int Thue;
    public int TongCong;
    public int TongTienKM;
    public int TongPhaiTra;
    public int TinhTrang;

    public HoaDonDTO(String MaHD, String MaPDP, String MaNV, String NgayLapHD, int TongTienThue, int TongTienDV, int Thue, int TongCong, int TongTienKM, int TongPhaiTra, int TinhTrang) {
        this.MaHD = MaHD;
        this.MaPDP = MaPDP;
        this.MaNV = MaNV;
        this.NgayLapHD = NgayLapHD;
        this.TongTienThue = TongTienThue;
        this.TongTienDV = TongTienDV;
        this.Thue = Thue;
        this.TongCong = TongCong;
        this.TongTienKM = TongTienKM;
        this.TongPhaiTra = TongPhaiTra;
        this.TinhTrang = TinhTrang;
    }
    
    public HoaDonDTO() {
        this.MaHD = null;
        this.MaPDP = null;
        this.MaNV = null;
        this.NgayLapHD = null;
        this.TongTienThue = 0;
        this.TongTienDV = 0;
        this.Thue = 0;
        this.TongCong = 0;
        this.TongTienKM = 0;
        this.TongPhaiTra = 0;
        this.TinhTrang = 0;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaPDP() {
        return MaPDP;
    }

    public void setMaPDP(String MaPDP) {
        this.MaPDP = MaPDP;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getNgayLapHD() {
        return NgayLapHD;
    }

    public void setNgayLapHD(String NgayLapHD) {
        this.NgayLapHD = NgayLapHD;
    }

    public int getTongTienThue() {
        return TongTienThue;
    }

    public void setTongTienThue(int TongTienThue) {
        this.TongTienThue = TongTienThue;
    }

    public int getTongTienDV() {
        return TongTienDV;
    }

    public void setTongTienDV(int TongTienDV) {
        this.TongTienDV = TongTienDV;
    }

    public int getThue() {
        return Thue;
    }

    public void setThue(int Thue) {
        this.Thue = Thue;
    }

    public int getTongCong() {
        return TongCong;
    }

    public void setTongCong(int TongCong) {
        this.TongCong = TongCong;
    }

    public int getTongTienKM() {
        return TongTienKM;
    }

    public void setTongTienKM(int TongTienKM) {
        this.TongTienKM = TongTienKM;
    }

    public int getTongPhaiTra() {
        return TongPhaiTra;
    }

    public void setTongPhaiTra(int TongPhaiTra) {
        this.TongPhaiTra = TongPhaiTra;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
}
