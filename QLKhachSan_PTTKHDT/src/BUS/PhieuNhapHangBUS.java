/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import static BUS.NhanVienBUS.dsnv;
import DAO.PhieuNhapHangDAO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapHangDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author camdu
 */
public class PhieuNhapHangBUS {
     public static ArrayList<PhieuNhapHangDTO> dspnh;
     public PhieuNhapHangBUS(){
        
    }
//------------------ Đọc danh sách phiếu nhập hàng -------------------------//
    public ArrayList<PhieuNhapHangDTO> docdspnh(){
        PhieuNhapHangDAO p=new PhieuNhapHangDAO();
        dspnh=p.getDanhsach();
        return dspnh;
    }
//--------------------- Lấy 1 phiếu nhập hàng ------------------------------//
    public PhieuNhapHangDTO LayThongtin1PNH(String mapnh){
        for (PhieuNhapHangDTO k : PhieuNhapHangBUS.dspnh){
            if (k.getMaPNH().equals(mapnh)){
                return k;
            }
        }
        return null;
    }

//--------------------- Lấy cuối ds ------------------------------//
    public int LayMaCuoiDS(){
        PhieuNhapHangDAO ncc = new PhieuNhapHangDAO();
        int sl =0;
        sl =ncc.MaCuoiDS();
        return sl;
    }    
    
//--------------------- Thêm 1 hàng hóa ------------------------------//
     public int thempnh(PhieuNhapHangDTO pnh ){
        PhieuNhapHangDAO pnhDAO = new PhieuNhapHangDAO();
        pnhDAO.themphieunhaphang(pnh);
        int i = PhieuNhapHangBUS.dspnh.size();
        PhieuNhapHangBUS.dspnh.add(pnh);
        int i1 = PhieuNhapHangBUS.dspnh.size();
        if(i1>i){
            return 1;
        }
        return 0;
    }

//--------------------- Xóa 1 phiếu nhập hàng ------------------------------//
     public int xoaPNH(PhieuNhapHangDTO pnh ){
        PhieuNhapHangDAO pnhDAO = new PhieuNhapHangDAO();
        pnhDAO.xoaPNH(pnh);
        int k=PhieuNhapHangBUS.dspnh.size();
        int i =0;
       for(PhieuNhapHangDTO pnh2 : PhieuNhapHangBUS.dspnh){
           if(pnh.getMaNCC().equals(pnh2.getMaNCC())){
               PhieuNhapHangBUS.dspnh.remove(i);
               break;
           }
           i++;
       }
       int k1= PhieuNhapHangBUS.dspnh.size();
       if(k1<k){
           return 1;
       }
       return 0;
    }
//--------------------- Sửa 1 hàng hóa ------------------------------//
      public int suaPNH(PhieuNhapHangDTO pnh ){
        PhieuNhapHangDAO pnhDAO = new PhieuNhapHangDAO();
        pnhDAO.suaphieunhaphang(pnh);
        int i =0;
       for(PhieuNhapHangDTO pnh2 : PhieuNhapHangBUS.dspnh){
           if(pnh.getMaPNH().equals(pnh.getMaPNH())){
               PhieuNhapHangBUS.dspnh.set(i, pnh);
               break;
           }
           i++;
       }
       return 1;
    }
//----------------------------- Tìm kiếm theo Ngày -----------------------------//
    public ArrayList<PhieuNhapHangDTO> timkiemNgay(String tungay,String denngay) {
            int ktra=0;
           PhieuNhapHangDAO pnhdao = new PhieuNhapHangDAO();
            ArrayList<PhieuNhapHangDTO> dstkpnh = new ArrayList<>();
            dstkpnh=pnhdao.listIdVoucherDay(tungay, denngay);
            if(dstkpnh.size()==0){
                ktra=1;
            }
            if(ktra==1){
                JOptionPane.showMessageDialog(null, "Không có dữ liệu tìm kiếm !!!");
            }
        return dstkpnh;
    }
    
//----------------------------- Tìm kiếm theo Mã NCC -----------------------------//
     public ArrayList<PhieuNhapHangDTO> Timkiemtheo_maNCC(String timkiem){   
        ArrayList<PhieuNhapHangDTO> ds = new ArrayList<>();
        for(PhieuNhapHangDTO ncc : PhieuNhapHangBUS.dspnh){
            if(ncc.getMaNCC().contains(timkiem))
                ds.add(ncc);
        }
        return ds;
    }
     
//----------------------------- Tìm kiếm theo Mã NV -----------------------------//
     public ArrayList<PhieuNhapHangDTO> Timkiemtheo_maNV(String timkiem){   
        ArrayList<PhieuNhapHangDTO> ds = new ArrayList<>();
        for(PhieuNhapHangDTO ncc : PhieuNhapHangBUS.dspnh){
            if(ncc.getMaNV().contains(timkiem))
                ds.add(ncc);
        }
        return ds;
    }
     
  public int KiemtraDuLieu(PhieuNhapHangDTO pnh){
      if(pnh.getMaNV().equals("")){
          JOptionPane.showMessageDialog(null, "Vui lòng chọn Mã nhân viên trong bảng");
          return 0;
      }
      if(pnh.getMaNCC().equals("")){
          JOptionPane.showMessageDialog(null, "Vui lòng chọn Mã nhà cung cấp trong bảng");
          return 0;
      }
      if(pnh.getNgayNhap().equals("")){
          JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày nhập");
          return 0;
      }
      return 1;
  }
}
