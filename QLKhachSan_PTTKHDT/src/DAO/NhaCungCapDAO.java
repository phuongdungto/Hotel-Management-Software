/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhaCungCapDTO;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author camdu
 */
public class NhaCungCapDAO {
    ResultSet rs;
    public NhaCungCapDAO(){
        
    }
    public ArrayList<NhaCungCapDTO> getDanhsach(){
        KetnoiCSDL kn=new KetnoiCSDL();
        ArrayList<NhaCungCapDTO> dsncc=new ArrayList<>();
        String sql="select * from nha_cung_cap";
        try{
            ResultSet rs=kn.getselect(sql);
            while(rs.next()){
                NhaCungCapDTO ncc=new NhaCungCapDTO();
                ncc.setMaNCC(rs.getString(1));
                ncc.setTenNCC(rs.getString(2));
                ncc.setDiaChi(rs.getString(3));
                ncc.setSdt(rs.getString(4));
                ncc.setTinhTrang(Integer.parseInt(rs.getString(5)));
                dsncc.add(ncc);
            }
        }catch(Exception e){
            e.getStackTrace();
        }
        return dsncc;
    }
//--------------------- Thêm 1 nhà cung cấp ------------------------------//
     public void themnhacungcap(NhaCungCapDTO ncc ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="INSERT INTO `nha_cung_cap` (`MaNCC`, `TenNCC`, `DiaChi`, `sdt`, `TinhTrang`) VALUES ('"+ncc.getMaNCC()+"', '"+ncc.getTenNCC()+"', '"+ncc.getDiaChi()+"', '"+ncc.getSdt()+"', '"+ncc.getTinhTrang()+"')";
            kn.setchange(qry);
    }    
 //--------------------- Sửa 1 nhà cung cấp ------------------------------//
     public void suanhacungcap(NhaCungCapDTO ncc ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="UPDATE `nha_cung_cap` SET `MaNCC`='"+ncc.getMaNCC()+"',`TenNCC`='"+ncc.getTenNCC()+"',`DiaChi`='"+ncc.getDiaChi()+"',`sdt`="+ncc.getSdt()+",`TinhTrang`="+ncc.getTinhTrang()+" WHERE `nha_cung_cap`.`MaNCC`='"+ncc.getMaNCC()+"'";
            kn.setchange(qry);
    }
 //--------------------- Xóa 1 nhacungcap ------------------------------//
     public void xoanhacungcap(NhaCungCapDTO ncc ){
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="UPDATE `nha_cung_cap` SET `TinhTrang`="+0+" WHERE `nha_cung_cap`.`MaNCC`='"+ncc.getMaNCC()+"'";
             kn.setchange(qry);
    }
    
    public int MaCuoiDS(){
        int sl=0;
            KetnoiCSDL kn=new KetnoiCSDL();
            String qry="select count(MaNCC) from nha_cung_cap";
            rs=kn.getselect(qry);
        try {
            while(rs.next()){
                sl= Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sl;
    }
   
}
