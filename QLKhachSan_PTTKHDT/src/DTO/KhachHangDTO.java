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
public class KhachHangDTO extends ConNguoi{
    public String MaKH;
    public int TinhTrang;
    public KhachHangDTO(String MaKH1,String ho,String ten,String sdt,String diachi,int gioitinh,String ngaysinh,int TinhTrang1){
        super(ho, ten, sdt, diachi, gioitinh, ngaysinh);
        this.MaKH=MaKH1;
        this.TinhTrang=TinhTrang1;
    }
    public KhachHangDTO(){
        super();
        this.MaKH=null;
        this.TinhTrang=0;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
}
