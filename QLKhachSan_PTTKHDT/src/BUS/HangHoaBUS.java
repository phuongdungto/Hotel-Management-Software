/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.HangHoaDAO;
import DTO.HangHoaDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author camdu
 */
public class HangHoaBUS {
     public static ArrayList<HangHoaDTO> dshh;
     public HangHoaBUS(){
        
    }
//------------------ Đọc danh sách hàng hóa -------------------------//
    public ArrayList<HangHoaDTO> docdshh(){
        HangHoaDAO p=new HangHoaDAO();
        dshh=p.getDanhsach();
        return dshh;
    }
//--------------------- Lấy 1 hàng hóa ------------------------------//
    public HangHoaDTO LayThongtin1HH(String mahh){
        for (HangHoaDTO k : HangHoaBUS.dshh){
            if (k.getMaHang().equals(mahh)){
                return k;
            }
        }
        return null;
    }
    
//--------------------- Lấy cuối ds ------------------------------//
    public int LayMaCuoiDS(){
        HangHoaDAO hh = new HangHoaDAO();
        int sl =0;
        sl =hh.MaCuoiDS();
        return sl;
    }
//--------------------- Thêm 1 hàng hóa ------------------------------//
     public int themhanghoa(HangHoaDTO hh ){
        HangHoaDAO hhDAO = new HangHoaDAO();
        hhDAO.themhanghoa(hh);
        int i = HangHoaBUS.dshh.size();
        HangHoaBUS.dshh.add(hh);
        int i1 = HangHoaBUS.dshh.size();
        if(i1>i){
            return 1;
        }
        return 0;
    }
//--------------------- Xóa 1 hàng hóa ------------------------------//
     public int xoaHanghoa(HangHoaDTO hh ){
        HangHoaDAO hhDAO = new HangHoaDAO();
        hhDAO.xoahanghoa(hh);
        int k=HangHoaBUS.dshh.size();
        int i =0;
       for(HangHoaDTO hh2 : HangHoaBUS.dshh){
           if(hh.getMaHang().equals(hh2.getMaHang())){
               HangHoaBUS.dshh.remove(i);
               break;
           }
           i++;
       }
       int k1= HangHoaBUS.dshh.size();
       if(k1<k){
          return 1;
       }
       return 0;
    }
//--------------------- Sửa 1 hàng hóa ------------------------------//
      public int suaHangHoa(HangHoaDTO hh ){
        HangHoaDAO hhDAO = new HangHoaDAO();
        hhDAO.suahanghoa(hh);
        int i =0;
       for(HangHoaDTO hh2 : HangHoaBUS.dshh){
           if(hh2.getMaHang().equals(hh.getMaHang())){
               HangHoaBUS.dshh.set(i, hh);
               break;
           }
           i++;
       }
       return 1;
    }

//--------------------- Lấy SL ------------------------------//
    public int LaySLHH(String mahh){
         int thu =0;
         for(HangHoaDTO ctDTO : dshh){
             if(ctDTO.getMaHang().equals(mahh)){
                 thu = ctDTO.getSL();
             }
         }
         return thu;
     }
//--------------------- Sửa 1 SL hàng hóa ------------------------------//
      public void suaSLHangHoa(String mahh,int soluong){
        HangHoaDAO hhDAO = new HangHoaDAO();
        hhDAO.suaSLhanghoa(mahh,soluong);
    }      
//--------------------- Kiểm tra dữ liệu ------------------------------//
     public int KtradulieuHangHoa(HangHoaDTO hh){
         Xulydulieu xldl = new Xulydulieu();
        if (hh.getTenHang().equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập tên hàng hóa"); 
            return 0;
        }
        if (hh.getLoaiHang().equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập loại hàng hóa"); 
            return 0;
        }
        if (hh.getSL()== 0){
            JOptionPane.showMessageDialog(null, "Số lượng hàng hóa không hợp lệ"); 
            return 0;
        }
        if (hh.getDonGia()== 0){
            JOptionPane.showMessageDialog(null, "Số lượng đơn giá không hợp lệ"); 
            return 0;
        }
        return 1;
    }
     public int KtraSluongDonGia(String sl,String dongia){
         Xulydulieu dl=new Xulydulieu();
         if (sl.equals("")){
             JOptionPane.showMessageDialog(null, "Chưa nhập số lượng");
             return 0;
         }         
         if (dl.KtraSo(sl)==false)
             return 0;
         if (dongia.equals("")){
             JOptionPane.showMessageDialog(null, "Chưa nhập đơn giá");
             return 0;
         }
         if (dl.KtraDonGia(dongia)==false)
             return 0;
         return 1;
     }
//--------------------- Kiểm tra dữ liệu tìm kiếm ------------------------------//
     public int KtradulieuTimKiem(String timkiem){   
        if (timkiem.equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập dữ liệu tìm kiếm"); 
            return 0;
        }
        return 1;
    }
//--------------------- Tìm kiếm dữ liệu ------------------------------//
     public ArrayList<HangHoaDTO> Timkiemtheo_TenHH(String timkiem){   
        ArrayList<HangHoaDTO> ds = new ArrayList<>();
        for(HangHoaDTO hh : HangHoaBUS.dshh){
            if(hh.getTenHang().toLowerCase().contains(timkiem.toLowerCase()))
                ds.add(hh);
        }
        return ds;
    }
}
