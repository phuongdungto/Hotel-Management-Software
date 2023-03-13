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
public class PhieuDatPhongDTO {
    public String MaPDP;
    public String MaKH;
    public String MaNV;
    public int SLPhong;
    public String NgayLapPDP;
    public String NgayThue;
    public String NgayTra;
    public int TongTienThue;
    public int TongTienKM;
    public int TinhTrang;

    public PhieuDatPhongDTO(String MaPDP, String MaKH,String MaNV, int SLPhong, String NgayLapPDP, String NgayThue, String NgayTra, int TongTienThue, int TongTienKM, int TinhTrang) {
        this.MaPDP = MaPDP;
        this.MaKH = MaKH;
        this.MaNV= MaNV;
        this.SLPhong = SLPhong;
        this.NgayLapPDP = NgayLapPDP;
        this.NgayThue = NgayThue;
        this.NgayTra = NgayTra;
        this.TongTienThue = TongTienThue;
        this.TongTienKM = TongTienKM;
        this.TinhTrang = TinhTrang;
    }
    
    public PhieuDatPhongDTO() {
        this.MaPDP = null;
        this.MaKH = null;
        this.MaNV=null;
        this.SLPhong = 0;
        this.NgayLapPDP = null;
        this.NgayThue = null;
        this.NgayTra = null;
        this.TongTienThue = 0;
        this.TongTienKM = 0;
        this.TinhTrang = 0;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getMaPDP() {
        return MaPDP;
    }

    public void setMaPDP(String MaPDP) {
        this.MaPDP = MaPDP;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public int getSLPhong() {
        return SLPhong;
    }

    public void setSLPhong(int SLPhong) {
        this.SLPhong = SLPhong;
    }

    public String getNgayLapPDP() {
        return NgayLapPDP;
    }

    public void setNgayLapPDP(String NgayLapPDP) {
        this.NgayLapPDP = NgayLapPDP;
    }

    public String getNgayThue() {
        return NgayThue;
    }

    public void setNgayThue(String NgayThue) {
        this.NgayThue = NgayThue;
    }

    public String getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(String NgayTra) {
        this.NgayTra = NgayTra;
    }

    public int getTongTienThue() {
        return TongTienThue;
    }

    public void setTongTienThue(int TongTienThue) {
        this.TongTienThue = TongTienThue;
    }

    public int getTongTienKM() {
        return TongTienKM;
    }

    public void setTongTienKM(int TongTienKM) {
        this.TongTienKM = TongTienKM;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
}
