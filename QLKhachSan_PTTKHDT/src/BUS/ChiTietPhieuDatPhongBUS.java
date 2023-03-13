/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietPhieuDatPhongDAO;
import DAO.KetnoiCSDL;
import DTO.ChiTietPhieuDatPhongDTO;
import DTO.PhieuDatPhongDTO;
import DTO.PhongDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author T P Dung
 */
public class ChiTietPhieuDatPhongBUS {
    public static ArrayList<ChiTietPhieuDatPhongDTO> dsctpdp;
    public ChiTietPhieuDatPhongBUS(){
        
    }
    public ArrayList<ChiTietPhieuDatPhongDTO> docdsctpdp(){
        ChiTietPhieuDatPhongDAO data=new ChiTietPhieuDatPhongDAO();
        Xulydulieu xldl = new Xulydulieu();
        dsctpdp=data.getDanhSach();
        return dsctpdp;
    }
    public int ktratrung(ChiTietPhieuDatPhongDTO p){
        for(ChiTietPhieuDatPhongDTO dv1 : ChiTietPhieuDatPhongBUS.dsctpdp){
            if(p.getMaPDP().equals(dv1.getMaPDP())){
                if(p.getMaPhong().equals(dv1.getMaPhong())){
                    return 0;
                }
            }
        }
        return 1;
    }
    public void them(ChiTietPhieuDatPhongDTO p){
        ChiTietPhieuDatPhongDAO data = new ChiTietPhieuDatPhongDAO();
        data.them(p);
        int i=ChiTietPhieuDatPhongBUS.dsctpdp.size();
        ChiTietPhieuDatPhongBUS.dsctpdp.add(p);
        int i1=ChiTietPhieuDatPhongBUS.dsctpdp.size();
        if (i1>i)
            JOptionPane.showMessageDialog(null, "Thêm thành công");
    }
    public void capnhattrangthaiphong(PhongDTO p,int x){
        ChiTietPhieuDatPhongDAO data = new ChiTietPhieuDatPhongDAO();
        int i=0;
//        System.out.println(p.getMaPhong());
        p.setTinhTrang(x);
        for(PhongDTO p1 : PhongBUS.dsp){
            if(p1.getMaPhong().equals(p.getMaPhong())){
                data.capnhattrangthaiphong(p);
                PhongBUS.dsp.set(i, p);
            }
            i++;
        }
    }
    public int ktrasoluongphong(String s){
        int t=0;
        for(ChiTietPhieuDatPhongDTO dv1 :ChiTietPhieuDatPhongBUS.dsctpdp){
            if(dv1.getMaPDP().equals(s)){
                t++;
            }
        }
        return t;
    }
    public void xoa1chitiet(ChiTietPhieuDatPhongDTO p){
        ChiTietPhieuDatPhongDAO data = new ChiTietPhieuDatPhongDAO();
        data.xoa1chitiet(p);
        int k=ChiTietPhieuDatPhongBUS.dsctpdp.size();
        int i=0;
        for(ChiTietPhieuDatPhongDTO dv1 : ChiTietPhieuDatPhongBUS.dsctpdp){
            if(dv1.getMaPDP().equals(p.getMaPDP()) && dv1.getMaPhong().equals(p.getMaPhong())){
                ChiTietPhieuDatPhongBUS.dsctpdp.remove(i);
                break;
            }
            i++;
        }
        int k1=ChiTietPhieuDatPhongBUS.dsctpdp.size();
        if (k1<k)
            JOptionPane.showMessageDialog(null, "Xóa thành công");
    }
    public int tinhtongtienthue(PhieuDatPhongDTO p){
        Xulydulieu xl = new Xulydulieu();
        int tongtien=0;
        for(ChiTietPhieuDatPhongDTO dv : ChiTietPhieuDatPhongBUS.dsctpdp){
            if(p.getMaPDP().equals(dv.getMaPDP())){
                tongtien+= dv.getDonGia();
            }
        }
        System.out.println(xl.TinhSoNgay(xl.chuyendate(p.getNgayThue()), xl.chuyendate(p.getNgayTra())));
        tongtien=(int) (tongtien*xl.TinhSoNgay(xl.chuyendate(p.getNgayThue()), xl.chuyendate(p.getNgayTra())));
        return tongtien;
    }
    public int tinhtongtienkm(PhieuDatPhongDTO p){
        Xulydulieu xl = new Xulydulieu();
        int tongtienkm=0;
        ChiTietPhieuDatPhongDTO ct = new ChiTietPhieuDatPhongDTO();
        for(ChiTietPhieuDatPhongDTO dv : ChiTietPhieuDatPhongBUS.dsctpdp){
            if(p.getMaPDP().equals(dv.getMaPDP())){
                tongtienkm+=dv.getTienKM();
            }
        }
        return tongtienkm;
    }
    public ArrayList<ChiTietPhieuDatPhongDTO> LaydsctpdpTheoMaPDP(String s){
        ArrayList<ChiTietPhieuDatPhongDTO> ds=new ArrayList<>();
        for (ChiTietPhieuDatPhongDTO k : ChiTietPhieuDatPhongBUS.dsctpdp){
            if (k.getMaPDP().equals(s))
                ds.add(k);
        }
        return ds;
    }
    
}
