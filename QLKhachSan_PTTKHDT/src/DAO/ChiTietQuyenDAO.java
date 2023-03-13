/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietQuyenDTO;
import DTO.QuyenTaiKhoanDTO;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author minhd
 */
public class ChiTietQuyenDAO {
    public ChiTietQuyenDAO(){
        
    }
    public ArrayList<ChiTietQuyenDTO> getDanhsach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        Connection con=kn.getKetnoi();
        ArrayList<ChiTietQuyenDTO> dsctq=new ArrayList<>();
        String sql="select * from chi_tiet_quyen";
        try{
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(sql);
            while(rs.next()){
                ChiTietQuyenDTO ctq=new ChiTietQuyenDTO();
                ctq.setMaQuyen(rs.getString(1));
                ctq.setMaChucNang(rs.getString(2));
                dsctq.add(ctq);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        return dsctq;
    }
    public void ThemChiTietQuyenTaiKhoan(ChiTietQuyenDTO ctq){
        KetnoiCSDL kn=new KetnoiCSDL();
        String sql="INSERT INTO `chi_tiet_quyen`(`MaQuyen`, `MaChucNang`) "
                + "VALUES ('"+ctq.getMaQuyen()+"','"+ctq.getMaChucNang()+"')";
        kn.setchange(sql);
    }
    public void SuaChiTietQuyenTaiKhoan(QuyenTaiKhoanDTO qtk,String maqtkcu){
        KetnoiCSDL kn=new KetnoiCSDL();
        String sql="UPDATE `chi_tiet_quyen` SET `MaQuyen`='"+qtk.getMaQuyen()+"' WHERE `MaQuyen`='"+maqtkcu+"'";
        kn.setchange(sql);
    }
    public void XoaChiTietQuyenTaiKhoan(ChiTietQuyenDTO ctq){
        KetnoiCSDL kn=new KetnoiCSDL();
        String sql="DELETE FROM `chi_tiet_quyen` WHERE MaQuyen='"+ctq.getMaQuyen()+"' "
                + "and MaChucNang='"+ctq.getMaChucNang()+"'";
        kn.setchange(sql);
    }
    
//    public static void main(String[] args) {
//        ChiTietQuyenDAO p=new ChiTietQuyenDAO();
////        ChiTietQuyenDTO p1=new ChiTietQuyenDTO();
////        p1.setMaQuyen("TN");
////        p1.setMaChucNang("QLKMDV");
////        p.XoaChiTietQuyenTaiKhoan(p1);
//    }
}
