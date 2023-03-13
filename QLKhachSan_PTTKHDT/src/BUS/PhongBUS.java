/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.PhongDAO;
import DTO.PhongDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author T P Dung
 */
public class PhongBUS {
    public static ArrayList<PhongDTO> dsp;
    public void PhongBUS(){
        
    }
    public ArrayList<PhongDTO> docdsp(){
        PhongDAO data=new PhongDAO();
        dsp=data.getDanhSach();
        return dsp;
    }
//--------------------- Lấy 1 phòng ------------------------------//
    public PhongDTO LayThongtin1P(String map){
        for (PhongDTO k : PhongBUS.dsp){
            if (k.getMaPhong().equals(map)){
                return k;
            }
        }
        return null;
    }
    
//--------------------- Lấy cuối ds ------------------------------//
    public int LayMaCuoiDS(){
        PhongDAO hh = new PhongDAO();
        int sl =0;
        sl =hh.MaCuoiDS();
        return sl;
    }
 
//--------------------- Thêm 1 phòng ------------------------------//
     public int themphong(PhongDTO hh ){
        PhongDAO hhDAO = new PhongDAO();
        hhDAO.themPhong(hh);
        int i = PhongBUS.dsp.size();
        PhongBUS.dsp.add(hh);
        int i1 = PhongBUS.dsp.size();
        if(i1>i){
            return 1;
        }
        return 0;
    }    
    
 public ArrayList<PhongDTO> timkiemtheoma(String s){
        ArrayList<PhongDTO> dstk = new  ArrayList<>();
        for(PhongDTO dv1 : PhongBUS.dsp){
            if(dv1.getMaPhong().contains(s)){
                dstk.add(dv1);
            }
        }
        return dstk;
    }
    public ArrayList<PhongDTO> timkiemtheoloai(String s){
        ArrayList<PhongDTO> dstk = new  ArrayList<>();
        for(PhongDTO dv1 : PhongBUS.dsp){
            if(dv1.getMaLoai().contains(s)){
                dstk.add(dv1);
            }
        }
        return dstk;
    }
    
    public ArrayList<PhongDTO> timkiemtheotang(int s){
        ArrayList<PhongDTO> dstk = new  ArrayList<>();
        for(PhongDTO pDTO : PhongBUS.dsp){
            if(pDTO.getTang()==s){
             dstk.add(pDTO);
            }}
        return dstk;
    }
        public ArrayList<PhongDTO> timkiemtheophong(int s){
        ArrayList<PhongDTO> dstk = new  ArrayList<>();
        for(PhongDTO pDTO : PhongBUS.dsp){
            if(pDTO.getTinhTrang()==s){
             dstk.add(pDTO);
            }}
        return dstk;
    }
    
     public ArrayList<PhongDTO> timkiemtheodongia(int s,int s1){
        ArrayList<PhongDTO> dstk = new  ArrayList<>();
        for(PhongDTO dv1 : PhongBUS.dsp){
            if(dv1.getDonGia() >= s && dv1.getDonGia() <= s1 ){
                dstk.add(dv1);
            }
        }
        if(dstk==null){
            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu");
        }
        return dstk;
    }
     public int kiemtradulieu(String s,String s1){
         Xulydulieu xldl = new Xulydulieu();
         if(s.equals("") || s1.equals("")){
               JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ số liệu để tìm kiếm");
               return 0;
           }
          if(xldl.KtraDonGia(s)== false || xldl.KtraDonGia(s1)== false){  
              return 0;
            }
          if(Integer.parseInt(s) > Integer.parseInt(s1)){
            JOptionPane.showMessageDialog(null, "Khoản giá không hợp lệ");
            return 0;
            }
          return 1;
     }
     
//--------------------- Sửa 1 phòng------------------------------//
      public int suaPhong(PhongDTO hh ){
        PhongDAO hhDAO = new PhongDAO();
        hhDAO.suaphong(hh);
        int i =0;
       for(PhongDTO hh2 : PhongBUS.dsp){
           if(hh2.getMaPhong().equals(hh.getMaPhong())){
              PhongBUS.dsp.set(i, hh);
               break;
           }
           i++;
       }
       return 1;
    }
     public int kiemtradulieuSua(String s,String s1,String s2){
         Xulydulieu xldl = new Xulydulieu();
         if(s.equals("") || s1.equals("") || s2.equals("")){
               JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ số liệu để cập nhật");
               return 0;
           }
         if( xldl.KtraSo(s1)==false){
             return 0;
            }
         if(xldl.KtraSo(s)==false){
             return 0;
         }
         if(Integer.parseInt(s) <=0 || Integer.parseInt(s) > 4){
             JOptionPane.showMessageDialog(null, "Tầng phài từ 1->4");
             return 0;
         }
         if(xldl.KtraDonGia(s2)== false ){  
              return 0;
            }
          return 1;
     }
     
       public int kiemtradulieuThem(String s,String s1,String s2){
         Xulydulieu xldl = new Xulydulieu();
         if(s.equals("") || s1.equals("") || s2.equals("")){
               JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ số liệu để thêm");
               return 0;
           }
         if( xldl.KtraSo(s1)==false){
             return 0;
            }
         if(xldl.KtraSo(s)==false){
             return 0;
         }
        if(Integer.parseInt(s) <=0 || Integer.parseInt(s) > 4){
            JOptionPane.showMessageDialog(null, "Tầng phài từ 1->4");
             return 0;
         }
         if(xldl.KtraDonGia(s2)== false ){  
              return 0;
            }
          return 1;
     }
//--------------------- Xóa 1 phòng ------------------------------//
     public int xoaPhong(PhongDTO hh ){
        PhongDAO hhDAO = new PhongDAO();
        hhDAO.xoaPhong(hh);
        int k=PhongBUS.dsp.size();
        int i =0;
       for(PhongDTO hh2 : PhongBUS.dsp){
           if(hh.getMaPhong().equals(hh2.getMaPhong())){
               PhongBUS.dsp.remove(i);
               break;
           }
           i++;
       }
       int k1= PhongBUS.dsp.size();
       if(k1<k){
          return 1;
       }
       return 0;
    }
     public PhongDTO lay1p(String s){
        for(PhongDTO dv1 : PhongBUS.dsp){
            if(dv1.getMaPhong().equals(s)){
                return dv1;
            }
        }
        return null;
    }
}
