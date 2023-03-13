/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietKMPhongDAO;
import DTO.ChiTietKMPhongDTO;
import DTO.KhuyenMaiPhongDTO;
import DTO.PhongDTO;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author T P Dung
 */
public class ChiTietKMPhongBUS {
    public static ArrayList<ChiTietKMPhongDTO> dsctkmp;
    public Pattern patso= Pattern.compile("^[0-9]{1,3}$");
    public ChiTietKMPhongBUS(){
        
    }
    public ArrayList<ChiTietKMPhongDTO> docdsctkmp(){
        ChiTietKMPhongDAO p=new ChiTietKMPhongDAO();
        dsctkmp=p.getDanhSach();
        return dsctkmp;
    }
    public void them(ChiTietKMPhongDTO p){
        ChiTietKMPhongDAO data = new ChiTietKMPhongDAO();
        data.them(p);
        int i=ChiTietKMPhongBUS.dsctkmp.size();
        ChiTietKMPhongBUS.dsctkmp.add(p);
        int i1=ChiTietKMPhongBUS.dsctkmp.size();
        if (i1>i)
            JOptionPane.showMessageDialog(null, "Thêm thành công");
    }
    public int sua(ChiTietKMPhongDTO p,ChiTietKMPhongDTO p2){
        ChiTietKMPhongDAO data = new ChiTietKMPhongDAO();
        if(p.getMaPhong().equals(p2.getMaPhong()) && p.getMaKMPhong().equals(p2.getMaKMPhong()) && p.getPhanTramKM()!= p2.getPhanTramKM()){
            int i=0;
            for(ChiTietKMPhongDTO dv1 : ChiTietKMPhongBUS.dsctkmp){
                if(p.getMaPhong().equals(dv1.getMaPhong()) && p.getMaKMPhong().equals(dv1.getMaKMPhong())){
                    data.sua(p,p2);
                    ChiTietKMPhongBUS.dsctkmp.set(i, p2);
                    JOptionPane.showMessageDialog(null, "Sửa thành công");
                    System.out.println("th1");
                    return 1;
                }
                i++;
            }
        }else if(!p.getMaPhong().equals(p2.getMaPhong()) && p.getMaKMPhong().equals(p2.getMaKMPhong())){    
            if(ktratrung(p2)==1){
                int i=0;
                for(ChiTietKMPhongDTO dv1 : ChiTietKMPhongBUS.dsctkmp){
                    if(p.getMaPhong().equals(dv1.getMaPhong()) && p.getMaKMPhong().equals(dv1.getMaKMPhong())){
                        data.sua(p,p2);
                        ChiTietKMPhongBUS.dsctkmp.set(i, p2);
                        JOptionPane.showMessageDialog(null, "Sửa thành công");
                        System.out.println("th2");
                        return 1;
                    }
                    i++;
                }
            }else{
                JOptionPane.showMessageDialog(null, "Dịch vụ đã tồn tại");
                return 0;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
            return 0;
        }
        return 0;
    }
    public void xoa1chitiet(ChiTietKMPhongDTO p){
        ChiTietKMPhongDAO data = new ChiTietKMPhongDAO();
        data.xoa1chitiet(p);
        int k=ChiTietKMPhongBUS.dsctkmp.size();
        int i=0;
        for(ChiTietKMPhongDTO dv1 : ChiTietKMPhongBUS.dsctkmp){
            if(p.getMaKMPhong().equals(dv1.getMaKMPhong()) && p.getMaPhong().equals(dv1.getMaPhong())){
                ChiTietKMPhongBUS.dsctkmp.remove(i);
                break;
            }
            i++;
        }
        int k1=ChiTietKMPhongBUS.dsctkmp.size();
        if (k1<k)
            JOptionPane.showMessageDialog(null, "Xóa thành công");
    }
    public void xoatatca(KhuyenMaiPhongDTO p){
        ChiTietKMPhongDAO data = new ChiTietKMPhongDAO();
        data.xoatatcachitiet(p);
        int i=0;
        for(ChiTietKMPhongDTO dv1 : ChiTietKMPhongBUS.dsctkmp){
            if(p.getMaKMPhong().equals(dv1.getMaKMPhong())){
                ChiTietKMPhongBUS.dsctkmp.remove(i);
            }
            i++;
        }
    }
    public String lay1phong(String s){
        PhongBUS p = new PhongBUS();
        if(PhongBUS.dsp==null){
            p.docdsp();
        }
        for(PhongDTO p1: PhongBUS.dsp){
            if(s.equals(p1.getMaPhong())){
                return p1.getMaLoai();
            }
        }
        return null;
    }
    public int ktratrung(ChiTietKMPhongDTO p){
        for(ChiTietKMPhongDTO dv1 : ChiTietKMPhongBUS.dsctkmp){
            if(p.getMaKMPhong().equals(dv1.getMaKMPhong())){
                if(p.getMaPhong().equals(dv1.getMaPhong())){
                    return 0;
                }
            }
        }
        return 1;
    }
    public int ktraphantram(String s){
        Matcher m = patso.matcher(s);
        if(m.matches()==false|| Integer.parseInt(s)>100){
            JOptionPane.showMessageDialog(null, "Chỉ được nhập số và nhỏ hơn 100");
            return 0;
        }
       return 1;
    }
    public ChiTietKMPhongDTO lay1ctkm(String s){
        ChiTietKMPhongDTO p =new ChiTietKMPhongDTO();
        ChiTietKMPhongDAO data = new ChiTietKMPhongDAO();
        p=data.lay1ctkm(s);
        return p;
    }
}
