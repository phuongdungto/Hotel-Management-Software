/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.HoaDonDTO;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author minhd
 */
public class HoaDonDAO {
    public HoaDonDAO(){
        
    }
    public ArrayList<HoaDonDTO> getDanhSach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        Connection con=kn.getKetnoi();
        ArrayList<HoaDonDTO> dshd=new ArrayList<>();
        String sql="select * from hoa_don where TinhTrang!=0";
        try{
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(sql);
            while (rs.next()){
                HoaDonDTO p=new HoaDonDTO();
                p.setMaHD(rs.getString(1));
                p.setMaPDP(rs.getString(2));
                p.setMaNV(rs.getString(3));
                p.setNgayLapHD(rs.getString(4));
                p.setTongTienThue(Integer.parseInt(rs.getString(5)));
                p.setTongTienDV(Integer.parseInt(rs.getString(6)));
                p.setThue(Integer.parseInt(rs.getString(7)));
                p.setTongCong(Integer.parseInt(rs.getString(8)));
                p.setTongTienKM(Integer.parseInt(rs.getString(9)));
                p.setTongPhaiTra(Integer.parseInt(rs.getString(10)));
                p.setTinhTrang(Integer.parseInt(rs.getString(11)));
                dshd.add(p);
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return dshd;
    }
    public ArrayList<HoaDonDTO> TimKiemTheoNgay(String tungay,String denngay){
        KetnoiCSDL kn=new KetnoiCSDL();
        Connection con=kn.getKetnoi();
        ArrayList<HoaDonDTO> dshd=new ArrayList<>();
        String sql="select * from hoa_don where TinhTrang!=0 and NgayLapHD>='"+tungay+"' and "
                + "NgayLapHD<='"+denngay+"'";
        try{
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(sql);
            while (rs.next()){
                HoaDonDTO p=new HoaDonDTO();
                p.setMaHD(rs.getString(1));
                p.setMaPDP(rs.getString(2));
                p.setMaNV(rs.getString(3));
                p.setNgayLapHD(rs.getString(4));
                p.setTongTienThue(Integer.parseInt(rs.getString(5)));
                p.setTongTienDV(Integer.parseInt(rs.getString(6)));
                p.setThue(Integer.parseInt(rs.getString(7)));
                p.setTongCong(Integer.parseInt(rs.getString(8)));
                p.setTongTienKM(Integer.parseInt(rs.getString(9)));
                p.setTongPhaiTra(Integer.parseInt(rs.getString(10)));
                p.setTinhTrang(Integer.parseInt(rs.getString(11)));
                dshd.add(p);
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return dshd;
    }

    public void XoaHoaDon(String mahd){
        String sql="UPDATE `hoa_don` SET `TinhTrang`=0 WHERE `MaHD`='"+mahd+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    
    public void CapNhapHoaDonKhiThanhToan(HoaDonDTO hd){
        String sql="UPDATE `hoa_don` SET `MaPDP`='"+hd.getMaPDP()+"',"
                + "`MaNV`='"+hd.getMaNV()+"',`NgayLapHD`='"+hd.getNgayLapHD()+"',`TongTienThue`='"+hd.getTongTienThue()+"',"
                + "`TongTienDV`='"+hd.getTongTienDV()+"',`Thue`=10,`TongCong`='"+hd.getTongCong()+"',"
                + "`TongTienKM`='"+hd.getTongTienKM()+"',`TongPhaiTra`='"+hd.getTongPhaiTra()+"',"
                + "`TinhTrang`=2 WHERE `MaHD`='"+hd.getMaHD()+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public ArrayList<HoaDonDTO> getDanhSachTKngay(String s){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<HoaDonDTO> dshd=new ArrayList<>();
        String sql="select * from hoa_don where NgayLapHD='"+s+"' && TinhTrang=2";
        try{
            ResultSet rs=kn.getselect(sql);
            while (rs.next()){
                HoaDonDTO p=new HoaDonDTO();
                p.setMaHD(rs.getString(1));
                p.setMaPDP(rs.getString(2));
                p.setMaNV(rs.getString(3));
                p.setNgayLapHD(rs.getString(4));
                p.setTongTienThue(Integer.parseInt(rs.getString(5)));
                p.setTongTienDV(Integer.parseInt(rs.getString(6)));
                p.setThue(Integer.parseInt(rs.getString(7)));
                p.setTongCong(Integer.parseInt(rs.getString(8)));
                p.setTongTienKM(Integer.parseInt(rs.getString(9)));
                p.setTongPhaiTra(Integer.parseInt(rs.getString(10)));
                p.setTinhTrang(Integer.parseInt(rs.getString(11)));
                dshd.add(p);
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return dshd;
    }
    public ArrayList<HoaDonDTO> getDanhSachTKthang(String m, String y){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<HoaDonDTO> dshd=new ArrayList<>();
        String sql="select * from hoa_don where Month(NgayLapHD)='"+m+"' && YEAR(NgayLapHD)='"+y+"' && TinhTrang=2";
        try{
            ResultSet rs=kn.getselect(sql);
            while (rs.next()){
                HoaDonDTO p=new HoaDonDTO();
                p.setMaHD(rs.getString(1));
                p.setMaPDP(rs.getString(2));
                p.setMaNV(rs.getString(3));
                p.setNgayLapHD(rs.getString(4));
                p.setTongTienThue(Integer.parseInt(rs.getString(5)));
                p.setTongTienDV(Integer.parseInt(rs.getString(6)));
                p.setThue(Integer.parseInt(rs.getString(7)));
                p.setTongCong(Integer.parseInt(rs.getString(8)));
                p.setTongTienKM(Integer.parseInt(rs.getString(9)));
                p.setTongPhaiTra(Integer.parseInt(rs.getString(10)));
                p.setTinhTrang(Integer.parseInt(rs.getString(11)));
                dshd.add(p);
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return dshd;
    }
    public ArrayList<HoaDonDTO> getDanhSachTKnam(String y){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<HoaDonDTO> dshd=new ArrayList<>();
        String sql="select * from hoa_don where YEAR(NgayLapHD)='"+y+"' && TinhTrang=2";
        try{
            ResultSet rs=kn.getselect(sql);
            while (rs.next()){
                HoaDonDTO p=new HoaDonDTO();
                p.setMaHD(rs.getString(1));
                p.setMaPDP(rs.getString(2));
                p.setMaNV(rs.getString(3));
                p.setNgayLapHD(rs.getString(4));
                p.setTongTienThue(Integer.parseInt(rs.getString(5)));
                p.setTongTienDV(Integer.parseInt(rs.getString(6)));
                p.setThue(Integer.parseInt(rs.getString(7)));
                p.setTongCong(Integer.parseInt(rs.getString(8)));
                p.setTongTienKM(Integer.parseInt(rs.getString(9)));
                p.setTongPhaiTra(Integer.parseInt(rs.getString(10)));
                p.setTinhTrang(Integer.parseInt(rs.getString(11)));
                dshd.add(p);
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return dshd;
    }
    public ArrayList<HoaDonDTO> getDanhSachTKquy(int q, String y){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<HoaDonDTO> dshd=new ArrayList<>();
        String sql="";
        if(q==0) {
            sql="select * from hoa_don where YEAR(NgayLapHD)='"+y+"' && TinhTrang=2";
        } else if (q==1) {
            sql="select * from hoa_don where (Month(NgayLapHD)='"+1+"' || Month(NgayLapHD)='"+2+"' || Month(NgayLapHD)='"+3+"') && YEAR(NgayLapHD)='"+y+"' && TinhTrang=2";
        } else if (q==2) {
            sql="select * from hoa_don where (Month(NgayLapHD)='"+4+"' || Month(NgayLapHD)='"+5+"' || Month(NgayLapHD)='"+6+"') && YEAR(NgayLapHD)='"+y+"' && TinhTrang=2";
        } else if (q==3) {
            sql="select * from hoa_don where (Month(NgayLapHD)='"+7+"' || Month(NgayLapHD)='"+8+"' || Month(NgayLapHD)='"+9+"') && YEAR(NgayLapHD)='"+y+"' && TinhTrang=2";
        } else {
            sql="select * from hoa_don where (Month(NgayLapHD)='"+10+"' || Month(NgayLapHD)='"+11+"' || Month(NgayLapHD)='"+12+"') && YEAR(NgayLapHD)='"+y+"' && TinhTrang=2";
        }
        try{
            ResultSet rs=kn.getselect(sql);
            while (rs.next()){
                HoaDonDTO p=new HoaDonDTO();
                p.setMaHD(rs.getString(1));
                p.setMaPDP(rs.getString(2));
                p.setMaNV(rs.getString(3));
                p.setNgayLapHD(rs.getString(4));
                p.setTongTienThue(Integer.parseInt(rs.getString(5)));
                p.setTongTienDV(Integer.parseInt(rs.getString(6)));
                p.setThue(Integer.parseInt(rs.getString(7)));
                p.setTongCong(Integer.parseInt(rs.getString(8)));
                p.setTongTienKM(Integer.parseInt(rs.getString(9)));
                p.setTongPhaiTra(Integer.parseInt(rs.getString(10)));
                p.setTinhTrang(Integer.parseInt(rs.getString(11)));
                dshd.add(p);
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return dshd;
    }
    public ArrayList<HoaDonDTO> getDanhSachTKkhoangthoigian(String d1, String d2){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<HoaDonDTO> dshd=new ArrayList<>();
        String sql="select * from hoa_don where '"+d1+"'<=NgayLapHD && NgayLapHD<='"+d2+"' && TinhTrang=2";
        try{
            ResultSet rs=kn.getselect(sql);
            while (rs.next()){
                HoaDonDTO p=new HoaDonDTO();
                p.setMaHD(rs.getString(1));
                p.setMaPDP(rs.getString(2));
                p.setMaNV(rs.getString(3));
                p.setNgayLapHD(rs.getString(4));
                p.setTongTienThue(Integer.parseInt(rs.getString(5)));
                p.setTongTienDV(Integer.parseInt(rs.getString(6)));
                p.setThue(Integer.parseInt(rs.getString(7)));
                p.setTongCong(Integer.parseInt(rs.getString(8)));
                p.setTongTienKM(Integer.parseInt(rs.getString(9)));
                p.setTongPhaiTra(Integer.parseInt(rs.getString(10)));
                p.setTinhTrang(Integer.parseInt(rs.getString(11)));
                dshd.add(p);
            }
        }catch (Exception e){
            e.getStackTrace();
        }
        return dshd;
    }
    //--------------------- Sửa 1 hàng hóa ------------------------------//
     public void UpdateTienHD(HoaDonDTO pnh ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="UPDATE hoa_don SET TongTienThue='"+pnh.getTongTienThue()+"',TongTienDV='"+pnh.getTongTienDV()+"',TongCong='"+pnh.getTongCong()+"',TongTienKM='"+pnh.getTongTienKM()+"',TongPhaiTra='"+pnh.getTongPhaiTra()+"' WHERE hoa_don.TinhTrang=1 AND hoa_don.MaHD='"+pnh.getMaHD()+"'";
            kn.setchange(qry);
    }
     public void them(HoaDonDTO p){
        String sql="INSERT INTO hoa_don(MaHD, MaPDP, MaNV, NgayLapHD, TongTienThue, TongTienDV, Thue, TongCong, TongTienKM, TongPhaiTra, TinhTrang)"
                + " VALUES ('"+p.getMaHD()+"','"+p.getMaPDP()+"','"+p.getMaNV()+"','"+p.getNgayLapHD()+"',"+p.getTongTienThue()+","+p.getTongTienDV()+","+p.getThue()+","+p.getTongCong()+","+p.getTongTienKM()+","+p.getTongPhaiTra()+","+p.getTinhTrang()+")";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
    public int MaCuoiDS(){
        int sl=0;
        ResultSet rs;
        KetnoiCSDL kn = new KetnoiCSDL();
        try{
            String sql="select count(MaHD) from hoa_don";
            rs=kn.getselect(sql);
            while(rs.next()){
                return Integer.parseInt(rs.getString(1));
            }
        }catch(SQLException ex){
            ex.getStackTrace();
        }
        return 0;
    }
    public void SuaHoaDon(HoaDonDTO p){
        String sql="UPDATE hoa_don SET TongTienThue="+p.getTongTienThue()+",TongTienDV="+p.getTongTienDV()+",TongCong="+p.getTongCong()+",TongTienKM="+p.getTongTienKM()+" , TongPhaiTra="+p.getTongPhaiTra()+" WHERE MaHD='"+p.getMaHD()+"'";
        KetnoiCSDL kn=new KetnoiCSDL();
        kn.setchange(sql);
    }
}
