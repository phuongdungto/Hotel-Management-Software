/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NhanVienDTO;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Duy
 */
public class NhanVienDAO {
    public NhanVienDAO(){
        
    }
    public ArrayList<NhanVienDTO> getDanhsach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        Connection con=kn.getKetnoi();
        ArrayList<NhanVienDTO> dsnv=new ArrayList<>();
        String sql="select * from nhan_vien where TinhTrang!=0";
        try{
            Statement smt=con.createStatement();
            ResultSet rs=smt.executeQuery(sql);
            while(rs.next()){
                NhanVienDTO nv=new NhanVienDTO();
                nv.setMaNV(rs.getString(1));
                nv.setHo(rs.getString(2));
                nv.setTen(rs.getString(3));
                nv.setSdt(rs.getString(4));
                nv.setNgaysinh(rs.getString(5));
                nv.setGioitinh(Integer.parseInt(rs.getString(6)));
                nv.setDiachi(rs.getString(7));
                nv.setTienLuong(Integer.parseInt(rs.getString(8)));
                nv.setTinhTrang(Integer.parseInt(rs.getString(9)));
                dsnv.add(nv);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        return dsnv;
    }
    public void them(NhanVienDTO p) {
        KetnoiCSDL kn = new KetnoiCSDL();
        String sql = "insert into `nhan_vien` values('"+p.getMaNV()+"','"+p.getHo()+"', '"+p.getTen()+"', '"+p.getSdt()+"'"
                + ", '"+p.getNgaysinh()+"', '"+p.getGioitinh()+"', '"+p.getDiachi()+"', '"+p.getTienLuong()+"', '1')";
        kn.setchange(sql);
    }
    public void sua(NhanVienDTO p) {
        KetnoiCSDL kn = new KetnoiCSDL();
        String sql = "update `nhan_vien` set `MaNV`='"+p.getMaNV()+"',`Ho`='"+p.getHo()+"',`Ten`='"+p.getTen()+"',"
                + "`sdt`='"+p.getSdt()+"', `NgaySinh`='"+p.getNgaysinh()+"',`GioiTinh`='"+p.getGioitinh()+"',"
                + "`DiaChi`='"+p.getDiachi()+"', `TienLuong`='"+p.getTienLuong()+"',"
                + "`TinhTrang`='1' where `MaNV`='"+p.getMaNV()+"'";
        kn.setchange(sql);
    }
    public void xoa(NhanVienDTO p) {
        KetnoiCSDL kn = new KetnoiCSDL();
        String sql = "update `nhan_vien` set `TinhTrang`='0' where `MaNV`='"+p.getMaNV()+"'";
        kn.setchange(sql);
    }
    
    public int layslds() {
        KetnoiCSDL kn = new KetnoiCSDL();
        String sql = "select count(MaNV) from `nhan_vien`";
        ResultSet rs = kn.getselect(sql);
        int d = 0;
        try {
            while(rs.next()) {
                d=Integer.parseInt(rs.getString(1));
            }
        } catch(Exception e) {
            e.getStackTrace();
        }
        return d;
    }
//    public static void main(String[] args) {
//        NhanVienDAO p=new NhanVienDAO();
//    }
}
