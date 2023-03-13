/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.QuyenTaiKhoanDTO;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author minhd
 */
public class QuyenTaiKhoanDAO {
    public QuyenTaiKhoanDAO(){
        
    }
    public ArrayList<QuyenTaiKhoanDTO> getDanhsach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        Connection con=kn.getKetnoi();
        ArrayList<QuyenTaiKhoanDTO> dsqtk=new ArrayList<>();
        String sql="select * from quyen_tk where TinhTrang!=0";
        try{
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(sql);
            while(rs.next()){
                QuyenTaiKhoanDTO q=new QuyenTaiKhoanDTO();
                q.setMaQuyen(rs.getString(1));
                q.setTenQuyen(rs.getString(2));
                dsqtk.add(q);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        return dsqtk;
    }
    public ArrayList<QuyenTaiKhoanDTO> LayToanBoQuyenTaiKhoan(){
        KetnoiCSDL kn=new KetnoiCSDL();
        Connection con=kn.getKetnoi();
        ArrayList<QuyenTaiKhoanDTO> dsqtk=new ArrayList<>();
        String sql="select * from quyen_tk";
        try{
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(sql);
            while(rs.next()){
                QuyenTaiKhoanDTO q=new QuyenTaiKhoanDTO();
                q.setMaQuyen(rs.getString(1));
                q.setTenQuyen(rs.getString(2));
                dsqtk.add(q);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        return dsqtk;
    }
    public void ThemQuyenTaiKhoan(QuyenTaiKhoanDTO qtk){
        KetnoiCSDL kn=new KetnoiCSDL();
        String sql="INSERT INTO `quyen_tk`(`MaQuyen`, `TenQuyen`, `TinhTrang`) "
                + "VALUES ('"+qtk.getMaQuyen()+"','"+qtk.getTenQuyen()+"',1)";
        kn.setchange(sql);
    }
    public void SuaQuyenTaiKhoan(QuyenTaiKhoanDTO qtk){
        KetnoiCSDL kn=new KetnoiCSDL();
        String sql="UPDATE `quyen_tk` SET `TenQuyen`='"+qtk.getTenQuyen()+"'"
                + " WHERE `MaQuyen`='"+qtk.MaQuyen+"'";
        kn.setchange(sql);
    }
    public void XoaQuyenTaiKhoan(String maq){
        KetnoiCSDL kn=new KetnoiCSDL();
        String sql="UPDATE `quyen_tk` SET `TinhTrang`=0 WHERE `MaQuyen`='"+maq+"'";
        kn.setchange(sql);
    }
    public void XoaQuyenTaiKhoanVinhVien(String maq){
        KetnoiCSDL kn=new KetnoiCSDL();
        String sql="DELETE FROM `quyen_tk` WHERE `MaQuyen`='"+maq+"'";
        kn.setchange(sql);
    }
//    public static void main(String[] args) {
//        QuyenTaiKhoanDAO p=new QuyenTaiKhoanDAO();
//        QuyenTaiKhoanDTO p1=new QuyenTaiKhoanDTO();
////        p1.setMaQuyen("QL124");
////        p1.setTenQuyen("Thử");
////        p.SuaQuyenTaiKhoan(p1, "QL123");
//    }
}
