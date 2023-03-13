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
public class ConNguoi {
    public String ho;
    public String ten;
    public String sdt;
    public String diachi;
    public int gioitinh;
    public String ngaysinh;
    public ConNguoi(String ho1, String ten1, String sdt1, String diachi1, int gioitinh1, String ngaysinh1){
        this.ho=ho1;
        this.ten=ten1;
        this.sdt=sdt1;
        this.diachi=diachi1;
        this.gioitinh=gioitinh1;
        this.ngaysinh=ngaysinh1;
    }
    public ConNguoi(){
        this.ho=null;
        this.ten=null;
        this.sdt=null;
        this.diachi=null;
        this.gioitinh=0;
        this.ngaysinh=null;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
    
}
