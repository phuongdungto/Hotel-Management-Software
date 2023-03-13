/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;


import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author camdu
 */
public class NhaCungCapBUS {
    public static ArrayList<NhaCungCapDTO> dsncc;
     public NhaCungCapBUS(){
        
    }
//------------------ Đọc danh sách nhà cung cấp -------------------------//
    public ArrayList<NhaCungCapDTO> docdsncc(){
        NhaCungCapDAO p=new NhaCungCapDAO();
        dsncc=p.getDanhsach();
        return dsncc;
    }
//--------------------- Lấy 1 nhà cung cấp ------------------------------//
    public NhaCungCapDTO LayThongtin1NCC(String mancc){
        for (NhaCungCapDTO k : NhaCungCapBUS.dsncc){
            if (k.getMaNCC().equals(mancc)){
                return k;
            }
        }
        return null;
    }

//--------------------- Lấy cuối ds ------------------------------//
    public int LayMaCuoiDS(){
        NhaCungCapDAO ncc = new NhaCungCapDAO();
        int sl =0;
        sl =ncc.MaCuoiDS();
        return sl;
    }
//--------------------- Thêm 1 hàng hóa ------------------------------//
     public int themNCC(NhaCungCapDTO ncc ){
        NhaCungCapDAO nccDAO = new NhaCungCapDAO();
        nccDAO.themnhacungcap(ncc);
        int i = NhaCungCapBUS.dsncc.size();
        NhaCungCapBUS.dsncc.add(ncc);
        int i1 = NhaCungCapBUS.dsncc.size();
        if(i1>i){
            return 1;
        }
        return 0;
    }
//--------------------- Xóa 1 nhà cung cấp ------------------------------//
     public int xoaNCC(NhaCungCapDTO ncc ){
        NhaCungCapDAO hhDAO = new NhaCungCapDAO();
        hhDAO.xoanhacungcap(ncc);
        int k=NhaCungCapBUS.dsncc.size();
        int i =0;
       for(NhaCungCapDTO ncc2 : NhaCungCapBUS.dsncc){
           if(ncc.getMaNCC().equals(ncc2.getMaNCC())){
               NhaCungCapBUS.dsncc.remove(i);
               break;
           }
           i++;
       }
       int k1= NhaCungCapBUS.dsncc.size();
       if(k1<k){
           return 1;
       }
       return 0;
    }
//--------------------- Sửa 1 hàng hóa ------------------------------//
      public int suaNCC(NhaCungCapDTO ncc ){
        NhaCungCapDAO nccDAO = new NhaCungCapDAO();
        nccDAO.suanhacungcap(ncc);
        int i =0;
       for(NhaCungCapDTO ncc2 : NhaCungCapBUS.dsncc){
           if(ncc2.getMaNCC().equals(ncc.getMaNCC())){
               NhaCungCapBUS.dsncc.set(i, ncc);
               break;
           }
           i++;
       }
       return 1;
    }
//--------------------- Kiểm tra dữ liệu ------------------------------//
     public int KtradulieuNCC(NhaCungCapDTO ncc){
         Xulydulieu xldl = new Xulydulieu();
        if (ncc.getTenNCC().equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập tên nhà cung cấp"); 
            return 0;
        }
        if (ncc.getDiaChi().equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập địa chỉ"); 
            return 0;
        }
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
     public ArrayList<NhaCungCapDTO> Timkiemtheo_TenHH(String timkiem){   
        ArrayList<NhaCungCapDTO> ds = new ArrayList<>();
        for(NhaCungCapDTO hh : NhaCungCapBUS.dsncc){
            if(hh.getTenNCC().toLowerCase().contains(timkiem.toLowerCase()))
                ds.add(hh);
        }
        return ds;
    }

}
