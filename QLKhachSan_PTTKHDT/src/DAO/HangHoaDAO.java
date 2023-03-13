/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.HangHoaDTO;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author camdu
 */
public class HangHoaDAO {
    ResultSet rs;
    public HangHoaDAO(){
        
    }
    public ArrayList<HangHoaDTO> getDanhsach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<HangHoaDTO> dshh=new ArrayList<>();
        String sql="select * from hang_hoa";
        try{
            rs=kn.getselect(sql);
            while(rs.next()){
                HangHoaDTO hh=new HangHoaDTO();
                hh.setMaHang(rs.getString(1));
                hh.setTenHang(rs.getString(2));
                hh.setLoaiHang(rs.getString(3));
                hh.setSL(Integer.parseInt(rs.getString(4)));
                hh.setDonGia(Integer.parseInt(rs.getString(5)));
                hh.setTinhTrang(Integer.parseInt(rs.getString(6)));
                dshh.add(hh);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        return dshh;
    }

//--------------------- Thêm 1 hàng hóa ------------------------------//
     public void themhanghoa(HangHoaDTO hh ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="INSERT INTO `hang_hoa` (`MaHang`, `TenHang`, `LoaiHang`, `SL`, `DonGia`, `TinhTrang`) VALUES ('"+hh.getMaHang()+"', '"+hh.getTenHang()+"', '"+hh.getLoaiHang()+"', '"+hh.getSL()+"', '"+hh.getDonGia()+"', '"+hh.getTinhTrang()+"')";
            kn.setchange(qry);
    }    
 //--------------------- Sửa 1 hàng hóa ------------------------------//
     public void suahanghoa(HangHoaDTO hh ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="UPDATE `hang_hoa` SET `MaHang`='"+hh.getMaHang()+"',`TenHang`='"+hh.getTenHang()+"',`LoaiHang`='"+hh.getLoaiHang()+"',`SL`="+hh.getSL()+",`DonGia`="+hh.getDonGia()+",`TinhTrang`="+hh.getTinhTrang()+" WHERE `hang_hoa`.`MaHang`='"+hh.getMaHang()+"'";
           kn.setchange(qry);
     }
      //--------------------- Sửa 1 SL hàng hóa ------------------------------//
     public void suaSLhanghoa(String mahh,int soluong ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="UPDATE `hang_hoa` SET `SL`="+soluong+" WHERE `hang_hoa`.`MaHang`='"+mahh+"'";
           kn.setchange(qry);
           
     }
 //--------------------- Xóa 1 hàng hóa ------------------------------//
    public void xoahanghoa(HangHoaDTO hh ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="UPDATE `hang_hoa` SET `TinhTrang`="+0+" WHERE `hang_hoa`.`MaHang`='"+hh.getMaHang()+"'";
             kn.setchange(qry);
    }
    
    public int MaCuoiDS(){
        int sl=0;
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="select count(MaHang) from hang_hoa";
            rs=kn.getselect(qry);
        try {
            while(rs.next()){
            sl= Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HangHoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl;
    }
}
