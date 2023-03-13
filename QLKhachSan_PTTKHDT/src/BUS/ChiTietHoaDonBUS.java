/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietHoaDonDAO;
import DTO.ChiTietHoaDonDTO;
import java.util.ArrayList;

/**
 *
 * @author minhd
 */
public class ChiTietHoaDonBUS {
    public static ArrayList<ChiTietHoaDonDTO> dscthd;
    public ChiTietHoaDonBUS(){
        
    }
    public ArrayList<ChiTietHoaDonDTO> docdscthd(){
        ChiTietHoaDonDAO p=new ChiTietHoaDonDAO();
        dscthd=p.getDanhSach();
        return dscthd;
    }
    public ArrayList<ChiTietHoaDonDTO> LayCTHoaDonTheoMaHD(String mahd){
        ArrayList<ChiTietHoaDonDTO> ds =new ArrayList<>();
        for (ChiTietHoaDonDTO k : ChiTietHoaDonBUS.dscthd){
            if (k.getMaHD().equals(mahd))
                ds.add(k);
        }
        return ds;
    }
    //================= Duyên===========================//
    public int LayViTriLapCTHD(ArrayList<ChiTietHoaDonDTO> cthd, String madv){
        int j=-1;
        for(int i=0;i<cthd.size();i++){
            if(cthd.get(i).getMaDV().equals(madv)){
                j=i;
            }
        }
        return j;
    }
    public ChiTietHoaDonDTO LayDoiTuongLapCTHD(ArrayList<ChiTietHoaDonDTO> cthd, String madv){
    for(ChiTietHoaDonDTO ct : cthd){
        if(ct.getMaDV().equals(madv)){
            return ct;
        }
    }
    return null;
    }
       public int them(ChiTietHoaDonDTO lp ) {
        ChiTietHoaDonDAO lpDAO = new  ChiTietHoaDonDAO();
        lpDAO.themCTHD(lp);
        int i = ChiTietHoaDonBUS.dscthd.size();
        ChiTietHoaDonBUS.dscthd.add(lp);
        int i1 = ChiTietHoaDonBUS.dscthd.size();
        if(i1>i){
            return 1;
        }
        return 0;
    }
       
       //--------------------- Kiểm tra cdichj vụ trùng ----------------------------//
     public int kiemtraDVTrung(ArrayList<ChiTietHoaDonDTO> dsctpnhh,String test){
         int thu =0;
         for(ChiTietHoaDonDTO ctDTO : dsctpnhh){
             if(ctDTO.getMaDV().equals(test)){
                 thu = 1;
             }
         }
         return thu;
     }
     
     //--------------------- Sửa  ------------------------------//
      public void sua(ChiTietHoaDonDTO hd){
        ChiTietHoaDonDAO ctpnhDAO = new ChiTietHoaDonDAO();
        ctpnhDAO.suaCTHD(hd);
        int i=0;
            for (ChiTietHoaDonDTO k : ChiTietHoaDonBUS.dscthd){
                if (k.getMaHD().equals(hd.getMaHD()) && k.getMaDV().equals(hd.getMaDV())){
                    ChiTietHoaDonBUS.dscthd.set(i, hd);
                    break;
                }
                i++;
            }
    }
     public ChiTietHoaDonDTO lay1CT(String mahd, String madv){
         for (ChiTietHoaDonDTO k : ChiTietHoaDonBUS.dscthd){
                if (k.getMaHD().equals(mahd) && k.getMaDV().equals(madv)){
                    return k;
                }
            }
         return null;
     }  
}
