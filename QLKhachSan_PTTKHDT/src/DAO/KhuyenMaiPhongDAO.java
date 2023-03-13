/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.KhuyenMaiPhongDTO;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author T P Dung
 */
public class KhuyenMaiPhongDAO {
    public KhuyenMaiPhongDAO(){
        
    }
    public ArrayList<KhuyenMaiPhongDTO> getDanhSach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<KhuyenMaiPhongDTO> dskmp=new ArrayList<>();
        String sql="select * from khuyen_mai_phong where TinhTrang!=0";
        ResultSet rs = kn.getselect(sql);
        try{
            while(rs.next()){
                KhuyenMaiPhongDTO p =new KhuyenMaiPhongDTO();
                p.setMaKMPhong(rs.getString(1));
                p.setTen(rs.getString(2));
                p.setNgayBD(rs.getString(3));
                p.setNgayKT(rs.getString(4));
                p.setTinhTrang(Integer.parseInt(rs.getString(5)));
                dskmp.add(p);
            }
        }
        catch(Exception ex){
            ex.getStackTrace();
        }
        return dskmp;
    }
    public void them(KhuyenMaiPhongDTO p){
        String sql="INSERT INTO `khuyen_mai_phong`"
                + "(`MaKMPhong`, `Ten`, `NgayBD`, `NgayKT`, `TinhTrang`) VALUES ('"+p.getMaKMPhong()+"', '"+p.getTen()+"', '"+p.getNgayBD()+"', '"+p.getNgayKT()+"', '"+p.getTinhTrang()+"')";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public void sua(KhuyenMaiPhongDTO p){
        String sql="UPDATE `khuyen_mai_phong` SET `Ten`='"+p.getTen()+"',`NgayBD`='"+p.getNgayBD()+"',"
        + "`NgayKT`='"+p.getNgayKT()+"',`TinhTrang`='"+p.getTinhTrang()+"' WHERE `MaKMPhong`='"+p.getMaKMPhong()+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public void xoa(KhuyenMaiPhongDTO p){
        String sql="UPDATE `khuyen_mai_phong` SET `TinhTrang`=0 WHERE `MaKMPhong`='"+p.getMaKMPhong()+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public int MaCuoiDS(){
        int sl=0;
        ResultSet rs;
        KetnoiCSDL kn = new KetnoiCSDL();
        try{
            String sql="select count(MaKMPhong) from khuyen_mai_phong";
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
        String sql="UPDATE `khuyen_mai_phong` SET `TinhTrang`=1 WHERE NgayBD<='"+date+"' and NgayKT>='"+date+"' and TinhTrang!=0";
        String sql1="UPDATE `khuyen_mai_phong` SET `TinhTrang`=2 WHERE NgayKT<'"+date+"' and TinhTrang!=0";
        String sql2="UPDATE `khuyen_mai_phong` SET `TinhTrang`=3 WHERE NgayBD>'"+date+"' and TinhTrang!=0";
        kn.setchange(sql);
        kn.setchange(sql1);
        kn.setchange(sql2);
    }
    public ArrayList<KhuyenMaiPhongDTO> Timkiemnangcao(int tt, String nbd, String nkt){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<KhuyenMaiPhongDTO> dstimkiem=new ArrayList<>();
        String query="";
        if(tt!=0 && nbd.equals("") && nkt.equals("")){
            query="select * from khuyen_mai_phong where TinhTrang="+tt+"";
        }else if(tt!=0 && !nbd.equals("") && !nkt.equals("")){
            query="select * from khuyen_mai_phong where TinhTrang="+tt+" and ("
                    + "NgayBD>='"+nbd+"' and NgayKT<='"+nkt+"' or NgayBD>='"+nbd+"' and NgayBD<='"+nkt+"' and NgayKT>='"+nkt+"'"
                    + " or NgayBD<='"+nbd+"' and NgayKT>='"+nbd+"' and NgayKT<='"+nkt+"')";
        }else if(tt==0 && !nbd.equals("") && !nkt.equals("")){
            query="select * from khuyen_mai_phong where TinhTrang!=0 and ("
                    + "NgayBD>='"+nbd+"' and NgayKT<='"+nkt+"' or NgayBD>='"+nbd+"' and NgayBD<='"+nkt+"' and NgayKT>='"+nkt+"'"
                    + " or NgayBD<='"+nbd+"' and NgayKT>='"+nbd+"' and NgayKT<='"+nkt+"')";;
        }
        System.out.println(query);
        ResultSet rs=kn.getselect(query);
        try{
            while(rs.next()){
            KhuyenMaiPhongDTO p =new KhuyenMaiPhongDTO();
            p.setMaKMPhong(rs.getString(1));
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
