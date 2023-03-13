/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.KhachHangDTO;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Duy
 */
public class KhachHangDAO {
    public KhachHangDAO() {
        
    }
    public ArrayList<KhachHangDTO> getDanhsach() {
        KetnoiCSDL kn = new KetnoiCSDL();
        ArrayList<KhachHangDTO> dskh = new ArrayList<>();
        String sql = "select * from khach_hang where `Tinhtrang`!='0'";
        ResultSet rs = kn.getselect(sql);
        try {
            while(rs.next()) {
                KhachHangDTO kh = new KhachHangDTO();
                kh.setMaKH(rs.getString(1));
                kh.setHo(rs.getString(2));
                kh.setTen(rs.getString(3));
                kh.setSdt(rs.getString(4));
                kh.setDiachi(rs.getString(5));
                kh.setNgaysinh(rs.getString(6));
                kh.setGioitinh(Integer.parseInt(rs.getString(7)));
                kh.setTinhTrang(Integer.parseInt(rs.getString(8)));
                dskh.add(kh);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return dskh;
    }
    public void them(KhachHangDTO p) {
        KetnoiCSDL kn = new KetnoiCSDL();
        String sql = "insert into `khach_hang` values('"+p.getMaKH()+"','"+p.getHo()+"', '"+p.getTen()+"', '"+p.getSdt()+"'"
                + ", '"+p.getDiachi()+"', '"+p.getNgaysinh()+"', '"+p.getGioitinh()+"', '1')";
        kn.setchange(sql);
    }
    public void sua(KhachHangDTO p) {
        KetnoiCSDL kn = new KetnoiCSDL();
        String sql = "update `khach_hang` set `MaKH`='"+p.getMaKH()+"',`Ho`='"+p.getHo()+"',`Ten`='"+p.getTen()+"',"
                + "`sdt`='"+p.getSdt()+"', `DiaChi`='"+p.getDiachi()+"', `NgaySinh`='"+p.getNgaysinh()+"',"
                + "`GioiTinh`='"+p.getGioitinh()+"',`TinhTrang`='1' where `MaKH`='"+p.getMaKH()+"'";
        kn.setchange(sql);
    }
    public void xoa(KhachHangDTO p) {
        KetnoiCSDL kn = new KetnoiCSDL();
        String sql = "update `khach_hang` set `TinhTrang`='0' where `MaKH`='"+p.getMaKH()+"'";
        kn.setchange(sql);
    }
    public int layslds() {
        KetnoiCSDL kn = new KetnoiCSDL();
        String sql = "select count(MaKH) from `khach_hang`";
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
    public ArrayList<KhachHangDTO> getDanhsachktra() {
        KetnoiCSDL kn = new KetnoiCSDL();
        ArrayList<KhachHangDTO> dskh = new ArrayList<>();
        String sql = "select * from khach_hang";
        ResultSet rs = kn.getselect(sql);
        try {
            while(rs.next()) {
                KhachHangDTO kh = new KhachHangDTO();
                kh.setMaKH(rs.getString(1));
                kh.setHo(rs.getString(2));
                kh.setTen(rs.getString(3));
                kh.setSdt(rs.getString(4));
                kh.setDiachi(rs.getString(5));
                kh.setNgaysinh(rs.getString(6));
                kh.setGioitinh(Integer.parseInt(rs.getString(7)));
                kh.setTinhTrang(Integer.parseInt(rs.getString(8)));
                dskh.add(kh);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return dskh;
    }
}
