/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietPhieuDatPhongDTO;
import DTO.PhongDTO;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author T P Dung
 */
public class ChiTietPhieuDatPhongDAO {
    public ChiTietPhieuDatPhongDAO(){
        
    }
    public ArrayList<ChiTietPhieuDatPhongDTO> getDanhSach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<ChiTietPhieuDatPhongDTO> dsctpdp=new ArrayList<>();
        String query="select * from chi_tiet_phieu_dat_phong";
        ResultSet rs=kn.getselect(query);
        try{
            while(rs.next()){
            ChiTietPhieuDatPhongDTO p =new ChiTietPhieuDatPhongDTO();
            p.setMaPDP(rs.getString(1));
            p.setMaPhong(rs.getString(2));
            p.setMaKMPhong(rs.getString(3));
            p.setSLNguoi(Integer.parseInt(rs.getString(4)));
            p.setDonGia(Integer.parseInt(rs.getString(5)));
            p.setTienKM(Integer.parseInt(rs.getString(6)));
            dsctpdp.add(p);
            }
        }
        catch(Exception ex){
            ex.getStackTrace();
        }
        return dsctpdp;
    }
    public void them(ChiTietPhieuDatPhongDTO p){
        String sql="INSERT INTO `chi_tiet_phieu_dat_phong`"
                + "(`MaPDP`, `MaPhong`, `MaKMPhong`,`SLNguoi`,`DonGia`,`TienKM`) "
                + "VALUES ('"+p.getMaPDP()+"', '"+p.getMaPhong()+"', '"+p.getMaKMPhong()+"', "+p.getSLNguoi()+","+p.getDonGia()+","+p.getTienKM()+")";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public void capnhattrangthaiphong(PhongDTO p){
        String sql = "UPDATE `phong` SET `TinhTrang`="+p.getTinhTrang()+" WHERE MaPhong='"+p.getMaPhong()+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
        public void xoa1chitiet(ChiTietPhieuDatPhongDTO p){
        String sql="DELETE FROM `chi_tiet_phieu_dat_phong` WHERE MaPhong='"+p.getMaPhong()+"' and MaPDP='"+p.getMaPDP()+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
}
