/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietHoaDonDTO;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author minhd
 */
public class ChiTietHoaDonDAO {
    public ChiTietHoaDonDAO(){
        
    }
    public ArrayList<ChiTietHoaDonDTO> getDanhSach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        Connection con=kn.getKetnoi();
        ArrayList<ChiTietHoaDonDTO> dscthd=new ArrayList<>();
        String sql="select * from chi_tiet_hoa_don";
        try{
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(sql);
            while (rs.next()){
                ChiTietHoaDonDTO p=new ChiTietHoaDonDTO();
                p.setMaHD(rs.getString(1));
                p.setMaDV(rs.getString(2));
                p.setMaKMDV(rs.getString(3));
                p.setDonGia(Integer.parseInt(rs.getString(4)));
                p.setSL(Integer.parseInt(rs.getString(5)));
                p.setThanhTien(Integer.parseInt(rs.getString(6)));
                p.setTienKM(Integer.parseInt(rs.getString(7)));
                dscthd.add(p);
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return dscthd;
    }
    public void ThemChiTietHoaDon(ChiTietHoaDonDTO cthd){
        String sql="INSERT INTO `chi_tiet_hoa_don`(`MaHD`, `MaDV`, `MaKMDV`, `DonGia`, `SL`, `ThanhTien`, `TienKM`) "
                + "VALUES ('"+cthd.getMaHD()+"','"+cthd.getMaDV()+"','"+cthd.getMaKMDV()+"',"
                + "'"+cthd.getDonGia()+"','"+cthd.getSL()+"','"+cthd.getThanhTien()+"','"+cthd.getTienKM()+"')";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public void SuaChiTietHoaDon(ChiTietHoaDonDTO cthd){
        String sql="UPDATE `chi_tiet_hoa_don` SET `MaKMDV`='"+cthd.getMaKMDV()+"',`DonGia`='"+cthd.getDonGia()+"',"
                + "`SL`='"+cthd.getSL()+"',`ThanhTien`='"+cthd.getThanhTien()+"',"
                + "`TienKM`='"+cthd.getTienKM()+"' WHERE `MaHD`='"+cthd.getMaHD()+"' and `MaDV`='"+cthd.getMaDV()+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public void XoaChiTietHoaDon(String mahd,String madv){
        String sql="DELETE FROM `chi_tiet_hoa_don` WHERE `MaHD`='"+mahd+"' "
                + "and `MaDV`='"+madv+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    //--------------------- Thêm 1 CTHD ------------------------------//
     public void themCTHD(ChiTietHoaDonDTO ctpnh ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="INSERT INTO `chi_tiet_hoa_don` (`MaHD`, `MaDV`, `MaKMDV`, `DonGia`, `SL`, `ThanhTien`, `TienKM`) VALUES ('"+ctpnh.getMaHD()+"', '"+ctpnh.getMaDV()+"', '"+ctpnh.getMaKMDV()+"', '"+ctpnh.getDonGia()+"', '"+ctpnh.getSL()+"', '"+ctpnh.getThanhTien()+"', '"+ctpnh.getTienKM()+"')";
            kn.setchange(qry);
    }
//--------------------- Update số lượng------------------------------//
    public void suaCTHD(ChiTietHoaDonDTO hd){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="UPDATE `chi_tiet_hoa_don` SET `DonGia`='"+hd.getDonGia()+"',`SL`='"+hd.getSL()+"',`ThanhTien`='"+hd.getThanhTien()+"',`TienKM`='"+hd.getTienKM()+"' WHERE `chi_tiet_hoa_don`.`MaHD`='"+hd.getMaHD()+"' AND `chi_tiet_hoa_don`.`MaDV`='"+hd.getMaDV()+"'";
             kn.setchange(qry);
    }
    
//--------------------- Xóa 1 CTHD------------------------------//
    public void xoaCTHD(String mahd, String madv ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="DELETE FROM `chi_tiet_hoa_don` WHERE `chi_tiet_hoa_don`.`MaHD`='"+mahd+"' AND `chi_tiet_hoa_don`.`MaDV`='"+madv+"'";
             kn.setchange(qry);
    }
}
