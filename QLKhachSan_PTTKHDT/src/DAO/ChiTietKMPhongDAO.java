/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietKMPhongDTO;
import DTO.KhuyenMaiPhongDTO;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author T P Dung
 */
public class ChiTietKMPhongDAO {
    public ChiTietKMPhongDAO(){
        
    }
    public ArrayList<ChiTietKMPhongDTO> getDanhSach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<ChiTietKMPhongDTO> dsctkmp=new ArrayList<>();
        String sql="select * from chi_tiet_km_phong";
        ResultSet rs = kn.getselect(sql);
        try{
            while(rs.next()){
            ChiTietKMPhongDTO p =new ChiTietKMPhongDTO();
            p.setMaKMPhong(rs.getString(1));
            p.setMaPhong(rs.getString(2));
            p.setPhanTramKM(Integer.parseInt(rs.getString(3)));
            dsctkmp.add(p);
            }
        }
        catch(Exception ex){
            ex.getStackTrace();
        }
        return dsctkmp;
    }
    public void them(ChiTietKMPhongDTO p){
        String sql="INSERT INTO `chi_tiet_km_phong`"
                + "(`MaKMPhong`, `MaPhong`, `%KM`) VALUES ('"+p.getMaKMPhong()+"', '"+p.getMaPhong()+"', "+p.getPhanTramKM()+")";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public void sua(ChiTietKMPhongDTO p,ChiTietKMPhongDTO p1){
        String sql="UPDATE `chi_tiet_km_phong` SET "
                    + "`MaPhong`='"+p1.getMaPhong()+"',`%KM`='"+p1.getPhanTramKM()+"' WHERE MaKMPhong='"+p.getMaKMPhong()+"' and MaPhong='"+p.getMaPhong()+"'";
        System.out.println(sql);    
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public void xoa1chitiet(ChiTietKMPhongDTO p){
        String sql="DELETE FROM `chi_tiet_km_phong` WHERE MaPhong='"+p.getMaPhong()+"' and MaKMPhong='"+p.getMaKMPhong()+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public void xoatatcachitiet(KhuyenMaiPhongDTO p){
        String sql="DELETE FROM `chi_tiet_km_phong` WHERE MaKMDV='"+p.getMaKMPhong()+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public ChiTietKMPhongDTO lay1ctkm(String s){
        KetnoiCSDL kn=new KetnoiCSDL();
        String sql="SELECT ctkmp.* FROM chi_tiet_km_phong ctkmp,  khuyen_mai_phong kmp WHERE ctkmp.MaPhong='"+s+"' and ctkmp.MaKMPhong = kmp.MaKMPhong and kmp.TinhTrang=1";
        ResultSet rs = kn.getselect(sql);
        ChiTietKMPhongDTO p =new ChiTietKMPhongDTO();
        try{
            while(rs.next()){
                p.setMaKMPhong(rs.getString(1));
                p.setMaPhong(rs.getString(2));
                p.setPhanTramKM(Integer.parseInt(rs.getString(3)));
            }
        }
        catch(Exception ex){
            ex.getStackTrace();
        }
        return p;
    }
}
