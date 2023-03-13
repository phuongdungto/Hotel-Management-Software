/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhanVienDTO;
import DTO.PhieuNhapHangDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camdu
 */
public class PhieuNhapHangDAO {
    ResultSet rs;
    public PhieuNhapHangDAO(){
        
    }
    public ArrayList<PhieuNhapHangDTO> getDanhsach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<PhieuNhapHangDTO> dspnh=new ArrayList<>();
        String sql="select * from phieu_nhap_hang";
        try{
            ResultSet rs=kn.getselect(sql);
            while(rs.next()){
                PhieuNhapHangDTO nv=new PhieuNhapHangDTO();
                nv.setMaPNH(rs.getString(1));
                nv.setMaNV(rs.getString(2));
                nv.setMaNCC(rs.getString(3));
                nv.setNgayNhap(rs.getString(4));
                nv.setTongTien(Integer.parseInt(rs.getString(5)));
                nv.setTinhTrang(Integer.parseInt(rs.getString(6)));
                dspnh.add(nv);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        return dspnh;
    }
//--------------------- Thêm 1 hàng hóa ------------------------------//
     public void themphieunhaphang(PhieuNhapHangDTO pnh ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="INSERT INTO `phieu_nhap_hang` (`MaPNH`, `MaNV`, `MaNCC`, `NgayNhap`, `TongTien`, `TinhTrang`) VALUES ('"+pnh.getMaPNH()+"', '"+pnh.getMaNV()+"', '"+pnh.getMaNCC()+"', '"+pnh.getNgayNhap()+"', '"+pnh.getTongTien()+"', '"+pnh.getTinhTrang()+"')";
            kn.setchange(qry);
    }    
 //--------------------- Sửa 1 hàng hóa ------------------------------//
     public void suaphieunhaphang(PhieuNhapHangDTO pnh ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="UPDATE `phieu_nhap_hang` SET `MaPNH`='"+pnh.getMaPNH()+"',`MaNV`='"+pnh.getMaNV()+"',`MaNCC`='"+pnh.getMaNCC()+"',`NgayNhap`='"+pnh.getNgayNhap()+"',`TongTien`="+pnh.getTongTien()+",`TinhTrang`="+pnh.getTinhTrang()+" WHERE `phieu_nhap_hang`.`MaPNH`='"+pnh.getMaPNH()+"'";
            kn.setchange(qry);
    }
 //--------------------- Xóa 1 phiếu nhập hàng ------------------------------//
    public void xoaPNH(PhieuNhapHangDTO pnh ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="UPDATE `phieu_nhap_hang` SET `TinhTrang`="+0+" WHERE `phieu_nhap_hang`.`MaPNH`='"+pnh.getMaPNH()+"'";
             kn.setchange(qry);
    }
//--------------------- Lấy mã cuối ds ------------------------------//    
    public int MaCuoiDS(){
        int sl=0;
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="select count(MaPNH) from phieu_nhap_hang";
            rs=kn.getselect(qry);
        try {
            while(rs.next()){
                sl= Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sl;
    }
//------------------------- Tìm kiếm theo ngày ----------------------------------------//
    public ArrayList<PhieuNhapHangDTO> listIdVoucherDay(String tungay, String denngay) {
        KetnoiCSDL kn = new KetnoiCSDL();
          ArrayList ds_pnh_timkiem = new ArrayList<PhieuNhapHangDTO>();
        try {
            String qry="SELECT * FROM `phieu_nhap_hang` WHERE `phieu_nhap_hang`.`NgayNhap`>='"+tungay+"' and `phieu_nhap_hang`.`NgayNhap`<='"+denngay+"'";
            rs = kn.getselect(qry);
            while (rs.next()) {
                PhieuNhapHangDTO dv = new PhieuNhapHangDTO();
                dv.setMaPNH(rs.getString(1));
                dv.setMaNV(rs.getString(2));
                dv.setMaNCC(rs.getString(3));
                dv.setNgayNhap(rs.getString(4));
                dv.setTongTien(rs.getInt(5));
                dv.setTinhTrang(rs.getInt(6));
                ds_pnh_timkiem.add(dv);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.toString());
        }
        return ds_pnh_timkiem;
    }
}
