/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import java.util.ArrayList;

/**
 *
 * @author camdu
 */
public class ChiTietPhieuNhapBUS {
    public static ArrayList<ChiTietPhieuNhapDTO> dsctpnh;


     public ChiTietPhieuNhapBUS(){
        
    }
     public ArrayList<ChiTietPhieuNhapDTO> docalldsctpnh(){
        ChiTietPhieuNhapDAO p=new ChiTietPhieuNhapDAO();
        dsctpnh=p.getDanhsachall();
        return dsctpnh;
    }
     //------------------ Đọc danh sách hàng hóa -------------------------//
    public ArrayList<ChiTietPhieuNhapDTO> docdsctpnh(String mapnh){
        ChiTietPhieuNhapDAO p=new ChiTietPhieuNhapDAO();
        ArrayList<ChiTietPhieuNhapDTO> dsctpnh1=p.getDanhsach(mapnh);
        return dsctpnh1;
    }
//--------------------- Lấy 1 hàng hóa ------------------------------//
    public ChiTietPhieuNhapDTO LayThongtin1CTPN(String mapnh,String mahh){
        for (ChiTietPhieuNhapDTO k : ChiTietPhieuNhapBUS.dsctpnh){
            if (k.getMaPNH().equals(mapnh) && k.getMaHang().equals(mahh)){
                return k;
            }
        }
        return null;
    }
//--------------------- Kiểm tra hàng hóa trùng ----------------------------//
     public int kiemtraHHTrung(ArrayList<ChiTietPhieuNhapDTO> dsctpnhh,String test){
         int thu =0;
         for(ChiTietPhieuNhapDTO ctDTO : dsctpnhh){
             if(ctDTO.getMaHang().equals(test)){
                 thu = 1;
             }
         }
         return thu;
     }
//--------------------- Thêm 1 CTPNH ------------------------------//
     public int themCTPNH(ChiTietPhieuNhapDTO pnh ){
        ChiTietPhieuNhapDAO ctpnhDAO = new ChiTietPhieuNhapDAO();
        ctpnhDAO.themCTPNH(pnh);
        int i = ChiTietPhieuNhapBUS.dsctpnh.size();
        ChiTietPhieuNhapBUS.dsctpnh.add(pnh);
        int i1 = ChiTietPhieuNhapBUS.dsctpnh.size();
        if(i1>i){
            return 1;
        }
        return 0;
    }
//--------------------- Xóa 1 CTPNH ------------------------------//
     public int XoaCTPNH(ChiTietPhieuNhapDTO pnh ){
        ChiTietPhieuNhapDAO ctpnhDAO = new ChiTietPhieuNhapDAO();
        ctpnhDAO.xoaCTPNH(pnh);
        int i = ChiTietPhieuNhapBUS.dsctpnh.size();
        ChiTietPhieuNhapBUS.dsctpnh.add(pnh);
        int i1 = ChiTietPhieuNhapBUS.dsctpnh.size();
        if(i1>i){
            return 1;
        }
        return 0;
    }
//--------------------- Sửa  ------------------------------//
      public void sua(String MaPNH,int soluong,String MaHang,int Tien){
        ChiTietPhieuNhapDAO ctpnhDAO = new ChiTietPhieuNhapDAO();
        ctpnhDAO.suaCTPNH(MaPNH, soluong, MaHang, Tien);
    }
//--------------------- Sửa SL ------------------------------//
    public int LaySLHH(ArrayList<ChiTietPhieuNhapDTO> dsctpnhh,String test){
         int thu =0;
         for(ChiTietPhieuNhapDTO ctDTO : dsctpnhh){
             if(ctDTO.getMaHang().equals(test)){
                 thu = ctDTO.getSL();
             }
         }
         return thu;
     }
//--------------------- Sửa Tien ------------------------------//
    public int LayTienHH(ArrayList<ChiTietPhieuNhapDTO> dsctpnhh,String test){
         int thu =0;
         for(ChiTietPhieuNhapDTO ctDTO : dsctpnhh){
             if(ctDTO.getMaHang().equals(test)){
                 thu = ctDTO.getThanhTien();
             }
         }
         return thu;
     }
//--------------------- Tổng tiền ------------------------------//
    public int TongTienPNH(ArrayList<ChiTietPhieuNhapDTO> dsctpnhh){
         int thu =0;
         for(ChiTietPhieuNhapDTO ctDTO : dsctpnhh){
                 thu += ctDTO.getThanhTien();
         }
         return thu;
     }
}
