/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.PhongDTO;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author T P Dung
 */
public class PhongDAO {
    ResultSet rs;
    public PhongDAO(){
        
    }
    public ArrayList<PhongDTO> getDanhSach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<PhongDTO> dsp=new ArrayList<>();
        String sql="select * from phong where TinhTrang!=0";
        rs = kn.getselect(sql);
        try{
            while(rs.next()){
                PhongDTO p =new PhongDTO();
                p.setMaPhong(rs.getString(1));
                p.setMaLoai(rs.getString(2));
                p.setTang(Integer.parseInt(rs.getString(3)));
                p.setSLNguoi(Integer.parseInt(rs.getString(4)));
                p.setDonGia(Integer.parseInt(rs.getString(5)));
                p.setTinhTrang(Integer.parseInt(rs.getString(6)));
                dsp.add(p);
            }
        }
        catch(Exception ex){
            ex.getStackTrace();
        }
        return dsp;
    }
    public void ThayDoiTinhTrangPhongSauKhiThanhToan(String map){
        String sql="UPDATE `phong` SET `TinhTrang`=3 WHERE `MaPhong`='"+map+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    //--------------------- Thêm 1 phòng ------------------------------//
     public void themPhong(PhongDTO hh ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="INSERT INTO `phong` (`MaPhong`, `MaLoai`, `Tang`, `SLNguoi`, `DonGia`, `TinhTrang`) VALUES ('"+hh.getMaPhong()+"', '"+hh.getMaLoai()+"', '"+hh.getTang()+"', '"+hh.getSLNguoi()+"', '"+hh.getDonGia()+"', '"+hh.getTinhTrang()+"')";
            kn.setchange(qry);
    }  
     
//--------------------------- xóa 1 phòng -----------------------------//
     public void xoaPhong(PhongDTO hh ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="UPDATE `phong` SET `TinhTrang`=0 WHERE `phong`.`MaPhong`='"+hh.getMaPhong()+"'";
            System.out.println(qry);
            kn.setchange(qry);
    } 
     
 //--------------------- Sửa 1 phòng ------------------------------//
     public void suaphong(PhongDTO hh ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="UPDATE `phong` SET `MaPhong`='"+hh.getMaPhong()+"',`MaLoai`='"+hh.getMaLoai()+"',`Tang`='"+hh.getTang()+"',`SLNguoi`='"+hh.getSLNguoi()+"',`DonGia`='"+hh.getDonGia()+"',`TinhTrang`="+hh.getTinhTrang()+" WHERE `phong`.`MaPhong`='"+hh.getMaPhong()+"'";
           kn.setchange(qry);
     }
//--------------------- Mã cuối ds phòng ------------------------------//     
     public int MaCuoiDS(){
        int sl=0;
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="select count(MaPhong) from phong";
            rs=kn.getselect(qry);
         try {
             while(rs.next()){
                 sl= Integer.parseInt(rs.getString(1));
             }
         } catch (SQLException ex) {
             ex.getStackTrace();
         }
        return sl;
    }    
//    public static void main(String[] args) {
//        PhongDAO p=new PhongDAO();
//        p.getDanhSach();
//    }
}
