/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.ChiTietPhieuNhapDTO;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author camdu
 */
public class ChiTietPhieuNhapDAO {
    ResultSet rs;
public ChiTietPhieuNhapDAO(){
        
    }
public ArrayList<ChiTietPhieuNhapDTO> getDanhsachall(){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<ChiTietPhieuNhapDTO> dsctpnh=new ArrayList<>();
        String sql="SELECT * FROM `chi_tiet_phieu_nhap`";
        try{
            rs=kn.getselect(sql);
            while(rs.next()){
                ChiTietPhieuNhapDTO ctpnh=new ChiTietPhieuNhapDTO();
                ctpnh.setMaPNH(rs.getString(1));
                ctpnh.setMaHang(rs.getString(2));
                ctpnh.setDonGia(Integer.parseInt(rs.getString(3)));
                ctpnh.setSL(Integer.parseInt(rs.getString(4)));
                ctpnh.setThanhTien(Integer.parseInt(rs.getString(5)));
                dsctpnh.add(ctpnh);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        return dsctpnh;
    }
//==================== lấy danh sách ========================//
public ArrayList<ChiTietPhieuNhapDTO> getDanhsach(String mapnh){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<ChiTietPhieuNhapDTO> dsctpnh=new ArrayList<>();
        String sql="SELECT * FROM `chi_tiet_phieu_nhap` WHERE MaPNH='"+mapnh+"'";
        try{
            rs=kn.getselect(sql);
            while(rs.next()){
                ChiTietPhieuNhapDTO ctpnh=new ChiTietPhieuNhapDTO();
                ctpnh.setMaPNH(rs.getString(1));
                ctpnh.setMaHang(rs.getString(2));
                ctpnh.setDonGia(Integer.parseInt(rs.getString(3)));
                ctpnh.setSL(Integer.parseInt(rs.getString(4)));
                ctpnh.setThanhTien(Integer.parseInt(rs.getString(5)));
                dsctpnh.add(ctpnh);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        return dsctpnh;
    }
//--------------------- Thêm 1 CTPNH ------------------------------//
     public void themCTPNH(ChiTietPhieuNhapDTO ctpnh ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="INSERT INTO `chi_tiet_phieu_nhap` (`MaPNH`, `MaHang`, `DonGia`, `SL`, `ThanhTien`) VALUES ('"+ctpnh.getMaPNH()+"', '"+ctpnh.getMaHang()+"', "+ctpnh.getDonGia()+", "+ctpnh.getSL()+", "+ctpnh.getThanhTien()+")";
            kn.setchange(qry);
    }  
 //--------------------- Xóa 1 CTPNH------------------------------//
    public void xoaCTPNH(ChiTietPhieuNhapDTO ctpnh ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="DELETE FROM `chi_tiet_phieu_nhap` WHERE `chi_tiet_phieu_nhap`.`MaPNH`='"+ctpnh.getMaPNH()+"' AND `chi_tiet_phieu_nhap`.`MaHang`='"+ctpnh.getMaHang()+"'";
             kn.setchange(qry);
    }
//--------------------- Update số lượng------------------------------//
    public void suaCTPNH(String MaPNH,int soluong,String MaHang,int Tien){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="UPDATE `chi_tiet_phieu_nhap` SET `SL`="+soluong+",`ThanhTien`="+Tien+" WHERE `chi_tiet_phieu_nhap`.`MaPNH`='"+MaPNH+"' AND `chi_tiet_phieu_nhap`.`MaHang`='"+MaHang+"'";
             kn.setchange(qry);
    }
}
