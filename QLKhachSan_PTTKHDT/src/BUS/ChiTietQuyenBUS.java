/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.ChiTietQuyenDAO;
import DTO.ChiTietQuyenDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author minhd
 */
public class ChiTietQuyenBUS {
    public static ArrayList<ChiTietQuyenDTO> dsctq;
    public ChiTietQuyenBUS(){
        
    }
    public ArrayList<ChiTietQuyenDTO> docdsctq(){
        ChiTietQuyenDAO p=new ChiTietQuyenDAO();
        dsctq=p.getDanhsach();
        return dsctq;
    }
    public ArrayList<ChiTietQuyenDTO> LayChiTietQuyen(String q){
        ArrayList<ChiTietQuyenDTO> ds1=new ArrayList<>();
        for (ChiTietQuyenDTO k : ChiTietQuyenBUS.dsctq){
            if (k.getMaQuyen().equals(q))
                ds1.add(k);
        }
        return ds1;
    }
    public int KtraDuLieu(String s){
        if (s.equals("")){
            JOptionPane.showMessageDialog(null, "Chưa chọn chức năng");
            return 0;
        }
        return 1;
    }
    public void ThemChiTietQuyenTaiKhoan(ChiTietQuyenDTO ctq){
        ChiTietQuyenDAO p=new ChiTietQuyenDAO();
        p.ThemChiTietQuyenTaiKhoan(ctq);
        ChiTietQuyenBUS.dsctq.add(ctq);
    }
    public void XoaChiTietQuyenTaiKhoan(ChiTietQuyenDTO ctq){
        ChiTietQuyenDAO p=new ChiTietQuyenDAO();
        p.XoaChiTietQuyenTaiKhoan(ctq);
        int i=0;
        for (ChiTietQuyenDTO k : ChiTietQuyenBUS.dsctq){
            if (k.getMaQuyen().equals(ctq.getMaQuyen()) && k.getMaChucNang().equals(ctq.getMaChucNang())){
                ChiTietQuyenBUS.dsctq.remove(i);
                break;
            }
            i++;
        }
    }
    public void SuaChiTietQuyenTaiKhoan(String maqcu,String maqmoi){
        int i=0;
        for (ChiTietQuyenDTO k : ChiTietQuyenBUS.dsctq){
            if (k.getMaQuyen().equals(maqcu))
                ChiTietQuyenBUS.dsctq.set(i, k).setMaQuyen(maqmoi);
            i++;
        }
    }
    public int KtraTonTai(ChiTietQuyenDTO ctq){
        for (ChiTietQuyenDTO k : ChiTietQuyenBUS.dsctq){
            if (k.getMaQuyen().equals(ctq.getMaQuyen()) && k.getMaChucNang().equals(ctq.getMaChucNang()))
                return 0;
        }
        return 1;
    }
//    public static void main(String[] args) {
//        ChiTietQuyenBUS p=new ChiTietQuyenBUS();
//    }
}
