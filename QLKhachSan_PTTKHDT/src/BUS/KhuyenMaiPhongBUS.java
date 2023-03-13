/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.KhuyenMaiPhongDAO;
import DTO.KhuyenMaiPhongDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author T P Dung
 */
public class KhuyenMaiPhongBUS {
    public static ArrayList<KhuyenMaiPhongDTO> dskmp;
    public KhuyenMaiPhongBUS(){
        
    }
    public ArrayList<KhuyenMaiPhongDTO> docdskmp(){
        KhuyenMaiPhongDAO data=new KhuyenMaiPhongDAO();
        Xulydulieu xldl = new Xulydulieu();
        data.CapNhatTrangThai(xldl.LayNgayThangNamHienTai());
        dskmp=data.getDanhSach();
        return dskmp;
    }
    public void them(KhuyenMaiPhongDTO p){
        KhuyenMaiPhongDAO data = new KhuyenMaiPhongDAO();
        Xulydulieu xl= new Xulydulieu();
        if(xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayBD()))<=0 && xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayKT()))>=0){
            p.setTinhTrang(1);
        }else if(xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayBD()))>0 && xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayKT()))>0){
            p.setTinhTrang(3);
        }
        p.setNgayBD(p.getNgayBD());
        p.setNgayKT(p.getNgayKT());
        data.them(p);
        int i=KhuyenMaiPhongBUS.dskmp.size();
        KhuyenMaiPhongBUS.dskmp.add(p);
        int i1=KhuyenMaiPhongBUS.dskmp.size();
        if (i1>i)
            JOptionPane.showMessageDialog(null, "Thêm thành công");
    }
    public void sua(KhuyenMaiPhongDTO p){
        KhuyenMaiPhongDAO data = new KhuyenMaiPhongDAO();
        Xulydulieu xl= new Xulydulieu();
        if(xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayBD()))<=0 && xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayKT()))>=0){
            p.setTinhTrang(1);
        }else if(xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayBD()))>0 && xl.TinhSoNgay(xl.chuyendate(xl.LayNgayThangNamHienTai()), xl.chuyendate(p.getNgayKT()))>0){
            p.setTinhTrang(3);
        }
        p.setNgayBD(p.getNgayBD());
        p.setNgayKT(p.getNgayKT());
        data.sua(p);
        int i=0;
        for(KhuyenMaiPhongDTO dv1 : KhuyenMaiPhongBUS.dskmp){
            if(p.getMaKMPhong().equals(dv1.getMaKMPhong())){
                KhuyenMaiPhongBUS.dskmp.set(i, p);
                break;
            }
            i++;
        }
        JOptionPane.showMessageDialog(null, "Sửa thành công");
    }
    public void xoa(KhuyenMaiPhongDTO p){
        int input=JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa", "Xóa tài khoản", JOptionPane.YES_NO_OPTION);
        if(input==0){
            KhuyenMaiPhongDAO data = new KhuyenMaiPhongDAO();
            data.xoa(p);
            int k=KhuyenMaiPhongBUS.dskmp.size();
            int i=0;
            for(KhuyenMaiPhongDTO dv1 : KhuyenMaiPhongBUS.dskmp){
                if(p.getMaKMPhong().equals(dv1.getMaKMPhong())){
                    KhuyenMaiPhongBUS.dskmp.remove(i);
                    break;
                }
                i++;
            }
            int k1=KhuyenMaiPhongBUS.dskmp.size();
            if (k1<k)
                JOptionPane.showMessageDialog(null, "Xóa thành công");
        }
    }
    public String MaCuoiDS(){
        KhuyenMaiPhongDAO data = new KhuyenMaiPhongDAO();
        int n=data.MaCuoiDS()+1;
        Xulydulieu xldl= new Xulydulieu();
        String s=xldl.TaoMaMoi("KMP", n);
        return s;
    }
    public ArrayList<KhuyenMaiPhongDTO> timkiemtheoten(String s){
        ArrayList<KhuyenMaiPhongDTO> dstk = new  ArrayList<>();
        for(KhuyenMaiPhongDTO dv1 : KhuyenMaiPhongBUS.dskmp){
            if(dv1.getTen().toLowerCase().contains(s.toLowerCase())){
                //System.out.println(dv1.getTen());
                dstk.add(dv1);
            }
        }
        return dstk;
    }
    public ArrayList<KhuyenMaiPhongDTO> timkiemnangcao(int tt, String nbd, String nkt){
        ArrayList<KhuyenMaiPhongDTO> dstimkiem = new  ArrayList<>();
        KhuyenMaiPhongDAO data = new KhuyenMaiPhongDAO();
        dstimkiem=data.Timkiemnangcao(tt, nbd, nkt);
        return dstimkiem;
    }
}
