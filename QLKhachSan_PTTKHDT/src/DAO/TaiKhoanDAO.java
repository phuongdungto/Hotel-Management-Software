/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.TaiKhoanDTO;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author minhd
 */
public class TaiKhoanDAO {
    public TaiKhoanDAO(){
        
    }
    public ArrayList<TaiKhoanDTO> getDanhSach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        Connection con=kn.getKetnoi();
        ArrayList<TaiKhoanDTO> dstk=new ArrayList<>();
        String sql="select * from tai_khoan where TinhTrang!=0";
        try{
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(sql);
            while (rs.next()){
                TaiKhoanDTO p=new TaiKhoanDTO();
                p.setMaTK(rs.getString(1));
                p.setMaNV(rs.getString(2));
                p.setMaQuyen(rs.getString(3));
                p.setUsername(rs.getString(4));
                p.setPassword(rs.getString(5));
                p.setTinhTrang(Integer.parseInt(rs.getString(6)));
                dstk.add(p);
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return dstk;
    }
    public ArrayList<TaiKhoanDTO> LayToanBoDanhSach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        Connection con=kn.getKetnoi();
        ArrayList<TaiKhoanDTO> dstk=new ArrayList<>();
        String sql="select * from tai_khoan";
        try{
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(sql);
            while (rs.next()){
                TaiKhoanDTO p=new TaiKhoanDTO();
                p.setMaTK(rs.getString(1));
                p.setMaNV(rs.getString(2));
                p.setMaQuyen(rs.getString(3));
                p.setUsername(rs.getString(4));
                p.setPassword(rs.getString(5));
                p.setTinhTrang(Integer.parseInt(rs.getString(6)));
                dstk.add(p);
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return dstk;
    }
    public void ThemTaiKhoan(TaiKhoanDTO tk){
        String sql="INSERT INTO `tai_khoan`(`MaTK`, `MaNV`, `MaQuyen`, `Username`, "
                + "`Password`, `TinhTrang`) VALUES ('"+tk.getMaTK()+"','"+tk.getMaNV()+"'"
                + ",'"+tk.getMaQuyen()+"','"+tk.getUsername()+"','"+tk.getPassword()+"',1)";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public void SuaTaiKhoan(TaiKhoanDTO tk){
        String sql="UPDATE `tai_khoan` SET `MaNV`='"+tk.getMaNV()+"',`MaQuyen`='"+tk.getMaQuyen()+"',"
                + "`Username`='"+tk.getUsername()+"',`Password`='"+tk.getPassword()+"' "
                + "WHERE MaTK='"+tk.getMaTK()+"'";

        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public void XoaTaiKhoan(String matk){
        String sql="UPDATE `tai_khoan` SET `TinhTrang`=0 WHERE MaTK='"+matk+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public int DemSoLuongTaiKhoan(){
        int sl=0;
        try {
            String sql="select COUNT(MaTK) from tai_khoan";
            KetnoiCSDL kn=new KetnoiCSDL();
            ResultSet rs=kn.getselect(sql);
            while(rs.next()){
                sl=Integer.parseInt(rs.getString(1));
            }
            
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return sl;
    }
    public void DoiMatKhau(String matk,String mkmoi){
        String sql="UPDATE `tai_khoan` SET `Password`='"+mkmoi+"' "
                + "WHERE MaTK='"+matk+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
//    public static void main(String[] args) {
//        
//        TaiKhoanDAO p=new TaiKhoanDAO();        
//    }
}
