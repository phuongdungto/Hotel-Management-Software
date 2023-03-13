/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.KhuyenMaiDichVuDAO;
import DTO.KhuyenMaiDichVuDTO;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author T P Dung
 */
public class KhuyenMaiDichVuBUS {
    public static ArrayList<KhuyenMaiDichVuDTO> dskmdv;
    public KhuyenMaiDichVuBUS(){
        
    }
    public ArrayList<KhuyenMaiDichVuDTO> docdskmdv(){
        KhuyenMaiDichVuDAO data=new KhuyenMaiDichVuDAO();
        Xulydulieu xldl = new Xulydulieu();
        data.CapNhatTrangThai(xldl.LayNgayThangNamHienTai());
        dskmdv=data.getDanhSach();
        return dskmdv;
    }
    public void them(KhuyenMaiDichVuDTO p){
        KhuyenMaiDichVuDAO data = new KhuyenMaiDichVuDAO();
        Xulydulieu xl= new Xulydulieu();
        if(xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayBD()))<=0 && xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayKT()))>=0){
            p.setTinhTrang(1);
        }else if(xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayBD()))>0 && xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayKT()))>0){
            p.setTinhTrang(3);
        }
        p.setNgayBD(p.getNgayBD());
        p.setNgayKT(p.getNgayKT());
        data.them(p);
        int i=KhuyenMaiDichVuBUS.dskmdv.size();
        KhuyenMaiDichVuBUS.dskmdv.add(p);
        int i1=KhuyenMaiDichVuBUS.dskmdv.size();
        if (i1>i)
            JOptionPane.showMessageDialog(null, "Thêm thành công");
    }
    public void sua(KhuyenMaiDichVuDTO p){
        KhuyenMaiDichVuDAO data = new KhuyenMaiDichVuDAO();
        Xulydulieu xl= new Xulydulieu();
        System.out.println(p.getNgayBD());
        if(xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayBD()))<=0 && xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayKT()))>=0){
            p.setTinhTrang(1);
        }else if(xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayBD()))>0 && xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayKT()))>0){
            p.setTinhTrang(3);
        }
        p.setNgayBD(p.getNgayBD());
        p.setNgayKT(p.getNgayKT());
        data.sua(p);
        int i=0;
        for(KhuyenMaiDichVuDTO dv1 : KhuyenMaiDichVuBUS.dskmdv){
            if(p.getMaKMDV().equals(dv1.getMaKMDV())){
                KhuyenMaiDichVuBUS.dskmdv.set(i, p);
                break;
            }
            i++;
        }
        JOptionPane.showMessageDialog(null, "Sửa thành công");
    }
    public void xoa(KhuyenMaiDichVuDTO p){
        int input=JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa", "Xóa tài khoản", JOptionPane.YES_NO_OPTION);
        if(input==0){
            KhuyenMaiDichVuDAO data = new KhuyenMaiDichVuDAO();
            data.xoa(p);
            int k=KhuyenMaiDichVuBUS.dskmdv.size();
            int i=0;
            for(KhuyenMaiDichVuDTO dv1 : KhuyenMaiDichVuBUS.dskmdv){
                if(p.getMaKMDV().equals(dv1.getMaKMDV())){
                    KhuyenMaiDichVuBUS.dskmdv.remove(i);
                    break;
                }
                i++;
            }
            int k1=KhuyenMaiDichVuBUS.dskmdv.size();
            if (k1<k)
                JOptionPane.showMessageDialog(null, "Xóa thành công");
        }
    }
   
    public String MaCuoiDS(){
        KhuyenMaiDichVuDAO data = new KhuyenMaiDichVuDAO();
        int n=data.MaCuoiDS()+1;
        Xulydulieu xldl= new Xulydulieu();
        String s=xldl.TaoMaMoi("KMDV", n);
        return s;
    }
    public ArrayList<KhuyenMaiDichVuDTO> timkiemtheoten(String s){
        ArrayList<KhuyenMaiDichVuDTO> dstk = new  ArrayList<>();
        for(KhuyenMaiDichVuDTO dv1 : KhuyenMaiDichVuBUS.dskmdv){
            if(dv1.getTen().toLowerCase().contains(s.toLowerCase())){
                dstk.add(dv1);
            }
        }
        return dstk;
    }
    public ArrayList<KhuyenMaiDichVuDTO> timkiemnangcao(int tt, String nbd, String nkt){
        ArrayList<KhuyenMaiDichVuDTO> dstimkiem = new  ArrayList<>();
        KhuyenMaiDichVuDAO data = new KhuyenMaiDichVuDAO();
        dstimkiem=data.Timkiemnangcao(tt, nbd, nkt);
        return dstimkiem;
    }
}
