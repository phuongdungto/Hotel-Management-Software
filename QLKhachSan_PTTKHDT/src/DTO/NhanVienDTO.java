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
public class NhanVienDTO extends ConNguoi{
    public String MaNV;
    public int TinhTrang;
    public int TienLuong;
    public NhanVienDTO(String MaNV1,String ho,String ten,String sdt,String diachi,int gioitinh,String ngaysinh,int TinhTrang1,int TienLuong1){
        super(ho, ten, sdt, diachi, gioitinh, ngaysinh);
        this.MaNV=MaNV1;
        this.TinhTrang=TinhTrang1;
        this.TienLuong=TienLuong1;
    }
    public NhanVienDTO(){
        super();
        this.MaNV=null;
        this.TinhTrang=0;
        this.TienLuong=0;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }

    public int getTienLuong() {
        return TienLuong;
    }

    public void setTienLuong(int TienLuong) {
        this.TienLuong = TienLuong;
    }
    
}
