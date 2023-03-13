/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.LoaiPhongDTO;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author T P Dung
 */
public class LoaiPhongDAO {
    public LoaiPhongDAO(){
        
    }
    public ArrayList<LoaiPhongDTO> getDanhSach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<LoaiPhongDTO> dslp=new ArrayList<>();
        String sql="select * from loai_phong where TinhTrang != 0";
        ResultSet rs = kn.getselect(sql);
        try{
            while(rs.next()){
            LoaiPhongDTO p =new LoaiPhongDTO();
            p.setMaLoai(rs.getString(1));
            p.setTenLoai(rs.getString(2));
            dslp.add(p);
            }
        }
        catch(Exception ex){
            ex.getStackTrace();
        }
        return dslp;
    }
    public void them(LoaiPhongDTO p){
        String sql="INSERT INTO `loai_phong`"
                + "(`MaLoai`, `TenLoai`,`TinhTrang`) VALUES ('"+p.getMaLoai()+"', '"+p.getTenLoai()+"',1)";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public void sua(LoaiPhongDTO p,LoaiPhongDTO p1){
        String sql="UPDATE `loai_phong` SET `TenLoai`='"+p1.getTenLoai()+"' "
        + " WHERE `Maloai`='"+p.getMaLoai()+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        System.out.println(sql);
        kn.setchange(sql);
    }
    public void xoa(LoaiPhongDTO p){
        String sql="UPDATE `loai_phong` SET `TinhTrang`=0 WHERE `MaLoai`='"+p.getMaLoai()+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
}
