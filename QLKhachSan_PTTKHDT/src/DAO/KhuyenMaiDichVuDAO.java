/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BUS.Xulydulieu;
import DTO.KhuyenMaiDichVuDTO;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author T P Dung
 */
public class KhuyenMaiDichVuDAO {
    public KhuyenMaiDichVuDAO(){
        
    }
    public ArrayList<KhuyenMaiDichVuDTO> getDanhSach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<KhuyenMaiDichVuDTO> dskmdv=new ArrayList<>();
        String query="select * from khuyen_mai_dich_vu where TinhTrang!=0";
        ResultSet rs=kn.getselect(query);
        try{
            while(rs.next()){
            KhuyenMaiDichVuDTO p =new KhuyenMaiDichVuDTO();
            p.setMaKMDV(rs.getString(1));
            p.setTen(rs.getString(2));
            p.setNgayBD(rs.getString(3));
            p.setNgayKT(rs.getString(4));
            p.setTinhTrang(Integer.parseInt(rs.getString(5)));
            dskmdv.add(p);
            }
        }
        catch(Exception ex){
            ex.getStackTrace();
        }
        return dskmdv;
    }
    public void them(KhuyenMaiDichVuDTO p){
        String sql="INSERT INTO `khuyen_mai_dich_vu`"
                + "(`MaKMDV`, `Ten`, `NgayBD`, `NgayKT`, `TinhTrang`) VALUES ('"+p.getMaKMDV()+"', '"+p.getTen()+"', '"+p.getNgayBD()+"', '"+p.getNgayKT()+"', "+p.getTinhTrang()+")";
        //System.out.println(sql);
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public void sua(KhuyenMaiDichVuDTO p){
        String sql="UPDATE `khuyen_mai_dich_vu` SET `Ten`='"+p.getTen()+"',`NgayBD`='"+p.getNgayBD()+"',"
        + "`NgayKT`='"+p.getNgayKT()+"', `TinhTrang`="+p.getTinhTrang()+" WHERE `MaKMDV`='"+p.getMaKMDV()+"'";
        System.out.println(sql);
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public void xoa(KhuyenMaiDichVuDTO p){
        String sql="UPDATE `khuyen_mai_dich_vu` SET `TinhTrang`=0  WHERE `MaKMDV`='"+p.getMaKMDV()+"'";
        System.out.println(sql);
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public int MaCuoiDS(){
        int sl=0;
        ResultSet rs;
        KetnoiCSDL kn = new KetnoiCSDL();
        try{
            String sql="select count(MaKMDV) from khuyen_mai_dich_vu";
            rs=kn.getselect(sql);
            while(rs.next()){
                return Integer.parseInt(rs.getString(1));
            }
        }catch(SQLException ex){
            ex.getStackTrace();
        }
        return 0;
    }
    public void CapNhatTrangThai(String date){
        KetnoiCSDL kn=new KetnoiCSDL();
        String sql="UPDATE `khuyen_mai_dich_vu` SET `TinhTrang`=1 WHERE NgayBD<='"+date+"' and NgayKT>='"+date+"' and TinhTrang!=0";
        String sql1="UPDATE `khuyen_mai_dich_vu` SET `TinhTrang`=2 WHERE NgayKT<'"+date+"' and TinhTrang!=0";
        String sql2="UPDATE `khuyen_mai_dich_vu` SET `TinhTrang`=3 WHERE NgayBD>'"+date+"' and TinhTrang!=0";
        kn.setchange(sql);
        kn.setchange(sql1);
        kn.setchange(sql2);
    }
    public ArrayList<KhuyenMaiDichVuDTO> Timkiemnangcao(int tt, String nbd, String nkt){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<KhuyenMaiDichVuDTO> dstimkiem=new ArrayList<>();
        String query="";
        if(tt!=0 && nbd.equals("") && nkt.equals("")){
            query="select * from khuyen_mai_dich_vu where TinhTrang="+tt+"";
        }else if(tt!=0 && !nbd.equals("") && !nkt.equals("")){
            query="select * from khuyen_mai_dich_vu where TinhTrang="+tt+" and ("
                    + "NgayBD>='"+nbd+"' and NgayKT<='"+nkt+"' or NgayBD>='"+nbd+"' and NgayBD<='"+nkt+"' and NgayKT>='"+nkt+"'"
                    + " or NgayBD<='"+nbd+"' and NgayKT>='"+nbd+"' and NgayKT<='"+nkt+"')";
        }else if(tt==0 && !nbd.equals("") && !nkt.equals("")){
            query="select * from khuyen_mai_dich_vu where TinhTrang!=0 and ("
                    + "NgayBD>='"+nbd+"' and NgayKT<='"+nkt+"' or NgayBD>='"+nbd+"' and NgayBD<='"+nkt+"' and NgayKT>='"+nkt+"'"
                    + " or NgayBD<='"+nbd+"' and NgayKT>='"+nbd+"' and NgayKT<='"+nkt+"')";;
        }
        ResultSet rs=kn.getselect(query);
        try{
            while(rs.next()){
            KhuyenMaiDichVuDTO p =new KhuyenMaiDichVuDTO();
            p.setMaKMDV(rs.getString(1));
            p.setTen(rs.getString(2));
            p.setNgayBD(rs.getString(3));
            p.setNgayKT(rs.getString(4));
            p.setTinhTrang(Integer.parseInt(rs.getString(5)));
            dstimkiem.add(p);
            }
        }
        catch(Exception ex){
            ex.getStackTrace();
        }
        return dstimkiem;
    }
}
