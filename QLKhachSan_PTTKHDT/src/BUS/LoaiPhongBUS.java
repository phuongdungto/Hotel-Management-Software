/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.LoaiPhongDAO;
import DTO.LoaiPhongDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author T P Dung
 */
 public class LoaiPhongBUS {
    public static ArrayList<LoaiPhongDTO> dslp;
    public LoaiPhongBUS(){
        
    }
    public ArrayList<LoaiPhongDTO> docdslp(){
        LoaiPhongDAO p=new LoaiPhongDAO();
        dslp=p.getDanhSach();
        return dslp;
    }
    public void them(LoaiPhongDTO p){
        LoaiPhongDAO data = new LoaiPhongDAO();
        data.them(p);
        int i=LoaiPhongBUS.dslp.size();
        LoaiPhongBUS.dslp.add(p);
        int i1=LoaiPhongBUS.dslp.size();
        if (i1>i)
            JOptionPane.showMessageDialog(null, "Thêm thành công");
    }
    public void sua(LoaiPhongDTO p,LoaiPhongDTO p1){
        LoaiPhongDAO data = new LoaiPhongDAO();
        data.sua(p,p1);
        int i=0;
        if(p.getMaLoai().equals(p1.getMaLoai()) && !p.getTenLoai().equals(p1.getTenLoai())){
            for(LoaiPhongDTO dv1 : LoaiPhongBUS.dslp){
                if(p.getMaLoai().equals(dv1.getMaLoai())){
                    LoaiPhongBUS.dslp.set(i, p1);
                    JOptionPane.showMessageDialog(null, "Sửa thành công");
                    break;
                }
                i++;
            }
        }else if(kiemtratrung(p1)==1){
            for(LoaiPhongDTO dv1 : LoaiPhongBUS.dslp){
                if(p.getMaLoai().equals(dv1.getMaLoai())){
                    LoaiPhongBUS.dslp.set(i, p1);
                    JOptionPane.showMessageDialog(null, "Sửa thành công");
                    break;
                }
                i++;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
        }
    }
    public void xoa(LoaiPhongDTO p){
        int input=JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa", "Xóa tài khoản", JOptionPane.YES_NO_OPTION);
        if(input==0){
            LoaiPhongDAO data = new LoaiPhongDAO();
            data.xoa(p);
            int k=LoaiPhongBUS.dslp.size();
            int i=0;
            for(LoaiPhongDTO dv1 : LoaiPhongBUS.dslp){
                if(p.getMaLoai().equals(dv1.getMaLoai())){
                    LoaiPhongBUS.dslp.remove(i);
                    break;
                }
                i++;
            }
            int k1=LoaiPhongBUS.dslp.size();
            if (k1<k)
                JOptionPane.showMessageDialog(null, "Xóa thành công");
        }
    }
    public int kiemtratrung(LoaiPhongDTO p){
        for(LoaiPhongDTO dv1 : LoaiPhongBUS.dslp){
            if(p.getMaLoai().equals(dv1.getMaLoai())){
                if(dv1.getMaLoai().equals(p.getMaLoai()) || dv1.getTenLoai().equals(p.getTenLoai()))
                    return 0;
            }
        }
        return 1;
    }
    public ArrayList<LoaiPhongDTO> timkiemtheoma(String s){
        ArrayList<LoaiPhongDTO> dstk = new  ArrayList<>();
        for(LoaiPhongDTO dv1 : LoaiPhongBUS.dslp){
            if(dv1.getMaLoai().contains(s)){
                dstk.add(dv1);
            }
        }
        return dstk;
    }
    public ArrayList<LoaiPhongDTO> timkiemtheoten(String s){
        ArrayList<LoaiPhongDTO> dstk = new  ArrayList<>();
        for(LoaiPhongDTO dv1 : LoaiPhongBUS.dslp){
            if(dv1.getTenLoai().contains(s)){
                dstk.add(dv1);
            }
        }
        return dstk;
    }
}
