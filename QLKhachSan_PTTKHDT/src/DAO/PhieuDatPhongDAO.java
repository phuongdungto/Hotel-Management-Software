/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.Xulydulieu;
import DTO.PhieuDatPhongDTO;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author T P Dung
 */
public class PhieuDatPhongDAO {
    public PhieuDatPhongDAO(){
        
    }
    public ArrayList<PhieuDatPhongDTO> getDanhSach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<PhieuDatPhongDTO> dspdp=new ArrayList<>();
        String query="select * from phieu_dat_phong where TinhTrang!=0";
        ResultSet rs=kn.getselect(query);
        try{
            while(rs.next()){
            PhieuDatPhongDTO p =new PhieuDatPhongDTO();
            p.setMaPDP(rs.getString(1));
            p.setMaKH(rs.getString(2));
            p.setMaNV(rs.getString(3));
            p.setSLPhong(Integer.parseInt(rs.getString(4)));
            p.setNgayLapPDP(rs.getString(5));
            p.setNgayThue(rs.getString(6));
            p.setNgayTra(rs.getString(7));
            p.setTongTienThue(Integer.parseInt(rs.getString(8)));
            p.setTongTienKM(Integer.parseInt(rs.getString(9)));
            p.setTinhTrang(Integer.parseInt(rs.getString(10)));
            dspdp.add(p);
            }
        }
        catch(Exception ex){
            ex.getStackTrace();
        }
        return dspdp;
    }
    public void them(PhieuDatPhongDTO p){
        String sql="INSERT INTO `phieu_dat_phong`"
                + "(`MaPDP`, `MaKH`,`MaNV` ,`SLPhong`, `NgayLapPDP`, `NgayThue`,`NgayTra`,`TongTienThue`,`TongTienKM`,`TinhTrang`) "
                + "VALUES ('"+p.getMaPDP()+"', '"+p.getMaKH()+"', '"+p.getMaNV()+"', "+p.getSLPhong()+", '"+p.getNgayLapPDP()+"', '"+p.getNgayThue()+"', '"+p.getNgayTra()+"', "+p.getTongTienThue()+","+p.getTongTienKM()+","+p.getTinhTrang()+")";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public int MaCuoiDS(){
        int sl=0;
        ResultSet rs;
        KetnoiCSDL kn = new KetnoiCSDL();
        try{
            String sql="select count(MaPDP) from phieu_dat_phong";
            rs=kn.getselect(sql);
            while(rs.next()){
                return Integer.parseInt(rs.getString(1));
            }
        }catch(SQLException ex){
            ex.getStackTrace();
        }
        return 0;
    }
    public void CapNhapPDPKhiThanhToan(PhieuDatPhongDTO pdp){
        String sql="UPDATE `phieu_dat_phong` SET `NgayTra`='"+pdp.getNgayTra()+"',"
                + "`TongTienThue`='"+pdp.getTongTienThue()+"',`TongTienKM`='"+pdp.getTongTienKM()+"',"
                + "`TinhTrang`=2 WHERE `MaPDP`='"+pdp.getMaPDP()+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public void XoaPhieuDatPhongKhiXoaHoaDon(String mapdp){
        String sql="UPDATE `phieu_dat_phong` SET `TinhTrang`=0 WHERE `MaPDP`='"+mapdp+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    //================== Duyên =====================================
    public String LayPDP(String mapdp){
        KetnoiCSDL kn = new KetnoiCSDL();
        String pdp="";
        String qry ="SELECT phong.MaPhong,phieu_dat_phong.MaPDP FROM phong,phieu_dat_phong,chi_tiet_phieu_dat_phong WHERE phong.MaPhong='"+mapdp+"' AND chi_tiet_phieu_dat_phong.MaPhong=phong.MaPhong AND chi_tiet_phieu_dat_phong.MaPDP=phieu_dat_phong.MaPDP AND phieu_dat_phong.TinhTrang=1 GROUP BY phong.MaPhong,phieu_dat_phong.MaPDP";
        ResultSet rs=kn.getselect(qry);
        try{
            while(rs.next()){
            pdp = rs.getString(2);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return pdp;
    }
    public void sua(PhieuDatPhongDTO p){
        String sql="UPDATE `phieu_dat_phong` SET `TinhTrang`="+p.getTinhTrang()+",`MaKH`='"+p.getMaKH()+"',`SLPhong`="+p.getSLPhong()+",`NgayThue`='"+p.getNgayThue()+"',`NgayTra`='"+p.getNgayTra()+"',`TongTienThue`="+p.getTongTienThue()+",`TongTienKM`="+p.getTongTienKM()+" WHERE `MaPDP`='"+p.getMaPDP()+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public void xoa(PhieuDatPhongDTO p){
        String sql="UPDATE `phieu_dat_phong` SET `TinhTrang`=0 WHERE `MaPDP`='"+p.getMaPDP()+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public ArrayList<PhieuDatPhongDTO> Timkiemnangcao(int tt, String nbd, String nkt){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<PhieuDatPhongDTO> dstimkiem=new ArrayList<>();
        String query="";
        if(tt!=0 && nbd.equals("") && nkt.equals("")){
            query="select * from phieu_dat_phong where TinhTrang="+tt+"";
        }else if(tt!=0 && !nbd.equals("") && !nkt.equals("")){
            query="select * from phieu_dat_phong where TinhTrang="+tt+" and (NgayLapPDP >= '"+nbd+"' and NgayLapPDP <= '"+nkt+"')";
        }else if(tt==0 && !nbd.equals("") && !nkt.equals("")){
            query="select * from phieu_dat_phong where TinhTrang!=0 and  NgayLapPDP >= '"+nbd+"' and NgayLapPDP <= '"+nkt+"'";;
        }
        ResultSet rs=kn.getselect(query);
        try{
            while(rs.next()){
            PhieuDatPhongDTO p =new PhieuDatPhongDTO();
            p.setMaPDP(rs.getString(1));
            p.setMaKH(rs.getString(2));
            p.setMaNV(rs.getString(3));
            p.setSLPhong(Integer.parseInt(rs.getString(4)));
            p.setNgayLapPDP(rs.getString(5));
            p.setNgayThue(rs.getString(6));
            p.setNgayTra(rs.getString(7));
            p.setTongTienThue(Integer.parseInt(rs.getString(8)));
            p.setTongTienKM(Integer.parseInt(rs.getString(9)));
            p.setTinhTrang(Integer.parseInt(rs.getString(10)));
            dstimkiem.add(p);
            }
        }
        catch(Exception ex){
            ex.getStackTrace();
        }
        return dstimkiem;
    }
//    public static void main(String[] args) {
//        PhieuDatPhongDAO p=new PhieuDatPhongDAO();
//    }
}
