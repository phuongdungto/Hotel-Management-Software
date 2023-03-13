/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietKMDichVuDTO;
import DTO.KhuyenMaiDichVuDTO;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author T P Dung
 */
public class ChiTietKMDichVuDAO {
    public ChiTietKMDichVuDAO(){
        
    }
    public ArrayList<ChiTietKMDichVuDTO> getDanhSach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<ChiTietKMDichVuDTO> dsctkmdv=new ArrayList<>();
        String sql="select * from chi_tiet_km_dich_vu";
        ResultSet rs = kn.getselect(sql);
        try{
            while(rs.next()){
            ChiTietKMDichVuDTO p =new ChiTietKMDichVuDTO();
            p.setMaKMDV(rs.getString(1));
            p.setMaDV(rs.getString(2));
            p.setPhanTramKM(Integer.parseInt(rs.getString(3)));
            dsctkmdv.add(p);
            }
        }
        catch(Exception ex){
            ex.getStackTrace();
        }
        return dsctkmdv;
    }
    public void them(ChiTietKMDichVuDTO p){
        String sql="INSERT INTO `chi_tiet_km_dich_vu`"
                +  " VALUES ('"+p.getMaKMDV()+"', '"+p.getMaDV()+"', "+p.getPhanTramKM()+")";
        KetnoiCSDL kn=new KetnoiCSDL();
        System.out.println(sql);
        kn.setchange(sql);
    }
    public void sua(ChiTietKMDichVuDTO p,ChiTietKMDichVuDTO p1){
            String sql="UPDATE `chi_tiet_km_dich_vu` SET "
                    + "`MaDV`='"+p1.getMaDV()+"',`%KM`='"+p1.getPhanTramKM()+"' WHERE MaKMDV='"+p.getMaKMDV()+"' and MaDV='"+p.getMaDV()+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public void xoa1chitiet(ChiTietKMDichVuDTO p){
        String sql="DELETE FROM `chi_tiet_km_dich_vu` WHERE MaKMDV='"+p.getMaKMDV()+"' and MaDV='"+p.getMaDV()+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public void xoatatcachitiet(KhuyenMaiDichVuDTO p){
        String sql="DELETE FROM `chi_tiet_km_dich_vu` WHERE MaKMDV='"+p.getMaKMDV()+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
}
