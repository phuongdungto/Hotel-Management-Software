/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.DichVuDTO;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author Duy
 */
public class DichVuDAO {
    public DichVuDAO() {
        
    }
    public ArrayList<DichVuDTO> getDanhsach() {
        KetnoiCSDL kn = new KetnoiCSDL();
        ArrayList<DichVuDTO> dsdv = new ArrayList<>();
        String sql = "select * from dich_vu where `TinhTrang`!='0'";
        ResultSet rs = kn.getselect(sql);
        try {
            while(rs.next()) {
                DichVuDTO dv = new DichVuDTO();
                dv.setMaDV(rs.getString(1));
                dv.setTenDV(rs.getString(2));
                dv.setDonGia(Integer.parseInt(rs.getString(3)));
                dv.setTinhTrang(Integer.parseInt(rs.getString(4)));
                dsdv.add(dv);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return dsdv;
    }
    public void them(DichVuDTO p) {
        KetnoiCSDL kn = new KetnoiCSDL();
        String sql = "insert into `dich_vu` values('"+p.getMaDV()+"','"+p.getTenDV()+"', '"+p.getDonGia()+"', '1')";
        kn.setchange(sql);
    }
    public void sua(DichVuDTO p) {
        KetnoiCSDL kn = new KetnoiCSDL();
        String sql = "update `dich_vu` set `MaDV`='"+p.getMaDV()+"',`TenDV`='"+p.getTenDV()+"',`DonGia`='"+p.getDonGia()+"',"
                + "`TinhTrang`='1'where `MaDV`='"+p.getMaDV()+"'";
        kn.setchange(sql);
    }
    public void xoa(DichVuDTO p) {
        KetnoiCSDL kn = new KetnoiCSDL();
        String sql = "update `dich_vu` set `TinhTrang`='0' where `MaDV`='"+p.getMaDV()+"'";
        kn.setchange(sql);
    }
    public int layslds() {
        KetnoiCSDL kn = new KetnoiCSDL();
        String sql = "select count(MaDV) from `dich_vu`";
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
   
}
